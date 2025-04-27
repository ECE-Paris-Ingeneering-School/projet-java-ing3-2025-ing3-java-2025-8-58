package Model;

/**
 * Classe représentant un administrateur dans le système.
 * Contient les informations relatives à un administrateur : identifiant, email et mot de passe.
 */
public class Admin {

    private int idAdmin;
    private String mailAdmin;
    private String mdpAdmin;

    /**
     * Constructeur par défaut.
     * Utilisé pour créer un objet Admin vide.
     */
    public Admin() {}

    /**
     * Constructeur pour initialiser un administrateur avec un email et un mot de passe.
     * @param mailAdmin L'email de l'administrateur
     * @param mdpAdmin Le mot de passe de l'administrateur
     */
    public Admin(String mailAdmin, String mdpAdmin) {
        this.mailAdmin = mailAdmin;
        this.mdpAdmin = mdpAdmin;
    }

    /**
     * Retourne l'identifiant de l'administrateur.
     * @return L'identifiant de l'administrateur
     */
    public int getIdAdmin() {
        return idAdmin;
    }

    /**
     * Définit l'identifiant de l'administrateur.
     * @param idAdmin L'identifiant à attribuer à l'administrateur
     */
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    /**
     * Retourne l'email de l'administrateur.
     * @return L'email de l'administrateur
     */
    public String getMailAdmin() {
        return mailAdmin;
    }

    /**
     * Définit l'email de l'administrateur.
     * @param mailAdmin L'email à attribuer à l'administrateur
     */
    public void setMailAdmin(String mailAdmin) {
        this.mailAdmin = mailAdmin;
    }

    /**
     * Retourne le mot de passe de l'administrateur.
     * @return Le mot de passe de l'administrateur
     */
    public String getMdpAdmin() {
        return mdpAdmin;
    }

    /**
     * Définit le mot de passe de l'administrateur.
     * @param mdpAdmin Le mot de passe à attribuer à l'administrateur
     */
    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    /**
     * Retourne une chaîne de caractères représentant l'administrateur.
     * La chaîne contient l'id et l'email de l'administrateur.
     * @return La chaîne de caractères représentant l'administrateur
     */
    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", mailAdmin='" + mailAdmin + '\'' +
                '}';
    }
}
