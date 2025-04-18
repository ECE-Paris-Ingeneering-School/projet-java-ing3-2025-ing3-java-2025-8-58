package View;

import Controller.AdminController;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUpView extends JFrame {

    private AdminController adminController;
    private LoginView loginView;

    private JTextField mailClientField;
    private JTextField mdpClientField;
    private JTextField nomClientField;
    private JTextField prenomClientField;

    public SignUpView(AdminController adminController) {
        this.adminController = adminController;
        initComponents();
    }

    public void setLoginView(LoginView loginView){
        this.loginView = loginView;
    }

    private void initComponents() {
        setTitle("Création de compte client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel mailClientLabel = new JLabel("Email Client:");
        mailClientField = new JTextField();
        JLabel mdpClientLabel = new JLabel("Mot de Passe Client:");
        mdpClientField = new JTextField();
        JLabel nomClientLabel = new JLabel("Nom Client:");
        nomClientField = new JTextField();
        JLabel prenomClientLabel = new JLabel("Prénom Client:");
        prenomClientField = new JTextField();
        JButton ajouterClientButton = new JButton("Ajouter Client");
        JButton retourLoginViewButton = new JButton("Retour à la page de connexion");

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

        ajouterClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterClient();
            }
        });

        retourLoginViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginView.setVisible(true);
            }
        });

    }

    private void ajouterClient() {
        String mailClient = mailClientField.getText();
        String mdpClient = mdpClientField.getText();
        String nomClient = nomClientField.getText();
        String prenomClient = prenomClientField.getText();

        Client client = new Client(mailClient, mdpClient, nomClient, prenomClient);
        try {
            adminController.ajouterClient(client);
            JOptionPane.showMessageDialog(this, "Client ajouté avec succès!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client: " + e.getMessage());
        }
    }
}
