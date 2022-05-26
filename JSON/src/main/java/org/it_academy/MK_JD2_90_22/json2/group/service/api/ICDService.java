package org.it_academy.MK_JD2_90_22.json2.group.service.api;

import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICreateController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IDeleteController;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.IReadController;

public interface ICDService extends ICreateController<GroupStudentsList>, IReadController<StudentsInGroup>, IDeleteController<GroupStudentsList> {

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
    long save(GroupStudentsList list);
}
