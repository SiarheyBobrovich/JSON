package org.it_academy.MK_JD2_90_22.json2.dto.group_student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.it_academy.MK_JD2_90_22.json2.dto.student.StudentId;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = GroupStudentsList.Builder.class)
public class GroupStudentsList implements Serializable, Iterable<Map.Entry<String, List<StudentId>>> {


    private Map<String, List<StudentId>> list;

    public GroupStudentsList() {
        list = new HashMap<>();
    }

    public GroupStudentsList(String groupName,
                             List<StudentId> studentsList) {
        list = new HashMap<>();
        this.list.put(groupName, studentsList);
    }

    public void add(String groupName, List<StudentId> studentsList){
        list.put(groupName, studentsList);
    }

    public Map<String, List<StudentId>> getList() {
        return list;
    }

    @Override
    public Iterator<Map.Entry<String, List<StudentId>>> iterator() {
        return list.entrySet().iterator();
    }

    @Override
    public String toString() {
        return "GroupStudentsList{" +
                "list=" + list +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    protected static class Builder {

        private Map<String, List<StudentId>> list;

        @JsonCreator
        private Builder(@JsonSetter(value = "list", nulls = Nulls.FAIL) Map<String, List<StudentId>> list) {
            this.list = list;
        }

        private GroupStudentsList build() {
            GroupStudentsList groupStudentsList = new GroupStudentsList();
            groupStudentsList.list = this.list;

            return groupStudentsList;
        }
    }
}
