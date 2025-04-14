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

    public AttractionDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterAttraction(Attraction attraction) throws SQLException {
        String query = "INSERT INTO Attraction (nom_attraction, description_attraction, prix_attraction) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attraction.getNomAttraction());
            preparedStatement.setString(2, attraction.getDescriptionAttraction());
            preparedStatement.setFloat(3, attraction.getPrixAttraction());
            preparedStatement.executeUpdate();
        }
    }

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
                attractions.add(attraction);
            }
        }
        return attractions;
    }

    public void mettreAJourAttraction(Attraction attraction) throws SQLException {
        String query = "UPDATE Attraction SET nom_attraction = ?, description_attraction = ?, prix_attraction = ? WHERE ID_attraction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attraction.getNomAttraction());
            preparedStatement.setString(2, attraction.getDescriptionAttraction());
            preparedStatement.setFloat(3, attraction.getPrixAttraction());
            preparedStatement.setInt(4, attraction.getIdAttraction());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerAttraction(int idAttraction) throws SQLException {
        String query = "DELETE FROM Attraction WHERE ID_attraction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAttraction);
            preparedStatement.executeUpdate();
        }
    }
}
