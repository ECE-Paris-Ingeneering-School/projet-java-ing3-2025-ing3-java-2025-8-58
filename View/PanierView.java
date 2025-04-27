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

/**
 * Cette classe représente la vue du panier où un client peut consulter les détails de ses réservations
 * et effectuer le paiement. Elle gère également les réductions et l'affichage des informations de paiement.
 *
 * @author [Votre Nom]
 */
public class PanierView extends JFrame {
    private ReservationView reservationView; // Vue des réservations
    private ReservationController reservationController; // Contrôleur des réservations
    private AttractionController attractionController; // Contrôleur des attractions
    private ReductionController reductionController; // Contrôleur des réductions
    private int clientId; // Identifiant du client

    private JPanel mainPanel; // Panneau principal de la vue
    private JTextArea detailsArea; // Zone de texte pour afficher les détails des réservations
    private JTextField cardNumberField; // Champ pour le numéro de carte bancaire
    private JTextField expiryDateField; // Champ pour la date d'expiration de la carte
    private JTextField cvvField; // Champ pour le CVV de la carte
    private JButton payButton; // Bouton pour effectuer le paiement
    private JButton retourButton; // Bouton pour revenir à la vue des réservations

    /**
     * Constructeur de la vue du panier, qui initialise les contrôleurs et charge les réservations et calculs des prix.
     *
     * @param reservationController Le contrôleur pour gérer les réservations
     * @param attractionController Le contrôleur pour gérer les attractions
     * @param reductionController Le contrôleur pour gérer les réductions
     * @param client Le client dont les réservations doivent être affichées
     */
    public PanierView(ReservationController reservationController, AttractionController attractionController, ReductionController reductionController, Client client) {
        this.reservationController = reservationController;
        this.attractionController = attractionController;
        this.reductionController = reductionController;

        // Si le client est nul, on assigne 0 comme ID client
        if (client == null) {
            this.clientId = 0;
        } else {
            this.clientId = client.getIdClient();
        }

        initializeUI(); // Initialisation de l'interface utilisateur
        loadReservationsAndCalculatePrices(); // Chargement des réservations et calcul des prix
    }

    /**
     * Permet de définir la vue des réservations, utile pour naviguer entre les vues.
     *
     * @param reservationView La vue des réservations
     */
    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    /**
     * Initialise l'interface utilisateur, en créant les composants nécessaires à l'affichage.
     */
    private void initializeUI() {
        setTitle("Paiement des réservations"); // Titre de la fenêtre
        setSize(600, 500); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Action à la fermeture
        setLocationRelativeTo(null); // Centre la fenêtre

        // Initialisation du panneau principal avec une disposition en bordure
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Section des détails des réservations
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Section des informations de paiement
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

        // Bouton retour pour revenir à la vue des réservations
        retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Masque la vue du panier
                reservationView.setVisible(true); // Affiche la vue des réservations
            }
        });
        paymentPanel.add(retourButton);

        // Bouton pour effectuer le paiement
        payButton = new JButton("Payer");
        payButton.addActionListener(new PayButtonListener()); // Ajout de l'écouteur d'événements
        paymentPanel.add(payButton);

        // Ajout du panneau de paiement au panneau principal
        mainPanel.add(paymentPanel, BorderLayout.SOUTH);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);
    }

    /**
     * Charge les réservations pour le jour actuel, calcule les prix totaux, applique les réductions, et met à jour
     * la zone des détails.
     */
    private void loadReservationsAndCalculatePrices() {
        try {
            Date today = new Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());

            // Récupère les réservations pour le jour actuel
            List<Reservation> reservations = reservationController.obtenirReservationsParDate(sqlDate);
            reservations.removeIf(res -> res.getID_client() != clientId || res.isPaye_reservation());

            // Si aucune réservation n'est trouvée, désactive le paiement
            if (reservations.isEmpty()) {
                detailsArea.setText("Aucune réservation à payer pour aujourd'hui.");
                payButton.setEnabled(false);
                return;
            }

            // Vérifie si le client a des réservations précédentes pour appliquer des réductions
            boolean hasPreviousReservation = reservationController.obtenirToutesReservations().stream()
                    .anyMatch(res -> res.getID_client() == clientId &&
                            !res.getDate_visite().equals(sqlDate));

            StringBuilder sb = new StringBuilder();
            sb.append("Réservations pour aujourd'hui (").append(new SimpleDateFormat("dd/MM/yyyy").format(today)).append("):\n\n");

            float totalAdult = 0;
            float totalSenior = 0;
            float totalChild = 0;
            float totalBeforeDiscount = 0;

            // Récupération des réductions
            Reduction reductionClientFrequent = reductionController.obtenirToutesReductions().stream()
                    .filter(r -> r.getTypeReduction() == 1) // Type 1 = client fréquent, 2 enfant, 3 sénior
                    .findFirst()
                    .orElse(null);
            Reduction reductionEnfant = reductionController.obtenirToutesReductions().stream()
                    .filter(r -> r.getTypeReduction() == 2)
                    .findFirst()
                    .orElse(null);
            Reduction reductionSenior = reductionController.obtenirToutesReductions().stream()
                    .filter(r -> r.getTypeReduction() == 3)
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
                    sb.append(String.format("  Seniors: %d x %.2f€ = %.2f€\n", res.getNb_senior(), attraction.getPrixAttraction(), seniorPrice));
                    sb.append(String.format("  Enfants: %d x %.2f€ = %.2f€\n", res.getNb_enfant(), attraction.getPrixAttraction(), childPrice));
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
                if (reductionClientFrequent != null) {
                    float discountPercent = Float.parseFloat(reductionClientFrequent.getPourcentageReduction().replace("%", "")) / 100;
                    float discountAmount = totalBeforeDiscount * discountPercent;
                    totalDiscount += discountAmount;
                    sb.append(String.format("Réduction %s (%s): -%.2f€\n",
                            reductionClientFrequent.getNomReduction(),
                            reductionClientFrequent.getPourcentageReduction(),
                            discountAmount));
                }
            }

            float totalAfterDiscount = totalBeforeDiscount - totalDiscount;
            sb.append(String.format("\nTotal après réductions: %.2f€\n", totalAfterDiscount));

            if (clientId == 0) {
                sb.append(String.format("\nCréez votre compte pour bénéficier de réductions sur les réservations !\n"));
            }

            detailsArea.setText(sb.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement des réservations: " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Cette classe écoute les événements de clic sur le bouton de paiement et effectue le traitement du paiement.
     */
    private class PayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Vérifie si les champs de paiement sont remplis
            if (cardNumberField.getText().trim().isEmpty() ||
                    expiryDateField.getText().trim().isEmpty() ||
                    cvvField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(PanierView.this,
                        "Veuillez remplir tous les champs de paiement",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Marque toutes les réservations comme payées
                java.sql.Date today = new java.sql.Date(new Date().getTime());
                List<Reservation> reservations = reservationController.obtenirReservationsParDate(today);
                reservations.removeIf(res -> res.getID_client() != clientId || res.isPaye_reservation());

                for (Reservation res : reservations) {
                    res.setPaye_reservation(true);
                    reservationController.mettreAJourReservation(res);
                }

                // Affichage de la confirmation de paiement
                JOptionPane.showMessageDialog(PanierView.this,
                        "Paiement effectué avec succès!",
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                dispose(); // Ferme la vue du panier

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(PanierView.this,
                        "Erreur lors de la mise à jour des réservations: " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
