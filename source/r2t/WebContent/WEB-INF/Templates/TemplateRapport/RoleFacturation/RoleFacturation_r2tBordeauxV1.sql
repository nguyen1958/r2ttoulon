SELECT DISTINCT
     facture.`typeTaxe` AS facture_typeTaxe,
     facture.`anneeEx` AS facture_anneeEx,
     batchtraitement.`dateExecution` AS batchtraitement_dateExecution
FROM
     `facture` facture,
     `batchtraitement` batchtraitement
WHERE
     facture.idBatchTraitement = $P{NumeroBatch}
 AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement
 AND facture.etat NOT LIKE "preRefacturation"
GROUP BY
     facture.`typeTaxe`,
     facture.`anneeEx