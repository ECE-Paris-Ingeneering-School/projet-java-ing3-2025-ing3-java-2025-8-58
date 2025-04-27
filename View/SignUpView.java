package View;

import Controller.AdminController;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Classe représentant la vue pour la création d'un compte client.
 * Permet à l'utilisateur de saisir ses informations pour créer un compte client.
 */
public class SignUpView extends JFrame {

    private AdminController adminController;
    private LoginView loginView;

    private JTextField mailClientField;
    private JTextField mdpClientField;
    private JTextField nomClientField;
    private JTextField prenomClientField;

    /**
     * Constructeur de la vue de création de compte client.
     *
     * @param adminController Le contrôleur d'administration pour l'ajout de clients.
     */
    public SignUpView(AdminController adminController) {
        this.adminController = adminController;
        initComponents();
    }

    /**
     * Définit la vue de la page de connexion.
     *
     * @param loginView La vue de connexion à afficher lorsqu'on retourne à la page de connexion.
     */
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Initialise les composants graphiques de la vue.
     * Cette méthode crée les champs de saisie et les boutons de l'interface.
     */
    private void initComponents() {
        setTitle("Création de compte client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Création des labels et champs de saisie
        JLabel mailClientLabel = new JLabel("Email Client:");
        mailClientField = new JTextField();
        JLabel mdpClientLabel = new JLabel("Mot de Passe Client:");
        mdpClientField = new JTextField();
        JLabel nomClientLabel = new JLabel("Nom Client:");
        nomClientField = new JTextField();
        JLabel prenomClientLabel = new JLabel("Prénom Client:");
        prenomClientField = new JTextField();

        // Création des boutons
        JButton ajouterClientButton = new JButton("Ajouter Client");
        JButton retourLoginViewButton = new JButton("Retour à la page de connexion");

        // Ajout des composants au layout
        add(mailClientLabel);
        add(mailClientField);
        add(mdpClientLabel);
        add(mdpClientField);
        add(nomClientLabel);
        add(nomClientField);
        add(prenomClientLabel);
        add(prenomClientField);
        add(ajouterClientButton);
        add(retourLoginViewButton);

        // Écouteurs d'événements pour les boutons
        ajouterClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterClient();
            }
        });

        retourLoginViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retour à la page de connexion
                setVisible(false);
                loginView.setVisible(true);
            }
        });
    }

    /**
     * Effectue l'ajout d'un nouveau client en récupérant les informations saisies.
     *
     * Cette méthode crée une nouvelle instance de client avec les données saisies et l'ajoute via le contrôleur d'administration.
     * En cas d'erreur, un message d'erreur est affiché à l'utilisateur.
     */
    private void ajouterClient() {
        // Récupération des valeurs saisies dans les champs
        String mailClient = mailClientField.getText();
        String mdpClient = mdpClientField.getText();
        String nomClient = nomClientField.getText();
        String prenomClient = prenomClientField.getText();

        // Création d'un objet client avec les informations saisies
        Client client = new Client(mailClient, mdpClient, nomClient, prenomClient);
        try {
            // Ajout du client via le contrôleur d'administration
            adminController.ajouterClient(client);
            JOptionPane.showMessageDialog(this, "Client ajouté avec succès!");
        } catch (SQLException e) {
            // En cas d'erreur lors de l'ajout dans la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client: " + e.getMessage());
        }
    }
}
