package org.it_academy.MK_JD2_90_22.json.dto.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StudentIdTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserialize() throws JsonProcessingException {
        String student = "{\"id\":1}";

        StudentId siarhey = mapper.readValue(student, StudentId.class);

        Assertions.assertEquals(1, siarhey.getId());
    }

}