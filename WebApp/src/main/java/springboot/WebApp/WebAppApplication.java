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
		Order newOrder6 = new Order("Joe", 15, 10, Enums.TradeActions.BUY);
		Order newOrder7 = new Order("Seb", 50, 50, Enums.TradeActions.BUY);
		Order newOrder8 = new Order("Adam", 30, 15, Enums.TradeActions.SELL);

		matcher.addNewOrder(newOrder);
		matcher.addNewOrder(newOrder1);
		matcher.addNewOrder(newOrder2);
		matcher.addNewOrder(newOrder3);
		matcher.addNewOrder(newOrder4);
		matcher.addNewOrder(newOrder5);
		matcher.addNewOrder(newOrder6);
		matcher.addNewOrder(newOrder7);
		matcher.addNewOrder(newOrder8);
	}




}
