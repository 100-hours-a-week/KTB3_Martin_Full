package Ingredient;

public class  Ingredient{
    //재료 이름
    public String name;
    //재료 개수
    public int num;
    //재료 1개당 칼로리(베이컨은 2줄 기준)
    public int kcal;


    public Ingredient(String name, int num, int kcal){
        this.name = name;
        this.num = num;
        this.kcal = kcal;
    }


}

