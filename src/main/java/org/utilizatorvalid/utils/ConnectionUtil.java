package org.utilizatorvalid.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private DataSource dataSource = null;

    private static ConnectionUtil INSTANCE;

    public Connection connection;

    private ConnectionUtil() throws SQLException, NamingException {
        Context initialContext = new InitialContext();
        Context envContex = (Context) initialContext.lookup("java:comp/env");
        this.dataSource = (DataSource) envContex.lookup("jdbc/postgres");
        this.connection = dataSource.getConnection();
        System.out.println(this.dataSource.toString());

    }

    public synchronized static ConnectionUtil getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new ConnectionUtil();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }
}
