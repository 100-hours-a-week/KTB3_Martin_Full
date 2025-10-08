package Object.Hamburger;

import Object.Ingredient.Ingredient;
import Object.Ingredient.Ingredients;
import Object.Ingredient.Patty;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Hamburger {
    //재료들을 모아놓은 리스트
    protected HashMap<String, Ingredients> IngredientList;
    protected Patty patty;
    //버거종류
    protected String name;

    //버거 객체별 재료변경을 위한 추상 메서드
    public abstract boolean add(String name, int num);

    public abstract boolean sub(String name, int num);


    //정상 입력시 true 입력실패시 false
    //재료추가
    protected boolean addIngredient(String name, int num) {
        if (name.equals("베이컨")) name = "베이컨(2장)";
        else if (name.equals("소스")) name = "소스(마요네즈)";
        boolean result = false;

        for (Ingredient i : IngredientList) {
            if (i.getName().equals(name)) {
                i.setNum(i.getNum()+1);
                result = true;
                StringBuilder sb = new StringBuilder();
                sb.append("갯수를 추가한 재료: ").append(i.getName()).append("\n");
                sb.append("총 갯수: ").append(i.getNum()).append("개");
                sb.append("(").append(i.getKcal()* i.getNum()).append("kcal)\n");
                System.out.println(sb.toString());


                break;
            }
        }

        return result;
    }


    //입력성공시 true, 실패시 false
    //재료빼기
    boolean subIngredient(String name, int num) {
        if (name.equals("베이컨")) name = "베이컨(2장)";
        else if (name.equals("소스")) name = "소스(마요네즈)";
        boolean result = false;

        for (Ingredient i : IngredientList) {
            if (i.getName().equals(name)) {
                if (i.getNum() < num) {
                    System.out.println("뺄려는 갯수가 이미 있는 갯수보다 큽니다.");
                } else {
                    i.setNum(i.getKcal()-1);
                    StringBuilder sb = new StringBuilder();

                    sb.append("갯수를 뺀 재료: ").append(i.getName()).append("\n");
                    sb.append("총 갯수: ").append(i.getNum()).append("개");
                    sb.append("(").append(i.getKcal() * i.getNum()).append("kcal)\n");
                    result = true;
                    System.out.println(sb.toString());

                }

                break;
            }
        }

        return result;
    }



    //현재 햄버거의 총 칼로리 계산
    public int total() {
        int totalkcal = 0;
//        StringBuilder sb = new StringBuilder();
//        sb.append("종류 : ").append(this.name).append("\n");
        for (Ingredient i : IngredientList) {

            if (i.getNum() == 0) continue;
//            sb.append(i.name).append(" ").append(i.num).append("개 ");
//            sb.append("(").append(i.kcal * i.num).append("kcal)\n");
            totalkcal += i.getKcal() * i.getNum();

        }

        return totalkcal;
    }

    //현재 재료들 보여주기
    public String ShowIngredient() {
        StringBuilder sb = new StringBuilder();
        for (Ingredient i : IngredientList) {
            if (i.getNum() == 0) continue;
            sb.append(i.getName()).append(" ").append(i.getNum()).append("개 ");
            sb.append("(").append(i.getKcal() * i.getNum()).append("kcal)\n");

        }
        return sb.toString();

    }

    protected Hamburger() {
        IngredientList = new ArrayList<>();
        IngredientList.add(new Ingredient("빵", 2, 90));
        IngredientList.add(new Ingredient("양상추", 1, 4));
        IngredientList.add(new Ingredient("토마토", 1, 6));
        IngredientList.add(new Ingredient("양파", 1, 7));
        IngredientList.add(new Ingredient("소스(마요네즈)", 1, 100));
        IngredientList.add(new Ingredient("베이컨(2장)", 0, 90));
        IngredientList.add(new Ingredient("치즈", 0, 50));

    }


}
