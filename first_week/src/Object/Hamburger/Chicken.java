package Object.Hamburger;

import Object.Ingredient.*;

import java.util.HashMap;

import static Object.Ingredient.Ingredients.*;
import static Object.Ingredient.Ingredients.MAYO;

public class Chicken extends Hamburger {
    public Chicken() {
        name = "치킨버거";
        patty = new ChickenPatty(true);
        super.name = "치킨버거";
        super.patty = new ChickenPatty(true);
        IngredientList = new HashMap<>();


        Ingredients bun = BUN;
        bun.setNum(2);
        IngredientList.put(bun.getDisplayName(), bun);
        IngredientList.put(LETTUCE.getDisplayName(), LETTUCE);
        IngredientList.put(TOMATO.getDisplayName(), TOMATO);
        IngredientList.put(ONION.getDisplayName(), ONION);
        IngredientList.put(MAYO.getDisplayName(), MAYO);
    }


//    public boolean add(String name, int num) {
//        if (name.equals("패티")) name = "치킨패티";
//        return super.addIngredient(name, num);
//    }
//
//   public boolean sub(String name, int num) {
//        if (name.equals("패티")) name = "치킨패티";
//        return super.subIngredient(name, num);
//    }
}
