package org.it_academy.MK_JD2_90_22.json2.dto.group;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GroupRefresh.Builder.class)
public class GroupRefresh {

    private final String oldName;

    private final String newName;

    public GroupRefresh(String oldName,
                        String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    protected static class Builder {

        private String oldName;

        private String newName;

        private Builder() {
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }

        private GroupRefresh build() {
            return new GroupRefresh(oldName, newName);
        }
    }
}
