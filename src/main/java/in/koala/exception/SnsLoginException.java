package in.koala.exception;

import in.koala.enums.ErrorMessage;

public class SnsLoginException extends NonCriticalException{
    public SnsLoginException(String className, ErrorMessage errorMessage) {
        super(className, errorMessage);
    }

    public SnsLoginException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
