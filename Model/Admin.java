package Model;

public class Admin {
    private int idAdmin;
    private String mailAdmin;
    private String mdpAdmin;

    // Constructeurs
    public Admin() {}

    public Admin(String mailAdmin, String mdpAdmin) {
        this.mailAdmin = mailAdmin;
        this.mdpAdmin = mdpAdmin;
    }

    // Getters et Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getMailAdmin() {
        return mailAdmin;
    }

    public void setMailClient(String mailAdmin) {
        this.mailAdmin = mailAdmin;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", mailAdmin='" + mailAdmin + '\'' +
                '}';
    }
}
