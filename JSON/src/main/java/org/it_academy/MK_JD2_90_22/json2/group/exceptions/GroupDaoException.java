package org.it_academy.MK_JD2_90_22.json2.group.exceptions;

public class GroupDaoException extends IllegalStateException {

    public GroupDaoException(String s) {
        super(s);
    }

    public GroupDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
