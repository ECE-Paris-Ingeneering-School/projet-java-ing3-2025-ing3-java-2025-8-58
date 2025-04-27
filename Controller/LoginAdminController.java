package Controller;

import DAO.AdminDAO;
import DAO.DaoFactory;
import Model.Admin;

import java.sql.SQLException;

public class LoginAdminController {

    private AdminDAO adminDAO;

    /**
     * Constructeur du contrôleur de connexion admin
     * Initialise le DAO pour les administrateurs
     * @param daoFactory Fabrique de DAO
     */
    public LoginAdminController(DaoFactory daoFactory) {
        this.adminDAO = daoFactory.getAdminDAO();
    }

    /**
     * Vérifie la connexion d'un administrateur
     * @param email Email de l'administrateur
     * @param motDePasse Mot de passe de l'administrateur
     * @return true si les identifiants sont corrects, false sinon
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean verifierConnexion(String email, String motDePasse) throws SQLException {
        Admin admin = adminDAO.obtenirAdminParEmail(email);
        return admin != null && admin.getMdpAdmin().equals(motDePasse);
    }

    /**
     * Récupère un administrateur par son email
     * @param email Email de l'administrateur
     * @return L'administrateur correspondant ou null
     * @throws SQLException en cas d'erreur SQL
     */
    public Admin obtenirAdminParEmail(String email) throws SQLException {
        return adminDAO.obtenirAdminParEmail(email);
    }
}
