import Controller.AdminController;
import Controller.LoginController;
import Controller.ReservationController;
import DAO.DaoFactory;
import Model.Attraction;
import Model.Client;
import Model.Reduction;
import Model.Reservation;

import java.sql.SQLException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance("attractions", "root", "root");

            // Initialisation des contrôleurs
            AdminController adminController = new AdminController(daoFactory);
            LoginController loginController = new LoginController(daoFactory);
            ReservationController reservationController = new ReservationController(daoFactory);

            // Exemple d'utilisation des contrôleurs

            // Ajouter une attraction
            Attraction attraction = new Attraction();
            attraction.setNomAttraction("Montagnes Russes");
            attraction.setDescriptionAttraction("Attraction à sensation forte");
            attraction.setPrixAttraction(15.0f);
            adminController.ajouterAttraction(attraction);

            // Ajouter un client
            Client client = new Client();
            client.setMailClient("client@example.com");
            client.setMdpClient("motdepasse");
            client.setNomClient("Dupont");
            client.setPrenomClient("Jean");
            adminController.ajouterClient(client);

            // Ajouter une réduction
            Reduction reduction = new Reduction();
            reduction.setNomReduction("Réduction Enfant");
            reduction.setPourcentageReduction("20%");
            reduction.setTypeReduction(2);
            adminController.ajouterReduction(reduction);

            // Ajouter une réservation
            Reservation reservation = new Reservation();
            reservation.setDateReservation(Date.valueOf("2023-10-15"));
            reservation.setIdClient(1);
            reservation.setIdAttraction(1);
            reservation.setIdClientReserve(1);
            reservation.setNomBillet("Dupont");
            reservation.setPrenomBillet("Jean");
            reservationController.ajouterReservation(reservation);

            // Vérifier la connexion d'un client
            boolean isConnected = loginController.verifierConnexion("client@example.com", "motdepasse");
            System.out.println("Connexion réussie : " + isConnected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
