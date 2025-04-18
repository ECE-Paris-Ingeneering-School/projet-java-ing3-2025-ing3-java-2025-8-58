-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : ven. 18 avr. 2025 à 16:16
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
  `ID_attraction` int NOT NULL,
  `ID_reduction` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Attraction`
--

CREATE TABLE `Attraction` (
  `ID_attraction` int NOT NULL,
  `nom_attraction` varchar(50) NOT NULL,
  `description_attraction` varchar(50) NOT NULL,
  `prix_attraction` float NOT NULL,
  `chemin_image_attraction` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Attraction`
--

INSERT INTO `Attraction` (`ID_attraction`, `nom_attraction`, `description_attraction`, `prix_attraction`, `chemin_image_attraction`) VALUES
(1, 'Oziris', 'Grand huit', 21, 'images/oziris.jpg'),
(2, 'Belle vue', 'Grande_roue', 30, 'images/grande_roue.jpg'),
(3, 'Mouille qui peut', 'Grand Splash', 30, 'images/grand_splash.jpg'),
(5, 'L\'envol', 'Chaises volantes', 10, 'images/chaise_volante.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `Billet`
--

CREATE TABLE `Billet` (
  `nom_billet` varchar(50) NOT NULL,
  `prenom_billet` varchar(50) NOT NULL,
  `date_billet` int NOT NULL,
  `date_naissance_billet` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(1, 'client@gmail.com', 'client', 'Clientnom', 'Clientprenom'),
(2, 'client2@gmail.com', 'client2', 'cli', 'ent2');

-- --------------------------------------------------------

--
-- Structure de la table `Propose`
--

CREATE TABLE `Propose` (
  `ID_attraction` int NOT NULL,
  `ID_reservation` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

-- --------------------------------------------------------

--
-- Structure de la table `Reservation`
--

CREATE TABLE `Reservation` (
  `ID_reservation` int NOT NULL,
  `date_reservation` date NOT NULL,
  `ID_client` int NOT NULL,
  `ID_attraction` int NOT NULL,
  `ID_client_Reserve` int NOT NULL,
  `nom_billet` varchar(50) NOT NULL,
  `prenom_billet` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  ADD PRIMARY KEY (`ID_admin`,`ID_attraction`,`ID_reduction`),
  ADD KEY `Ajouter_Supprimer_Attraction0_FK` (`ID_attraction`),
  ADD KEY `Ajouter_Supprimer_Reduction1_FK` (`ID_reduction`);

--
-- Index pour la table `Attraction`
--
ALTER TABLE `Attraction`
  ADD PRIMARY KEY (`ID_attraction`);

--
-- Index pour la table `Billet`
--
ALTER TABLE `Billet`
  ADD PRIMARY KEY (`nom_billet`,`prenom_billet`);

--
-- Index pour la table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`ID_client`);

--
-- Index pour la table `Propose`
--
ALTER TABLE `Propose`
  ADD PRIMARY KEY (`ID_attraction`,`ID_reservation`),
  ADD KEY `Propose_Reservation0_FK` (`ID_reservation`);

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
  ADD UNIQUE KEY `Reservation_AK` (`ID_client`,`ID_attraction`),
  ADD KEY `Reservation_Client_FK` (`ID_client_Reserve`),
  ADD KEY `Reservation_Billet0_FK` (`nom_billet`,`prenom_billet`);

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
  MODIFY `ID_client` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `Reduction`
--
ALTER TABLE `Reduction`
  MODIFY `ID_reduction` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `ID_reservation` int NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Ajouter_Supprimer`
--
ALTER TABLE `Ajouter_Supprimer`
  ADD CONSTRAINT `Ajouter_Supprimer_Administrateur_FK` FOREIGN KEY (`ID_admin`) REFERENCES `Administrateur` (`ID_admin`),
  ADD CONSTRAINT `Ajouter_Supprimer_Attraction0_FK` FOREIGN KEY (`ID_attraction`) REFERENCES `Attraction` (`ID_attraction`),
  ADD CONSTRAINT `Ajouter_Supprimer_Reduction1_FK` FOREIGN KEY (`ID_reduction`) REFERENCES `Reduction` (`ID_reduction`);

--
-- Contraintes pour la table `Propose`
--
ALTER TABLE `Propose`
  ADD CONSTRAINT `Propose_Attraction_FK` FOREIGN KEY (`ID_attraction`) REFERENCES `Attraction` (`ID_attraction`),
  ADD CONSTRAINT `Propose_Reservation0_FK` FOREIGN KEY (`ID_reservation`) REFERENCES `Reservation` (`ID_reservation`);

--
-- Contraintes pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `Reservation_Billet0_FK` FOREIGN KEY (`nom_billet`,`prenom_billet`) REFERENCES `Billet` (`nom_billet`, `prenom_billet`),
  ADD CONSTRAINT `Reservation_Client_FK` FOREIGN KEY (`ID_client_Reserve`) REFERENCES `Client` (`ID_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
