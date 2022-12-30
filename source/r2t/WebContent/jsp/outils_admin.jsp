<html>
<head>
<title>Test calendrier dateur</title>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />   
<%@ page language = "java" %>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.administration.BAffAdministration"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/> 
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/jquery1.7.2.js"></script>
<script>	
 function SupprimerFichierTemporaire()
	{      
  	  
        var url = './gestionParametreAdministration?SupprimerFichierTemporaire=ok';
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestSupprimerFichierTemporaire(); } ;
        httpRequest.send(null); 
	}
	
	
	function processRequestSupprimerFichierTemporaire()
   {       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {                        
               document.getElementById("info").innerHTML = "Suppression des fichiers temporaires effectuée avec succès , vous devez vous reconnecter pour prendre en compte les nouveaux paramètres ";  
           }
            else
            {
                alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
            }             
        }
   }  

	function changerNiveauDebogage()
	{      
  	    alert("NiveauDebogage= "+niveauDebogageApplication);
  	    niveauDebogageApplication= document.forms[0].niveauDebogageApplication.value;
        var url = './gestionParametreAdministration?niveauDebogageApplication=' + niveauDebogageApplication;
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequest(); } ;
        httpRequest.send(null); 
	}
	
	
	function processRequest()
   {       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {                        
               document.getElementById("info").innerHTML = "Mise à jour du niveau de débogage de l'application effectuée avec succès , vous devez vous reconnecter pour prendre en compte les nouveaux paramétres ";  
           }
            else
            {
                alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
            }             
        }
   }  
   
	function changerNiveauDebogageR2TMobile()
	{      
  	    alert("changerNiveauDebogageR2TMobile");
  	    niveauDebogageR2TMobile= document.forms[0].niveauDebogageR2TMobile.value;
        var url = './gestionParametreAdministration?niveauDebogageR2TMobile=' + niveauDebogageR2TMobile;
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestNiveauDebogageR2TMobile(); } ;
        httpRequest.send(null); 
	}
	
	
	function processRequestNiveauDebogageR2TMobile()
   {       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {                        
               document.getElementById("info").innerHTML = "Mise à jour du niveau de débogage de R2T Mobile Service effectuée avec succès , vous devez vous reconnecter pour prendre en compte les nouveaux paramétres ";  
           }
            else
            {
                alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
            }             
        }
   } 

	
	
	
	
	
    function supprimerFichierTemp()
	{      
	   alert("supprimerFichierTemp");
	}
	
	function effacerHistorique()
	{ 
            ret=confirm("Etes-vous sûr de vouloir supprimer tout l'historique ?");
            if (ret == true)
            {
					//document.forms[0].action ="";
                    document.forms[0].choix.value="supprimer";
                    document.forms[0].submit();
            }

	}
	
	function debloquerBatch()
	{
    	$.ajax( {
  	        type: "POST",
  	        url: "rechercheAction",
  	        data:{
  	        	choix:"suppression",
  	        	action:"batchTraitement",
  	        	typeTaxe:$("#typeDeTaxe").val()
  	        },
  	        dataType: "json",
  	        success: function(response) {
  	        	 	$('#info').html("Opération effectuée ...");
  	        },
  	        error: function(error) {
  	        	alert("Erreur rechercheAction \n"+ JSON.stringify(error));
  	        }
  	    })
     
	}
	
  </script>

</head>

<body background="./images/nuages.jpg" topmargin="0">

<form method="POST" action="entree?action=HistoriqueAction.jsp" >
<table border="1" cellpadding="0" cellspacing="0" width="1140" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        
        <tr>
          <td bgcolor="#AFF3BB" width="1136">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td bgcolor="#AFF3BB" width="1136">
            <b><font size="4">OUTILS D'ADMINISTRATION :</font></b>          </td>
        </tr>
   
  </table>  
      

<table border="1" cellpadding="0" cellspacing="0" width="99%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="1">

      <tr>
        <td width="101%" height="1" colspan="2" align="center" bgcolor="#FFFFE8">
         <div id="info"><font color="#FF0000"><b><img border="0" src="./images/informationJaune.png" width="16" height="14">&nbsp;</b></font> </div>
          </td>
      </tr>   


      <tr>
        <td width="22%"  height="1" background="./images/fond_bleu.gif">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Niveau débogage
          pour R2T </font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"> :</font></b>
          </td>
        <td width="79%" height="1">
         <jsp:getProperty name="bean" property="niveauDebogageApplication"/>
        </td>
      </tr>   


      <tr>
        <td width="22%"  height="1" background="./images/fond_bleu.gif">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Niveau débogage
          pour R2T Mobile </font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"> :</font></b>
          </td>
        <td width="79%" height="1">
         <jsp:getProperty name="bean" property="niveauDebogageR2TMobile"/>
        </td>
      </tr>


      <tr>
        <td width="22%" background="./images/fond_bleu.gif" height="1">
          &nbsp;<b><font size="2" face="Arial">Fichiers temporaires</font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"> :</font></b>
          </td>
        <td width="79%" height="1">
         <a href="javascript:SupprimerFichierTemporaire()">
        Supprimer les fichiers temporaires
         </a>
        </td>
      </tr>  
      <!-- Paul Débloquer le batch de traitement -->
      <tr>
        <td width="22%" background="./images/fond_bleu.gif" height="1">
          &nbsp;<b><font size="2" face="Arial">Débloquer batch</font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"> :</font></b>
          </td>
        <td width="79%" height="1">
         	<jsp:getProperty name="bean" property="tousTypeDeTaxe"/>&nbsp;
         	<font size="3" face="Arial"><a href="javascript:debloquerBatch();">
         		<img src="images/lettre_d.gif" align="absmiddle" border="0" width="20" height="20">ébloquer</a>
         	</font>
        </td>
      </tr>    


      <tr>
        <td width="22%" background="./images/fond_bleu.gif" height="1">
          &nbsp;-
          </td>
        <td width="79%" height="1">
       
        &nbsp;
       
        </td>
      </tr>   


</table>

</form>






</body>
</html>

