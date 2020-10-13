<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen"><SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
 <script type="text/javascript" src="./resources/js/Calendar.js"></script> 
<%@ page language = "java" %>


  <script>   
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=liste_imputation.jsp">

<input type="hidden" name="choix" size="20" value="chercher" >

  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="125">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">RECHERCHE DE TYPE DE TAXE :</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
          détaillées.</font></td>
        </tr>

        <tr>
      <td width="238" height="23">        
        <b><font face="Arial" size="2">&nbsp;Code du type de taxe :</font></b>
      </td>
          <td width="173" height="23">
            <input type="text" name="numtypeTaxe" size="20" >
          </td>
          <td width="808" height="23">
            &nbsp;&nbsp;</td>
        </tr>
        
        <tr>
      <td width="238" height="23">
        <b><font face="Arial" size="2">&nbsp;Libellé
        :</font></b>
      </td>
          <td width="173" height="23">
            <input type="text" name="libelle" size="20" >
          </td>
          <td width="808" height="23">
            &nbsp;&nbsp;</td>
        </tr>
        
        <tr>
      <td width="238" height="19">
        <b><font face="Arial" size="2">&nbsp;Code tri bordereau :</font></b>
      </td>
          <td width="173" height="23">
            <input type="text" name="code" size="20" >
          </td>
          <td width="808" height="23">
            &nbsp;&nbsp;
          </td>
        </tr>
        
        
       <tr>
      <td width="238" height="19">
        <b><font face="Arial" size="2">&nbsp;N° d’enveloppe budgétaire :</font></b>
      </td>
          <td width="173" height="19">
            <input type="text" name="section" size="20" >
          </td>
          <td width="808" height="19">
            &nbsp;&nbsp;
          </td>
        </tr>
        
        
        
       <tr>
      <td width="238" height="19">
        &nbsp;<b><font face="Arial" size="2">Année :</font></b>
      </td>
          <td width="173" height="19">
            <input type="text" name="anneeExercice" size="20" >
          </td>
          <td width="808" height="19">
            &nbsp;
          </td>
        </tr>
        
        
        
       <tr>
      <td width="238" height="19">
        <b><font face="Arial" size="2">&nbsp;&nbsp; </font></b>
      </td>
          <td width="173" height="19">
            &nbsp;&nbsp;
          </td>
          <td width="808" height="19">
            &nbsp;<input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3">
          </td>
        </tr>
        
        
        
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
