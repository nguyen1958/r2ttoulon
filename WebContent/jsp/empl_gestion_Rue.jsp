<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen"><SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<style type="text/css">
<!--
.Style4 {font-size: 9px}
-->
</style>
</head>
<body background="./images/nuages.jpg" top margin="0" >
<script type="text/javascript" src="./resources/js/controle.js"></script>  





<%@ page language = "java" %>
<jsp:useBean id="beanRueRecherche1" scope="request" class="fr.analogon.r2t.rue.BAffRue"/>
<jsp:setProperty name="beanRueRecherche1" property="request" value="<%=request%>"/>

<script type="text/javascript">  


function rechercherQuartier(nomQuartier)
{
        //alert(numEmplacement);        
        var url = './rechercheQuartier?nomQuartier='+nomQuartier;    
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestQuartier(); } ;
        httpRequest.send(null); 
}  
  
function processRequestQuartier()
{       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("quartier");
              var contenuListQuartier="<b><font face='Arial' size='2'>Choix du quartier :  </font></b>";
              contenuListQuartier=" "+contenuListQuartier+"<select name='listDesQuartier'  onclick='majQuartier(this.value)' >";
              //alert(node.length);
				  for (i = 0; i < node.length; i++)
				  {				                              
                 var quartier= xmldoc.getElementsByTagName("quartier")[i].childNodes[0].nodeValue;                
                 contenuListQuartier=contenuListQuartier+"<option value='"+quartier+"'>"+quartier+"</option>";
               }             	                           

              if (node.length==0)           
              {
                contenuListQuartier=contenuListQuartier+"<option>    PAS DE RESULTATS    </option>"; 
                 document.getElementById("listeQuartier").innerHTML = "";
                
              }
               contenuListQuartier=contenuListQuartier+"</select>"; 
               document.getElementById("listeQuartier").innerHTML = contenuListQuartier;  
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 




function majQuartier(valeur)
{
    res= valeur.indexOf("PAS", 0);
    if(res == -1)
    {
      document.forms[0].quartierRue.value=valeur;
    }
}


















function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer cette rue  ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";
				document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer cette rue, il y a des éléments liés ");	   
}

function valider()
{
     var verfiPrefixeAdresseRue= verifier("Prefixe",document.forms[0].prefixeAdresseRue.value); 
     //var verfiLiaisonAdresseRue= verifier("liaison",document.forms[0].liaisonAdresseRue.value); 
     var verfiNomAdresseRue= verifier("Adresse",document.forms[0].nomAdresseRue.value); 
     var verfiCodeRivolieRue= verifier("Code Rivolie",document.forms[0].codeRivolieRue.value); 
     var verfiQuartierRue= verifier("Quartier",document.forms[0].quartierRue.value); 
	 
	 if ( verfiPrefixeAdresseRue == 0 &&  verfiNomAdresseRue== 0 && 
		  verfiCodeRivolieRue== 0 &&  verfiQuartierRue== 0 )
     {
    		 document.forms[0].submit();  	 
     }
     else
     {
       //alert("Champs manquant !!")

     }
}



</script>



<form method="POST" action="gestionRue" name="formulaire" >
  <div id="calcontainer"></div>
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden"  name="peutEtreSupprimer" size="20" value="<jsp:getProperty name="beanRueRecherche1" property="peutEtreSupprimer" />" >
<a href="javascript:rechercherLeRemplacant();" target="bas"></a>
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="317">
 <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/users.png" width="40" height="40"></b></font>          </td>
 </tr>
   

    <tr>
      <td width="1133" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!beanRueRecherche1.getCreation()){%>  
	              <font size="4"><b>MODIFIER RUE :&nbsp</b></font>
                  <% }else{ %>                 
                 <font size="4"><b>AJOUTER </b></font>  
	              <font size="4"><b> UNE RUE  </b></font>
              <font size="4"><b> :&nbsp</b></font>                 
		      <% } %>                 
        </p>      </td>
    </tr>
<% if(!beanRueRecherche1.getCreation()){%> 
    <tr>
      <td width="232" height="23">
        <b><font face="Arial" size="2">&nbsp;Code rue :</font></b>      </td>
      <td width="899" height="23"><input type="text" readonly name="codeRue" size="20"
	   value="<jsp:getProperty name="beanRueRecherche1" property="codeRue" />" ></td>
    </tr>
    
<%}%>

 

 
    
    

    <tr>
      <td width="1131" height="21" colspan="2">&nbsp;      </td>
    </tr>

     <tr>
      <td width="232" height="23"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Préfixe</font><i><font face="Arial" size="2" color="#FF0000"> *</font></i></b> <font face="Arial" size="2"><b>:  </b></font> </span></td>
      <td width="899" height="23">
        <input type="text"  name="prefixeAdresseRue" size="30" value="<jsp:getProperty name="beanRueRecherche1" property="prefixeAdresseRue"  />" >
        <span class="Style4" style="margin-left: 5; margin-right: 5"><b><font face="Arial">Exemple</font></b> <font face="Arial"><b>: rue, place...</b></font></span></td>
    </tr>
	
	 <tr>
      <td width="232" height="24"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Liaison</font></b>
        <font face="Arial" size="2"><b>:</b></font> </span></td>
      <td width="899" height="24">
        <input type="text"  name="liaisonAdresseRue" size="30" value="<jsp:getProperty name="beanRueRecherche1" property="liaisonAdresseRue"  />" > 
        <span class="Style4" style="margin-left: 5; margin-right: 5"><b><font face="Arial">Exemple</font></b> <font face="Arial"><b>:
        de …, de la …, du …</b></font></span></td>
    </tr>


    <tr>
      <td width="232" height="24"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2"> Nom
        rue </font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b> <font face="Arial" size="2"><b>:</b></font> </span></td>
      <td width="899" height="24">
        <input type="text"  name="nomAdresseRue" size="30" value="<jsp:getProperty name="beanRueRecherche1" property="nomAdresseRue"  />" > 
        <span class="Style4" style="margin-left: 5; margin-right: 5"><b><font face="Arial">Exemple</font></b> <font face="Arial"><b>:
        Chatrine... </b></font></span> </td>
    </tr>

    <tr>
      <td height="24" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Code
        rivoli </font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b>
        <font face="Arial" size="2"><b>:</b></font>      </td>
      <td width="899" height="24">
        <input type="text"  name="codeRivolieRue" size="30" value="<jsp:getProperty name="beanRueRecherche1" property="codeRivolieRue" />" >      </td>
    </tr>

     
	
    <tr>
      <td height="24" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Début
        numéro  
        </font></b>
        <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b>      </td>
      <td width="899" height="24"><jsp:getProperty name="beanRueRecherche1" property="debutNumeroRue" /></td>
    </tr>
    

    <tr>
      <td height="24" width="232">
      <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Fin
      numéro </font></b> <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b> </td>
      <td width="899" height="24"> <jsp:getProperty name="beanRueRecherche1" property="finNumeroRue" /></td>
    </tr>
 <tr>
      <td height="24" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Code
        postal </font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b>
        <font face="Arial" size="2"><b>:</b></font>      </td>
      <td width="899" height="24">
        <input type="text"  name="codePostal" size="5" value="<jsp:getProperty name="beanRueRecherche1" property="codePostal" />" >      </td>
 </tr>
    <tr>
      <td height="24"><p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Quartier  </font></b> <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b> </td>
      <td height="24">
        <input type="text"  name="quartierRue" size="30" 
	  value="<jsp:getProperty name="beanRueRecherche1" property="quartierRue" />" >
	  <a href="javascript:rechercherQuartier(document.forms[0].quartierRue.value);" target="bas"><font face="Arial" size="3">
      <img height="20" src="./images/lettre_r.gif" width="20" align="absMiddle" border="0"> <b>echercher quartier </b></font></a></td>
    </tr>
    <tr>
      <td height="19"><p style="margin-left: 5; margin-right: 5">&nbsp;-</td>
      <td height="19"><div id="listeQuartier" style="width: 593; height: 20"> </div></td>
    </tr>
    

 <tr>
      <td height="19" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Remarques
        :</font></b>      </td>
      <td width="899" height="19"><label>
        <textarea name="remarqueRue" cols="70" rows="4"><jsp:getProperty name="beanRueRecherche1" property="remarqueRue" /></textarea>
      </label>	  </td>
 </tr>
 




<%
	String marche = (String)session.getAttribute("marche");
	if (  marche !=null && marche.equalsIgnoreCase("true")) 
	{ 
	%>	  
		<tr>
	      <td width="269" height="23">
	        <b><font face="Arial" size="2">&nbsp;Marché </font><font face="Arial" size="2">
	        :</font></b>
	      </td>
	      <td width="868" height="23">
	        <jsp:getProperty name="beanRueRecherche1" property="familleMarche"/>
	      </td>
	    </tr> 	  
<% } else {  %>
	<input type="hidden" name="familleMarche" id ="familleMarche" value="0">
<% }   %>

<%
	if (  marche !=null && marche.equalsIgnoreCase("true")) 
	{ 
	%>	  
		<tr>
	      <td width="269" height="23">
	        <b><font face="Arial" size="2">&nbsp;Code Secteur </font><font face="Arial" size="2">
	        :</font></b>
	      </td>
	      <td width="868" height="23">
	        <input type="text"  name="codeSecteur" size="30" value="<jsp:getProperty name="beanRueRecherche1" property="codeSecteur" />" >
	      </td>
	    </tr> 	  
<% } else {  %>
	<input type="hidden" name="codeSecteur" id ="codeSecteur" value="0">
<% }   %>













 



  


    <tr>
      <td width="232" height="19">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
      Champs obligatoires</i></font></b>      </td>
      <td width="899" height="19">&nbsp;      </td>
    </tr>
    <tr>
      <td width="1133" colspan="2" height="1">
        <table border="0" width="100%">
          <tr>
 
    
  <% if(!beanRueRecherche1.getCreation()){%>   
    <td align="center"><a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a></td>
      <td align="center" width="375">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>      </td>
 <%}else{%>
 
   <td align="center" width="374">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20" ></a>        </p>      </td>
  <%}%>
          </tr>
        </table>      </td>
    </tr>
  </table>  
</form>



<p align="center"><font face="Arial" size="3" color="#0000FF"><b><a href="entree?action=liste_Rue.jsp">
<img height="20" border="0" align="absmiddle" width="20" src="images/lettre_l.gif"/>iste des rues</a></b></font></p><p align="center">&nbsp;</p>
</body>

</html>













