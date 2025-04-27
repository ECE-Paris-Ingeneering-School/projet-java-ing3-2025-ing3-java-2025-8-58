package Controller;

import java.sql.SQLException;
import java.util.List;

import DAO.*;
import Model.*;

public class AttractionController {

    private AttractionDAO attractionDAO;

    /**
     * Constructeur du contrôleur d'attraction
     * Initialise le DAO pour les attractions
     * @param daoFactory Fabrique de DAO
     */
    public AttractionController(DaoFactory daoFactory) {
        this.attractionDAO = daoFactory.getAttractionDAO();
    }

    /**
     * Ajoute une nouvelle attraction
     * @param attraction Attraction à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterAttraction(Attraction attraction) throws SQLException {
        attractionDAO.ajouterAttraction(attraction);
    }

    /**
     * Récupère toutes les attractions
     * @return Liste des attractions
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Attraction> obtenirToutesAttractions() throws SQLException {
        return attractionDAO.obtenirToutesAttractions();
    }

    /**
     * Met à jour une attraction existante
     * @param attraction Attraction à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourAttraction(Attraction attraction) throws SQLException {
        attractionDAO.mettreAJourAttraction(attraction);
    }

    /**
     * Supprime une attraction par son ID
     * @param idAttraction ID de l'attraction à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerAttraction(int idAttraction) throws SQLException {
        attractionDAO.supprimerAttraction(idAttraction);
    }
}
