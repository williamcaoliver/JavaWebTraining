package springboot.WebApp.services;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

@ToString
@Component
public class MarketController {

    @Autowired
    private final Market market;

    @Autowired
    private final Matcher matcher;

    @Autowired
    private final OrderBook orderbook;

    public MarketController(Market market, Matcher matcher, OrderBook orderbook) {
        this.market = market;
        this.matcher = matcher;
        this.orderbook = orderbook;
    }

    public void addNewOrder(Order newOrder) {
        if (market.validateOrder(newOrder)) {
            orderbook.addOrderBookEntry(newOrder);
            System.out.println("Success");
            if (newOrder.getAction() == Enums.TradeActions.BUY) {
                market.getBuyOrders().add(newOrder);
                System.out.println("Buy Order added to Buy Array");
                System.out.println(market.getBuyOrders());
                market.getBuyOrders().sort(new OrderArraySortDesc());
                System.out.println("Sorted");
                System.out.println(market.getBuyOrders());

            } else if (newOrder.getAction() == Enums.TradeActions.SELL) {
                market.getSellOrders().add(newOrder);
                System.out.println("Sell Order added to Sell Array");
                System.out.println(market.getSellOrders());
                market.getSellOrders().sort(new OrderArraySortAsc());
                System.out.println("Sorted");
                System.out.println(market.getSellOrders());


            }

            matcher.matchOrders(newOrder);

        } else {
            System.out.println("Fail");
        }
    }

}
