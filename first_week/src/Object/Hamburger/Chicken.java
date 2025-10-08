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
        IngredientList.put(bun.getName(), bun);
        IngredientList.put(LETTUCE.getName(), LETTUCE);
        IngredientList.put(TOMATO.getName(), TOMATO);
        IngredientList.put(ONION.getName(), ONION);
        IngredientList.put(MAYO.getName(), MAYO);
    }


}
