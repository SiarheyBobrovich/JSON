package org.it_academy.MK_JD2_90_22.json2.student.exceptions.students;

import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalStateException;

public class StudentsDaoExceptionIllegal extends CoursesIllegalStateException {
    public StudentsDaoExceptionIllegal(int status, String message) {
        super(status, message);
    }

    public StudentsDaoExceptionIllegal(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
