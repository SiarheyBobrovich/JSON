package org.it_academy.MK_JD2_90_22.json2.group.dao;

import org.it_academy.MK_JD2_90_22.json.dao.DataSourceFactory;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.utils.QueryContainer;
import org.it_academy.MK_JD2_90_22.json2.group.exceptions.GroupDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IDao, ICRUDGroupDao {

    private static final GroupDao instance = new GroupDao();

    private GroupDao() {
    }

    @Override
    public void save(Group group) {
        try {
            execute(QueryContainer.INSERT_QUERY, group.getName());

        }catch (SQLException e) {
            throw new GroupDaoException("Insert failed ", e);
        }
    }

    @Override
    public void delete(Group group) {
        try {
            execute(QueryContainer.deleteQuery + QueryContainer.deleteCrossQuery, group.getId(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException("Insert failed ", e);
        }
    }

    @Override
    public List<Group> getAll() {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Group> groups = new ArrayList<>();

                while (resultSet.next()) {
                    groups.add(map(resultSet));
                }

                return groups;
            }

        }catch (SQLException e) {
            throw new GroupDaoException("Select failed", e);
        }
    }

    @Override
    public Group get(long id) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(QueryContainer.SELECT_WHERE_QUERY)) {

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return map(resultSet);
                }

                throw new GroupDaoException("404 Not Found");
            }

        }catch (SQLException e) {
            throw new GroupDaoException("Get failed", e);
        }
    }

    @Override
    public void update(Group group) {
        try {
            execute(QueryContainer.UPDATE_QUERY, group.getName(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException("Update failed", e);
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
