package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static String url;
    private String username;
    private String password;

    public DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance(String database, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de connexion à la base de données");
        }

        url = "jdbc:mysql://localhost:8889/" + database;
        return new DaoFactory(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public AttractionDAO getAttractionDAO() {
        return new AttractionDAO(this);
    }

    public ClientDAO getClientDAO() {
        return new ClientDAO(this);
    }

    public ReductionDAO getReductionDAO() {
        return new ReductionDAO(this);
    }

    public ReservationDAO getReservationDAO() {
        return new ReservationDAO(this);
    }

    public void disconnect() {
        Connection connexion = null;
        try {
            connexion = this.getConnection();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de déconnexion à la base de données");
        }
    }
}
