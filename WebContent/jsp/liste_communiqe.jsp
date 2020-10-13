<html>
<head>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.view.batch.BAffListeCoomunique" />
<jsp:useBean id="beanDivers" scope="request" class="fr.analogon.r2t.view.batch.BAffDivers"/>

<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<title>Liste des communiqués</title>
</head>

<script type="text/javascript">  
function getRoleCommunique(typeRapport)
{



    if (typeRapport == "generale" )
		document.getElementById("roleCommunique").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";
	if (typeRapport == "detaille" )
		document.getElementById("roleCommuniqueDetaille").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";	
		
	var nomQuatier=document.forms[0].nomQuartier.value;
	var numSecteur=document.forms[0].numSecteur.value;
	//alert("typeRapport="+typeRapport);
	//alert(nomQuatier);
    var url = "./getRoleComnunique?nomQuartier="+nomQuatier+"&numSecteur="+numSecteur+"&typeRapport="+typeRapport;
    if (window.ActiveXObject)
    {
       httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequest = new XMLHttpRequest();
    }
    httpRequest.open("GET", url, true);        
    httpRequest.onreadystatechange = function() {processRequestRoleCommunique(typeRapport); } ;
    httpRequest.send(null);     
}


function processRequestRoleCommunique(typeRapport)
{
   
   if (httpRequest.readyState == 4)
   {
	    if(httpRequest.status == 200)
	    {
	       var xmldoc = httpRequest.responseXML.documentElement; 
		  if (typeRapport == "generale" )
		  {
		    var lienRoleCommunique= xmldoc.getElementsByTagName("lienRoleCommunique")[0].childNodes[0].nodeValue;                
			var link="<a href=" + lienRoleCommunique + " target=\"_blank\" > RoleCommunique.pdf </a>";
			document.getElementById("roleCommunique").innerHTML = link;  
			
		  }
		  
		  if (typeRapport == "detaille" )
		  {
		    var lienRoleCommunique= xmldoc.getElementsByTagName("lienRoleCommunique")[0].childNodes[0].nodeValue;                
			var link="<a href=" + lienRoleCommunique + " target=\"_blank\" > RoleCommuniqueDetaille.pdf </a>";
			document.getElementById("roleCommuniqueDetaille").innerHTML = link;  			
		  }
	    }
	    else
	    {
	        alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);    
		}
	} 
}
</script>  



<body background="./images/nuages.jpg" topmargin="0">
<form method="POST">
<input type="hidden" name="controle" value="<jsp:getProperty name="bean" property="controle"/>">
<input type="hidden" name="nomQuartier" value="<jsp:getProperty name="bean" property="nomQuartier"/>">
<input type="hidden" name="numSecteur" value="<jsp:getProperty name="bean" property="numSecteur"/>">
<%        
	String ville = (String)session.getAttribute("ville"); 
%>

 
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="129">
 
  <tr>
          <td width="100%" bgcolor="#AFF3BB" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>
          <td width="100%" bgcolor="#AFF3BB" height="32">
            <p>
              <b><font size="4">LISTE DES COMMUNIQUES POUR LE  <font color="#FF0000"><jsp:getProperty name="bean" property="dateDuJour"/></font>, il y a 
               <font color="#FF0000"><jsp:getProperty name="bean" property="nbreOuvrageAControle"/></font> ouvrages à contrôler
               
             </font></b>          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="19">        
         <jsp:getProperty name="bean" property="listeCommunique"/>
		 
          </p>
        </td>
  </tr>
     

     
      <tr>
        <td width="1139" align="center" height="28">          
         
         
<div id="roleCommunique">
<a href="javascript:getRoleCommunique('generale')" > Rôle communiqué </a>
</div>          

<% if ( ! ville.equalsIgnoreCase("Bordeaux")) { %>
	<div id="roleCommuniqueDetaille">
	<a href="javascript:getRoleCommunique('detaille')" > Rôle communiqué detaillé</a>
	</div>          
<% } %>         
         
        </td>
  </tr> 
  </table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<script>
   if(document.forms[0].controle.value =="false")
    document.getElementById("roleCommunique").innerHTML = "";
	
</script>







</body>

