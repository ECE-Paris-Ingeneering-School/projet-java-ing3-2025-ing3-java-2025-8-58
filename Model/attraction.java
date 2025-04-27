package Model;

/**
 * Classe représentant une attraction dans le système.
 * Contient les informations relatives à une attraction : identifiant, nom, description, prix, et le chemin de l'image.
 */
public class Attraction {

    private int idAttraction;
    private String nomAttraction;
    private String descriptionAttraction;
    private float prixAttraction;
    private String cheminImageAttraction; // Utiliser le nom correct de la colonne

    /**
     * Constructeur par défaut.
     * Utilisé pour créer un objet Attraction vide.
     */
    public Attraction() {}

    /**
     * Constructeur pour initialiser une attraction avec un nom, une description, un prix et un chemin d'image.
     * @param nomAttraction Le nom de l'attraction
     * @param descriptionAttraction La description de l'attraction
     * @param prixAttraction Le prix de l'attraction
     * @param cheminImageAttraction Le chemin de l'image représentant l'attraction
     */
    public Attraction(String nomAttraction, String descriptionAttraction, float prixAttraction, String cheminImageAttraction) {
        this.nomAttraction = nomAttraction;
        this.descriptionAttraction = descriptionAttraction;
        this.prixAttraction = prixAttraction;
        this.cheminImageAttraction = cheminImageAttraction;
    }

    /**
     * Retourne l'identifiant de l'attraction.
     * @return L'identifiant de l'attraction
     */
    public int getIdAttraction() {
        return idAttraction;
    }

    /**
     * Définit l'identifiant de l'attraction.
     * @param idAttraction L'identifiant à attribuer à l'attraction
     */
    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    /**
     * Retourne le nom de l'attraction.
     * @return Le nom de l'attraction
     */
    public String getNomAttraction() {
        return nomAttraction;
    }

    /**
     * Définit le nom de l'attraction.
     * @param nomAttraction Le nom à attribuer à l'attraction
     */
    public void setNomAttraction(String nomAttraction) {
        this.nomAttraction = nomAttraction;
    }

    /**
     * Retourne la description de l'attraction.
     * @return La description de l'attraction
     */
    public String getDescriptionAttraction() {
        return descriptionAttraction;
    }

    /**
     * Définit la description de l'attraction.
     * @param descriptionAttraction La description à attribuer à l'attraction
     */
    public void setDescriptionAttraction(String descriptionAttraction) {
        this.descriptionAttraction = descriptionAttraction;
    }

    /**
     * Retourne le prix de l'attraction.
     * @return Le prix de l'attraction
     */
    public float getPrixAttraction() {
        return prixAttraction;
    }

    /**
     * Définit le prix de l'attraction.
     * @param prixAttraction Le prix à attribuer à l'attraction
     */
    public void setPrixAttraction(float prixAttraction) {
        this.prixAttraction = prixAttraction;
    }

    /**
     * Retourne le chemin de l'image de l'attraction.
     * @return Le chemin de l'image de l'attraction
     */
    public String getCheminImageAttraction() {
        return cheminImageAttraction;
    }

    /**
     * Définit le chemin de l'image de l'attraction.
     * @param cheminImageAttraction Le chemin de l'image à attribuer à l'attraction
     */
    public void setCheminImageAttraction(String cheminImageAttraction) {
        this.cheminImageAttraction = cheminImageAttraction;
    }

    /**
     * Retourne une chaîne de caractères représentant l'attraction, ici le nom de l'attraction.
     * @return Le nom de l'attraction
     */
    @Override
    public String toString() {
        return nomAttraction;
    }
}
