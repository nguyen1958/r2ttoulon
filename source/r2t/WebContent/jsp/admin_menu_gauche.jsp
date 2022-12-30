<html>
<head>
<title>R2T - Régie - ANALOGON</title>
</head>


<body background="../images/back_parametres.gif" topmargin="0" leftmargin="0" link="#000000" vlink="#000000" alink="#000000">
<%
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur"); 
String typeApplication = (String)session.getAttribute("typeApplication");
%>
  <table border="0" cellpadding="0" cellspacing="0" width="100%" height="326">
    <tr>
      <td width="100%" height="98">
        <p><img border="0" src="../images/admin_dossier.gif" width="135" height="101">
        </p>
      </td>
      
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/imputation_index.htm"><img border="0" src="../images/lettre_t.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>ype
        </b></font></a><br>&nbsp;&nbsp;&nbsp;&nbsp; de taxe</td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/Rue_index.htm"><img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>ue</b></font></a></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/parametres_admin_index.htm"><img border="0" src="../images/lettre_p.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">aramètre</font></b></a></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/utilisateur_index.htm"><img border="0" src="../images/lettre_u.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>tilisateur</b></font></a></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="22">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
	  <a href="../empl/historiqueAction.htm" 
	  target="droite"><img border="0" src="../images/lettre_h.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>istorique</b></font></a><font face="Arial" size="3"><b>&nbsp;</b></font><p style="line-height: 100%; margin-top: 0"><b><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b><font face="Arial" size="2">des
      tâches</font></td>
    </tr>
    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="22">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20">
	  <a target="droite" href="../empl/outils.htm"><img border="0" src="../images/lettre_o.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>utils</b></font></a></td>
    </tr>
    <tr>
      <td width="100%" height="21"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
	
<% 
if (  typeApplication !=null && typeApplication.equalsIgnoreCase("r2t")) 
{ 
%>
    <tr>
      <td width="100%" height="22">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/idoss_index.htm"><img border="0" src="../images/lettre_i.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>doss</b></font></a></td>
    </tr>
    <tr>
      <td width="100%" height="21"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    

<tr>
      <td width="100%" height="22">
      <p style="line-height: 100%; word-spacing: 0; margin: 0">
      <img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/ModeleCourier_index.htm"><img border="0" src="../images/lettre_g.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>estion des
      </b></font></a></p>
      <p style="line-height: 100%; word-spacing: 0; margin: 0"><b><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </font></b><font face="Arial" size="2">modèles courrier</font></p>
      </td>
</tr>
    <tr>
      <td width="100%" height="21"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    
<tr>
    <td width="100%" height="22"><img border="0" src="../images/masque_menu.gif" align="absmiddle" width="21" height="20"><a target="droite" href="../empl/groupeTaxe_index.htm"><img border="0" src="../images/lettre_g.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>roupement
      </b></font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;de taxe</td>
  </tr>
  <tr>
    <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
  </tr>   	
<%}%>

    <tr>
      <td width="100%" height="21">
      </td>

    </tr>

  </table>

</body>

</html>
