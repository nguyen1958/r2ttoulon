<html>
<head>
<title>Recherche de paiement</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<jsp:useBean id="beanRecherchePayement" scope="page" class="fr.analogon.r2t.view.regie.BAffListePayement"/>
<jsp:setProperty name="beanRecherchePayement" property="request" value="<%=request%>"/>
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script>
    function ok()
    {
      document.forms[0].submit();
    }   
	
	function lancerBatchChangementAdresseRedevable()
	{
       document.forms[0].action="./entree?action=gestionBatchChangementAdressRedevable.jsp"
	   document.forms[0].submit();
	}
	
	function rechercherBatchChangementAdresseRedevable()
	{
       document.forms[0].action="./entree?action=liste_rapportChangementAdresseRedevable.jsp"
	   document.forms[0].submit();
	}
	
	function changementTypePayement()
	{			
		document.getElementById('idBanque').value= document.forms[0].banque.options[document.forms[0].banque.selectedIndex].value;	
		if(document.forms[0].banque.options[document.forms[0].banque.selectedIndex].value == "")
		document.getElementById('idBanque').value=0;
		
		document.getElementById('idTypePayement').value= document.forms[0].typePaiement.options[document.forms[0].typePaiement.selectedIndex].value;	
		if(document.forms[0].typePaiement.options[document.forms[0].typePaiement.selectedIndex].value == "")
		document.getElementById('idTypePayement').value=0;
	}
		
	
	
  </script>

</head>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>

<body background="./images/nuages.jpg" topmargin="0">

<form method="POST"  action="entree?action=liste_payement.jsp">
<input type ="hidden" id= "idBanque" name="idBanque" >
<input type ="hidden" id= "idTypePayement" name="idTypePayement" >
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">RECHERCHE DE PAIEMENT :</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
          détaillées.</font></td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Numéro de la
            facture</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="222">
            <input type="text" name="numeroFacture" size="15">
          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de
            taxe : </font></b></font>
          </td>
          <td width="222">
 				<jsp:getProperty name="beanRecherchePayement" property="typeTaxe"/>
 			</td>
          <td width="698">&nbsp;
          </td>
        </tr>
        

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Montant
            du paiement : </font></b></font>
          </td>
          <td width="222">       
            <input type="text" name="montantPayement" size="15">
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de
            paiement :</font></b></font>
          </td>
          <td width="222">       
            <jsp:getProperty name="beanRecherchePayement" property="typePaiement"/>
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Banque : </font></b></font>
          </td>
          <td width="222">       
				<jsp:getProperty name="beanRecherchePayement" property="banque"/>
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Etat du
            paiement :</font></b></font>
          </td>
          <td width="222">       
            <select size="1" name="etatPaiement">
              <option>Valide</option>
              <option>Annule</option>
            </select>
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Numéro
            de quittance : </font></b></font>
          </td>
          <td width="222">       
            <input type="text" name="numeroQuittance" size="15">
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Numéro
            du chèque : </font></b></font>
          </td>
          <td width="222">       
            <input type="text" name="numeroCheque" size="15">
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Nombre de
            factures payées : </font></b></font>
          </td>
          <td width="222">       
            <input type="text" name="nombreFacturePaye" size="15">
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date de
            paiement : </font></b></font>
          </td>
          <td width="222">                      
            <input maxlength="10" type="text" name="datePayement" id="dateCreationFacture" size="12" />
            
            <img onclick="displayCalendar(document.forms[0].dateCreationFacture,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
          </td>        
            <td width="222">&nbsp;
            
          </td>
        </tr>

    
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Année : </font></b></font>
          </td>
          <td width="222">                      
            <input maxlength="10" type="text" name="anneePaiement" id="anneePaiement" size="12" />
            
          </td>        
          <td width="698">
            &nbsp;
          </td>
        </tr>       

    
        <tr>
          <td background="./images/fond_bleu.gif" width="218">&nbsp;
            
          </td>
          <td width="222">&nbsp;
            
          </td>
          <td width="698">
            <input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3">
          </td>
        </tr>       
  </table>  
</form>






</body>
</html>

