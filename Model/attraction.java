package Model;

public class Attraction {
    private int idAttraction;
    private String nomAttraction;
    private String descriptionAttraction;
    private float prixAttraction;
    private String cheminImageAttraction; // Utiliser le nom correct de la colonne

    // Constructeurs
    public Attraction() {}

    public Attraction(String nomAttraction, String descriptionAttraction, float prixAttraction, String cheminImageAttraction) {
        this.nomAttraction = nomAttraction;
        this.descriptionAttraction = descriptionAttraction;
        this.prixAttraction = prixAttraction;
        this.cheminImageAttraction = cheminImageAttraction;
    }

    // Getters et Setters
    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public String getNomAttraction() {
        return nomAttraction;
    }

    public void setNomAttraction(String nomAttraction) {
        this.nomAttraction = nomAttraction;
    }

    public String getDescriptionAttraction() {
        return descriptionAttraction;
    }

    public void setDescriptionAttraction(String descriptionAttraction) {
        this.descriptionAttraction = descriptionAttraction;
    }

    public float getPrixAttraction() {
        return prixAttraction;
    }

    public void setPrixAttraction(float prixAttraction) {
        this.prixAttraction = prixAttraction;
    }

    public String getCheminImageAttraction() {
        return cheminImageAttraction;
    }

    public void setCheminImageAttraction(String cheminImageAttraction) {
        this.cheminImageAttraction = cheminImageAttraction;
    }

    @Override
    public String toString() {
        return nomAttraction;
    }
}
