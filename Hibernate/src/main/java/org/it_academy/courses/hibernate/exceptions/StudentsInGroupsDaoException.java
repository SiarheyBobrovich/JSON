package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalStateException;

public class StudentsInGroupsDaoException extends CoursesIllegalStateException {

    public StudentsInGroupsDaoException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
