package org.it_academy.MK_JD2_90_22.json2.services.api;

import org.it_academy.MK_JD2_90_22.json2.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json2.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;

import java.util.List;

public interface ICRUDStudentsService extends ICRUDController<StudentDto, Student, StudentDto, StudentId> {
    @Override
    void save(StudentDto studentDto);

    @Override
    void delete(StudentId studentId);

    @Override
    List<Student> getAll();

    @Override
    Student get(long id);

    @Override
    void update(StudentDto studentDto);
}
