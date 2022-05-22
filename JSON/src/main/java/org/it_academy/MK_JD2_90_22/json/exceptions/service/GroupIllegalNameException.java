package org.it_academy.MK_JD2_90_22.json.exceptions.service;

public class GroupIllegalNameException extends IllegalArgumentException{

    public GroupIllegalNameException() {
        super("Такая группа уже существует");
    }

    public GroupIllegalNameException(String s) {
        super(s);
    }
}
