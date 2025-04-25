package View;

import Controller.AdminController;
import Model.Attraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifierAttractionView extends JFrame {

    private AdminController adminController;
    private Attraction attraction;

    private JTextField nomAttractionField;
    private JTextField descriptionAttractionField;
    private JTextField prixAttractionField;
    private JTextField cheminImageAttractionField; // Champ pour le chemin de l'image

    public ModifierAttractionView(AdminController adminController, Attraction attraction) {
        this.adminController = adminController;
        this.attraction = attraction;
        initComponents();
    }

    private void initComponents() {
        setTitle("Modifier Attraction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel nomAttractionLabel = new JLabel("Nom Attraction:");
        nomAttractionField = new JTextField(attraction.getNomAttraction());
        JLabel descriptionAttractionLabel = new JLabel("Description Attraction:");
        descriptionAttractionField = new JTextField(attraction.getDescriptionAttraction());
        JLabel prixAttractionLabel = new JLabel("Prix Attraction:");
        prixAttractionField = new JTextField(String.valueOf(attraction.getPrixAttraction()));
        JLabel cheminImageAttractionLabel = new JLabel("Chemin de l'image:");
        cheminImageAttractionField = new JTextField(attraction.getCheminImageAttraction()); // Champ pour le chemin de l'image
        JButton enregistrerButton = new JButton("Enregistrer");

        add(nomAttractionLabel);
        add(nomAttractionField);
        add(descriptionAttractionLabel);
        add(descriptionAttractionField);
        add(prixAttractionLabel);
        add(prixAttractionField);
        add(cheminImageAttractionLabel);
        add(cheminImageAttractionField);
        add(enregistrerButton);

        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminController.mettreAJourAttraction(attraction);
                    JOptionPane.showMessageDialog(null, "Attraction modifiée avec succès!");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'attraction: " + ex.getMessage());
                }
            }
        });
    }
}
