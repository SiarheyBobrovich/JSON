package org.it_academy.MK_JD2_90_22.json2.group.service.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.group.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.group.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;

public interface ICRUDGroupService extends ICRUDController<NewGroup, Group, UpdatedGroup, GroupId> {

}
