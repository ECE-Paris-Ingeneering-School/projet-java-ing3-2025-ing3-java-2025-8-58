import Controller.*;
import DAO.DaoFactory;
import View.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialisation de la connexion à la base de données
            DaoFactory daoFactory = DaoFactory.getInstance("attractions", "root", "root");

            // Initialisation des contrôleurs
            AdminController adminController = new AdminController(daoFactory);
            LoginController loginController = new LoginController(daoFactory);
            LoginAdminController loginAdminController = new LoginAdminController(daoFactory);
            AttractionController attractionController = new AttractionController(daoFactory);
            ReservationController reservationController = new ReservationController(daoFactory);

            // Initialisation des vues
            AdminView adminView = new AdminView(adminController);
            ReservationView reservationView = new ReservationView(attractionController, reservationController);
            AdminLoginView adminLoginView = new AdminLoginView(loginAdminController);
            SignUpView signUpView = new SignUpView(adminController);
            LoginView loginView = new LoginView(loginController);

            // Transfet des instances
            adminLoginView.setAdminView(adminView);
            adminLoginView.setLoginView(loginView);
            signUpView.setLoginView(loginView);
            loginView.setadminLoginView(adminLoginView);
            loginView.setReservationView(reservationView);
            loginView.setSignUpView(signUpView);

            // Afficher la vue de connexion
            loginView.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
