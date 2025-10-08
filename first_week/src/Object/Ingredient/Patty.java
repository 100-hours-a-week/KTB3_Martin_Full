package Object.Ingredient;

public abstract class Patty{
    protected final String name;
    protected final int kcal;
    protected final String material;
    protected int num = 1;

    public Patty(String name, int kcal, String material) {
        this.name = name;
        this.kcal = kcal;
        this.material = material;
    }

    public String getName() {
        return name;
    }
    public int getKcal() {
        return kcal;
    }

    public String getMaterial() {
        return material;
    }




}
