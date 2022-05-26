package org.it_academy.MK_JD2_90_22.json2.group.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFactory implements AutoCloseable{

    private static final DataSource instance;

    static {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Проверь имя драйвера!!!!", e);
        }

        pool.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        pool.setUser("postgres");
        pool.setPassword("172143");

        instance = pool;
    }

    private DataSourceFactory() {
    }

    public Connection getConnection() throws SQLException {
        return instance.getConnection();
    }

    public static DataSource getInstance() {
        return instance;
    }

    @Override
    public void close() throws Exception {
        ((ComboPooledDataSource) instance).close();
    }
}
