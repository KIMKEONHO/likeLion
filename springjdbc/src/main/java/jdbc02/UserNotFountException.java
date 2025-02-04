package jdbc02;

public class UserNotFountException extends Exception {
    public UserNotFountException(String message) {
        super(message);
    }
}
