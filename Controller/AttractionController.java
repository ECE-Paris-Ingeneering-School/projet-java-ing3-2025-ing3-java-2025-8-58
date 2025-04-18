package Controller;

import java.sql.SQLException;
import java.util.List;

import DAO.*;
import Model.*;

public class AttractionController {
    
    private AttractionDAO attractionDAO;

    public AttractionController(DaoFactory daoFactory) {
        this.attractionDAO = daoFactory.getAttractionDAO();
    }

    public void ajouterAttraction(Attraction attraction) throws SQLException {
        attractionDAO.ajouterAttraction(attraction);
    }

    public List<Attraction> obtenirToutesAttractions() throws SQLException {
        return attractionDAO.obtenirToutesAttractions();
    }

    public void mettreAJourAttraction(Attraction attraction) throws SQLException {
        attractionDAO.mettreAJourAttraction(attraction);
    }

    public void supprimerAttraction(int idAttraction) throws SQLException {
        attractionDAO.supprimerAttraction(idAttraction);
    }
}
