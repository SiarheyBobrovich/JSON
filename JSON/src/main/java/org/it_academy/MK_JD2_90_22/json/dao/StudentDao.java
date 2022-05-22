package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentDto;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.StudentDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IDao, ICRUDStudentDao {

    private static final String DB_NAME = "courses.students";

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                DB_NAME + " " +
                "(name, age, score, olimpic_gamer) " +
            "VALUES " +
                "(?, ?, ?, ?);"
            ;

    private static final String SELECT_QUERY =
            "SELECT " +
                "id, name, age, score, olimpic_gamer " +
            "FROM " +
                DB_NAME + ";"
            ;

    //Сформировать запрос
    private static final String SELECT_WHERE_QUERY =
            "SELECT " +
                "id, name, age, score, olimpic_gamer " +
            "FROM " +
                DB_NAME + " " +
            "WHERE " +
                "id = ?;"
            ;


    //Сформировать запрос
    private static final String UPDATE_QUERY =
            "UPDATE " +
                DB_NAME + " " +
            "SET " +
                    "name = ? " +
            "WHERE " +
                    "id = ?;"
            ;

    private static final String DELETE_QUERY =
            "DELETE FROM " +
                DB_NAME + " " +
            "WHERE " +
                "id = ?;";

    private static StudentDao instance = new StudentDao();

    private StudentDao() {
    }

    @Override
    public List<Student> getAll() {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement preparedStatement =  connection.prepareStatement(SELECT_QUERY)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return map(resultSet);
            }

        }catch (SQLException e) {
            throw new StudentDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public Student get(long id) {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =  connection.prepareStatement(SELECT_WHERE_QUERY)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return map(resultSet).get(0);
            }

        }catch (SQLException e) {
            throw new StudentDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }

    }

    @Override
    public void save(StudentDto studentDto) {
        try {
            execute(INSERT_QUERY,
                    studentDto.getName(),
                    studentDto.getAge(),
                    studentDto.getScore(),
                    studentDto.isOlympicGamer()
            );

        }catch (SQLException e) {
            throw new StudentDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }
    }


    @Override
    public void update(StudentDto studentDto) {
        try {
            execute(UPDATE_QUERY,

                    studentDto.getName(),
                    studentDto.getAge(),
                    studentDto.getScore(),
                    studentDto.isOlympicGamer()
            );

        }catch (SQLException e) {
            throw new StudentDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }
    }
    @Override
    public void delete(StudentId studentId) {
        try {
            execute(DELETE_QUERY,
                    studentId.getId()
            );

        }catch (SQLException e) {
            throw new StudentDaoException(
                    "Сбой при работе с базой " + DB_NAME, e);
        }
    }

    private List<Student> map(ResultSet rs) throws SQLException {
        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setAge(rs.getInt("age"));
            student.setScore(rs.getDouble("score"));
            student.setOlympicGamer(rs.getBoolean("olimpic_gamer"));
            students.add(student);
        }

        return students;
    }

    public static StudentDao getInstance() {
        return instance;
    }

}
