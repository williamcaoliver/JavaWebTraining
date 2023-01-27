package springboot.WebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.Trade;

@Service
public class MatcherService {

    @Autowired
    private final MarketService marketService;
    @Autowired
    private final OrderBookService orderBookService;

    public MatcherService(MarketService marketService, OrderBookService orderBookService) {
        this.marketService = marketService;
        this.orderBookService = orderBookService;
    }

    public void matchOrders(Order newOrder) {
        if (newOrder.getAction() == Enums.TradeActions.BUY && marketService.getSellOrders().size() != 0) {
            matchBuyer(newOrder);
            System.out.println(marketService.getTradesList());
            System.out.println("End");
        } else if (newOrder.getAction() == Enums.TradeActions.SELL && marketService.getBuyOrders().size() != 0) {
            matchSeller(newOrder);
            System.out.println(marketService.getTradesList());
            System.out.println("End");
        } else {
            System.out.println("No match");
        }
    }

    private void matchBuyer(Order newOrder) {
        for (int i = 0; i < marketService.getSellOrders().size(); i++) {
            Order currentSellOrder = marketService.getSellOrders().get(i);
            if (newOrder.getPrice() >= currentSellOrder.getPrice() && newOrder.getQuantity() != 0) {
                Trade tradedObj = new Trade(newOrder, currentSellOrder, newOrder.getPrice(), Math.min(newOrder.getQuantity(), currentSellOrder.getQuantity()));
                marketService.getTradesList().add(tradedObj);
                System.out.println("Matched BUY");
                System.out.println("Matching End");
                i--;
                orderBookService.updateOrderBook(newOrder, currentSellOrder);
                completeTrade(newOrder, currentSellOrder);
            }


        }
    }

    private void matchSeller(Order newOrder) {
        for (int i = 0; i < marketService.getBuyOrders().size(); i++) {
            Order currentBuyOrder = marketService.getBuyOrders().get(i);
            if (newOrder.getPrice() <= currentBuyOrder.getPrice() && newOrder.getQuantity() != 0) {
                Trade tradedObj = new Trade(currentBuyOrder, newOrder, currentBuyOrder.getPrice(), Math.min(newOrder.getQuantity(), currentBuyOrder.getQuantity()));
                marketService.getTradesList().add(tradedObj);
                System.out.println("Matched Sell");
                System.out.println("Matching End");
                i--;
                orderBookService.updateOrderBook(currentBuyOrder, newOrder);
                completeTrade(currentBuyOrder, newOrder);

            }


        }


    }


    private void completeTrade(Order buyOrder, Order sellOrder) {
        System.out.println("\nBefore Complete Trade\nBuyOrder: " +
                buyOrder +
                "\nSellOrder: " +
                sellOrder);

        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            buyOrder.setQuantity((buyOrder.getQuantity() - sellOrder.getQuantity()));
            sellOrder.setQuantity(0);
        } else {
            sellOrder.setQuantity((sellOrder.getQuantity()) - buyOrder.getQuantity());
            buyOrder.setQuantity(0);

        }
        cleanUp(buyOrder, sellOrder);
        System.out.println("\nAfter Complete Trade\nBuyOrder: " +
                buyOrder +
                "\nSellOrder: " +
                sellOrder);
    }

    private void cleanUp(Order buyOrder, Order sellOrder) {

        if (buyOrder.getQuantity() == 0) {
            marketService.getBuyOrders().remove(buyOrder);
        }
        if (sellOrder.getQuantity() == 0) {
            marketService.getSellOrders().remove(sellOrder);
        }
    }

}

