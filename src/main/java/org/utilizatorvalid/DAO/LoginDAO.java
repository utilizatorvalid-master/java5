package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.User;

import java.sql.SQLException;

public interface LoginDAO {
    boolean validate(User user) throws SQLException;
}
