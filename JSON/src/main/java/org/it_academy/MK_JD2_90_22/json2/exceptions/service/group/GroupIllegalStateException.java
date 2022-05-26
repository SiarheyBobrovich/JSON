package org.it_academy.MK_JD2_90_22.json2.exceptions.service.group;

public class GroupIllegalStateException extends IllegalStateException{

    public GroupIllegalStateException() {
        super("Такая группа уже существует");
    }

    public GroupIllegalStateException(String s) {
        super(s);
    }
}
