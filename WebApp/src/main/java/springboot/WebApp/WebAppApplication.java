package springboot.WebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.services.Matcher;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WebAppApplication.class, args);
		Matcher matcher = new Matcher();
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

		matcher.addNewOrder(newOrder);
		matcher.addNewOrder(newOrder1);
		matcher.addNewOrder(newOrder2);
		matcher.addNewOrder(newOrder3);
		matcher.addNewOrder(newOrder4);
		matcher.addNewOrder(newOrder5);
		matcher.addNewOrder(newOrder6);
		matcher.addNewOrder(newOrder7);
		matcher.addNewOrder(newOrder8);
		matcher.addNewOrder(newOrder9);
		matcher.addNewOrder(newOrder10);
		matcher.addNewOrder(newOrder11);
		matcher.addNewOrder(newOrder12);
		matcher.addNewOrder(newOrder13);
		matcher.addNewOrder(newOrder14);
		matcher.addNewOrder(newOrder15);
		matcher.addNewOrder(newOrder16);
		matcher.addNewOrder(newOrder17);
	}




}
