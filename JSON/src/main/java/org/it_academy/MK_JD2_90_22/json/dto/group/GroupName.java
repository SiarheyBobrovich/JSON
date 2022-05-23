package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public class GroupName {

    private String name;

    public GroupName(@JsonSetter(value = "name", nulls = Nulls.FAIL) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
