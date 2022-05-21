package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;

import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GroupDaoTest {

    private static final GroupDao dao = GroupDao.getInstance();

    @Test
    void delete() {
        GroupName refresh = new GroupName("NewGroup");

        Assertions.assertDoesNotThrow(() -> dao.save(refresh));

        Assertions.assertDoesNotThrow(() -> dao.delete(refresh));

    }

    @Test
    void getAll() {
        List<Group> groups = dao.getAll();
        System.out.println(groups);
        Assertions.assertNotNull(groups);
    }

    @Test
    void get() {
        Group groups = dao.get(1);
        System.out.println(groups);
        Assertions.assertNotNull(groups);
    }

    @Test
    void saveUpdateDelete() {
        GroupName save = new GroupName("Group");
        GroupRefresh update = new GroupRefresh("Group", "NewGroup");
        GroupName delete = new GroupName("NewGroup");
        int countRow = dao.getAll().size();

        Assertions.assertDoesNotThrow(() -> dao.save(save));

        Assertions.assertEquals(countRow + 1, dao.getAll().size());

        Assertions.assertDoesNotThrow(() -> dao.update(update));

        Assertions.assertEquals("NewGroup", dao.getAll().get(countRow).getName());

        Assertions.assertDoesNotThrow(() -> dao.delete(delete));

        Assertions.assertEquals(countRow, dao.getAll().size());

    }
}