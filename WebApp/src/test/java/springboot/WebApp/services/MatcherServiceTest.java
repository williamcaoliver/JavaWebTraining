package springboot.WebApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MatcherServiceTest {
    //does need mocktio
    MatcherService matcherService;
    MarketService marketService;
    OrderBookService orderBookService;
    @BeforeEach
    void setup(){
        marketService = mock(MarketService.class);
        orderBookService = mock(OrderBookService.class);
        //Mocking database
        matcherService = new MatcherService(marketService, orderBookService);
    }
    @Test
    void matchOrders() {

    }

    @Test
    void matchBuyerCalled(){
        Order order1 = new Order("Alice", 50, 25, Enums.TradeActions.BUY);
        matcherService.matchOrders(order1);


    }
}