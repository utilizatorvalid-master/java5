package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.User;
import org.utilizatorvalid.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    private Connection connection;
    private final static String GET_USER = "SELECT uname, password from USERS WHERE uname = ? and password = ?";

    public LoginDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }

    @Override
    public boolean validate(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() == false) {
            return false;
        }
        User user1 = new User();
        user1.setName(resultSet.getString("uname"));
        user1.setPassword(resultSet.getString("password"));
        return user.equals(user1);
    }
}
