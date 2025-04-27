package Model;

/**
 * Classe représentant une réduction dans le système.
 * Contient les informations relatives à une réduction : identifiant, nom, pourcentage et type de réduction.
 */
public class Reduction {

    private int idReduction;
    private String nomReduction;
    private String pourcentageReduction;
    private int typeReduction;

    /**
     * Constructeur par défaut.
     * Utilisé pour créer une réduction vide.
     */
    public Reduction() {}

    /**
     * Constructeur pour initialiser une réduction avec un nom, un pourcentage et un type.
     * @param nomReduction Le nom de la réduction
     * @param pourcentageReduction Le pourcentage de la réduction
     * @param typeReduction Le type de la réduction (par exemple, "percent" ou "fixed")
     */
    public Reduction(String nomReduction, String pourcentageReduction, int typeReduction) {
        this.nomReduction = nomReduction;
        this.pourcentageReduction = pourcentageReduction;
        this.typeReduction = typeReduction;
    }

    /**
     * Retourne l'identifiant de la réduction.
     * @return L'identifiant de la réduction
     */
    public int getIdReduction() {
        return idReduction;
    }

    /**
     * Définit l'identifiant de la réduction.
     * @param idReduction L'identifiant à attribuer à la réduction
     */
    public void setIdReduction(int idReduction) {
        this.idReduction = idReduction;
    }

    /**
     * Retourne le nom de la réduction.
     * @return Le nom de la réduction
     */
    public String getNomReduction() {
        return nomReduction;
    }

    /**
     * Définit le nom de la réduction.
     * @param nomReduction Le nom à attribuer à la réduction
     */
    public void setNomReduction(String nomReduction) {
        this.nomReduction = nomReduction;
    }

    /**
     * Retourne le pourcentage de la réduction.
     * @return Le pourcentage de la réduction
     */
    public String getPourcentageReduction() {
        return pourcentageReduction;
    }

    /**
     * Définit le pourcentage de la réduction.
     * @param pourcentageReduction Le pourcentage à attribuer à la réduction
     */
    public void setPourcentageReduction(String pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    /**
     * Retourne le type de la réduction (par exemple, "percent" ou "fixed").
     * @return Le type de la réduction
     */
    public int getTypeReduction() {
        return typeReduction;
    }

    /**
     * Définit le type de la réduction.
     * @param typeReduction Le type à attribuer à la réduction (par exemple, "percent" ou "fixed")
     */
    public void setTypeReduction(int typeReduction) {
        this.typeReduction = typeReduction;
    }

    /**
     * Retourne une chaîne de caractères représentant la réduction.
     * Cette méthode retourne une chaîne contenant l'ID, le nom, le pourcentage et le type de la réduction.
     * @return La représentation sous forme de chaîne de la réduction
     */
    @Override
    public String toString() {
        return "Reduction{" +
                "idReduction=" + idReduction +
                ", nomReduction='" + nomReduction + '\'' +
                ", pourcentageReduction='" + pourcentageReduction + '\'' +
                ", typeReduction=" + typeReduction +
                '}';
    }
}
