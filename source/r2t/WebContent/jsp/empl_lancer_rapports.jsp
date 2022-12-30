<html>
<head>
<title>Test calendrier dateur</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script>  
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffListeFactures"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
</head>

<script>
 
    function chargement()
	{
	  contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu;
       document.forms[0].submit();
       document.getElementById("chargement").innerHTML = contenu;
	}
	
	function lancerBatchChangementAdresseRedevable()	
	{
       chargement();       
       document.forms[0].action="./entree?action=gestionBatchChangementAdressRedevable.jsp";
	    //alert(document.forms[0].action);
	    document.forms[0].submit();
	    
	}
	
	function lancerBatchSuiviDeFacturation()	
	{
	    chargement();
	    verifDateDebut= verifier("date début de periode ",document.forms[0].debutPeriode.value);     
  	    verifDateFin= verifier("Date Fin de periode ",document.forms[0].finPeriode.value);     
		var pos = document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(document.forms[0].typeTaxe.value);
       if(pos !=-1)
       {
         if( verifDateDebut==0  && verifDateFin ==0 )
         {
           document.forms[0].action="./entree?action=gestionBatchSuivieFacturation.jsp";	      
	        document.forms[0].submit();
	      }
	   
	   }
	   else
	   {
	      alert ("Vous n'avez pas le droits de lancer des rapports du type "+ document.forms[0].typeTaxe.value);
	   }
	}	
  </script>

<body background="./images/nuages.jpg" topmargin="0">

<form method="POST"  >
<%
String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");  
%>  
<input type="hidden" name="listeLibelleDesTypesDeTaxeAutorise" value="<%out.print(listeLibelleDesTypesDeTaxeAutorise); %>" >

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="273">
        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/reclamation.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">&nbsp;RAPPORTS :</font></b>          </td>
        </tr>
   
        <tr>
          <td width="1138" bgcolor="#FFFFCC" height="19" colspan="3">
            <b><font size="3">Rapport de suivi de facturation :</font></b>          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218" height="23">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Début de période</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>          </td>
          <td width="222" height="23">
            <input type="text" maxLength="10" name="debutPeriode" id="debutPeriode" size="15">
             
            <img onclick="displayCalendar(document.forms[0].debutPeriode,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
&nbsp;          </td>
          <td width="698" height="23">
            &nbsp;&nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="218" height="24">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Fin de période</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>          </td>
          <td width="222" height="24">
            <input type="text" maxLength="10" name="finPeriode" id="finPeriode" size="15">
             
            <img onclick="displayCalendar(document.forms[0].finPeriode,'dd/mm/yyyy',this)"
                 src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
&nbsp;          </td>
          <td width="698" height="24">&nbsp;          </td>
        </tr>

    
        <tr>
          <td background="./images/fond_bleu.gif" width="218" height="24">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de
            taxe : </font></b></font>          </td>
          <td width="222" height="24">
            <jsp:getProperty name="bean"  property="typeTaxe" />          </td>
          <td width="698" height="24">&nbsp;          </td>
        </tr>

    
        <tr>
          <td background="./images/fond_bleu.gif" colspan="3" height="22" ><div align="center"><a href="javascript:lancerBatchSuiviDeFacturation();"><font color="#0000FF"><img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">ancer
              le Batch</font></b></font></a></div> </td>
        </tr>

    
        <tr>
          <td  width="218" height="20"></td>
          <td width="222" height="20"></td>
          <td width="698" height="20"></td>
        </tr>

    
      <tr>
          <td  width="218" height="20"></td>
          <td width="222" height="20"></td>
          <td width="698" height="20"></td>
        </tr>

        <tr>
          <td bgcolor="#FFFFCC" height="19" colspan="3">
            <b><font size="3">Rapport de changement d'adresse des redevables :</font></b>          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" colspan="3" height="22" ><div align="center"><a href="javascript:lancerBatchChangementAdresseRedevable();"><font color="#0000FF"><img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">ancer
              le Batch</font></b></font></a></div> </td>
        </tr>
        
</table>  
</form>
<div id="chargement" style="width: 100%; height: 71" > 
</div>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>






</body>
</html>

