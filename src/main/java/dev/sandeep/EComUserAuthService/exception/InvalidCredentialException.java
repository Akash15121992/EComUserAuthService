package dev.sandeep.EComUserAuthService.exception;

public class InvalidCredentialException extends RuntimeException{

    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String message) {
        super(message);
    }
}
