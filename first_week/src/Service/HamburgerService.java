package Service;

import Object.Hamburger.Cheese;
import Object.Hamburger.Hamburger;
import Object.Ingredient.Ingredients;
import Object.Ingredient.Patty;
import Repository.HamburgerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HamburgerService {
    private final HamburgerRepository hamburgerRepository;
    private final HamburgerFactory hamburgerFactory;
    private final IngredientService ingredientService;



    public HamburgerService() {
        this.hamburgerRepository = new HamburgerRepository();
        this.ingredientService = new IngredientService();
        this.hamburgerFactory = new HamburgerFactory();
    }

    public Hamburger getHamburger(String request)throws IllegalArgumentException {
        return hamburgerFactory.create(request);
    }

    public Hamburger saveHamburger(Hamburger hamburger) {
        return hamburgerRepository.save(hamburger);
    }

    public int getkcal(Hamburger hamburger) {
        HashMap <String, Ingredients> IngredientList = hamburger.getIngredientList();
        Patty patty = hamburger.getPatty();

        int kcal = patty.getKcal();

        for(Ingredients i : IngredientList.values() ){
            kcal += i.getKcal();
        }

        return kcal;
    }

    public int gettotalKcal() {
        AtomicInteger kcal = new AtomicInteger();

        ExecutorService es = Executors.newFixedThreadPool(4);

        ArrayList<Hamburger> burgers = new ArrayList<>(hamburgerRepository.getHamburgers().values());

        for(Hamburger h : burgers){
            es.execute(() -> {
                int eachkcal = getkcal(h);
                kcal.addAndGet(eachkcal);
            });
        }

        es.shutdown();
        try{
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        return kcal.get();



    }
}
