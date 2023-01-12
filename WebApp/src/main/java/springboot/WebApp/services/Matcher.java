package springboot.WebApp.services;

import springboot.WebApp.dao.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Matcher {
    private ArrayList<Order> buyOrders = new ArrayList<Order>();
    private ArrayList<Order> sellOrders = new ArrayList<Order>();
    private ArrayList<Trade> tradesList = new ArrayList<Trade>();
    private ArrayList<OrderBookEntry> aggOrderBook = new ArrayList<OrderBookEntry>();

    public void addNewOrder(Order newOrder) {
        if (validateOrder(newOrder)) {
            addOrderBookEntry(newOrder);
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
                i--;
                updateOrderBook(newOrder, currentSellOrder);
                completeTrade(newOrder, currentSellOrder);
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
                i--;
                updateOrderBook(currentBuyOrder, newOrder);
                completeTrade(currentBuyOrder, newOrder);

            }


        }


    }


    private void completeTrade(Order buyOrder, Order sellOrder) {
        System.out.println("\nBefore Complete Trade\nBuyOrder: " +
                buyOrder.showData() +
                "\nSellOrder: " +
                sellOrder.showData());

        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            buyOrder.setQuantity((buyOrder.getQuantity() - sellOrder.getQuantity()));
            sellOrder.setQuantity(0);
        } else {
            sellOrder.setQuantity((sellOrder.getQuantity()) - buyOrder.getQuantity());
            buyOrder.setQuantity(0);

        }
        cleanUp(buyOrder, sellOrder);
        System.out.println("\nAfter Complete Trade\nBuyOrder: " +
                buyOrder.showData() +
                "\nSellOrder: " +
                sellOrder.showData());
    }

    private void cleanUp(Order buyOrder, Order sellOrder) {

        if (buyOrder.getQuantity() == 0) {
            buyOrders.remove(buyOrders.indexOf(buyOrder));
        }
        if (sellOrder.getQuantity() == 0) {
            sellOrders.remove(sellOrders.indexOf(sellOrder));
        }
    }

    private void addOrderBookEntry(Order newOrder) {
        int existingEntryIndex = findExistingIndex(newOrder);
        if (existingEntryIndex == -1) {
            OrderBookEntry newEntry = new OrderBookEntry(newOrder.getAction(), newOrder.getPrice(), newOrder.getQuantity());
            aggOrderBook.add(newEntry);
            System.out.println("\nOrderBook Added: " + newOrder.showData() + "\n");
        } else {
            aggregateOrderBook(newOrder, existingEntryIndex);
            System.out.println("Order Book Aggregated: ");
        }
        Collections.sort(aggOrderBook, new OrderBookSort());

    }

    private int findExistingIndex(Order newOrder) {
        int index = -1;
        if(aggOrderBook.size() != 0) {
            for (OrderBookEntry item : aggOrderBook) {
                if (newOrder.getPrice() == item.getPrice() && newOrder.getAction().equals(item.getActions())) {
                    index = aggOrderBook.indexOf(item);
                    break;
                }

            }

        } return index;
    }

    private void aggregateOrderBook(Order newOrder, int objIndex) {
        aggOrderBook.get(objIndex).setQuantity(aggOrderBook.get(objIndex).getQuantity() + newOrder.getQuantity());
    }

    private void updateOrderBook(Order buyOrder, Order sellOrder) {
        int objBuyIndex = findExistingIndex(buyOrder);
        int objSellIndex = findExistingIndex(sellOrder);
        int orderBookBuyEntry = aggOrderBook.get(objBuyIndex).getQuantity();
        int orderBookSellEntry = aggOrderBook.get(objSellIndex).getQuantity();

        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            aggOrderBook.get(objBuyIndex).setQuantity(orderBookBuyEntry - sellOrder.getQuantity());
            aggOrderBook.get(objSellIndex).setQuantity(orderBookSellEntry - sellOrder.getQuantity());

        } else {
            aggOrderBook.get(objSellIndex).setQuantity(orderBookSellEntry - buyOrder.getQuantity());
            aggOrderBook.get(objBuyIndex).setQuantity(orderBookBuyEntry - buyOrder.getQuantity());
        }
        if (aggOrderBook.get(objBuyIndex).getQuantity() <= 0) {
            aggOrderBook.remove(objBuyIndex);
            objSellIndex = findExistingIndex(sellOrder);
        }
        if (aggOrderBook.get(objSellIndex).getQuantity() <= 0) {
            aggOrderBook.remove(objSellIndex);
        }
    }

//    public ArrayList<Order> getPrivateBook(String name) {
//re
//    }
//
//    public ArrayList<Trade> getTradesList() {
//        return tradesList;
//    }
//
//    public ArrayList<OrderBookEntry> getAggOrderBook() {
//        return aggOrderBook;
//    }

}
