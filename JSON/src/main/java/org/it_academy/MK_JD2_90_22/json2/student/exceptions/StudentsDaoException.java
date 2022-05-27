package org.it_academy.MK_JD2_90_22.json2.student.exceptions;

public class StudentsDaoException extends IllegalStateException {

    private final int status;

    public StudentsDaoException(int status, String message) {
        super(message);
        this.status = status;
    }

    public StudentsDaoException(int status, String s, Throwable cause) {
        super(s, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
