package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICrossController;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentId;

public class CrossDao implements ICrossController<GroupStudentId> {

    private static final CrossDao instance = new CrossDao();

    private CrossDao() {
    }

    @Override
    public void delete(GroupStudentId student) {

    }

    @Override
    public void update(GroupStudentId student) {

    }

    public static CrossDao getInstance() {
        return instance;
    }
}
