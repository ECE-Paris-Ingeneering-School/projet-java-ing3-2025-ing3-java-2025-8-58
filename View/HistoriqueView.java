package View;

import Controller.ReservationController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Cette classe représente la vue de l'historique des réservations d'un client.
 * Elle permet à l'utilisateur de visualiser les réservations passées en fonction de la date.
 * Le client peut également consulter les détails des réservations sélectionnées.
 *
 * @author [Votre Nom]
 */
public class HistoriqueView extends JFrame {

    private ReservationController reservationController; // Contrôleur de gestion des réservations
    private ReservationView reservationView; // Vue de réservation à laquelle retourner
    private JList<Date> dateList; // Liste des dates de réservation
    private DefaultListModel<Date> dateListModel; // Modèle pour la liste des dates
    private JPanel reservationDetailsPanel; // Panneau pour afficher les détails de la réservation
    private JTextArea reservationDetailsArea; // Zone de texte pour afficher les détails
    private JButton retourButton; // Bouton pour revenir à la vue précédente
    private int clientId; // ID du client pour filtrer les réservations

    /**
     * Constructeur de la vue de l'historique des réservations.
     * Initialise les composants de l'interface et charge les réservations.
     *
     * @param reservationController Le contrôleur qui gère les réservations.
     * @param client Le client pour lequel afficher l'historique des réservations.
     */
    public HistoriqueView(ReservationController reservationController, Client client) {
        this.reservationController = reservationController;
        this.clientId = (client == null) ? 0 : client.getIdClient(); // Initialiser l'ID du client
        initComponents(); // Initialiser les composants de l'interface graphique
        loadReservations(); // Charger les réservations du client
    }

    /**
     * Définit la vue de réservation pour permettre la navigation vers cette vue.
     *
     * @param reservationView La vue de réservation à afficher lors du retour.
     */
    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    /**
     * Initialise les composants de l'interface graphique pour afficher l'historique des réservations.
     * Configure les panneaux, les boutons et la disposition de la fenêtre.
     */
    private void initComponents() {
        setTitle("Historique des Réservations");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changer de EXIT_ON_CLOSE à DISPOSE_ON_CLOSE
        setLayout(new BorderLayout());

        // Panneau supérieur avec un bouton "Retour"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Masquer la fenêtre actuelle
                reservationView.setVisible(true); // Afficher la vue de réservation
            }
        });
        topPanel.add(retourButton);
        add(topPanel, BorderLayout.NORTH);

        // Panneau de gauche pour la liste des dates
        dateListModel = new DefaultListModel<>();
        dateList = new JList<>(dateListModel);
        dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Éviter les déclenchements multiples
                showSelectedDateReservations(); // Afficher les réservations pour la date sélectionnée
            }
        });

        JScrollPane dateScrollPane = new JScrollPane(dateList);
        dateScrollPane.setPreferredSize(new Dimension(200, 0));
        add(dateScrollPane, BorderLayout.WEST);

        // Panneau de droite pour afficher les détails de la réservation
        reservationDetailsPanel = new JPanel();
        reservationDetailsPanel.setLayout(new BorderLayout());

        reservationDetailsArea = new JTextArea();
        reservationDetailsArea.setEditable(false);
        reservationDetailsArea.setLineWrap(true);
        reservationDetailsArea.setWrapStyleWord(true);
        reservationDetailsPanel.add(new JScrollPane(reservationDetailsArea), BorderLayout.CENTER);

        add(reservationDetailsPanel, BorderLayout.CENTER);
    }

    /**
     * Charge toutes les réservations du client et les affiche par date.
     * Les réservations sont triées par ordre chronologique.
     */
    private void loadReservations() {
        try {
            List<Reservation> reservations = reservationController.obtenirToutesReservations();
            Map<Date, List<Reservation>> reservationsByDate = reservations.stream()
                    .collect(Collectors.groupingBy(reservation -> new Date(reservation.getDate_reservation().getTime())));

            // Trier les dates par ordre chronologique
            reservationsByDate.keySet().stream()
                    .sorted()
                    .forEach(dateListModel::addElement); // Ajouter les dates triées au modèle de liste
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement des réservations: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE); // Afficher un message d'erreur en cas d'exception
        }
    }

    /**
     * Affiche les détails des réservations pour la date sélectionnée dans la liste.
     * Les informations sont affichées dans une zone de texte.
     */
    private void showSelectedDateReservations() {
        Date selectedDate = dateList.getSelectedValue(); // Récupérer la date sélectionnée
        if (selectedDate != null) {
            try {
                List<Reservation> reservations = reservationController.obtenirReservationsParDateetClient(selectedDate, clientId);
                StringBuilder details = new StringBuilder();

                if (reservations.isEmpty()) {
                    details.append("Aucune réservation trouvée pour cette date.\n");
                } else {
                    for (Reservation reservation : reservations) {
                        // Ajouter les détails de chaque réservation
                        details.append("ID Réservation: ").append(reservation.getID_reservation()).append("\n");
                        details.append("Date Réservation: ").append(reservation.getDate_reservation()).append("\n");
                        details.append("Date Visite: ").append(reservation.getDate_visite()).append("\n");
                        details.append("Nombre d'adultes: ").append(reservation.getNb_adulte()).append("\n");
                        details.append("Nombre de seniors: ").append(reservation.getNb_senior()).append("\n");
                        details.append("Nombre d'enfants: ").append(reservation.getNb_enfant()).append("\n");
                        details.append("ID Client: ").append(reservation.getID_client()).append("\n");
                        details.append("ID Attraction: ").append(reservation.getID_attraction()).append("\n");
                        details.append("Payée: ").append(reservation.isPaye_reservation() ? "Oui" : "Non").append("\n");
                        details.append("-------------------------\n");
                    }
                }

                reservationDetailsArea.setText(details.toString()); // Afficher les détails dans la zone de texte
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erreur lors du chargement des réservations pour la date sélectionnée: " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE); // Afficher un message d'erreur en cas d'exception
            }
        }
    }
}
