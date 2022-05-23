package org.it_academy.MK_JD2_90_22.json.dto.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupNameTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void jsonSerialize() throws JsonProcessingException {
        GroupName groupName = new GroupName("group-001");

        String s = mapper.writeValueAsString(groupName);

        Assertions.assertTrue(s.contains("name"));
        Assertions.assertTrue(s.contains("group-001"));
    }

    @Test
    void jsonDeserialize() throws JsonProcessingException {
        String str = "{\"name\":\"group-002\"}";

        GroupName groupName = mapper.readValue(str, GroupName.class);

        Assertions.assertEquals("group-002", groupName.getName());
    }

    @Test
    void jsonDeserialize2() throws JsonProcessingException {
        String str = "{\"name\":null}";

        Assertions.assertThrows(InvalidNullException.class,
                () -> mapper.readValue(str, GroupName.class)
        );
    }
}