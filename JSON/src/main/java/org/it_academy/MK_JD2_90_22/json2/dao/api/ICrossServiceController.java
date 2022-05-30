package org.it_academy.MK_JD2_90_22.json2.dao.api;

import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupStudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupWithStudents;

import java.util.List;

public interface ICrossServiceController {

    void save(GroupId g, StudentId s);

    void delete(GroupStudentId s);

    List<GroupWithStudents> getAll();

    List<Student> getG(long id);

    Group getS(long id);
}
