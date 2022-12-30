SELECT DISTINCT
     redevable.`nomRedevable` AS redevable_nomRedevable,
     redevable.`adresse1` AS redevable_adresse1,
     redevable.`adresse2` AS redevable_adresse2,
     redevable.`adresse3` AS redevable_adresse3,
     redevable.`rdCedex` AS redevable_rdCedex,
     redevable.`codePostal` AS redevable_codePostal,
     redevable.`numRue` AS redevable_numRue,
     redevable.`ville` AS redevable_ville,
     redevable.`prenom` AS redevable_prenom,
     redevable.`civilite` AS redevable_civilite,
     redevable.`numRedevable` AS redevable_numRedevable,
     historiqueadresseredevable.`numRue` AS historiqueadresseredevable_numRue,
     historiqueadresseredevable.`adresse1` AS historiqueadresseredevable_adresse1,
     historiqueadresseredevable.`adresse2` AS historiqueadresseredevable_adresse2,
     historiqueadresseredevable.`adresse3` AS historiqueadresseredevable_adresse3,
     historiqueadresseredevable.`ville` AS historiqueadresseredevable_ville,
     historiqueadresseredevable.`codePostal` AS historiqueadresseredevable_codePostal,
     historiqueadresseredevable.`rdCedex` AS historiqueadresseredevable_rdCedex,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `redevable` redevable,
     `facture` facture,
     `historiqueadresseredevable` historiqueadresseredevable,
     `batchtraitement` batchtraitement
WHERE
     facture.idClient = redevable.numRedevable
 AND redevable.changementAdresse = 'true'
 AND facture.etat NOT LIKE 'ANNULEE'
 AND facture.etat NOT LIKE 'preRefacturation'
 AND facture.solde NOT LIKE '0'
 AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement
 AND batchtraitement.valide = 'true'
AND historiqueadresseredevable.idRedevable = redevable.numRedevable

GROUP BY
     redevable.numRedevable
ORDER BY
     redevable.`nomRedevable` ASC