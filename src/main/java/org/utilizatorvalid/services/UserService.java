package org.utilizatorvalid.services;

import org.utilizatorvalid.DAO.LoginDAO;
import org.utilizatorvalid.DAO.LoginDAOImpl;
import org.utilizatorvalid.entities.User;

import java.sql.SQLException;

public class UserService {

    private static UserService INSTANCE;
    public synchronized static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    private LoginDAO loginDAO;

    private UserService() {
        this.loginDAO = new LoginDAOImpl();
    }

    public boolean validate(User user){
        try {
            return this.loginDAO.validate(user);
//            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
