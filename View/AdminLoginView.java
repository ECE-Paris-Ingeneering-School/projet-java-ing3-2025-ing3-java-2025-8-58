package View;

import Controller.LoginAdminController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminLoginView extends JFrame {

    private LoginAdminController loginAdminController;
    private AdminView adminView;

    private JTextField emailField;
    private JPasswordField passwordField;

    public AdminLoginView(LoginAdminController loginAdminController, AdminView adminView) {
        this.loginAdminController = loginAdminController;
        this.adminView = adminView;
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de Passe:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion();
            }
        });
    }

    private void verifierConnexion() {
        String email = emailField.getText();
        String motDePasse = new String(passwordField.getPassword());

        try {
            boolean isConnected = loginAdminController.verifierConnexion(email, motDePasse);
            if (isConnected) {
                JOptionPane.showMessageDialog(this, "Connexion réussie!");
                setVisible(false);
                adminView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion: " + ex.getMessage());
        }
    }
}
