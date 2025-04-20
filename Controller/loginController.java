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

    public Client verifierConnexion(String email, String motDePasse) throws SQLException {
        return clientDAO.obtenirClientParEmailEtMotDePasse(email, motDePasse);
    }

    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }
}
