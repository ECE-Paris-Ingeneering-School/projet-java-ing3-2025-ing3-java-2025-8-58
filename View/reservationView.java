package View;

import Controller.AttractionController;
import Controller.ReductionController;
import Controller.ReservationController;
import Model.*;

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


    public ReservationView(AttractionController attractionController, ReservationController reservationController, ReductionController reductionController) {
        this.attractionController = attractionController;
        this.reservationController = reservationController;
        this.reductionController = reductionController;
        initComponents();
        loadAttractions();

    }

    public void setHistoriqueView(HistoriqueView historiqueView){
        this.historiqueView = historiqueView;
    }

    public void setLoginView(LoginView loginView){
        this.loginView = loginView;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private void initComponents() {
        setTitle("Réservation");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        panierButton = new JButton("Panier");
        historiqueButton = new JButton("Historique des réservations");
        retourConnexionButton = new JButton("Retour");

        topPanel.add(retourConnexionButton);
        topPanel.add(historiqueButton);
        topPanel.add(panierButton);

        add(topPanel, BorderLayout.NORTH);

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
                HistoriqueView historiqueView = new HistoriqueView(reservationController);
                setVisible(false);
                historiqueView.setVisible(true);
            }
        });
        panierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanierView panierView = new PanierView(reservationController, attractionController, reductionController, client);
                setVisible(false);
                panierView.setVisible(true);
            }
        });

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
            attractionImageLabel.setIcon(new ImageIcon(selectedAttraction.getCheminImageAttraction()));
            attractionDescriptionArea.setText(selectedAttraction.getDescriptionAttraction());
        }
    }

    private void ouvrirPanierView() {
        Attraction selectedAttraction = attractionList.getSelectedValue();
        if (selectedAttraction != null) {
            ReserverView reserverView = new ReserverView(reservationController, client, selectedAttraction.getIdAttraction());
            reserverView.setVisible(true);
            this.setVisible(false); // Masquer la fenêtre actuelle
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une attraction.");
        }
    }
}
