package org.it_academy.MK_JD2_90_22.json.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFactoryTest {

    @Test
    void getConnection() throws SQLException {
        try(Connection connection = DataSourceFactory.getInstance().getConnection()) {
            Assertions.assertFalse(connection.isClosed());
        }
    }

    @Test
    void close() throws SQLException {
        try(Connection connection = DataSourceFactory.getInstance().getConnection()) {
            connection.close();
            Assertions.assertTrue(connection.isClosed());
        }
    }
}