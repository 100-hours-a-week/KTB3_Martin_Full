package Hamburger;

import Ingredient.*;

public class Chicken extends Hamburger {
    public Chicken() {
        name = "치킨버거";
        ingarr.add(new Chicken_Patty((true)));

        StringBuilder sb = new StringBuilder();
        System.out.println("치킨버거 기본재료 : ");
        System.out.println(super.ShowIngredient());

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
