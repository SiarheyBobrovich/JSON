package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupDao1_1;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroupUpdate;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.group.GroupIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.group.GroupIllegalStateException;
import org.it_academy.MK_JD2_90_22.json.services.api.ICRUDGroupService1_1;

import java.util.List;
import java.util.Objects;

public class GroupService1_1 implements ICRUDGroupService1_1 {

    private static final GroupService1_1 instance;
    private static final IValidationDao validator = ValidationDao.getInstance();
    private static final GroupDao1_1 dao = GroupDao1_1.getInstance();

    static {
        instance = new GroupService1_1();
    }

    private GroupService1_1() {
    }

    @Override
    public void save(IGroup group) {

        if (group.getName().isEmpty()) {
            throw new GroupIllegalArgumentException("Не введено название группы для сохранения");
        }

        if(validator.isExistGroup(group.getName())) {
            throw new GroupIllegalStateException();
        }

        dao.save(group);
    }

    @Override
    public void delete(IGroup group) {

        if (group.getId() < 1) {
            throw new GroupIllegalArgumentException();
        }

        if (group.getName().isEmpty()) {
            throw new GroupIllegalArgumentException("Не введено название группы для сохранения");
        }

        if (Objects.isNull(dao.get(group.getId()))) {
            throw new GroupIllegalStateException("Такой группы не существует");
        }

        dao.delete(group);
    }

    @Override
    public List<Group> getAll() {
        return dao.getAll();
    }

    @Override
    public Group get(long id) {

        if (id <= 0) {
            throw new GroupIllegalArgumentException();
        }

        return dao.get(id);
    }

    @Override
    public void update(IGroup group) {

        long id = group.getId();
        String newName = group.getName();

        if (id < 1) {
            throw new GroupIllegalArgumentException();
        }

        if (newName == null || newName.isEmpty()) {
            throw new GroupIllegalArgumentException("Не введено новое название группы");
        }

        boolean isExist = dao.getAll().stream()
                .anyMatch(
                        g -> g.getName().equals(group.getName())
                );

        if (!isExist) {
            throw new GroupIllegalStateException("Такая группа уже существует");
        }

        dao.update(group);
    }

    public static GroupService1_1 getInstance() {
        return instance;
    }
}
