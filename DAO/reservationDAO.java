package DAO;

import Model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private DaoFactory daoFactory;

    public ReservationDAO(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO Reservation (date_reservation, ID_client, ID_attraction, ID_client_Reserve, nom_billet, prenom_billet) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdClient());
            preparedStatement.setInt(3, reservation.getIdAttraction());
            preparedStatement.setInt(4, reservation.getIdClientReserve());
            preparedStatement.setString(5, reservation.getNomBillet());
            preparedStatement.setString(6, reservation.getPrenomBillet());
            preparedStatement.executeUpdate();
        }
    }

    public List<Reservation> obtenirToutesReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("ID_reservation"));
                reservation.setDateReservation(resultSet.getDate("date_reservation"));
                reservation.setIdClient(resultSet.getInt("ID_client"));
                reservation.setIdAttraction(resultSet.getInt("ID_attraction"));
                reservation.setIdClientReserve(resultSet.getInt("ID_client_Reserve"));
                reservation.setNomBillet(resultSet.getString("nom_billet"));
                reservation.setPrenomBillet(resultSet.getString("prenom_billet"));
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public void mettreAJourReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE Reservation SET date_reservation = ?, ID_client = ?, ID_attraction = ?, ID_client_Reserve = ?, nom_billet = ?, prenom_billet = ? WHERE ID_reservation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdClient());
            preparedStatement.setInt(3, reservation.getIdAttraction());
            preparedStatement.setInt(4, reservation.getIdClientReserve());
            preparedStatement.setString(5, reservation.getNomBillet());
            preparedStatement.setString(6, reservation.getPrenomBillet());
            preparedStatement.setInt(7, reservation.getIdReservation());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerReservation(int idReservation) throws SQLException {
        String query = "DELETE FROM Reservation WHERE ID_reservation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idReservation);
            preparedStatement.executeUpdate();
        }
    }
}
