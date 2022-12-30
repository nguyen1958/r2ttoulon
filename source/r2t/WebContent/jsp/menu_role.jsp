<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Role</title>
</head>

<body background="../images/back_parametres.gif" topmargin="0" leftmargin="0" link="#000000" alink="#000000" vlink="#000000">

  <table border="0" cellpadding="0" cellspacing="0" width="150">
    <tr>
      <td width="100%">
        <p><img border="0" src="../images/role_dossier.gif" width="135" height="95">
        </p>
      </td>
    </tr>
    <tr>
      <td width="100%">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
      <a href="../entree?action=empl_recherche_redevable.jsp&typeRecherche=role" target="bas">
      <img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">echerche</font></b>
      </a>
      <font face="Arial" size="2"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        d'un redevable
      </font>
      </td>
    </tr>
    
<%
String typeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");  
if (  typeTaxeAutorise.length() != 0 )           
{
 %>

    <tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
      <a target="bas" href="../entree?action=empl_gestion_redevable.jsp&choix=creer&typeRecherche=role&typeRedevable=normal&optionTransfert=role&origine=origine">
      <img border="0" src="../images/lettre_c.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">réation</font></b>
      </a>
      <font face="Arial" size="2"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        d'un redevable
      </font>
      </td>
    </tr>
   
<% } %>  
	<tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>   
   <tr>
      <td width="100%">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
      <a href="../entree?action=empl_resultat_recherche_alerte.jsp&numRedevable=-1&alerte=1" target="bas">
      <img border="0" src="../images/lettre_a.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">lerte</font></b>
      </a>
      </td>
    </tr>
  </table>

</body>

</html>
