<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Régie</title>
</head>

<body background="../images/back_parametres.gif" topmargin="0" leftmargin="0" link="#000000" alink="#000000" vlink="#000000">

  <table border="0" cellpadding="0" cellspacing="0" width="150">
    <tr>
      <td width="100%">
        <p><img border="0" src="../images/parametres_dossier.gif" width="135" height="95">
        </p>
      </td>
    </tr>
    <tr>
      <td width="100%"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
        <a target="droite" href="../empl/bareme_index.htm"><img border="0" src="../images/lettre_b.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>arème</b></font></a></td>
    </tr>
    <tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
	
	<% 
	String marche = (String)session.getAttribute("marche");
	if (  marche !=null && marche.equalsIgnoreCase("true")) 
{ 
%>	

	<tr>
      <td width="100%"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
        <a target="droite" href="../empl/marche_index.htm"><img border="0" src="../images/lettre_m.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>arché</b></font></a></td>
    </tr>
    <tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>




  
	  	  
<% } %>
	<!-- Paul ajouter la saisie de la table ville -->
    <tr>
      <td width="100%"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
        <a target="droite" href="../empl/ville_index.htm"><img border="0" src="../images/lettre_v.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>ille</b></font></a></td>
    </tr>
    <tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>

    	
  </table>



</body>

</html>
