package org.it_academy.MK_JD2_90_22.json2.service.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;

public interface ICRUDGroupService extends ICRUDController<NewGroup, Group, UpdatedGroup, GroupId> {

}
