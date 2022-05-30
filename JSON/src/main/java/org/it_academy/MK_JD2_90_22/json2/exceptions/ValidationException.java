package org.it_academy.MK_JD2_90_22.json2.exceptions;

import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalStateException;

public class ValidationException extends CoursesIllegalStateException {
    public ValidationException(int status, String message) {
        super(status, message);
    }

    public ValidationException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
