package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fabrique (Factory) pour créer les instances DAO et gérer la connexion à la base de données.
 */
public class DaoFactory {
    private static String url;
    private String username;
    private String password;

    /**
     * Constructeur de DaoFactory
     * @param url L'URL de la base de données
     * @param username Le nom d'utilisateur de la base de données
     * @param password Le mot de passe de la base de données
     */
    public DaoFactory(String url, String username, String password) {
        DaoFactory.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Obtient une instance de DaoFactory avec les paramètres fournis
     * @param database Le nom de la base de données
     * @param username Le nom d'utilisateur
     * @param password Le mot de passe
     * @return Une instance de DaoFactory
     */
    public static DaoFactory getInstance(String database, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de connexion à la base de données");
        }

        url = "jdbc:mysql://localhost:3306/" + database;
        return new DaoFactory(url, username, password);
    }

    /**
     * Ouvre une connexion à la base de données
     * @return Une connexion SQL active
     * @throws SQLException en cas d'erreur SQL
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Retourne une instance de AdminDAO
     * @return Un objet AdminDAO
     */
    public AdminDAO getAdminDAO() {
        return new AdminDAO(this);
    }

    /**
     * Retourne une instance de AttractionDAO
     * @return Un objet AttractionDAO
     */
    public AttractionDAO getAttractionDAO() {
        return new AttractionDAO(this);
    }

    /**
     * Retourne une instance de ClientDAO
     * @return Un objet ClientDAO
     */
    public ClientDAO getClientDAO() {
        return new ClientDAO(this);
    }

    /**
     * Retourne une instance de ReductionDAO
     * @return Un objet ReductionDAO
     */
    public ReductionDAO getReductionDAO() {
        return new ReductionDAO(this);
    }

    /**
     * Retourne une instance de ReservationDAO
     * @return Un objet ReservationDAO
     */
    public ReservationDAO getReservationDAO() {
        return new ReservationDAO(this);
    }

    /**
     * Ferme la connexion à la base de données
     */
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
