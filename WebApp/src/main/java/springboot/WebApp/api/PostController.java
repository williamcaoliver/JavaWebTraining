package springboot.WebApp.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;
import springboot.WebApp.dao.Enums;
import springboot.WebApp.dao.Order;
import springboot.WebApp.model.PostOrderInput;
import springboot.WebApp.model.PostOrderReturn;
import springboot.WebApp.services.Market;
import springboot.WebApp.services.MarketController;

import java.sql.SQLOutput;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    private final MarketController marketController;

    @Autowired
    private final Market market;
    @PostMapping(value = "new-order")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody PostOrderInput newOrder){
        System.out.println(newOrder);
        Order orderResponse = new Order(newOrder.getAccountID(), newOrder.getPrice(), newOrder.getQuantity(), newOrder.getAction());
        System.out.println(orderResponse);
        marketController.addNewOrder(orderResponse);
        PostOrderReturn postOrderReturn = new PostOrderReturn(market.getAggOrderBook(), market.getPrivateOrders(newOrder.getAccountID()),market.getTradesList() );
        return ResponseEntity.ok(postOrderReturn);
    }

}
