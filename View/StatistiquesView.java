package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import Model.StatistiquesModel;  // Ajouter cette ligne en haut de ton fichier

import javax.swing.*;
import java.awt.*;

public class StatistiquesView extends JFrame {

    private StatistiquesModel statistiquesModel;

    public StatistiquesView(StatistiquesModel model) {
        this.statistiquesModel = model;
        initUI();
    }

    private void initUI() {
        setTitle("Statistiques des Réservations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Créer le graphique en camembert
        JFreeChart pieChart = createPieChart();
        ChartPanel pieChartPanel = new ChartPanel(pieChart);

        // Créer l'histogramme
        JFreeChart barChart = createBarChart();
        ChartPanel barChartPanel = new ChartPanel(barChart);

        // Ajouter les graphiques au panneau
        add(pieChartPanel);
        add(barChartPanel);

        setLocationRelativeTo(null);
    }

    /**
     * Crée un graphique en camembert (pie chart) pour afficher les réservations par attraction.
     */
    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // Récupérer les données
        var data = statistiquesModel.getReservationData();
        data.forEach(dataset::setValue);

        return ChartFactory.createPieChart(
                "Répartition des Réservations",  // Titre du graphique
                dataset,                       // Dataset (les données)
                true,                          // Légende
                true,                          // Info tool
                false                          // URL
        );
    }

    /**
     * Crée un graphique à barres (histogramme) pour afficher le nombre de réservations par attraction.
     */
    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Récupérer les données
        var data = statistiquesModel.getReservationData();
        data.forEach((attraction, count) ->
                dataset.addValue(count, "Réservations", attraction));

        return ChartFactory.createBarChart(
                "Réservations par Attraction", // Titre du graphique
                "Attractions",                 // Axe des X
                "Nombre de réservations",      // Axe des Y
                dataset,                       // Dataset
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,                          // Légende
                true,                          // Info tool
                false                          // URL
        );
    }
}
