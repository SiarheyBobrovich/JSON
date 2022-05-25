package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.dao.entity.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroupUpdate;

public interface IValidationDao1_1 {

    boolean isExistGroup(IGroup groupName);

    long isExistGroup(IGroupUpdate group);

    boolean isExistGroup(long id);

    boolean isExistStudent(long studentId);
}
