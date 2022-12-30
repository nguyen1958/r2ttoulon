<html>
<head>
<title>Test calendrier dateur</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<jsp:useBean id="beanRechercheFacture" scope="page" class="fr.analogon.r2t.batch.facture.BAffListeFactures"/>
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
</head>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>

<body background="./images/nuages.jpg" topmargin="0">

<form method="POST" action="entree?action=liste_factures.jsp">
<table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">RECHERCHE DE FACTURES :</font></b>
          </td>
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
          <td background="./images/fond_bleu.gif" width="218">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Numéro de la
            facture</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="222">
            <input type="text" name="numeroFacture" size="15">
          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Numéro du
            redevable</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="222">
            <input type="text" name="numeroRedevable" size="15">
          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Nom du
            redevable</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="222">
            <input type="text" name="nomRedevable" size="15">
          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de
            taxe : </font></b></font>
          </td>
          <td width="222">
            <jsp:getProperty name="beanRechercheFacture"  property="typeTaxe" />

          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Etat de
            la facture : </font></b></font>
          </td>
          <td width="222">       
            <jsp:getProperty name="beanRechercheFacture" property="etatFacture"/>
          </td>
            <td width="222">&nbsp;
            
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Montant de la
            facture</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="222">
            <input type="text" name="montantFacture" size="15">
          </td>
          <td width="698">&nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date de
            création de la facture : </font></b></font>
          </td>
          <td width="222">                      
            <input maxlength="10" type="text" name="dateCreationFacture" id="dateCreationFacture" size="12" />
            
            <img onclick="displayCalendar(document.forms[0].dateCreationFacture,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
          </td>        
            <td width="222">&nbsp;
            
          </td>
        </tr>

    
        <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Année : </font></b></font>
          </td>
          <td width="222">                      
            <input maxlength="10" type="text" name="anneeFacture" id="anneeFacture" size="12" />
            
          </td>        
          <td width="698">
            &nbsp;
          </td>
        </tr>       

    
        <tr>
          <td background="./images/fond_bleu.gif" width="218">&nbsp;
            
          </td>
          <td width="222">&nbsp;
            
          </td>
          <td width="698">
            <input type="image" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I3">
          </td>
        </tr>       
  </table>  
</form>






</body>
</html>

