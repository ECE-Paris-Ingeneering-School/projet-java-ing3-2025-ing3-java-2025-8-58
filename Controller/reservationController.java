package Controller;

import DAO.ReservationDAO;
import DAO.DaoFactory;
import Model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationController {

    private ReservationDAO reservationDAO;

    public ReservationController(DaoFactory daoFactory) throws SQLException {
        this.reservationDAO = new ReservationDAO(daoFactory);
    }

    // Ajouter une nouvelle réservation
    public void ajouterReservation(Reservation reservation) throws SQLException {
        reservationDAO.ajouterReservation(reservation);
    }

    // Récupérer toutes les réservations
    public List<Reservation> obtenirToutesReservations() throws SQLException {
        return reservationDAO.obtenirToutesReservations();
    }

    // Mettre à jour une réservation
    public void mettreAJourReservation(Reservation reservation) throws SQLException {
        reservationDAO.mettreAJourReservation(reservation);
    }

    // Supprimer une réservation
    public void supprimerReservation(int idReservation) throws SQLException {
        reservationDAO.supprimerReservation(idReservation);
    }
}
