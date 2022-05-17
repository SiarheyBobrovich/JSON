package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dto.Group;

import java.util.List;

public class GroupDao implements ICRUDController<Group> {

    private static GroupDao instance = new GroupDao();

    private GroupDao() {
    }
    @Override
    public void create(Group group) {

    }

    @Override
    public List<Group> select(String id) {
        return null;
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Group group) {
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
