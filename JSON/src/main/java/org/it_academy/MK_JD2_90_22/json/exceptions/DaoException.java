package org.it_academy.MK_JD2_90_22.json.exceptions;

public class DaoException extends IllegalStateException{

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
