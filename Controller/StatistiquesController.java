package Controller;

import Model.StatistiquesModel;
import View.StatistiquesView;

public class StatistiquesController {

    private StatistiquesModel statistiquesModel;
    private StatistiquesView statistiquesView;

    public StatistiquesController() {
        // Crée une instance de StatistiquesModel
        statistiquesModel = new StatistiquesModel();

        // Crée une instance de StatistiquesView en lui passant l'instance de StatistiquesModel
        statistiquesView = new StatistiquesView(statistiquesModel);
    }

    public void afficherStatistiques() {
        statistiquesView.setVisible(true);
    }
}
