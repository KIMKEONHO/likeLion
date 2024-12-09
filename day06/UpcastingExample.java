package day06;

class Parent {
    void greet() {
        System.out.println("Hello from Parent");
    }
}

class Child extends Parent {
    void greet() {
        System.out.println("Hello from Child");
    }

    void specificMethod() {
        System.out.println("Child-specific method");
    }
}

public class UpcastingExample {
    public static void main(String[] args) {
        Parent p = new Child();
        p.greet(); // "Hello from Child" (다형성)

        // parent.specificMethod(); // 컴파일 오류 (Parent 타입에 없는 메서드)
    }
}
// 물려준 함수인 greet함수는 실행 되지만 자식만 들고 있는 메서드인
// specificMethod는 접근이 되지않는다.

