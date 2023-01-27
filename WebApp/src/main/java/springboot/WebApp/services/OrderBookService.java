package springboot.WebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.services.SortingService.OrderBookSort;

@Service
public class OrderBookService {
     @Autowired private final MarketService marketService;

    public OrderBookService(MarketService marketService) {
        this.marketService = marketService;
    }

    public void addOrderBookEntry(Order newOrder) {
        int existingEntryIndex = findExistingIndex(newOrder);
        if (existingEntryIndex == -1) {
            OrderBookEntry newEntry = new OrderBookEntry(newOrder.getAction(), newOrder.getPrice(), newOrder.getQuantity());
            marketService.getAggOrderBook().add(newEntry);
            System.out.println("\nOrderBook Added: " + newOrder + "\n");
        } else {
            aggregateOrderBook(newOrder, existingEntryIndex);
            System.out.println("Order Book Aggregated: ");
        }
        marketService.getAggOrderBook().sort(new OrderBookSort());

    }

    private int findExistingIndex(Order newOrder) {
        int index = -1;
        if(marketService.getAggOrderBook().size() != 0) {
            for (OrderBookEntry item : marketService.getAggOrderBook()) {
                if (newOrder.getPrice() == item.getPrice() && newOrder.getAction().equals(item.getAction())) {
                    index = marketService.getAggOrderBook().indexOf(item);
                    break;
                }

            }

        } return index;
    }

    private void aggregateOrderBook(Order newOrder, int objIndex) {
        marketService.getAggOrderBook().get(objIndex).setQuantity(marketService.getAggOrderBook().get(objIndex).getQuantity() + newOrder.getQuantity());
    }

    public void updateOrderBook(Order buyOrder, Order sellOrder) {
        int objBuyIndex = findExistingIndex(buyOrder);
        int objSellIndex = findExistingIndex(sellOrder);
        int orderBookBuyEntry = marketService.getAggOrderBook().get(objBuyIndex).getQuantity();
        int orderBookSellEntry = marketService.getAggOrderBook().get(objSellIndex).getQuantity();

        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            marketService.getAggOrderBook().get(objBuyIndex).setQuantity(orderBookBuyEntry - sellOrder.getQuantity());
            marketService.getAggOrderBook().get(objSellIndex).setQuantity(orderBookSellEntry - sellOrder.getQuantity());

        } else {
            marketService.getAggOrderBook().get(objSellIndex).setQuantity(orderBookSellEntry - buyOrder.getQuantity());
            marketService.getAggOrderBook().get(objBuyIndex).setQuantity(orderBookBuyEntry - buyOrder.getQuantity());
        }
        if (marketService.getAggOrderBook().get(objBuyIndex).getQuantity() <= 0) {
            marketService.getAggOrderBook().remove(objBuyIndex);
            objSellIndex = findExistingIndex(sellOrder);
        }
        if (marketService.getAggOrderBook().get(objSellIndex).getQuantity() <= 0) {
            marketService.getAggOrderBook().remove(objSellIndex);
        }
    }
}
