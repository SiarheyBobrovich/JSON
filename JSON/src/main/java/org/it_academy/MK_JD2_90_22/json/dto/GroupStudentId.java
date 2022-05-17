package org.it_academy.MK_JD2_90_22.json.dto;

public class GroupStudentId {

    private String groupId;
    private String studentId;


    public GroupStudentId(String groupId, String studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


}
