package com.lyc.service.ex;

import com.lyc.controller.BaseController;

public class LowStocksException extends ServiceException {
    public LowStocksException() {
        super();
    }

    public LowStocksException(String message) {
        super(message);
    }

    public LowStocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public LowStocksException(Throwable cause) {
        super(cause);
    }

    protected LowStocksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
