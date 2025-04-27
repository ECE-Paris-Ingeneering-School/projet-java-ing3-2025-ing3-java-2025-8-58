package DAO;

import Model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private DaoFactory daoFactory;

    /**
     * Constructeur du DAO Admin
     * @param daoFactory Fabrique de DAO pour obtenir la connexion
     */
    public AdminDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Ajoute un nouvel administrateur dans la base de données
     * @param admin Administrateur à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO Administrateur (mail_admin, mdp_admin) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getMailAdmin());
            preparedStatement.setString(2, admin.getMdpAdmin());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Récupère un administrateur par son email
     * @param email Email de l'administrateur
     * @return Administrateur correspondant ou null si non trouvé
     * @throws SQLException en cas d'erreur SQL
     */
    public Admin obtenirAdminParEmail(String email) throws SQLException {
        String query = "SELECT * FROM Administrateur WHERE mail_admin = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setIdAdmin(resultSet.getInt("ID_admin"));
                    admin.setMailAdmin(resultSet.getString("mail_admin"));
                    admin.setMdpAdmin(resultSet.getString("mdp_admin"));
                    return admin;
                }
            }
        }
        return null;
    }

    /**
     * Met à jour les informations d'un administrateur
     * @param admin Administrateur avec les nouvelles informations
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourAdmin(Admin admin) throws SQLException {
        String query = "UPDATE Administrateur SET mail_admin = ?, mdp_admin = ? WHERE ID_admin = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getMailAdmin());
            preparedStatement.setString(2, admin.getMdpAdmin());
            preparedStatement.setInt(3, admin.getIdAdmin());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Supprime un administrateur par son ID
     * @param idAdmin ID de l'administrateur à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerAdmin(int idAdmin) throws SQLException {
        String query = "DELETE FROM Administrateur WHERE ID_admin = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.executeUpdate();
        }
    }
}
