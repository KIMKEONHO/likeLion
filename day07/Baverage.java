package day07;

public abstract class Baverage {

    public final void prepareRecipe() {
        brew();
        pourIncup();
        boilWater();
        addCondiments();
    }

    private void pourIncup() {
        System.out.println("물 끓이는중 ...");
    }

    private void boilWater() {
        System.out.println("컵에 붓는 중 ... ");
    }

    public abstract void brew();
    public abstract void addCondiments();
}
