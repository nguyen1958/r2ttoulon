<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<script type="text/javascript" src="./resources/js/jquery1.7.2.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffAutorisation" scope="request" class="fr.analogon.r2t.view.autorisation.BAffAutorisation"/>
<jsp:setProperty name="baffAutorisation" property="request" value="<%=request%>"/>
<script>
function valider()
{
   document.forms[0].choix.value="modifier";
   document.forms[0].action='./gestionAutorisation';  
   document.forms[0].submit(); 
}

function supprimer()
{
   document.forms[0].choix.value="supprimer";
   document.forms[0].action='./gestionAutorisation';  
   document.forms[0].submit(); 
}

function editerArrete(){
	window.open("editerDocument?module=autorisation&idautorisation=<%=baffAutorisation.getAutorisation().getIdAutorisation() %>","Autorisation","width=1000,height=700,scrollbars=yes,resizable=yes");
}


</script>

<body background="./images/nuages.jpg" topmargin="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Autorisations</font></td>
  </tr>
</table>
<form name="formulaire" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="idautorisation" value="<%=baffAutorisation.getIdautorisation() %>"/>
<input type="hidden" name="anneeExercice" value="<%=baffAutorisation.getAnneeExercice() %>"/>

<table border="1" cellpadding="2" cellspacing="2" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center">
 <tr>
     <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/autoris01.jpg" width="40" height="40"></b></font>
     </td>
 </tr>  
 <tr>
     <td  colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left"><font size="4"><b>MODIFIER AUTORISATION N° <%=baffAutorisation.getAutorisation().getIdAutorisation() %></b></font></p>
     </td> 
 </tr>
 <tr>    
	<td width="23%"><b>Référence du  dossier :</b></td>
	<td width="77%"><input  type="text" name="refDossier" size="33" value="<%=baffAutorisation.getAutorisation().getRefDossier()%>"></td>
 </tr>
 <tr>    
	<td width="23%"><b>Date de création :</b></td>
	<td width="77%"><%=baffAutorisation.getAutorisation().getDateCreation()%></td>
 </tr>
  <tr>    
	<td width="23%"><b>Etat :</b></td>
	<td width="77%"><%=baffAutorisation.getListeEtat() %></td>
 </tr>
 <tr>
      <td height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Information sur le redevable :</b></font></td>
 </tr>
 <tr>            
	<td width="23%"><b>Numéro du redevable :</b></td>
	<td width="77%"><%=baffAutorisation.getAutorisation().getNumRedevable() %></td>
 </tr>        
	<td width="23%"><b>Nom du redevable :</b></td>
	<td width="77%"><%=baffAutorisation.getRedevable().getNomRedevable()+" "+ baffAutorisation.getRedevable().getPrenomRedevable()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable=<%=baffAutorisation.getAutorisation().getNumRedevable() %>">
		<font face='Arial' color='#0000FF' size='2' >Lien vers le redevable</a></font></b></a>
	</td>
 </tr>
<tr>
<td width="23%"><b>Adresse du redevable :</b></td>
<td width="77%"><%=baffAutorisation.getRedevable().getAdressRedevable()%></td>       
</tr>
<tr>
<td width="23%"><b>Code postal :</b></td>
<td width="77%"><%=baffAutorisation.getRedevable().getCodePostaleRedevable()%></td>       
</tr>
<tr>
<td width="23%"><b>Ville :</b></td>
<td width="77%"><%=baffAutorisation.getRedevable().getVilleeRedevable()%></td>       
</tr>
           
<!-- ADRESSE -->
 <tr>
     <td height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>&nbsp;Information sur l'emplacement :</b></font></td>
 </tr>
     
<tr>
	<td width="23%"><b>Adresse :</b></td>
	<td width="77%"><%=baffAutorisation.getEmplacement().getAdresseComplete()%></td>				       
</tr>
<tr>
<td width="23%"><b>Code postal :</b></td>
<td width="77%"><%=baffAutorisation.getEmplacement().getCodePostal()%></td>       
</tr>
<tr>
<td width="23%"><b>Ville :</b></td>
<td width="77%"><%=baffAutorisation.getEmplacement().getVille()%></td>       
</tr>
 <tr>
     <td bgcolor="#AFF3BB" colspan="2" height="1"><p align="center"><img border="0" src="images/emplacement.png" width="40" height="40"></p></td>
 </tr>
 <tr>
        <td width="100%" height="20" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>
        &nbsp;Liste des emplacements et des ouvrages autorisés :</b></font>
        </td>
 </tr>
 <tr>
       <td width="100%" height="38" colspan="2"><%=baffAutorisation.getListeOuvrageAutorise()%></td>
 </tr>
  <tr>
   <td width="23%" align="center">
   	<div>
   		<%if (baffAutorisation.getAutorisation().getEtat().equals("Acceptée")) { %>			
			<a href="javascript:editerArrete();">
			<img src="./images/pdfbleue.png" border="0" height="21" width="20">&nbsp;Editer arrêté
			</a>	
		<%} %>
	</div>	
   </td>
   <td>
   	 <table border="0" width="100%" height="1">
   	 <tr>
        <td align="center" height="1">
        <a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>
      </td>
      <td align="center" height="1">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>
      </td>  
   </tr>
   </table>
   </td>
  
 </tr>      
</table>
</form>
</body>

