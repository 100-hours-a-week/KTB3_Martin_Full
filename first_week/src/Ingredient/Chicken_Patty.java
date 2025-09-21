package Ingredient;

public class Chicken_Patty extends Patty {
    public boolean isfried;

    public Chicken_Patty(boolean fried) {
        super("치킨패티", 1, 300, "Chicken");
        this.isfried = fried;
    }
}
