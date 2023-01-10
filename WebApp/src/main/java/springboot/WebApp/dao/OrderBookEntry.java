package springboot.WebApp.dao;

public class OrderBookEntry {
    private Enums.TradeActions actions = Enums.TradeActions.BUY;
    private int price = 0;
    private int quantity = 0;

    public OrderBookEntry(Enums.TradeActions actionsIn, int priceIn, int quantityIn) {
        this.actions = actionsIn;
        this.price = priceIn;
        this.quantity = quantityIn;
    }

    public Enums.TradeActions getActions() {
        return actions;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setActions(Enums.TradeActions actions) {
        this.actions = actions;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
