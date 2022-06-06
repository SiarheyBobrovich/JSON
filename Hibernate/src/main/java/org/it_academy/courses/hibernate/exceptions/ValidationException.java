package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalArgumentException;

public class ValidationException extends CoursesIllegalArgumentException {
    public ValidationException(int status, String message) {
        super(status, message);
    }

    public ValidationException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
