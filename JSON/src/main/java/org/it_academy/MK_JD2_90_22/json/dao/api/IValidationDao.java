package org.it_academy.MK_JD2_90_22.json.dao.api;

public interface IValidationDao {

    boolean isExistGroup(String groupName);

    boolean isExistGroup(long id);

    boolean isExistStudent(long studentId);
}
