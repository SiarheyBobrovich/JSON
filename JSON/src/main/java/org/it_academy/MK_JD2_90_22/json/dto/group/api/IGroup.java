package org.it_academy.MK_JD2_90_22.json.dto.group.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IGroup {

    String DB_NAME = "courses.groups";

    @JsonIgnore
    String getDbName();

    long getId();

    String getName();
}
