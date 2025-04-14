package Controller;

import DAO.ClientDAO;
import DAO.DaoFactory;
import Model.Client;

import java.sql.SQLException;

public class LoginController {

    private ClientDAO clientDAO;

    public LoginController(DaoFactory daoFactory) {
        this.clientDAO = daoFactory.getClientDAO();
    }

    public boolean verifierConnexion(String email, String motDePasse) throws SQLException {
        Client client = clientDAO.obtenirClientParEmail(email);
        return client != null && client.getMdpClient().equals(motDePasse);
    }

    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }
}
