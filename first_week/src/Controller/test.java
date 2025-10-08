package Controller;

import java.util.ArrayList;

enum Ingredient {
    BUN("빵",2),
    PATTY("패티", 5),
    CHEESE("치즈", 6),
    LETTUCE("양상추",6),
    TOMATO("토마토",6),
    ONION("양파",6),
    SAUCE("소스",6),
    BACON("베이컨",6);

    private final String displayName;
    private final int kcal;
    private int num = 0;

    Ingredient(String displayName, int kcal) {
        this.displayName = displayName;
        this.kcal = kcal;
    }

    public String getDisplayName() {
        return displayName;
    }
    public int getKcal() {
        return kcal;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}

public class test {

    public static void main(String[] args) {

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(Ingredient.BUN);
        ingredients.add(Ingredient.PATTY);
        ingredients.add(Ingredient.CHEESE);
        ingredients.add(Ingredient.LETTUCE);
        ingredients.add(Ingredient.TOMATO);



    }
}
