package View;

import Controller.AdminController;
import Model.Attraction;
import Model.Client;
import Model.Reduction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Vue permettant la gestion des actions administratives telles que l'ajout et la modification des attractions,
 * l'ajout de clients et de réductions. Elle permet également de gérer les interactions avec le contrôleur.
 */
public class AdminView extends JFrame {

    private AdminController adminController;

    private JTextField nomAttractionField;
    private JTextField descriptionAttractionField;
    private JTextField prixAttractionField;
    private JTextField cheminImageAttractionField; // Champ pour le chemin de l'image
    private JTextField mailClientField;
    private JTextField mdpClientField;
    private JTextField nomClientField;
    private JTextField prenomClientField;
    private JTextField nomReductionField;
    private JTextField pourcentageReductionField;
    private JTextField typeReductionField;

    private LoginView loginView;

    /**
     * Constructeur de la vue administrateur.
     *
     * @param adminController Le contrôleur associé à cette vue pour gérer les actions administratives.
     */
    public AdminView(AdminController adminController) {
        this.adminController = adminController;
        initComponents();
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Initialise les composants de l'interface graphique pour la vue administrateur.
     * Crée et configure les panneaux et champs de saisie pour gérer les attractions, clients et réductions.
     */
    private void initComponents() {
        setTitle("Fenêtre administrateur");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Panneau pour les attractions
        JPanel attractionPanel = new JPanel();
        attractionPanel.setBorder(BorderFactory.createTitledBorder("Attraction"));
        attractionPanel.setLayout(new GridLayout(6, 2));

        JLabel nomAttractionLabel = new JLabel("Nom Attraction:");
        nomAttractionField = new JTextField();
        JLabel descriptionAttractionLabel = new JLabel("Description Attraction:");
        descriptionAttractionField = new JTextField();
        JLabel prixAttractionLabel = new JLabel("Prix Attraction:");
        prixAttractionField = new JTextField();
        JLabel cheminImageAttractionLabel = new JLabel("Chemin de l'image:");
        cheminImageAttractionField = new JTextField(); // Champ pour le chemin de l'image
        JButton ajouterAttractionButton = new JButton("Ajouter Attraction");
        JButton modifierAttractionButton = new JButton("Modifier Attraction"); // Bouton pour modifier une attraction

        attractionPanel.add(nomAttractionLabel);
        attractionPanel.add(nomAttractionField);
        attractionPanel.add(descriptionAttractionLabel);
        attractionPanel.add(descriptionAttractionField);
        attractionPanel.add(prixAttractionLabel);
        attractionPanel.add(prixAttractionField);
        attractionPanel.add(cheminImageAttractionLabel);
        attractionPanel.add(cheminImageAttractionField);
        attractionPanel.add(ajouterAttractionButton);
        attractionPanel.add(modifierAttractionButton);

        // Action pour ajouter une attraction
        ajouterAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterAttraction();
            }
        });

        // Action pour modifier une attraction
        modifierAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierAttraction();
            }
        });

        // Panneau pour les clients
        JPanel clientPanel = new JPanel();
        clientPanel.setBorder(BorderFactory.createTitledBorder("Client"));
        clientPanel.setLayout(new GridLayout(5, 2));

        JLabel mailClientLabel = new JLabel("Email Client:");
        mailClientField = new JTextField();
        JLabel mdpClientLabel = new JLabel("Mot de Passe Client:");
        mdpClientField = new JTextField();
        JLabel nomClientLabel = new JLabel("Nom Client:");
        nomClientField = new JTextField();
        JLabel prenomClientLabel = new JLabel("Prénom Client:");
        prenomClientField = new JTextField();
        JButton ajouterClientButton = new JButton("Ajouter Client");

        clientPanel.add(mailClientLabel);
        clientPanel.add(mailClientField);
        clientPanel.add(mdpClientLabel);
        clientPanel.add(mdpClientField);
        clientPanel.add(nomClientLabel);
        clientPanel.add(nomClientField);
        clientPanel.add(prenomClientLabel);
        clientPanel.add(prenomClientField);
        clientPanel.add(ajouterClientButton);

        // Action pour ajouter un client
        ajouterClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterClient();
            }
        });

        // Panneau pour les réductions
        JPanel reductionPanel = new JPanel();
        reductionPanel.setBorder(BorderFactory.createTitledBorder("Reduction"));
        reductionPanel.setLayout(new GridLayout(4, 2));

        JLabel nomReductionLabel = new JLabel("Nom Reduction:");
        nomReductionField = new JTextField();
        JLabel pourcentageReductionLabel = new JLabel("Pourcentage Reduction:");
        pourcentageReductionField = new JTextField();
        JLabel typeReductionLabel = new JLabel("Type Reduction:");
        typeReductionField = new JTextField();
        JButton ajouterReductionButton = new JButton("Ajouter Reduction");

        reductionPanel.add(nomReductionLabel);
        reductionPanel.add(nomReductionField);
        reductionPanel.add(pourcentageReductionLabel);
        reductionPanel.add(pourcentageReductionField);
        reductionPanel.add(typeReductionLabel);
        reductionPanel.add(typeReductionField);
        reductionPanel.add(ajouterReductionButton);

        // Action pour ajouter une réduction
        ajouterReductionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterReduction();
            }
        });

        add(attractionPanel);
        add(clientPanel);
        add(reductionPanel);


        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginView.setVisible(true);
            }
        });
        add(retourButton);
    }

    /**
     * Ajoute une nouvelle attraction en récupérant les informations saisies par l'administrateur.
     * En cas de succès, affiche un message de confirmation. En cas d'erreur, affiche un message d'erreur.
     */
    private void ajouterAttraction() {
        String nomAttraction = nomAttractionField.getText();
        String descriptionAttraction = descriptionAttractionField.getText();
        float prixAttraction = Float.parseFloat(prixAttractionField.getText());
        String cheminImageAttraction = cheminImageAttractionField.getText(); // Récupérer le chemin de l'image

        Attraction attraction = new Attraction(nomAttraction, descriptionAttraction, prixAttraction, cheminImageAttraction);
        try {
            adminController.ajouterAttraction(attraction);
            JOptionPane.showMessageDialog(this, "Attraction ajoutée avec succès!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'attraction: " + e.getMessage());
        }
    }

    /**
     * Permet de modifier une attraction existante. L'administrateur peut choisir l'attraction à modifier
     * via une liste déroulante. En cas d'erreur, un message est affiché.
     */
    private void modifierAttraction() {
        try {
            // Récupérer toutes les attractions
            java.util.List<Attraction> attractions = adminController.obtenirToutesAttractions();

            // Afficher une liste de choix pour sélectionner l'attraction à modifier
            String[] nomsAttractions = attractions.stream()
                    .map(Attraction::getNomAttraction)
                    .toArray(String[]::new);

            String selection = (String) JOptionPane.showInputDialog(
                    this,
                    "Choisissez une attraction à modifier :",
                    "Modifier Attraction",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nomsAttractions,
                    nomsAttractions.length > 0 ? nomsAttractions[0] : null
            );

            if (selection != null) {
                // Trouver l'attraction sélectionnée
                Attraction attractionSelectionnee = attractions.stream()
                        .filter(a -> a.getNomAttraction().equals(selection))
                        .findFirst()
                        .orElse(null);

                if (attractionSelectionnee != null) {
                    adminController.ouvrirModifierAttractionView(attractionSelectionnee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des attractions: " + e.getMessage());
        }
    }

    /**
     * Ajoute un client en récupérant les informations saisies dans les champs de texte.
     * En cas de succès, un message de confirmation est affiché. Sinon, une erreur est affichée.
     */
    private void ajouterClient() {
        String mailClient = mailClientField.getText();
        String mdpClient = mdpClientField.getText();
        String nomClient = nomClientField.getText();
        String prenomClient = prenomClientField.getText();

        Client client = new Client(mailClient, mdpClient, nomClient, prenomClient);
        try {
            adminController.ajouterClient(client);
            JOptionPane.showMessageDialog(this, "Client ajouté avec succès!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du client: " + e.getMessage());
        }
    }

    /**
     * Ajoute une réduction en récupérant les informations saisies dans les champs de texte.
     * En cas de succès, un message de confirmation est affiché. Sinon, une erreur est affichée.
     */
    private void ajouterReduction() {
        String nomReduction = nomReductionField.getText();
        String pourcentageReduction = pourcentageReductionField.getText();
        int typeReduction = Integer.parseInt(typeReductionField.getText());

        Reduction reduction = new Reduction(nomReduction, pourcentageReduction, typeReduction);
        try {
            adminController.ajouterReduction(reduction);
            JOptionPane.showMessageDialog(this, "Réduction ajoutée avec succès!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réduction: " + e.getMessage());
        }
    }
}
