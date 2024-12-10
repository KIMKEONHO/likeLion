package day07;

public class ShapeTest {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(12);
        Shape triangle = new Triangle(12,2);
        Shape circle = new Circle(12);

        System.out.println("사각형의 넓이 : " + rectangle.getarea());
        System.out.println("삼각형의 넓이 : " + triangle.getarea());
        System.out.println("원의 넓이 : " + circle.getarea());


    }
}
