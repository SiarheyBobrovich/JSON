package org.it_academy.MK_JD2_90_22.json2.student.exceptions.studentsInGroups;

import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalArgumentException;

public class StudentsInGroupsServiceException extends CoursesIllegalArgumentException {

    public StudentsInGroupsServiceException(int status, String message) {
        super(status, message);
    }

    public StudentsInGroupsServiceException(int status, String s, Throwable cause) {
        super(status, s, cause);
    }
}
