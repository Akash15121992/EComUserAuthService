package dev.sandeep.EComUserAuthService.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

}
