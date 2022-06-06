package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalArgumentException;

public class StudentsInGroupsServiceException extends CoursesIllegalArgumentException {

    public StudentsInGroupsServiceException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
