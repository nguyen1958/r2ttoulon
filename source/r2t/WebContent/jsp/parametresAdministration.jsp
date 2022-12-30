<script>
var httpRequest;
var httpRequest1;


function selectionnerLigneRaisonSocial()
{
   document.forms[0].raisonSocial.value = document.forms[0].listeRaisonSocial.options[document.forms[0].listeRaisonSocial.selectedIndex].value;  
   document.forms[0].newRaisonSocial.value="";   
}

function Ajouter(libelle) 
{
    var o=new Option(libelle,libelle);
    document.forms[0].listeTypeAnnulation.options[document.forms[0].listeTypeAnnulation.options.length]=o;
}

function AjouterRaisonSocial(libelle) 
{
    var o=new Option(libelle,libelle);
    document.forms[0].listeRaisonSocial.options[document.forms[0].listeRaisonSocial.options.length]=o;
}



function SupprimerTout(list) 
{
   list.options.length=0;
}

function chargerListRaisonSocial()
{
    
    var url ="./gestionParametreAdministration?type=RaisonSocial";		
    if (window.ActiveXObject)
    {
        httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequest1 = new XMLHttpRequest();
    }
    httpRequest1.open("GET", url, true);     
    
    httpRequest1.onreadystatechange = function() {processRequestRaisonSocial(); } ;
    httpRequest1.send(null); 
    
}


function ajouterRaisonSocial()
{
    var newRaisonSocial = document.forms[0].newRaisonSocial.value;	
    if(newRaisonSocial.length > 0)
	{
		var url ='./gestionParametreAdministration?type=RaisonSocial&action=ajouterRaisonSocial&newValeur='+newRaisonSocial;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestRaisonSocial(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Donnez un nouveau libelle pour la raison social ");
	}
}

function modifierRaisonSocial()
{ 

    var oldMotif= document.forms[0].listeRaisonSocial.options[document.forms[0].listeRaisonSocial.selectedIndex].value;
	var newRaisonSocial=document.forms[0].raisonSocial.value;
	
    if(newRaisonSocial.length > 0 )
	{
		var url ="./gestionParametreAdministration?type=RaisonSocial&action=modifierRaisonSocial&oldValeur="+oldMotif+"&newValeur="+newRaisonSocial;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestRaisonSocial(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libellé de la liste pour le modifier");
	}
}

function supprimerRaisonSocial()
{
    raisonSocial =document.forms[0].raisonSocial.value;
    if(raisonSocial.length > 0)
	{
		var url ='./gestionParametreAdministration?type=RaisonSocial&action=supprimerRaisonSocial&oldValeur='+raisonSocial;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestRaisonSocial(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libelle de la liste pour le supprimer");
	}
}

function processRequestRaisonSocial()
{       
    if (httpRequest.readyState == 4)
    {	   

        if(httpRequest1.status == 200)
        {
           var xmldoc = httpRequest1.responseXML.documentElement;
           var node = xmldoc.getElementsByTagName("raisonSocial");   
			SupprimerTout(document.forms[0].listeRaisonSocial);	
		   // alert("taille="+node.length);
            for (i = 0; i < node.length; i++)
			{ 
			   var RaisonSocial= xmldoc.getElementsByTagName("raisonSocial")[i].childNodes[0].nodeValue;
				AjouterRaisonSocial(RaisonSocial); 
			}
		document.forms[0].newRaisonSocial.value="";
		document.forms[0].raisonSocial.value="";
        }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest1.status +":"+ httpRequest1.statusText);
        }
     }
}


function selectionnerLigne()
{
   document.forms[0].typeAnulation.value = document.forms[0].listeTypeAnnulation.options[document.forms[0].listeTypeAnnulation.selectedIndex].value;  
   document.forms[0].newMotif.value="";   
}


function SupprimerTout(list) 
{
   list.options.length=0;
}

function chargerListMotifAnnulation()
{
    var url ="./gestionParametreAdministration?type=motifAnnulation";		
    if (window.ActiveXObject)
    {
        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequest = new XMLHttpRequest();
    }
    httpRequest.open("GET", url, true);        
    httpRequest.onreadystatechange = function() {processRequestMotifAnnulation(); } ;
    httpRequest.send(null); 
		
}


function ajouterMotifAnnulation()
{
    var newMotif = document.forms[0].newMotif.value;
    if(newMotif.length > 0)
	{
		var url ='./gestionParametreAdministration?type=motifAnnulation&action=ajouterMotifAnnulation&newValeur='+newMotif;		
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestMotifAnnulation(); } ;
        httpRequest.send(null); 		
	}
	else
	{
	   alert("Donnez un nouveau libellé pour le motif d'annulation");
	}
}

function modifierMotifAnnulation()
{ 

    var oldMotif= document.forms[0].listeTypeAnnulation.options[document.forms[0].listeTypeAnnulation.selectedIndex].value;
	var newMotif=document.forms[0].typeAnulation.value;
	
    if(newMotif.length > 0 )
	{
		var url ="./gestionParametreAdministration?type=motifAnnulation&action=modifierMotifAnnulation&oldValeur="+oldMotif+"&newValeur="+newMotif;		
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestMotifAnnulation(); } ;
        httpRequest.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libellé de la liste pour le modifier");
	}
}

function supprimerMotifAnnulation()
{
    typeAnulation =document.forms[0].typeAnulation.value;
    if(typeAnulation.length > 0)
	{
		var url ='./gestionParametreAdministration?type=motifAnnulation&action=supprimerMotifAnnulation&oldValeur='+typeAnulation;		
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestMotifAnnulation(); } ;
        httpRequest.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libellé de la liste pour le supprimer");
	}
}

function processRequestMotifAnnulation()
{       
    if (httpRequest.readyState == 4)
    {	   
        if(httpRequest.status == 200)
        {
            var xmldoc = httpRequest.responseXML.documentElement;
            var node = xmldoc.getElementsByTagName("motifAnnulation");   
			SupprimerTout(document.forms[0].listeTypeAnnulation);			
            for (i = 0; i < node.length; i++)
			{ 
			    var motifAnnulation= xmldoc.getElementsByTagName("motifAnnulation")[i].childNodes[0].nodeValue;            	 
				Ajouter(motifAnnulation); 
			}
		document.forms[0].newMotif.value="";
		document.forms[0].typeAnulation.value="";
        }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
        }
    }
}











function selectionnerLigneBanque()
{
   document.forms[0].Banque.value = document.forms[0].listeBanque.options[document.forms[0].listeBanque.selectedIndex].value;  
   document.forms[0].newBanque.value="";   
}

function AjouterBanque(libelle) 
{
    //alert("ajourt banque"+libelle);
    var o=new Option(libelle,libelle);
    document.forms[0].listeBanque.options[document.forms[0].listeBanque.options.length]=o;
}

function chargerListBanque()
{
    var url ="./gestionParametreAdministration?type=Banque";		
    if (window.ActiveXObject)
    {
        httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequest1 = new XMLHttpRequest();
    }
    httpRequest1.open("GET", url, true);         
    httpRequest1.onreadystatechange = function() {processRequestBanque(); } ;
    httpRequest1.send(null);     
}


function ajouterBanque()
{
    var newBanque = document.forms[0].newBanque.value;	
    if(newBanque.length > 0)
	{
		var url ='./gestionParametreAdministration?type=Banque&action=ajouterBanque&newValeur='+newBanque;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestBanque(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Donnez un nouveau libelle pour la banque ");
	}
}

function modifierBanque()
{ 

    var oldMotif= document.forms[0].listeBanque.options[document.forms[0].listeBanque.selectedIndex].value;
	var newBanque=document.forms[0].Banque.value;
	
    if(newBanque.length > 0 )
	{
		var url ="./gestionParametreAdministration?type=Banque&action=modifierBanque&oldValeur="+oldMotif+"&newValeur="+newBanque;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestBanque(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libellé de la liste pour le modifier");
	}
}

function supprimerBanque()
{
    Banque =document.forms[0].Banque.value;
    if(Banque.length > 0)
	{
		var url ='./gestionParametreAdministration?type=Banque&action=supprimerBanque&oldValeur='+Banque;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestBanque(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libelle de la liste pour le supprimer");
	}
}

function processRequestBanque()
{       
    if (httpRequest1.readyState == 4)
    {	   
        if(httpRequest1.status == 200)
        {
           var xmldoc = httpRequest1.responseXML.documentElement;
           var node = xmldoc.getElementsByTagName("banque");   
			//alert("taille="+node.length);
			SupprimerTout(document.forms[0].listeBanque);			    
			var toto ="";
            for (i = 0; i < node.length; i++)
			{ 
			   var Banque= xmldoc.getElementsByTagName("banque")[i].childNodes[0].nodeValue;
				AjouterBanque(Banque); 
				//toto = toto + Banque;
			}
			//alert(toto);
   		   document.forms[0].newBanque.value="";
		   document.forms[0].Banque.value="";
        }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest1.status +":"+ httpRequest1.statusText);
        }
    }


}















function selectionnerLigneProfesion()
{
   document.forms[0].Profesion.value = document.forms[0].listeProfesion.options[document.forms[0].listeProfesion.selectedIndex].value;  
   document.forms[0].newProfesion.value="";   
}

function AjouterProfesion(libelle) 
{
    //alert("ajourt Profesion"+libelle);
    var o=new Option(libelle,libelle);
    document.forms[0].listeProfesion.options[document.forms[0].listeProfesion.options.length]=o;
}

function chargerListProfesion()
{
    var url ="./gestionParametreAdministration?type=Profesion";		
    if (window.ActiveXObject)
    {
        httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequest1 = new XMLHttpRequest();
    }
    httpRequest1.open("GET", url, true);         
    httpRequest1.onreadystatechange = function() {processRequestProfesion(); } ;
    httpRequest1.send(null);     
}


function ajouterProfesion()
{
    var newProfesion = document.forms[0].newProfesion.value;	
    if(newProfesion.length > 0)
	{
		var url ='./gestionParametreAdministration?type=Profesion&action=ajouterProfesion&newValeur='+newProfesion;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestProfesion(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Donnez un nouveau libelle pour la Profesion ");
	}
}

function modifierProfesion()
{ 

    var oldMotif= document.forms[0].listeProfesion.options[document.forms[0].listeProfesion.selectedIndex].value;
	var newProfesion=document.forms[0].Profesion.value;
	
    if(newProfesion.length > 0 )
	{
		var url ="./gestionParametreAdministration?type=Profesion&action=modifierProfesion&oldValeur="+oldMotif+"&newValeur="+newProfesion;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestProfesion(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libellé de la liste pour le modifier");
	}
}

function supprimerProfesion()
{
    Profesion =document.forms[0].Profesion.value;
    if(Profesion.length > 0)
	{
		var url ='./gestionParametreAdministration?type=Profesion&action=supprimerProfesion&oldValeur='+Profesion;		
        if (window.ActiveXObject)
        {
            httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest1 = new XMLHttpRequest();
        }
        httpRequest1.open("GET", url, true);        
        httpRequest1.onreadystatechange = function() {processRequestProfesion(); } ;
        httpRequest1.send(null); 		
	}
	else
	{
	   alert("Selectionnez un libelle de la liste pour le supprimer");
	}
}

function processRequestProfesion()
{       
    if (httpRequest1.readyState == 4)
    {	   
        if(httpRequest1.status == 200)
        {
           var xmldoc = httpRequest1.responseXML.documentElement;
           var node = xmldoc.getElementsByTagName("profesion");   
			//alert("taille="+node.length);
			SupprimerTout(document.forms[0].listeProfesion);			    
			var toto ="";
            for (i = 0; i < node.length; i++)
			{ 
			   var Profesion= xmldoc.getElementsByTagName("profesion")[i].childNodes[0].nodeValue;
				AjouterProfesion(Profesion); 
				//toto = toto + Profesion;
			}
			//alert(toto);
   		   document.forms[0].newProfesion.value="";
		   document.forms[0].Profesion.value="";
        }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest1.status +":"+ httpRequest1.statusText);
        }
    }


}

































</script>

<%
String ville = (String)session.getAttribute("ville"); 
%>



<body background="./images/nuages.jpg" topmargin="0">
<form method="POST" action="--WEBBOT-SELF--">
<table border="1" width="100%" height="1">
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#AFF3BB"><A NAME="gestionEmplacement"><font color="#000000"><b><font size="3" face="Arial">MOTIF
      D'ANNULATION </font></b></font></a><b><font face="Arial" size="2">
      :</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Liste
      des valeurs :</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="1">
      <table border="0" width="100%" cellpadding="0" height="50">
        <tr>
          <td width="100%" height="94">
<select name="listeTypeAnnulation" onclick="selectionnerLigne()" size="11"  style="width: 401; height: 121" >
</select></td>
        </tr>
        <tr>
          <td width="100%" height="21"> <input type="text" name="typeAnulation" size="63">&nbsp;<b>&nbsp;
		    <a href="javascript:modifierMotifAnnulation( )">
			 Modifier</a>&nbsp;
            <a href="javascript:supprimerMotifAnnulation()">
			Supprimer
			</a></b>&nbsp;</td>
			
			
        </tr>
        <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Ajouter
      un motif d'annulation :</font></b></td>
        </tr>
        <tr>
    <td width="100%" height="14"><input type="text" onclick="document.forms[0].typeAnulation.value=''"
    name="newMotif" size="63">&nbsp;&nbsp;
    <a href="javascript:ajouterMotifAnnulation()" >
    <b>Ajouter</b>
    </a>&nbsp;&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<p> 






&nbsp;
<p> 






&nbsp; 






<table border="1" width="100%" height="1">
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#AFF3BB"><A NAME="gestionEmplacement"><font size="3" face="Arial" color="#000000"><b>RAISON
      SOCIALE</b></font><font color="#000000"><b><font size="3" face="Arial"> </font></b></font></a><b><font face="Arial" size="2">:</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Liste
      des valeurs :</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="1">
      <table border="0" width="100%" cellpadding="0" height="50">
        <tr>
          <td width="100%" height="94">
<select name="listeRaisonSocial" onclick="selectionnerLigneRaisonSocial()" size="11"  style="width: 401; height: 82" >
</select></td>
        </tr>
        <tr>
          <td width="100%" height="21"> <input type="text" name="raisonSocial" size="63">&nbsp;<b>&nbsp;
		    <a href="javascript:modifierRaisonSocial( )">
			 Modifier</a>&nbsp;
            <a href="javascript:supprimerRaisonSocial()">
			Supprimer
			</a></b>&nbsp;</td>
			
			
        </tr>
        <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Ajouter
      une raison sociale :</font></b></td>
        </tr>
        <tr>
    <td width="100%" height="14"><input type="text" onclick="document.forms[0].raisonSocial.value=''"
    name="newRaisonSocial" size="63">&nbsp;&nbsp;
    <a href="javascript:ajouterRaisonSocial()" >
    <b>Ajouter</b>
    </a>&nbsp;&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>





<BR>
<table border="1" width="100%" height="1">
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>

    <td width="100%" height="14" bgcolor="#AFF3BB"><A NAME="gestionEmplacement"><font size="3" face="Arial" color="#000000"><b>PROFESION</b></font><font color="#000000"><b><font size="3" face="Arial"> </font></b></font></a><b><font face="Arial" size="2">:</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Liste
      des valeurs :</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="1">
      <table border="0" width="100%" cellpadding="0" height="50">
        <tr>
          <td width="100%" height="94">
<select name="listeProfesion" onclick="selectionnerLigneProfesion()" size="11"  style="width: 401; height: 82" >
</select></td>
        </tr>
        <tr>
          <td width="100%" height="21"> <input type="text" name="Profesion" size="63">&nbsp;<b>&nbsp;
		    <a href="javascript:modifierProfesion( )">
			 Modifier</a>&nbsp;
            <a href="javascript:supprimerProfesion()">
			Supprimer
			</a></b>&nbsp;</td>
			
			
        </tr>
        <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Ajouter
      une profession :</font></b></td>
        </tr>
        <tr>
    <td width="100%" height="14"><input type="text" onclick="document.forms[0].Profesion.value=''"
    name="newProfesion" size="63">&nbsp;&nbsp;
    <a href="javascript:ajouterProfesion()" >
    <b>Ajouter</b>
    </a>&nbsp;&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>









































<% if (ville.equalsIgnoreCase("toulon")) 
{
%>


<BR>
<table border="1" width="100%" height="1">
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#AFF3BB"><font size="3" face="Arial" color="#000000"><b>BANQUE</b></font><font color="#000000"><b><font size="3" face="Arial"> </font></b></font><b><font face="Arial" size="2">:</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Liste
      des valeurs :</font></b></td>
  </tr>
  <tr>
    <td width="100%" height="1">
      <table border="0" width="100%" cellpadding="0" height="50">
        <tr>
          <td width="100%" height="94">
<select name="listeBanque" onclick="selectionnerLigneBanque()" size="11"  style="width: 401; height: 82" >
</select></td>
        </tr>
        <tr>
          <td width="100%" height="21"> <input type="text" name="Banque" size="63">&nbsp;<b>&nbsp;
		    <a href="javascript:modifierBanque()">
			 Modifier</a>&nbsp;
            <a href="javascript:supprimerBanque()">
			Supprimer
			</a></b>&nbsp;</td>
			
			
        </tr>
        <tr>
    <td width="100%" height="14" bgcolor="#FFFFE8"><b><font face="Arial" size="2">Ajouter
      une banque :</font></b></td>
        </tr>
        <tr>
    <td width="100%" height="14"><input type="text" onclick="document.forms[0].Banque.value=''"
    name="newBanque" size="63">&nbsp;&nbsp;
    <a href="javascript:ajouterBanque()" >
    <b>Ajouter</b>
    </a>&nbsp;&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<% } %>










</form>

<script  >
   chargerListMotifAnnulation();
  chargerListProfesion();
</script>


<% if (ville.equalsIgnoreCase("toulon"))  { %>
<script defer="defer" >
  setTimeout("chargerListBanque()",1000);
  setTimeout("chargerListRaisonSocial()",2000);  
  
</script>
<% } %>


