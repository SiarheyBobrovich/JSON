package org.it_academy.courses.hibernate.service;

import org.it_academy.courses.hibernate.dao.GroupDao;
import org.it_academy.courses.hibernate.dao.HibernateDao;
import org.it_academy.courses.hibernate.dao.entity.Group;
import org.it_academy.courses.hibernate.dto.GroupId;
import org.it_academy.courses.hibernate.dto.NewGroup;
import org.it_academy.courses.hibernate.dto.UpdatedGroup;
import org.it_academy.courses.hibernate.exceptions.GroupDaoException;
import org.it_academy.courses.hibernate.exceptions.GroupServiceException;
import org.it_academy.courses.hibernate.mappers.GroupFactory;
import org.it_academy.courses.hibernate.service.api.ICRUDGroupService;

import java.util.List;

public class GroupService implements ICRUDGroupService {

    private static final GroupService instance = new GroupService();
    private static final HibernateDao<Group> dao = GroupDao.getInstance();



    @Override
    public long save(NewGroup group) {
        String name = group.getName();

        if (name == null || name.isEmpty() || name.length() > 255) {
            throw new GroupServiceException(400, "Incorrect name");
        }

        Group entity = GroupFactory.getInstance().get(group);

        try {
            return dao.save(entity);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void delete(GroupId groupId) {
        if (groupId.getId() == null) {
            throw new GroupServiceException(415, "Unsupported media type");
        }

        Group group;

        try {
            group = dao.get(groupId.getId());

        }catch (GroupDaoException e) {
            group = new Group();
            group.setId(group.getId());
        }

        try {
            dao.delete(group);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public List<Group> getAll() {
        try {
            return dao.getAll();

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Group get(long id) {
        Group group;

        try {
            group = dao.get(id);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getStatus(), e.getMessage(), e);
        }

        return group;
    }

    @Override
    public void update(UpdatedGroup group) {
        String newName = group.getName();

        if (newName == null || newName.isEmpty() || newName.length() > 255) {
            throw new GroupServiceException(400, "Incorrect new name");
        }

        Group entity = GroupFactory.getInstance().get(group);

        try {
            dao.update(entity);

        }catch (GroupDaoException e) {
            throw new GroupServiceException(e.getStatus(), e.getMessage(), e);
        }

    }

    public static GroupService getInstance() {
        return instance;
    }
}
