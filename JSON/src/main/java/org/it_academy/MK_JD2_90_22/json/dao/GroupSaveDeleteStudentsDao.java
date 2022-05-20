package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICDController;
import org.it_academy.MK_JD2_90_22.json.dto.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.StudentIdDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupSaveDeleteStudentsDao implements ICDController<GroupStudentsList> {

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

    public static GroupSaveDeleteStudentsDao getInstance() {
        return instance;
    }
}
