package com.lyc.service.ex;

public class OrderItemNumTooLargeException extends ServiceException{
    public OrderItemNumTooLargeException() {
        super();
    }

    public OrderItemNumTooLargeException(String message) {
        super(message);
    }

    public OrderItemNumTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderItemNumTooLargeException(Throwable cause) {
        super(cause);
    }

    public OrderItemNumTooLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
