package springboot.WebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;

import java.util.ArrayList;
import java.util.Collections;

public class MarketController {

    @Autowired private final Market market;

    @Autowired private final Matcher matcher;

    @Autowired private final OrderBook orderbook;

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
                market.getBuyOrders().forEach(Order::showData);
                market.getBuyOrders().sort(new OrderArraySortDesc());
                System.out.println("Sorted");
                market.getBuyOrders().forEach(Order::showData);
            } else if (newOrder.getAction() == Enums.TradeActions.SELL) {
                market.getSellOrders().add(newOrder);
                System.out.println("Sell Order added to Sell Array");
                market.getSellOrders().forEach(Order::showData);
                market.getSellOrders().sort(new OrderArraySortAsc());
                System.out.println("Sorted");
                market.getSellOrders().forEach(Order::showData);

            }

            matcher.matchOrders(newOrder);

        } else {
            System.out.println("Fail");
        }
    }

}
