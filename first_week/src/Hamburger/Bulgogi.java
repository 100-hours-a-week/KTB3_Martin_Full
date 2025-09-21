package Hamburger;

import Ingredient.Bulgogi_Patty;

public class Bulgogi extends Hamburger {

    public Bulgogi() {
        name = "불고기버거";
        ingarr.add(new Bulgogi_Patty());

        StringBuilder sb = new StringBuilder();
        System.out.println("불고기버거 기본 재료 :");
        System.out.println(super.ShowIngredient());


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
