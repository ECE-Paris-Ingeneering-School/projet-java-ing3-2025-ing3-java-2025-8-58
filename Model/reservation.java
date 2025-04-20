package Model;

import java.sql.Date;

public class Reservation {
    private int idReservation;
    private Date dateReservation;
    private int idClient;
    private int idAttraction;
    private int idClientReserve;
    private String nomBillet;
    private String prenomBillet;
    private Boolean payeBillet;
    // Constructeurs
    public Reservation() {}

    public Reservation(Date dateReservation, int idClient, int idAttraction, int idClientReserve, String nomBillet, String prenomBillet, Boolean payeBillet) {
        this.dateReservation = dateReservation;
        this.idClient = idClient;
        this.idAttraction = idAttraction;
        this.idClientReserve = idClientReserve;
        this.nomBillet = nomBillet;
        this.prenomBillet = prenomBillet;
        this.payeBillet = payeBillet;
    }

    // Getters et Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public int getIdClientReserve() {
        return idClientReserve;
    }

    public void setIdClientReserve(int idClientReserve) {
        this.idClientReserve = idClientReserve;
    }

    public String getNomBillet() {
        return nomBillet;
    }

    public void setNomBillet(String nomBillet) {
        this.nomBillet = nomBillet;
    }

    public String getPrenomBillet() {
        return prenomBillet;
    }

    public void setPrenomBillet(String prenomBillet) {
        this.prenomBillet = prenomBillet;
    }

    public Boolean getPayeBillet() {
        return payeBillet;
    }

    public void setPayeBillet(Boolean payeBillet) {
        this.payeBillet = payeBillet;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", dateReservation=" + dateReservation +
                ", idClient=" + idClient +
                ", idAttraction=" + idAttraction +
                ", idClientReserve=" + idClientReserve +
                ", nomBillet='" + nomBillet + '\'' +
                ", prenomBillet='" + prenomBillet + '\'' +
                ", payeBillet='" + payeBillet + '\'' +
                '}';
    }
}
