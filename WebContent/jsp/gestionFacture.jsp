<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<%@ page language = "java" %>

<jsp:useBean id="baffFacture" scope="page" class="fr.analogon.r2t.batch.facture.BAffUneFacture" />
<jsp:setProperty name="baffFacture" property="request" value="<%=request%>"/>
<input type="hidden" value="<jsp:getProperty name="baffFacture" property="peutetreannule"/>">
<script>
function annulerFacture()
    {         
        document.forms[0].actionFacture.value="ANNULEE";		 
		 if(document.forms[0].motifAnnulation.value=="")
		       alert("Choisisez un motif d'annulation ");
		 else
		 {
		   if (confirm("Vous désirez annuler cette facture ?"))
          { 		 
		     document.forms[0].submit();       
		     //alert("Submit");
          } 
        }
    }
	
	
function reediterLaFacture()
{         
		  if (confirm("Vous désirez reediter cette facture ?"))
          { 		 		     
			 var numeroFacture =document.getElementById("numeroFacture").value;
			 var lienFacture =document.getElementById("lienFacture").value;	
			 document.getElementById("chargementEnCours").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";
			 var url = "./reediterFacture?numeroFacture="+numeroFacture ;
			 if (window.ActiveXObject)
			 {
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			 }
			 else if (window.XMLHttpRequest)
			 {
				httpRequest = new XMLHttpRequest();
			 }			
			 httpRequest.open("GET", url, true);
			 httpRequest.onreadystatechange = function() {processRequestRediterFacture(); } ;
			 httpRequest.send(null);	
			 //alert("chargement")		
          }         
}


function relancerLaFacture()
{         
		  if (confirm("Vous désirez lancer la relance de cette facture ?"))
          { 		 		     
			 var numeroFacture =document.getElementById("numeroFacture").value;
			 var lienFactureRelance =document.getElementById("lienFactureRelance").value  ;
			 var nomFacturerelance= numeroFacture +"relance" ;	
			 lienFactureRelance = lienFactureRelance.replace(numeroFacture,nomFacturerelance);
             lienFactureRelance = lienFactureRelance.replace("Facture Num","Relance de la facture");
			 document.getElementById("lienFactureRelance").value = lienFactureRelance;		 
			 
			 document.getElementById("chargementEnCours").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";
			 var url = "./relancerFacture?numeroFacture="+numeroFacture ;
			 if (window.ActiveXObject)
			 {
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			 }
			 else if (window.XMLHttpRequest)
			 {
				httpRequest = new XMLHttpRequest();
			 }			
			 httpRequest.open("GET", url, true);
			 httpRequest.onreadystatechange = function() {processRequestRelancerFacture(); } ;
			 httpRequest.send(null);	
			 //alert("chargement")		
          }         
}
	
function processRequestRelancerFacture()
{

	if (httpRequest.readyState == 4)	
	{
		if(httpRequest.status == 200)
		{		    
			var lienFactureRelance =document.getElementById("lienFactureRelance").value;
			
			document.getElementById("chargementEnCours").innerHTML = "Relance de la facture: "+lienFactureRelance;	
		}
		else
		{
			alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
		}
	}
}


function processRequestRediterFacture()
{

	if (httpRequest.readyState == 4)	
	{
		if(httpRequest.status == 200)
		{		    
			var lienFacture =document.getElementById("lienFacture").value;
			document.getElementById("chargementEnCours").innerHTML = "Nouvelle facture: "+lienFacture;	
		}
		else
		{
			alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
		}
	}
}















</script>


<title>Gestion Facture </title>
</head>

<%
String regie = (String)session.getAttribute("regie");
%>
<Form action="gestionFacture">
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">
<input type="hidden" name="typeTaxe" value="<jsp:getProperty name="baffFacture" property="typeTaxe"/>">
<input type="hidden" name="actionFacture">
<input type="hidden" name="numeroFacture" id="numeroFacture" value="<jsp:getProperty name="baffFacture" property="numeroFacture"/>">
<input type="hidden" name="lienFacture" id="lienFacture" value="<jsp:getProperty name="baffFacture" property="lienFacture"/>">
<input type="hidden" name="lienFactureRelance" id="lienFactureRelance" value="<jsp:getProperty name="baffFacture" property="lienFacture"/>">





<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="119" width="100%">
 
<tr>
    <td bgcolor="#AFF3BB" height="40" colspan="2" width="1113"><p align="center"><a name="rapport"><img border="0" src="images/pdf.jpg" width="40" height="40">&nbsp;</a></td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22" colspan="2" width="1113"><b><font size="4"><span style="text-transform: uppercase">Facture
      </span></font></b><span style="text-transform: uppercase"><b><font size="4">:</font></b></span></td>
</tr>

<tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>N° de facture :</b> </td>
    <td width="916" height="23" align="left">
      <p align="left">  <jsp:getProperty name="baffFacture" property="numeroFacture"/> </td>
	  
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>N° de titre :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="numeroTitre"/> </td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Lien facture&nbsp; :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="lienFacture"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <% if ( ! baffFacture.getEtatFacture().equalsIgnoreCase("ANNULEE")) { %>
      &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:reediterLaFacture()"><b>Re-editer la facture
      </b>
      </a>      
	<% } %>
	<% if (  regie !=null && regie.equalsIgnoreCase("true"))  { %> 
	    <% if ( ! baffFacture.getEtatFacture().equalsIgnoreCase("ANNULEE")) { %>
          &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:relancerLaFacture()"><b>Relance de la facture
      </b>
      </a>      
	   <% } %>
	<% } %>
     </td>
  </tr>

 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Type de taxe :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="typeTaxe"/> </td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Date de création :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="dateCreationFacture"/></td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>N° de bacth :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="numeroBatch"/> </td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Redevable :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="lienRedevable"/> </td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Montant :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="montant"/> </td>
  </tr>
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Solde :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="solde"/> </td>
  </tr>
  <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Etat :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="etatFacture"/> </td>
  </tr>
  
<% if (  regie !=null && regie.equalsIgnoreCase("true"))  { %>  
  <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Historique des paiements :</b>
	</td>
    <td width="916" height="23" align="left">
	<jsp:getProperty name="baffFacture" property="historiquePayement"/>
	</a></td>
  </tr>   
  
<% } %> 
  
  
  
  
  <% if ( baffFacture.getEtatFacture().equalsIgnoreCase("ANNULEE")) { %>  
  <tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Date
      annulation :</b> </td>
    <td width="916" height="23" align="left"><jsp:getProperty name="baffFacture" property="dateAnnulation"/></td>
  </tr>   
  
<% } %>
<tr>
    <td width="195" background="./images/fond_bleu.gif" height="23" align="left"><b>Motif annulation :</b> </td>
    <td width="916" height="23" align="left"> <jsp:getProperty name="baffFacture" property="motifAnnuation"/> 
	<% if ( ! baffFacture.getEtatFacture().equalsIgnoreCase("ANNULEE") 
			 &&
			 baffFacture.getPeutetreannule().equalsIgnoreCase("true")
			) { %>
      <a href="javascript:annulerFacture()"><b>
         Annuler la facture</b></a></td>
		 <% } %>
  </tr>

<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    Boolean peutAcceder = Boolean.valueOf(baffFacture.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
 if (peutAcceder) { 
%>


 
 <tr>
    <td width="195" background="./images/fond_bleu.gif" height="22" align="left"> &nbsp; </td>
    <td width="916" height="22" align="center">
     <p align="center"> 
     <div id="chargementEnCours">
     </td>
     
  </tr>  
    
<% } %>

 </table>

<p align="center"><a href="./entree?action=liste_factures.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">iste des
</font></b><font face="Arial" size="3"><b>factures</b></font></font></a>

</body>

</Form>
</html>
   
    
    
 
 













