package View;

import Controller.*;
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

public class HistoriqueView extends JFrame {

    private ReservationController reservationController;
    private JList<Date> dateList;
    private DefaultListModel<Date> dateListModel;
    private JPanel reservationDetailsPanel;
    private JTextArea reservationDetailsArea;

    public HistoriqueView(ReservationController reservationController) {
        this.reservationController = reservationController;
        initComponents();
        loadReservations();
    }

    private void initComponents() {
        setTitle("Historique des Réservations");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel for date list
        dateListModel = new DefaultListModel<>();
        dateList = new JList<>(dateListModel);
        dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateList.addListSelectionListener(e -> showSelectedDateReservations());

        JScrollPane dateScrollPane = new JScrollPane(dateList);
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
                    .collect(Collectors.groupingBy(Reservation::getDateReservation));

            reservationsByDate.keySet().forEach(dateListModel::addElement);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des réservations: " + ex.getMessage());
        }
    }

    private void showSelectedDateReservations() {
        Date selectedDate = dateList.getSelectedValue();
        if (selectedDate != null) {
            try {
                // Filtrer les réservations pour ne garder que celles qui correspondent à la date sélectionnée
                // .stream() : Convertit la liste en un flux (stream) pour permettre des opérations de traitement en chaîne
                // .filter(reservation -> reservation.getDateReservation().equals(selectedDate)) :
                //     Filtre les réservations pour ne garder que celles dont la date correspond à la date sélectionnée
                // .collect(Collectors.toList()) : Collecte les réservations filtrées dans une nouvelle liste
                List<Reservation> reservations = reservationController.obtenirToutesReservations().stream()
                        .filter(reservation -> reservation.getDateReservation().equals(selectedDate))
                        .collect(Collectors.toList());
    
                String details = "";
                for (Reservation reservation : reservations) {
                    details += "ID Réservation: " + reservation.getIdReservation() + "\n" +
                               "ID Client: " + reservation.getIdClient() + "\n" +
                               "ID Attraction: " + reservation.getIdAttraction() + "\n" +
                               "Nom Billet: " + reservation.getNomBillet() + "\n" +
                               "Prénom Billet: " + reservation.getPrenomBillet() + "\n" +
                               "Payé: " + (reservation.getPayeBillet() ? "Oui" : "Non") + "\n\n";
                }
                reservationDetailsArea.setText(details);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement des réservations pour la date sélectionnée: " + ex.getMessage());
            }
        }
    }
    
}
