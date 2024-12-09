package day06;

public class Exam3 {
    public static void test(Parent2 p){
        System.out.println(p.i);
        System.out.println(p.getI());
    }

    public static void main(String[] args) {
        Parent2 p =  new Parent2();
        System.out.println(p.i);
        System.out.println(p.getI());

        Child2 c = new Child2();
        System.out.println(c.i);
        System.out.println(c.getI());

        // c가 Child2 타입이였어도 호출할 때 Parent2 타입으로 받았기에 5/10이 호출된다.
        test(c);
    }
}
