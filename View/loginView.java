package View;

import Controller.LoginController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JFrame {

    private LoginController loginController;
    private ReservationView reservationView; 
    private AdminLoginView adminLoginView;
    private SignUpView signUpView;

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;
        initComponents();
    }

    public void setReservationView(ReservationView reservationView){
        this.reservationView = reservationView;
    }

    public void setadminLoginView(AdminLoginView adminLoginView){
        this.adminLoginView = adminLoginView;
    }

    public void setSignUpView(SignUpView signUpView){
        this.signUpView = signUpView;
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de Passe:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        JButton adminButton = new JButton("Administrateur");
        JButton signupButton = new JButton("Créer un compte");
        JButton invitedButton = new JButton("Ne pas se connecter");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(adminButton);
        add(signupButton);
        add(invitedButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                adminLoginView.setVisible(true);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                signUpView.setVisible(true);
            }
        });

        invitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                reservationView.setVisible(true);
            }
        });
    }

    private void verifierConnexion() {
        String email = emailField.getText();
        String motDePasse = new String(passwordField.getPassword());

        try {
            Client client = loginController.verifierConnexion(email, motDePasse);
            if (client != null) {
                JOptionPane.showMessageDialog(this, "Connexion réussie!");
                setVisible(false);
                reservationView.setClient(client); 
                reservationView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion: " + ex.getMessage());
        }
    }
}
