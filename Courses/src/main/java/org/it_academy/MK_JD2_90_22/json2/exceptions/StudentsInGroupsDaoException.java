package org.it_academy.MK_JD2_90_22.json2.exceptions;

import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalStateException;

public class StudentsInGroupsDaoException extends CoursesIllegalStateException {

    public StudentsInGroupsDaoException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
