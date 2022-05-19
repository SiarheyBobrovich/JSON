package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDController;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GroupDao implements ICRUDController<GroupStudentsList> {

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                "courses.students_in_groupe\n" +
                "\t(groupe_name, student_id)\n" +
                "\tVALUES " +
                    "(?, ?);"
            ;

    private static final String SELECT_QUERY =
            "SELECT " +
                "student_id " +
            "FROM " +
                "courses.students_in_groupe " +
                "WHERE " +
                    "group_name = ?;"
            ;

    private static final String UPDATE_QUERY =
            "UPDATE " +
                "courses.students_in_groupe " +
                "SET " +
                    "student_id=? " +
                "WHERE " +
                    "groupe_name = ?;"
            ;

    private static final String DELETE_QUERY =
            "DELETE FROM " +
                "courses.students_in_groupe " +
            "WHERE " +
                "student_id = ?;";

    private static GroupDao instance = new GroupDao();

    private GroupDao() {
    }

    @Override
    public void save(GroupStudentsList groupStudentsList) {
        String groupName = groupStudentsList.getGroupName();
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            for (StudentIdDto studentIdDto : groupStudentsList.getStudentsList()) {
                preparedStatement.setString(1, groupName);
                preparedStatement.setLong(2, studentIdDto.getId());
                preparedStatement.execute();
            }

        }catch (SQLException e) {
            throw new RuntimeException("Студента или группы не существует", e);
        }
    }

    @Override
    public List<GroupStudentsList> get(String id) {
        return null;
    }

    @Override
    public void update(GroupStudentsList groupStudentsList) {

    }

    @Override
    public void delete(GroupStudentsList groupStudentsList) {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            for (StudentIdDto studentIdDto : groupStudentsList.getStudentsList()) {
                preparedStatement.setLong(1, studentIdDto.getId());
                preparedStatement.execute();
            }

        }catch (SQLException e) {
            throw new RuntimeException("Студента или группы не существует", e);
        }
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
