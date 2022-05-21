package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;

public interface ICRUDGroupDao extends ICRUDController<GroupName, Group, GroupRefresh, GroupName> {
}
