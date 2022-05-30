package in.koala.exception;

import in.koala.enums.ErrorMessage;

public class EmailException extends NonCriticalException{
    public EmailException(String className, ErrorMessage errorMessage) {
        super(className, errorMessage);
    }

    public EmailException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
