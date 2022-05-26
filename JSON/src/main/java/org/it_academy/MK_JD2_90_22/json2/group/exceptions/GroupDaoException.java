package org.it_academy.MK_JD2_90_22.json2.group.exceptions;

public class GroupDaoException extends IllegalStateException {

    private final int status;

    public GroupDaoException(int status, String message) {
        super(message);
        this.status = status;
    }

    public GroupDaoException(int status, String s, Throwable cause) {
        super(s, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
