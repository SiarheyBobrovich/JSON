package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.GroupIllegalNameException;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDGroupService;

import java.util.List;

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

        if (groupName.getName().isEmpty()) {
            throw new GroupIllegalNameException("Не введено название группы для сохранения");
        }

        dao.delete(groupName);

        dao.save(groupName);
    }

    @Override
    public void delete(GroupName groupName) {

        if (groupName.getName().isEmpty()) {
            throw new GroupIllegalNameException("Не введено название группы для удаления");
        }

        if (!validator.isExistGroup(groupName.getName())) {
            throw new GroupIllegalNameException("Такой группы не существует");
        }

        dao.delete(groupName);
    }

    @Override
    public List<Group> getAll() {
        return dao.getAll();
    }

    @Override
    public Group get(long id) {

        if (id <= 0) {
            throw new GroupIllegalNameException("ID < 1");
        }

        return dao.get(id);
    }

    @Override
    public void update(GroupRefresh groupRefresh) {

        String oldName = groupRefresh.getOldName();
        String newName = groupRefresh.getNewName();

        if (oldName == null || oldName.isEmpty()) {
            throw new GroupIllegalNameException("Не введено старое название группы");
        }

        if (newName == null || newName.isEmpty()) {
            throw new GroupIllegalNameException("Не введено новое название группы");
        }

        if (!validator.isExistGroup(oldName)) {
            throw new GroupIllegalNameException("Такой группы не существует");
        }

        if (validator.isExistGroup(newName)) {
            throw new GroupIllegalNameException("Такая группа уже существует");
        }

        dao.update(groupRefresh);
    }

    public static GroupService getInstance() {
        return instance;
    }
}
