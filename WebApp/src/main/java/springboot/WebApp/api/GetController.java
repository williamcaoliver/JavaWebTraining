package springboot.WebApp.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.dao.OrderBookEntry;
import springboot.WebApp.dao.Trade;
import springboot.WebApp.services.Market;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GetController {

    @Autowired private final Market market;

    @GetMapping(value = "order-book/global-book")
    public ResponseEntity<ArrayList<OrderBookEntry>> getOrderBook(){
        return ResponseEntity.ok(market.getAggOrderBook());
    }

    @GetMapping(value = "order-book/buy-book")
    public ResponseEntity<ArrayList<Order>> getBuyBook(){
        return ResponseEntity.ok(market.getBuyOrders());
    }

    @GetMapping(value = "order-book/sell-book")
    public ResponseEntity<ArrayList<Order>> getSellBook(){
        return ResponseEntity.ok(market.getSellOrders());
    }

    @GetMapping(value = "order-book/trade-list")
    public ResponseEntity<ArrayList<Trade>> getTradeBook(){
        return ResponseEntity.ok(market.getTradesList());
    }

    @GetMapping(value = "order-book/private-book/buy/{accountID}")
    public ResponseEntity<List<Order>> getPrivateBookBuy(@PathVariable String accountID){
        return ResponseEntity.ok(market.getPrivateOrders(Enums.TradeActions.BUY, accountID));
    }

    @GetMapping(value = "order-book/private-book/sell/{accountID}")
    public ResponseEntity<List<Order>> getPrivateBookSell(@PathVariable String accountID){
        return ResponseEntity.ok(market.getPrivateOrders(Enums.TradeActions.SELL, accountID));
    }

    @GetMapping(value = "order-book/private-book/{accountID}")
    public ResponseEntity<List<Order>> getPrivateBook(@PathVariable String accountID){
        return ResponseEntity.ok(market.getPrivateOrders(accountID));
    }

}
