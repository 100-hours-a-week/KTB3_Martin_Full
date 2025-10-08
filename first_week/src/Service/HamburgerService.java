package Service;

public class HamburgerService {
    private final HamburgerService hamburgerService;
    private final IngredientService ingredientService;



    public HamburgerService() {
        this.ingredientService = new IngredientService();
        this.hamburgerService = new HamburgerService();
    }
}
