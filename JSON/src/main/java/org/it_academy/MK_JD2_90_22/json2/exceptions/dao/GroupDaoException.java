package org.it_academy.MK_JD2_90_22.json2.exceptions.dao;

public class GroupDaoException extends IllegalStateException{

    public GroupDaoException(String message) {
        super(message);
    }

    public GroupDaoException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
