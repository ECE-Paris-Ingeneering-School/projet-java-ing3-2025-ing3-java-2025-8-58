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

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Fenêtre administrateur");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Attraction Panel
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

        ajouterAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterAttraction();
            }
        });

        modifierAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierAttraction();
            }
        });

        // Client Panel
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

        ajouterClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterClient();
            }
        });

        // Reduction Panel
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

        ajouterReductionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterReduction();
            }
        });

        add(attractionPanel);
        add(clientPanel);
        add(reductionPanel);
    }

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

    private void modifierAttraction() {

    }

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
