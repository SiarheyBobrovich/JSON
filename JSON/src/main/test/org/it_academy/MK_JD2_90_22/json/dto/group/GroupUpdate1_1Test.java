package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupUpdate1_1Test {


    public final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    @Test
    void serialize() throws JsonProcessingException {
        GroupUpdate1_1 group = new GroupUpdate1_1(12, "group-001");
        String s = mapper.writeValueAsString(group);

        System.out.println(s);

        Assertions.assertTrue(s.contains("id"));
        Assertions.assertTrue(s.contains("12"));
        Assertions.assertTrue(s.contains("name"));
        Assertions.assertTrue(s.contains("group-001"));
    }

    @Test
    void deserialize1() throws JsonProcessingException {
        String s = "{\"id\":12,\"new_name\":\"group-001\"}";
        GroupUpdate1_1 group = mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .readValue(s, GroupUpdate1_1.class);

        Assertions.assertEquals(12, group.getId());
        Assertions.assertEquals("group-001", group.getName());
    }

    @Test
    void deserialize2() throws JsonProcessingException {
        String s = "{\"id\":12}";
        Assertions.assertThrows(JsonProcessingException.class, () -> mapper.readValue(s, GroupUpdate1_1.class));
    }

    @Test
    void deserializeFail1() throws JsonProcessingException {
        String s = "{\"name\":\"group-001\"}";
        Assertions.assertThrows(JsonProcessingException.class, () -> mapper.readValue(s, GroupUpdate1_1.class));
    }

    @Test
    void deserializeFail2() throws JsonProcessingException {
        String s = "{}";
        Assertions.assertThrows(JsonProcessingException.class, () -> mapper.readValue(s, GroupUpdate1_1.class));
    }
}