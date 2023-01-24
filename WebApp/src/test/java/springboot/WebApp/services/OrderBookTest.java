package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.dao.Trade;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderBookTest {
    Market market;
    OrderBook orderBook;

    OrderBook orderBookMock;

    Order newOrder;
    @BeforeEach
    void setUp() {
        market = mock(Market.class);
        orderBookMock = mock(OrderBook.class);
        orderBook = new OrderBook(market);
        newOrder = new Order("James", 10, 15, Enums.TradeActions.BUY);
    }

    @Test
    void addOrderBookEntryCall() {
        orderBookMock.addOrderBookEntry(newOrder);
        verify(orderBookMock).addOrderBookEntry(newOrder);
    }

    @Test
    void findExistingEntry(){
        when(market.getAggOrderBook()).thenReturn(new ArrayList<OrderBookEntry>(List.of(new OrderBookEntry(Enums.TradeActions.BUY, 10, 10),
        new OrderBookEntry(Enums.TradeActions.BUY, 5, 5), new OrderBookEntry(Enums.TradeActions.SELL, 10, 20), new OrderBookEntry(Enums.TradeActions.SELL, 3, 10))));
        orderBook.addOrderBookEntry(newOrder);
        verify(orderBook).;
    }

    @Test
    void updateOrderBook() {
    }
}