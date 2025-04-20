package View;

import Model.Client;
import Model.Reservation;
import DAO.ReservationDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReserverView extends JFrame {
    private JTextField dateVisiteField;
    private JTextField nbAdulteField;
    private JTextField nbSeniorField;
    private JTextField nbEnfantField;
    private JButton reserverButton;
    private ReservationDAO reservationDAO;
    private Client client;
    private int idAttraction;

    public ReserverView(ReservationDAO reservationDAO, Client client, int idAttraction) {
        this.reservationDAO = reservationDAO;
        this.client = client;
        this.idAttraction = idAttraction;
        initComponents();
    }

    private void initComponents() {
        setTitle("Réserver une attraction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel dateVisiteLabel = new JLabel("Date de visite (YYYY-MM-DD):");
        dateVisiteField = new JTextField();

        JLabel nbAdulteLabel = new JLabel("Nombre d'adultes:");
        nbAdulteField = new JTextField();

        JLabel nbSeniorLabel = new JLabel("Nombre de seniors:");
        nbSeniorField = new JTextField();

        JLabel nbEnfantLabel = new JLabel("Nombre d'enfants:");
        nbEnfantField = new JTextField();

        reserverButton = new JButton("Réserver");
        reserverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserver();
            }
        });

        panel.add(dateVisiteLabel);
        panel.add(dateVisiteField);
        panel.add(nbAdulteLabel);
        panel.add(nbAdulteField);
        panel.add(nbSeniorLabel);
        panel.add(nbSeniorField);
        panel.add(nbEnfantLabel);
        panel.add(nbEnfantField);
        panel.add(new JLabel());
        panel.add(reserverButton);

        add(panel);
    }

    private void reserver() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateVisite = dateFormat.parse(dateVisiteField.getText());
            int nbAdulte = Integer.parseInt(nbAdulteField.getText());
            int nbSenior = Integer.parseInt(nbSeniorField.getText());
            int nbEnfant = Integer.parseInt(nbEnfantField.getText());

            Reservation reservation = new Reservation();
            reservation.setDate_visite(dateVisite);
            reservation.setNb_adulte(nbAdulte);
            reservation.setNb_senior(nbSenior);
            reservation.setNb_enfant(nbEnfant);
            reservation.setID_attraction(idAttraction);
            reservation.setPaye_reservation(false);

            if (client != null) {
                reservation.setID_client(client.getIdClient());
            } else {
                reservation.setID_client(0); 
            }

            reservationDAO.ajouterReservation(reservation);
            JOptionPane.showMessageDialog(this, "Réservation effectuée avec succès!");
        } catch (ParseException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
