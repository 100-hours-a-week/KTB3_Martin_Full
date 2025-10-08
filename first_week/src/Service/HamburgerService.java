package Service;

import Object.Hamburger.*;
import Object.Ingredient.Ingredients;
import Object.Ingredient.Patty;
import Repository.HamburgerRepository;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


//햄버거 서비스 레이어
public class HamburgerService {
    private final HamburgerRepository hamburgerRepository;
    private final IngredientService ingredientService;



    public HamburgerService() {
        this.hamburgerRepository = new HamburgerRepository();
        this.ingredientService = new IngredientService();
    }

    public Hamburger getHamburger(String request)throws IllegalArgumentException {
        Hamburger hamburger = null;

        SetofHamburger burger = SetofHamburger.valueOf(request);
        switch (burger) {
            case 불고기버거 -> hamburger = new Bulgogi();
            case 치킨버거 -> hamburger = new Chicken();
            case 치즈버거 -> hamburger = new Cheese();
        }

        return hamburger;
    }

    public String getHamburgerType(){
        StringBuilder sb = new StringBuilder();
        List<SetofHamburger> menu= Arrays.asList(SetofHamburger.values());
        for(SetofHamburger burger:menu){
            sb.append(burger).append(" ");
        }

        return sb.toString();
    }

    public String showIngredientForAdd(){
        return ingredientService.ShowIngredientForAdd();
    }

    public boolean isAddable(String name){
        return ingredientService.isAddable(name);
    }

    public Hamburger addIngredient(Hamburger hamburger, String name, int num){
        return ingredientService.addIngredient(hamburger, name, num);

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
