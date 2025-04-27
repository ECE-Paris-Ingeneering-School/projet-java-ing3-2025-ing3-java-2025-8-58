package View;

import Controller.AttractionController;
import Controller.ReductionController;
import Controller.ReservationController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe représentant la vue de réservation d'attractions.
 * Permet à l'utilisateur de voir la liste des attractions, leurs détails et de faire une réservation.
 */
public class ReservationView extends JFrame {

    private ReservationController reservationController;
    private AttractionController attractionController;
    private ReductionController reductionController;
    private LoginView loginView;
    private PanierView panierView;
    private HistoriqueView historiqueView;
    private Client client;
    private JList<Attraction> attractionList;
    private DefaultListModel<Attraction> attractionListModel;
    private JPanel attractionDetailsPanel;
    private JLabel attractionImageLabel;
    private JTextArea attractionDescriptionArea;
    private JButton reserveButton;
    private JButton panierButton;
    private JButton historiqueButton;
    private JButton retourConnexionButton;

    /**
     * Constructeur de la vue de réservation.
     *
     * @param attractionController Le contrôleur pour les attractions.
     * @param reservationController Le contrôleur pour les réservations.
     * @param reductionController Le contrôleur pour les réductions.
     */
    public ReservationView(AttractionController attractionController, ReservationController reservationController, ReductionController reductionController) {
        this.attractionController = attractionController;
        this.reservationController = reservationController;
        this.reductionController = reductionController;
        initComponents();
        loadAttractions();
    }

    /**
     * Définit la vue de l'historique des réservations.
     *
     * @param historiqueView La vue des réservations passées.
     */
    public void setHistoriqueView(HistoriqueView historiqueView) {
        this.historiqueView = historiqueView;
    }

    /**
     * Définit la vue de la connexion.
     *
     * @param loginView La vue de la connexion.
     */
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Définit le client connecté.
     *
     * @param client Le client connecté.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Initialise les composants graphiques de la vue.
     */
    private void initComponents() {
        setTitle("Réservation");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panneau supérieur avec les boutons
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        panierButton = new JButton("Panier");
        historiqueButton = new JButton("Historique des réservations");
        retourConnexionButton = new JButton("Retour");

        topPanel.add(retourConnexionButton);
        topPanel.add(historiqueButton);
        topPanel.add(panierButton);

        add(topPanel, BorderLayout.NORTH);

        // Panneau gauche pour la liste des attractions
        attractionListModel = new DefaultListModel<>();
        attractionList = new JList<>(attractionListModel);
        attractionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attractionList.addListSelectionListener(e -> showSelectedAttractionDetails());

        JScrollPane attractionScrollPane = new JScrollPane(attractionList);
        add(attractionScrollPane, BorderLayout.WEST);

        // Panneau droit pour afficher les détails de l'attraction
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
                ouvrirReserverView();
            }
        });
        attractionDetailsPanel.add(reserveButton, BorderLayout.SOUTH);

        add(attractionDetailsPanel, BorderLayout.CENTER);

        // Actions des boutons
        retourConnexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginView.setVisible(true);
            }
        });

        historiqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirHistoriqueView();
            }
        });

        panierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirPanierView();
            }
        });
    }

    /**
     * Charge la liste des attractions à partir du contrôleur.
     */
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

    /**
     * Affiche les détails de l'attraction sélectionnée.
     */
    private void showSelectedAttractionDetails() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction != null) {
            attractionImageLabel.setIcon(new ImageIcon(selectedAttraction.getCheminImageAttraction()));
            attractionDescriptionArea.setText("Prix : " + selectedAttraction.getPrixAttraction() + " €\n" +
                    "Description : " + selectedAttraction.getDescriptionAttraction());
        }
    }

    /**
     * Ouvre la vue de réservation pour l'attraction sélectionnée.
     */
    private void ouvrirReserverView() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction != null) {
            ReserverView reserverView = new ReserverView(reservationController, client, selectedAttraction.getIdAttraction());
            reserverView.setReservationView(this);
            reserverView.setVisible(true);
            this.setVisible(false); // Masquer la fenêtre actuelle
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une attraction.");
        }
    }

    /**
     * Ouvre la vue de l'historique des réservations.
     */
    private void ouvrirHistoriqueView() {
        historiqueView = new HistoriqueView(reservationController, client);
        historiqueView.setReservationView(this);
        setVisible(false);
        historiqueView.setVisible(true);
    }

    /**
     * Ouvre la vue du panier pour la réservation.
     */
    private void ouvrirPanierView() {
        PanierView panierView = new PanierView(reservationController, attractionController, reductionController, client);
        panierView.setReservationView(this);
        setVisible(false);
        panierView.setVisible(true);
    }
}
