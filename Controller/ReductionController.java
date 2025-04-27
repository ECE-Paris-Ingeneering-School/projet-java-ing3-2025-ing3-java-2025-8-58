package Controller;

import DAO.DaoFactory;
import DAO.ReductionDAO;
import Model.Reduction;
import java.sql.SQLException;
import java.util.List;

public class ReductionController {
    private ReductionDAO reductionDAO;

    public ReductionController(DaoFactory daoFactory) {
        this.reductionDAO = new ReductionDAO(daoFactory);
    }

    /**
     * Ajoute une nouvelle réduction
     * @param nomReduction Nom de la réduction
     * @param pourcentageReduction Pourcentage de réduction
     * @param typeReduction Type de réduction
     * @throws SQLException en cas d'erreur SQL
     */
    public void ajouterReduction(String nomReduction, String pourcentageReduction, int typeReduction) throws SQLException {
        Reduction reduction = new Reduction();
        reduction.setNomReduction(nomReduction);
        reduction.setPourcentageReduction(pourcentageReduction);
        reduction.setTypeReduction(typeReduction);
        
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
     * @param idReduction ID de la réduction à mettre à jour
     * @param nomReduction Nouveau nom
     * @param pourcentageReduction Nouveau pourcentage
     * @param typeReduction Nouveau type
     * @throws SQLException en cas d'erreur SQL
     */
    public void mettreAJourReduction(int idReduction, String nomReduction, String pourcentageReduction, int typeReduction) throws SQLException {
        Reduction reduction = new Reduction();
        reduction.setIdReduction(idReduction);
        reduction.setNomReduction(nomReduction);
        reduction.setPourcentageReduction(pourcentageReduction);
        reduction.setTypeReduction(typeReduction);
        
        reductionDAO.mettreAJourReduction(reduction);
    }

    /**
     * Supprime une réduction
     * @param idReduction ID de la réduction à supprimer
     * @throws SQLException en cas d'erreur SQL
     */
    public void supprimerReduction(int idReduction) throws SQLException {
        reductionDAO.supprimerReduction(idReduction);
    }

    /**
     * Récupère une réduction par son ID
     * @param idReduction ID de la réduction
     * @return La réduction trouvée ou null
     * @throws SQLException en cas d'erreur SQL
     */
    public Reduction obtenirReductionParId(int idReduction) throws SQLException {
        List<Reduction> reductions = reductionDAO.obtenirToutesReductions();
        for (Reduction reduction : reductions) {
            if (reduction.getIdReduction() == idReduction) {
                return reduction;
            }
        }
        return null;
    }

    /**
     * Récupère une réduction par son type
     * @param typeReduction 1 client fréquent, 2 client enfant, 3 client 
     * @return La réduction trouvée ou null
     * @throws SQLException en cas d'erreur SQL
     */
    public Reduction obtenirReductionParType(int typeReduction) throws SQLException {
        List<Reduction> reductions = reductionDAO.obtenirToutesReductions();
        for (Reduction reduction : reductions) {
            if (reduction.getTypeReduction() == typeReduction) {
                return reduction;
            }
        }
        return null;
    }
}