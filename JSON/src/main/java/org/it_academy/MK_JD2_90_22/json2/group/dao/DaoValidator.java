package org.it_academy.MK_JD2_90_22.json2.group.dao;

import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IDaoValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoValidator implements IDaoValidator {

    private static final DaoValidator instance = new DaoValidator();

    private DaoValidator() {
    }

    private final String SELECT_GROUP_BY_NAME_ID = "SELECT id FROM courses.groups WHERE name = ? AND id != ?";
    private final String SELECT_GROUP_BY_NAME = "SELECT id FROM courses.groups WHERE name = ?";

    @Override
    public boolean isExistGroup(long id, String groupName) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_GROUP_BY_NAME_ID)) {

            statement.setString(1, groupName);
            statement.setLong(2, id);

            return statement.executeQuery().next();

        }catch (SQLException e) {
            throw new GroupDaoException(e.getMessage());
        }
    }

    @Override
    public boolean isExistGroup(String groupName) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_GROUP_BY_NAME)) {

            statement.setString(1, groupName);

            return statement.executeQuery().next();

        }catch (SQLException e) {
            throw new GroupDaoException(e.getMessage());
        }
    }

    public static DaoValidator getInstance() {
        return instance;
    }
}
