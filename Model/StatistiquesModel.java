package Model;

import java.util.Map;

public class StatistiquesModel {

    /**
     * Méthode qui simule l'obtention des données statistiques pour les graphes.
     *
     * @return Une map représentant les données des statistiques (par exemple, nombre de réservations).
     */
    public Map<String, Integer> getReservationData() {
        // Retourne des données simulées
        return Map.of(
                "Attraction A", 20,
                "Attraction B", 30,
                "Attraction C", 10,
                "Attraction D", 40
        );
    }
}
