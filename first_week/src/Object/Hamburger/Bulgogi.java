package Object.Hamburger;

import Object.Ingredient.BulgogiPatty;
import Object.Ingredient.Ingredients;

import java.util.HashMap;

import static Object.Ingredient.Ingredients.*;

public class Bulgogi extends Hamburger {

    public Bulgogi() {
        super.name = "불고기버거";
        super.patty = new BulgogiPatty();
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
