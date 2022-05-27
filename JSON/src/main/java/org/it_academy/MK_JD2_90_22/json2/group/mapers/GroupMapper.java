package org.it_academy.MK_JD2_90_22.json2.group.mapers;

import org.it_academy.MK_JD2_90_22.json2.group.dto.NewGroup;
import org.it_academy.MK_JD2_90_22.json2.group.dto.UpdatedGroup;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;

public class GroupMapper {

    private static final GroupMapper instance = new GroupMapper();

    public GroupMapper() {
    }

    public Group map(NewGroup newGroup) {
        Group group = new Group();
        group.setName(newGroup.getName());

        return group;
    }

    public Group map(long id) {
        Group group = new Group();
        group.setId(id);

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
