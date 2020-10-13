SELECT DISTINCT reclamation.etat AS reclamationEtat 
FROM  `facture` facture, `reclamation` reclamation 
WHERE 
 	reclamation.idFacture = $P{laFacture}
	AND reclamation.etat = 'ENCOURS'