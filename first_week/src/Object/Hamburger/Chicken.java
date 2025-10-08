package Object.Hamburger;

import Object.Ingredient.*;

public class Chicken extends Hamburger {
    public Chicken() {
        name = "치킨버거";
        patty = new ChickenPatty(true);
    }


    public boolean add(String name, int num) {
        if (name.equals("패티")) name = "치킨패티";
        return super.addIngredient(name, num);
    }

   public boolean sub(String name, int num) {
        if (name.equals("패티")) name = "치킨패티";
        return super.subIngredient(name, num);
    }
}
