package View;

import Controller.ReservationController;
import Model.Client;
import Model.Reservation;

import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe représentant la vue de réservation d'une attraction.
 * Permet à l'utilisateur de saisir les informations de réservation pour une attraction.
 */
public class ReserverView extends JFrame {
    private ReservationView reservationView;
    private JTextField dateVisiteField;
    private JTextField nbAdulteField;
    private JTextField nbSeniorField;
    private JTextField nbEnfantField;
    private JButton retourButton;
    private JButton reserverButton;
    private ReservationController reservationController;
    private Client client;
    private int idAttraction;

    /**
     * Constructeur de la vue de réservation.
     *
     * @param reservationController Le contrôleur pour les réservations.
     * @param client Le client effectuant la réservation.
     * @param idAttraction L'ID de l'attraction à réserver.
     */
    public ReserverView(ReservationController reservationController, Client client, int idAttraction) {
        this.reservationController = reservationController;
        this.client = client;
        this.idAttraction = idAttraction;
        initComponents();
    }

    /**
     * Définit la vue de réservation d'attractions.
     *
     * @param reservationView La vue de réservation d'attractions.
     */
    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    /**
     * Initialise les composants graphiques de la vue.
     */
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

        // Bouton pour effectuer la réservation
        reserverButton = new JButton("Réserver");
        reserverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserver();
            }
        });

        // Bouton pour revenir à la vue de réservation précédente
        retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                reservationView.setVisible(true);
            }
        });

        // Ajout des composants au panneau
        panel.add(dateVisiteLabel);
        panel.add(dateVisiteField);
        panel.add(nbAdulteLabel);
        panel.add(nbAdulteField);
        panel.add(nbSeniorLabel);
        panel.add(nbSeniorField);
        panel.add(nbEnfantLabel);
        panel.add(nbEnfantField);
        panel.add(retourButton);
        panel.add(reserverButton);

        // Ajout du panneau à la fenêtre
        add(panel);
    }

    /**
     * Effectue la réservation en récupérant les informations saisies par l'utilisateur.
     *
     * Cette méthode crée une nouvelle réservation et l'ajoute via le contrôleur de réservation.
     * En cas d'erreur, elle affiche un message d'erreur.
     */
    private void reserver() {
        try {
            // Format de date attendu (YYYY-MM-DD)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateVisite = dateFormat.parse(dateVisiteField.getText());
            int nbAdulte = Integer.parseInt(nbAdulteField.getText());
            int nbSenior = Integer.parseInt(nbSeniorField.getText());
            int nbEnfant = Integer.parseInt(nbEnfantField.getText());

            // Création de la réservation
            Reservation reservation = new Reservation();

            // Définir la date actuelle pour la date de réservation
            Date currentDate = new Date();
            String formattedCurrentDate = dateFormat.format(currentDate);
            reservation.setDate_reservation(java.sql.Date.valueOf(formattedCurrentDate));

            // Définir la date de visite
            String formattedVisitDate = dateFormat.format(dateVisite);
            reservation.setDate_visite(java.sql.Date.valueOf(formattedVisitDate));

            // Définir le nombre d'adultes, seniors et enfants
            reservation.setNb_adulte(nbAdulte);
            reservation.setNb_senior(nbSenior);
            reservation.setNb_enfant(nbEnfant);

            // Définir l'ID de l'attraction et l'état de paiement
            reservation.setID_attraction(idAttraction);
            reservation.setPaye_reservation(false);

            // Assigner l'ID client (s'il existe)
            if (client != null) {
                reservation.setID_client(client.getIdClient());
            } else {
                reservation.setID_client(0);
            }

            // Afficher la réservation dans la console pour debug
            System.out.println(reservation.toString());

            // Ajouter la réservation via le contrôleur
            reservationController.ajouterReservation(reservation);
            JOptionPane.showMessageDialog(this, "Réservation effectuée avec succès!");

            // Fermer la vue actuelle et afficher la vue de réservation
            setVisible(false);
            reservationView.setVisible(true);
        } catch (ParseException | NumberFormatException ex) {
            // En cas d'erreur de formatage ou de conversion
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // En cas d'erreur lors de l'ajout de la réservation dans la base de données
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
