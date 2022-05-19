package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;

import java.util.List;

public class StudentDao implements ICRUDController<StudentIdDto> {

    private static StudentDao instance = new StudentDao();

    private StudentDao() {
    }

    @Override
    public void save(StudentIdDto studentIdDto) {
    }

    @Override
    public List<StudentIdDto> get(String id) {
        return null;
    }

    @Override
    public void update(StudentIdDto studentIdDto) {
    }

    @Override
    public void delete(StudentIdDto studentIdDto) {
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
