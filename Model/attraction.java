package Model;

public class Attraction {
    private int idAttraction;
    private String nomAttraction;
    private String descriptionAttraction;
    private float prixAttraction;
    private String imagePath;

    // Constructeurs
    public Attraction() {}

    public Attraction(String nomAttraction, String descriptionAttraction, float prixAttraction) {
        this.nomAttraction = nomAttraction;
        this.descriptionAttraction = descriptionAttraction;
        this.prixAttraction = prixAttraction;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return nomAttraction;
    }
}
