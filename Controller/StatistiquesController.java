package Controller;

import Model.StatistiquesModel;
import View.StatistiquesView;

import java.util.Map;

/**
 * Le contrôleur pour gérer les interactions entre le modèle de statistiques et la vue des statistiques.
 */
public class StatistiquesController {

    private StatistiquesModel statistiquesModel;
    private StatistiquesView statistiquesView;

    /**
     * Constructeur pour initialiser le contrôleur avec le modèle et la vue.
     *
     * @param statistiquesModel Le modèle des statistiques
     * @param statistiquesView  La vue des statistiques
     */
    public StatistiquesController(StatistiquesModel statistiquesModel, StatistiquesView statistiquesView) {
        this.statistiquesModel = statistiquesModel;
        this.statistiquesView = statistiquesView;
    }

    /**
     * Méthode pour récupérer les données de réservation par attraction et les passer à la vue pour affichage.
     */
    public void afficherStatistiques() {
        // Récupérer les données du modèle
        Map<String, Integer> data = statistiquesModel.getReservationsParAttraction();

        // Passer les données à la vue pour l'affichage
        statistiquesView.afficherGraphique(data);
    }
}
