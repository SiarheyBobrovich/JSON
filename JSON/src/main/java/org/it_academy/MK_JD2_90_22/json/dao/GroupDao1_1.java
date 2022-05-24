package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDGroupDao1_1;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.api.IGroupUpdate;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.group.GroupIllegalStateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao1_1 implements IDao, ICRUDGroupDao1_1 {

    private final String ERROR = "Сбой при работе с базой";

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                "? " +
                "(name) " +
            "VALUES " +
                "(?);"
            ;

    private static final String SELECT_QUERY =
            "SELECT " +
                "id, name " +
            "FROM " +
                "?;"
            ;

    private static final String SELECT_WHERE_QUERY =
            "SELECT " +
                "id, name " +
            "FROM " +
                "? " +
            "WHERE " +
                "id = ?;"
            ;

    private static final String UPDATE_QUERY =
            "UPDATE " +
                "? " +
                "SET " +
                    "name = ? " +
                "WHERE " +
                    "id = ?;"
            ;

    private static final String DELETE_QUERY =
            "DELETE FROM " +
                "? " +
            "WHERE " +
                "id = ?;";

    private static final GroupDao1_1 instance = new GroupDao1_1();

    private GroupDao1_1() {
    }

    @Override
    public void save(IGroup group) {

        try {
            execute(INSERT_QUERY, group.getDbName(), group.getName());

        }catch (SQLException e) {
            throw new GroupDaoException(ERROR + " " + group.getDbName(), e);
        }
    }

    @Override
    public List<Group> getAll() {

        try(Connection connection =
                    DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_QUERY)) {

            preparedStatement.setString(1, IGroup.DB_NAME);

            try(ResultSet rs = preparedStatement.executeQuery()) {
                return map(rs);
            }

        }catch (SQLException e) {
            throw new GroupDaoException(ERROR + " " + IGroup.DB_NAME, e);
        }
    }

    @Override
    public Group get(long id) {
        try(Connection connection =
                    DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_WHERE_QUERY)) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Group> map = map(rs);

                if (map.isEmpty()) {
                    throw new GroupIllegalStateException("Такой группы не существует");
                }

                return map.get(0);
            }

        } catch (SQLException e) {
            throw new GroupDaoException(ERROR + " " + IGroup.DB_NAME, e);
        }
    }

    @Override
    public void update(IGroup group) {
        try {
            execute(UPDATE_QUERY, group.getDbName(), group.getName(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(ERROR + " " + IGroup.DB_NAME, e);
        }
    }

    @Override
    public void delete(IGroup group) {
        try {
            execute(DELETE_QUERY, group.getDbName(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(ERROR + " " + IGroup.DB_NAME, e);
        }
    }

    private List<Group> map(ResultSet rs) throws SQLException {
        List<Group> groups = new ArrayList<>();

        while (rs.next()) {
            groups.add(new Group(
                    rs.getLong("id"),
                    rs.getString("name"))
            );
        }

        return groups;
    }

    public static GroupDao1_1 getInstance() {
        return instance;
    }
}