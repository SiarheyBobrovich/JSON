package org.it_academy.MK_JD2_90_22.json.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GroupSaveDeleteStudentsDaoTest {

    private static GroupSaveDeleteStudentsDao instance = GroupSaveDeleteStudentsDao.getInstance();


    @Test
    void getAll() {
        Assertions.assertDoesNotThrow(() -> instance.getAll());
    }

}