package com.lyc.service.ex;

public class UserDeleteException extends ServiceException{
    public UserDeleteException() {
        super();
    }

    public UserDeleteException(String message) {
        super(message);
    }

    public UserDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDeleteException(Throwable cause) {
        super(cause);
    }

    protected UserDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
