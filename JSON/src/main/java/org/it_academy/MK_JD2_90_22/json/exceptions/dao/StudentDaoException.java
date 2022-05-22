package org.it_academy.MK_JD2_90_22.json.exceptions.dao;

public class StudentDaoException extends IllegalStateException{

    public StudentDaoException(String message) {
        super(message);
    }

    public StudentDaoException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
