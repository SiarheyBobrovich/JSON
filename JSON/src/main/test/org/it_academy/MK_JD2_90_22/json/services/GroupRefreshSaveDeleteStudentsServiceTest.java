package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GroupRefreshSaveDeleteStudentsServiceTest {

    private static final GroupSaveDeleteStudentsService instance =
            GroupSaveDeleteStudentsService.getInstance();
    private static GroupStudentsList groupList;

    private static List<GroupStudentsList> list;

    @BeforeAll
    static void init() {
        list = new ArrayList<>();

        List<StudentIdDto> collect =
                Arrays.stream(new StudentIdDto[]
                        {new StudentIdDto(1),new StudentIdDto(2)}
                        ).collect(Collectors.toList());

        List<StudentIdDto> collect2 =
                Arrays.stream(new StudentIdDto[]
                        {new StudentIdDto(4),new StudentIdDto(3)}
                ).collect(Collectors.toList());

        groupList = new GroupStudentsList("MK-JD2-90-22", collect);
        list.add(groupList);

        groupList = new GroupStudentsList("MK-JD2-90-23", collect2);
        list.add(groupList);
    }

    @Test
    void saveDelete() {
        Assertions.assertDoesNotThrow(() -> instance.save(list));
        Assertions.assertThrows(RuntimeException.class, () -> instance.save(list));

        Assertions.assertDoesNotThrow(() -> instance.delete(list));
    }
}