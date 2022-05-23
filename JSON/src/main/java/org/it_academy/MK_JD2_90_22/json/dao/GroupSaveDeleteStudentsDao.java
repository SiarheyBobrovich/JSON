package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRDDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupSaveDeleteStudentsDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupSaveDeleteStudentsDao implements IDao, ICRDDao {


    private static final String DB_NAME = "courses.students_in_groups";

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                DB_NAME + "\n" +
                "\t(group_name, " +
                "\tstudent_id)\n" +
            "\tVALUES " +
                "(?, ?);"
            ;
    private static final String DELETE_QUERY =
            "DELETE FROM " +
                DB_NAME + " " +
            "WHERE " +
                "student_id = ?;";

    private static final String SELECT_STUDENTS_BY_GROUP_NAME_QUERY =
            "SELECT\n" +
                "\tg.id as group_id,\n" +
                "\tg.name as group_name,\n" +
                "\ts.id as student_id,\n" +
                "\ts.name as student_name,\n" +
                "\ts.age as student_age,\n" +
                "\ts.score as student_score,\n" +
                "\ts.olympic_gamer as student_olympic_gamer\n" +
            "FROM\n" +
                DB_NAME + " as sg\n" +
            "JOIN\n" +
                "\tcourses.groups as g " +
            "ON\n" +
                "\tsg.group_name = g.name\n" +
            "JOIN\n" +
                "\tcourses.students as s " +
            "ON\n" +
                "\tsg.student_id = s.id\n" +
            "WHERE\n" +
                "\tg.id = ?;";

    private static final String SELECT_ALL_GROUP =
            "SELECT\n" +
                "\tg.id as group_id,\n" +
                "\tg.name as group_name,\n" +
                "\ts.id as student_id,\n" +
                "\ts.name as student_name,\n" +
                "\ts.age as student_age,\n" +
                "\ts.score as student_score,\n" +
                "\ts.olympic_gamer as student_olympic_gamer\n" +
            "FROM\n" +
                DB_NAME + " as sg\n" +
            "JOIN\n" +
                "\tcourses.groups as g " +
            "ON\n" +
                "\tsg.group_name = g.name\n" +
            "JOIN\n" +
                "\tcourses.students as s " +
            "ON\n" +
                "\tsg.student_id = s.id\n" +
            "ORDER BY \n" +
                "\tg.id;";

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
                    throw new GroupSaveDeleteStudentsDaoException(
                            "Сбой при работе с базой " + DB_NAME, e);
                }
            }
        }
    }

    @Override
    public List<StudentsInGroup> getAll() {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GROUP)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                return map(resultSet);
            }

        }catch (SQLException e) {
            throw new GroupSaveDeleteStudentsDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public StudentsInGroup get(long id) {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_STUDENTS_BY_GROUP_NAME_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                return map(resultSet).get(0);
            }

        }catch (SQLException e) {
            throw new GroupSaveDeleteStudentsDaoException("Сбой при работе с базой" + DB_NAME, e);
        }
    }

    @Override
    public void delete(GroupStudentsList groupStudentsList) {
        for (Map.Entry<String, List<StudentId>> stringListEntry : groupStudentsList) {
            for (StudentId studentId : stringListEntry.getValue()) {

                try {
                    execute(DELETE_QUERY, studentId.getId());

                }catch (SQLException e) {
                    throw new GroupSaveDeleteStudentsDaoException(
                            "Сбой при работе с базой " + DB_NAME, e);
                }
            }
        }
    }

    private List<StudentsInGroup> map(ResultSet rs) throws SQLException {
        List<StudentsInGroup> result = new ArrayList<>();
        List<Student> students = null;
        Group group = null;

        while (rs.next()) {
            long groupCurrentId = rs.getLong("group_id");
            String groupCurrentName = rs.getString("group_name");
            long studentId = rs.getLong("student_id");
            String studentName = rs.getString("student_name");
            int studentAge = rs.getInt("student_age");
            double studentScore = rs.getDouble("student_score");
            boolean studentOlimpicGamer = rs.getBoolean("student_olympic_gamer");

            if (group == null || groupCurrentId != group.getId()) {

                if (group != null) {
                    result.add(new StudentsInGroup(group, students));
                }

                group = new Group(groupCurrentId, groupCurrentName);
                students = new ArrayList<>();
            }

            Student student = new Student();
            student.setId(studentId);
            student.setName(studentName);
            student.setAge(studentAge);
            student.setScore(studentScore);
            student.setOlympicGamer(studentOlimpicGamer);

            students.add(student);
        }

        if (group != null) {
            result.add(new StudentsInGroup(group, students));
        }

        return result;
    }

    public static GroupSaveDeleteStudentsDao getInstance() {
        return instance;
    }
}
