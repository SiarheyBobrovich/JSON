package org.it_academy.MK_JD2_90_22.json2.group.exceptions;

import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalStateException;

public class GroupDaoException extends CoursesIllegalStateException {

    public GroupDaoException(int status, String message) {
        super(status, message);
    }

    public GroupDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
