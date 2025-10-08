package Object.Ingredient;

public class ChickenPatty extends Patty {
    private final boolean isfried;

    public ChickenPatty(boolean fried) {
        super("치킨패티", 300, "Chicken");
        this.isfried = fried;
    }
}
