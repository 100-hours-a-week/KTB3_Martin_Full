package Object.Hamburger;

import Object.Ingredient.Ingredients;
import Object.Ingredient.Patty;

import java.util.HashMap;

public abstract class Hamburger {
    //재료들을 모아놓은 리스트
    protected HashMap<String, Ingredients> IngredientList;
    protected Patty patty;
    //버거종류

    protected  String name;





    public HashMap<String, Ingredients> getIngredientList() {
        return IngredientList;
    }

    public Patty getPatty() {
        return patty;
    }
    public void setPatty(Patty patty) {
        this.patty = patty;
    }

    public String getName() {
        return name;
    }





}
