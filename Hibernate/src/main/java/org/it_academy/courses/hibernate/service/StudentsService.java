package org.it_academy.courses.hibernate.service;

import org.it_academy.courses.hibernate.dao.HibernateDao;
import org.it_academy.courses.hibernate.dao.StudentDao;
import org.it_academy.courses.hibernate.dao.entity.Student;
import org.it_academy.courses.hibernate.dto.NewStudent;
import org.it_academy.courses.hibernate.dto.StudentId;
import org.it_academy.courses.hibernate.dto.UpdatedStudent;
import org.it_academy.courses.hibernate.exceptions.StudentsDaoException;
import org.it_academy.courses.hibernate.exceptions.StudentsServiceException;
import org.it_academy.courses.hibernate.exceptions.api.CoursesIllegalStateException;
import org.it_academy.courses.hibernate.mappers.IStudentFactory;
import org.it_academy.courses.hibernate.mappers.StudentFactory;
import org.it_academy.courses.hibernate.service.api.ICRUDStudentsService;

import java.util.List;

public class StudentsService implements ICRUDStudentsService {

    private static final StudentsService instance = new StudentsService();
    private static final HibernateDao<Student> dao = StudentDao.getInstance();

    private StudentsService() {}

    @Override
    public long save(NewStudent newStudent) {
        IStudentFactory factory = new StudentFactory();

        return dao.save(factory.get(newStudent));
    }

    @Override
    public void delete(StudentId studentId) {
        IStudentFactory factory = new StudentFactory();

        Student student = factory.get(studentId);

        try {
            dao.delete(student);

        }catch (CoursesIllegalStateException e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            return dao.getAll();

        }catch (CoursesIllegalStateException e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Student get(long id) {
        try {
            return dao.get(id);

        }catch (CoursesIllegalStateException e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void update(UpdatedStudent updatedStudent) {
        StudentFactory studentFactory = new StudentFactory();

        Student update = studentFactory.get(updatedStudent);
        Student currentStudent;

        try {
            currentStudent = get(updatedStudent.getId());

        }catch (CoursesIllegalStateException e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }

        studentFactory.update(update, currentStudent);

        try {
            dao.update(update);
        }catch (StudentsDaoException e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    public static StudentsService getInstance() {
        return instance;
    }
}
