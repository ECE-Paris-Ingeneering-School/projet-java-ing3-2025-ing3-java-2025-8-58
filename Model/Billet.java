package Model;

import java.sql.Date;

public class Billet {
    private String nomBillet;
    private String prenomBillet;
    private Date dateBillet;
    private Date dateNaissanceBillet;
    private int idReservation;

    // Constructeur par défaut
    public Billet() {}

    // Constructeur avec paramètres
    public Billet(String nomBillet, String prenomBillet, Date dateBillet, Date dateNaissanceBillet, int idReservation) {
        this.nomBillet = nomBillet;
        this.prenomBillet = prenomBillet;
        this.dateBillet = dateBillet;
        this.dateNaissanceBillet = dateNaissanceBillet;
        this.idReservation = idReservation;
    }

    // Getters et Setters
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

    public Date getDateBillet() {
        return dateBillet;
    }

    public void setDateBillet(Date dateBillet) {
        this.dateBillet = dateBillet;
    }

    public Date getDateNaissanceBillet() {
        return dateNaissanceBillet;
    }

    public void setDateNaissanceBillet(Date dateNaissanceBillet) {
        this.dateNaissanceBillet = dateNaissanceBillet;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Override
    public String toString() {
        return "Billet{" +
                "nomBillet='" + nomBillet + '\'' +
                ", prenomBillet='" + prenomBillet + '\'' +
                ", dateBillet=" + dateBillet +
                ", dateNaissanceBillet=" + dateNaissanceBillet +
                ", idReservation=" + idReservation +
                '}';
    }
}

