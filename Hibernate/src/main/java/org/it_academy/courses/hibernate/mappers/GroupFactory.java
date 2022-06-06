package org.it_academy.courses.hibernate.mappers;

import org.it_academy.courses.hibernate.dao.entity.Group;
import org.it_academy.courses.hibernate.dto.GroupId;
import org.it_academy.courses.hibernate.dto.NewGroup;
import org.it_academy.courses.hibernate.dto.UpdatedGroup;

public class GroupFactory {

    private static final GroupFactory instance = new GroupFactory();

    private GroupFactory() {
    }

    public Group get(NewGroup newGroup) {
        Group group = new Group();
        group.setName(newGroup.getName());

        return group;
    }

    public Group get(GroupId groupId) {
        Group group = new Group();
        group.setId(groupId.getId());
        return group;
    }


    public Group get(UpdatedGroup updatedGroup) {
        Group result = new Group();

        result.setId(updatedGroup.getId());
        result.setName(updatedGroup.getName());

        return result;
    }

    public static GroupFactory getInstance() {
        return instance;
    }
}
