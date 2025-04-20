package View;

import Controller.AttractionController;
import Controller.ReservationController;
import Model.Attraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReservationView extends JFrame {

    private ReservationController reservationController;
    private AttractionController attractionController;
    private JList<Attraction> attractionList;
    private DefaultListModel<Attraction> attractionListModel;
    private JPanel attractionDetailsPanel;
    private JLabel attractionImageLabel;
    private JTextArea attractionDescriptionArea;
    private JButton reserveButton;
    private JTextField dateReservationField;
    private JTextField idClientField;

    public ReservationView(AttractionController attractionController, ReservationController reservationController) {
        this.attractionController = attractionController;
        this.reservationController = reservationController;
        initComponents();
        loadAttractions();
    }

    private void initComponents() {
        setTitle("Réservation");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel for attraction list
        attractionListModel = new DefaultListModel<>();
        attractionList = new JList<>(attractionListModel);
        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attractionList.addListSelectionListener(e -> showSelectedAttractionDetails());

        JScrollPane attractionScrollPane = new JScrollPane(attractionList);
        add(attractionScrollPane, BorderLayout.WEST);

        // Right panel for attraction details
        attractionDetailsPanel = new JPanel();
        attractionDetailsPanel.setLayout(new BorderLayout());

        attractionImageLabel = new JLabel();
        attractionImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attractionDetailsPanel.add(attractionImageLabel, BorderLayout.NORTH);

        attractionDescriptionArea = new JTextArea();
        attractionDescriptionArea.setEditable(false);
        attractionDescriptionArea.setLineWrap(true);
        attractionDescriptionArea.setWrapStyleWord(true);
        attractionDetailsPanel.add(new JScrollPane(attractionDescriptionArea), BorderLayout.CENTER);

        reserveButton = new JButton("Réserver l'attraction");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirPanierView();
            }
        });
        attractionDetailsPanel.add(reserveButton, BorderLayout.SOUTH);

        add(attractionDetailsPanel, BorderLayout.CENTER);

        // Bottom panel for reservation fields
        JPanel reservationPanel = new JPanel(new GridLayout(2, 2));
        JLabel dateReservationLabel = new JLabel("Date Réservation:");
        dateReservationField = new JTextField();
        JLabel idClientLabel = new JLabel("ID Client:");
        idClientField = new JTextField();

        reservationPanel.add(dateReservationLabel);
        reservationPanel.add(dateReservationField);
        reservationPanel.add(idClientLabel);
        reservationPanel.add(idClientField);

        add(reservationPanel, BorderLayout.SOUTH);
    }

    private void loadAttractions() {
        try {
            List<Attraction> attractions = attractionController.obtenirToutesAttractions();
            for (Attraction attraction : attractions) {
                attractionListModel.addElement(attraction);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des attractions: " + ex.getMessage());
        }
    }

    private void showSelectedAttractionDetails() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction != null) {
            attractionImageLabel.setIcon(new ImageIcon(selectedAttraction.getImagePath()));
            attractionDescriptionArea.setText(selectedAttraction.getDescriptionAttraction());
        }
    }

    private void ouvrirPanierView() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction != null) {
            PanierView panierView = new PanierView(reservationController, selectedAttraction.getIdAttraction());
            panierView.setVisible(true);
            this.setVisible(false); // Masquer la fenêtre actuelle
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une attraction.");
        }
    }

    private void ajouterReservation() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une attraction.");
            return;
        }

        Date dateReservation = Date.valueOf(dateReservationField.getText());
        int idClient = Integer.parseInt(idClientField.getText());
        int idAttraction = selectedAttraction.getIdAttraction();

        /*Reservation reservation = new Reservation(dateReservation, idClient, idAttraction);
        try {
            reservationController.ajouterReservation(reservation);
            JOptionPane.showMessageDialog(this, "Réservation ajoutée avec succès!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réservation: " + ex.getMessage());
        }*/
    }
}
