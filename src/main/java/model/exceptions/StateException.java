package model.exceptions;

public class StateException extends Exception{
    String message;

    public StateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
