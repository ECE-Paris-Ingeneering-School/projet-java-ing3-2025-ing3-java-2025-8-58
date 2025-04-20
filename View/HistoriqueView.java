package View;

import Controller.ReservationController;
import Model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HistoriqueView extends JFrame {

    private ReservationController reservationController;
    private ReservationView reservationView;
    private JList<Date> dateList;
    private DefaultListModel<Date> dateListModel;
    private JPanel reservationDetailsPanel;
    private JTextArea reservationDetailsArea;
    private JButton retourButton;

    public HistoriqueView(ReservationController reservationController) {
        this.reservationController = reservationController;
        initComponents();
        loadReservations();
    }

    public void setReservationView(ReservationView reservationView){
        this.reservationView = reservationView;
    }


    private void initComponents() {
        setTitle("Historique des Réservations");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changé de EXIT_ON_CLOSE à DISPOSE_ON_CLOSE
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                reservationView.setVisible(true);
            }
        });
        topPanel.add(retourButton);
        add(topPanel, BorderLayout.NORTH);

        // Left panel for date list
        dateListModel = new DefaultListModel<>();
        dateList = new JList<>(dateListModel);
        dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Pour éviter les déclenchements multiples
                showSelectedDateReservations();
            }
        });

        JScrollPane dateScrollPane = new JScrollPane(dateList);
        dateScrollPane.setPreferredSize(new Dimension(200, 0));
        add(dateScrollPane, BorderLayout.WEST);

        // Right panel for reservation details
        reservationDetailsPanel = new JPanel();
        reservationDetailsPanel.setLayout(new BorderLayout());

        reservationDetailsArea = new JTextArea();
        reservationDetailsArea.setEditable(false);
        reservationDetailsArea.setLineWrap(true);
        reservationDetailsArea.setWrapStyleWord(true);
        reservationDetailsPanel.add(new JScrollPane(reservationDetailsArea), BorderLayout.CENTER);

        add(reservationDetailsPanel, BorderLayout.CENTER);
    }

    private void loadReservations() {
        try {
            List<Reservation> reservations = reservationController.obtenirToutesReservations();
            Map<Date, List<Reservation>> reservationsByDate = reservations.stream()
                    .collect(Collectors.groupingBy(reservation -> new Date(reservation.getDate_reservation().getTime())));

            // Trier les dates par ordre chronologique
            reservationsByDate.keySet().stream()
                    .sorted()
                    .forEach(dateListModel::addElement);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement des réservations: " + ex.getMessage(),
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSelectedDateReservations() {
        Date selectedDate = dateList.getSelectedValue();
        if (selectedDate != null) {
            try {
                List<Reservation> reservations = reservationController.obtenirReservationsParDate(selectedDate);
                StringBuilder details = new StringBuilder();
                
                if (reservations.isEmpty()) {
                    details.append("Aucune réservation trouvée pour cette date.\n");
                } else {
                    for (Reservation reservation : reservations) {
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
                
                reservationDetailsArea.setText(details.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors du chargement des réservations pour la date sélectionnée: " + ex.getMessage(),
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}