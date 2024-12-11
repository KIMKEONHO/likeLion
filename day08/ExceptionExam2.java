package day08;

public class ExceptionExam2 {
    public static void main(String[] args) {
        int [] iarr = {0,1,2,3};

        try {
            int i = iarr[3] / iarr[0];
            System.out.println("테스트");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
