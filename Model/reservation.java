package Model;

public class Reservation {
    // Attributs
    private int ID_reservation;
    private java.util.Date date_reservation;
    private java.util.Date date_visite;
    private int nb_adulte;
    private int nb_senior;
    private int nb_enfant;
    private int ID_client;
    private int ID_attraction;
    private boolean paye_reservation;

    // Constructeur par défaut
    public Reservation() {
    }

    // Constructeur avec paramètres
    public Reservation(int ID_reservation, java.util.Date date_reservation, java.util.Date date_visite, int nb_adulte, int nb_senior, int nb_enfant, int ID_client, int ID_attraction, boolean paye_reservation) {
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
    public int getID_reservation() {
        return ID_reservation;
    }

    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    public java.util.Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(java.util.Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public java.util.Date getDate_visite() {
        return date_visite;
    }

    public void setDate_visite(java.util.Date date_visite) {
        this.date_visite = date_visite;
    }

    public int getNb_adulte() {
        return nb_adulte;
    }

    public void setNb_adulte(int nb_adulte) {
        this.nb_adulte = nb_adulte;
    }

    public int getNb_senior() {
        return nb_senior;
    }

    public void setNb_senior(int nb_senior) {
        this.nb_senior = nb_senior;
    }

    public int getNb_enfant() {
        return nb_enfant;
    }

    public void setNb_enfant(int nb_enfant) {
        this.nb_enfant = nb_enfant;
    }

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    public int getID_attraction() {
        return ID_attraction;
    }

    public void setID_attraction(int ID_attraction) {
        this.ID_attraction = ID_attraction;
    }

    public boolean isPaye_reservation() {
        return paye_reservation;
    }

    public void setPaye_reservation(boolean paye_reservation) {
        this.paye_reservation = paye_reservation;
    }

    // Méthode toString pour afficher les informations de la réservation
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
