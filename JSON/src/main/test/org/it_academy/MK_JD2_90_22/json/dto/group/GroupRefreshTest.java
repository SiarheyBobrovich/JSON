package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GroupRefreshTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void serializeCamelCase() throws JsonProcessingException {
       GroupRefresh group = new GroupRefresh("group-002", "group-003");
       mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

        String s = mapper.writeValueAsString(group);

        Assertions.assertTrue(s.contains("oldName"));
        Assertions.assertTrue(s.contains("newName"));
    }

    @Test
    void serializeSnakeCase() throws JsonProcessingException {
        GroupRefresh group = new GroupRefresh("group-002", "group-003");
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        String s = mapper.writeValueAsString(group);

        Assertions.assertTrue(s.contains("old_name"));
        Assertions.assertTrue(s.contains("new_name"));
    }

    @Test
    void deserializeCamelCase() throws JsonProcessingException {
        String group = "{\"oldName\":\"group-002\",\"newName\":\"group-003\"}";

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

        GroupRefresh groupRefresh = mapper.readValue(group, GroupRefresh.class);

        Assertions.assertEquals("group-002", groupRefresh.getOldName());
        Assertions.assertEquals("group-003", groupRefresh.getNewName());
    }

    @Test
    void deserializeSnakeCase() throws IOException {
        String group = "{\"old_name\":\"group-002\",\"new_name\":\"group-003\"}";

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        GroupRefresh groupRefresh = mapper.readValue(group, GroupRefresh.class);

        Assertions.assertEquals("group-002", groupRefresh.getOldName());
        Assertions.assertEquals("group-003", groupRefresh.getNewName());
    }
}