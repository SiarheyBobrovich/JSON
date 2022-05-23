package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValidationDao implements IValidationDao {

    private static final String DB_NAME = "courses.groups";

    private static final ValidationDao instance = new ValidationDao();

    private static final String SELECT_FOR_NAME_QUERY =
            "SELECT " +
                    "id, name " +
                    "FROM " +
                     DB_NAME + " " +
                    "WHERE name = ?;"
            ;

    private ValidationDao() {
    }

    @Override
    public boolean isExistGroup(String groupName) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOR_NAME_QUERY)){

            statement.setString(1, groupName);

            return statement.executeQuery().next();

        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME);
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
