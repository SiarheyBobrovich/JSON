package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalArgumentException;

public class GroupServiceException extends CoursesIllegalArgumentException {
    public GroupServiceException(int status, String message) {
        super(status, message);
    }

    public GroupServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
