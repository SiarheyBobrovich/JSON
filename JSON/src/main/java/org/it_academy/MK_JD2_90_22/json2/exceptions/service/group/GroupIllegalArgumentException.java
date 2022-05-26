package org.it_academy.MK_JD2_90_22.json2.exceptions.service.group;

public class GroupIllegalArgumentException extends IllegalArgumentException{

    public GroupIllegalArgumentException() {
        super("ID < 1");
    }

    public GroupIllegalArgumentException(String s) {
        super(s);
    }
}
