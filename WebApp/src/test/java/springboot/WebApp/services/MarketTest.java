package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import java.util.ArrayList;
import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MarketTest {


    Market market;

    @BeforeEach
    void setUp() {
        market = new Market();
        market.getSellOrders().add(new Order("James", 10, 15, Enums.TradeActions.SELL));
        market.getBuyOrders().add(new Order("Alice", 50, 25, Enums.TradeActions.BUY));
        market.getSellOrders().add(new Order("Joe", 100, 10, Enums.TradeActions.SELL));
        market.getBuyOrders().add(new Order("Will", 100, 10, Enums.TradeActions.BUY));
        market.getSellOrders().add(new Order("Ronnie", 100, 10, Enums.TradeActions.SELL));
        market.getBuyOrders().add(new Order("James", 25, 50, Enums.TradeActions.BUY));
        market.getSellOrders().add(new Order("Joe", 50, 10, Enums.TradeActions.SELL));
        market.getBuyOrders().add(new Order("Seb", 50, 50, Enums.TradeActions.BUY));
        market.getSellOrders().add(new Order("Adam", 50, 15, Enums.TradeActions.SELL));
        market.getSellOrders().add(new Order("James", 10, 15, Enums.TradeActions.SELL));
        market.getBuyOrders().add(new Order("Alice", 50, 25, Enums.TradeActions.BUY));

    }

    @Test
    void validateOrderSuccess() {
        boolean result = market.validateOrder(new Order("James", 10, 15, Enums.TradeActions.SELL));
        assertTrue(result);
    }

    @Test
    void validateOrderFail() {
        boolean result1 = market.validateOrder(new Order(null, 10, 15, Enums.TradeActions.SELL));
        boolean result2 = market.validateOrder(new Order("James", 10, 15, null));
        boolean result3 = market.validateOrder(new Order("James", -10, 15, Enums.TradeActions.SELL));
        boolean result4 = market.validateOrder(new Order("James", 10, 0, Enums.TradeActions.SELL));

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    void getPrivateOrdersBuy() {

        ArrayList<Order> result = new ArrayList<>(market.getPrivateOrders(Enums.TradeActions.BUY, "Alice"));

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(0).getAction());
        assertEquals("Alice", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(1).getAction());


    }

    @Test
    void getPrivateOrdersSell() {
        ArrayList<Order> result = new ArrayList<>(market.getPrivateOrders(Enums.TradeActions.SELL, "Joe"));

        assertEquals(2, result.size());
        assertEquals("Joe", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(0).getAction());
        assertEquals("Joe", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(1).getAction());

    }

    @Test
    void getPrivateOrdersBoth() {

        ArrayList<Order> result = new ArrayList<>(market.getPrivateOrders("James"));

        assertEquals(3, result.size());
        assertEquals("James", result.get(0).getAccountID());
        assertEquals(Enums.TradeActions.BUY, result.get(0).getAction());
        assertEquals("James", result.get(1).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(1).getAction());
        assertEquals("James", result.get(2).getAccountID());
        assertEquals(Enums.TradeActions.SELL, result.get(2).getAction());
    }
}