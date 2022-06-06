package org.it_academy.MK_JD2_90_22.json2.exceptions;

import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalArgumentException;

public class StudentsInGroupsServiceException extends CoursesIllegalArgumentException {

    public StudentsInGroupsServiceException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
