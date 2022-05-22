package com.lyc.service.ex;

/**
 * 业务层异常的基类
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }


    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }
}
