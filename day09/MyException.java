package day09;

public class MyException extends Exception {
    public MyException() {
        super("MyException 예외 ");
    }

    public MyException(String message) {
        super(message);
    }
}
