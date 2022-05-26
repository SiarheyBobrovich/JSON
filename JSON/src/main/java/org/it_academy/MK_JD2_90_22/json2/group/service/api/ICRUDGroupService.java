package org.it_academy.MK_JD2_90_22.json2.group.service.api;

import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupCreate;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupDto;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;

import java.util.List;

public interface ICRUDGroupService extends ICRUDController<GroupCreate, Group, GroupDto, GroupId> {

}
