package org.it_academy.courses.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class NewGroup {

    private final String name;

    @JsonCreator
    public NewGroup(@JsonSetter("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
