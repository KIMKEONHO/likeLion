package day07;

public class Juice extends Baverage{
    @Override
    public void brew() {
        System.out.println("과일 우리는중 ... ");
    }

    @Override
    public void addCondiments() {
        System.out.println("얼음을 추가하는 중");
    }
}
