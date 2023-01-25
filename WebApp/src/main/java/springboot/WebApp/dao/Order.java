package springboot.WebApp.dao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.Date;
@ToString
public class Order {
    private String accountID;
    private int price;
    private int quantity;
    private Enums.TradeActions action;
    private long dateTime;

    public Order(String accountID, int price, int quantity, Enums.TradeActions action) {
        this.accountID = accountID;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
        this.dateTime = new Date().getTime();

    }



    public String getAccountID() {
        return accountID;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Enums.TradeActions getAction() {
        return action;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAction(Enums.TradeActions action) {
        this.action = action;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
