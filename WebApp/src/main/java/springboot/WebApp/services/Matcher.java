package springboot.WebApp.services;

import springboot.WebApp.dao.*;

import java.util.ArrayList;
import java.util.Collections;

public class Matcher {
    private ArrayList<Order> buyOrders = new ArrayList<Order>();
    private ArrayList<Order> sellOrders = new ArrayList<Order>();
    private ArrayList<Trade> tradesList = new ArrayList<Trade>();
    private ArrayList<OrderBookEntry> aggOrderBook = new ArrayList<OrderBookEntry>();

    public void addNewOrder(Order newOrder) {
        if (validateOrder(newOrder)) {
            System.out.println("Success");
            if (newOrder.getAction() == Enums.TradeActions.BUY) {
                buyOrders.add(newOrder);
                System.out.println("Buy Order added to Buy Array");
                buyOrders.forEach(Order::showData);
                Collections.sort(buyOrders, new OrderArraySortDesc());
                System.out.println("Sorted");
                buyOrders.forEach(Order::showData);
            } else if (newOrder.getAction() == Enums.TradeActions.SELL) {
                sellOrders.add(newOrder);
                System.out.println("Sell Order added to Sell Array");
                sellOrders.forEach(Order::showData);
                Collections.sort(sellOrders, new OrderArraySortAsc());
                System.out.println("Sorted");
                sellOrders.forEach(Order::showData);

            }
            matchOrders(newOrder);

        } else {
            System.out.println("Fail");
        }
    }

    private boolean validateOrder(Order newOrder) {
        if (newOrder == null) {
            System.out.println("The new order is Null");
            return false;
        }
//      for (Enums.TradeActions action : Enums.TradeActions.values()){
//
//            if(!(action.equals(newOrder.getAction()))){
//                System.out.println("Order action is invalid");
//            }
//        }
        if (newOrder.getAction() == null) {
            System.out.println("No Order Action: " + newOrder.getAction());
            return false;
        }
        if (newOrder.getPrice() <= 0) {
            System.out.println("Price below 0 :" + newOrder.getPrice());
            return false;
        }
        if (newOrder.getQuantity() <= 0) {
            System.out.println("Quantity below 0 :" + newOrder.getQuantity());
            return false;
        }

        return true;

    }

    private void matchOrders(Order newOrder) {
        if (newOrder.getAction() == Enums.TradeActions.BUY && sellOrders.size() != 0) {
            matchBuyer(newOrder);
            tradesList.forEach(Trade::showData);
            System.out.println("End");
        } else if (newOrder.getAction() == Enums.TradeActions.SELL && buyOrders.size() != 0) {
            matchSeller(newOrder);
            tradesList.forEach(Trade::showData);
            System.out.println("End");
        } else {
            System.out.println("No match");
        }
    }

    private void matchBuyer(Order newOrder) {
        for (int i = 0; i < sellOrders.size(); i++) {
            Order currentSellOrder = sellOrders.get(i);
            if (newOrder.getPrice() >= currentSellOrder.getPrice() && newOrder.getQuantity() != 0) {
                Trade tradedObj = new Trade(newOrder, currentSellOrder, newOrder.getPrice(), Math.min(newOrder.getQuantity(), currentSellOrder.getQuantity()));
                tradesList.add(tradedObj);
                System.out.println("Matched BUY");
                System.out.println("Matching End");

            }


        }
    }

    private void matchSeller(Order newOrder) {
        for (int i = 0; i < buyOrders.size(); i++) {
            Order currentBuyOrder = buyOrders.get(i);
            if (newOrder.getPrice() >= currentBuyOrder.getPrice() && newOrder.getQuantity() != 0) {
                Trade tradedObj = new Trade(currentBuyOrder, newOrder, currentBuyOrder.getPrice(), Math.min(newOrder.getQuantity(), currentBuyOrder.getQuantity()));
                tradesList.add(tradedObj);
                System.out.println("Matched Sell");
                System.out.println("Matching End");

            }


        }
    }


}
