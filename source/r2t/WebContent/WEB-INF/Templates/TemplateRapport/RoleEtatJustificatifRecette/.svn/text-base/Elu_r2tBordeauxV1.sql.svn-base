SELECT
     imputation.`eluRenseignement1` AS imputation_eluRenseignement1,
     imputation.`eluRenseignement2` AS imputation_eluRenseignement2,
     imputation.`nomElu` AS imputation_nomElu,
     imputation.`eluRenseignement3` AS imputation_eluRenseignement3
FROM
     `imputation` imputation
WHERE
     imputation.anneeExercice = $P{AnneeExercice}
 AND imputation.libelle = $P{LeTypeDeTaxe}