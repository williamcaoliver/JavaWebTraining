package springboot.WebApp.dao;

import lombok.ToString;

@ToString
public class OrderBookEntry {
    private Enums.TradeActions action;
    private int price;
    private int quantity;

    public OrderBookEntry(Enums.TradeActions action, int price, int quantity) {
        this.action = action;
        this.price = price;
        this.quantity = quantity;
    }


    public Enums.TradeActions getAction() {
        return action;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setAction(Enums.TradeActions actions) {
        this.action = actions;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
