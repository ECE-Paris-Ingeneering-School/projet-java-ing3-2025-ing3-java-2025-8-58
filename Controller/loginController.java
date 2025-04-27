package Controller;

import DAO.ClientDAO;
import DAO.DaoFactory;
import Model.Client;

import java.sql.SQLException;

public class LoginController {

    private ClientDAO clientDAO;

    /**
     * Constructeur du contrôleur de connexion client
     * Initialise le DAO pour les clients
     * @param daoFactory Fabrique de DAO
     */
    public LoginController(DaoFactory daoFactory) {
        this.clientDAO = daoFactory.getClientDAO();
    }

    /**
     * Vérifie la connexion d'un client
     * @param email Email du client
     * @param motDePasse Mot de passe du client
     * @return Le client correspondant s'il existe, sinon null
     * @throws SQLException en cas d'erreur SQL
     */
    public Client verifierConnexion(String email, String motDePasse) throws SQLException {
        return clientDAO.obtenirClientParEmailEtMotDePasse(email, motDePasse);
    }

    /**
     * Récupère un client par son email
     * @param email Email du client
     * @return Le client correspondant ou null
     * @throws SQLException en cas d'erreur SQL
     */
    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }
}
