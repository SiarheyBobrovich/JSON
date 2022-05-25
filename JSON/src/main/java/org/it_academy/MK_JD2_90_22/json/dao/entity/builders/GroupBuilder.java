package org.it_academy.MK_JD2_90_22.json.dao.entity.builders;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dao.entity.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.group.GroupIllegalArgumentException;

@JsonPOJOBuilder(withPrefix = "set")
public class GroupBuilder {
    private long id;
    private String name;

    public GroupBuilder() {
    }


    @JsonAnySetter
    public GroupBuilder setId(long id) {
        this.id = id;
        return this;
    }

    @JsonAnySetter
    public GroupBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IGroup build() {
        return new Group(this.id, this.name);
    }
}
