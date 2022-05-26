package org.it_academy.MK_JD2_90_22.json2.dto.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.api.IGroup;

public class Group1_1 implements IGroup {

    private long id;

    private String name;

    @JsonCreator
    public Group1_1(@JsonProperty(value = "id", required = true) long id,
                    @JsonSetter(value = "name", nulls = Nulls.AS_EMPTY) String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
