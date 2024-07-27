package onlineFileStorage.root.app.FileStorage.handlers.exception;

public class ExistingUsernameException extends RuntimeException{
    public ExistingUsernameException(String message) {
        super(message);
    }
}
