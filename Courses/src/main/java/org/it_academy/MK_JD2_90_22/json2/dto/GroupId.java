package org.it_academy.MK_JD2_90_22.json2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GroupId {
    private final Long id;

    @JsonCreator
    public GroupId(@JsonSetter("id") Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
