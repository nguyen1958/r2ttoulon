SELECT DISTINCT
     facture.`typeTaxe` AS facture_typeTaxe,
     facture.`anneeEx` AS facture_anneeEx,
     batchtraitement.`dateExecution` AS batchtraitement_dateExecution
FROM
     `facture` facture,
     `batchtraitement` batchtraitement,
     `imputation` imputation
WHERE
     imputation.libelle = facture.typeTaxe
     AND imputation.anneeExercice = facture.anneeEx	
     AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement
     AND facture.etat = "ANNULEE"
     AND facture.`typeTaxe` = $P{LeTypedeTaxe}
     AND facture.annulationEditee = "false"

GROUP BY
     facture.`typeTaxe`,
     facture.`anneeEx`