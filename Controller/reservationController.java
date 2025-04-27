package Controller;

import DAO.ReservationDAO;
import DAO.DaoFactory;
import Model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationController {

    private ReservationDAO reservationDAO;

    /**
     * Constructeur du contrôleur de réservation
     * Initialise le DAO pour les réservations
     * @param daoFactory Fabrique de DAO
     */
    public ReservationController(DaoFactory daoFactory) {
        this.reservationDAO = daoFactory.getReservationDAO();
    }

    /**
     * Ajoute une nouvelle réservation
     * @param reservation Réservation à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterReservation(Reservation reservation) throws SQLException {
        reservationDAO.ajouterReservation(reservation);
    }

    /**
     * Récupère toutes les réservations
     * @return Liste des réservations
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Reservation> obtenirToutesReservations() throws SQLException {
        return reservationDAO.obtenirToutesReservations();
    }

    /**
     * Récupère les réservations effectuées à une date spécifique
     * @param dateReservation Date de la réservation
     * @return Liste des réservations correspondantes
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Reservation> obtenirReservationsParDate(java.sql.Date dateReservation) throws SQLException {
        return reservationDAO.obtenirReservationsParDate(dateReservation);
    }

    /**
     * Récupère les réservations d'un client spécifique
     * @param idClient ID du client
     * @return Liste des réservations du client
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Reservation> obtenirReservationsParClient(int idClient) throws SQLException {
        return reservationDAO.obtenirReservationsParClient(idClient);
    }

    /**
     * Récupère les réservations pour une date et un client spécifiques
     * @param dateReservation Date de la réservation
     * @param idClient ID du client
     * @return Liste des réservations correspondantes
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Reservation> obtenirReservationsParDateetClient(java.sql.Date dateReservation, int idClient) throws SQLException {
        return reservationDAO.obtenirReservationsParDateEtClient(dateReservation, idClient);
    }

    /**
     * Met à jour une réservation existante
     * @param reservation Réservation à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourReservation(Reservation reservation) throws SQLException {
        reservationDAO.mettreAJourReservation(reservation);
    }

    /**
     * Supprime une réservation
     * @param idReservation ID de la réservation à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerReservation(int idReservation) throws SQLException {
        reservationDAO.supprimerReservation(idReservation);
    }
}
