package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICDDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GroupSaveDeleteStudentsDao implements IDao, ICDDao {

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                "courses.students_in_groupe\n" +
                "\t(groupe_name, student_id)\n" +
                "\tVALUES " +
                    "(?, ?);"
            ;
    private static final String DELETE_QUERY =
            "DELETE FROM " +
                "courses.students_in_groupe " +
            "WHERE " +
                "student_id = ?;";

    private static final GroupSaveDeleteStudentsDao instance = new GroupSaveDeleteStudentsDao();

    private GroupSaveDeleteStudentsDao() {
    }

    @Override
    public void save(GroupStudentsList groupStudentsList) {
        for (Map.Entry<String, List<StudentId>> stringListEntry : groupStudentsList) {
            String groupName = stringListEntry.getKey();

            for (StudentId studentId : stringListEntry.getValue()) {
                try {
                    execute(INSERT_QUERY, groupName, studentId.getId());
                }catch (SQLException e) {
                    //поменять
                    throw new RuntimeException();
                }
            }
        }
    }

    @Override
    public void delete(GroupStudentsList groupStudentsList) {
        for (Map.Entry<String, List<StudentId>> stringListEntry : groupStudentsList) {
            String groupName = stringListEntry.getKey();

            for (StudentId studentId : stringListEntry.getValue()) {
                try {
                    execute(DELETE_QUERY, studentId.getId());
                }catch (SQLException e) {
                    //поменять
                    throw new RuntimeException();
                }
            }
        }
    }

    public static GroupSaveDeleteStudentsDao getInstance() {
        return instance;
    }
}
