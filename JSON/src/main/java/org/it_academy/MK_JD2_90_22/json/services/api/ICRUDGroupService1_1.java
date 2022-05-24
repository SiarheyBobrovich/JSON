package org.it_academy.MK_JD2_90_22.json.services.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroupUpdate;

import java.util.List;

public interface ICRUDGroupService1_1 extends ICRUDController<IGroup, Group, IGroup, IGroup>{

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
    default void update(IGroup groupUpdate) {

    }
}
