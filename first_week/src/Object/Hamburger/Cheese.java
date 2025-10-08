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
        IngredientList.put(bun.getName(), bun);
        IngredientList.put(LETTUCE.getName(), LETTUCE);
        IngredientList.put(TOMATO.getName(), TOMATO);
        IngredientList.put(ONION.getName(), ONION);
        IngredientList.put(MAYO.getName(), MAYO);
        IngredientList.put(CHEESE.getName(), CHEESE);
    }



}
