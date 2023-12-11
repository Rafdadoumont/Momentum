package model.exceptions;

public class InvalidPlayersException extends Exception{
    String message;

    public InvalidPlayersException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
