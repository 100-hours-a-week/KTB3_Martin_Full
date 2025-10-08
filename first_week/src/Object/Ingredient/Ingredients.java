package Object.Ingredient;

public enum Ingredients {
    BUN("빵",90),
    CHEESE("치즈", 50),
    LETTUCE("양상추",4),
    TOMATO("토마토",6),
    ONION("양파",7),
    MAYO("마요네즈",100),
    BACON("베이컨",90),
    KETCHUP("케첩", 70);

    private final String name;
    private final int kcal;
    private int num = 1;

    Ingredients(String displayName, int kcal) {
        this.name = displayName;
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }
    public int getKcal() {
        return kcal;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}