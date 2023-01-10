package springboot.WebApp.dao;
import java.util.Date;

public class Order {
    private String accountID = "";
    private int price = 0;
    private int quantity = 0;
    private Enums.TradeActions action = Enums.TradeActions.BUY;
    private long dateTime = 0;

    public Order(String name, int priceIn, int quantityIn, Enums.TradeActions actionIn){
        this.accountID = name;
        this.price = priceIn;
        this.quantity = quantityIn;
        this.action = actionIn;
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
