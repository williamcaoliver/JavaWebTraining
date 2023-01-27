package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import static org.junit.jupiter.api.Assertions.*;

class OrderBookServiceTest {
    MarketService marketService;
    OrderBookService orderBookService;

    Order newOrderBUY;
    Order newOrderSELL;
    Order noMatchOrderBUY;
    Order noMatchOrderSELL;

    @BeforeEach
    void setUp() {
        marketService = new MarketService();
        orderBookService = new OrderBookService(marketService);

        orderBookService.addOrderBookEntry(new Order("James", 10, 15, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("Alice", 50, 25, Enums.TradeActions.BUY));
        orderBookService.addOrderBookEntry(new Order("Joe", 100, 10, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("Will", 100, 10, Enums.TradeActions.BUY));
        orderBookService.addOrderBookEntry(new Order("Ronnie", 100, 10, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("James", 25, 50, Enums.TradeActions.BUY));
        orderBookService.addOrderBookEntry(new Order("Joe", 50, 10, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("Seb", 50, 50, Enums.TradeActions.BUY));
        orderBookService.addOrderBookEntry(new Order("Adam", 50, 15, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("James", 10, 15, Enums.TradeActions.SELL));
        orderBookService.addOrderBookEntry(new Order("Alice", 50, 25, Enums.TradeActions.BUY));


        newOrderBUY = new Order("James", 100, 20, Enums.TradeActions.BUY);
        newOrderSELL = new Order("Alice", 100, 10, Enums.TradeActions.SELL);
        noMatchOrderBUY = new Order("James", 30, 20, Enums.TradeActions.BUY);
        noMatchOrderSELL = new Order("Alice", 30, 30, Enums.TradeActions.SELL);

    }

    @Test
    void matchIncomingOrderWithCorrectAggEntry(){
        assertEquals(10, marketService.getAggOrderBook().get(0).getQuantity());
        orderBookService.addOrderBookEntry(newOrderBUY);
        assertEquals(30, marketService.getAggOrderBook().get(0).getQuantity());

        assertEquals(20, marketService.getAggOrderBook().get(3).getQuantity());
        orderBookService.addOrderBookEntry(newOrderSELL);
        assertEquals(30, marketService.getAggOrderBook().get(3).getQuantity());


    }

    @Test
    void noMatchIncomingOrderWithNewEntry(){

        assertEquals(6, marketService.getAggOrderBook().size());
        orderBookService.addOrderBookEntry(noMatchOrderBUY);
        assertEquals(7, marketService.getAggOrderBook().size());

        assertEquals(7, marketService.getAggOrderBook().size());
        orderBookService.addOrderBookEntry(noMatchOrderSELL);
        assertEquals(8, marketService.getAggOrderBook().size());


    }

    @Test
    void newEntrySortAgg(){
        assertEquals(25, marketService.getAggOrderBook().get(2).getPrice());
        orderBookService.addOrderBookEntry(noMatchOrderBUY);
        assertEquals(30, marketService.getAggOrderBook().get(2).getPrice());
        assertEquals(25, marketService.getAggOrderBook().get(3).getPrice());


        assertEquals(10, marketService.getAggOrderBook().get(6).getPrice());
        orderBookService.addOrderBookEntry(noMatchOrderSELL);
        assertEquals(30, marketService.getAggOrderBook().get(6).getPrice());
        assertEquals(10, marketService.getAggOrderBook().get(7).getPrice());


    }

    @Test
    void updateOrderBookBuyQuantityBigger(){
        assertEquals(10, marketService.getAggOrderBook().get(0).getQuantity());
        assertEquals(100, marketService.getAggOrderBook().get(0).getPrice());
        assertEquals(20, marketService.getAggOrderBook().get(3).getQuantity());
        orderBookService.updateOrderBook(newOrderBUY, newOrderSELL);
        assertEquals(50, marketService.getAggOrderBook().get(0).getPrice());
        assertEquals(10, marketService.getAggOrderBook().get(2).getQuantity());
    }

    @Test
    void updateOrderBookSellQuantityBigger(){
        Order newOrderSellMod = new Order("Alice", 100, 30, Enums.TradeActions.SELL);

        assertEquals(10, marketService.getAggOrderBook().get(0).getQuantity());
        assertEquals(100, marketService.getAggOrderBook().get(0).getPrice());
        assertEquals(20, marketService.getAggOrderBook().get(3).getQuantity());
        assertEquals(100, marketService.getAggOrderBook().get(3).getPrice());

        orderBookService.updateOrderBook(newOrderBUY, newOrderSellMod);

        assertEquals(50, marketService.getAggOrderBook().get(0).getPrice());
        assertEquals(50, marketService.getAggOrderBook().get(2).getPrice());
    }
}


