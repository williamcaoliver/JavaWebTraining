package springboot.WebApp.dao;
import java.util.Date;
public class Trade {
    private Order buyOrder;
    private Order sellOrder;
    private int agreedPrice;
    private int agreedQuantity;
    private long dateTime;

    public Trade(Order buyersOrder, Order sellerOrder, int price, int quantity) {
        this.buyOrder = buyersOrder;
        this.sellOrder = sellerOrder;
        this.agreedPrice = price;
        this.agreedQuantity = quantity;
        this.dateTime = new Date().getTime();
    }
    public void showData(){
        String displayBuyOrder = buyOrder.showData();
        String displaySellOrder = sellOrder.showData();
        System.out.print("\nShowing Trade Data: BuyOrder= "+ displayBuyOrder+ "\n" + "SellOrder= "+displaySellOrder + "\n" + "AgreedPrice= "+agreedPrice+ "  " + "AgreedQuantity= "+agreedQuantity+ "  " + "DateTime= "+dateTime);
        System.out.println();
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
