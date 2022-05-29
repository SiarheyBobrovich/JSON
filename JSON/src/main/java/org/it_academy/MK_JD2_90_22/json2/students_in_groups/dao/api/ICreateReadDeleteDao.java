package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICreateController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IDeleteController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IReadController;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dto.GroupsStudents;

import java.util.Map;
import java.util.Set;

public interface ICreateReadDeleteDao extends IReadController<Map.Entry<Group, Set<Student>>>,
        ICreateController<GroupsStudents>, IDeleteController<Student> {
}