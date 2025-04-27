package Model;

import DAO.DaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;


/**
 * La classe StatistiquesModel est responsable de la récupération des données de la base de données
 * pour être utilisées dans les graphiques statistiques.
 */
public class StatistiquesModel {

    private DaoFactory daoFactory;

    // Constructeur pour initialiser la connexion à la base de données via DaoFactory
    public StatistiquesModel(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Récupère le nombre de réservations par attraction.
     *
     * @return Une carte contenant les noms des attractions et le nombre de réservations correspondantes.
     */
    public Map<String, Integer> getReservationsParAttraction() {
        Map<String, Integer> data = new HashMap<>();
        String query = "SELECT a.nom_attraction, COUNT(r.ID_reservation) AS nb_reservations " +
                "FROM attraction a " +
                "LEFT JOIN reservation r ON a.ID_attraction = r.ID_attraction " +
                "GROUP BY a.ID_attraction";

        // Connexion à la base de données et exécution de la requête
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Traitement des résultats de la requête
            while (rs.next()) {
                String attractionName = rs.getString("nom_attraction");
                int numReservations = rs.getInt("nb_reservations");
                data.put(attractionName, numReservations);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Récupère le nombre de réservations par mois de visite.
     *
     * @return Une carte contenant les mois (ex: Avril) et le nombre de réservations.
     */
    public Map<String, Integer> getReservationsParMois() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String query = "SELECT MONTH(date_visite) AS mois, COUNT(*) AS nb " +
                "FROM reservation " +
                "GROUP BY mois " +
                "ORDER BY mois ASC";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            String[] moisNoms = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                    "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};

            while (rs.next()) {
                int mois = rs.getInt("mois");
                int nb = rs.getInt("nb");
                String moisNom = moisNoms[mois - 1];
                data.put(moisNom, nb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }



    // Autres méthodes de statistiques peuvent être ajoutées ici
}
