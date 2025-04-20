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
        String query = "INSERT INTO Reservation (date_reservation, date_visite, nb_adulte, nb_senior, nb_enfant, ID_client, ID_attraction, paye_reservation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(reservation.getDate_reservation().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(reservation.getDate_visite().getTime()));
            preparedStatement.setInt(3, reservation.getNb_adulte());
            preparedStatement.setInt(4, reservation.getNb_senior());
            preparedStatement.setInt(5, reservation.getNb_enfant());
            preparedStatement.setInt(6, reservation.getID_client());
            preparedStatement.setInt(7, reservation.getID_attraction());
            preparedStatement.setBoolean(8, reservation.isPaye_reservation());
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
                reservation.setID_reservation(resultSet.getInt("ID_reservation"));
                reservation.setDate_reservation(resultSet.getDate("date_reservation"));
                reservation.setDate_visite(resultSet.getDate("date_visite"));
                reservation.setNb_adulte(resultSet.getInt("nb_adulte"));
                reservation.setNb_senior(resultSet.getInt("nb_senior"));
                reservation.setNb_enfant(resultSet.getInt("nb_enfant"));
                reservation.setID_client(resultSet.getInt("ID_client"));
                reservation.setID_attraction(resultSet.getInt("ID_attraction"));
                reservation.setPaye_reservation(resultSet.getBoolean("paye_reservation"));
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public List<Reservation> obtenirReservationsParDate(java.sql.Date dateReservation) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation WHERE date_reservation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, dateReservation);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setID_reservation(resultSet.getInt("ID_reservation"));
                    reservation.setDate_reservation(resultSet.getDate("date_reservation"));
                    reservation.setDate_visite(resultSet.getDate("date_visite"));
                    reservation.setNb_adulte(resultSet.getInt("nb_adulte"));
                    reservation.setNb_senior(resultSet.getInt("nb_senior"));
                    reservation.setNb_enfant(resultSet.getInt("nb_enfant"));
                    reservation.setID_client(resultSet.getInt("ID_client"));
                    reservation.setID_attraction(resultSet.getInt("ID_attraction"));
                    reservation.setPaye_reservation(resultSet.getBoolean("paye_reservation"));
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public void mettreAJourReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE Reservation SET date_reservation = ?, date_visite = ?, nb_adulte = ?, nb_senior = ?, nb_enfant = ?, ID_client = ?, ID_attraction = ?, paye_reservation = ? WHERE ID_reservation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(reservation.getDate_reservation().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(reservation.getDate_visite().getTime()));
            preparedStatement.setInt(3, reservation.getNb_adulte());
            preparedStatement.setInt(4, reservation.getNb_senior());
            preparedStatement.setInt(5, reservation.getNb_enfant());
            preparedStatement.setInt(6, reservation.getID_client());
            preparedStatement.setInt(7, reservation.getID_attraction());
            preparedStatement.setBoolean(8, reservation.isPaye_reservation());
            preparedStatement.setInt(9, reservation.getID_reservation());
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