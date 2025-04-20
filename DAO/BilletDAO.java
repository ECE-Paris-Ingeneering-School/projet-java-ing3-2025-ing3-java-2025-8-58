package DAO;

import Model.Billet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BilletDAO {

    private DaoFactory daoFactory;

    public BilletDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterBillet(Billet billet) throws SQLException {
        String query = "INSERT INTO Billet (nom_billet, prenom_billet, date_billet, date_naissance_billet, ID_reservation) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, billet.getNomBillet());
            preparedStatement.setString(2, billet.getPrenomBillet());
            preparedStatement.setDate(3, billet.getDateBillet());
            preparedStatement.setDate(4, billet.getDateNaissanceBillet());
            preparedStatement.setInt(5, billet.getIdReservation());
            preparedStatement.executeUpdate();
        }
    }

    public List<Billet> obtenirTousLesBillets() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM Billet";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Billet billet = new Billet();
                billet.setNomBillet(resultSet.getString("nom_billet"));
                billet.setPrenomBillet(resultSet.getString("prenom_billet"));
                billet.setDateBillet(resultSet.getDate("date_billet"));
                billet.setDateNaissanceBillet(resultSet.getDate("date_naissance_billet"));
                billet.setIdReservation(resultSet.getInt("ID_reservation"));
                billets.add(billet);
            }
        }
        return billets;
    }

    public void mettreAJourBillet(Billet billet) throws SQLException {
        String query = "UPDATE Billet SET date_billet = ?, date_naissance_billet = ?, ID_reservation = ? WHERE nom_billet = ? AND prenom_billet = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, billet.getDateBillet());
            preparedStatement.setDate(2, billet.getDateNaissanceBillet());
            preparedStatement.setInt(3, billet.getIdReservation());
            preparedStatement.setString(4, billet.getNomBillet());
            preparedStatement.setString(5, billet.getPrenomBillet());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerBillet(String nomBillet, String prenomBillet) throws SQLException {
        String query = "DELETE FROM Billet WHERE nom_billet = ? AND prenom_billet = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nomBillet);
            preparedStatement.setString(2, prenomBillet);
            preparedStatement.executeUpdate();
        }
    }

    public List<Billet> obtenirBilletsParReservation(int idReservation) throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM Billet WHERE ID_reservation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idReservation);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Billet billet = new Billet();
                    billet.setNomBillet(resultSet.getString("nom_billet"));
                    billet.setPrenomBillet(resultSet.getString("prenom_billet"));
                    billet.setDateBillet(resultSet.getDate("date_billet"));
                    billet.setDateNaissanceBillet(resultSet.getDate("date_naissance_billet"));
                    billet.setIdReservation(resultSet.getInt("ID_reservation"));
                    billets.add(billet);
                }
            }
        }
        return billets;
    }
}
