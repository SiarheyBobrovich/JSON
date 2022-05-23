package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.StudentDao;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.StudentsIllegalIdException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.StudentNullPointerException;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDStudentsService;

import java.util.List;
import java.util.Objects;

public class StudentService implements ICRUDStudentsService {

    private static final IValidationDao validator = ValidationDao.getInstance();
    private static final StudentService instance;
    private static final ICRUDStudentDao dao;

    static {
        dao = StudentDao.getInstance();
        instance = new StudentService();
    }

    private StudentService() {
    }

    @Override
    public void save(StudentDto studentDto) {
        if (studentDto.getName() == null ||
            studentDto.getName().isEmpty() ||
            studentDto.getAge() == 0) {
            throw new StudentNullPointerException();
        }

        dao.save(studentDto);
    }

    @Override
    public void delete(StudentId studentId) {
        if (studentId.getId() < 1) {
            throw new StudentsIllegalIdException();
        }

        if (!validator.isExistStudent(studentId.getId())) {
            throw new StudentsIllegalIdException("Такого студента не существует");
        }

        dao.delete(studentId);
    }

    @Override
    public List<Student> getAll() {
        return dao.getAll();
    }

    @Override
    public Student get(long id) {
        if (id < 1) {
            throw new StudentsIllegalIdException();
        }

        return dao.get(id);
    }

    @Override
    public void update(StudentDto studentDto) {
        if (studentDto.getName() == null ||
                studentDto.getName().isEmpty() ||
                studentDto.getAge() == 0) {
            throw new StudentNullPointerException();
        }
        if (studentDto.getId() < 1) {
            throw new StudentsIllegalIdException();
        }

        if (!validator.isExistStudent(studentDto.getId())) {
            throw new StudentsIllegalIdException("Такого студента не существует");
        }

        dao.update(studentDto);
    }

    private void isNull(Object o) {

        if (Objects.isNull(o)){
            throw new StudentNullPointerException();
        }
    }

    public static StudentService getInstance() {
        return instance;
    }
}
