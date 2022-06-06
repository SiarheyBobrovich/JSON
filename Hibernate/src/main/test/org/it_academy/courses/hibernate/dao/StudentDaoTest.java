package org.it_academy.courses.hibernate.dao;

import org.it_academy.courses.hibernate.dao.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {

    private static final HibernateDao<Student> dao = StudentDao.getInstance();

    @Test
    void save() {
        Student student = new Student();
        student.setName("Олег");
        student.setAge(24);
        student.setScore(9.0);
        student.setOlympicGamer(true);

        dao.save(student);

    }

    @Test
    void getAll() {
        List<Student> all = dao.getAll();

        System.out.println(all);
    }
}