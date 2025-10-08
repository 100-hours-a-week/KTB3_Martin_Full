package Object.Ingredient;

public class BulgogiPatty extends Patty {

    int sauce;

    public BulgogiPatty() {
        super("불고기패티", 280, "Beef");
        this.sauce = 30;
    }

    public int showrawpattykcal() {
        return kcal - sauce;
    }
}
