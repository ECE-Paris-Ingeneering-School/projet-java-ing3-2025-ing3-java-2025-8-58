package View;

import Controller.ReservationController;
import Model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class PanierView extends JFrame {

    private ReservationController reservationController;
    private int attractionId;

    private JTextField dateReservationField;
    private JTextField idClientField;
    private JTextField idAttractionField;
    private JTextField idClientReserveField;
    private JTextField nomBilletField;
    private JTextField prenomBilletField;
    private JTextField nombrePersonnesField;

    public PanierView(ReservationController reservationController, int attractionId) {
        this.reservationController = reservationController;
        this.attractionId = attractionId;
        initComponents();
    }

    private void initComponents() {
        setTitle("Panier de Réservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        JLabel dateReservationLabel = new JLabel("Date Réservation (YYYY-MM-DD):");
        dateReservationField = new JTextField();
        JLabel idClientLabel = new JLabel("ID Client:");
        idClientField = new JTextField();
        JLabel idAttractionLabel = new JLabel("ID Attraction:");
        idAttractionField = new JTextField(String.valueOf(attractionId));
        idAttractionField.setEditable(false);
        JLabel idClientReserveLabel = new JLabel("ID Client Réserve:");
        idClientReserveField = new JTextField();
        JLabel nomBilletLabel = new JLabel("Nom Billet:");
        nomBilletField = new JTextField();
        JLabel prenomBilletLabel = new JLabel("Prénom Billet:");
        prenomBilletField = new JTextField();
        JLabel nombrePersonnesLabel = new JLabel("Nombre de Personnes:");
        nombrePersonnesField = new JTextField();
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
        add(nombrePersonnesLabel);
        add(nombrePersonnesField);
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
        int idAttraction = attractionId;
        int idClientReserve = Integer.parseInt(idClientReserveField.getText());
        String nomBillet = nomBilletField.getText();
        String prenomBillet = prenomBilletField.getText();
        int nombrePersonnes = Integer.parseInt(nombrePersonnesField.getText());

        Reservation reservation = new Reservation(dateReservation, idClient, idAttraction, idClientReserve, nomBillet, prenomBillet);
        try {
            reservationController.ajouterReservation(reservation);
            JOptionPane.showMessageDialog(this, "Réservation ajoutée avec succès!");
            dispose(); // Fermer la fenêtre après l'ajout de la réservation
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réservation: " + ex.getMessage());
        }
    }
}
