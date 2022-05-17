package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.CrossDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.ICrossController;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentId;
import org.it_academy.MK_JD2_90_22.json.services.api.ICrossService;

import java.util.List;

public class GroupCreateDeleteListStudentsService implements ICrossService<GroupStudentId> {

    private static final GroupCreateDeleteListStudentsService instance;

    static {
        instance = new GroupCreateDeleteListStudentsService();
    }

    private GroupCreateDeleteListStudentsService() {
    }

    @Override
    public void delete(List<GroupStudentId> t) {
        ICrossController<GroupStudentId> crossDao = CrossDao.getInstance();
        t.forEach(crossDao :: delete);
    }

    @Override
    public void update(List<GroupStudentId> t) {
        ICrossController<GroupStudentId> crossDao = CrossDao.getInstance();
        t.forEach(crossDao :: update);
    }

    public static GroupCreateDeleteListStudentsService getInstance() {
        return instance;
    }
}
