package org.it_academy.courses.hibernate.dto;

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
