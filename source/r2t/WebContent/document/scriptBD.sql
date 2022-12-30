--
-- Creer Table autorisation et autorisationemplacement 
--

CREATE TABLE IF NOT EXISTS `autorisation` (
  `idautorisation` int(11) NOT NULL AUTO_INCREMENT,
  `numeroTmp` text,
  `numRedevable` int(11) NOT NULL,
  `etat` varchar(80) DEFAULT NULL,
  `refDossier` text,
  `dateCreation` varchar(40) DEFAULT NULL,
  `dateModification` varchar(40) DEFAULT NULL,
  `createur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idautorisation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `autorisationemplacement` (
  `idautorisation` int(11) NOT NULL,
  `idemplacement` int(11) NOT NULL,
  PRIMARY KEY (`idautorisation`,`idemplacement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Structure de la table `commentaireredevable`
--

CREATE TABLE IF NOT EXISTS `commentaireredevable` (
  `idcommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `numredevable` int(11) DEFAULT NULL,
  `auteur` int(11) DEFAULT NULL,
  `commentaire` text NOT NULL,
  `resultatvisite` varchar(40),
  `date` datetime NOT NULL,
  PRIMARY KEY (`idcommentaire`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

--
-- Structure de la table `historiqueetatouvrage`
-- A ajouter uniquement les champs manquants
--

CREATE TABLE IF NOT EXISTS `historiqueetatouvrage` (
  `idHistoriqueEtatOuvrage` int(11) NOT NULL AUTO_INCREMENT,
  `etatOuvrage` varchar(40) DEFAULT NULL,
  `dateModification` varchar(20) DEFAULT NULL,
  `typeModifcation` varchar(250) DEFAULT NULL,
  `idOuvrage` int(11) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `source` varchar(40) NOT NULL,
  `idutilisateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHistoriqueEtatOuvrage`),
  KEY `idx_type` (`type`),
  KEY `idx_date` (`dateModification`),
  KEY `idx_source` (`source`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

--
-- Structure de la table `historiqueredevable`
--

CREATE TABLE IF NOT EXISTS `historiqueredevable` (
  `idHistoriqueRedevable` int(11) NOT NULL AUTO_INCREMENT,
  `numredevable` int(11) DEFAULT NULL,
  `dateModification` varchar(20) DEFAULT NULL,
  `typeModifcation` varchar(250) DEFAULT NULL,  
  `type` varchar(40) DEFAULT NULL,
  `source` varchar(40) NOT NULL,
  `idutilisateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHistoriqueRedevable`),
  KEY `idx_type` (`type`),
  KEY `idx_date` (`dateModification`),
  KEY `idx_source` (`source`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
-- Update Table parametre (ajout d'un champs valeur)

ALTER TABLE `parametre` ADD `libelle` VARCHAR( 100 ) NOT NULL AFTER `valeur` ;

SET CHARACTER SET UTF8;

INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('etat_ouvrage', 'Remboursé', 'Rembourser', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('etat_ouvrage', 'FacturerAControler', 'Facturé à Controler', NULL);

UPDATE parametre SET libelle='A Facturer' WHERE type='etat_ouvrage' and valeur='AFacturer';
UPDATE parametre SET libelle='Facturer' WHERE type='etat_ouvrage' and valeur='Facturer';
UPDATE parametre SET libelle='Contrôlé Absent' WHERE type='etat_ouvrage' and valeur='ControlerAbscent';
UPDATE parametre SET libelle='Contrôlé Présent' WHERE type='etat_ouvrage' and valeur='ControlerPresent';
UPDATE parametre SET libelle='Contrôlé Alerte' WHERE type='etat_ouvrage' and valeur='ControlerAlerte';
UPDATE parametre SET libelle='Ne Plus Facturer' WHERE type='etat_ouvrage' and valeur='NePlusFacturer';
UPDATE parametre SET libelle='Contrôlé à Facturer' WHERE type='etat_ouvrage' and valeur='ControlerAFacturer';
UPDATE parametre SET libelle='Ne Pas Facturer' WHERE type='etat_ouvrage' and valeur='NePasFacturer';
UPDATE parametre SET libelle='Rembourser' WHERE type='etat_ouvrage' and valeur='Remboursé';
UPDATE parametre SET libelle='Facturé à Controler' WHERE type='etat_ouvrage' and valeur='FacturerAControler';

-- Ajouter le type='resultat_visite' avant insertion
ALTER TABLE `parametre` CHANGE `type` `type` ENUM( 'type_civilite', 'type_utilisateur', 'code_secteur', 'etatReclamation', 'typeReceptionReclamation', 'etatAlerte', 'etat_facture', 'etat_ouvrage', 'uniteTravail', 'typeArrondi', 'etat_emplacement', 'codeTriBordeau', 'uniteTemps', 'typeFacturation', 'motifAnnulationFacture', 'cheminPhotos', 'cheminData', 'niveauDebogageApplication', 'complNumRue', 'niveauDebogageR2TMobile', 'ville', 'siteWebVille', 'codePostal', 'nature_juridique', 'dateDeliberation', 'resultat_visite') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ;
-- Inserer les valeurs de resultatvisite
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Responsable établissement absent', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Ne peut pas payer pour le moment', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Ne veut pas payer', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Demande à faire des acomptes', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Demande à faire un virement', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Demande à faire un prélèvement', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Préfère qu’on vienne le voir régulièrement', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Ne peut pas se déplacer au service', '', NULL);
INSERT INTO `parametre` (`type`, `valeur`, `libelle`, `id`) VALUES ('resultat_visite', 'Comportement agressif', '', NULL);

-- Update Table historiqueetatouvrage (ajout d'un champs idutilisateur)
ALTER TABLE `historiqueetatouvrage` ADD `source` VARCHAR( 40 ) NULL ;
ALTER TABLE `historiqueetatouvrage` ADD `idutilisateur` INT NULL ;

-- Update Table redevable (créer redevable nondefini)

INSERT INTO `redevable` (`numRedevable`, `codeVoie`, `numeroProfession`, `nomRedevable`, `responsable`, `codetiers`, `nomJF`, `prenom`, `civilite`, `dateNaissance`, `lieuNaissance`, `nationalite`, `numRue`, `adresse1`, `adresse2`, `adresse3`, `ville`, `rdCedex`, `codePostal`, `email`, `numTel`, `numfax`, `numPortable`, `typeRedevable`, `naturejuridique`, `raisonSocialeLiquidateur`, `nomLiquidateur`, `prenomLiquidateur`, `numVoieLiquidateur`, `codeVoixLiquidateur`, `adressLiquidateur`, `complement1AdressLiquidateur`, `complement2AdressLiquidateur`, `codePostaleLiquidateur`, `villeeLiquidateur`, `cedexLiquidateur`, `numTelFixeLiquidateur`, `numTelPortableLiquidateur`, `numTelFaxeLiquidateur`, `emailLiquidateur`, `changementAdresse`, `actif`, `complementNumeroRueRedevable`, `complementNumeroRueLiquidateur`, `informationComplementaire`, `commentaire`, `siret`, `siren`, `immeubleResidence`) VALUES
(-1, ' ', 1, 'NONDEFINI', '', '', '', '', '', '', '', '', '', '', '', '', '', 'false', '', '', '', '', '', 'normal', '', '', '', '', '', '', '', '', '', '', '', 'false', '', '', '', '', 'false', 'true', '', '', '', '', '', '', '');

-- Tables ajoutées

--
-- Structure de la table `historiquecontrolemplacement`
--

CREATE TABLE IF NOT EXISTS `historiquecontrolemplacement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idControleur` int(11) DEFAULT  NULL,
  `dateControle` varchar(30) DEFAULT NULL,
  `idEmplacement` int(11) NOT NULL,
  `etat` varchar(20) DEFAULT  '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE imputation ADD chapitre VARCHAR( 10 ) DEFAULT '',
ADD nature VARCHAR(10) DEFAULT '',
ADD fonction VARCHAR(10) DEFAULT '',
ADD codeOpeEquipement VARCHAR(10) DEFAULT '',
ADD typeMouvement VARCHAR(10) DEFAULT '',
ADD sens VARCHAR(10) DEFAULT '',
ADD codeSegStructurelle VARCHAR(10) DEFAULT '',
ADD codeEleStructurelleGestionnaire VARCHAR(10) DEFAULT '',
ADD codeEleStructurelleDestinataire VARCHAR(10) DEFAULT '',
ADD codeSegment1 VARCHAR(10) DEFAULT '',
ADD codeEleSectoriel1 VARCHAR(10) DEFAULT '',
ADD codeSegment2 VARCHAR(10) DEFAULT '',
ADD codeEleSectoriel2 VARCHAR(10) DEFAULT '',
ADD codeSegment3 VARCHAR(10) DEFAULT '',
ADD codeEleSectoriel3 VARCHAR(10) DEFAULT '',
ADD codeSegment4 VARCHAR(10) DEFAULT '',
ADD codeEleSectoriel4 VARCHAR(10) DEFAULT '',
ADD codeSegment5 VARCHAR(10) DEFAULT '',
ADD codeEleSectoriel5 VARCHAR(10) DEFAULT '',
ADD codeSegOperationnel VARCHAR(10) DEFAULT '',
ADD codeEleOperationnel VARCHAR(10) DEFAULT '',
ADD codeSegStrategique VARCHAR(10) DEFAULT '',
ADD codeEleStrategique VARCHAR(10) DEFAULT '';
