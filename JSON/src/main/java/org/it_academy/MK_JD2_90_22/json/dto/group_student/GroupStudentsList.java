package org.it_academy.MK_JD2_90_22.json.dto.group_student;

import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupStudentsList implements Serializable, Iterable<Map.Entry<String, List<StudentId>>> {

    private Map<String, List<StudentId>> list;

    public GroupStudentsList() {
        list = new HashMap<>();
    }

    public GroupStudentsList(String groupName, List<StudentId> studentsList) {
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
}
