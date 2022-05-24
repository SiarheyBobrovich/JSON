package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao1_1;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValidationDao1_1 implements IValidationDao1_1 {


    private static final ValidationDao1_1 instance = new ValidationDao1_1();

    private static final String SELECT_FOR_NAME_QUERY =
            "SELECT " +
                "id, name " +
            "FROM " +
                 "? " +
            "WHERE " +
                "id = ?;"
            ;

    private ValidationDao1_1() {
    }

    @Override
    public boolean isExistGroup(IGroup group) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOR_NAME_QUERY)){

            statement.setString(1, group.getDbName());
            statement.setLong(2, group.getId());

            return statement.executeQuery().next();

        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + group.getDbName());
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

    public static ValidationDao1_1 getInstance() {
        return instance;
    }
}
