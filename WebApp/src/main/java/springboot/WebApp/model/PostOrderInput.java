package springboot.WebApp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springboot.WebApp.dao.Enums;

@Getter @Setter @ToString @AllArgsConstructor
public class PostOrderInput {
    String accountID;
    @Min(0) @Max(1000)
    int price;
    @Min(0) @Max(1000)
    int quantity;
    @NotNull
    Enums.TradeActions action;
}
