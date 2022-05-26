package org.it_academy.MK_JD2_90_22.json2.services;

import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json2.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json2.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.group.GroupIllegalStateException;
import org.it_academy.MK_JD2_90_22.json2.services.api.ICRUDGroupService;

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
            throw new GroupIllegalStateException("Не введено название группы для сохранения");
        }

        if(validator.isExistGroup(groupName.getName())) {
            return;
        }

        dao.save(groupName);
    }

    @Override
    public void delete(GroupName groupName) {

        if (groupName.getName().isEmpty()) {
            throw new GroupIllegalStateException("Не введено название группы для удаления");
        }

        if (!validator.isExistGroup(groupName.getName())) {
            throw new GroupIllegalStateException("Такой группы не существует");
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
            throw new GroupIllegalStateException("ID < 1");
        }

        return dao.get(id);
    }

    @Override
    public void update(GroupRefresh groupRefresh) {

        String oldName = groupRefresh.getOldName();
        String newName = groupRefresh.getNewName();

        if (oldName == null || oldName.isEmpty()) {
            throw new GroupIllegalStateException("Не введено старое название группы");
        }

        if (newName == null || newName.isEmpty()) {
            throw new GroupIllegalStateException("Не введено новое название группы");
        }

        if (!validator.isExistGroup(oldName)) {
            throw new GroupIllegalStateException("Такой группы не существует");
        }

        if (validator.isExistGroup(newName)) {
            throw new GroupIllegalStateException("Такая группа уже существует");
        }

        dao.update(groupRefresh);
    }

    public static GroupService getInstance() {
        return instance;
    }
}
