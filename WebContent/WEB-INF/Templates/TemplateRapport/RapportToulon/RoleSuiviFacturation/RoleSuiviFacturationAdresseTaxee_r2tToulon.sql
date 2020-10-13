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
     `lignefacture` lignefacture,
     `article` article,
     `elementfacturation` elementfacturation
WHERE
     facture.numeroFacture = $P{LaFacture}
 AND emplacementgeneral.numRedevable = redevable.numRedevable
 AND redevable.numRedevable = facture.idClient
 AND `lignefacture`.`idArticle` = article.`id_article`
 AND `article`.`id_elementfacturation` = `elementfacturation`.`numero`
 AND `elementfacturation`.`numeroEmplacement` = `emplacementgeneral`.`numero`