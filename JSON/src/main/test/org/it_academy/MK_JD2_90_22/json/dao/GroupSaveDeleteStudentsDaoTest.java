package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GroupSaveDeleteStudentsDaoTest {

    private static GroupDao instance = GroupDao.getInstance();
    private static GroupStudentsList groupList;

    @BeforeAll
    static void init() {
        List<StudentIdDto> list = new ArrayList<>();
        list.add(new StudentIdDto(1));
        list.add(new StudentIdDto(3));
        list.add(new StudentIdDto(4));
        groupList = new GroupStudentsList("MK-JD2-90-22", list);
    }

    @Test
    void saveDelete() {
        Assertions.assertDoesNotThrow(() -> instance.save(groupList));
        Assertions.assertThrows(RuntimeException.class, () -> instance.save(groupList));

        Assertions.assertDoesNotThrow(() -> instance.delete(groupList));
    }
}