package org.it_academy.MK_JD2_90_22.json2.group.mapers;

import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupCreate;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupDto;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;

public class GroupMapper {

    private static final GroupMapper instance = new GroupMapper();

    public GroupMapper() {
    }

    public Group map(GroupCreate groupCreate) {
        Group group = new Group();
        group.setName(groupCreate.getName());

        return group;
    }

    public Group map(long id) {
        Group group = new Group();
        group.setId(id);

        return group;
    }

    public Group map(GroupDto groupDto) {
        Group group = new Group();

        group.setId(group.getId());
        group.setName(groupDto.getNewName());

        return group;
    }

    public static GroupMapper getInstance() {
        return instance;
    }
}
