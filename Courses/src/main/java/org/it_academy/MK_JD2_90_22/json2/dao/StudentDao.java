package org.it_academy.MK_JD2_90_22.json2.dao;


import org.it_academy.MK_JD2_90_22.json2.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dao.utils.StudentsQueryContainer;
import org.it_academy.MK_JD2_90_22.json2.exceptions.StudentsDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IDao, ICRUDStudentDao {

    private static final StudentDao instance = new StudentDao();

    private StudentDao() {
    }

    @Override
    public long save(Student student) {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(StudentsQueryContainer.INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());

            statement.executeUpdate();

            try(ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong("id");
                }
            }

        }catch (SQLException e) {
            throw new StudentsDaoException(500, "Insert failed", e);
        }

        throw new StudentsDaoException(500, "Insert failed");
    }

    @Override
    public void delete(Student student) {
        try {
            execute(
                    StudentsQueryContainer.DELETE_FROM_CROSS_TABLE + StudentsQueryContainer.DELETE_QUERY,
                    student.getId(), student.getId());

        }catch (SQLException e) {
            throw new StudentsDaoException(500, "Delete failed", e);
        }
    }

    @Override
    public List<Student> getAll() {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(StudentsQueryContainer.SELECT_QUERY)) {

            try(ResultSet resultSet = statement.executeQuery()) {
                List<Student> all = new ArrayList<>();

                while (resultSet.next()) {
                    all.add(map(resultSet));
                }

                return all;
            }
        }catch (SQLException e) {
            throw new StudentsDaoException(500, "Select failed", e);
        }
    }

    @Override
    public Student get(long id) {

        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(StudentsQueryContainer.SELECT_WHERE_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return map(resultSet);
                }

                throw new StudentsDaoException(404, "Not found");
            }
        }catch (SQLException e) {
            throw new StudentsDaoException(500, "Select failed", e);
        }
    }

    @Override
    public void update(Student student) {
        try {
            execute(StudentsQueryContainer.UPDATE_QUERY,
                    student.getName(), student.getAge(), student.getScore(),
                    student.isOlympicGamer(), student.getId()
            );

        }catch (SQLException e) {
            throw new StudentsDaoException(500, "Delete failed", e);
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();

        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));

        return student;
    }

    public static StudentDao getInstance() {
        return instance;
    }
}
