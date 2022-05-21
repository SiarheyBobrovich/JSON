package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValidationDao implements IValidationDao {

    private static final ValidationDao instance = new ValidationDao();

    private ValidationDao() {
    }

    private static final String SELECT_FOR_NAME_QUERY =
            "SELECT " +
                    "id, name " +
                    "FROM " +
                    "courses.groups " +
                    "WHERE name = ?;"
            ;



    @Override
    public boolean isExistGroup(String groupName) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOR_NAME_QUERY)){

            statement.setString(1, groupName);
            return statement.executeQuery().getRow() > 0;

        }catch (SQLException e) {
            //Изменить
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isExistGroup(long id) {
        return GroupDao.getInstance().get(id) != null;
    }

    @Override
    public boolean isExistStudent(long studentId) {
        return StudentDao.getInstance().get(studentId) != null;
    }

    public static ValidationDao getInstance() {
        return instance;
    }
}
