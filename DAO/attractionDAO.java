package DAO;

import Model.Attraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttractionDAO {

    private DaoFactory daoFactory;

    /**
     * Constructeur pour initialiser AttractionDAO avec une instance de DaoFactory
     * @param daoFactory Instance de DaoFactory pour obtenir la connexion
     */
    public AttractionDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Ajoute une nouvelle attraction dans la base de données
     * @param attraction L'attraction à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterAttraction(Attraction attraction) throws SQLException {
        String query = "INSERT INTO Attraction (nom_attraction, description_attraction, prix_attraction, chemin_image_attraction) VALUES (?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attraction.getNomAttraction());
            preparedStatement.setString(2, attraction.getDescriptionAttraction());
            preparedStatement.setFloat(3, attraction.getPrixAttraction());
            preparedStatement.setString(4, attraction.getCheminImageAttraction());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Récupère toutes les attractions depuis la base de données
     * @return Liste des attractions
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Attraction> obtenirToutesAttractions() throws SQLException {
        List<Attraction> attractions = new ArrayList<>();
        String query = "SELECT * FROM Attraction";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Attraction attraction = new Attraction();
                attraction.setIdAttraction(resultSet.getInt("ID_attraction"));
                attraction.setNomAttraction(resultSet.getString("nom_attraction"));
                attraction.setDescriptionAttraction(resultSet.getString("description_attraction"));
                attraction.setPrixAttraction(resultSet.getFloat("prix_attraction"));
                attraction.setCheminImageAttraction(resultSet.getString("chemin_image_attraction"));
                attractions.add(attraction);
            }
        }
        return attractions;
    }

    /**
     * Met à jour une attraction existante dans la base de données
     * @param attraction L'attraction à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourAttraction(Attraction attraction) throws SQLException {
        String query = "UPDATE Attraction SET nom_attraction = ?, description_attraction = ?, prix_attraction = ?, chemin_image_attraction = ? WHERE ID_attraction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attraction.getNomAttraction());
            preparedStatement.setString(2, attraction.getDescriptionAttraction());
            preparedStatement.setFloat(3, attraction.getPrixAttraction());
            preparedStatement.setString(4, attraction.getCheminImageAttraction());
            preparedStatement.setInt(5, attraction.getIdAttraction());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Supprime une attraction de la base de données
     * @param idAttraction ID de l'attraction à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerAttraction(int idAttraction) throws SQLException {
        String query = "DELETE FROM Attraction WHERE ID_attraction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAttraction);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Met à jour uniquement le chemin de l'image d'une attraction
     * @param idAttraction ID de l'attraction
     * @param cheminImageAttraction Nouveau chemin de l'image
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourCheminImageAttraction(int idAttraction, String cheminImageAttraction) throws SQLException {
        String query = "UPDATE Attraction SET chemin_image_attraction=? WHERE ID_attraction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cheminImageAttraction);
            preparedStatement.setInt(2, idAttraction);
            preparedStatement.executeUpdate();
        }
    }
}
