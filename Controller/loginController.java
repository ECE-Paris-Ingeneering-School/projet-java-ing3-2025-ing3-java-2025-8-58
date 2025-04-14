package Controller;

import DAO.ClientDAO;
import DAO.DaoFactory;
import Model.Client;

import java.sql.SQLException;

public class LoginController {

    private ClientDAO clientDAO;

    public LoginController(DaoFactory daoFactory) throws SQLException {
        this.clientDAO = new ClientDAO(daoFactory);
    }

    // Vérifier les informations de connexion d'un client
    public boolean verifierConnexion(String email, String motDePasse) throws SQLException {
        Client client = clientDAO.obtenirClientParEmail(email);
        return client != null && client.getMdpClient().equals(motDePasse);
    }

    // Récupérer un client par son email
    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }
}
