package Controller;

import DAO.ReservationDAO;
import DAO.DaoFactory;
import Model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationController {

    private ReservationDAO reservationDAO;

    public ReservationController(DaoFactory daoFactory) {
        this.reservationDAO = daoFactory.getReservationDAO();
    }

    public void ajouterReservation(Reservation reservation) throws SQLException {
        reservationDAO.ajouterReservation(reservation);
    }

    public List<Reservation> obtenirToutesReservations() throws SQLException {
        return reservationDAO.obtenirToutesReservations();
    }

    public void mettreAJourReservation(Reservation reservation) throws SQLException {
        reservationDAO.mettreAJourReservation(reservation);
    }

    public void supprimerReservation(int idReservation) throws SQLException {
        reservationDAO.supprimerReservation(idReservation);
    }
}
