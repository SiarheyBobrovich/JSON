package org.it_academy.MK_JD2_90_22.json2.factories;

import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;

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
