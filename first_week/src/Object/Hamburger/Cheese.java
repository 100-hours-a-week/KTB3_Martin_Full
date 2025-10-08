package Object.Hamburger;

import Object.Ingredient.*;

public class Cheese extends Hamburger {

    public Cheese() {
        name = "치즈버거";

        for (Ingredient i : IngredientList) {
            if (i.getName().equals("치즈")) {
                i.setNum(i.getNum() + 1);
                break;
            }
        }
        IngredientList.add(new RawPatty());

        StringBuilder sb = new StringBuilder();

    }


    @Override
    public boolean add(String name, int num) {
        return super.addIngredient(name, num);

    }

    public boolean sub(String name, int num) {
        return super.subIngredient(name, num);

    }
}
