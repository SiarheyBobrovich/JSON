package org.it_academy.MK_JD2_90_22.json.services.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICC;
import org.it_academy.MK_JD2_90_22.json.api.CRUD.IDC;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;

public interface ICDService extends ICC<GroupStudentsList>, IDC<GroupStudentsList> {

    /**
     * Delegating to delete a GroupStudentsList in dao
     * @param list - List of GroupStudentsList which will be deleted
     */
    @Override
    void delete(GroupStudentsList list);


    /**
     * Delegating to update a GroupStudentsList in dao
     * @param list - List of GroupStudentsList which will be updating
     */
    @Override
    void save(GroupStudentsList list);
}
