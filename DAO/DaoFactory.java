package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory instance;
    private Connection connection;

    private DaoFactory(String databaseName, String user, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static DaoFactory getInstance(String databaseName, String user, String password) throws SQLException {
        if (instance == null) {
            instance = new DaoFactory(databaseName, user, password);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
