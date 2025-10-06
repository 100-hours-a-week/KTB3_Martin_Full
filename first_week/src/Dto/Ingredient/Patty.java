package Dto.Ingredient;

class Patty extends Ingredient {
    String material;

    public Patty(String name, int num, int kcal, String material) {
        super(name, num, kcal);
        this.material = material;
    }


}
