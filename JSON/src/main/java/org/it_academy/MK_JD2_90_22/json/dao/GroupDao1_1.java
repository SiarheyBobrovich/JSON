package org.it_academy.MK_JD2_90_22.json.dao;

import org.it_academy.MK_JD2_90_22.json.dao.api.ICRUDGroupDao1_1;
import org.it_academy.MK_JD2_90_22.json.dao.api.IDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json.dao.entity.api.IGroup;
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

    private static final GroupDao1_1 instance = new GroupDao1_1();

    private final String error = "Сбой при работе с базой";
    
    private final String schemaTableName = "courses.groups";
    private final String schemaCrossTableName = "courses.students_in_groups";

    private final String insertQuery =
            "INSERT INTO " +
                this.schemaTableName + " " +
                "(name) " +
            "VALUES " +
                "(?);"
            ;

    private final String selectQuery =
            "SELECT " +
                "id, name " +
            "FROM " +
                this.schemaTableName + ";"
            ;

    private final String selectWhereQuery =
            "SELECT " +
                "id, name " +
            "FROM " +
                    this.schemaTableName + " " +
            "WHERE " +
                "id = ?;"
            ;

    private final String updateQuery =
            "UPDATE " +
                this.schemaTableName + " " +
            "SET " +
                "name = ? " +
            "WHERE " +
                "id = ?;"
            ;

    private final String deleteQuery =
            "DELETE FROM " +
                this.schemaTableName + " " +
            "WHERE " +
                "id = ?\n;";

    private final String deleteCrossQuery =
            "DELETE FROM " +
                this.schemaCrossTableName + " " +
            "WHERE " +
                "group_id = ?;";


    private GroupDao1_1() {
    }

    @Override
    public void save(IGroup group) {

        try {
            execute(insertQuery, group.getName());

        }catch (SQLException e) {
            throw new GroupDaoException(error + " " + this.schemaTableName, e);
        }
    }

    @Override
    public List<Group> getAll() {

        try(Connection connection =
                    DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectQuery)) {

            try(ResultSet rs = preparedStatement.executeQuery()) {
                return map(rs);
            }

        }catch (SQLException e) {
            throw new GroupDaoException(error + " " + this.schemaTableName, e);
        }
    }

    @Override
    public Group get(long id) {
        try(Connection connection =
                    DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectWhereQuery)) {

            preparedStatement.setLong(1, id);


            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Group> map = map(rs);

                if (map.isEmpty()) {
                    throw new GroupIllegalStateException("Такой группы не существует");
                }

                return map.get(0);
            }

        } catch (SQLException e) {
            throw new GroupDaoException(error + " " + this.schemaTableName, e);
        }
    }

    @Override
    public void update(IGroupUpdate group) {
        try {
            execute(updateQuery, group.getNewName(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(error + " " + this.schemaTableName, e);
        }
    }

    @Override
    public void delete(IGroup group) {
        try {
            execute(deleteQuery + deleteCrossQuery, group.getId(), group.getId());

        }catch (SQLException e) {
            throw new GroupDaoException(error + " " + this.schemaTableName, e);
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