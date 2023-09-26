package com.rest.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T, K> {
    T findById(K id);

    boolean deleteById(K id);

    List<T> findAll();

    default Long getSavedObjectId(PreparedStatement statement) throws SQLException {
        Long id = null;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
        }
        return id;
    }
}
