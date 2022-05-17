package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDService;

import java.util.List;

public class GroupService implements ICRUDService {

    private static final GroupService instance;

    static {
        instance = new GroupService();
    }

    private GroupService() {
    }


    @Override
    public void create(Object o) {

    }

    @Override
    public List select(String id) {
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }

    public static GroupService getInstance() {
        return instance;
    }
}
