package org.it_academy.MK_JD2_90_22.json2.dto;

import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;

import java.util.List;

public class GroupWithStudents {
    private final long id;

    private final String name;

    private final List<Student> students;

    public GroupWithStudents(long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
}
