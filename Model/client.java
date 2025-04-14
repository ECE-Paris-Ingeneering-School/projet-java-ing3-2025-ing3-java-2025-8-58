package Model;

public class Client {
    private int idClient;
    private String mailClient;
    private String mdpClient;
    private String nomClient;
    private String prenomClient;

    // Constructeurs
    public Client() {}

    public Client(String mailClient, String mdpClient, String nomClient, String prenomClient) {
        this.mailClient = mailClient;
        this.mdpClient = mdpClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
    }

    // Getters et Setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

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
