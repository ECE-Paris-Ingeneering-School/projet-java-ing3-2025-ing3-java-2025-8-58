import Controller.StatistiquesController;

public class MainStatistiques {

    public static void main(String[] args) {
        // Initialiser le contrôleur des statistiques
        StatistiquesController statistiquesController = new StatistiquesController();

        // Afficher la vue des statistiques
        statistiquesController.afficherStatistiques();
    }
}
