-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 26 mars 2021 à 13:25
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ppebiorelai`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherent`
--

CREATE TABLE `adherent` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `MAIL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `adherent`
--

INSERT INTO `adherent` (`IDUTILISATEUR`, `MAIL`) VALUES
(1, 'thibaultheyt@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `IDCATEGORIE` int(11) NOT NULL,
  `NOMCATEGORIE` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`IDCATEGORIE`, `NOMCATEGORIE`) VALUES
(1, 'Fruits'),
(2, 'Légumes'),
(3, 'Viandes'),
(4, 'Boissons'),
(5, 'Céréales'),
(6, 'Poissons');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `IDCOMMANDE` int(11) NOT NULL,
  `IDVENTE` int(11) NOT NULL,
  `IDETAT` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) NOT NULL,
  `DATECOMMANDE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`IDCOMMANDE`, `IDVENTE`, `IDETAT`, `IDUTILISATEUR`, `DATECOMMANDE`) VALUES
(1, 1, 4, 1, '2021-03-23');

-- --------------------------------------------------------

--
-- Structure de la table `etat`
--

CREATE TABLE `etat` (
  `IDETAT` int(11) NOT NULL,
  `LIBELLEETAT` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etat`
--

INSERT INTO `etat` (`IDETAT`, `LIBELLEETAT`) VALUES
(1, 'Commande validée'),
(2, 'Partiellement récupérée'),
(3, 'Non récupérée'),
(4, 'En cours'),
(5, 'Client absent'),
(6, 'Producteur absent'),
(7, 'En attente de validation');

-- --------------------------------------------------------

--
-- Structure de la table `lignescommande`
--

CREATE TABLE `lignescommande` (
  `IDCOMMANDE` int(11) NOT NULL,
  `IDPRODUIT` int(11) NOT NULL,
  `QUANTITE` int(10) NOT NULL,
  `QUANTITE_LIVREE` int(10) NOT NULL,
  `QUANTITE_RECUP` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lignescommande`
--

INSERT INTO `lignescommande` (`IDCOMMANDE`, `IDPRODUIT`, `QUANTITE`, `QUANTITE_LIVREE`, `QUANTITE_RECUP`) VALUES
(1, 3, 20, 10, 2);

-- --------------------------------------------------------

--
-- Structure de la table `producteur`
--

CREATE TABLE `producteur` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `ADRESSEPRODUCTEUR` varchar(50) NOT NULL,
  `COMMUNEPRODUCTEUR` varchar(40) NOT NULL,
  `CODEPOSTALPRODUCTEUR` varchar(5) NOT NULL,
  `DESCRIPTIFPRODUCTEUR` text NOT NULL,
  `PHOTOPRODUCTEUR` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `producteur`
--

INSERT INTO `producteur` (`IDUTILISATEUR`, `ADRESSEPRODUCTEUR`, `COMMUNEPRODUCTEUR`, `CODEPOSTALPRODUCTEUR`, `DESCRIPTIFPRODUCTEUR`, `PHOTOPRODUCTEUR`) VALUES
(2, '19 chemin de Gamarde', 'Villenave d\'Ornon', '33140', 'Jeune producteur dynamique', 'photoproducteur.png');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `IDPRODUIT` int(11) NOT NULL,
  `IDCATEGORIE` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) NOT NULL,
  `NOMPRODUIT` varchar(50) NOT NULL,
  `DESCRIPTIFPRODUIT` text NOT NULL,
  `PHOTOPRODUIT` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`IDPRODUIT`, `IDCATEGORIE`, `IDUTILISATEUR`, `NOMPRODUIT`, `DESCRIPTIFPRODUIT`, `PHOTOPRODUIT`) VALUES
(1, 1, 2, 'Pommes', 'Cageot de pommes rouges', 'pommerouge.png'),
(2, 2, 2, 'Carottes', 'Cageot de carottes', 'carrote.png'),
(3, 1, 2, 'Fraises', 'Cageot de fraises', 'fraises.png');

-- --------------------------------------------------------

--
-- Structure de la table `proposer`
--

CREATE TABLE `proposer` (
  `IDPRODUIT` int(11) NOT NULL,
  `IDVENTE` int(11) NOT NULL,
  `UNITE` varchar(10) NOT NULL,
  `QUANTITE` int(11) NOT NULL,
  `PRIX` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `proposer`
--

INSERT INTO `proposer` (`IDPRODUIT`, `IDVENTE`, `UNITE`, `QUANTITE`, `PRIX`) VALUES
(3, 1, '500g', 50, '4.50');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `MAIL` varchar(50) NOT NULL,
  `MDP` varchar(128) NOT NULL,
  `STATUT` varchar(15) NOT NULL,
  `NOMUTILISATEUR` varchar(40) DEFAULT 'NULL',
  `PRENOMUTILISATEUR` varchar(40) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`IDUTILISATEUR`, `MAIL`, `MDP`, `STATUT`, `NOMUTILISATEUR`, `PRENOMUTILISATEUR`) VALUES
(1, 'thibaultheyt@gmail.com', '1234', 'adherent', 'Heytmann', 'Thibault'),
(2, 'baptiste.bnd33@gmail.com', 'mdp', 'producteur', 'Bonnaud', 'Baptiste'),
(3, 'pablocorrales@outlook.fr', 'mdp123', 'responsable', 'Corrales', 'Pablo');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE `vente` (
  `IDVENTE` int(11) NOT NULL,
  `DATEVENTE` date NOT NULL,
  `DATEDEBUTPROD` date NOT NULL,
  `DATEFINPROD` date NOT NULL,
  `DATEDEBUTCLI` date NOT NULL,
  `DATEFINCLI` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`IDVENTE`, `DATEVENTE`, `DATEDEBUTPROD`, `DATEFINPROD`, `DATEDEBUTCLI`, `DATEFINCLI`) VALUES
(1, '2021-03-24', '2021-03-22', '2021-03-23', '2021-03-25', '2021-03-26');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherent`
--
ALTER TABLE `adherent`
  ADD PRIMARY KEY (`IDUTILISATEUR`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`IDCATEGORIE`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`IDCOMMANDE`),
  ADD KEY `FK_COMMANDE_ADHERENT` (`IDUTILISATEUR`),
  ADD KEY `FK_COMMANDE_VENTE` (`IDVENTE`),
  ADD KEY `FK_COMMANDE_ETAT` (`IDETAT`);

--
-- Index pour la table `etat`
--
ALTER TABLE `etat`
  ADD PRIMARY KEY (`IDETAT`);

--
-- Index pour la table `lignescommande`
--
ALTER TABLE `lignescommande`
  ADD PRIMARY KEY (`IDCOMMANDE`,`IDPRODUIT`),
  ADD KEY `FK_LIGNESCOMMANDE_PRODUIT` (`IDPRODUIT`);

--
-- Index pour la table `producteur`
--
ALTER TABLE `producteur`
  ADD PRIMARY KEY (`IDUTILISATEUR`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`IDPRODUIT`),
  ADD KEY `FK_PRODUIT_PRODUCTEUR` (`IDUTILISATEUR`),
  ADD KEY `FK_PRODUIT_CATEGORIE` (`IDCATEGORIE`);

--
-- Index pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD PRIMARY KEY (`IDPRODUIT`,`IDVENTE`),
  ADD KEY `FK_PROPOSER_VENTE` (`IDVENTE`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`IDUTILISATEUR`);

--
-- Index pour la table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`IDVENTE`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adherent`
--
ALTER TABLE `adherent`
  ADD CONSTRAINT `FK_ADHERENT_UTILISATEUR` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `utilisateur` (`IDUTILISATEUR`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_COMMANDE_ADHERENT` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `adherent` (`IDUTILISATEUR`),
  ADD CONSTRAINT `FK_COMMANDE_ETAT` FOREIGN KEY (`IDETAT`) REFERENCES `etat` (`IDETAT`),
  ADD CONSTRAINT `FK_COMMANDE_VENTE` FOREIGN KEY (`IDVENTE`) REFERENCES `vente` (`IDVENTE`);

--
-- Contraintes pour la table `lignescommande`
--
ALTER TABLE `lignescommande`
  ADD CONSTRAINT `FK_LIGNESCOMMANDE_COMMANDE` FOREIGN KEY (`IDCOMMANDE`) REFERENCES `commande` (`IDCOMMANDE`),
  ADD CONSTRAINT `FK_LIGNESCOMMANDE_PRODUIT` FOREIGN KEY (`IDPRODUIT`) REFERENCES `produit` (`IDPRODUIT`);

--
-- Contraintes pour la table `producteur`
--
ALTER TABLE `producteur`
  ADD CONSTRAINT `FK_PRODUCTEUR_UTILISATEUR` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `utilisateur` (`IDUTILISATEUR`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_PRODUIT_CATEGORIE` FOREIGN KEY (`IDCATEGORIE`) REFERENCES `categorie` (`IDCATEGORIE`),
  ADD CONSTRAINT `FK_PRODUIT_PRODUCTEUR` FOREIGN KEY (`IDUTILISATEUR`) REFERENCES `producteur` (`IDUTILISATEUR`);

--
-- Contraintes pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD CONSTRAINT `FK_PROPOSER_PRODUIT` FOREIGN KEY (`IDPRODUIT`) REFERENCES `produit` (`IDPRODUIT`),
  ADD CONSTRAINT `FK_PROPOSER_VENTE` FOREIGN KEY (`IDVENTE`) REFERENCES `vente` (`IDVENTE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
