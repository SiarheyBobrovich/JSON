package org.it_academy.MK_JD2_90_22.json.dto;

import java.io.Serializable;

/**
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */

public class StudentIdDto implements Serializable {
    private static final long serialVersionUID = 2L;

    private long id;

    public StudentIdDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
