package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDGroupService;

import java.util.List;
import java.util.Objects;

public class GroupService implements ICRUDGroupService {

    private static final GroupService instance;
    private static final IValidationDao validator = ValidationDao.getInstance();

    private static final GroupDao dao = GroupDao.getInstance();

    static {
        instance = new GroupService();
    }

    private GroupService() {
    }

    @Override
    public void save(GroupName groupName) {
        isNull(groupName);

        if (validator.isExistGroup(groupName.getName())) {
            new IllegalArgumentException("Такая группа уже существует");
        }

        dao.save(groupName);
    }

    @Override
    public void delete(GroupName groupName) {
        isNull(groupName);

        dao.delete(groupName);
    }

    @Override
    public List<Group> getAll() {
        return dao.getAll();
    }

    @Override
    public Group get(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id меньше нуля");
        }

        return dao.get(id);
    }

    @Override
    public void update(GroupRefresh groupRefresh) {
        isNull(groupRefresh);

        dao.update(groupRefresh);
    }

    private void isNull(Object o) {

        if (Objects.isNull(o)){
            new IllegalArgumentException("Данные не переданы");
        }
    }

    public static GroupService getInstance() {
        return instance;
    }
}
