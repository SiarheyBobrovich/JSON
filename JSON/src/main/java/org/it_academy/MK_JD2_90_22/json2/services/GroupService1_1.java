package org.it_academy.MK_JD2_90_22.json2.services;

import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IValidationDao1_1;
import org.it_academy.MK_JD2_90_22.json2.dao.GroupDao1_1;
import org.it_academy.MK_JD2_90_22.json2.dao.ValidationDao1_1;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.api.IGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.group.api.IGroupUpdate;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.group.GroupIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.group.GroupIllegalStateException;
import org.it_academy.MK_JD2_90_22.json2.services.api.ICRUDGroupService1_1;

import java.util.List;
import java.util.Objects;

public class GroupService1_1 implements ICRUDGroupService1_1 {

    private static final GroupService1_1 instance;
    private static final IValidationDao1_1 validator = ValidationDao1_1.getInstance();
    private static final GroupDao1_1 dao = GroupDao1_1.getInstance();

    static {
        instance = new GroupService1_1();
    }

    private GroupService1_1() {
    }

    @Override
    public void save(IGroup group) {

        if (group == null || group.getName() == null) {
            throw new GroupIllegalArgumentException("Не верно переданные данные");
        }

        if (group.getName().isEmpty()) {
            throw new GroupIllegalArgumentException("Не введено название группы для сохранения");
        }

        if(validator.isExistGroup(group)) {
            throw new GroupIllegalStateException();
        }

        dao.save(group);
    }

    @Override
    public void delete(IGroup group) {

        if (group.getId() < 1) {
            throw new GroupIllegalArgumentException();
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
    public void update(IGroupUpdate group) {
        if (group == null || group.getGroup() == null) {
            throw new GroupIllegalArgumentException("Не верно переданные данные");
        }

        long id = group.getId();
        String newName = group.getNewName();

        if (id < 1) {
            throw new GroupIllegalArgumentException();
        }

        if (newName == null || newName.isEmpty()) {
            throw new GroupIllegalArgumentException("Не введено новое название группы");
        }

        if (!validator.isExistGroup(group.getId())) {
            throw new GroupIllegalStateException("Такой группы не существует");
        }

        long existGroupId = validator.isExistGroup(group);

        if (existGroupId > 0 && existGroupId != group.getId()) {
            throw new GroupIllegalStateException("Такая группа уже существует");
        }

        dao.update(group);
    }

    public static GroupService1_1 getInstance() {
        return instance;
    }
}
