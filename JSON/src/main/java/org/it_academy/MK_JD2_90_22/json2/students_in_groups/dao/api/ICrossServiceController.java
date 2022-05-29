package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api;

import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;

import java.util.Map;
import java.util.Set;

public interface ICrossServiceController {

    void save(GroupId g, StudentId s);

    void deleteG(GroupId g);

    void deleteS(StudentId s);

    Map<Group, Set<Student>> getAll();

    Map.Entry<Group, Set<Student>> getG(long id);

    Group getS(long id);
}
