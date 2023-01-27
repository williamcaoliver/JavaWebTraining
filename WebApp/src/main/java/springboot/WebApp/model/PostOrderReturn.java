package springboot.WebApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.dao.Trade;

import java.util.List;

//Record new java feature where it creates a simple class, where it defines the constructor, getter, and setter methods,
// and if you want to use the object with data structures like HashMap or print the contents of its objects as a string,
// it will override methods such as equals(), hashCode(), and toString()
public record PostOrderReturn(List<OrderBookEntry> market,
                              List<Order> privateOrders,
                              List<Trade> completedTrades
                              ) {}
