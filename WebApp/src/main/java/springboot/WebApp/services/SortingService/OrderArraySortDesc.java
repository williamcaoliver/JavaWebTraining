package springboot.WebApp.services.SortingService;
import springboot.WebApp.dao.Order;
import java.util.Comparator;

public class OrderArraySortDesc implements Comparator<Order> {

    public int compare(Order order1, Order order2){
        if (order2.getPrice() == order1.getPrice()) {
            int dateTimeDiff = (int)(order1.getDateTime() - order2.getDateTime());
            return dateTimeDiff;
        }
        return order2.getPrice() - order1.getPrice();
    }
}
