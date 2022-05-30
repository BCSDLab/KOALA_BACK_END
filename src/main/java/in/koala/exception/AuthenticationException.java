package in.koala.exception;

import in.koala.enums.ErrorMessage;

public class AuthenticationException extends NonCriticalException {
    public AuthenticationException(String className, ErrorMessage errorMessage) {
        super(className, errorMessage);
    }

    public AuthenticationException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
