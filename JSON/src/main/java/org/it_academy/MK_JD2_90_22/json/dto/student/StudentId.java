package org.it_academy.MK_JD2_90_22.json.dto.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.io.Serializable;

/**
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */

public class StudentId implements Serializable {
    private static final long serialVersionUID = 2L;

    private final long id;

    @JsonCreator
    public StudentId(@JsonSetter(value = "id", nulls = Nulls.FAIL) long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
