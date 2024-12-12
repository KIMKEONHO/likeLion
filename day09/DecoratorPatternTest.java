package day09;

public class DecoratorPatternTest {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " 가격 : " + coffee.getCost() + "천원");

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " 가격 : " + coffee.getCost() + "천원");

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " 가격 : " + coffee.getCost() + "천원") ;
    }
}
