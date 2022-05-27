package org.it_academy.MK_JD2_90_22.json2.student.services.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.student.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.student.dto.UpdatedStudent;

public interface ICRUDStudentsService extends ICRUDController<NewStudent, Student, UpdatedStudent, StudentId> {

}
