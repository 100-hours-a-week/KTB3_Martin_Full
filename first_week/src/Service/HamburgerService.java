package Service;

import Object.Hamburger.Cheese;
import Object.Hamburger.Hamburger;
import Repository.HamburgerRepository;

public class HamburgerService {
    private final HamburgerRepository hamburgerRepository;
    private final HamburgerFactory hamburgerFactory;
    private final IngredientService ingredientService;



    public HamburgerService() {
        this.hamburgerRepository = new HamburgerRepository();
        this.ingredientService = new IngredientService();
        this.hamburgerFactory = new HamburgerFactory(ingredientService);
    }

    public Hamburger create(){
        return new Cheese();
    }
}
