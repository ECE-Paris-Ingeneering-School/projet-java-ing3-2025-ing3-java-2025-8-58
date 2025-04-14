package DAO;

import Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    private DaoFactory daoFactory;

    public ClientDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterClient(Client client) throws SQLException {
        String query = "INSERT INTO Client (mail_client, mdp_client, nom_client, prenom_client) VALUES (?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getMailClient());
            preparedStatement.setString(2, client.getMdpClient());
            preparedStatement.setString(3, client.getNomClient());
            preparedStatement.setString(4, client.getPrenomClient());
            preparedStatement.executeUpdate();
        }
    }

    public Client obtenirClientParEmail(String email) throws SQLException {
        String query = "SELECT * FROM Client WHERE mail_client = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setIdClient(resultSet.getInt("ID_client"));
                    client.setMailClient(resultSet.getString("mail_client"));
                    client.setMdpClient(resultSet.getString("mdp_client"));
                    client.setNomClient(resultSet.getString("nom_client"));
                    client.setPrenomClient(resultSet.getString("prenom_client"));
                    return client;
                }
            }
        }
        return null;
    }

    public void mettreAJourClient(Client client) throws SQLException {
        String query = "UPDATE Client SET mail_client = ?, mdp_client = ?, nom_client = ?, prenom_client = ? WHERE ID_client = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getMailClient());
            preparedStatement.setString(2, client.getMdpClient());
            preparedStatement.setString(3, client.getNomClient());
            preparedStatement.setString(4, client.getPrenomClient());
            preparedStatement.setInt(5, client.getIdClient());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerClient(int idClient) throws SQLException {
        String query = "DELETE FROM Client WHERE ID_client = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        }
    }
}
