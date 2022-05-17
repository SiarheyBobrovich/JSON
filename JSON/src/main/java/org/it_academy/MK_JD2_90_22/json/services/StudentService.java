package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dto.Student;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDService;

import java.util.List;

public class StudentService implements ICRUDService<Student> {

    private static final StudentService instance;

    static {
        instance = new StudentService();
    }

    private StudentService() {
    }

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

    public static StudentService getInstance() {
        return instance;
    }
}
