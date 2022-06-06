package org.it_academy.MK_JD2_90_22.json2.service.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedStudent;

public interface ICRUDStudentsService extends ICRUDController<NewStudent, Student, UpdatedStudent, StudentId> {

}
