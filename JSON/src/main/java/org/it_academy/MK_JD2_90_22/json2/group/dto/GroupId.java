package org.it_academy.MK_JD2_90_22.json2.group.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GroupId {
    private final long id;

    @JsonCreator
    public GroupId(@JsonSetter long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
