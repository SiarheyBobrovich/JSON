package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDService;

import java.util.List;

public class StudentService implements ICRUDService<StudentIdDto> {

    private static final StudentService instance;

    static {
        instance = new StudentService();
    }

    private StudentService() {
    }

    public void create(StudentIdDto studentIdDto) {

    }

    @Override
    public List<StudentIdDto> select(String id) {
        return null;
    }

    @Override
    public void update(StudentIdDto studentIdDto) {

    }

    @Override
    public void delete(StudentIdDto studentIdDto) {

    }

    public static StudentService getInstance() {
        return instance;
    }
}
