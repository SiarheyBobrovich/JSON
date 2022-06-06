package org.it_academy.courses.hibernate.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.it_academy.courses.hibernate.dao.entity.Student;

import java.sql.*;
import java.util.List;

public class StudentDao extends HibernateDao<Student> {

    private static final StudentDao instance = new StudentDao();

    private StudentDao() {
    }

    @Override
    public long save(Student student) {
        return super.save(student);
    }

    @Override
    public void delete(Student student) {
        super.delete(student);
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
    public void update(Student student) {
        EntityManager manager = getFactory().getManager();

        manager.getTransaction().begin();


        Student currentStudent = manager.find(Student.class, student.getId(), LockModeType.OPTIMISTIC);

        currentStudent.setGroup(student.getGroup());
        currentStudent.setAge(student.getAge());
        currentStudent.setName(student.getName());
        currentStudent.setScore(student.getScore());
        currentStudent.setOlympicGamer(student.isOlympicGamer());

        manager.getTransaction().commit();
        manager.close();
    }

    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();

        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));

        return student;
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
