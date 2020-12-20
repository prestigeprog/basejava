package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @FunctionalInterface
    public interface CustomHelper<T> {
        T doStatement(PreparedStatement ps) throws SQLException;
    }

    public void doStatement(String sql) {
        doStatement(sql, PreparedStatement::execute);
    }


    public<T> T doStatement(String statement, CustomHelper<T> helper) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            return helper.doStatement(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
