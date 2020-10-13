<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.view.batch.BAffListeCoomunique"/>

  <script>   
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=ruptureControle.jsp">

<input type="hidden" name="choix" size="20" value="chercher" >

  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="86">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="23">
            <b><font size="4">RECHERCHE :</font></b>          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
            détaillées.</font></td>
        </tr>

		  <tr>
          <td height="12"><b> <font face="Arial" size="2">&nbsp;Quartier  :</font></b></td>
		  <td height="12"> <jsp:getProperty name="bean" property="listeQuartier" />
		  </td>
		  <td height="12">&nbsp;</td>
		  </tr>
		  
  

        
        
        
       
        
        
        
       <tr>
      <td width="231" height="1">&nbsp;      </td>
      <td width="252" height="1">
        <b><font face="Arial" size="2">&nbsp;&nbsp; </font></b>      </td>
          <td width="655" height="1">
            <input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I4">          </td>
        </tr>
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
