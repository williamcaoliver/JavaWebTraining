package springboot.WebApp.dao;

public class OrderBookEntry {
    private Enums.TradeActions actions;
    private int price;
    private int quantity;

    public OrderBookEntry(Enums.TradeActions actionsIn, int priceIn, int quantityIn) {
        this.actions = actionsIn;
        this.price = priceIn;
        this.quantity = quantityIn;
    }

    public String showData(){
        String result = ("\nShowing OrderBookEntry Data: Price= "+price + "  " + "Quantity= "+quantity+ "  " + "Action= "+actions);
        System.out.print("\nShowing OrderBookEntry Data: Price= "+price + "  " + "Quantity= "+quantity+ "  " + "Action= "+actions);
        System.out.println();
        return result;
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
