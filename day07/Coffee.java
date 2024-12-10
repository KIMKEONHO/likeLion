package day07;

public class Coffee extends Baverage{

    final int i = 0;

    @Override
    public void brew() {
        System.out.println("루왁 커피로 우리는 중 ...");
    }

    @Override
    public void addCondiments() {
        System.out.println("설탕을 추가 하는 중 ...");
    }

}
