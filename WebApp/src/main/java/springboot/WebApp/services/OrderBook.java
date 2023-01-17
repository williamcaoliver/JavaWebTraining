package springboot.WebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;

import java.util.Collections;
@Service
public class OrderBook {
    @Autowired private final Market market;

    public OrderBook(Market market) {
        this.market = market;
    }

    public void addOrderBookEntry(Order newOrder) {
        int existingEntryIndex = findExistingIndex(newOrder);
        if (existingEntryIndex == -1) {
            OrderBookEntry newEntry = new OrderBookEntry(newOrder.getAction(), newOrder.getPrice(), newOrder.getQuantity());
            market.getAggOrderBook().add(newEntry);
            System.out.println("\nOrderBook Added: " + newOrder.showData() + "\n");
        } else {
            aggregateOrderBook(newOrder, existingEntryIndex);
            System.out.println("Order Book Aggregated: ");
        }
        market.getAggOrderBook().sort(new OrderBookSort());

    }

    private int findExistingIndex(Order newOrder) {
        int index = -1;
        if(market.getAggOrderBook().size() != 0) {
            for (OrderBookEntry item : market.getAggOrderBook()) {
                if (newOrder.getPrice() == item.getPrice() && newOrder.getAction().equals(item.getActions())) {
                    index = market.getAggOrderBook().indexOf(item);
                    break;
                }

            }

        } return index;
    }

    private void aggregateOrderBook(Order newOrder, int objIndex) {
        market.getAggOrderBook().get(objIndex).setQuantity(market.getAggOrderBook().get(objIndex).getQuantity() + newOrder.getQuantity());
    }

    public void updateOrderBook(Order buyOrder, Order sellOrder) {
        int objBuyIndex = findExistingIndex(buyOrder);
        int objSellIndex = findExistingIndex(sellOrder);
        int orderBookBuyEntry = market.getAggOrderBook().get(objBuyIndex).getQuantity();
        int orderBookSellEntry = market.getAggOrderBook().get(objSellIndex).getQuantity();

        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            market.getAggOrderBook().get(objBuyIndex).setQuantity(orderBookBuyEntry - sellOrder.getQuantity());
            market.getAggOrderBook().get(objSellIndex).setQuantity(orderBookSellEntry - sellOrder.getQuantity());

        } else {
            market.getAggOrderBook().get(objSellIndex).setQuantity(orderBookSellEntry - buyOrder.getQuantity());
            market.getAggOrderBook().get(objBuyIndex).setQuantity(orderBookBuyEntry - buyOrder.getQuantity());
        }
        if (market.getAggOrderBook().get(objBuyIndex).getQuantity() <= 0) {
            market.getAggOrderBook().remove(objBuyIndex);
            objSellIndex = findExistingIndex(sellOrder);
        }
        if (market.getAggOrderBook().get(objSellIndex).getQuantity() <= 0) {
            market.getAggOrderBook().remove(objSellIndex);
        }
    }
}
