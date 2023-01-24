package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MatcherTest {
    //does need mocktio
    Matcher matcher;
    Market market;
    OrderBook orderBook;
    @BeforeEach
    void setup(){
        market = mock(Market.class);
        orderBook = mock(OrderBook.class);
        matcher = new Matcher(market, orderBook);
    }
    @Test
    void matchOrders() {

    }

    @Test
    void matchBuyerCalled(){
        Order order1 = new Order("Alice", 50, 25, Enums.TradeActions.BUY);
        matcher.matchOrders(order1);


    }
}