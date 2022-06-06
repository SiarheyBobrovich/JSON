package org.it_academy.courses.hibernate.service.api;

import org.it_academy.courses.hibernate.api.CRUD.ICRUDController;
import org.it_academy.courses.hibernate.dao.entity.Student;
import org.it_academy.courses.hibernate.dto.NewStudent;
import org.it_academy.courses.hibernate.dto.StudentId;
import org.it_academy.courses.hibernate.dto.UpdatedStudent;

public interface ICRUDStudentsService extends ICRUDController<NewStudent, Student, UpdatedStudent, StudentId> {

}
