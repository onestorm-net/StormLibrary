package net.onestorm.library.menu.exception;

public class BuildMenuException extends RuntimeException {

    public BuildMenuException() {
    }

    public BuildMenuException(String message) {
        super(message);
    }

    public BuildMenuException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuildMenuException(Throwable cause) {
        super(cause);
    }
}
