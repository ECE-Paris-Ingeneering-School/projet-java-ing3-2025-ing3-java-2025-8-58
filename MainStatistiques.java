import DAO.DaoFactory;
import Model.StatistiquesModel;
import View.StatistiquesView;
import Controller.StatistiquesController;

import javax.swing.*;

/**
 * Classe principale de l'application.
 * Cette classe initialise les contrôleurs, les vues, et établit la connexion à la base de données,
 * puis lance l'interface utilisateur.
 */
public class MainStatistiques {

    /**
     * Point d'entrée principal de l'application.
     * Cette méthode initialise la connexion à la base de données, configure les contrôleurs et les vues,
     * et affiche la fenêtre de statistiques.
     *
     * @param args Arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        try {
            // Initialisation de la connexion à la base de données
            DaoFactory daoFactory = DaoFactory.getInstance("attractions", "root", "root");

            // Initialisation du modèle de statistiques avec la connexion
            StatistiquesModel statistiquesModel = new StatistiquesModel(daoFactory);

            // Initialisation de la vue des statistiques
            StatistiquesView statistiquesView = new StatistiquesView();

            // Initialisation du contrôleur des statistiques
            StatistiquesController statistiquesController = new StatistiquesController(statistiquesModel, statistiquesView);

            // Afficher les statistiques
            statistiquesController.afficherStatistiques();

            // Afficher la vue des statistiques
            statistiquesView.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
