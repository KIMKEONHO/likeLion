package day06;

public class DowncastingExample {
    public static void main(String[] args) {
        Parent parent = new Child(); // 업캐스팅
        Child child = null; // 블록 외부에서 선언

        if (parent instanceof Child) {
            child = (Child) parent; // 다운캐스팅 (명시적 변환)
        }

        // child가 null이 아닌 경우에만 호출
        if (child != null) {
            child.greet(); // "Hello from Child"
            child.specificMethod(); // "Child-specific method"
        }
    }
}
