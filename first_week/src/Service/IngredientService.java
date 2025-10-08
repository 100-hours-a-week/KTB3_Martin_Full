package Service;

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

    public String Showaddable(){
        StringBuilder sb = new StringBuilder();
        for (String s : AddableIngredient.keySet()) {
            sb.append(s).append("\n");
        }

        return sb.toString();
    }


}
