SELECT DISTINCT
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
     facture.`solde` AS facture_solde,
     imputation.`designation` AS imputation_designation,
     redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable
FROM
     `facture` facture,
     `redevable` redevable,
     `imputation` imputation
WHERE
     facture.idclient = redevable.numRedevable
 AND facture.typeTaxe = $P{TypeTaxe}
 AND imputation.libelle = facture.typeTaxe
 AND facture.etat NOT LIKE "preRefacturation"
 AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') >= STR_TO_DATE($P{DateDebutPeriode},'%d/%m/%Y')
 AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') <= STR_TO_DATE($P{DateFinPeriode},'%d/%m/%Y')
GROUP BY
     facture.idFacture
ORDER BY
     facture.`numeroTitre` ASC,
     facture.`numeroFacture` ASC