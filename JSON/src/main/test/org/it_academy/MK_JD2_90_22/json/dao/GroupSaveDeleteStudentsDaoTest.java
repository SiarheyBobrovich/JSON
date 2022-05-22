package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupSaveDeleteStudentsDaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class GroupSaveDeleteStudentsDaoTest {

    private static GroupSaveDeleteStudentsDao instance = GroupSaveDeleteStudentsDao.getInstance();
    private static GroupStudentsList groupList;

    @BeforeAll
    static void init() {
        List<StudentId> list = new ArrayList<>();
        list.add(new StudentId(1));
        list.add(new StudentId(3));
        list.add(new StudentId(4));
        groupList = new GroupStudentsList("MK-JD2-90-22", list);
    }

    @Test
    void saveDelete() throws InterruptedException {
        Assertions.assertDoesNotThrow(() -> instance.save(groupList));
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

        Assertions.assertThrows(RuntimeException.class, () -> instance.save(groupList));

        Assertions.assertDoesNotThrow(() -> instance.delete(groupList));
    }

    @Test
    void getAll() {
        Assertions.assertDoesNotThrow(() -> instance.getAll());
    }

    @Test
    void get() {
        long id = 2;
        Assertions.assertDoesNotThrow(() -> instance.get(id));
//
//        StudentsInGroup studentsInGroup = instance.get(id);
//        System.out.println(studentsInGroup.getGroup());
//        for (Student student : studentsInGroup.getStudents()) {
//            System.out.println("\t" + student);
//        }

    }
}