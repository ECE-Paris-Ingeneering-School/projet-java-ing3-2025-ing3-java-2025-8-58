package View;

import Controller.LoginAdminController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Vue permettant la gestion de la connexion de l'administrateur.
 * Elle permet à un administrateur de se connecter avec son email et mot de passe.
 */
public class AdminLoginView extends JFrame {

    private LoginAdminController loginAdminController;
    private AdminView adminView;
    private LoginView loginView;

    private JTextField emailField;
    private JPasswordField passwordField;

    /**
     * Constructeur de la vue de connexion administrateur.
     *
     * @param loginAdminController Le contrôleur associé à cette vue pour la gestion de la connexion.
     */
    public AdminLoginView(LoginAdminController loginAdminController) {
        this.loginAdminController = loginAdminController;
        initComponents();
    }

    /**
     * Définit la vue administrateur (après connexion réussie).
     *
     * @param adminView La vue administrateur à afficher après la connexion.
     */
    public void setAdminView(AdminView adminView){
        this.adminView = adminView;
    }

    /**
     * Définit la vue de connexion client.
     *
     * @param loginView La vue de connexion client à afficher si l'administrateur souhaite revenir à la connexion client.
     */
    public void setLoginView(LoginView loginView){
        this.loginView = loginView;
    }

    /**
     * Initialise les composants de la vue de connexion.
     * Configure les éléments de l'interface graphique (champs, boutons, actions).
     */
    private void initComponents() {
        setTitle("Login administrateur");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Création des composants
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de Passe:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        JButton retourLoginViewButton = new JButton("Connexion Client");

        // Ajouter les composants à la fenêtre
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(retourLoginViewButton);

        // Action du bouton de connexion administrateur
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion();
            }
        });

        // Action du bouton de retour vers la vue client
        retourLoginViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginView.setVisible(true);
            }
        });
    }

    /**
     * Vérifie la validité de la connexion avec les informations saisies.
     * Si les informations sont correctes, l'administrateur est redirigé vers la vue administrateur.
     * Sinon, un message d'erreur est affiché.
     */
    private void verifierConnexion() {
        String email = emailField.getText();
        String motDePasse = new String(passwordField.getPassword());

        try {
            // Vérification de la connexion via le contrôleur
            boolean isConnected = loginAdminController.verifierConnexion(email, motDePasse);
            if (isConnected) {
                // Connexion réussie
                JOptionPane.showMessageDialog(this, "Connexion réussie!");
                setVisible(false);
                adminView.setVisible(true);
                adminView.setLoginView(loginView);
            } else {
                // Connexion échouée
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.");
            }
        } catch (SQLException ex) {
            // Gestion des erreurs SQL
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion: " + ex.getMessage());
        }
    }
}
