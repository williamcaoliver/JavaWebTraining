package springboot.WebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.dao.Trade;

import java.util.ArrayList;
import java.util.Collections;
@Service
public class Market {
    private ArrayList<Order> buyOrders = new ArrayList<Order>();
    private ArrayList<Order> sellOrders = new ArrayList<Order>();
    private ArrayList<Trade> tradesList = new ArrayList<Trade>();
    private ArrayList<OrderBookEntry> aggOrderBook = new ArrayList<OrderBookEntry>();


    public boolean validateOrder(Order newOrder) {
        if (newOrder == null) {
            System.out.println("The new order is Null");
            return false;
        }

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

    public ArrayList<Order> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(ArrayList<Order> buyOrders) {
        this.buyOrders = buyOrders;
    }

    public ArrayList<Order> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(ArrayList<Order> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public ArrayList<Trade> getTradesList() {
        return tradesList;
    }

    public void setTradesList(ArrayList<Trade> tradesList) {
        this.tradesList = tradesList;
    }

    public ArrayList<OrderBookEntry> getAggOrderBook() {
        return aggOrderBook;
    }

    public void setAggOrderBook(ArrayList<OrderBookEntry> aggOrderBook) {
        this.aggOrderBook = aggOrderBook;
    }
}
