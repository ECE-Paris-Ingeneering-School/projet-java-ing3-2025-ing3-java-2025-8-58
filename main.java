import Controller.AdminController;
import Controller.LoginAdminController;
import Controller.LoginController;
import Controller.ReservationController;
import DAO.DaoFactory;
import View.AdminLoginView;
import View.AdminView;
import View.LoginView;
import View.ReservationView;

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
            ReservationController reservationController = new ReservationController(daoFactory);

            // Initialisation des vues
            AdminView adminView = new AdminView(adminController);
            ReservationView reservationView = new ReservationView(reservationController);
            AdminLoginView adminLoginView = new AdminLoginView(loginAdminController, adminView);
            LoginView loginView = new LoginView(loginController, reservationView, adminLoginView); // Passer ReservationView à LoginView

            // Afficher la vue de connexion
            loginView.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
