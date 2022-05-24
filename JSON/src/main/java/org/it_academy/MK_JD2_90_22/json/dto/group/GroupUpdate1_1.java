package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.annotation.*;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;

public class GroupUpdate1_1 implements IGroup {

    /**
     * A group's id which will be changing
     */
    private long id;

    /**
     * New name for group
     */
    private String newName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GroupUpdate1_1(@JsonProperty(value = "id", required = true) long id,
                          @JsonAlias({"newName", "new_name"})
                          @JsonProperty(value = "name", required = true) String newName) {
        this.id = id;
        this.newName = newName;
    }

    @Override
    public String getDbName() {
        return DB_NAME;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.newName;
    }
}
