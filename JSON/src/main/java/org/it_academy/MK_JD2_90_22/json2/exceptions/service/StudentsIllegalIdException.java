package org.it_academy.MK_JD2_90_22.json2.exceptions.service;

public class StudentsIllegalIdException extends IllegalArgumentException{

    public StudentsIllegalIdException(String message) {
        super(message);
    }

    public StudentsIllegalIdException() {
        super("ID меньше 1");
    }
}
