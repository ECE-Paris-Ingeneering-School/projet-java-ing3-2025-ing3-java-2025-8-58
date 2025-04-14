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

    public AdminController(DaoFactory daoFactory) throws SQLException {
        this.attractionDAO = new AttractionDAO(daoFactory);
        this.clientDAO = new ClientDAO(daoFactory);
        this.reductionDAO = new ReductionDAO(daoFactory);
    }

    // Ajouter une nouvelle attraction
    public void ajouterAttraction(Attraction attraction) throws SQLException {
        attractionDAO.ajouterAttraction(attraction);
    }

    // Récupérer toutes les attractions
    public List<Attraction> obtenirToutesAttractions() throws SQLException {
        return attractionDAO.obtenirToutesAttractions();
    }

    // Mettre à jour une attraction
    public void mettreAJourAttraction(Attraction attraction) throws SQLException {
        attractionDAO.mettreAJourAttraction(attraction);
    }

    // Supprimer une attraction
    public void supprimerAttraction(int idAttraction) throws SQLException {
        attractionDAO.supprimerAttraction(idAttraction);
    }

    // Ajouter un nouveau client
    public void ajouterClient(Client client) throws SQLException {
        clientDAO.ajouterClient(client);
    }

    // Récupérer un client par son email
    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }

    // Mettre à jour un client
    public void mettreAJourClient(Client client) throws SQLException {
        clientDAO.mettreAJourClient(client);
    }

    // Supprimer un client
    public void supprimerClient(int idClient) throws SQLException {
        clientDAO.supprimerClient(idClient);
    }

    // Ajouter une nouvelle réduction
    public void ajouterReduction(Reduction reduction) throws SQLException {
        reductionDAO.ajouterReduction(reduction);
    }

    // Récupérer toutes les réductions
    public List<Reduction> obtenirToutesReductions() throws SQLException {
        return reductionDAO.obtenirToutesReductions();
    }

    // Mettre à jour une réduction
    public void mettreAJourReduction(Reduction reduction) throws SQLException {
        reductionDAO.mettreAJourReduction(reduction);
    }

    // Supprimer une réduction
    public void supprimerReduction(int idReduction) throws SQLException {
        reductionDAO.supprimerReduction(idReduction);
    }
}
