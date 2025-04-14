import DAO.AttractionDAO;
import DAO.ClientDAO;
import DAO.DaoFactory;
import DAO.ReductionDAO;
import DAO.ReservationDAO;
import Model.Attraction;
import Model.Client;
import Model.Reduction;
import Model.Reservation;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance("achats", "root", "root");

            // Exemple d'utilisation des DAO
            AttractionDAO attractionDAO = new AttractionDAO(daoFactory);
            ClientDAO clientDAO = new ClientDAO(daoFactory);
            ReductionDAO reductionDAO = new ReductionDAO(daoFactory);
            ReservationDAO reservationDAO = new ReservationDAO(daoFactory);

            // Ajouter une attraction
            Attraction attraction = new Attraction();
            attraction.setNomAttraction("Montagnes Russes");
            attraction.setDescriptionAttraction("Attraction à sensation forte");
            attraction.setPrixAttraction(15.0f);
            attractionDAO.ajouterAttraction(attraction);

            // Ajouter un client
            Client client = new Client();
            client.setMailClient("client@example.com");
            client.setMdpClient("motdepasse");
            client.setNomClient("Dupont");
            client.setPrenomClient("Jean");
            clientDAO.ajouterClient(client);

            // Ajouter une réduction
            Reduction reduction = new Reduction();
            reduction.setNomReduction("Réduction Enfant");
            reduction.setPourcentageReduction("20%");
            reduction.setTypeReduction(2);
            reductionDAO.ajouterReduction(reduction);

            // Ajouter une réservation
            Reservation reservation = new Reservation();
            reservation.setDateReservation(java.sql.Date.valueOf("2023-10-15"));
            reservation.setIdClient(1);
            reservation.setIdAttraction(1);
            reservation.setIdClientReserve(1);
            reservation.setNomBillet("Dupont");
            reservation.setPrenomBillet("Jean");
            reservationDAO.ajouterReservation(reservation);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
