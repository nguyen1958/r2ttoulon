--Supprimer tous les paiments d'une facture
DELETE lp,p 
FROM payement p 
JOIN lignepayement lp ON lp.idpayement = p.idpayement 
WHERE lp.idfacture =409522;

UPDATE facture SET solde = '624.50' WHERE idFacture =409522;

--Supprimer un paiement via idpayement
DELETE lp,p 
FROM payement p 
JOIN lignepayement lp ON lp.idpayement = p.idpayement 
WHERE p.idpayement =72345;