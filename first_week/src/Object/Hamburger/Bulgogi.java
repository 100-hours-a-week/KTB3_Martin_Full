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
        IngredientList.put(bun.getDisplayName(), bun);
        IngredientList.put(LETTUCE.getDisplayName(), LETTUCE);
        IngredientList.put(TOMATO.getDisplayName(), TOMATO);
        IngredientList.put(ONION.getDisplayName(), ONION);
        IngredientList.put(MAYO.getDisplayName(), MAYO);




    }


//    public boolean add(String name, int num) {
//        if (name.equals("패티")) name = "불고기패티";
//        return super.addIngredient(name, num);
//    }
//
//    public boolean sub(String name, int num) {
//        if (name.equals("패티")) name = "불고기패티";
//        return super.subIngredient(name, num);
//    }
}
