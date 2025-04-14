import Controller.AdminController;
import Controller.LoginController;
import Controller.ReservationController;
import DAO.DaoFactory;
import View.AdminView;
import View.LoginView;
import View.ReservationView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialisation de la connexion à la base de données
            DaoFactory daoFactory = DaoFactory.getInstance("attractions", "root", "root");

            // Initialisation des contrôleurs
            AdminController adminController = new AdminController(daoFactory);
            LoginController loginController = new LoginController(daoFactory);
            ReservationController reservationController = new ReservationController(daoFactory);

            // Initialisation des vues
            AdminView adminView = new AdminView(adminController);
            LoginView loginView = new LoginView(loginController);
            ReservationView reservationView = new ReservationView(reservationController);

            // Afficher les vues
            adminView.setVisible(true);
            loginView.setVisible(true);
            reservationView.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

