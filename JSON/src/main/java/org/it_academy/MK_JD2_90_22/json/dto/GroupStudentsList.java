package org.it_academy.MK_JD2_90_22.json.dto;

import java.io.Serializable;
import java.util.List;

public class GroupStudentsList implements Serializable {
    private String groupName;

    private List<StudentIdDto> studentsList;

    public GroupStudentsList(String groupName, List<StudentIdDto> studentsList) {
        this.groupName = groupName;
        this.studentsList = studentsList;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<StudentIdDto> getStudentsList() {
        return studentsList;
    }

}
