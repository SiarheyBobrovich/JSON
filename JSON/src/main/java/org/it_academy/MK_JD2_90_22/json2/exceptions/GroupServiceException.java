package org.it_academy.MK_JD2_90_22.json2.exceptions;

public class GroupServiceException extends IllegalArgumentException {

    private int status;

    public GroupServiceException(int status, String s) {
        super(s);
        this.status = status;
    }

    public GroupServiceException(int status, String s, Throwable cause) {
        super(s, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
