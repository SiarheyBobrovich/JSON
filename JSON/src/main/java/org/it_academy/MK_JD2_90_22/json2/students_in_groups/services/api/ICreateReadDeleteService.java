package org.it_academy.MK_JD2_90_22.json2.students_in_groups.services.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICreateController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IDeleteController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IReadController;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dto.GroupsStudents;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dto.StudentInGroup;


public interface ICreateReadDeleteService extends IReadController<GroupsStudents>,
        ICreateController<StudentInGroup>, IDeleteController<StudentId> {
}
