package day09;

public class BankException extends Exception {
    public BankException() {
        super("통장 잔고보다 많은 금액을 출력합니다.");
    }
    public BankException(String message) {
        super(message);
    }
}
