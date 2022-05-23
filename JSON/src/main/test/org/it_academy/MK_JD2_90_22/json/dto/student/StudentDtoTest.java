package org.it_academy.MK_JD2_90_22.json.dto.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDtoTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void serialize() throws JsonProcessingException {
        StudentDto siarhey = StudentDto.create().setName("Siarhey")
                .setAge(34)
                .setScore(2.2)
                .setOlympicGamer(true).build();

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        String student = mapper.writeValueAsString(siarhey);

        Assertions.assertTrue(student.contains("id"));
        Assertions.assertTrue(student.contains("0"));
        Assertions.assertTrue(student.contains("name"));
        Assertions.assertTrue(student.contains("Siarhey"));
        Assertions.assertTrue(student.contains("age"));
        Assertions.assertTrue(student.contains("34"));
        Assertions.assertTrue(student.contains("score"));
        Assertions.assertTrue(student.contains("2.2"));
        Assertions.assertTrue(student.contains("olympicGamer"));
        Assertions.assertTrue(student.contains("true"));
    }

    @Test
    void serializeSnakeCase() throws JsonProcessingException {
        StudentDto siarhey = StudentDto.create().setName("Siarhey")
                .setAge(34)
                .setScore(2.2)
                .setOlympicGamer(true).build();

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        String student = mapper.writeValueAsString(siarhey);

        Assertions.assertTrue(student.contains("id"));
        Assertions.assertTrue(student.contains("0"));
        Assertions.assertTrue(student.contains("name"));
        Assertions.assertTrue(student.contains("Siarhey"));
        Assertions.assertTrue(student.contains("age"));
        Assertions.assertTrue(student.contains("34"));
        Assertions.assertTrue(student.contains("score"));
        Assertions.assertTrue(student.contains("2.2"));
        Assertions.assertTrue(student.contains("olympic_gamer"));
        Assertions.assertTrue(student.contains("true"));
    }

    @Test
    void deserialize() throws JsonProcessingException {
        String student = "{\"id\":1,\"name\":\"Siarhey\",\"age\":34,\"score\":2.2,\"olympic_gamer\":true}";

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        StudentDto siarhey = mapper.readValue(student, StudentDto.class);

        Assertions.assertEquals(1, siarhey.getId());
        Assertions.assertEquals("Siarhey", siarhey.getName());
        Assertions.assertEquals(34, siarhey.getAge());
        Assertions.assertEquals(2.2, siarhey.getScore());
        Assertions.assertEquals(true, siarhey.isOlympicGamer());
    }

    @Test
    void deserialize2() throws JsonProcessingException {
        String student = "{\"id\":1,\"name\":\"Siarhey\",\"age\":34,\"score\":2.2,\"olympic_gamer\":true}";

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        StudentDto siarhey = mapper.readValue(student, StudentDto.class);

        Assertions.assertEquals(1, siarhey.getId());
        Assertions.assertEquals("Siarhey", siarhey.getName());
        Assertions.assertEquals(34, siarhey.getAge());
        Assertions.assertEquals(2.2, siarhey.getScore());
        Assertions.assertEquals(true, siarhey.isOlympicGamer());
    }
}