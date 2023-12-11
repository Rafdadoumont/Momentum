package model.exceptions;

public class IllegalMoveException extends Exception{
    String message;

    public IllegalMoveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
