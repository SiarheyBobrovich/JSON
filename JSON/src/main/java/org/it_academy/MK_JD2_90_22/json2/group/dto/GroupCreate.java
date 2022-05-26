package org.it_academy.MK_JD2_90_22.json2.group.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GroupCreate {

    private final String name;

    @JsonCreator
    public GroupCreate(@JsonSetter("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
