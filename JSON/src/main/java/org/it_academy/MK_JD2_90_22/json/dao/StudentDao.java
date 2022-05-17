package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dto.Student;

import java.util.List;

public class StudentDao implements ICRUDController<Student> {

    private static StudentDao instance = new StudentDao();

    private StudentDao() {
    }

    @Override
    public void create(Student student) {
    }

    @Override
    public List<Student> select(String id) {
        return null;
    }

    @Override
    public void update(Student student) {
    }

    @Override
    public void delete(Student student) {
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
