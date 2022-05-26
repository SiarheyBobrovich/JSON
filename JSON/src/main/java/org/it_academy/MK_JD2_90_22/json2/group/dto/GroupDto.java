package org.it_academy.MK_JD2_90_22.json2.group.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GroupDto {

    private final long id;
    private final String name;

    @JsonCreator
    public GroupDto(@JsonSetter("id") long id,
                    @JsonSetter("name") String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
            return id;
        }

    public String getName() {
            return name;
        }
}
