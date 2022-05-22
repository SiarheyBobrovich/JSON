package org.it_academy.MK_JD2_90_22.json.exceptions.service;

public class GroupSaveDeleteStudentsIllegalIDException extends IllegalArgumentException{

    public GroupSaveDeleteStudentsIllegalIDException(String s) {
        super(s);
    }

    public GroupSaveDeleteStudentsIllegalIDException() {
        super("ID меньше 1");
    }
}
