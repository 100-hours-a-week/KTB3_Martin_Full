package Service;

import Object.Hamburger.Bulgogi;
import Object.Hamburger.Cheese;
import Object.Hamburger.Chicken;
import Object.Hamburger.Hamburger;
import Service.*;

import java.io.IOException;

public class HamburgerFactory {
    private final IngredientService ingredientService;

    public HamburgerFactory(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

}
