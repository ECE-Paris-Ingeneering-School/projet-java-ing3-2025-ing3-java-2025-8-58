package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import Controller.*;
import Model.*;


public class PanierView extends JFrame {
    private ReservationView reservationView;
    private ReservationController reservationController;
    private AttractionController attractionController;
    private ReductionController reductionController;
    private int clientId;

    private JPanel mainPanel;
    private JTextArea detailsArea;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JButton payButton;
    private JButton retourButton;

    public PanierView(ReservationController reservationController, AttractionController attractionController, ReductionController reductionController, Client client) {
        this.reservationController = reservationController;
        this.attractionController = attractionController;
        this.reductionController = reductionController;

        if (client==null) {
            this.clientId = 0;
        }else{
            this.clientId = client.getIdClient();
        }

        initializeUI();
        loadReservationsAndCalculatePrices();
    }

    public void setReservationView(ReservationView reservationView){
        this.reservationView=reservationView;
    }

    private void initializeUI() {
        setTitle("Paiement des réservations");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Section détails des réservations et calcul du prix
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Section paiement
        JPanel paymentPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Informations de paiement"));

        paymentPanel.add(new JLabel("Numéro de carte:"));
        cardNumberField = new JTextField();
        paymentPanel.add(cardNumberField);

        paymentPanel.add(new JLabel("Date d'expiration (MM/AA):"));
        expiryDateField = new JTextField();
        paymentPanel.add(expiryDateField);

        paymentPanel.add(new JLabel("CVV:"));
        cvvField = new JTextField();
        paymentPanel.add(cvvField);

        retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                reservationView.setVisible(true);
            }
        });
        paymentPanel.add(retourButton);

        payButton = new JButton("Payer");
        payButton.addActionListener(new PayButtonListener());
        
        paymentPanel.add(payButton);

        mainPanel.add(paymentPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadReservationsAndCalculatePrices() {
        try {
            Date today = new Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            
            // Récupérer les réservations du jour pour ce client
            List<Reservation> reservations = reservationController.obtenirReservationsParDate(sqlDate);
            reservations.removeIf(res -> res.getID_client() != clientId || res.isPaye_reservation());
            
            if (reservations.isEmpty()) {
                detailsArea.setText("Aucune réservation à payer pour aujourd'hui.");
                payButton.setEnabled(false);
                return;
            }
            
            // Vérifier si le client a des réservations précédentes (pour réduction fréquent)
            boolean hasPreviousReservation = reservationController.obtenirToutesReservations().stream()
                    .anyMatch(res -> res.getID_client() == clientId && 
                                   !res.getDate_visite().equals(sqlDate));
            
            StringBuilder sb = new StringBuilder();
            sb.append("Réservations pour aujourd'hui (").append(new SimpleDateFormat("dd/MM/yyyy").format(today)).append("):\n\n");
            
            float totalAdult = 0;
            float totalSenior = 0;
            float totalChild = 0;
            float totalBeforeDiscount = 0;

            //Récupération des réductions
            Reduction reductionClientFrequent = reductionController.obtenirToutesReductions().stream()
            .filter(r -> r.getTypeReduction() == 1) // Type 1 = client fréquent, 2 enfant, 3 sénior
            .findFirst()
            .orElse(null);
            Reduction reductionEnfant = reductionController.obtenirToutesReductions().stream()
            .filter(r -> r.getTypeReduction() == 2) 
            .findFirst()
            .orElse(null);
            Reduction reductionSenior = reductionController.obtenirToutesReductions().stream()
            .filter(r -> r.getTypeReduction() ==3) 
            .findFirst()
            .orElse(null);


            
            // Calcul des prix 
            for (Reservation res : reservations) {
                Attraction attraction = attractionController.obtenirToutesAttractions().stream()
                        .filter(a -> a.getIdAttraction() == res.getID_attraction())
                        .findFirst()
                        .orElse(null);
                
                if (attraction != null) {
                    float adultPrice = res.getNb_adulte() * attraction.getPrixAttraction();
                    float seniorPrice = res.getNb_senior() * attraction.getPrixAttraction();
                    float childPrice = res.getNb_enfant() * attraction.getPrixAttraction(); 
                    
                    sb.append(attraction.getNomAttraction()).append(":\n");
                    sb.append(String.format("  Adultes: %d x %.2f€ = %.2f€\n", res.getNb_adulte(), attraction.getPrixAttraction(), adultPrice));
                    sb.append(String.format("  Seniors: %d x %.2f€ = %.2f€\n", res.getNb_senior(), attraction.getPrixAttraction() , seniorPrice));
                    sb.append(String.format("  Enfants: %d x %.2f€ = %.2f€\n", res.getNb_enfant(), attraction.getPrixAttraction() , childPrice));
                    sb.append(String.format("  Sous-total: %.2f€\n\n", adultPrice + seniorPrice + childPrice));
                    
                    totalAdult += adultPrice;
                    totalSenior += seniorPrice;
                    totalChild += childPrice;
                }
            }
            
            totalBeforeDiscount = totalAdult + totalSenior + totalChild;
            sb.append(String.format("Total avant réductions: %.2f€\n", totalBeforeDiscount));
            
            // Application des réductions
            float totalDiscount = 0;
            
            // Réduction client fréquent
            if (hasPreviousReservation) {
                Reduction frequentClientReduction = reductionController.obtenirToutesReductions().stream()
                        .filter(r -> r.getTypeReduction() == 1) // Type 1 = client fréquent, 2 enfant, 3 sénior
                        .findFirst()
                        .orElse(null);
                
                if (frequentClientReduction != null) {
                    float discountPercent = Float.parseFloat(frequentClientReduction.getPourcentageReduction().replace("%", "")) / 100;
                    float discountAmount = totalBeforeDiscount * discountPercent;
                    totalDiscount += discountAmount;
                    sb.append(String.format("Réduction %s (%s): -%.2f€\n", 
                        frequentClientReduction.getNomReduction(), 
                        frequentClientReduction.getPourcentageReduction(), 
                        discountAmount));
                }
            }
            
            float totalAfterDiscount = totalBeforeDiscount - totalDiscount;
            sb.append(String.format("\nTotal après réductions: %.2f€\n", totalAfterDiscount));

            if (clientId==0) {
                sb.append(String.format("\nCréez votre compte pour bénéficier de réductions sur les réservations !\n", totalAfterDiscount));
            }
            
            detailsArea.setText(sb.toString());
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement des réservations: " + e.getMessage(), 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private class PayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validation des informations de carte
            if (cardNumberField.getText().trim().isEmpty() || 
                expiryDateField.getText().trim().isEmpty() || 
                cvvField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(PanierView.this, 
                    "Veuillez remplir tous les champs de paiement", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Marquer toutes les réservations comme payées
                java.sql.Date today = new java.sql.Date(new Date().getTime());
                List<Reservation> reservations = reservationController.obtenirReservationsParDate(today);
                reservations.removeIf(res -> res.getID_client() != clientId || res.isPaye_reservation());
                
                for (Reservation res : reservations) {
                    res.setPaye_reservation(true);
                    reservationController.mettreAJourReservation(res);
                }

                JOptionPane.showMessageDialog(PanierView.this, 
                    "Paiement effectué avec succès!", 
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                // Retour à la vue précédente
                dispose();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(PanierView.this, 
                    "Erreur lors de la mise à jour des réservations: " + ex.getMessage(), 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}