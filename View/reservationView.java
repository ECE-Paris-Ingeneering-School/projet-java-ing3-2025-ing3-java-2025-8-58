package View;

import Controller.ReservationController;
import Model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class ReservationView extends JFrame {

    private ReservationController reservationController;

    private JTextField dateReservationField;
    private JTextField idClientField;
    private JTextField idAttractionField;
    private JTextField idClientReserveField;
    private JTextField nomBilletField;
    private JTextField prenomBilletField;

    public ReservationView(ReservationController reservationController) {
        this.reservationController = reservationController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Réservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel dateReservationLabel = new JLabel("Date Réservation:");
        dateReservationField = new JTextField();
        JLabel idClientLabel = new JLabel("ID Client:");
        idClientField = new JTextField();
        JLabel idAttractionLabel = new JLabel("ID Attraction:");
        idAttractionField = new JTextField();
        JLabel idClientReserveLabel = new JLabel("ID Client Réserve:");
        idClientReserveField = new JTextField();
        JLabel nomBilletLabel = new JLabel("Nom Billet:");
        nomBilletField = new JTextField();
        JLabel prenomBilletLabel = new JLabel("Prénom Billet:");
        prenomBilletField = new JTextField();
        JButton ajouterReservationButton = new JButton("Ajouter Réservation");

        add(dateReservationLabel);
        add(dateReservationField);
        add(idClientLabel);
        add(idClientField);
        add(idAttractionLabel);
        add(idAttractionField);
        add(idClientReserveLabel);
        add(idClientReserveField);
        add(nomBilletLabel);
        add(nomBilletField);
        add(prenomBilletLabel);
        add(prenomBilletField);
        add(ajouterReservationButton);

        ajouterReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterReservation();
            }
        });
    }

    private void ajouterReservation() {
        Date dateReservation = Date.valueOf(dateReservationField.getText());
        int idClient = Integer.parseInt(idClientField.getText());
        int idAttraction = Integer.parseInt(idAttractionField.getText());
        int idClientReserve = Integer.parseInt(idClientReserveField.getText());
        String nomBillet = nomBilletField.getText();
        String prenomBillet = prenomBilletField.getText();

        Reservation reservation = new Reservation(dateReservation, idClient, idAttraction, idClientReserve, nomBillet, prenomBillet);
        try {
            reservationController.ajouterReservation(reservation);
            JOptionPane.showMessageDialog(this, "Réservation ajoutée avec succès!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réservation: " + ex.getMessage());
        }
    }
}
