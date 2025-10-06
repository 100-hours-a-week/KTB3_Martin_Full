package Dto.Ingredient;

public class  Ingredient{
    //재료 이름
    protected String name;
    //재료 개수
    protected int num;
    //재료 1개당 칼로리(베이컨은 2줄 기준)
    protected int kcal;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num = num;
    }

    public int getKcal(){
        return kcal;
    }

    public void setKcal(int kcal){
        this.kcal = kcal;
    }


    public Ingredient(String name, int num, int kcal){
        this.name = name;
        this.num = num;
        this.kcal = kcal;
    }


}

