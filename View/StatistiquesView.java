package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * La vue pour afficher les statistiques sous forme de graphiques.
 * Elle permet d'afficher à la fois :
 * - un camembert du nombre de réservations par attraction,
 * - un histogramme du nombre de réservations par mois (selon la date de visite).
 */
public class StatistiquesView extends JFrame {

    private JPanel mainPanel; // Panneau principal qui contiendra les graphiques

    /**
     * Constructeur qui initialise la fenêtre et son contenu.
     */
    public StatistiquesView() {
        setTitle("Statistiques des Réservations");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // 1 ligne, 2 colonnes pour afficher 2 graphiques côte à côte
        setContentPane(mainPanel);
    }

    /**
     * Affiche les graphiques de statistiques dans la fenêtre :
     * - un camembert pour les réservations par attraction
     * - un histogramme pour les réservations par mois
     *
     * @param reservationsParAttraction Les données de réservation par attraction (nom -> nombre de réservations)
     * @param reservationsParMois       Les données de réservation par mois (nom du mois -> nombre de réservations)
     */
    public void afficherStatistiques(Map<String, Integer> reservationsParAttraction, Map<String, Integer> reservationsParMois) {
        mainPanel.removeAll(); // Nettoyer les anciens graphiques

        // --- Création du camembert ---
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : reservationsParAttraction.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Réservations par Attraction",
                pieDataset,
                true,
                true,
                false
        );
        ChartPanel pieChartPanel = new ChartPanel(pieChart);

        // --- Création de l'histogramme ---
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : reservationsParMois.entrySet()) {
            barDataset.addValue(entry.getValue(), "Réservations", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Réservations par Mois",
                "Mois",
                "Nombre de Réservations",
                barDataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );
        ChartPanel barChartPanel = new ChartPanel(barChart);

        // --- Ajout des deux graphiques au panneau principal ---
        mainPanel.add(pieChartPanel);
        mainPanel.add(barChartPanel);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
