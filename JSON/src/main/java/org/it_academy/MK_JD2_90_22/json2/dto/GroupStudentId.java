package org.it_academy.MK_JD2_90_22.json2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GroupStudentId.Builder.class)
public class GroupStudentId {

    private final long groupId;
    private final long studentId;

    public GroupStudentId(long groupId, long studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getStudentId() {
        return studentId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    static class Builder {

        private long groupId;
        private long studentId;

        private Builder() {}

        private void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        private void setStudentId(long studentId) {
            this.studentId = studentId;
        }

        private GroupStudentId build() {
            return new GroupStudentId(groupId, studentId);
        }
    }
}
