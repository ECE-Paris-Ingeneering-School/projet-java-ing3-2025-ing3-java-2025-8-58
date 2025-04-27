package Controller;

import DAO.AttractionDAO;
import DAO.ClientDAO;
import DAO.DaoFactory;
import DAO.ReductionDAO;
import Model.Attraction;
import Model.Client;
import Model.Reduction;
import View.ModifierAttractionView;
import java.sql.SQLException;
import java.util.List;

public class AdminController {

    private AttractionDAO attractionDAO;
    private ClientDAO clientDAO;
    private ReductionDAO reductionDAO;

    /**
     * Constructeur du contrôleur administrateur
     * Initialise les DAO pour attractions, clients et réductions
     * @param daoFactory Fabrique de DAO
     */
    public AdminController(DaoFactory daoFactory) {
        this.attractionDAO = daoFactory.getAttractionDAO();
        this.clientDAO = daoFactory.getClientDAO();
        this.reductionDAO = daoFactory.getReductionDAO();
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

    /**
     * Ajoute un nouveau client
     * @param client Client à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterClient(Client client) throws SQLException {
        clientDAO.ajouterClient(client);
    }

    /**
     * Récupère un client par son email
     * @param email Email du client
     * @return Client correspondant ou null
     * @throws SQLException en cas d'erreur SQL
     */
    public Client obtenirClientParEmail(String email) throws SQLException {
        return clientDAO.obtenirClientParEmail(email);
    }

    /**
     * Met à jour un client existant
     * @param client Client à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourClient(Client client) throws SQLException {
        clientDAO.mettreAJourClient(client);
    }

    /**
     * Supprime un client par son ID
     * @param idClient ID du client à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerClient(int idClient) throws SQLException {
        clientDAO.supprimerClient(idClient);
    }

    /**
     * Ajoute une nouvelle réduction
     * @param reduction Réduction à ajouter
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterReduction(Reduction reduction) throws SQLException {
        reductionDAO.ajouterReduction(reduction);
    }

    /**
     * Récupère toutes les réductions
     * @return Liste des réductions
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Reduction> obtenirToutesReductions() throws SQLException {
        return reductionDAO.obtenirToutesReductions();
    }

    /**
     * Met à jour une réduction existante
     * @param reduction Réduction à mettre à jour
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourReduction(Reduction reduction) throws SQLException {
        reductionDAO.mettreAJourReduction(reduction);
    }

    /**
     * Supprime une réduction par son ID
     * @param idReduction ID de la réduction à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerReduction(int idReduction) throws SQLException {
        reductionDAO.supprimerReduction(idReduction);
    }

    /**
     * Ouvre la fenêtre de modification d'une attraction
     * @param attraction Attraction à modifier
     */
    public void ouvrirModifierAttractionView(Attraction attraction) {
        ModifierAttractionView modifyAttractionView = new ModifierAttractionView(this, attraction);
        modifyAttractionView.setVisible(true);
    }
}
