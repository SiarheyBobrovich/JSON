package org.it_academy.MK_JD2_90_22.json2.service;

import org.it_academy.MK_JD2_90_22.json2.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.dao.DaoValidator;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.IDaoValidator;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.exceptions.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.GroupServiceException;
import org.it_academy.MK_JD2_90_22.json2.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.mapers.GroupMapper;
import org.it_academy.MK_JD2_90_22.json2.service.api.ICRUDGroupService;

import java.util.List;

public class GroupService implements ICRUDGroupService {

    private static final GroupService instance = new GroupService();
    private static final ICRUDGroupDao dao = GroupDao.getInstance();
    private static final IDaoValidator validator = DaoValidator.getInstance();

    @Override
    public long save(NewGroup group) {
        String name = group.getName();

        if (name == null || name.isEmpty() || name.length() > 255) {
            throw new GroupServiceException(400, "Incorrect name");
        }

        if (validator.isExistGroup(group.getName())) {
            throw new GroupServiceException(409, "Conflict");
        }

        Group entity = GroupMapper.getInstance().map(group);

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

        if (!get(group.getId()).getName().equals(group.getName())) {

            try {
                if (validator.isExistGroup(group.getName())) {
                    throw new GroupServiceException(409, "Conflict");
                }

            } catch (GroupDaoException e) {/*ignore*/}
        }

        Group entity = GroupMapper.getInstance().map(group);

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
