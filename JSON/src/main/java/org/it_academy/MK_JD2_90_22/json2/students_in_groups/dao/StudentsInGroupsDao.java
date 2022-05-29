package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao;

import org.it_academy.MK_JD2_90_22.json2.group.dao.DataSourceFactory;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.student.exceptions.studentsInGroups.StudentsInGroupsDaoException;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api.ICrossDaoController;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.utils.QueryContainer;

import java.sql.*;
import java.util.*;

public class StudentsInGroupsDao implements IDao, ICrossDaoController<Group, Student> {

    private static final StudentsInGroupsDao instance = new StudentsInGroupsDao();

    private StudentsInGroupsDao() {}

    @Override
    public void save(Group group, Student student) {
        try {
            execute(QueryContainer.INSERT_QUERY, group.getId(), student.getId());

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Insert failed", e);
        }
    }

    @Override
    public void deleteS(Student student) {
        try {
            execute(QueryContainer.DELETE_BY_STUDENT_ID_QUERY, student.getId());

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Delete failed", e);
        }
    }

    @Override
    public void deleteG(Group group) {
        try {
            execute(QueryContainer.DELETE_BY_GROUP_ID_QUERY, group.getId());

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Delete failed", e);
        }
    }

    @Override
    public Map<Group, Set<Student>> getAll() {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_ALL_QUERY)) {

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return map(resultSet);
                }
            }

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Select failed", e);
        }

        return new HashMap<>();
    }

    @Override
    public Set<Student> getG(long id) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_BY_GROUP_ID_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapStudent(resultSet);
                }
            }

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Select failed", e);
        }

        throw new StudentsInGroupsDaoException(404, "Not found");
    }

    @Override
    public Group getS(long id) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_BY_STUDENT_ID_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Group group = new Group();
                    group.setName(resultSet.getString("name"));
                    group.setId(resultSet.getLong("id"));

                    return group;
                }
            }

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Select failed", e);
        }

        throw new StudentsInGroupsDaoException(404, "Not found");
    }

    private Map<Group, Set<Student>> map(ResultSet rs) throws SQLException {

        Map<Group, Set<Student>> resultList = new HashMap<>();

        Long groupId = null;
        Group group = null;
        Set<Student> students = null;

        do {
            long currentGroupId = rs.getLong("group_id");

            if (groupId == null || groupId != currentGroupId) {
                groupId = currentGroupId;

                String groupName = rs.getString("group_name");

                group = new Group();
                group.setId(groupId);
                group.setName(groupName);

                students = new LinkedHashSet<>();
            }

            Student student = new Student();
            student.setId(rs.getLong("student_id"));
            student.setName(rs.getString("student_name"));
            student.setAge(rs.getInt("student_age"));
            student.setScore(rs.getDouble("student_score"));
            student.setOlympicGamer(rs.getBoolean("student_olympic_gamer"));

            students.add(student);

            resultList.put(group, students);

        }while (rs.next());

        return resultList;
    }

    private Set<Student> mapStudent(ResultSet rs) throws SQLException {
        Set<Student> students = new LinkedHashSet<>();

        do {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setScore(rs.getDouble("score"));
            student.setOlympicGamer(rs.getBoolean("olympic_gamer"));

            students.add(student);
        }while (rs.next());

        return students;
    }

    public static StudentsInGroupsDao getInstance() {
        return instance;
    }
}
