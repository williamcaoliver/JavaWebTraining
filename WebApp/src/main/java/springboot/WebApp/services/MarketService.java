package springboot.WebApp.services;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.dao.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString @Getter @Setter
@Service
public class MarketService {
    private ArrayList<Order> buyOrders = new ArrayList<Order>();
    private ArrayList<Order> sellOrders = new ArrayList<Order>();
    private ArrayList<Trade> tradesList = new ArrayList<Trade>();
    private ArrayList<OrderBookEntry> aggOrderBook = new ArrayList<OrderBookEntry>();


    public boolean validateOrder(Order newOrder) {
        if (newOrder == null) {
            System.out.println("The new order is Null");
            return false;
        }

        if (newOrder.getAccountID() == null) {
            System.out.println("The new order is Null name");
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

    public List<Order> getPrivateOrders(Enums.TradeActions actions, String name){
        List<Order> filtered;
        if(actions == Enums.TradeActions.BUY){
             filtered = buyOrders.stream().filter((buyOrders) -> Objects.equals(buyOrders.getAccountID(), name)).toList();
        }else{
            filtered = sellOrders.stream().filter((sellOrders) -> Objects.equals(sellOrders.getAccountID(), name)).toList();
        }
        return  filtered;
    }

    public List<Order> getPrivateOrders(String name){
        List<Order> filteredBuy = buyOrders.stream().filter((buyOrders) -> Objects.equals(buyOrders.getAccountID(), name)).toList();
        List<Order> filteredSell = sellOrders.stream().filter((sellOrders) -> Objects.equals(sellOrders.getAccountID(), name)).toList();
        List<Order> filtered = Stream.concat(filteredBuy.stream(),filteredSell.stream()).collect(Collectors.toList());
        return filtered;

    }

}
