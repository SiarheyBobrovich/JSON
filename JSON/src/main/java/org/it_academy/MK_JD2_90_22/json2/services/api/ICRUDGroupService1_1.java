package org.it_academy.MK_JD2_90_22.json2.services.api;

import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.api.IGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.group.api.IGroupUpdate;

import java.util.List;

public interface ICRUDGroupService1_1 extends ICRUDController<IGroup, Group, IGroupUpdate, IGroup> {

    @Override
    default void save(IGroup group) {

    }

    @Override
    default void delete(IGroup group) {

    }

    @Override
    default List<Group> getAll() {
        return null;
    }

    @Override
    default Group get(long id) {
        return null;
    }

    @Override
    default void update(IGroupUpdate group) {

    }
}
