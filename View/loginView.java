package View;

import Controller.LoginController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Cette classe représente la vue de connexion de l'application. Elle permet à un utilisateur de se connecter,
 * de se connecter en tant qu'administrateur, de créer un compte, ou de se connecter en tant qu'invité.
 * Elle interagit avec le contrôleur de connexion pour vérifier les informations de l'utilisateur.
 *
 * @author [Votre Nom]
 */
public class LoginView extends JFrame {

    private LoginController loginController; // Contrôleur de gestion de la connexion
    private ReservationView reservationView; // Vue de la réservation après une connexion réussie
    private AdminLoginView adminLoginView; // Vue de connexion administrateur
    private SignUpView signUpView; // Vue pour la création d'un compte

    private JTextField emailField; // Champ pour entrer l'email
    private JPasswordField passwordField; // Champ pour entrer le mot de passe

    /**
     * Constructeur de la vue de connexion.
     * Initialise les composants graphiques de la vue.
     *
     * @param loginController Le contrôleur qui gère la logique de connexion
     */
    public LoginView(LoginController loginController) {
        this.loginController = loginController;
        initComponents(); // Initialisation des composants graphiques
    }

    /**
     * Définit la vue de réservation à afficher après une connexion réussie.
     *
     * @param reservationView La vue de réservation à afficher
     */
    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    /**
     * Définit la vue de connexion administrateur à afficher.
     *
     * @param adminLoginView La vue de connexion administrateur
     */
    public void setadminLoginView(AdminLoginView adminLoginView) {
        this.adminLoginView = adminLoginView;
    }

    /**
     * Définit la vue de création de compte à afficher.
     *
     * @param signUpView La vue de création de compte
     */
    public void setSignUpView(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    /**
     * Initialise les composants graphiques de la fenêtre de connexion, tels que les champs de saisie,
     * les boutons et leurs écouteurs d'événements.
     */
    private void initComponents() {
        setTitle("Login"); // Titre de la fenêtre
        setSize(300, 200); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportement lors de la fermeture de la fenêtre
        setLayout(new GridLayout(4, 2)); // Disposition en grille pour les composants

        // Création des étiquettes et des champs de saisie
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de Passe:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        JButton adminButton = new JButton("Administrateur");
        JButton signupButton = new JButton("Créer un compte");
        JButton invitedButton = new JButton("Ne pas se connecter");

        // Ajout des composants à la fenêtre
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(adminButton);
        add(signupButton);
        add(invitedButton);

        // Définition des écouteurs pour les boutons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion(); // Vérifier la connexion de l'utilisateur
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Masquer la vue de connexion
                adminLoginView.setVisible(true); // Afficher la vue administrateur
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Masquer la vue de connexion
                signUpView.setVisible(true); // Afficher la vue de création de compte
            }
        });

        invitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Masquer la vue de connexion
                reservationView.setVisible(true); // Afficher la vue de réservation en tant qu'invité
            }
        });
    }

    /**
     * Vérifie les informations de connexion saisies par l'utilisateur. Si la connexion réussit, l'utilisateur est
     * redirigé vers la vue de réservation. En cas d'échec, un message d'erreur est affiché.
     */
    private void verifierConnexion() {
        String email = emailField.getText(); // Récupérer l'email saisi
        String motDePasse = new String(passwordField.getPassword()); // Récupérer le mot de passe saisi

        try {
            // Vérifier la connexion via le contrôleur
            Client client = loginController.verifierConnexion(email, motDePasse);
            if (client != null) {
                JOptionPane.showMessageDialog(this, "Connexion réussie!"); // Afficher un message de succès
                setVisible(false); // Masquer la fenêtre de connexion
                reservationView.setClient(client); // Définir le client dans la vue de réservation
                reservationView.setVisible(true); // Afficher la vue de réservation
            } else {
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect."); // Afficher un message d'erreur
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion: " + ex.getMessage()); // Afficher un message d'erreur en cas d'exception SQL
        }
    }
}
