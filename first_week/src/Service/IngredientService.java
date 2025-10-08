package Service;

import Object.Hamburger.Hamburger;
import Object.Ingredient.Ingredients;
import java.util.*;

public class IngredientService {

    //추가 가능한 재료들 목록
    private final HashMap<String, Ingredients> AddableIngredient;

    public IngredientService() {
        AddableIngredient = new HashMap<>();
        for (Ingredients i : Ingredients.values()) {
            AddableIngredient.put(i.getDisplayName(), i);
        }
    }


    public HashMap<String, Ingredients> getAddableIngredient() {
        return AddableIngredient;
    }

    public boolean isAddable(String name){
        return AddableIngredient.containsKey(name);
    }

    public Hamburger addIngredient(Hamburger hamburger, String name, int num) {
        HashMap<String, Ingredients> tmp = hamburger.getIngredientList();

        if(tmp.containsKey(name)){
            int presentnum = tmp.get(name).getNum();
            tmp.get(name).setNum(num+ presentnum);
        }
        else{
            Ingredients newingredient = AddableIngredient.get(name);
            newingredient.setNum(num);
            tmp.put(name, newingredient);
        }
        return hamburger;
    }

    public boolean isSubtractable(Hamburger hamburger, String name, int num){
        if(hamburger.getIngredientList().containsKey(name)){
            Ingredients tmp = hamburger.getIngredientList().get(name);
            return tmp.getNum() >= num;
        }
        else return false;
    }

    public Hamburger subIngredient(Hamburger hamburger, String name, int num){
        HashMap<String, Ingredients> tmp = hamburger.getIngredientList();

        Ingredients target = tmp.get(name);
        int targetnum = target.getNum() - num;
        if(targetnum ==0) tmp.remove(name);
        else tmp.get(name).setNum(targetnum);

        return hamburger;
    }




}
