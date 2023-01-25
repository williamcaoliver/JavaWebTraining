package springboot.WebApp.dao;

public class Enums {

    public enum TradeActions {
        BUY,
        SELL;

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
