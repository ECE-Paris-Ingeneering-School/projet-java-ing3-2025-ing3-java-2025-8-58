import Controller.*;
import DAO.DaoFactory;
import View.*;
import javax.swing.*;

/**
 * Classe principale de l'application.
 * Cette classe initialise les contrôleurs, les vues, et établit la connexion à la base de données,
 * puis lance l'interface utilisateur.
 */
public class Main {

    /**
     * Point d'entrée principal de l'application.
     * Cette méthode initialise la connexion à la base de données, configure les contrôleurs et les vues,
     * et affiche la fenêtre de connexion.
     *
     * @param args Arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        try {
            // Initialisation de la connexion à la base de données
            // Crée une instance de DaoFactory pour gérer la connexion avec la base de données
            DaoFactory daoFactory = DaoFactory.getInstance("attractions", "root", "root");

            // Initialisation des contrôleurs
            // Chaque contrôleur est responsable de la gestion d'une fonctionnalité spécifique
            AdminController adminController = new AdminController(daoFactory);
            LoginController loginController = new LoginController(daoFactory);
            LoginAdminController loginAdminController = new LoginAdminController(daoFactory);
            AttractionController attractionController = new AttractionController(daoFactory);
            ReservationController reservationController = new ReservationController(daoFactory);
            ReductionController reductionController = new ReductionController(daoFactory);

            // Initialisation des vues
            // Crée les différentes vues utilisées dans l'application (par exemple, vue d'administration, vue de réservation, etc.)
            AdminView adminView = new AdminView(adminController);
            ReservationView reservationView = new ReservationView(attractionController, reservationController, reductionController);
            AdminLoginView adminLoginView = new AdminLoginView(loginAdminController);
            SignUpView signUpView = new SignUpView(adminController);
            LoginView loginView = new LoginView(loginController);

            // Transfert des instances entre les vues
            // Les vues ont besoin des références des autres vues pour les interactions
            adminLoginView.setAdminView(adminView);
            adminLoginView.setLoginView(loginView);
            signUpView.setLoginView(loginView);
            loginView.setadminLoginView(adminLoginView);
            loginView.setReservationView(reservationView);
            loginView.setSignUpView(signUpView);
            reservationView.setLoginView(loginView);

            // Afficher la vue de connexion
            // La vue de connexion est affichée en premier lorsque l'application démarre
            loginView.setVisible(true);

        } catch (Exception e) {
            // Gestion des erreurs de connexion à la base de données
            // Si une erreur survient lors de l'initialisation, elle est affichée à l'utilisateur
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
