package Model;

import java.util.Date;

/**
 * Classe représentant une réservation d'attraction dans le système.
 * Contient les informations relatives à une réservation : ID, dates, nombre de personnes, client et attraction associés, ainsi que l'état de paiement.
 */
public class Reservation {

    // Attributs
    private int ID_reservation;
    private Date date_reservation;
    private Date date_visite;
    private int nb_adulte;
    private int nb_senior;
    private int nb_enfant;
    private int ID_client;
    private int ID_attraction;
    private boolean paye_reservation;

    /**
     * Constructeur par défaut.
     * Crée une instance de réservation sans valeur initiale.
     */
    public Reservation() {
    }

    /**
     * Constructeur pour initialiser une réservation avec les valeurs passées en paramètres.
     * @param ID_reservation L'identifiant de la réservation
     * @param date_reservation La date de la réservation
     * @param date_visite La date de la visite
     * @param nb_adulte Le nombre d'adultes
     * @param nb_senior Le nombre de seniors
     * @param nb_enfant Le nombre d'enfants
     * @param ID_client L'identifiant du client ayant effectué la réservation
     * @param ID_attraction L'identifiant de l'attraction réservée
     * @param paye_reservation L'état du paiement de la réservation (true si payé, false sinon)
     */
    public Reservation(int ID_reservation, Date date_reservation, Date date_visite, int nb_adulte, int nb_senior, int nb_enfant, int ID_client, int ID_attraction, boolean paye_reservation) {
        this.ID_reservation = ID_reservation;
        this.date_reservation = date_reservation;
        this.date_visite = date_visite;
        this.nb_adulte = nb_adulte;
        this.nb_senior = nb_senior;
        this.nb_enfant = nb_enfant;
        this.ID_client = ID_client;
        this.ID_attraction = ID_attraction;
        this.paye_reservation = paye_reservation;
    }

    // Getters et Setters

    /**
     * Retourne l'identifiant de la réservation.
     * @return L'identifiant de la réservation
     */
    public int getID_reservation() {
        return ID_reservation;
    }

    /**
     * Définit l'identifiant de la réservation.
     * @param ID_reservation L'identifiant de la réservation
     */
    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    /**
     * Retourne la date de la réservation.
     * @return La date de la réservation
     */
    public Date getDate_reservation() {
        return date_reservation;
    }

    /**
     * Définit la date de la réservation.
     * @param date_reservation La date de la réservation
     */
    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    /**
     * Retourne la date de la visite.
     * @return La date de la visite
     */
    public Date getDate_visite() {
        return date_visite;
    }

    /**
     * Définit la date de la visite.
     * @param date_visite La date de la visite
     */
    public void setDate_visite(Date date_visite) {
        this.date_visite = date_visite;
    }

    /**
     * Retourne le nombre d'adultes pour cette réservation.
     * @return Le nombre d'adultes
     */
    public int getNb_adulte() {
        return nb_adulte;
    }

    /**
     * Définit le nombre d'adultes pour cette réservation.
     * @param nb_adulte Le nombre d'adultes
     */
    public void setNb_adulte(int nb_adulte) {
        this.nb_adulte = nb_adulte;
    }

    /**
     * Retourne le nombre de seniors pour cette réservation.
     * @return Le nombre de seniors
     */
    public int getNb_senior() {
        return nb_senior;
    }

    /**
     * Définit le nombre de seniors pour cette réservation.
     * @param nb_senior Le nombre de seniors
     */
    public void setNb_senior(int nb_senior) {
        this.nb_senior = nb_senior;
    }

    /**
     * Retourne le nombre d'enfants pour cette réservation.
     * @return Le nombre d'enfants
     */
    public int getNb_enfant() {
        return nb_enfant;
    }

    /**
     * Définit le nombre d'enfants pour cette réservation.
     * @param nb_enfant Le nombre d'enfants
     */
    public void setNb_enfant(int nb_enfant) {
        this.nb_enfant = nb_enfant;
    }

    /**
     * Retourne l'identifiant du client associé à cette réservation.
     * @return L'identifiant du client
     */
    public int getID_client() {
        return ID_client;
    }

    /**
     * Définit l'identifiant du client associé à cette réservation.
     * @param ID_client L'identifiant du client
     */
    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    /**
     * Retourne l'identifiant de l'attraction réservée.
     * @return L'identifiant de l'attraction
     */
    public int getID_attraction() {
        return ID_attraction;
    }

    /**
     * Définit l'identifiant de l'attraction réservée.
     * @param ID_attraction L'identifiant de l'attraction
     */
    public void setID_attraction(int ID_attraction) {
        this.ID_attraction = ID_attraction;
    }

    /**
     * Retourne l'état du paiement de la réservation.
     * @return True si la réservation est payée, sinon false
     */
    public boolean isPaye_reservation() {
        return paye_reservation;
    }

    /**
     * Définit l'état du paiement de la réservation.
     * @param paye_reservation L'état du paiement (true pour payé, false sinon)
     */
    public void setPaye_reservation(boolean paye_reservation) {
        this.paye_reservation = paye_reservation;
    }

    /**
     * Retourne une chaîne de caractères représentant les informations de la réservation.
     * Cette méthode retourne une représentation sous forme de chaîne de la réservation.
     * @return La représentation sous forme de chaîne de la réservation
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "ID_reservation=" + ID_reservation +
                ", date_reservation=" + date_reservation +
                ", date_visite=" + date_visite +
                ", nb_adulte=" + nb_adulte +
                ", nb_senior=" + nb_senior +
                ", nb_enfant=" + nb_enfant +
                ", ID_client=" + ID_client +
                ", ID_attraction=" + ID_attraction +
                ", paye_reservation=" + paye_reservation +
                '}';
    }
}
