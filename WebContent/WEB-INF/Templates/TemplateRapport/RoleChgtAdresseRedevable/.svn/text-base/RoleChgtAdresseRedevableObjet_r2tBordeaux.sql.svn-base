SELECT DISTINCT
     
     facture.`typeTaxe` AS facture_typeTaxe,
     facture.`numeroFacture` AS facture_numeroFacture,
     facture.`dateCreation` AS facture_dateCreation,
     facture.`anneeEx` AS facture_anneeEx,
     facture.`idClient` AS facture_idClient
FROM
     `facture` facture,
     `batchtraitement` batchtraitement
WHERE
     facture.idClient = $P{ReferenceRedevable}
 AND facture.etat NOT LIKE 'ANNULEE'
 AND facture.etat NOT LIKE 'preRefacturation'
 AND facture.solde NOT LIKE '0'
 AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement   
 AND batchtraitement.valide='true'
GROUP BY
     facture.typeTaxe,
     facture.anneeEx