package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupRefresh;
import org.it_academy.MK_JD2_90_22.json.exceptions.dao.GroupDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IDao, ICRUDGroupDao {

    private static final String DB_NAME = "courses.groups";

    private static final String INSERT_QUERY =
            "INSERT INTO " +
                DB_NAME + "\n" +
                "\t(name)\n" +
            "\tVALUES " +
                "(?);"
            ;

    private static final String SELECT_QUERY =
            "SELECT " +
                "id, name " +
            "FROM " +
                DB_NAME + ";"
            ;

    private static final String SELECT_WHERE_QUERY =
            "SELECT " +
                "id, name " +
            "FROM " +
                DB_NAME + " " +
            "WHERE " +
                "id = ?;"
            ;

    private static final String UPDATE_QUERY =
            "UPDATE " +
                DB_NAME + " " +
                "SET " +
                    "name = ? " +
                "WHERE " +
                    "name = ?;"
            ;

    private static final String DELETE_QUERY =
            "DELETE FROM " +
                DB_NAME + " " +
            "WHERE " +
                "name = ?;";

    private static final GroupDao instance = new GroupDao();

    private GroupDao() {
    }

    @Override
    public void save(GroupName groupRefresh) {

        try {
            execute(INSERT_QUERY, groupRefresh.getName());

        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public List<Group> getAll() {

        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {

            try(ResultSet rs = preparedStatement.executeQuery()) {
                return map(rs);
            }

        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public Group get(long id) {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_WHERE_QUERY)) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return map(rs).get(0);
            }

        } catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public void update(GroupRefresh groupRefresh) {
        try {
            execute(UPDATE_QUERY, groupRefresh.getNewName(), groupRefresh.getOldName());

        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME, e);
        }
    }

    @Override
    public void delete(GroupName groupRefresh) {
        try {
            execute(DELETE_QUERY, groupRefresh.getName());
        }catch (SQLException e) {
            throw new GroupDaoException("Сбой при работе с базой " + DB_NAME, e);
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

    public static GroupDao getInstance() {
        return instance;
    }
}