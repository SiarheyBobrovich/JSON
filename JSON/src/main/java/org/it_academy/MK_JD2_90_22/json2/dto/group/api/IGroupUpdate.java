package org.it_academy.MK_JD2_90_22.json2.dto.group.api;

import org.it_academy.MK_JD2_90_22.json2.dao.entity.api.IGroup;

public interface IGroupUpdate extends IGroup {

    String getNewName();

    IGroup getGroup();

}
