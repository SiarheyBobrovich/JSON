package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dto;

import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;

import java.util.Set;

public class GroupWithStudents {
    private final long id;

    private final String name;

    private final Set<Student> students;

    public GroupWithStudents(long id, String name, Set<Student> students) {
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

    public Set<Student> getStudents() {
        return students;
    }
}
