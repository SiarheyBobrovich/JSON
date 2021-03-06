package org.it_academy.MK_JD2_90_22.json2.dao;

import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json2.dao.utils.GroupQueryContainer;
import org.it_academy.MK_JD2_90_22.json2.exceptions.GroupDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IDao, ICRUDGroupDao {

    private static final GroupDao instance = new GroupDao();

    private GroupDao() {
    }

    @Override
    public long save(Group group) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GroupQueryContainer.INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, group.getName());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    return generatedKeys.getLong("id");
                }
            }

        }catch (SQLException e) {
            throw new GroupDaoException(500, "Save failed ", e);
        }

        throw new GroupDaoException(500, "Save failed");
    }

    @Override
    public void delete(Group group) {
        try {
            execute(GroupQueryContainer.deleteCrossQuery + GroupQueryContainer.deleteQuery, group.getId(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(500, "Delete failed ", e);
        }
    }

    @Override
    public List<Group> getAll() {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(GroupQueryContainer.SELECT_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Group> groups = new ArrayList<>();

                while (resultSet.next()) {
                    groups.add(map(resultSet));
                }

                return groups;
            }

        }catch (SQLException e) {
            throw new GroupDaoException(500, "Select failed", e);
        }
    }

    @Override
    public Group get(long id) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GroupQueryContainer.SELECT_WHERE_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return map(resultSet);
                }

                throw new GroupDaoException(404, "404 Not Found");
            }

        }catch (SQLException e) {
            throw new GroupDaoException(500, "Get failed", e);
        }
    }

    @Override
    public void update(Group group) {
        try {
            execute(GroupQueryContainer.UPDATE_QUERY, group.getName(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(500, "Update failed", e);
        }
    }

    private Group map(ResultSet resultSet) throws SQLException {
        Group group = new Group();

        group.setId(resultSet.getLong("id"));
        group.setName(resultSet.getString("name"));

        return group;
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
