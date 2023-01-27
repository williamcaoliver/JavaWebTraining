package springboot.WebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.services.*;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		ApplicationContext container = SpringApplication.run(WebAppApplication.class, args);
		for(String s: container.getBeanDefinitionNames()){
			System.out.println(s);
		}

//		ApplicationContext context = new AnnotationConfigApplicationContext(MatcherConfig.class);
//		for(String i: context.getBeanDefinitionNames()){
//			System.out.println(i);
//		}
		AddOrderService addOrderService = container.getBean(AddOrderService.class);






//		Market market = new Market();
//		OrderBook orderBook = new OrderBook(market);
//		Matcher matcher = new Matcher(market, orderBook);
//
//
//
//		MarketController marketController = new MarketController(market, matcher, orderBook);
		Order newOrder = new Order("James", 10, 15, Enums.TradeActions.SELL);
		Order newOrder1 = new Order("Alice", 50, 25, Enums.TradeActions.BUY);
		Order newOrder2 = new Order("Joe", 100, 10, Enums.TradeActions.SELL);
		Order newOrder3 = new Order("Will", 100, 10, Enums.TradeActions.BUY);
		Order newOrder4 = new Order("Ronnie", 100, 10, Enums.TradeActions.SELL);
		Order newOrder5 = new Order("James", 25, 50, Enums.TradeActions.BUY);
		Order newOrder6 = new Order("Joe", 50, 10, Enums.TradeActions.BUY);
		Order newOrder7 = new Order("Seb", 50, 50, Enums.TradeActions.BUY);
		Order newOrder8 = new Order("Adam", 50, 15, Enums.TradeActions.SELL);
		Order newOrder9 = new Order("James", 10, 15, Enums.TradeActions.SELL);
		Order newOrder10 = new Order("Alice", 50, 25, Enums.TradeActions.BUY);
		Order newOrder11 = new Order("Joe", 100, 10, Enums.TradeActions.SELL);
		Order newOrder12= new Order("Will", 100, 10, Enums.TradeActions.BUY);
		Order newOrder13 = new Order("Ronnie", 100, 10, Enums.TradeActions.SELL);
		Order newOrder14 = new Order("James", 25, 50, Enums.TradeActions.BUY);
		Order newOrder15= new Order("Joe", 50, 10, Enums.TradeActions.BUY);
		Order newOrder16= new Order("Seb", 50, 50, Enums.TradeActions.BUY);
		Order newOrder17= new Order("Adam", 50, 15, Enums.TradeActions.SELL);

		addOrderService.addNewOrder(newOrder);
		addOrderService.addNewOrder(newOrder1);
		addOrderService.addNewOrder(newOrder2);
		addOrderService.addNewOrder(newOrder3);
		addOrderService.addNewOrder(newOrder4);
		addOrderService.addNewOrder(newOrder5);
		addOrderService.addNewOrder(newOrder6);
		addOrderService.addNewOrder(newOrder7);
		addOrderService.addNewOrder(newOrder8);
		addOrderService.addNewOrder(newOrder9);
		addOrderService.addNewOrder(newOrder10);
		addOrderService.addNewOrder(newOrder11);
		addOrderService.addNewOrder(newOrder12);
		addOrderService.addNewOrder(newOrder13);
		addOrderService.addNewOrder(newOrder14);
		addOrderService.addNewOrder(newOrder15);
		addOrderService.addNewOrder(newOrder16);
		addOrderService.addNewOrder(newOrder17);
	}




}
