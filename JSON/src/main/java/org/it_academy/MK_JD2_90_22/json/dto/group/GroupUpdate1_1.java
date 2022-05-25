package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.it_academy.MK_JD2_90_22.json.dao.entity.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroupUpdate;

@JsonDeserialize(builder = GroupUpdate1_1.Builder.class)
public class GroupUpdate1_1 implements IGroupUpdate {

    private final IGroup group;

    private final String newName;

    public GroupUpdate1_1(IGroup group, String newName) {
        this.group = group;
        this.newName = newName;
    }

    @Override
    public IGroup getGroup() {
        return group;
    }

    @JsonIgnore
    @Override
    public long getId() {
        return this.group.getId();
    }

    @JsonIgnore
    @Override
    public String getName() {
        return this.group.getName();
    }

    @Override
    public String getNewName() {
        return this.newName;
    }

    public static Builder create() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private IGroup group;

        private String newName;
        private Builder() {
        }

        public Builder setGroup(IGroup group) {
            this.group = group;
            return this;
        }

        public Builder setNewName(String newName) {
            this.newName = newName;
            return this;
        }

        public IGroupUpdate build() {
            return new GroupUpdate1_1(group, newName);
        }
    }
}
