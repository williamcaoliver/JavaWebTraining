package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.services.SortingService.OrderArraySortAsc;
import springboot.WebApp.services.SortingService.OrderArraySortDesc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddOrderServiceTest {

    //Needs mocktio
    MarketService mockMarketService;
    MatcherService mockMatcherService;
    OrderBookService mockOrderBookService;
    AddOrderService mockAddOrderService;

    MarketService marketService;
    MatcherService matcherService;
    OrderBookService orderBookService;
    AddOrderService addOrderService;

    Order newOrderBUY;

    Order newOrderSELL;

    @BeforeEach
    void setup(){
        mockMarketService = Mockito.mock(MarketService.class);
        mockMatcherService = Mockito.mock(MatcherService.class);
        mockOrderBookService = Mockito.mock(OrderBookService.class);
        mockAddOrderService = new AddOrderService(mockMarketService, mockMatcherService, mockOrderBookService);

        marketService = new MarketService();
        orderBookService = new OrderBookService(marketService);
        matcherService = new MatcherService(marketService, orderBookService);
        addOrderService = new AddOrderService(marketService, matcherService, orderBookService);

        newOrderBUY = new Order("James", 10, 15, Enums.TradeActions.BUY);
        newOrderSELL = new Order("Alice", 15, 15, Enums.TradeActions.SELL);
    }
    @Test
    void validateOrderCalled() {
        mockAddOrderService.addNewOrder(newOrderBUY);
        Mockito.verify(mockMarketService).validateOrder(newOrderBUY);
    }

    @Test
    void validOrderReturnTrue(){
        assertTrue(marketService.validateOrder(newOrderBUY));
        assertTrue(marketService.validateOrder(newOrderSELL));

    }

    @Test
    void validOrderReturnFalse(){
        boolean result1 = marketService.validateOrder(new Order(null, 10, 15, Enums.TradeActions.SELL));
        boolean result2 = marketService.validateOrder(new Order("James", 10, 15, null));
        boolean result3 = marketService.validateOrder(new Order("James", -10, 15, Enums.TradeActions.SELL));
        boolean result4 = marketService.validateOrder(new Order("James", 10, 0, Enums.TradeActions.SELL));

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    void addOrderToOrderBookCalled(){
        Mockito.when(mockMarketService.validateOrder(newOrderBUY)).thenReturn(true);
        mockAddOrderService.addNewOrder(newOrderBUY);
        Mockito.verify(mockOrderBookService).addOrderBookEntry(newOrderBUY);
    }

    @Test
    void addOrderToOrderBook(){
        addOrderService.addNewOrder(newOrderBUY);
        OrderBookEntry newEntryBUY = new OrderBookEntry(newOrderBUY.getAction(), newOrderBUY.getPrice(), newOrderBUY.getQuantity());
        assertEquals(newEntryBUY.getAction(), marketService.getAggOrderBook().get(0).getAction());
        assertEquals(newEntryBUY.getPrice(), marketService.getAggOrderBook().get(0).getPrice());
        assertEquals(newEntryBUY.getQuantity(), marketService.getAggOrderBook().get(0).getQuantity());

    }

    @Test
    void buyOrderFollowedBuyRoute(){
        //Setup Mock Returns
        Mockito.when(mockMarketService.validateOrder(newOrderBUY)).thenReturn(true);
        mockAddOrderService.addNewOrder(newOrderBUY);
        Mockito.verify(mockMarketService, Mockito.times(4)).getBuyOrders();

    }

    @Test
    void sellOrderFollowedSellRoute(){
        //Setup Mock Returns
        Mockito.when(mockMarketService.validateOrder(newOrderSELL)).thenReturn(true);
        mockAddOrderService.addNewOrder(newOrderSELL);
        Mockito.verify(mockMarketService, Mockito.times(4)).getSellOrders();

    }

    @Test
    void buyOrderAddedToMarket(){
        addOrderService.addNewOrder(newOrderBUY);
        ArrayList<Order> buyOrders = marketService.getBuyOrders();
        assertEquals(newOrderBUY.getAccountID(), buyOrders.get(0).getAccountID());
    }

    @Test
    void sellOrderAddedToMarket(){
        addOrderService.addNewOrder(newOrderSELL);
        ArrayList<Order> sellOrders = marketService.getSellOrders();
        assertEquals(newOrderSELL.getAccountID(), sellOrders.get(0).getAccountID());
    }

    @Test
    void returnSortedList(){
        marketService.getSellOrders().add(new Order("James", 10, 15, Enums.TradeActions.SELL));
        marketService.getBuyOrders().add(new Order("Alice", 50, 25, Enums.TradeActions.BUY));
        marketService.getSellOrders().add(new Order("Joe", 100, 10, Enums.TradeActions.SELL));
        marketService.getBuyOrders().add(new Order("Will", 100, 10, Enums.TradeActions.BUY));
        marketService.getSellOrders().add(new Order("Ronnie", 100, 10, Enums.TradeActions.SELL));
        marketService.getBuyOrders().add(new Order("James", 25, 50, Enums.TradeActions.BUY));
        marketService.getSellOrders().add(new Order("Joe", 50, 10, Enums.TradeActions.SELL));
        marketService.getBuyOrders().add(new Order("Seb", 50, 50, Enums.TradeActions.BUY));
        marketService.getSellOrders().add(new Order("Adam", 50, 15, Enums.TradeActions.SELL));
        marketService.getSellOrders().add(new Order("James", 10, 15, Enums.TradeActions.SELL));
        marketService.getBuyOrders().add(new Order("Alice", 50, 25, Enums.TradeActions.BUY));

        marketService.getBuyOrders().sort(new OrderArraySortDesc());
        marketService.getSellOrders().sort(new OrderArraySortAsc());

        assertEquals(100, marketService.getBuyOrders().get(0).getPrice());
        assertEquals(50, marketService.getBuyOrders().get(1).getPrice());
        assertEquals(50, marketService.getBuyOrders().get(2).getPrice());
        assertEquals(50, marketService.getBuyOrders().get(3).getPrice());
        assertEquals(25, marketService.getBuyOrders().get(4).getPrice());

        assertEquals(10, marketService.getSellOrders().get(0).getPrice());
        assertEquals(10, marketService.getSellOrders().get(1).getPrice());
        assertEquals(50, marketService.getSellOrders().get(2).getPrice());
        assertEquals(50, marketService.getSellOrders().get(3).getPrice());
        assertEquals(100, marketService.getSellOrders().get(4).getPrice());
        assertEquals(100, marketService.getSellOrders().get(5).getPrice());

    }

    @Test
    void callMatcher(){
        Mockito.when(mockMarketService.validateOrder(newOrderBUY)).thenReturn(true);
        mockAddOrderService.addNewOrder(newOrderBUY);
        Mockito.verify(mockMatcherService).matchOrders(newOrderBUY);

    }

}