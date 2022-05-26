package org.it_academy.MK_JD2_90_22.json2.group.service;

import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao1_1;
import org.it_academy.MK_JD2_90_22.json2.group.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.Validator;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupDto;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupServiceException;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupCreate;
import org.it_academy.MK_JD2_90_22.json2.group.mapers.GroupMapper;
import org.it_academy.MK_JD2_90_22.json2.group.service.api.ICRUDGroupService;

import java.util.List;

public class GroupService implements ICRUDGroupService {

    private static final GroupService instance = new GroupService();
    private static final ICRUDGroupDao dao = GroupDao.getInstance();

    @Override
    public long save(GroupCreate group) {
        String name = group.getName();

        if (name == null || name.isEmpty() || name.length() > 255) {
            throw new GroupServiceException(400, "Incorrect name");
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
        Group entity = GroupMapper.getInstance().map(groupId.getId());

        try {
            dao.delete(entity);
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
    public void update(GroupDto group) {
        String newName = group.getName();

        if (newName == null || newName.isEmpty() || newName.length() > 255) {
            throw new GroupServiceException(400, "Incorrect new name");
        }

        try {
            if (Validator.getInstance().isExistGroup(group.getId(), group.getName())) {
                throw new GroupServiceException(409, "Conflict");
            }

        }catch (GroupDaoException e) {/*ignore*/}

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
