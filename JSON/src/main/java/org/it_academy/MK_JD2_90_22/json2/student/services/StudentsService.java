package org.it_academy.MK_JD2_90_22.json2.student.services;

import org.it_academy.MK_JD2_90_22.json2.student.dao.StudentDao;
import org.it_academy.MK_JD2_90_22.json2.student.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.student.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.student.dto.UpdatedStudent;
import org.it_academy.MK_JD2_90_22.json2.student.services.api.ICRUDStudentsService;

import java.util.List;

public class StudentsService implements ICRUDStudentsService {

    private static final StudentsService instance = new StudentsService();
    private static final ICRUDStudentDao dao = StudentDao.getInstance();

    private StudentsService() {}

    @Override
    public long save(NewStudent newStudent) {
        return 0;
    }

    @Override
    public void delete(StudentId studentId) {

    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student get(long id) {
        return null;
    }

    @Override
    public void update(UpdatedStudent updatedStudent) {

    }

    public static StudentsService getInstance() {
        return instance;
    }
}
