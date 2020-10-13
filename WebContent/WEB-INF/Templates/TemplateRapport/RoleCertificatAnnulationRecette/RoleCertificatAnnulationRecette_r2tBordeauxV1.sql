SELECT DISTINCT
     facture.`typeTaxe` AS facture_typeTaxe,
     facture.`anneeEx` AS facture_anneeEx,
     imputation.`code` AS imputation_code,
     imputation.`libelle` AS imputation_libelle,
     STR_TO_DATE(batchtraitement.`dateExecution`,'%d/%m/%Y')AS batchtraitement_dateExecution,
     imputation.`section` AS imputation_section,
     imputation.`codeFonction` AS imputation_codeFonction,
     imputation.`designation` AS imputation_designation, 
     imputation.`codeCentreResponsable` AS imputation_codeCentreResponsable
FROM
     `facture` facture,
     `imputation` imputation,
     `batchtraitement` batchtraitement
WHERE
     imputation.libelle = facture.typeTaxe
     AND imputation.anneeExercice = facture.anneeEx	
     AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement
     AND facture.etat = "ANNULEE"
     AND facture.`typeTaxe` = $P{LeTypedeTaxe}
     AND facture.annulationEditee = "false"
GROUP BY
     	imputation.nomElu,
	facture.typeTaxe,
     	facture.anneeEx