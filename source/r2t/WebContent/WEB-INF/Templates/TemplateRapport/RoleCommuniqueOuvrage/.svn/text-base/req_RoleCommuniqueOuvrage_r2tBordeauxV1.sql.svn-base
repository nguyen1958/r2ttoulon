SELECT DISTINCT
     CONCAT('C')as type,
     rue.`nomrue` AS rue_nomrue,
     rue.`nomQuartier` AS rue_nomQuartier,
     article.`id_article` AS id_article,
     article.`etat` AS etat,
     article.`nom` AS nomArticle,
     article.`dateDernierControl` AS article_dateDernierControl,
     article.`dateProchainControl` AS article_dateProchainControl,
     bareme.`libelle` AS bareme_libelle,
     emplacementgeneral.`numRue` AS emplacementgeneral_numRue,
     emplacementgeneral.`adresse1` AS emplacementgeneral_adresse1,
     emplacementgeneral.`adresse2` AS emplacementgeneral_adresse2,
     emplacementgeneral.`adresse3` AS emplacementgeneral_adresse3,
     emplacementgeneral.`codePostal` AS emplacementgeneral_codePostal,
     emplacementgeneral.`ville` AS emplacementgeneral_ville,
     emplacementgeneral.`cedex` AS emplacementgeneral_cedex,
     redevable.`nomRedevable` AS redevable_nomRedevable,
     redevable.`prenom` AS redevable_prenom,
     redevable.`numRue` AS redevable_numRue,
     redevable.`adresse1` AS redevable_adresse1,
     redevable.`adresse2` AS redevable_adresse2,
     redevable.`adresse3` AS redevable_adresse3,
     redevable.`ville` AS redevable_ville,
     redevable.`rdCedex` AS redevable_rdCedex,
     redevable.`codePostal` AS redevable_codePostal,
     redevable.`civilite` AS redevable_civilite,
     emplacementgeneral.`complementNumeroRueEmpl` AS emplacementgeneral_complementNumeroRueEmpl,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `article` article,
     `bareme` bareme,
     `emplacementgeneral` emplacementgeneral,
     `elementfacturation` elementfacturation,
     `rue` rue,
     `redevable` redevable
WHERE
     article.etat = 'FacturerAControler'
 AND article.codebareme = bareme.code
 AND redevable.numRedevable = emplacementgeneral.numRedevable
 AND emplacementgeneral.codeVoie = rue.codeVoie
 AND article.id_elementfacturation = elementfacturation.numero
 AND elementfacturation.numeroEmplacement = emplacementgeneral.numero
 AND rue.nomQuartier LIKE $P{nomQuartier}
 AND STR_TO_DATE(article.dateProchainControl,'%d/%m/%Y') <= STR_TO_DATE($P{dateLimite},'%d/%m/%Y')
UNION
SELECT DISTINCT
     CONCAT('R')as type,
     rue.`nomrue` AS rue_nomrue,
     rue.`nomQuartier` AS rue_nomQuartier,
     article.`id_article` AS id_article,
     article.`etat` AS etat,
     article.`nom` AS nomArticle,
     article.`dateDernierControl` AS article_dateDernierControl,
     article.`dateProchainControl` AS article_dateProchainControl,
     bareme.`libelle` AS bareme_libelle,
     emplacementgeneral.`numRue` AS emplacementgeneral_numRue,
     emplacementgeneral.`adresse1` AS emplacementgeneral_adresse1,
     emplacementgeneral.`adresse2` AS emplacementgeneral_adresse2,
     emplacementgeneral.`adresse3` AS emplacementgeneral_adresse3,
     emplacementgeneral.`codePostal` AS emplacementgeneral_codePostal,
     emplacementgeneral.`ville` AS emplacementgeneral_ville,
     emplacementgeneral.`cedex` AS emplacementgeneral_cedex,
     redevable.`nomRedevable` AS redevable_nomRedevable,
     redevable.`prenom` AS redevable_prenom,
     redevable.`numRue` AS redevable_numRue,
     redevable.`adresse1` AS redevable_adresse1,
     redevable.`adresse2` AS redevable_adresse2,
     redevable.`adresse3` AS redevable_adresse3,
     redevable.`ville` AS redevable_ville,
     redevable.`rdCedex` AS redevable_rdCedex,
     redevable.`codePostal` AS redevable_codePostal,
     redevable.`civilite` AS redevable_civilite,
     emplacementgeneral.`complementNumeroRueEmpl` AS emplacementgeneral_complementNumeroRueEmpl,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `article` article,
     `bareme` bareme,
     `emplacementgeneral` emplacementgeneral,
     `elementfacturation` elementfacturation,
     `rue` rue,
     `reclamation` reclamation,
     `reclamation_article` reclamation_article,
     `redevable` redevable
WHERE
     article.id_elementfacturation = elementfacturation.numero
 AND redevable.numRedevable = emplacementgeneral.numRedevable
 AND article.codebareme = bareme.code
 AND elementfacturation.numeroEmplacement = emplacementgeneral.numero
 AND emplacementgeneral.codeVoie = rue.codeVoie
 AND article.id_article = reclamation_article.idArticle
 AND rue.nomQuartier LIKE $P{nomQuartier}
 AND reclamation.idReclamation = reclamation_article.idReclamation
 AND reclamation.etat = 'ENCOURS'
ORDER BY
     emplacementgeneral_numRue ASC,
     emplacementgeneral_adresse1 ASC