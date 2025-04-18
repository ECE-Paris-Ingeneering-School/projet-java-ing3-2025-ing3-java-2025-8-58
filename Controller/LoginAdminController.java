package Controller;

import DAO.AdminDAO;
import DAO.DaoFactory;
import Model.Admin;

import java.sql.SQLException;

public class LoginAdminController {

    private AdminDAO adminDAO;

    public LoginAdminController(DaoFactory daoFactory) {
        this.adminDAO = daoFactory.getAdminDAO();
    }

    public boolean verifierConnexion(String email, String motDePasse) throws SQLException {
        Admin admin = adminDAO.obtenirAdminParEmail(email);
        return admin != null && admin.getMdpAdmin().equals(motDePasse);
    }

    public Admin obtenirAdminParEmail(String email) throws SQLException {
        return adminDAO.obtenirAdminParEmail(email);
    }
}
