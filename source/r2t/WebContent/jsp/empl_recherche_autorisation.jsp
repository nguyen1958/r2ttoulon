<html>
<head>

<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<script type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/jquery.js"></script>
 
<%@ page language = "java" %>
 <jsp:useBean id="baffAutorisation" scope="request" class="fr.analogon.r2t.view.autorisation.BAffAutorisation"/>
 <jsp:setProperty name="baffAutorisation" property="request" value="<%=request%>"/>

  <script>
  $(function() {
	$('div[id]').one('click', function(){
		  var idString = $(this).text() + ' = ' + $(this).attr('id');
		  $(this).text(idString);
	});

  });
  		  
  function ok()
  {
    if ( isNaN(document.forms[0].numAutorisation.value))
    	alert("Le numéro d'autorisation doit etre un nombre ! ");
    else   
    	document.forms[0].submit();
  }

  
  </script>
</head>
<body background="./images/nuages.jpg" >

<table border="0" cellpadding="0" cellspacing="0" width="100%">
   <tr>
     <td background="./images/fond_trait.gif" width="15%">
       <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
     </td>
   <td background="./images/fond_trait.gif" valign="top">
     <p align="right"><font face="Arial" size="5">Autorisations</font></td>
 </tr>
 </table>
<form method="POST" action="entree?action=liste_autorisation.jsp" onSubmit="ok()" >
  <input type="hidden" name="choix" value="rechercher" />
  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="387">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">RECHERCHE DES AUTORISATIONS :</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche </font><font size="2" face="Arial">par numéro d'autorisation</font></td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">N°
            Autorisation&nbsp; :</font></b></font>
          </td>
          <td width="239" >
            <input type="text" name="numAutorisation" size="33" >
          </td>
          <td width="631" >
             <A href="javascript:ok();"><img border="0" src="./images/ok.gif" width="20" height="20" align="left"></a>
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
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Référence
            du dossier :</font></b></font>
          </td>
          <td width="239" >
            <input type="text" name="refDossier" size="33" >
          </td>
          <td width="631" >
            &nbsp;</td>
        </tr>
        
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Date de création 
            de l'autorisation :</font></b></font>
          </td>
          <td width="239" >
            <input type="text"  readOnly name="dateCreation" id="dateCreationAlerte" size="15"><b><font size="2" face="Arial">        			
			<img onclick="displayCalendar(document.forms[0].dateCreation,'dd/mm/yyyy',this)"
			src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" /></font></b>
          </td>
          <td width="631" >
            &nbsp;</td>
        </tr>

		<tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Nom/Prénom
            du redevable :</font></b></font>
          </td>
          <td width="239" >
            <input type="text" name="nomRedevable" size="33" >
          </td>
          <td width="631" >
            &nbsp;</td>
        </tr>
     
		<tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Adresse
            de l'emplacement :</font></b></font>
          </td>
          <td width="239" >
            <input type="text" name="adresseEmplacement" size="66" >
          </td>
          <td width="631" >
            &nbsp;</td>
        </tr>
       
		<tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Type
            de taxe :</font></b></font>
          </td>
          <td width="239" >
            <jsp:getProperty name="baffAutorisation" property="tousTypeDeTaxe"/>   
          </td>
          <td width="631" >
            &nbsp;</td>
        </tr>
       
		<tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Etat :</font></b></font>
          </td>
          <td width="239" >
            <select size="1" name="etatAutorisation">
            <option selected value=""></option>
            <option value="En cours">En cours</option>
            <option value="Acceptée">Acceptée</option>
            <option value="Refusée">Refusée</option>
          </select>        
          </td>
          <td width="631" >
            <p align="center">
             <A href="javascript:ok();">                
              <img border="0" src="./images/ok.gif" width="20" height="20" align="left">         
             </a>
            </p>
        </tr>
        </table>
      </center>
    </div>
  </div>
  
 
</form>

</body>

</html>
