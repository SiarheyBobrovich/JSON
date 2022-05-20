package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupSaveDeleteStudentsDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.ICDController;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.services.api.ICrossService;

import java.util.List;

public class GroupSaveDeleteStudentsService implements ICrossService<GroupStudentsList> {

    private static final ICDController<GroupStudentsList> controller =
            GroupSaveDeleteStudentsDao.getInstance();
    private static final GroupSaveDeleteStudentsService instance;

    static {
        instance = new GroupSaveDeleteStudentsService();
    }

    private GroupSaveDeleteStudentsService() {
    }

    @Override
    public void delete(List<GroupStudentsList> t) {
        t.forEach(controller :: delete);
    }

    @Override
    public void save(List<GroupStudentsList> t) {
        t.forEach(controller :: save);
    }

    public static GroupSaveDeleteStudentsService getInstance() {
        return instance;
    }
}
