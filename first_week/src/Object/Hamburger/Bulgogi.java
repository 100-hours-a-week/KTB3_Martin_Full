package Object.Hamburger;

import Object.Ingredient.BulgogiPatty;

public class Bulgogi extends Hamburger {

    public Bulgogi() {
        name = "불고기버거";
        IngredientList.add(new BulgogiPatty());

        StringBuilder sb = new StringBuilder();


    }


    public boolean add(String name, int num) {
        if (name.equals("패티")) name = "불고기패티";
        return super.addIngredient(name, num);
    }

    public boolean sub(String name, int num) {
        if (name.equals("패티")) name = "불고기패티";
        return super.subIngredient(name, num);
    }
}
