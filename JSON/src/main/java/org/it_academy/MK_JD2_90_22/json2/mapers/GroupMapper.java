package org.it_academy.MK_JD2_90_22.json2.mapers;

import org.it_academy.MK_JD2_90_22.json2.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;

public class GroupMapper {

    private static final GroupMapper instance = new GroupMapper();

    private GroupMapper() {
    }

    public Group map(NewGroup newGroup) {
        Group group = new Group();
        group.setName(newGroup.getName());

        return group;
    }

    public Group map(UpdatedGroup updatedGroup) {
        Group result = new Group();

        result.setId(updatedGroup.getId());
        result.setName(updatedGroup.getName());

        return result;
    }

    public static GroupMapper getInstance() {
        return instance;
    }
}
