package springboot.WebApp.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.WebApp.dao.Order;
import springboot.WebApp.model.PostOrderInput;
import springboot.WebApp.model.PostOrderReturn;
import springboot.WebApp.services.MarketService;
import springboot.WebApp.services.AddOrderService;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    private final AddOrderService addOrderService;

    @Autowired
    private final MarketService marketService;
    @PostMapping(value = "new-order")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody PostOrderInput newOrder){
        System.out.println(newOrder);
        Order orderResponse = new Order(newOrder.getAccountID(), newOrder.getPrice(), newOrder.getQuantity(), newOrder.getAction());
        System.out.println(orderResponse);
        addOrderService.addNewOrder(orderResponse);
        PostOrderReturn postOrderReturn = new PostOrderReturn(marketService.getAggOrderBook(), marketService.getPrivateOrders(newOrder.getAccountID()), marketService.getTradesList() );
        return ResponseEntity.ok(postOrderReturn);
    }

}
