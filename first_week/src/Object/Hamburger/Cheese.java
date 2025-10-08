package Object.Hamburger;

import Object.Ingredient.*;

import java.util.HashMap;

import static Object.Ingredient.Ingredients.*;
import static Object.Ingredient.Ingredients.MAYO;

public class Cheese extends Hamburger {

    public Cheese() {
        super.name = "치즈버거";
        super.patty = new RawPatty();
        IngredientList = new HashMap<>();


        Ingredients bun = BUN;
        bun.setNum(2);
        IngredientList.put(bun.getDisplayName(), bun);
        IngredientList.put(LETTUCE.getDisplayName(), LETTUCE);
        IngredientList.put(TOMATO.getDisplayName(), TOMATO);
        IngredientList.put(ONION.getDisplayName(), ONION);
        IngredientList.put(MAYO.getDisplayName(), MAYO);
        IngredientList.put(CHEESE.getDisplayName(), CHEESE);
    }



}
