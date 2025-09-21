package Ingredient;

public class Bulgogi_Patty extends Patty {

    int sauce;

    public Bulgogi_Patty() {
        super("불고기패티", 1, 280, "Beef");
        this.sauce = 30;
    }

    public int showrawpattykcal() {
        return kcal - sauce;
    }
}
