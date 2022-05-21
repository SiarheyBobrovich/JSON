package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;

public interface ICRUDStudentDao extends ICRUDController<StudentDto, Student, StudentDto, StudentId> {
}
