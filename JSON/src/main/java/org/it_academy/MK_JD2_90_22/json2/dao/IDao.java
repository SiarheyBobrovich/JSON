package org.it_academy.MK_JD2_90_22.json2.dao;

import org.it_academy.MK_JD2_90_22.json.dao.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IDao {


    static void execute(String query, Object... params) throws SQLException {
        try(Connection connection = DataSourceFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.execute();
        }
    }

}
