SELECT
     facture.`numeroTitre` AS facture_numeroTitre,
     redevable.`nomRedevable` AS redevable_nomRedevable,
     redevable.`adresse1` AS redevable_adresse1,
     redevable.`adresse2` AS redevable_adresse2,
     redevable.`adresse3` AS redevable_adresse3,
     redevable.`rdCedex` AS redevable_rdCedex,
     redevable.`codePostal` AS redevable_codePostal,
     redevable.`numRue` AS redevable_numRue,
     redevable.`ville` AS redevable_ville,
     facture.`montantTotal` AS facture_montantTotal,
     redevable.`prenom` AS redevable_prenom,
     redevable.`civilite` AS redevable_civilite,
     facture.`etat` AS facture_etat,
     facture.`numeroFacture` AS facture_numeroFacture,
     facture.`motifAnnulation` AS facture_motifAnnulation,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `facture` facture,
     `batchtraitement` batchtraitement,
     `redevable` redevable
WHERE
     redevable.numRedevable = facture.idclient
 AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement
 AND facture.anneeEx = $P{Lexercice}
 AND facture.typeTaxe = $P{LeTypedeTaxe}
 AND facture.etat = "ANNULEE"
 AND facture.annulationEditee = "false"
ORDER BY
     facture.`numeroFacture` ASC