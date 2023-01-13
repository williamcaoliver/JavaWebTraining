package springboot.WebApp.services;

import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderBookSort implements Comparator<OrderBookEntry> {

    public int compare(OrderBookEntry order1, OrderBookEntry order2) {
        String order1Action = order1.getActions().toString();
        String order2Action = order2.getActions().toString();
        if(order1Action.compareToIgnoreCase(order2Action) > 0) {
            return 1;
        }
        if(order1Action.compareToIgnoreCase(order2Action) < 0) {
            return -1;
        }
        return (order2.getPrice()-order1.getPrice());

    }


}
