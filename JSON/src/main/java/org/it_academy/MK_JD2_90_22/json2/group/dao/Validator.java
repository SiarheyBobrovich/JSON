package org.it_academy.MK_JD2_90_22.json2.group.dao;

import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IValidationDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Validator implements IValidationDao {

    private static final Validator instance = new Validator();

    private Validator() {
    }

    private final String SELECT_GROUP_BY_NAME = "SELECT id FROM courses.groups WHERE name = ? AND id != ?";

    @Override
    public boolean isExistGroup(long id, String groupName) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_GROUP_BY_NAME)) {

            statement.setString(1, groupName);
            statement.setLong(2, id);

            return statement.executeQuery().next();

        }catch (SQLException e) {
            throw new GroupDaoException(e.getMessage());
        }
    }

    public static Validator getInstance() {
        return instance;
    }
}
