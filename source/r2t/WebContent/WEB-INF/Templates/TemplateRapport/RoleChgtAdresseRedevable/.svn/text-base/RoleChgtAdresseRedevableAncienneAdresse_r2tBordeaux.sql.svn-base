SELECT DISTINCT
     historiqueadresseredevable.`numRue` AS historiqueadresseredevable_numRue,
     historiqueadresseredevable.`adresse1` AS historiqueadresseredevable_adresse1,
     historiqueadresseredevable.`adresse2` AS historiqueadresseredevable_adresse2,
     historiqueadresseredevable.`adresse3` AS historiqueadresseredevable_adresse3,
     historiqueadresseredevable.`ville` AS historiqueadresseredevable_ville,
     historiqueadresseredevable.`codePostal` AS historiqueadresseredevable_codePostal,
     historiqueadresseredevable.`rdCedex` AS historiqueadresseredevable_rdCedex

FROM
     `redevable` redevable,
     `historiqueadresseredevable` historiqueadresseredevable
WHERE
	historiqueadresseredevable.idRedevable = $P{NumRedevable}