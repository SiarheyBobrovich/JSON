package org.it_academy.MK_JD2_90_22.json2.group.dao.api;

import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;

public interface ICRUDStudentDao extends ICRUDController<StudentDto, Student, StudentDto, StudentId> {
}
