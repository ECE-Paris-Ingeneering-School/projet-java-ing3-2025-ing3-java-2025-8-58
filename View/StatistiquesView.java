package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Map;

/**
 * La vue pour afficher les statistiques sous forme de graphiques.
 */
public class StatistiquesView extends JFrame {

    public StatistiquesView() {
        // Configuration de la fenêtre
        setTitle("Statistiques des Réservations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Affiche le graphique avec les données de réservation par attraction.
     *
     * @param data Les données sous forme de Map (nom d'attraction, nombre de réservations)
     */
    public void afficherGraphique(Map<String, Integer> data) {
        // Créer le dataset pour le graphique
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Créer un graphique circulaire (camembert) à partir du dataset
        JFreeChart chart = ChartFactory.createPieChart(
                "Réservations par Attraction", // Titre
                dataset,                      // Dataset
                true,                          // Légende
                true,                          // Tooltip
                false                          // URL
        );

        // Ajouter le graphique à la fenêtre
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }
}
