package DAO;

import Model.Reduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReductionDAO {

    private DaoFactory daoFactory;

    public ReductionDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterReduction(Reduction reduction) throws SQLException {
        String query = "INSERT INTO Reduction (nom_reduction, pourcentage_reduction, type_reduction) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reduction.getNomReduction());
            preparedStatement.setString(2, reduction.getPourcentageReduction());
            preparedStatement.setInt(3, reduction.getTypeReduction());
            preparedStatement.executeUpdate();
        }
    }

    public List<Reduction> obtenirToutesReductions() throws SQLException {
        List<Reduction> reductions = new ArrayList<>();
        String query = "SELECT * FROM Reduction";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Reduction reduction = new Reduction();
                reduction.setIdReduction(resultSet.getInt("ID_reduction"));
                reduction.setNomReduction(resultSet.getString("nom_reduction"));
                reduction.setPourcentageReduction(resultSet.getString("pourcentage_reduction"));
                reduction.setTypeReduction(resultSet.getInt("type_reduction"));
                reductions.add(reduction);
            }
        }
        return reductions;
    }

    public void mettreAJourReduction(Reduction reduction) throws SQLException {
        String query = "UPDATE Reduction SET nom_reduction = ?, pourcentage_reduction = ?, type_reduction = ? WHERE ID_reduction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reduction.getNomReduction());
            preparedStatement.setString(2, reduction.getPourcentageReduction());
            preparedStatement.setInt(3, reduction.getTypeReduction());
            preparedStatement.setInt(4, reduction.getIdReduction());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerReduction(int idReduction) throws SQLException {
        String query = "DELETE FROM Reduction WHERE ID_reduction = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idReduction);
            preparedStatement.executeUpdate();
        }
    }
}
