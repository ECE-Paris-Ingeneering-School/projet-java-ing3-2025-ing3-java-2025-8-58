package DAO;

import Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO pour gérer les opérations liées aux clients dans la base de données.
 */
public class ClientDAO {

    private DaoFactory daoFactory;

    /**
     * Constructeur pour initialiser ClientDAO avec une instance de DaoFactory
     * @param daoFactory Instance de DaoFactory pour obtenir la connexion
     */
    public ClientDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Ajoute un nouveau client dans la base de données
     * @param client Le client à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
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

    /**
     * Récupère un client par son adresse email
     * @param email L'email du client
     * @return Le client correspondant, ou null s'il n'existe pas
     * @throws SQLException en cas d'erreur SQL
     */
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

    /**
     * Récupère un client par son adresse email et son mot de passe
     * @param email L'email du client
     * @param motDePasse Le mot de passe du client
     * @return Le client correspondant, ou null si aucun ne correspond
     * @throws SQLException en cas d'erreur SQL
     */
    public Client obtenirClientParEmailEtMotDePasse(String email, String motDePasse) throws SQLException {
        String query = "SELECT * FROM Client WHERE mail_client = ? AND mdp_client = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        return null;
    }

    /**
     * Met à jour un client existant dans la base de données
     * @param client Le client à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
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

    /**
     * Supprime un client de la base de données
     * @param idClient L'ID du client à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerClient(int idClient) throws SQLException {
        String query = "DELETE FROM Client WHERE ID_client = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        }
    }
}
