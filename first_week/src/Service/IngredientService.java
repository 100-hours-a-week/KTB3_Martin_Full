package Service;

import Repository.IngredientRepository;

public class IngredientService {
    private IngredientRepository ingredientRepo;
    public IngredientService(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }



}
