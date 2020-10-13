<html>
<head>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<%@ page language = "java" %>
<jsp:useBean id="beanDivers" scope="page" class="fr.analogon.r2t.batch.facture.BAffDivers"/>
<script>
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
</script></head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=liste_refacturation.jsp">


  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">RECHERCHE DE REFACTURATION :</font></b>          </td>
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
          <td background="./images/fond_bleu.gif" width="257">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de taxe : </font></b></font>          </td>
          <td width="193">
		  <jsp:getProperty name="beanDivers"  property="typeTaxe" />
          </td>
          <td width="688">&nbsp;            </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date de la
            refacturation : </font></b></font>          </td>
          <td width="193">
            <input maxlength="10" type="text" name="dateCreationRefacturation" id="dateCreationRefacturation" size="20">
		     
			  <img onclick="displayCalendar(document.forms[0].dateCreationRefacturation,'dd/mm/yyyy',this)"
			  src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />          </td>
          <td width="688">&nbsp;            </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif"><strong><font color="#000000" size="2" face="Arial"><b>&nbsp;N&deg; redevable </b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font> </strong></td>
          <td><input name="numRedevable" type="text" id="numRedevable" size="20"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b> &nbsp;N° facture
            annulée</b></font><font color="#000000"><b><font face="Arial" size="2">
            : </font></b></font>          </td>
          <td width="193">
            <input type="text" name="numeroFacture" size="20">          </td>
          <td width="688">&nbsp;          </td>
        </tr>
        
        
       <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Année</b></font><font color="#000000"><b><font face="Arial" size="2">&nbsp;
            :</font></b></font>          </td>
          <td width="193">
            <input name="anneeExercice" type="text" id="anneeExercice" size="20">          </td>
          <td width="688">
            <input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3">          </td>
        </tr>
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
