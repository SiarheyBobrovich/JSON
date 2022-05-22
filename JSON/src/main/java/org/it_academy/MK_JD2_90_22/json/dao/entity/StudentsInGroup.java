package org.it_academy.MK_JD2_90_22.json.dao.entity;

import java.util.List;

public class StudentsInGroup {

    private Group group;

    private List<Student> students;

    public StudentsInGroup(Group group, List<Student> students) {
        this.group = group;
        this.students = students;
    }

    public Group getGroup() {
        return group;
    }

    public List<Student> getStudents() {
        return students;
    }
}
