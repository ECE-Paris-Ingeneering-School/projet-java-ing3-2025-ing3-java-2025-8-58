package DAO;

import Model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private DaoFactory daoFactory;

    public AdminDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO Administrateur (mail_admin, mdp_admin) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getMailAdmin());
            preparedStatement.setString(2, admin.getMdpAdmin());
            preparedStatement.executeUpdate();
        }
    }

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

    public void supprimerAdmin(int idAdmin) throws SQLException {
        String query = "DELETE FROM Administrateur WHERE ID_admin = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAdmin);
            preparedStatement.executeUpdate();
        }
    }
}
