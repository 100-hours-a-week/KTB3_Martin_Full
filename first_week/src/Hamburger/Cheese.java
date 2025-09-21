package Hamburger;

import Ingredient.*;

public class Cheese extends Hamburger {

    public Cheese() {
        name = "치즈버거";

        for (Ingredient i : ingarr) {
            if (i.name.equals("치즈")) {
                i.num++;
                break;
            }
        }
        ingarr.add(new Raw_Patty());

        StringBuilder sb = new StringBuilder();
        System.out.println("치즈버거 기본 재료 : ");

        System.out.println(super.ShowIngredient());


    }



    @Override
    public boolean add(String name, int num) {
        return super.addIngredient(name, num);

    }

    public boolean sub(String name, int num) {
        return super.subIngredient(name, num);

    }
}
