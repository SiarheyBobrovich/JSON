package org.it_academy.MK_JD2_90_22.json2.student.exceptions.studentsInGroups;

import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalStateException;

public class StudentsInGroupsDaoException extends CoursesIllegalStateException {

    public StudentsInGroupsDaoException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsDaoException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
