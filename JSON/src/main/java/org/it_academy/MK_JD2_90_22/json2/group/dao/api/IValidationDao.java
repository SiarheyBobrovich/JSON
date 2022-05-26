package org.it_academy.MK_JD2_90_22.json2.group.dao.api;

public interface IValidationDao {

    boolean isExistGroup(long id, String groupName);
    boolean isExistGroup(String groupName);
}
