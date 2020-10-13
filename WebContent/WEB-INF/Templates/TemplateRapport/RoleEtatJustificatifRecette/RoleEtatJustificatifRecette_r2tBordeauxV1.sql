SELECT DISTINCT
     facture.`typeTaxe` AS facture_typeTaxe,
     facture.`anneeEx` AS facture_anneeEx,
     imputation.`code` AS imputation_code,
     imputation.`libelle` AS imputation_libelle,
     STR_TO_DATE(batchtraitement.`dateExecution`,'%d/%m/%Y - %H:%i:%s')AS batchtraitement_dateExecution,
     imputation.`section` AS imputation_section,
     imputation.`designation` AS imputation_designation,
     imputation.`codeFonction` AS imputation_codeFonction,
     imputation.`codeCentreResponsable` AS imputation_codeCentreResponsable
FROM
     `facture` facture,
     `imputation` imputation,
     `batchtraitement` batchtraitement
WHERE
     imputation.libelle = facture.typeTaxe
 AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement
 AND facture.idBatchTraitement = $P{NumeroBatch}
AND imputation.anneeExercice = facture.anneeEx
 AND facture.etat NOT LIKE "preRefacturation"

GROUP BY
     imputation.nomElu,
     facture.typeTaxe,
     facture.anneeEx