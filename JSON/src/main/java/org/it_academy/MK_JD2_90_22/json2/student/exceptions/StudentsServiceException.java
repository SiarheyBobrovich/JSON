package org.it_academy.MK_JD2_90_22.json2.student.exceptions;

public class StudentsServiceException extends IllegalArgumentException {

    private int status;

    public StudentsServiceException(int status, String s) {
        super(s);
        this.status = status;
    }

    public StudentsServiceException(int status, String s, Throwable cause) {
        super(s, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
