package org.it_academy.MK_JD2_90_22.json.exceptions.dao;

public class GroupSaveDeleteStudentsDaoException extends IllegalStateException{

    public GroupSaveDeleteStudentsDaoException(String s) {
        super(s);
    }

    public GroupSaveDeleteStudentsDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
