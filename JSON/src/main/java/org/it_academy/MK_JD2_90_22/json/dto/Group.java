package org.it_academy.MK_JD2_90_22.json.dto;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {

    private String name;

    private List<Student> studentsList;

    public Group(String name, List<Student> studentsList) {
        this.name = name;
        this.studentsList = studentsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }
}
