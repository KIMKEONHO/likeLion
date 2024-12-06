package day05;

public class PersonTest {

    public static void main(String[] args) {
        Person p = new Person();
        Person p1 = new Person();
        Person p2 = new Person();

        p.name = "강";
        p1.name = "김";
        p2.name = "홍";

        System.out.println(p.name);
        System.out.println(p1.name);
        System.out.println(p2.name);

        p.count = 10;
        p1.count = 20;
        p2.count = 30;
        
        System.out.println(p.count);
        System.out.println(p1.count);
        System.out.println(p2.count);

    }
}
