package org.it_academy.MK_JD2_90_22.json2.group.dao.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICreateController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IDeleteController;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IReadController;

public interface ICRDDao extends ICreateController<GroupStudentsList>, IDeleteController<GroupStudentsList>, IReadController<StudentsInGroup> {
}
