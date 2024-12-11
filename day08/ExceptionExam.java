package day08;

public class ExceptionExam {
    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("여기");
        }
    }
}
