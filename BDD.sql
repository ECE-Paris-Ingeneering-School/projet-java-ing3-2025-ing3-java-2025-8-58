-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : dim. 27 avr. 2025 à 18:03
-- Version du serveur : 8.0.40
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `attractions`
--

-- --------------------------------------------------------

--
-- Structure de la table `Administrateur`
--

CREATE TABLE `Administrateur` (
  `ID_admin` int NOT NULL,
  `mail_admin` varchar(50) NOT NULL,
  `mdp_admin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Administrateur`
--

INSERT INTO `Administrateur` (`ID_admin`, `mail_admin`, `mdp_admin`) VALUES
(1, 'admin@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `Ajouter_Supprimer`
--

CREATE TABLE `Ajouter_Supprimer` (
  `ID_admin` int NOT NULL,
  `ID_reduction` int NOT NULL,
  `ID_attraction` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Attraction`
--

CREATE TABLE `Attraction` (
  `ID_attraction` int NOT NULL,
  `nom_attraction` varchar(50) NOT NULL,
  `description_attraction` varchar(500) NOT NULL,
  `prix_attraction` float NOT NULL,
  `chemin_image_attraction` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Attraction`
--

INSERT INTO `Attraction` (`ID_attraction`, `nom_attraction`, `description_attraction`, `prix_attraction`, `chemin_image_attraction`) VALUES
(1, 'Oziris', 'Préparez-vous à vivre une montée d\'adrénaline sur notre Grand huit ! Entre descentes vertigineuses, virages serrés et loopings renversants, cette attraction promet des émotions fortes et des souvenirs inoubliables pour les amateurs de sensations extrêmes.', 21, 'images/oziris.jpg'),
(2, 'Belle vue', 'Offrez-vous un moment suspendu dans les airs avec la Grande roue. Prenez place dans une cabine et admirez un panorama exceptionnel à 360 degrés. De jour comme de nuit, c’est l’attraction idéale pour profiter d’une vue imprenable et magique.', 30, 'images/grande_roue.jpg'),
(3, 'Mouille qui peut', 'En quête de fraîcheur et d\'aventure ? Embarquez pour le Grand Splash ! Montez à bord d’une embarcation, grimpez au sommet... puis dévalez à toute vitesse pour un atterrissage spectaculaire dans l’eau. Rires et éclaboussures garantis !', 30, 'images/grand_splash.jpg'),
(5, 'L\'envol', 'Laissez-vous emporter par le vent à bord des Chaises volantes ! Confortablement installé, vous vous élèverez doucement avant de tournoyer dans les airs, porté par la brise. Une sensation de liberté et de légèreté à partager en famille ou entre amis !', 10, 'images/chaise_volante.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `Client`
--

CREATE TABLE `Client` (
  `ID_client` int NOT NULL,
  `mail_client` varchar(50) NOT NULL,
  `mdp_client` varchar(50) NOT NULL,
  `nom_client` varchar(50) NOT NULL,
  `prenom_client` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Client`
--

INSERT INTO `Client` (`ID_client`, `mail_client`, `mdp_client`, `nom_client`, `prenom_client`) VALUES
(0, '0', '0', '0', '0'),
(1, 'client@gmail.com', 'client', 'Clientnom', 'Clientprenom'),
(2, 'client2@gmail.com', 'client2', 'cli', 'ent2');

-- --------------------------------------------------------

--
-- Structure de la table `Reduction`
--

CREATE TABLE `Reduction` (
  `ID_reduction` int NOT NULL,
  `nom_reduction` varchar(50) NOT NULL,
  `pourcentage_reduction` varchar(50) NOT NULL,
  `type_reduction` int NOT NULL COMMENT 'type_reduction : 1 - client fréquent 2 - client enfant 3 - client sénior'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Reduction`
--

INSERT INTO `Reduction` (`ID_reduction`, `nom_reduction`, `pourcentage_reduction`, `type_reduction`) VALUES
(1, 'Jeune', '20%', 2);

-- --------------------------------------------------------

--
-- Structure de la table `Reservation`
--

CREATE TABLE `Reservation` (
  `ID_reservation` int NOT NULL,
  `date_reservation` date NOT NULL,
  `date_visite` date NOT NULL,
  `nb_adulte` int NOT NULL,
  `nb_senior` int NOT NULL,
  `nb_enfant` int NOT NULL,
  `ID_client` int NOT NULL,
  `ID_attraction` int NOT NULL,
  `paye_reservation` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Reservation`
--

INSERT INTO `Reservation` (`ID_reservation`, `date_reservation`, `date_visite`, `nb_adulte`, `nb_senior`, `nb_enfant`, `ID_client`, `ID_attraction`, `paye_reservation`) VALUES
(9, '2025-04-20', '2004-12-02', 1, 1, 1, 0, 1, 1),
(10, '2025-04-20', '2003-10-02', 1, 1, 1, 0, 1, 1),
(11, '2025-04-20', '2002-10-03', 1, 2, 1, 1, 1, 0),
(12, '2025-04-26', '2004-05-12', 1, 0, 0, 0, 2, 1),
(13, '2025-04-26', '2001-12-02', 1, 0, 0, 0, 3, 0),
(14, '2025-04-26', '2002-11-11', 1, 2, 1, 1, 1, 0),
(15, '2025-04-26', '2001-12-02', 1, 0, 1, 1, 5, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Administrateur`
--
ALTER TABLE `Administrateur`
  ADD PRIMARY KEY (`ID_admin`);

--
-- Index pour la table `Ajouter_Supprimer`
--
ALTER TABLE `Ajouter_Supprimer`
  ADD PRIMARY KEY (`ID_admin`,`ID_reduction`,`ID_attraction`),
  ADD KEY `Ajouter_Supprimer_Reduction0_FK` (`ID_reduction`),
  ADD KEY `Ajouter_Supprimer_Attraction1_FK` (`ID_attraction`);

--
-- Index pour la table `Attraction`
--
ALTER TABLE `Attraction`
  ADD PRIMARY KEY (`ID_attraction`);

--
-- Index pour la table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`ID_client`);

--
-- Index pour la table `Reduction`
--
ALTER TABLE `Reduction`
  ADD PRIMARY KEY (`ID_reduction`);

--
-- Index pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD PRIMARY KEY (`ID_reservation`),
  ADD KEY `Reservation_Client_FK` (`ID_client`),
  ADD KEY `Reservation_Attraction0_FK` (`ID_attraction`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Administrateur`
--
ALTER TABLE `Administrateur`
  MODIFY `ID_admin` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Attraction`
--
ALTER TABLE `Attraction`
  MODIFY `ID_attraction` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `Client`
--
ALTER TABLE `Client`
  MODIFY `ID_client` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `Reduction`
--
ALTER TABLE `Reduction`
  MODIFY `ID_reduction` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `ID_reservation` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Ajouter_Supprimer`
--
ALTER TABLE `Ajouter_Supprimer`
  ADD CONSTRAINT `Ajouter_Supprimer_Administrateur_FK` FOREIGN KEY (`ID_admin`) REFERENCES `Administrateur` (`ID_admin`),
  ADD CONSTRAINT `Ajouter_Supprimer_Attraction1_FK` FOREIGN KEY (`ID_attraction`) REFERENCES `Attraction` (`ID_attraction`),
  ADD CONSTRAINT `Ajouter_Supprimer_Reduction0_FK` FOREIGN KEY (`ID_reduction`) REFERENCES `Reduction` (`ID_reduction`);

--
-- Contraintes pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `Reservation_Attraction0_FK` FOREIGN KEY (`ID_attraction`) REFERENCES `Attraction` (`ID_attraction`),
  ADD CONSTRAINT `Reservation_Client_FK` FOREIGN KEY (`ID_client`) REFERENCES `Client` (`ID_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
