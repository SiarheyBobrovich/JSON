package org.it_academy.MK_JD2_90_22.json2.group.service;

import org.it_academy.MK_JD2_90_22.json2.group.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupDto;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupServiceException;
import org.it_academy.MK_JD2_90_22.json2.api.CRUD.ICRUDController;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupCreate;
import org.it_academy.MK_JD2_90_22.json2.group.mapers.GroupMapper;

import java.util.List;

public class GroupService implements ICRUDController<GroupCreate, Group, GroupDto, Integer> {

    private static final GroupService instance = new GroupService();
    private static final ICRUDGroupDao dao = GroupDao.getInstance();

    @Override
    public void save(GroupCreate group) {
        String name = group.getName();

        if (name == null || name.isEmpty() || name.length() > 255) {
            throw new GroupServiceException("Incorrect name");
        }

        Group entity = GroupMapper.getInstance().map(group);

        try {
            dao.save(entity);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        Group entity = GroupMapper.getInstance().map(id);

        try {
            dao.delete(entity);
        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Group> getAll() {
        try {
            return dao.getAll();

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group get(long id) {
        Group group;

        try {
            group = dao.get(id);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getMessage(), e);
        }

        return group;
    }

    @Override
    public void update(GroupDto group) {
        String newName = group.getNewName();

        if (newName == null || newName.isEmpty() || newName.length() > 255) {
            throw new GroupServiceException("Incorrect new name");
        }

        Group entity = GroupMapper.getInstance().map(group);

        try {
            dao.update(entity);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getMessage(), e);
        }

    }

    public static GroupService getInstance() {
        return instance;
    }
}
