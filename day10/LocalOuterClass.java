package day10;

public class LocalOuterClass {
    private int outerField = 10;

    public void outerMethod() {
        class InnerClass {

            public void outerMethod() {
                System.out.println("outerField : " + outerField);
            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.outerMethod();
    }

    public static void main(String[] args) {
    }



}



