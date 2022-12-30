SELECT DISTINCT
     facture.`montantTotal` AS facture_montantTotal,
     redevable.`civilite` AS redevable_civilite,
     redevable.`prenom` AS redevable_prenom,
     redevable.`nomRedevable` AS redevable_nomRedevable,
     redevable.`numRue` AS redevable_numRue,
     redevable.`adresse1` AS redevable_adresse1,
     redevable.`adresse2` AS redevable_adresse2,
     redevable.`adresse3` AS redevable_adresse3,
     redevable.`ville` AS redevable_ville,
     redevable.`rdCedex` AS redevable_rdCedex,
     redevable.`codePostal` AS redevable_codePostal,
     facture.`anneeEx` AS facture_anneeEx,
     facture.`motifAnnulation` AS facture_motifAnnulation,
     facture.`numeroTitre` AS facture_numeroTitre,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `facture` facture,
     `redevable` redevable
WHERE
     facture.typeTaxe = $P{LeTypeDeTaxe}
 AND facture.anneeEx = $P{LExercice}
 AND redevable.numRedevable = facture.idClient
 AND facture.etat = "ANNULEE"
 AND facture.annulationEditee = "false"