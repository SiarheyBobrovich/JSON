package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GroupSaveDeleteStudentsServiceTest {

    private static final GroupSaveDeleteStudentsService instance =
            GroupSaveDeleteStudentsService.getInstance();
    private static GroupStudentsList groupList;

    @BeforeAll
    static void init() {

        List<StudentId> collect =
                Arrays.stream(new StudentId[]
                        {new StudentId(1),new StudentId(2)}
                        ).collect(Collectors.toList());

        List<StudentId> collect2 =
                Arrays.stream(new StudentId[]
                        {new StudentId(4),new StudentId(3)}
                ).collect(Collectors.toList());

        groupList = new GroupStudentsList("MK-JD2-90-22", collect);
        groupList.add("MK-JD2-90-23", collect2);
    }

    @Test
    void saveDelete() throws InterruptedException {
        Assertions.assertDoesNotThrow(() -> instance.save(groupList));
        Thread.sleep(1000 * 5);
        Assertions.assertThrows(RuntimeException.class, () -> instance.save(groupList));

        Assertions.assertDoesNotThrow(() -> instance.delete(groupList));
    }
}