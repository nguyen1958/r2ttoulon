SELECT DISTINCT
     facture.`numeroFacture` AS facture_numeroFacture,
     facture.`dateCreation` AS facture_dateCreation,
     facture.`montantTotal` AS facture_montantTotal,
     facture.`numeroTitre` AS facture_numeroTitre
FROM
     `facture` facture,
     `batchtraitement` batchtraitement
WHERE
     facture.idClient = $P{LaReferenceduRedevable}
 AND facture.typeTaxe = $P{LeTypedeTaxe}
 AND facture.anneeEx = $P{Lexercice}
 AND facture.etat NOT LIKE 'ANNULEE'
 AND facture.etat NOT LIKE 'preRefacturation'
AND facture.solde NOT LIKE 0
 AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement
 AND batchtraitement.valide = 'true'