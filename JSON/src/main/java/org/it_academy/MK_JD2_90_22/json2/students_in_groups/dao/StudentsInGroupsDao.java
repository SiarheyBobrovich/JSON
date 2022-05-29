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
            execute(QueryContainer.DELETE_BY_STUDENT_ID_QUERY, group.getId());

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Delete failed", e);
        }
    }

    @Override
    public Map<Group, Set<Student>> getAll() {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_ALL_QUERY)) {

            try(ResultSet resultSet = statement.executeQuery()) {

                return map(resultSet);
            }

        }catch (SQLException e) {
            throw new StudentsInGroupsDaoException(500, "Select failed", e);
        }
    }

    @Override
    public Map.Entry<Group, Set<Student>> getG(long id) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_BY_GROUP_ID_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.getRow() > 0) {
                    return map(resultSet).entrySet().iterator().next();
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

                if (resultSet.getRow() > 0) {
                    Group group = new Group();
                    group.setName(resultSet.getString("g.name"));
                    group.setId(resultSet.getLong("g.id"));

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

        while (rs.next()) {
            long groupId = rs.getLong("g.id");
            String groupName = rs.getString("g.name");

            Group group = new Group();
            group.setId(groupId);
            group.setName(groupName);

            Set<Student> students = new HashSet<>();
            long currentGroupId;

            do {

                currentGroupId = rs.getLong("g.id");

                Student student = new Student();
                student.setId(rs.getLong("s.id"));
                student.setName(rs.getString("s.name"));
                student.setAge(rs.getInt("s.age"));
                student.setScore(rs.getDouble("s.score"));
                student.setOlympicGamer(rs.getBoolean("olympic_gamer"));

                students.add(student);

            } while (Objects.equals(currentGroupId, groupId));

            resultList.put(group, students);
        }

        return resultList;
    }

    public static StudentsInGroupsDao getInstance() {
        return instance;
    }
}
