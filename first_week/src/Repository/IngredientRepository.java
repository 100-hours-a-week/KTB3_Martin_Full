package Repository;

import Dto.Ingredient.Ingredient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

public class IngredientRepository {
    private HashMap<String, Ingredient> ingredients;

    public IngredientRepository() {
        this.ingredients = new HashMap<>();
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient save(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
        return ingredient;
    }

    public Optional<Ingredient> findByName(String name) {
        return Optional.ofNullable(ingredients.get(name));
    }
}
