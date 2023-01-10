package springboot.WebApp.dao;

public class Enums {

    public enum TradeActions {
        BUY("buy"),
        SELL("sell");

        private final String e_action;

        TradeActions(String action){
            this.e_action = action;
        }
        public String getAction() {
            return e_action;
        }
    }

    public enum Direction {
        Desc("desc"),
        Asc("asc");

        private final String e_direction;

        Direction(String direction){
            this.e_direction = direction;
        }
        public String getDirection() {
            return e_direction;
        }
    }
}
