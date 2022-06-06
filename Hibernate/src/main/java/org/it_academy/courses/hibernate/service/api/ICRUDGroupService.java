package org.it_academy.courses.hibernate.service.api;

import org.it_academy.courses.hibernate.api.CRUD.ICRUDController;
import org.it_academy.courses.hibernate.dao.entity.Group;
import org.it_academy.courses.hibernate.dto.GroupId;
import org.it_academy.courses.hibernate.dto.NewGroup;
import org.it_academy.courses.hibernate.dto.UpdatedGroup;

public interface ICRUDGroupService extends ICRUDController<NewGroup, Group, UpdatedGroup, GroupId> {

    long save(NewGroup group);
}
