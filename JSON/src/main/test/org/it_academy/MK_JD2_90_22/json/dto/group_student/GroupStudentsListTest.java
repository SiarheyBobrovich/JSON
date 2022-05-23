package org.it_academy.MK_JD2_90_22.json.dto.group_student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class GroupStudentsListTest {


    private static final ObjectMapper mapper = new ObjectMapper();
    private GroupStudentsList group;

    @BeforeEach
    void init() {
        group = new GroupStudentsList("group-002",
                Stream.of(
                                new StudentId(1),
                                new StudentId(2),
                                new StudentId(3))
                        .collect(Collectors.toList())
        );
    }

    @Test
    void serializeCamelCase() throws JsonProcessingException {

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

        String s = mapper.writeValueAsString(group);

        System.out.println(s);

        Assertions.assertTrue(s.contains("list"));
        Assertions.assertTrue(s.contains("group-002"));
        Assertions.assertTrue(s.contains("id"));
        Assertions.assertTrue(s.contains("1"));
        Assertions.assertTrue(s.contains("id"));
        Assertions.assertTrue(s.contains("2"));
        Assertions.assertTrue(s.contains("id"));
        Assertions.assertTrue(s.contains("3"));
    }


    @Test
    void deserializeCamelCase() throws JsonProcessingException {
        String group = "{\"list\":{\"group-002\":[{\"id\":1},{\"id\":2},{\"id\":3}]}}";

        GroupStudentsList groupStudentsList = mapper.readValue(group, GroupStudentsList.class);

        System.out.println(groupStudentsList);

        for (int i = 0; i < this.group.getList().size(); i++) {

            Assertions.assertEquals(
                    this.group.getList().get("group-002").get(i).getId(),
                    groupStudentsList.getList().get("group-002").get(i).getId());
        }


    }
}