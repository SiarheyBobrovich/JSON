package org.it_academy.MK_JD2_90_22.json.services.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;

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
