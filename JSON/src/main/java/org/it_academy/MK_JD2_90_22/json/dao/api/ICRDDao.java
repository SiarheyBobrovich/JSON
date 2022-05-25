package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICreateController;
import org.it_academy.MK_JD2_90_22.json.api.CRUD.IDeleteController;
import org.it_academy.MK_JD2_90_22.json.api.CRUD.IReadController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;

public interface ICRDDao extends ICreateController<GroupStudentsList>, IDeleteController<GroupStudentsList>, IReadController<StudentsInGroup> {
}
