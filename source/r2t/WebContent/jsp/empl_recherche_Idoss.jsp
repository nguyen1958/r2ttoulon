<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.rue.BAffRue"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>


  <script>   
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=liste_Idoss.jsp">

<input type="hidden" name="choix" size="20" value="chercher" >

  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="86">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="49">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="26">
            <b><font size="4">RECHERCHE D'UN BATCH IDOSS :</font></b>          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" height="18">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
            détaillées.</font></td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="199" height="1">
            <font color="#000000"><b><font face="Arial" size="2" color="#000000">&nbsp;Date
            de lancement : </font></b></font>          </td>
          <td width="196" height="1">                      
            <input type="text" name="dateAction"  maxlength="10" id="dateAction" size="12" />
           
            <img onclick="displayCalendar(document.forms[0].dateAction,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
                     </td>        
            <td width="743" height="1"><input type="image" href="javascript:rechercherTache();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3"></td>
        </tr>
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
