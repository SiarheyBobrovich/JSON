package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;

public interface IValidationDao1_1 {

    boolean isExistGroup(IGroup groupName);

    boolean isExistGroup(long id);

    boolean isExistStudent(long studentId);
}
