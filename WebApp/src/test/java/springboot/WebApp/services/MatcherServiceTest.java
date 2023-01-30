package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import static org.junit.jupiter.api.Assertions.*;


class MatcherServiceTest {

    MatcherService matcherService;
    MarketService marketService;
    OrderBookService orderBookService;
    AddOrderService addOrderService;
    @BeforeEach
    void setup(){
        marketService = new MarketService();
        orderBookService = new OrderBookService(marketService);
        //Mocking database
        matcherService = new MatcherService(marketService, orderBookService);
        addOrderService = new AddOrderService(marketService,matcherService,orderBookService);


        addOrderService.addNewOrder(new Order("Will", 100, 10, Enums.TradeActions.BUY));
        addOrderService.addNewOrder(new Order("Alice", 50, 25, Enums.TradeActions.BUY));
        addOrderService.addNewOrder(new Order("Seb", 50, 50, Enums.TradeActions.BUY));
        addOrderService.addNewOrder(new Order("Alice", 50, 25, Enums.TradeActions.BUY));
        addOrderService.addNewOrder(new Order("James", 25, 50, Enums.TradeActions.BUY));

        addOrderService.addNewOrder(new Order("James", 10, 15, Enums.TradeActions.SELL));
        addOrderService.addNewOrder(new Order("James", 10, 15, Enums.TradeActions.SELL));
        addOrderService.addNewOrder(new Order("Joe", 50, 10, Enums.TradeActions.SELL));
        addOrderService.addNewOrder(new Order("Adam", 50, 15, Enums.TradeActions.SELL));
        addOrderService.addNewOrder(new Order("Joe", 100, 10, Enums.TradeActions.SELL));
        addOrderService.addNewOrder(new Order("Ronnie", 100, 10, Enums.TradeActions.SELL));

    }
    @Test
    void correctlyMatchBuyOrder() {
        assertEquals(6, marketService.getTradesList().size());
        Order newOrder = new Order("James", 120, 10, Enums.TradeActions.BUY);
        addOrderService.addNewOrder(newOrder);
        assertEquals(7, marketService.getTradesList().size());
        assertEquals(newOrder, marketService.getTradesList().get(6).getBuyOrder());

    }

    @Test
    void correctlyMatchSellOrder() {
        assertEquals(6, marketService.getTradesList().size());
        Order newOrder = new Order("James", 40, 10, Enums.TradeActions.SELL);
        addOrderService.addNewOrder(newOrder);
        assertEquals(7, marketService.getTradesList().size());
        assertEquals(newOrder, marketService.getTradesList().get(6).getSellOrder());
    }

    @Test
    void noMatchExceptionCalled() {

    }

    @Test
    void tradeObjectIsMadeAndAdded(){
        Order newOrder = new Order("James", 40, 10, Enums.TradeActions.SELL);
        addOrderService.addNewOrder(newOrder);
        assertEquals(newOrder, marketService.getTradesList().get(6).getSellOrder());
        assertEquals(50, marketService.getTradesList().get(6).getAgreedPrice());
        assertEquals(10, marketService.getTradesList().get(6).getAgreedQuantity());

    }

    @Test
    void correctlyMatchConsecutiveOrdersBUY() {
        assertEquals(6, marketService.getTradesList().size());
        Order newOrder = new Order("James", 120, 15, Enums.TradeActions.BUY);
        addOrderService.addNewOrder(newOrder);
        assertEquals(8, marketService.getTradesList().size());
        assertEquals(newOrder, marketService.getTradesList().get(6).getBuyOrder());
        assertEquals(newOrder, marketService.getTradesList().get(7).getBuyOrder());

    }

    @Test
    void correctlyMatchConsecutiveOrdersSELL() {
        assertEquals(6, marketService.getTradesList().size());
        Order newOrder = new Order("James", 20, 60, Enums.TradeActions.SELL);
        addOrderService.addNewOrder(newOrder);
        assertEquals(9, marketService.getTradesList().size());
        assertEquals(newOrder, marketService.getTradesList().get(6).getSellOrder());
        assertEquals(newOrder, marketService.getTradesList().get(7).getSellOrder());
        assertEquals(newOrder, marketService.getTradesList().get(8).getSellOrder());

    }

    @Test
    void correctDifferenceOfQuantityWhenMatchedBUY() {
        Order newOrder = new Order("James", 120, 5, Enums.TradeActions.BUY);
        assertEquals(10, marketService.getSellOrders().get(0).getQuantity());
        addOrderService.addNewOrder(newOrder);
        assertEquals(5, marketService.getSellOrders().get(0).getQuantity());
    }

    @Test
    void correctDifferenceOfQuantityWhenMatchedSELL() {
        Order newOrder = new Order("James", 40, 15, Enums.TradeActions.SELL);
        assertEquals(30, marketService.getBuyOrders().get(0).getQuantity());
        addOrderService.addNewOrder(newOrder);
        assertEquals(15, marketService.getBuyOrders().get(0).getQuantity());
    }

    @Test
    void correctRemovalOfOrdersWhenNoQuantity() {
        Order newOrder = new Order("James", 20, 60, Enums.TradeActions.SELL);
        assertEquals(50, marketService.getBuyOrders().get(0).getPrice());
        assertEquals(50, marketService.getBuyOrders().get(1).getPrice());
        assertEquals(25, marketService.getBuyOrders().get(2).getPrice());
        addOrderService.addNewOrder(newOrder);
        assertEquals(25, marketService.getBuyOrders().get(0).getPrice());
    }


}