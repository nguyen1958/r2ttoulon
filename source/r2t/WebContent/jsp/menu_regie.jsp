<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>R�gie</title>
</head>

<body background="../images/back_parametres.gif" topmargin="0" leftmargin="0" link="#000000" alink="#000000" vlink="#000000">

  <table border="0" cellpadding="0" cellspacing="0" width="150">
    <tr>
      <td width="100%">
        <p><img border="0" src="../images/regie_dossier.gif" width="135" height="95">
        </p>
      </td>
    </tr>
    <tr>
      <td width="100%">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
      <a href="../entree?action=empl_recherche_paiement.jsp" target="bas">
      <img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">echerche</font></b>
      </a>
      <font face="Arial" size="2"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        d'un paiement
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
      <a target="bas" href="../entree?action=empl_recherche_factures.jsp">
      <img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">echerche</font></b>
      </a>
      <font face="Arial" size="2"><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        de facture
      </font>
      </td>
    </tr>
   
<% } %>   


<% 
	String marche = (String)session.getAttribute("marche");
	if (  marche !=null && marche.equalsIgnoreCase("true")) 
{ 
%>
    <tr>
      <td width="100%"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    
   <tr>
      <td width="100%">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
      <a target="bas" href="../entree?action=empl_gestion_paiementMarche.jsp&choix=ajouter">
      <img border="0" src="../images/lettre_s.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">aisie</font></b>
      </a>
      <font face="Arial" size="2"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; d'un paiement</font>
      <font face="Arial" size="2"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; march�</font>
      </td>
    </tr>
  	  	  
<% } %> 
   <!-- <tr>
      <td width="100%"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="bas" href="indexRedevable.htm"><img border="0" src="../images/lettre_l.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>iste</b></font></a></b></td>
    </tr>-->
  </table>

</body>

</html>