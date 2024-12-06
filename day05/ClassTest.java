package day05;

public class ClassTest {
    public static void main(String[] args) {
        ClassA a = new ClassA();
        System.out.println(a.foeldB);
        System.out.println(a.fieldC);
        // 프로 텍티드는 못건듬
        // System.out.println(a.fieldD);
        System.out.println(a.fieldE);
    }
}
