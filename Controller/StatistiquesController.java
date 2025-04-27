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
     * Méthode pour récupérer les données et les passer à la vue pour affichage.
     */
    public void afficherStatistiques() {
        Map<String, Integer> dataAttractions = statistiquesModel.getReservationsParAttraction();
        Map<String, Integer> dataMois = statistiquesModel.getReservationsParMois(); // <<< ajoute cette méthode dans le Model

        statistiquesView.afficherStatistiques(dataAttractions, dataMois);
    }
}
