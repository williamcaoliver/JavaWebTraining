package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MarketServiceTest {


    MarketService marketService;

    @BeforeEach
    void setUp() {
        marketService = new MarketService();
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

    }

    @Test
    void validateOrderSuccess() {
        boolean result = marketService.validateOrder(new Order("James", 10, 15, Enums.TradeActions.SELL));
        assertTrue(result);
    }

    @Test
    void validateOrderFail() {
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
    void getPrivateOrdersBuy() {

        ArrayList<Order> result = new ArrayList<>(marketService.getPrivateOrders(Enums.TradeActions.BUY, "Alice"));

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(0).getAction());
        assertEquals("Alice", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(1).getAction());


    }

    @Test
    void getPrivateOrdersSell() {
        ArrayList<Order> result = new ArrayList<>(marketService.getPrivateOrders(Enums.TradeActions.SELL, "Joe"));

        assertEquals(2, result.size());
        assertEquals("Joe", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(0).getAction());
        assertEquals("Joe", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(1).getAction());

    }

    @Test
    void getPrivateOrdersBoth() {

        ArrayList<Order> result = new ArrayList<>(marketService.getPrivateOrders("James"));

        assertEquals(3, result.size());
        assertEquals("James", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(0).getAction());
        assertEquals("James", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(1).getAction());
        assertEquals("James", result.get(2).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(2).getAction());
    }
}