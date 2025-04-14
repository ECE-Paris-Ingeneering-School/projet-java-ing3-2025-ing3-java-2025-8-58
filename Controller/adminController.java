package Controller;

import DAO.AttractionDAO;
import DAO.ClientDAO;
import DAO.DaoFactory;
import DAO.ReductionDAO;
import Model.Attraction;
import Model.Client;
import Model.Reduction;

import java.sql.SQLException;
import java.util.List;

public class AdminController {

    private AttractionDAO attractionDAO;
    private ClientDAO clientDAO;
    private ReductionDAO reductionDAO;

    public AdminController(DaoFactory daoFactory) {
        this.attractionDAO = daoFactory.getAttractionDAO();
        this.clientDAO = daoFactory.getClientDAO();
        this.reductionDAO = daoFactory.getReductionDAO();
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

    public void ajouterClient(Client client) throws SQLException {
        clientDAO.ajouterClient(client);
    }

    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }

    public void mettreAJourClient(Client client) throws SQLException {
        clientDAO.mettreAJourClient(client);
    }

    public void supprimerClient(int idClient) throws SQLException {
        clientDAO.supprimerClient(idClient);
    }

    public void ajouterReduction(Reduction reduction) throws SQLException {
        reductionDAO.ajouterReduction(reduction);
    }

    public List<Reduction> obtenirToutesReductions() throws SQLException {
        return reductionDAO.obtenirToutesReductions();
    }

    public void mettreAJourReduction(Reduction reduction) throws SQLException {
        reductionDAO.mettreAJourReduction(reduction);
    }

    public void supprimerReduction(int idReduction) throws SQLException {
        reductionDAO.supprimerReduction(idReduction);
    }
}
