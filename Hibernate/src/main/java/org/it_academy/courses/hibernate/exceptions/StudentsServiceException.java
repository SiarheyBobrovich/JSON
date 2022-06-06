package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalArgumentException;

public class StudentsServiceException extends CoursesIllegalArgumentException {

    public StudentsServiceException(int status, String message) {
        super(status, message);
    }

    public StudentsServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
