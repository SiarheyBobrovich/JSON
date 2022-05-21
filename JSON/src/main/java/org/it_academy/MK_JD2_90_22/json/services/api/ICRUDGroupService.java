package org.it_academy.MK_JD2_90_22.json.services.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;

import java.util.List;

public interface ICRUDGroupService extends ICRUDController<GroupName, Group, GroupRefresh, GroupName>{
    @Override
    void save(GroupName groupName);

    @Override
    void delete(GroupName groupName);

    @Override
    List<Group> getAll();

    @Override
    Group get(long id);

    @Override
    void update(GroupRefresh groupRefresh);
}
