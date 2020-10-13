SELECT DISTINCT
     emplacementgeneral.`numRue` AS emplacementgeneral_numRue,
     emplacementgeneral.`adresse1` AS emplacementgeneral_adresse1,
     emplacementgeneral.`adresse2` AS emplacementgeneral_adresse2,
     emplacementgeneral.`adresse3` AS emplacementgeneral_adresse3,
     emplacementgeneral.`codePostal` AS emplacementgeneral_codePostal,
     emplacementgeneral.`ville` AS emplacementgeneral_ville,
     emplacementgeneral.`cedex` AS emplacementgeneral_cedex,
     emplacementgeneral.`complementNumeroRueEmpl` AS emplacementgeneral_complementNumeroRueEmpl
FROM
     `facture` facture,
     `redevable` redevable,
     `emplacementgeneral` emplacementgeneral,
     `article` article,
     `lignefacture` lignefacture,
     `elementfacturation` elementfacturation
WHERE
     facture.numeroFacture = $P{LaFacture}
 AND redevable.numRedevable = facture.idClient
 AND emplacementgeneral.numRedevable = redevable.numRedevable
 AND lignefacture.idFacture = facture.numeroFacture
 AND article.id_article = lignefacture.idArticle
 AND elementfacturation.numero = article.id_elementfacturation
 AND emplacementgeneral.numero = elementfacturation.numeroEmplacement