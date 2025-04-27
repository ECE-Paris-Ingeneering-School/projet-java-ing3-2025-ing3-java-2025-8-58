package Model;

/**
 * Classe représentant un client dans le système.
 * Contient les informations relatives à un client : identifiant, email, mot de passe, nom et prénom.
 */
public class Client {

    private int idClient;
    private String mailClient;
    private String mdpClient;
    private String nomClient;
    private String prenomClient;

    /**
     * Constructeur par défaut.
     * Utilisé pour créer un objet Client vide.
     */
    public Client() {}

    /**
     * Constructeur pour initialiser un client avec un email, un mot de passe, un nom et un prénom.
     * @param mailClient L'email du client
     * @param mdpClient Le mot de passe du client
     * @param nomClient Le nom du client
     * @param prenomClient Le prénom du client
     */
    public Client(String mailClient, String mdpClient, String nomClient, String prenomClient) {
        this.mailClient = mailClient;
        this.mdpClient = mdpClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
    }

    /**
     * Retourne l'identifiant du client.
     * @return L'identifiant du client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Définit l'identifiant du client.
     * @param idClient L'identifiant à attribuer au client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Retourne l'email du client.
     * @return L'email du client
     */
    public String getMailClient() {
        return mailClient;
    }

    /**
     * Définit l'email du client.
     * @param mailClient L'email à attribuer au client
     */
    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    /**
     * Retourne le mot de passe du client.
     * @return Le mot de passe du client
     */
    public String getMdpClient() {
        return mdpClient;
    }

    /**
     * Définit le mot de passe du client.
     * @param mdpClient Le mot de passe à attribuer au client
     */
    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    /**
     * Retourne le nom du client.
     * @return Le nom du client
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * Définit le nom du client.
     * @param nomClient Le nom à attribuer au client
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * Retourne le prénom du client.
     * @return Le prénom du client
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * Définit le prénom du client.
     * @param prenomClient Le prénom à attribuer au client
     */
    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    /**
     * Retourne une chaîne de caractères représentant le client.
     * Cette méthode retourne une chaîne avec l'ID, l'email, le nom et le prénom du client.
     * @return La représentation sous forme de chaîne du client
     */
    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", mailClient='" + mailClient + '\'' +
                ", nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                '}';
    }
}
