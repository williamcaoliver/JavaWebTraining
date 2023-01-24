package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketControllerTest {

    //Needs mocktio
    Market market;

    Matcher matcher;

    OrderBook orderbook;

    MarketController marketController;

    Order newOrder;

    @BeforeEach
    void setup(){
        market = Mockito.mock(Market.class);
        matcher = Mockito.mock(Matcher.class);
        orderbook = Mockito.mock(OrderBook.class);
        marketController = new MarketController(market, matcher, orderbook);
        newOrder = new Order("James", 10, 15, Enums.TradeActions.BUY);
    }
    @Test
    void validateOrderCalled() {
        marketController.addNewOrder(newOrder);
        Mockito.verify(market).validateOrder(newOrder);
    }

    @Test
    void addOrderToOrderBook(){
        Mockito.when(market.validateOrder(newOrder)).thenReturn(true);
        marketController.addNewOrder(newOrder);
        Mockito.verify(orderbook).addOrderBookEntry(newOrder);
    }

    @Test
    void addNewBuyOrderToMarket(){
        //Setup Mock Returns
        Mockito.when(market.validateOrder(newOrder)).thenReturn(true);
        marketController.addNewOrder(newOrder);
        Mockito.verify(market, Mockito.times(4)).getBuyOrders();

    }

    @Test
    void addNewSellOrderToMarket(){
        Order newSell = new Order("James", 10, 15, Enums.TradeActions.SELL);
        //Setup Mock Returns
        Mockito.when(market.validateOrder(newSell)).thenReturn(true);
        marketController.addNewOrder(newSell);
        Mockito.verify(market, Mockito.times(4)).getSellOrders();

    }

    @Test
    void callMatcher(){
        Mockito.when(market.validateOrder(newOrder)).thenReturn(true);
        marketController.addNewOrder(newOrder);
        Mockito.verify(matcher).matchOrders(newOrder);

    }

    @Test
    void sortOrdersDesc(){
        Mockito.when(market.validateOrder(newOrder)).thenReturn(true);
        Mockito.when(market.getBuyOrders()).thenReturn(new ArrayList<Order>(List.of(new Order("Will", 10, 10, Enums.TradeActions.BUY))))
        marketController.addNewOrder(newOrder);
    }

    @Test
    void sortOrderAsc(){

    }
}