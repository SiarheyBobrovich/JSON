package org.it_academy.MK_JD2_90_22.json2.exceptions.api;

public abstract class CoursesIllegalArgumentException extends IllegalArgumentException{

    private final int status;

    public CoursesIllegalArgumentException(int status, String message) {
        super(message);
        this.status = status;
    }

    public CoursesIllegalArgumentException(int status, String s, Throwable cause) {
        super(s, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
