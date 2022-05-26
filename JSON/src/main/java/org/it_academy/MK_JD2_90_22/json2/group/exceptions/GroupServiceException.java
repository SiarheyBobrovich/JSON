package org.it_academy.MK_JD2_90_22.json2.group.exceptions;

public class GroupServiceException extends IllegalArgumentException {

    public GroupServiceException(String s) {
        super(s);
    }

    public GroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
