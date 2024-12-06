package day05;

public class ClassA {
    public static int fieldA; // 클래스 필드
    public int foeldB; // 인스턴스 필드

    int fieldC; // default 접근 제한자

    private int fieldD;

    // 기본 패키지까지 허용되고, 다른 패키기라도 나의 자손은 접근 가능
    protected int fieldE;

    public static void main(String[] args) {
        System.out.println(ClassA.fieldA);

        // 불가능
        //System.out.println(ClassA.fieldB);
    }
}
