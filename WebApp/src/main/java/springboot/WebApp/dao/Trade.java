package springboot.WebApp.dao;
import lombok.ToString;

import java.util.Date;
@ToString
public class Trade {
    private Order buyOrder;
    private Order sellOrder;
    private int agreedPrice;
    private int agreedQuantity;
    private long dateTime;

    public Trade(Order buyOrder, Order sellOrder, int agreedPrice, int agreedQuantity) {
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        this.agreedPrice = agreedPrice;
        this.agreedQuantity = agreedQuantity;
        this.dateTime = new Date().getTime();
    }

    public Order getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(Order buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Order getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(Order sellOrder) {
        this.sellOrder = sellOrder;
    }

    public int getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(int agreedPrice) {
        this.agreedPrice = agreedPrice;
    }

    public int getAgreedQuantity() {
        return agreedQuantity;
    }

    public void setAgreedQuantity(int agreedQuantity) {
        this.agreedQuantity = agreedQuantity;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
