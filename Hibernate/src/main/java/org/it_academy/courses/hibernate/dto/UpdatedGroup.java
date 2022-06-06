package org.it_academy.courses.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UpdatedGroup {

    private final long id;
    private final String name;

    @JsonCreator
    public UpdatedGroup(@JsonSetter("id") long id,
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
