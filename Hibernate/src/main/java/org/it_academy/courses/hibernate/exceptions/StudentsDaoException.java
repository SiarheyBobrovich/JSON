package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalStateException;

public class StudentsDaoException extends CoursesIllegalStateException {

    public StudentsDaoException(int status, String message) {
        super(status, message);
    }

    public StudentsDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
