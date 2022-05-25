package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupUpdate1_1Test {


    public final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    @Test
    void serialize() throws JsonProcessingException {
        GroupUpdate1_1 group = new GroupUpdate1_1(new Group(12, "group-000"), "group-001");
        String s = mapper.writeValueAsString(group);

        System.out.println(s);

        Assertions.assertTrue(s.contains("id"));
        Assertions.assertTrue(s.contains("12"));
        Assertions.assertTrue(s.contains("name"));
        Assertions.assertTrue(s.contains("group-001"));
    }

    @Test
    void deserialize1() throws JsonProcessingException {

        String s = "{\n" +
                "  \"group\": {\n" +
                "    \"id\": 12,\n" +
                "    \"name\": \"group-000\"\n" +
                "  },\n" +
                "  \"newName\": \"group-001\"\n" +
                "}";

        GroupUpdate1_1 group = mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .readValue(s, GroupUpdate1_1.class);

        Assertions.assertEquals(12, group.getId());
        Assertions.assertEquals("group-000", group.getName());
        Assertions.assertEquals("group-001", group.getNewName());

    }

    @Test
    void deserialize2() throws JsonProcessingException {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        String s = "{\n" +
                "  \"newName\": \"group-001\"\n" +
                "}";

        GroupUpdate1_1 group = mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .readValue(s, GroupUpdate1_1.class);

        Assertions.assertNull(group.getGroup());
        Assertions.assertEquals("group-001", group.getNewName());
    }

    @Test
    void deserializeFail1() throws JsonProcessingException {
        String s = "{\n" +
                "  \"group\": {\n" +
                "    \"id\": 12,\n" +
                "    \"name\": \"group-000\"\n" +
                "  }\n" +
                "}";

        GroupUpdate1_1 group = mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .readValue(s, GroupUpdate1_1.class);

        Assertions.assertNull(group.getNewName());
        Assertions.assertEquals("group-000", group.getName());
        Assertions.assertEquals(12, group.getId());
    }

    @Test
    void deserializeFail2() throws JsonProcessingException {
        String s = "{\n" +
                "  \"group\": {\n" +
                "    \"name\": \"group-000\"\n" +
                "  },\n" +
                "  \"newName\": \"group-001\"\n" +
                "}";

        GroupUpdate1_1 group = mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .readValue(s, GroupUpdate1_1.class);

        Assertions.assertEquals("group-000", group.getName());
        Assertions.assertEquals(0, group.getId());
        Assertions.assertEquals("group-001", group.getNewName());
    }
}