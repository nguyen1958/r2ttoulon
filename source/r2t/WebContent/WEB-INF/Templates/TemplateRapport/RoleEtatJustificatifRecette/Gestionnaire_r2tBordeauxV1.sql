SELECT
     imputation.`nomGestionnaire` AS imputation_nomGestionnaire,
     imputation.`telGestionnaire` AS imputation_telGestionnaire,
     imputation.`faxGestionnaire` AS imputation_faxGestionnaire
FROM
     `imputation` imputation
WHERE
     imputation.anneeExercice = $P{AnneeExercice}
 AND imputation.libelle = $P{LeTypeDeTaxe}