<html>
<head>
<title>Test calendrier dateur</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffDivers"/>
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
 <script>	
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



<form method="POST" action="entree?action=HistoriqueAction.jsp" >
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB">
            <b><font size="4">HISTORIQUE DES TACHES  :</font></b>          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
          détaillées.</font></td>
        </tr>

       

 

        <tr>
          <td background="./images/fond_bleu.gif" width="243">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Information sur le type de
            taxe : </font></b></font>          </td>
          <td width="197">
            <jsp:getProperty name="bean"  property="typeTaxe" />          </td>
          <td width="698">&nbsp;          </td>
        </tr>

        <tr>
          <td width="243" height="21" background="./images/fond_bleu.gif">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Mots clefs  : </font></b></font>          </td>
          <td width="197"><input type="text" name="motClef" id="motClef" size="20" /></td>
            <td width="698">&nbsp;          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif"><font color="#000000"><b><font face="Arial" size="2">&nbsp;Action  : </font></b></font> </td>
          <td><label>
            <select name="actionRecherche">
              <option></option>
			  <option>Bascule</option>            
              <option>Lancement de batch</option>
              <option>Changement d'adresse redevable</option>
              <option>Ajout/Suprresion de photos</option>
            </select>
          </label></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="243">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date : </font></b></font>          </td>
          <td width="197">                      
            <input type="text" name="dateAction"  maxlength="10" id="dateAction" size="12" />
           
            <img onclick="displayCalendar(document.forms[0].dateAction,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
                     </td>        
            <td width="698"><input type="image" href="javascript:rechercherTache();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3"></td>
        </tr>
        <tr>
          <td height="27"></td>
          <td height="27"></td>
          <td height="27"></td>
        </tr>
  </table>  
<p>&nbsp;</p>
</form>






</body>
</html>

