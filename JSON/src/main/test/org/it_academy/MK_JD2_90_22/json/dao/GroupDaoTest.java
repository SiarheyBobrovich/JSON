package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;

import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GroupDaoTest {

    private static final GroupDao dao = GroupDao.getInstance();
    private final GroupName refresh = new GroupName("Group");
    private final GroupName delete = new GroupName("NewGroup");

    @Test
    void get() {
        Assertions.assertDoesNotThrow(() -> dao.save(refresh));

        Group groups = dao.get(1);

        Assertions.assertNotNull(groups);
    }

    @Test
    void getAll() {
        List<Group> groups = dao.getAll();

        Assertions.assertNotNull(groups);
    }

    @Test
    void saveUpdateDelete() {
        GroupRefresh update = new GroupRefresh("Group", "NewGroup");

        Assertions.assertDoesNotThrow(() -> dao.update(update));

        Assertions.assertEquals("NewGroup", dao.getAll().get(0).getName());
    }

    @Test
    void delete() {
        Assertions.assertDoesNotThrow(() -> dao.delete(delete));
    }
}