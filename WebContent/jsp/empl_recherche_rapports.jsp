<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffDivers"/>

  <script>
	function rechercherBatchChangementAdresseRedevable()
	{
       document.forms[0].action="./entree?action=liste_rapportChangementAdresseRedevable.jsp"
	   document.forms[0].submit();
	}
	
   function rechercherBatchSuivieFacturation()
	{
       document.forms[0].action="./entree?action=liste_rapportSuivieFacturation.jsp"
	   document.forms[0].submit();
	}
	
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST">
<table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="184">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">&nbsp;RAPPORTS :</font></b>          </td>
        </tr>
   
        <tr>
          <td width="1138" bgcolor="#FFFFCC" height="19" colspan="3">
            <b><font size="3">Rapport de suivi de facturation :</font></b>          </td>
        </tr>

    
        <tr>
          <td background="./images/fond_bleu.gif" width="1138" height="22" colspan="3" align="center"><a href="javascript:rechercherBatchSuivieFacturation();"><font color="#0000FF"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">echerche</font></b></font></a><font face="Arial" size="3" color="#0000FF"><b>r</b></font>          </td>
        </tr>

    
        <tr>
          <td  width="218" height="22">          </td>
          <td width="222" height="22">          </td>
          <td width="698" height="22">
          </td>
        </tr>
        <tr>
          <td bgcolor="#FFFFCC" height="19" colspan="3">
            <b><font size="3">Rapport de changement d'adresse des redevables :</font></b>          </td>
        </tr>
        <tr>
          <td  background="./images/fond_bleu.gif" colspan="3" ><div align="center"><a href="javascript:rechercherBatchChangementAdresseRedevable();"><font color="#0000FF"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">echerche</font></b></font></a><font face="Arial" size="3" color="#0000FF"><b>r</b></font></div> </td>
        </tr>
        
</table>  
</form>
</body>

</html>
