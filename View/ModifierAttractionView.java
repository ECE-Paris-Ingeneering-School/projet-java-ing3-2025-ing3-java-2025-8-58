package View;

import Controller.AdminController;
import Model.Attraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Cette classe représente la vue permettant à un administrateur de modifier les informations d'une attraction existante.
 * Elle permet de modifier le nom, la description, le prix et l'image de l'attraction.
 *
 * @author [Votre Nom]
 */
public class ModifierAttractionView extends JFrame {

    private AdminController adminController; // Le contrôleur qui gère la logique des administrateurs
    private Attraction attraction; // L'attraction à modifier

    private JTextField nomAttractionField; // Champ pour le nom de l'attraction
    private JTextField descriptionAttractionField; // Champ pour la description de l'attraction
    private JTextField prixAttractionField; // Champ pour le prix de l'attraction
    private JTextField cheminImageAttractionField; // Champ pour le chemin de l'image de l'attraction

    /**
     * Constructeur de la vue permettant de modifier une attraction.
     * Initialise les composants graphiques et pré-remplit les champs avec les données de l'attraction.
     *
     * @param adminController Le contrôleur gérant les actions de l'administrateur
     * @param attraction L'attraction à modifier
     */
    public ModifierAttractionView(AdminController adminController, Attraction attraction) {
        this.adminController = adminController;
        this.attraction = attraction;
        initComponents(); // Initialisation des composants graphiques
    }

    /**
     * Initialise les composants graphiques de la vue, y compris les champs de texte pour modifier l'attraction
     * et le bouton pour enregistrer les modifications.
     */
    private void initComponents() {
        setTitle("Modifier Attraction"); // Titre de la fenêtre
        setSize(400, 300); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Comportement de la fenêtre à la fermeture
        setLayout(new GridLayout(5, 2)); // Disposition des composants dans une grille de 5 lignes et 2 colonnes

        // Création des étiquettes et des champs de saisie pré-remplis avec les données existantes de l'attraction
        JLabel nomAttractionLabel = new JLabel("Nom Attraction:");
        nomAttractionField = new JTextField(attraction.getNomAttraction());
        JLabel descriptionAttractionLabel = new JLabel("Description Attraction:");
        descriptionAttractionField = new JTextField(attraction.getDescriptionAttraction());
        JLabel prixAttractionLabel = new JLabel("Prix Attraction:");
        prixAttractionField = new JTextField(String.valueOf(attraction.getPrixAttraction()));
        JLabel cheminImageAttractionLabel = new JLabel("Chemin de l'image:");
        cheminImageAttractionField = new JTextField(attraction.getCheminImageAttraction()); // Champ pour le chemin de l'image
        JButton enregistrerButton = new JButton("Enregistrer"); // Bouton pour enregistrer les modifications

        // Ajout des composants à la fenêtre
        add(nomAttractionLabel);
        add(nomAttractionField);
        add(descriptionAttractionLabel);
        add(descriptionAttractionField);
        add(prixAttractionLabel);
        add(prixAttractionField);
        add(cheminImageAttractionLabel);
        add(cheminImageAttractionField);
        add(enregistrerButton);

        // Définition de l'action à exécuter lors du clic sur le bouton "Enregistrer"
        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mise à jour des informations de l'attraction via le contrôleur
                    adminController.mettreAJourAttraction(attraction);
                    JOptionPane.showMessageDialog(null, "Attraction modifiée avec succès!"); // Afficher un message de succès
                    dispose(); // Fermer la fenêtre après l'enregistrement
                } catch (SQLException ex) {
                    // Afficher un message d'erreur en cas de problème lors de la mise à jour
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'attraction: " + ex.getMessage());
                }
            }
        });
    }
}
