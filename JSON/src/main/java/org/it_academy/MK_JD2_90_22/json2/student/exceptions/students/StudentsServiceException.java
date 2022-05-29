package org.it_academy.MK_JD2_90_22.json2.student.exceptions.students;

import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalArgumentException;

public class StudentsServiceException extends CoursesIllegalArgumentException {

    public StudentsServiceException(int status, String message) {
        super(status, message);
    }

    public StudentsServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
