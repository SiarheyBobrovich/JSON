package org.it_academy.courses.hibernate.exceptions;

import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalStateException;

public class GroupDaoException extends CoursesIllegalStateException {

    public GroupDaoException(int status, String message) {
        super(status, message);
    }

    public GroupDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
