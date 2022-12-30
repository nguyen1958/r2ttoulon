<html>
<head>
<title>Test calendrier dateur</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffDivers"/>
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
 <script>	
 
 
 	function rechercherbatchRelance()
	{      
	   alert("Historique de batch relance");
	   alert("Developpement en cours");
	   //document.forms[1].submit();
	}
	
 
	function rechercherTache()
	{      
	   document.forms[0].submit();
	}
	
	function effacerHistorique()
	{ 
            ret=confirm("Etes-vous sûr de supprimer tous l'historique ?");
            if (ret == true)
            {
					//document.forms[0].action ="";
                    document.forms[0].choix.value="supprimer";
                    document.forms[0].submit();
            }

	}		
	
  </script>

</head>

<body background="./images/nuages.jpg" topmargin="0">

<form method="POST" action="entree?action=batch_journeaux.jsp" >
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/annulation.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">HISTORIQUE DES BATCHS :</font></b>          </td>
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
          <td background="./images/fond_bleu.gif" width="246">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Type de
            taxe : </font></b></font>          </td>
          <td width="149">
            <jsp:getProperty name="bean"  property="typeTaxe" />          </td>
          <td width="743">&nbsp;          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="246">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Etat
            du batch : </font></b></font>          </td>
          <td width="149">                      
            <select size="1" name="etatBatch">
              <option>Validé</option>
              <option>Non Validé</option>
            </select>
                     </td>        
            <td width="743">&nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="246">
            <b><font face="Arial" size="2" color="#000000">
            &nbsp;A créé des factures :</font></b>          </td>
          <td width="149">                      
            <select size="1" name="aCreeDesFactures">
              <option selected></option>
              <option >Oui</option>
              <option>Non</option>
            </select>
                     </td>        
            <td width="743">&nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="246">
            <b><font face="Arial" size="2" color="#000000">
            &nbsp;A créé des rapports d'annulation :</font></b>          </td>
          <td width="149">                      
            <select size="1" name="ACreeRapportsAnnulation">
              <option selected></option>
              <option >Oui</option>
              <option>Non</option>
            </select>
                     </td>        
            <td width="743">&nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="246">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Date
            de lancement du batch : </font></b></font>          </td>
          <td width="149">                      
            <input type="text" name="dateAction"  maxlength="10" id="dateAction" size="12" />
           
            <img onclick="displayCalendar(document.forms[0].dateAction,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
                     </td>        
            <td width="743"><input type="image" href="javascript:rechercherTache();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3"></td>
        </tr>
  </table>  
<p>&nbsp;</p>
</form>









<% String ville = (String)session.getAttribute("ville");  %>
<% if(ville.equalsIgnoreCase("toulon") && false) {%>

<form method="POST" action="entree?action=batch_relance_journeaux.jsp" >
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
   
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">HISTORIQUE DES BATCHS RELANCE :</font></b>          </td>
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
          <td background="./images/fond_bleu.gif" width="246">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Type de
            batch relance: </font></b></font>          </td>
          <td width="149">
            <jsp:getProperty name="bean"  property="typeBatchRelance" />          </td>
          <td width="743">&nbsp;          </td>
        </tr>
        
        
         <tr>
          <td background="./images/fond_bleu.gif" width="246">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Date
            de lancement du batch : </font></b></font>          </td>
          <td width="149">                      
            <input type="text" name="dateActionBatchRelance"  maxlength="10" id="dateActionBatchRelance" size="12" />
           
            <img onclick="displayCalendar(document.forms[1].dateActionBatchRelance,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
                     </td>        
            <td width="743"><input type="image" href="rechercherbatchRelance();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3"></td>
        </tr>
        
        
        
  </table>  
<p>&nbsp;</p>
</form>




<% } %>




</body>
</html>

