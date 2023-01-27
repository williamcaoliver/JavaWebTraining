package springboot.WebApp.services;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.services.SortingService.OrderArraySortAsc;
import springboot.WebApp.services.SortingService.OrderArraySortDesc;

@ToString
@Component
public class AddOrderService {

    @Autowired
    private final MarketService marketService;

    @Autowired
    private final MatcherService matcherService;

    @Autowired
    private final OrderBookService orderbookService;

    public AddOrderService(MarketService marketService, MatcherService matcherService, OrderBookService orderbookService) {
        this.marketService = marketService;
        this.matcherService = matcherService;
        this.orderbookService = orderbookService;
    }

    public void addNewOrder(Order newOrder) {
        if (marketService.validateOrder(newOrder)) {
            orderbookService.addOrderBookEntry(newOrder);
            System.out.println("Success");
            if (newOrder.getAction() == Enums.TradeActions.BUY) {
                marketService.getBuyOrders().add(newOrder);
                System.out.println("Buy Order added to Buy Array");
                System.out.println(marketService.getBuyOrders());
                marketService.getBuyOrders().sort(new OrderArraySortDesc());
                System.out.println("Sorted");
                System.out.println(marketService.getBuyOrders());

            } else if (newOrder.getAction() == Enums.TradeActions.SELL) {
                marketService.getSellOrders().add(newOrder);
                System.out.println("Sell Order added to Sell Array");
                System.out.println(marketService.getSellOrders());
                marketService.getSellOrders().sort(new OrderArraySortAsc());
                System.out.println("Sorted");
                System.out.println(marketService.getSellOrders());


            }

            matcherService.matchOrders(newOrder);

        } else {
            System.out.println("Fail Expection");
        }
    }

}
