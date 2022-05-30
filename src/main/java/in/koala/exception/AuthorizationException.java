package in.koala.exception;

import in.koala.enums.ErrorMessage;

public class AuthorizationException extends NonCriticalException{
    public AuthorizationException(String className, ErrorMessage errorMessage) {
        super(className, errorMessage);
    }

    public AuthorizationException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
