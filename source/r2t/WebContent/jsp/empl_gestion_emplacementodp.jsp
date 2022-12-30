<%@ page import="java.util.Vector"%>
<html >
<head>
<%--  <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCbwTSu-u5hn-MXS7cx4M3YU1vCOGIwgvI&sensor=false"> --%> 
<script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Expires" content="0">
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<script type="text/javascript" src="./diaporama/highslide/highslide-with-gallery.js"></script>
<link rel="stylesheet" type="text/css" href="./diaporama/highslide/highslide.css" />
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<%@ page language = "java" %>
<jsp:useBean id="beanEmplGeneral" scope="request" class="fr.analogon.r2t.view.role.BAffEmplacement"/>
<jsp:setProperty name="beanEmplGeneral" property="request" value="<%=request%>"/>
</head>
<body background="./images/nuages.jpg" topmargin="0" leftmargin="0" >
<?xml version="1.0" encoding="utf-8"?>

<script type="text/javascript">
diaporama1.graphicsDir = './diaporama/highslide/graphics/';
diaporama1.align = 'center';
diaporama1.transitions = ['expand', 'crossfade'];
diaporama1.fadeInOut = true;
diaporama1.outlineType = 'glossy-dark';
diaporama1.wrapperClassName = 'dark borderless floating-caption';
diaporama1.captionEval = 'this.a.title';
diaporama1.numberPosition = 'caption';
diaporama1.useBox = true;
diaporama1.width = 950;
diaporama1.height = 700;
diaporama1.dimmingOpacity = .75;
// Add the slideshow providing the controlbar and the thumbstrip
diaporama1.addSlideshow({
//slideshowGroup: 'group1',
interval: 5000,
repeat: false,
useControls: true,
fixedControls: 'fit',
overlayOptions: {
position: 'bottom center',
opacity: .75,
hideOnMouseOut: true
},
thumbstrip: {
position: 'above',
mode: 'horizontal',
relativeTo: 'expander'
}
});
// Make all images animate to the one visible thumbnail
var miniGalleryOptions1 = {
thumbnailId: 'thumb1'
}
</script>
<script language="JavaScript" type="text/JavaScript">
//Paul convertir les caractères entrés du clavier en majuscule
function caps(element){
	element.value = element.value.toUpperCase();
}
//paul pour la géolocalisation
function geolocaliser(){
	 //Définir les options du map
	  var mapProp= {
	     center:new google.maps.LatLng(46.227638, 2.213749),
	     zoom:14,
	     mapTypeId:google.maps.MapTypeId.ROADMAP
	   };
	 document.getElementById("googleMap").style.display="block";
	//Définir l'objet map /parametres= element contenant le map et les options
	 var map=new google.maps.Map(document.getElementById("googleMap")
	   ,mapProp);
	 // adresse à géolocaliser
	 var adr = document.forms[0].numRue.value + " "+
	 				document.forms[0].adresse1.value + " "+
	 				document.forms[0].codePostal.value+ " "+
	 				document.forms[0].ville.value; 
	 //Définir l'objet service de recherche d'une adresse
	 var geocoder=new google.maps.Geocoder();
	 geocoder.geocode( { 'address': adr}, function(results, status) {
			 if (status == google.maps.GeocoderStatus.OK) {
			 	map.setCenter(results[0].geometry.location);
			 	var marker = new google.maps.Marker({
				 										map: map,
				 										position: results[0].geometry.location
													});
			 }
			 else{
			 	alert(" Pb rencontré lors de la géolocalisation");
		 	}
	 });//geocoder

}//function

function fermer(){
	 document.getElementById("googleMap").style.display="none";
}

<!--Generation des images de l'emplcament-->
<%=beanEmplGeneral.getTableauImagesEmplacement()%>

function majChampCodeVille(codeVille)
{
	var reg=new RegExp("-----"); 
	var tableau=codeVille.split(reg); 
	res= codeVille.indexOf("PAS", 0);

	if(res < 0)
	{
	  document.forms[0].codePostal.value=tableau[0];
	  document.forms[0].ville.value=tableau[1];
	}
	else
	{
	  document.forms[0].codePostal.value="";
	  document.forms[0].ville.value="";
	}
  
}

function majChampCodeVilleProp(codeVille)
{
	var reg=new RegExp("-----"); 
	var tableau=codeVille.split(reg); 
	res= codeVille.indexOf("PAS", 0);

	if(res < 0)
	{
	  document.forms[0].codePostaleProprietaire.value=tableau[0];
	  document.forms[0].villeeProprietaire.value=tableau[1];
	}
	else
	{
	  document.forms[0].codePostaleProprietaire.value="";
	  document.forms[0].villeeProprietaire.value="";
	}
  
}


function rechercheCodeVille(codeRecherche)
{
		if(codeRecherche.length ==0) {
			alert("Entrez au moins 1 caractére pour le code");
			return
		}      
        var url = './rechercheCodeVille?code='+codeRecherche;
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestRechercheCodeVille(); } ;
        httpRequest.send(null); 
}


function processRequestRechercheCodeVille()
{       
	if (httpRequest.readyState == 4)
    {
        if(httpRequest.status == 200)
        {
          var xmldoc = httpRequest.responseXML.documentElement;			 			  
          var node = xmldoc.getElementsByTagName("ligne");			  
          var contenuListVille="<select name='listeCodeVille' onClick=majChampCodeVille(listeCodeVille.options[this.selectedIndex].text) onChange=majChampCodeVille(listeCodeVille.options[this.selectedIndex].text)>";
  
			  for (i = 0; i < node.length; i++)
			  {			  
	           var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;                  
	           var ville= xmldoc.getElementsByTagName("ville")[i].childNodes[0].nodeValue;                  
	       	   var element= code+"-----"+ville;
	              //alert(element);
	              contenuListVille+="<option value="+element+">"+element+"</option>";
           } 
        
           if (node.length==0)           
          {
        	 contenuListVille+="<option>    PAS DE RESULTATS    </option>";
             document.forms[0].codePostal.value="";
             document.forms[0].ville.value="";
          }                	 

           contenuListVille+="</select>"; 
           document.getElementById("listeCodeVille").innerHTML = contenuListVille;  
       }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
        }             
    }   
} 


function rechercheCodeVilleProp(codeRecherche)
{
		if(codeRecherche.length ==0) {
			alert("Entrez au moins 1 caractére pour le code");
			return
		}      
        var url = './rechercheCodeVille?code='+codeRecherche;
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestRechercheCodeVilleProp(); } ;
        httpRequest.send(null); 
}

function processRequestRechercheCodeVilleProp()
{       
	if (httpRequest.readyState == 4)
    {
        if(httpRequest.status == 200)
        {
          var xmldoc = httpRequest.responseXML.documentElement;			 			  
          var node = xmldoc.getElementsByTagName("ligne");			  
          var contenuListVille="<select name='listeCodeVilleProp' onClick=majChampCodeVilleProp(listeCodeVilleProp.options[this.selectedIndex].text) onChange=majChampCodeVilleProp(listeCodeVilleLiq.options[this.selectedIndex].text)>";
  
			  for (i = 0; i < node.length; i++)
			  {			  
	           var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;                  
	           var ville= xmldoc.getElementsByTagName("ville")[i].childNodes[0].nodeValue;                  
	       	   var element= code+"-----"+ville;
	              //alert(element);
	              contenuListVille+="<option value="+element+">"+element+"</option>";
           } 
        
           if (node.length==0)           
          {
        	 contenuListVille+="<option>    PAS DE RESULTATS    </option>";
             document.forms[0].codePostaleProprietaire.value="";
             document.forms[0].villeeProprietaire.value="";
          }                	 

           contenuListVille+="</select>"; 
           document.getElementById("listeCodeVilleProp").innerHTML = contenuListVille;  
       }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
        }             
    }   
} 


function verfierUnicite()
{
//alert("verfierUnicite");
numeroEmplacementPersonalise=document.forms[0].numeroEmplacementPersonalise.value;
codeSecteur=document.forms[0].leCodeSecteur.value;
numEmplacementR2T= document.forms[0].numEmplacment.value;
//alert("numeroEmplacementPersonalise="+numeroEmplacementPersonalise);
//alert("codeSecteur="+codeSecteur);
//alert("numEmplacementR2T="+numEmplacementR2T);

if ( numeroEmplacementPersonalise.length == 0)
alert("le numero de l'emplacement est obligatoire");
else
{

var url = "./verifierUnciteCodeNumEmplacement?codeSecteur="+codeSecteur+"&numeroEmplacementPersonalise="+numeroEmplacementPersonalise+"&numEmplacementR2T="+numEmplacementR2T;
if (window.ActiveXObject)
{
httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest)
{
httpRequest = new XMLHttpRequest();
}
httpRequest.open("GET", url, true);
httpRequest.onreadystatechange = function() {processRequestUnicite(); } ;
httpRequest.send(null);
}

}

function processRequestUnicite()
{
if (httpRequest.readyState == 4)
{
//alert("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
if(httpRequest.status == 200)
{
var xmldoc = httpRequest.responseXML.documentElement;
var node = xmldoc.getElementsByTagName("disponible");
var disponible ;

for (i = 0; i < node.length; i++)
{
disponible= xmldoc.getElementsByTagName("disponible")[i].childNodes[0].nodeValue;
}


if ( disponible == 'ko')
{
//alert("ko");
document.forms[0].verifNumeroPersonalise.value = -1 ;
}
else if ( disponible == 'ok')
{
//alert("ok");
document.forms[0].verifNumeroPersonalise.value = 0 ;
}
else
{
alert("erreur");
document.forms[0].verifNumeroPersonalise.value = -1 ;
}
}
else
{
alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
}
}
}

function reglerdimention()
{
//alert (document.getElementById("pic").width);
//if (document.getElementById("pic").height > 488) document.getElementById("pic").height =488;
//if (document.getElementById("pic").width > 722) document.getElementById("pic").width =722;
document.getElementById("pic").height =488;
document.getElementById("pic").width =822;


}
function actualiser()
{
//alert("test refresh");
document.forms[0].choix.value="";
document.forms[0].choix.value="";
window.location.reload( false );
}





function supprimerImageEmplacement()
{
ret=confirm("Etes-vous sûr de supprimer?");
if (ret == true)
{
   document.forms[0].nomImageASupprimer.value= document.forms[0].nomImageEmplacement.value;
   modifier()
}
  //window.location.reload( false );

}

function majLienRedevable(adresse)
{
//alert(adresse);
var reg=new RegExp("---");
var tableau=adresse.split(reg);
var numRedevable= tableau[0];
var link ="./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role";
link=link +"&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable="+numRedevable;
//alert(link);
contenuLienRedevable="<font face='Arial' color='#0000FF' size='2' >";
contenuLienRedevable = contenuLienRedevable +"<a href="+ link+" > Lien vers le redevable numero : "+ numRedevable +" </a></font></b>";
res= adresse.indexOf("PAS", 0);
if(res !=0)
{
document.getElementById("lienRedevable").innerHTML = contenuLienRedevable;
document.forms[0].idRedevableAutorise.value=numRedevable;
}
else
{
document.getElementById("lienRedevable").innerHTML = "";
document.forms[0].idRedevableAutorise.value="";
}

}
function rechercheRedevable(nomRedevable)
{
//alert(numRedevable);
var url = './rechercheRedevable?nomRedevable='+nomRedevable;
if (window.ActiveXObject)
{
httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest)
{
httpRequest = new XMLHttpRequest();
}
httpRequest.open("GET", url, true);
httpRequest.onreadystatechange = function() {processRequestRedevable(); } ;
httpRequest.send(null);
}


function processRequestRedevable()
{
if (httpRequest.readyState == 4)
{
if(httpRequest.status == 200)
{
var xmldoc = httpRequest.responseXML.documentElement;
var node = xmldoc.getElementsByTagName("redevable");
var contenuListRedevable="<b><font face='Arial' size='2'>Choix du Redevable : </font></b>";
contenuListRedevable=" "+contenuListRedevable+"<select name='listDesEmplacment' onclick='majLienRedevable(this.value)' >";
//alert(node.length);
for (i = 0; i < node.length; i++)
{
var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue;
var numRedevable= xmldoc.getElementsByTagName("numRedevable")[i].childNodes[0].nodeValue;
element= numRedevable+"---"+adresse;
contenuListRedevable=contenuListRedevable+"<option value="+element+">"+element+"</option>";
}
if (node.length==0)
{
contenuListRedevable=contenuListRedevable+"<option> PAS DE RESULTATS </option>";
document.getElementById("lienRedevable").innerHTML = "";

}
contenuListRedevable=contenuListRedevable+"</select>";
document.getElementById("listeRedevable").innerHTML = contenuListRedevable;
}
else
{
alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
}
}
}






function majChampAdresse(adresse)
{
//alert(adresse);
var reg=new RegExp("-----");
var tableau=adresse.split(reg);
res= adresse.indexOf("PAS", 0);
if(res !=0)
{
document.forms[0].codeVoie.value=tableau[0];
document.forms[0].adresse1.value=tableau[1];
document.forms[0].NomQuartier.value=tableau[2];
document.forms[0].codeRivolie.value=tableau[3];
document.forms[0].codePostal.value=tableau[4];
}
else
{
document.forms[0].codeVoie.value="";
document.forms[0].adresse1.value="";
}




}

function rechercheVoie(nomRueRecherche)
{
//alert(nomRueRecherche);
if(nomRueRecherche.length > 3)
{
var url = './rechercheRue?nomRueRecherche=' + nomRueRecherche;
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
else
{
alert("Entrez au moins 4 caractères pour rechercher des rues")
}

}
function rechercheVoieParCodeVoie(codeVoixRecherche, numeroRue)
{
//alert(numeroRue);
var url = './rechercheRue?codeVoixRecherche='+codeVoixRecherche+"&numeroRue="+numeroRue;
if (window.ActiveXObject)
{
httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest)
{
httpRequest = new XMLHttpRequest();
}
httpRequest.open("GET", url, true);
httpRequest.onreadystatechange = function() {processRequestRechercheVoieParCodeVoie(); } ;
httpRequest.send(null);
}

function processRequestRechercheVoieParCodeVoie()
{
if (httpRequest.readyState == 4)
{
if(httpRequest.status == 200)
{
var xmldoc = httpRequest.responseXML.documentElement;
var node = xmldoc.getElementsByTagName("rue");

if (node.length==0)
{
alert ("Aucune rue disponible pour ce code rivoli !!");
document.getElementById("listeRue").innerHTML = "";
document.forms[0].adresse1.value="";
document.forms[0].NomQuartier.value="";
}
else if (node.length==1)
{

var code= xmldoc.getElementsByTagName("code")[0].childNodes[0].nodeValue;
var adresse= xmldoc.getElementsByTagName("adresse")[0].childNodes[0].nodeValue;
var quartier= xmldoc.getElementsByTagName("quartier")[0].childNodes[0].nodeValue;
var codeRivolie= xmldoc.getElementsByTagName("codeRivolie")[0].childNodes[0].nodeValue;
var codePostal= xmldoc.getElementsByTagName("codePostal")[0].childNodes[0].nodeValue;
//alert("test");
document.forms[0].codeVoie.value=code;
document.forms[0].adresse1.value=adresse;
document.forms[0].NomQuartier.value=quartier;
document.forms[0].codeRivolie.value=codeRivolie;
document.forms[0].codePostal.value=codePostal;
document.getElementById("listeRue").innerHTML = "";

}
else
{
//alert ("2 res");
var contenuListRue="<select name='listDesRues' onclick=majChampAdresse(listDesRues.options[this.selectedIndex].text)  onChange=majChampAdresse(listDesRues.options[this.selectedIndex].text)>";
for (i = 0; i < node.length; i++)
{
var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;
var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue;
var quartier= xmldoc.getElementsByTagName("quartier")[i].childNodes[0].nodeValue;
var codeRivolie= xmldoc.getElementsByTagName("codeRivoli")[i].childNodes[0].nodeValue;
var codePostal= xmldoc.getElementsByTagName("codePostal")[0].childNodes[0].nodeValue;
var element= code+"-----"+adresse+"-----"+quartier+"-----"+codeRivolie+"-----"+codePostal;
contenuListRue=contenuListRue+"<option>"+element+"</option>";

}
contenuListRue=contenuListRue+"</select>";
document.getElementById("listeRue").innerHTML = contenuListRue;

}
}
else
{
alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
}
}
else
{
//alert("Code de voix non valide ");
}
}


function processRequest()
{
if (httpRequest.readyState == 4)
{
if(httpRequest.status == 200)
{
var xmldoc = httpRequest.responseXML.documentElement;
var node = xmldoc.getElementsByTagName("rue");
//alert(node.length) ;
var contenuListRue="<select name='listDesRues'  onclick=majChampAdresse(listDesRues.options[this.selectedIndex].text) onChange=majChampAdresse(listDesRues.options[this.selectedIndex].text)>";

for (i = 0; i < node.length; i++)
{
var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;

var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue;

var quartier= xmldoc.getElementsByTagName("quartier")[i].childNodes[0].nodeValue;

var codeRivolie= xmldoc.getElementsByTagName("codeRivolie")[i].childNodes[0].nodeValue;
//alert(codeRivoli);
var codePostal= xmldoc.getElementsByTagName("codePostal")[0].childNodes[0].nodeValue;

var element= code+"-----"+adresse+"-----"+quartier+"-----"+codeRivolie+"-----"+codePostal;
contenuListRue=contenuListRue+"<option>"+element+"</option>";

}

if (node.length==0)
{
contenuListRue=contenuListRue+"<option> PAS DE RESULTATS </option>";
document.forms[0].adresse1.value="";
document.forms[0].codeRivolie.value="";
}

contenuListRue=contenuListRue+"</select>";
document.getElementById("listeRue").innerHTML = contenuListRue;
}
else
{
alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
}
}
}


function uploadFichierDocument()
{

if (document.forms[2].mydocument.value.length !=0)
{
//window.location.reload( false );
//alert("Ajout de dcouement"+ document.getElementById("mydocument").value);
var fileName = document.getElementById("mydocument").value;
document.forms[2].action="importDocumentEmplacment?action=addDocumentEmplacement&source=emplacement&numEmplacment="+document.forms[0].numEmplacment.value+"&fileName="+fileName ;
document.forms[2].submit();
}
else
{
alert("Veuillez indiquer le chemin du document ! ");
}

}


function SupprimerFichierDocument(fileName)
{
    if (confirm("Voulez-vous supprimer le document "+fileName+" ?")) 
    { // Clic sur OK
       document.forms[2].action="importDocumentEmplacment?action=deleteDocumentEmplacement&source=emplacement&numEmplacment="+document.forms[0].numEmplacment.value+"&fileName="+fileName ;
       document.forms[2].submit();
	}
}

function uploadFichier()
{

if (document.forms[1].myimage.value.length !=0)
{
//window.location.reload( false );
document.forms[1].action="importImageEmplacment?source=emplacement&numEmplacment="+document.forms[0].numEmplacment.value ;
document.forms[1].submit();
}
else
{
alert("Veuillez indiquer le chemin vers la photo ! ");
}

}

function modifierCedexProprietaire()
{

if(document.forms[0].cedexProprietaireCk.checked==true )
{
document.forms[0].cedexProprietaire.value="true";
}
else
{
document.forms[0].cedexProprietaire.value="false";
}
}

function modifierCedex()
{

if(document.forms[0].cedexCk.checked==true )
{
document.forms[0].cedex.value="true";
}
else
{
document.forms[0].cedex.value="false";
}
}

function afficherNumeroImageEmplacement()
{
var a =document.forms[0].numeroImageEmplacement.value ;
var b =document.forms[0].tailleTableauEmplacement.value;
var ai = parseInt(a)+1;
document.forms[0].numeroImageEmplacementCourante.value=ai+"/"+ b;
}

function initImagesEmplacement()
{
tableauImageEmplacement= getTableauImagesEmplacement();
document.forms[0].numeroImageEmplacement.value=0;
document.forms[0].tailleTableauEmplacement.value=tableauImageEmplacement.length;
//alert(tableauImageEmplacement.length);
if(tableauImageEmplacement.length != 0)
{
var nomFichier= tableauImageEmplacement[0][0].substr(17,tableauImageEmplacement[0][0].length);
document.forms[0].nomImageEmplacement.value=nomFichier;
document.forms[0].dateCreationImageEmplacement.value=tableauImageEmplacement[0][1];
document.getElementById('pic').src=tableauImageEmplacement[0][0];
//document.getElementById("diapo").href ="http://yahoo.fr";
//<a href="../MultiBox/files/IMAGE1.jpg" id="image1" class="diaporama"title="FLV" >image1</a> <div class="multiBoxDesc image1"></div>
afficherNumeroImageEmplacement();
}
}


function suivantImageEmplacement()
{
tableauImageEmplacement= getTableauImagesEmplacement();
document.forms[0].numeroImageEmplacement.value++;
document.forms[0].tailleTableauEmplacement.value=tableauImageEmplacement.length;
if (document.forms[0].numeroImageEmplacement.value == tableauImageEmplacement.length)
document.forms[0].numeroImageEmplacement.value=0;
var pathFichier=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][0];
var nomFichier= pathFichier.substr(17,pathFichier.length);
document.forms[0].nomImageEmplacement.value=nomFichier;
document.forms[0].dateCreationImageEmplacement.value=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][1];
document.getElementById('pic').src=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][0];
afficherNumeroImageEmplacement();
reglerdimention();
}


function precedantImageEmplacement()
{
tableauImageEmplacement= getTableauImagesEmplacement();
document.forms[0].numeroImageEmplacement.value--;
document.forms[0].tailleTableauEmplacement.value=tableauImageEmplacement.length;
if (document.forms[0].numeroImageEmplacement.value < 0 )
document.forms[0].numeroImageEmplacement.value=tableauImageEmplacement.length-1;
var pathFichier=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][0];
var nomFichier= pathFichier.substr(17,pathFichier.length);
document.forms[0].nomImageEmplacement.value=nomFichier;
document.forms[0].dateCreationImageEmplacement.value=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][1];
document.getElementById('pic').src=tableauImageEmplacement[document.forms[0].numeroImageEmplacement.value][0];
afficherNumeroImageEmplacement();
reglerdimention();

}
function supprimerImageAlerte()
{
ret=confirm("Etes-vous sûr de supprimer?");
if (ret == true)
{

}
}



function utiliseAddRedevable()
{

var redevableAdd1="<%=beanEmplGeneral.getRedevableAdd1()%>";
var redevableAdd2="<%=beanEmplGeneral.getRedevableAdd2()%>";
var redevableAdd3="<%=beanEmplGeneral.getRedevableAdd3()%>";
var redevableCedex="<%=beanEmplGeneral.getRedevableCedex()%>";
var redevableCodePostal="<%=beanEmplGeneral.getRedevableCodePostal()%>";
var redevableCodeVoie="<%=beanEmplGeneral.getRedevableCodeVoie()%>";
var redevableNumRue="<%=beanEmplGeneral.getRedevableNumRue()%>";
var redevableVille="<%=beanEmplGeneral.getRedevableVille()%>";
var codeRivolieRedevable="<%=beanEmplGeneral.getRedevableCodeRivolie()%>";
document.forms[0].adresse1.value=redevableAdd1;
document.forms[0].adresse2.value=redevableAdd2;
document.forms[0].adresse3.value=redevableAdd3;
document.forms[0].codePostal.value=redevableCodePostal;
document.forms[0].codeVoie.value=redevableCodeVoie;
document.forms[0].numRue.value=redevableNumRue;
document.forms[0].ville.value=redevableVille;
document.forms[0].codeRivolie.value=codeRivolieRedevable;
}


function creer()
{
if (verifFormulaire()=="true")
{
//alert(document.forms[0].action);
document.forms[0].choix.value="creer";
document.forms[0].submit();
//alert("test1");
}
}



//verifie les champs du formulaire et affiche les boites d'alertes
function verifFormulaire()
{


var res="false";
//verifNomCommercialEmplacment= verifier("Nom commercial de l'empalcement ",document.forms[0].nomCommercialEmplacment.value);
verifAdresse1= verifier("Adresse de l'emplacement ",document.forms[0].adresse1.value);
verifNumRue= verifier("Numéro de rue de l'emplacement ",document.forms[0].numRue.value);
verifCodeVoie= verifier("Code voie ",document.forms[0].codeVoie.value);
verifDateDebutActivite= verifier("Date Debut d'activité ",document.forms[0].dateDebutActivite.value);


verifIdRedevableAutorise= verifier("Identifiant du redevable autorisé ",document.forms[0].idRedevableAutorise.value);
verifCodeRivolie= verifier("Code Rivoli ",document.forms[0].codeRivolie.value);
ville = document.forms[0].nomDeLaVille.value ;
verifNumeroPersonalise = 0 ;
if (ville == 'Toulon')
{
//alert(ville);
//verfierUnicite();
//verfier que c pas vide
verifNumeroPersonlise1= verifier("Numero de l'emplacement ",document.forms[0].numeroEmplacementPersonalise.value);
//alert (verifNumeroPersonlise1);

//chanmp modifie par appel ajax
verifNumeroPersonlise2 = document.forms[0].verifNumeroPersonalise.value;

etatEmplacement = document.forms[0].etatEmplacement.value;
//alert("etatEmplacement"+etatEmplacement);

//alert (etatEmplacement.indexOf("termine"));
//si etatEmplacement = termine on ne controle pas l'uncite NumerpEmpalcmentPersonlaise + codeSecteur +etat ena ctivite 
if(etatEmplacement.indexOf("termine") >= 0)
{
	verifNumeroPersonalise = 0;
}
else if (verifNumeroPersonlise1 == 0 && verifNumeroPersonlise2 == 0)
{
	verifNumeroPersonalise = 0;
}
else
{
	verifNumeroPersonalise = -1;
}

if (verifNumeroPersonalise == -1)
{
  alert("Le numero "+document.forms[0].numeroEmplacementPersonalise.value+ " avec le code secteur "+document.forms[0].leCodeSecteur.value + " existe deja pour un autre emplacement actif ");
}

}
if(verifDateDebutActivite== 0)
{
//alert("date existe");
verifDateDebutActivite= verif_date(document.forms[0].dateDebutActivite.value);
if (!verifDateDebutActivite)
{
alert("Date de début de période doit être de la forme JJ/MM/AAAA");
verifDateDebutActivite=1;
}
else
verifDateDebutActivite=0;
}


if(document.forms[0].dateFinActivite.value.length != 0 )
{
//alert("date existe");
verfiDateFinActivite= verif_date(document.forms[0].dateFinActivite.value);
if (!verfiDateFinActivite)
{
alert("Date de fin d'activité doit être de la forme JJ/MM/AAAA");
verfiDateFinActivite=1;
}
else
verfiDateFinActivite=0;
}
else
{
//alert("date n existe pas");
verfiDateFinActivite=0;
}

if(document.forms[0].dateInscription.value.length != 0 )
{
//alert("date existe");
verfifDateInscription= verif_date(document.forms[0].dateInscription.value);
if (!verfifDateInscription)
{
alert("Date d'inscription doit être de la forme JJ/MM/AAAA");
verfifDateInscription=1;
}
else
verfifDateInscription=0;
}
else
{
//alert("date n existe pas");
verfifDateInscription=0;
}




verifDateReceptionDeclaration = 0 ;
if (document.forms[0].dateReceptionDeclaration != null)
{

	if(document.forms[0].dateReceptionDeclaration.value.length != 0 )
	{
	//alert("date existe");	
	verifDateReceptionDeclaration= verif_date(document.forms[0].dateReceptionDeclaration.value);	
	if (!verifDateReceptionDeclaration)
	{
	alert("Date reception declaration doit être de la forme JJ/MM/AAAA");
	verifDateReceptionDeclaration=1;
	}
	else
	verifDateReceptionDeclaration=0;
	}
	else
	{
	//alert("date n existe pas");
	verifDateReceptionDeclaration=0;
	}
}

var verfiemail=0;
if(document.forms[0].email.value.length != 0 )
{
verfiemail= VerifMail(document.forms[0].email.value);
if (verfiemail=="false")
{
alert("Adresse e-mail de l'emplacement invalide ");
verfiemail=1;
}
else
verfiemail=0;
}
else
{
verfiemail=0;
}
var verfemailProprietaire=0;
if(document.forms[0].emailProprietaire.value.length != 0 )
{
verfemailProprietaire= VerifMail(document.forms[0].emailProprietaire.value);
if (verfemailProprietaire=="false")
{
alert("Adresse e-mail du propriétaire ");
verfemailProprietaire=1;
}
else
verfemailProprietaire=0;
}
else
{
verfemailProprietaire=0;
}

verifEnactivite= 0;
if (document.forms[0].nombreOuvrageActif.value !='0'
&& document.forms[0].etatEmplacement.value =='termine' )
{
alert("Impossible de terminer cet emplacement : il y a des ouvrages actifs à cet emplacement !! ")
verifEnactivite=-1;
}

if( verifCodeVoie==0
&& verifDateDebutActivite==0 && verifIdRedevableAutorise==0
&& verfiDateFinActivite==0 && verfifDateInscription ==0
&& verfemailProprietaire== 0 && verfiemail==0 && verifAdresse1==0
&& verifEnactivite==0 && verifCodeRivolie== 0 && verifNumRue==0
&& verifNumeroPersonalise== 0 && verifDateReceptionDeclaration==0)
{
res="true";
}
else
{
//alert("Champs manquant !!")
}

return res;
}


function supprimer()
{
	
	if(document.forms[0].peutEtreSupprimer.value =='ok')
	{	
		if (document.forms[0].nombreOuvrageActif.value =='0' )
		{
			ret=confirm("Etes-vous sûr de supprimer?");
			if (ret == true)
			{
				document.forms[0].choix.value="supprimer";
				document.forms[0].submit();
			}
			else
			{
				alert("Impossible de terminer cet emplacment , il y a des ouvrages actifs à cet emplacement !! !! ")
			}
		}
	}
	else
	{
		alert("Impossible de supprimer cet emplacment , il y une facture valide relatif à cet emplacement !!  ")
	}
}


function modifier()
{
if (verifFormulaire()=="true")
{
document.forms[0].choix.value="modifier";
document.forms[0].submit();
}
}


</script>
<div align="center">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td background="./images/fond_trait.gif" width="15%">
<p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
</td>
<td background="./images/fond_trait.gif" valign="top">
<p align="right"><font face="Arial" size="5">Gestion de l'emplacement
</font></td>
</tr>
</table>
</div>










<form method="POST" action="gestionemplodp" >
<%
String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");
Boolean peutAcceder = Boolean.valueOf(beanEmplGeneral.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));
String ville = (String)session.getAttribute("ville");
%>

<input type="hidden" name="nomDeLaVille" size="20" value="<% out.print (ville); %>" >
<input type="hidden" name="verifNumeroPersonalise" size="20" value="0" >

<input type="hidden" name="nomImageASupprimer" >
<input type="hidden" name="codeVoie" size="20" value="<%=beanEmplGeneral.getCodeVoie()%>" >
<input type="hidden" name="peutEtreSupprimer" size="20" value="<%=beanEmplGeneral.getPeutEtreSupprimer()%>" >

<input type="hidden" name="cedex" value="<jsp:getProperty name="beanEmplGeneral" property="cedex"/>">
<input type="hidden" name="cedexProprietaire" value="<jsp:getProperty name="beanEmplGeneral" property="cedexProprietaire"/>">
<input type="hidden" name="numeroImageEmplacement" >
<input type="hidden" name="tailleTableauEmplacement" >
<input type="hidden" name="imageASupprimer" >

<input type="hidden" name="choix" >
<input type="hidden" name="numcompte" value="">
<input type="hidden" name="Redevable" value="<jsp:getProperty name="beanEmplGeneral" property="nomRedevable"/>">
<input type="hidden" name="origine" value="empl_gestion_emplacementodp.jsp">
<input type="hidden" name="numEmplacment" value="<jsp:getProperty name="beanEmplGeneral" property="numEmplacement"/>">
<input type="hidden" name="numRedevable" value="<%=beanEmplGeneral.getNumRedevable()%>">
<input type="hidden" name="idImputation" value="<%=beanEmplGeneral.getIdImputation()%>">
<input type="hidden" name="libelleImputation" value="<%=beanEmplGeneral.getLibelleImputation()%>">
<input type="hidden" name="anneeExerciceImputation" value="<%=beanEmplGeneral.getAnneeExerciceImputation()%>">
<input type="hidden" name="nombreOuvrageActif" value="<%=beanEmplGeneral.getNombreOuvrageActif()%>">




<%
    if (request.getParameter("errortailleimage") != null && ! request.getParameter("errortailleimage").contains("ok") ) 
    {
%>
   <input type="hidden" id="errortailleimage" value="<%= request.getParameter("errortailleimage") %>">
   <script type="text/javascript">alert("La taille de l'image doit etre inferieur a "+document.getElementById("errortailleimage").value  +" koctet")</script>
<% 
    }
%>



<!--1-->

<CENTER>
<TABLE cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="100%" borderColorLight=#c0c0c0 border=1 height="133">
<tr>
<td bgcolor="#AFF3BB" width="1129" height="42">
<p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/listeouvrage.jpg" width="40" height="40"></b></font>
</td>
</tr>
<tr>
<td align="center" width="868" height="22" bgcolor="#AFF3BB">
<p align="left"><font size="4">
<b>
<%if( beanEmplGeneral.getNumEmplacement().length() != 0 ) {%>
&nbsp;</b></font><b><font size="4">MODIFICATION DE L'EMPLACEMENT :

<% if ( ville.equalsIgnoreCase("toulon")) { %>
<jsp:getProperty name="beanEmplGeneral" property="numeroEmplacementPersonalise"/>
<%}else{%>
<jsp:getProperty name="beanEmplGeneral" property="numEmplacement"/>
<%}%>


<% } else{ %>
&nbsp;CREATION D'UN EMPLACEMENT : </font></b>
<% } %>
</td>
</tr>
<tr>
<TD bgColor=#ffffff width="1120" height="20">
<P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>
Redevable : </FONT>
<a href='./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=modifier&typeRecherche=role&numRedevable=<%=beanEmplGeneral.getNumRedevable()%>' >
<font face="Arial" size="2" color="#0000FF">
<%=beanEmplGeneral.getRaisonSocialeRedevable()%> <%=beanEmplGeneral.getNomRedevable()%> <%=beanEmplGeneral.getPrenomRedevable()%></font></a></B></P>
</TD>
</tr>

<tr>
<TD bgColor=#ffffff width="1120" height="20">
<P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>
Type d'emplacement : </FONT>
<FONT face=Arial color=#cc3300 size=2>
<%=beanEmplGeneral.getLibelleImputation()%> </FONT></B></P>
</TD>
</tr>

<tr>
<TD bgColor=#ffffff width="1120" height="20">
<P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>
Année d'exercice du type de taxe : </FONT>
<FONT face=Arial color=#cc3300 size=2>
<%=beanEmplGeneral.getAnneeExerciceImputation()%> </FONT></B></P>
</TD>
</tr>
<%if( beanEmplGeneral.getNumEmplacement().length() != 0 ) {%>

<tr>
<TD bgColor=#ffffff width="1120" height="20">
<P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>
Liste des ouvrages : </FONT>
<a href='<%=beanEmplGeneral.getLienListeOuvrage()%>'>
<FONT face=Arial color=#0000FF size=2>
Liste des ouvrages actifs (<%=beanEmplGeneral.getNombreOuvrageActif()%>)
<%

if ( ! beanEmplGeneral.getNombreOuvrage().equals(beanEmplGeneral.getNombreOuvrageActif()) )
out.println(" !! ");
if ( ! beanEmplGeneral.getNbreAlerteActif().equals("0") )
out.println(" <img title='Il y a des alertes en cours sur des ouvrages dans cet emplacement !' width='15' length='13' src ='../images/alerteb.PNG'>");

%>
</FONT>
</a>
</B></P>
</TD>
</tr>
<%}%>


</TABLE>
</CENTER>
<BR>

<!--1-->
<div align="center" style="width: 1158; height: 1764">
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
<tr>
<td width="100%" bgcolor="#FFFFE8">
<b><font size="3" face="Arial" color="#003399">Emplacement :&nbsp;&nbsp;<b>
</b></font></b>
</td>
</tr>
</table>



<!--2-->
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="494">

<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">

<font size="2" face="Arial"><b>&nbsp;N°emplacement personalisé&nbsp;</b><font color="#FF0000">*</font><b>:</b></font></td>
</td>
<td width="906" colspan="2" height="23">

<input onChange="javascript:verfierUnicite();" onClick="javascript:verfierUnicite();" type="text" size="51" maxlength="40" name="numeroEmplacementPersonalise"
value="<jsp:getProperty name="beanEmplGeneral" property="numeroEmplacementPersonalise"/>" >
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Observation&nbsp;</b><font color="#FF0000"></font><b>:</b></font></td>
</td>
<td width="906" colspan="2" height="23">
<textarea cols="110" rows="5" name="observation" ><jsp:getProperty name="beanEmplGeneral" property="observation" /></textarea>
</td>
</tr>



<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<p style="margin-left: 5"><font face="Arial" size="2"><b>Nom commercial&nbsp; :</b></font></p>
</td>
<td width="906" colspan="2" height="23">

<input type="text" size="51" maxlength="40" name="nomCommercialEmplacment" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getNomCommercialEmplacment()%>" >
</td>
</tr>


<tr>
<td width="269" background="./images/fond_bleu.gif" height="22">

<font size="2" face="Arial"><b>&nbsp;Quartier&nbsp;</b><font color="#FF0000">*</font><b>:</b></font></td>
<td width="906" colspan="2" height="22">
<input type="text" onkeyup="caps(this)" size="45" readonly name="NomQuartier" value="<jsp:getProperty name="beanEmplGeneral" property="nomQuartier"/>" >
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="22">
<font size="2" face="Arial"><b>&nbsp;Secteur </b><font color="#FF0000">*</font><b>:</b></font></td>
<td width="906" colspan="2" height="22">
<jsp:getProperty name="beanEmplGeneral" property="codeSecteur" />
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="1">
<font face="Arial" size="2"><b>&nbsp;Numéro de voie</b><font color="#FF0000">*</font></font>
<font face="Arial" size="2"><b> :</b></font></td>
<td width="906" colspan="2" height="1">
<table border="1" width="100%" height="1">
<tr>
<td width="894" height="15">

<b><font size="2" face="Arial">N°</font></b><font color="#FF0000">*</font>
<input type="text" name="numRue" size="5" value=<jsp:getProperty name="beanEmplGeneral" property="numRue"/> >


<b><font size="2" face="Arial">Compl</font></b>
<jsp:getProperty name="beanEmplGeneral" property="complementNumeroRueEmpl"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><font size="2" face="Arial">Code Rivoli</font></b><font color="#FF0000">*</font>
<input type="text" size="20" maxlength="6" name="codeRivolie" value=<jsp:getProperty name="beanEmplGeneral" property="codeRivolie" />
>
<b>&nbsp;</b>
<a href="javascript:rechercheVoieParCodeVoie(document.forms[0].codeRivolie.value,document.forms[0].numRue.value);" >
<img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a><b>&nbsp;&nbsp;&nbsp;&nbsp; <font size="2" face="Arial">Recherche par
voie : </font> </b>
<input type="text" name="nomRueRecherche" size="26">
<a href="javascript:rechercheVoie(document.forms[0].nomRueRecherche.value);" >
<img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>&nbsp;
</td>
</tr>
<tr>
<td width="876" height="21">
<div id="listeRue" style="width: 550; height: 20"> </div>
</td>
</tr>
</table>
</td>
</tr>


<tr>
<td width="269" background="./images/fond_bleu.gif" height="72" rowspan="3">
<p style="margin-left: 5"><font size="2" face="Arial"><b>Adresse </b><font color="#FF0000">*</font><b>:</b></font></td>
<td width="906" colspan="2" height="25">
<p style="margin-left: 2">
<font size="2" face="Arial"><b>
<input readonly type="text" size="47" maxlength="60" name="adresse1"
value="<%=beanEmplGeneral.getAdresse1()%>" >
</b></font><b><font size="2" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Utiliser l'adresse du redevable : <a href="javascript:utiliseAddRedevable();" >
<img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
</font></b>
</td>
</tr>

<tr>
<td height="25" width="440">
<p style="margin-left: 2">
<font size="2" face="Arial">compl. Adresse :&nbsp;<b>
<input type="text" size="29" maxlength="40" name="adresse2" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAdresse2()%>" >
</b>&nbsp;</font></p>
</td>
<td width="464" height="25">
	<p style="margin-left: 2;margin-top:2">
    	<b>&nbsp;Recherche ville par code : </b>
     	<input type="text" name="codeRecherche" size="10">
     	<a href="javascript:rechercheCodeVille(document.forms[0].codeRecherche.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
     	&nbsp;&nbsp;<span id="listeCodeVille"></span>
	</p>	
</td>
</tr>

<tr>
<td height="27" width="440">
<p style="margin-left: 2">
<font size="2" face="Arial">compl. Adresse :<b>&nbsp; <input type="text" size="29" maxlength="40" name="adresse3" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAdresse3()%>" >
</b></font></p>
</td>
<td width="464" height="27" >
	<font size="2" face="Arial">&nbsp;CP :&nbsp;<b>&nbsp; <input type="text" size="14" maxlength="40" name="codePostal"
	value=<jsp:getProperty name="beanEmplGeneral" property="codePostal" />></b>&nbsp;&nbsp;</font>
	<font size="2" face="Arial">Ville</font>
	<font size="2" face="Arial"> :&nbsp;&nbsp;
	<input type="text" name="ville" value="<jsp:getProperty name="beanEmplGeneral" property="ville" />" >&nbsp;
	<input type="checkbox" onClick="modifierCedex()" name="cedexCk" <jsp:getProperty name="beanEmplGeneral" property="cedex"/>>Cédex</font>
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="26">
<p style="margin-left: 5"><b><font face="Arial" size="2">N° de téléphone :</font></b>
</td>
<td width="906" colspan="2" height="26">
<p style="margin-left: 2"><font size="2" face="Arial">Fixe :&nbsp;&nbsp;
<input type="text" size="24" maxlength="40" name="numTel"
value=<jsp:getProperty name="beanEmplGeneral" property="numTel" /> >&nbsp;&nbsp;
Portable :&nbsp;
<input type="text" size="23" maxlength="40" name="numPortable"
value=<jsp:getProperty name="beanEmplGeneral" property="numPortable" />
> &nbsp;&nbsp;&nbsp;&nbsp;Fax :&nbsp;</font>&nbsp;<font size="2" face="Arial"><b>
<input type="text" size="25" maxlength="40" name="numfax"
value=<jsp:getProperty name="beanEmplGeneral" property="numfax" /> >
</b></font>
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<p style="margin-left: 5"><font face="Arial" size="2"><b>Émail :</b></font>
</td>
<td width="906" colspan="2" height="23">
<p style="margin-left: 2">
<font size="2" face="Arial"><b>
<input type="text" size="29" maxlength="40" name="email"
value=<jsp:getProperty name="beanEmplGeneral" property="email" />></b></font>
</p>
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="25">
<p style="margin-left: 5"><font face="Arial" size="2"><b>Inscription :</b></font></p>
</td>
<td width="906" colspan="2" height="25">
<p style="margin-left: 2"><font size="2" face="Arial">
<input type="radio" value="RCS" checked name="codeInscription" >
RCS&nbsp;&nbsp;&nbsp; <input type="radio" value="RM" name="codeInscription" >
RM&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Numéro : <b>
<input type="text" size="17" maxlength="40" name="numInscription"
value=<jsp:getProperty name="beanEmplGeneral" property="numInscription"/>
></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Date d'inscription: <input type="text" size="17" maxlength="10" name="dateInscription" id="dateInscription" readonly
value=<jsp:getProperty name="beanEmplGeneral" property="dateInscription" />>


<img onclick="displayCalendar(document.forms[0].dateInscription,'dd/mm/yyyy',this)"
src="./images/calendar.gif" border="0" width="16" height="16" >

</font>

</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="26">
<p style="margin-left: 5"><font size="2" face="Arial"><b>Date
d'activité </b><font color="#FF0000">(JJ/MM/AAAA) *</font>:</font></td>
<td width="906" colspan="2" height="26">
<font size="2" face="Arial">
Début<font color="#FF0000">*</font></font>
<font size="2" face="Arial">
:</font>&nbsp;&nbsp;<font size="2" face="Arial"><b>
<input type="text" size="21" maxlength="10" name="dateDebutActivite" id="dateDebutActivite" readonly
value='<jsp:getProperty name="beanEmplGeneral" property="dateDebutActivite" />' >
</b></font>

<img onclick="displayCalendar(document.forms[0].dateDebutActivite,'dd/mm/yyyy',this)"
src="./images/calendar.gif" border="0" width="16" height="16" />
<font size="2" face="Arial">Fin :</font> <font size="2" face="Arial"><b>
<input type="text" size="17" maxlength="10" name="dateFinActivite" id="dateFinActivite" readonly
value=<jsp:getProperty name="beanEmplGeneral" property="dateFinActivite" /> ></b></font> &nbsp;

<img onclick="displayCalendar(document.forms[0].dateFinActivite,'dd/mm/yyyy',this)"
src="./images/calendar.gif" border="0" width="16" height="16" />
</td>
</tr>


<tr>
<td width="269" background="./images/fond_bleu.gif" height="19">
&nbsp;<b><font face="Arial" size="2">Rapprochement avec Idoss :</font>
</b>
</td>
<td width="906" colspan="2" height="19">
<jsp:getProperty name="beanEmplGeneral" property="rapprochementIdoss" />
</td>
</tr>





<tr>
<td width="269" background="./images/fond_bleu.gif" height="19">
&nbsp;<font face="Arial" size="2"><b>Information IDOSS :</b></font>
</td>
<td width="906" colspan="2" height="19">
<TEXTAREA READONLY name="infosIdoss" rows=5 COLS=110><jsp:getProperty name="beanEmplGeneral" property="infosIdoss"/></TEXTAREA>
</td>
</tr>





<tr>
<td width="269" background="./images/fond_bleu.gif" height="19">
<font face="Arial" size="2"><b>&nbsp;Activité emplacement </b><font color="#FF0000">*</font><b> :</b></font>
</td>
<td width="906" colspan="2" height="19">
<jsp:getProperty name="beanEmplGeneral" property="codeProfession" />
</td>
</tr>





<tr>
<td width="269" background="./images/fond_bleu.gif" height="16">
<p style="margin-left: 5"><font face="Arial" size="2"><b>Etat de
l'emplacement </b><font color="#FF0000">*</font><b> :</b></font></p>
</td>
<td width="906" colspan="2" height="16">
<p style="margin-left: 2">
<jsp:getProperty name="beanEmplGeneral" property="etatEmplacement" />

<%if( beanEmplGeneral.getNumEmplacement().length() > 0 ) {%>
<script>
if (document.forms[0].nombreOuvrageActif.value !='0')
document.write (" il y a des ouvrages actifs à cet emplacement !! ")
</script>
<% } %>
</td>
</tr>

<%
String libelleImputation = beanEmplGeneral.getLibelleImputation();
if( libelleImputation.equalsIgnoreCase("tlpe")) {%>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="26">
<p style="margin-left: 5"><font size="2" face="Arial"><b>Date de réception de la déclaration
 </b><font color="#FF0000">(JJ/MM/AAAA)</font>:</font></td>
<td width="906" colspan="2" height="26">
<input type="text" size="21" maxlength="10" name="dateReceptionDeclaration" id="dateReceptionDeclaration" readonly
value='<jsp:getProperty name="beanEmplGeneral" property="dateReceptionDeclaration" />' >
</b></font>
<img onclick="displayCalendar(document.forms[0].dateReceptionDeclaration,'dd/mm/yyyy',this)"
src="./images/calendar.gif" border="0" width="16" height="16" />
</td>
</tr>

<%}%>

<% if ( ! ville.equalsIgnoreCase("Bordeaux")) { %>

<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("STATIONNEMENT") ) {%>


<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations taxi :</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Numéro de taxi :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="taxiNumeroTaxi"
value="<%=beanEmplGeneral.getTaxiNumeroTaxi()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Numéro d'assurance :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="taxiNumeroAssurance"
value="<%=beanEmplGeneral.getTaxiNumeroAssurance()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Immatriculation :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="taxiImmatriculation" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getTaxiImmatriculation()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Numéro de carte de grise :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="taxiNumeroDeCarteGrise"
value="<%=beanEmplGeneral.getTaxiNumeroDeCarteGrise()%>" >
</font>
</td>
</tr>

<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations bus :</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Compagnie autobus :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="busCompanieAutobus" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getBusCompanieAutoBus()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nombre de car :</b></font>
</td>
<td width="906" colspan="2" height="23">

<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="busNombreCar"
value="<%=beanEmplGeneral.getBusNombreCar()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Ligne de bus :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="busLigneDeBus" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getBusLigneDeBus()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nombre de place assises :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="busNombreDePlaceAssises"
value="<%=beanEmplGeneral.getBusNombreDePlaceAssises()%>" >
</font>
</td>
</tr>

<%}%>

<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("MARCHE_MENSUELLE") || beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("MARCHE_TRIMESTRIELLE") ) {%>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations marché :</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nom marché :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="marcheNomMarche" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getMarcheNomMarche()%>" >
</font>
</td>
</tr>
<%}%>


<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("AMBULANT") ) {%>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations ambulant :</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nom tournee :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="ambulantNomTournee" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAmbulantNomTournee()%>" >
</font>
</td>
</tr>



<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Carte professionelle :</b></font></td>
<td width="906" colspan="2" height="23">
<table border="0" width="100%">
<tr>
<td width="50%"><font size="2" face="Arial"><b>Date d'obtention
:</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCarteProfessionelleDateObtention"
value="<%=beanEmplGeneral.getAmbulantCarteProfessionelleDateObtention()%>" >
</td>
<td width="50%"><font size="2" face="Arial"><b>N°
:&nbsp;</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCarteProfessionelleNumero"
value="<%=beanEmplGeneral.getAmbulantCarteProfessionelleNumero()%>" >
</td>
</tr>
</table>
</td>
</tr>



<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Certficat Hygiene :</b></font></td>
<td width="906" colspan="2" height="23">
<table border="0" width="100%">
<tr>
<td width="50%"><font size="2" face="Arial"><b>Date d'obtention
:</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCertficatHygieneDateObtention"
value="<%=beanEmplGeneral.getAmbulantCertficatHygieneDateObtention()%>" >
</td>
<td width="50%"><font size="2" face="Arial"><b>N°
:&nbsp;</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCertficatHygieneNumero"
value="<%=beanEmplGeneral.getAmbulantCertficatHygieneNumero()%>" >
</td>
</tr>
</table>
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Certificat pompier :</b></font></td>
<td width="906" colspan="2" height="23">
<table border="0" width="100%">
<tr>
<td width="50%"><font size="2" face="Arial"><b>Date d'obtention
:</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCertificatPompierDateObtention"
value="<%=beanEmplGeneral.getAmbulantCertficatPompierDateObtention()%>" >
</td>
<td width="50%"><font size="2" face="Arial"><b>N°
:&nbsp;</b></font>
<input type="text" size="29" maxlength="40" name="ambulantCertficatPompierNumero"
value="<%=beanEmplGeneral.getAmbulantCertficatPompierNumero()%>" >
</td>
</tr>
</table>
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Vehicule :</b></font>
</td>

<td width="906" colspan="2" height="23">
<table border="0" width="100%">
<tr>
<td width="33%">&nbsp;<font size="2" face="Arial"><b>Marque
:</b></font>
<input type="text" size="22" maxlength="40" name="ambulantVehiculeMarque" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAmbulantVehiculeMarque()%>" >
</td>
<td width="33%">&nbsp;<font size="2" face="Arial"><b>Modele
:</b></font>
<input type="text" size="22" maxlength="40" name="ambulantVehiculeModele" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAmbulantVehiculeModele()%>" >
</td>
<td width="34%">&nbsp;<font size="2" face="Arial"><b>Immatriculation
:</b></font>
<input type="text" size="22" maxlength="40" name="ambulantVehiculeImmatriculation" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAmbulantVehiculeImmatriculation()%>" >
</td>
</tr>
</table>
</td>
</tr>


<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Compagnie assurance :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="ambulantCompanieAssurance" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAmbulantCompanieAssurance()%>" >
</font>
</td>
</tr>
<%}%>

<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("CONVENTION_TRIMESTRIELLE") || beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("CONVENTION_ANNUELLE")) {%>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations convention
:</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Convention :</b></font>
</td>
<td width="906" colspan="2" height="23">
<table border="1" width="100%">
<tr>
<td width="16%"><font size="2" face="Arial"><b>Date : </b></font>
<font size="2" face="Arial"><input type="text" size="15" maxlength="40" name="conventionDate"
value="<%=beanEmplGeneral.getConventionDate()%>" >
</font>
</td>
<td width="36%"><font size="2" face="Arial"><b>Durée : </b><input type="text" size="15" maxlength="40" name="conventionDureeAns"
value="<%=beanEmplGeneral.getConventionDureeAns()%>" ><b>ans </b><input type="text" size="15" maxlength="40" name="conventionDureeMois"
value="<%=beanEmplGeneral.getConventionDureeMois()%>" ><b>mois&nbsp;</b></font></td>
<td width="23%"><font size="2" face="Arial"><b>Date d'effet :</b></font><font size="2" face="Arial"><b>
</b><input type="text" size="15" maxlength="40" name="conventionDateEffet"
value="<%=beanEmplGeneral.getConventionDateEffet()%>" >
</font></td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Date première révision :</b></font></td>
<td width="906" colspan="2" height="23">
<table border="0" width="100%">
<tr>
<td width="50%"><font size="2" face="Arial"><input type="text" size="15" maxlength="40" name="conventionDatePremiereRevision"
value="<%=beanEmplGeneral.getConventionDatePremiereRevision()%>" >
</font>
</td>
<td width="50%"><font size="2" face="Arial"><b>Durée :&nbsp;&nbsp; </b><input type="text" size="15" maxlength="40" name="conventionDureeDatePremiereRevision"
value="<%=beanEmplGeneral.getConventionDureeDatePremiereRevision()%>" ><b>mois&nbsp;</b></font></td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Objet convention :</b></font></td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="conventionObjet" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getConventionObjet()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Libellé convention :</b></font></td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="conventionLibelle" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getConventionLibelle()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Renouvellement :</b></font>
</td>

<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="conventionRenouvellement"
value="<%=beanEmplGeneral.getConventionRenouvellement()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Montant du contionnement&nbsp;
:</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="conventionMontantDuConditionnement"
value="<%=beanEmplGeneral.getConventionMontantDuConditionnement()%>" ></font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Reconduction :</b></font>
</td>
<td width="906" colspan="2" height="23">
<table border="1" width="100%">
<tr>
<td width="47%">&nbsp;<font size="2" face="Arial">
<jsp:getProperty name="beanEmplGeneral" property="conventionRecondiction"/></font></td>
<td width="53%">&nbsp;<font size="2" face="Arial">&nbsp;<b>
Si Oui : Nombre maximum :</b><input type="text" size="21" maxlength="40"
name="conventionRecondictionNombreMaximum"
value="<%=beanEmplGeneral.getConventionRecondictionNombreMaximum()%>" ></font></td>
</tr>
</table>
</td>
</tr>
<%}%>









<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("ODP") ) {%>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations droit de
voirie
:</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Autorisation :</b></font>
</td>
<td width="906" colspan="2" height="23">
<table border="1" width="100%" height="24">
<tr>
<td width="25%" height="18"><font size="2" face="Arial"><b>N° :</b></font><font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieAutorisationNumeroAutorisation"
value="<%=beanEmplGeneral.getDroitDeVoirieAutorisationNumeroAutorisation()%>" >
</font>
</td>
<td width="25%" height="18"><font size="2" face="Arial"><b>Date
:</b></font><font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieAutorisationDate"
value="<%=beanEmplGeneral.getDroitDeVoirieAutorisationDate()%>" >
</font>
</td>
<td width="25%" height="18"><font size="2" face="Arial"><b>Type
:</b></font><font size="2" face="Arial"><input type="text" size="19" maxlength="40" onkeyup="caps(this)"
name="droitDeVoirieAT"
value="<%=beanEmplGeneral.getDroitDeVoirieAutorisationType()%>" >
</font>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;</b></font><font size="2" face="Arial"><b>Travaux</b></font><font size="2" face="Arial"><b>
:</b></font></td>
<td width="906" colspan="2" height="23">
<table border="1" width="100%">
<tr>
<td width="50%"><font size="2" face="Arial"><b>Durée</b></font><font size="2" face="Arial"><b>
:</b></font> <font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieTraveauxDureeMois"
value="<%=beanEmplGeneral.getDroitDeVoirieTraveauxDureeMois()%>" ></font>&nbsp;<b>
mois</b>&nbsp;&nbsp;&nbsp; <font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieTraveauxDureeJour"
value="<%=beanEmplGeneral.getDroitDeVoirieTraveauxDureeJour()%>" ></font>
<b>jour</b></td>
<td width="24%"><b>Executé</b><font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieTraveauxExecute"
value="<%=beanEmplGeneral.getDroitDeVoirieTraveauxExecute()%>"  onkeyup="caps(this)">
</font>
</td>
<td width="26%"><b>Conforme :</b><font size="2" face="Arial"><input type="text" size="19" maxlength="40" name="droitDeVoirieTraveauxConforme"
value="<%=beanEmplGeneral.getDroitDeVoirieTraveauxConforme()%>" onkeyup="caps(this)">
</font>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nom inspecteur :</b></font></td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="droitDeVoirieNomInspecteur"
value="<%=beanEmplGeneral.getDroitDeVoirieNomInspecteur()%>" onkeyup="caps(this)" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nature :</b></font></td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="droitDeVoirieNature" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getDroitDeVoirieNature()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Objet des travaux :</b></font>
</td>

<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="droitDeVoirieObjetDeTraveaux" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getDroitDeVoirieObjetDeTraveaux()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Objet d'autorisation</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="40" maxlength="40" name="droitDeVoirieObjetDeAutorisation" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getDroitDeVoirieObjetDeAutorisation()%>" >
</font>
</td>
</tr>
<%}%>






<%if( beanEmplGeneral.getLibelleImputation().equalsIgnoreCase("KIOSQUE") ) {%>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Informations kiosque :</font></b>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Catégorie :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="kiosqueCategorie" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getKiosqueCategorie()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nature vente :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="kiosqueNatureVente" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getKiosqueNatureVente()%>" >
</font>
</td>
</tr>
<%}%>




<% } %>

<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Propriétaire :</font></b>&nbsp;
</td>
</tr>

<tr>
<td width="269" background="./images/fond_bleu.gif" height="22">
<font size="2" face="Arial"><b>&nbsp;</b></font><b><font size="2" face="Arial">Raison
sociale :</font></b></td>
<td width="906" colspan="2" height="22">
<font size="2" face="Arial"><b>
<jsp:getProperty name="beanEmplGeneral" property="raisonSocialeProprietaire"/></b></font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Nom :</b></font></td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40" name="nomProprietaire" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getNomProprietaire()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Prénom :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="29" maxlength="40"
name="prenomProprietaire" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getPrenomProprietaire()%>" >
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="23">
<font size="2" face="Arial"><b>&nbsp;Numéro de voie :</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial">
<b>N°</b>
<input type="text" name="numVoieProprietaire" size="3" value=<jsp:getProperty name="beanEmplGeneral" property="numVoieProprietaire"/> >
&nbsp;<b>Compl&nbsp; </b><jsp:getProperty name="beanEmplGeneral" property="complementNumeroRueProprietaire" />
</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="78" rowspan="3">
<font size="2" face="Arial"><b> &nbsp;Adresse&nbsp;:</b></font>
</td>
<td width="906" colspan="2" height="23">
<font size="2" face="Arial"><input type="text" size="27" maxlength="40"
name="adressProprietaire" onkeyup="caps(this)"
value="<%=beanEmplGeneral.getAdressProprietaire()%>" >
</font>
</td>
</tr>
<tr>
<td width="440" height="23">
<p style="margin-left: 2">
<font size="2" face="Arial">Compl. Adresse &nbsp; :
<input type="text" onkeyup="caps(this)"
name="complement1AdressProprietaire" id="complement1AdressProprietaire"
value="<%=beanEmplGeneral.getComplement1AdressProprietaire()%>" >
</font></p>
</td>
<td>
	<p style="margin-left: 2;margin-top:2">
		<b>&nbsp;Recherche ville par code : </b>
    	<input type="text" name="codeRechercheProp" size="10">
    	<a href="javascript:rechercheCodeVilleProp(document.forms[0].codeRechercheProp.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
    	&nbsp;&nbsp;<span id="listeCodeVilleProp"></span>
	</p>	
</td>
</tr>
<tr>
<td width="440" height="32">
<p style="margin-left: 2">
<font size="2" face="Arial">Compl. Adresse&nbsp; :&nbsp;
<input type="text" onkeyup="caps(this)"
name="complement2AdressProprietaire" id="complement2AdressProprietaire"
value="<%=beanEmplGeneral.getComplement2AdressProprietaire()%>" >
</font></p>
</td>
<td width="464" height="23" >
<font size="2" face="Arial">&nbsp;CP&nbsp;:&nbsp;
<input type="text"
name="codePostaleProprietaire" id="codePostaleProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="codePostaleProprietaire" />"
maxlength="5" size="12"></font>
<font size="2" face="Arial">&nbsp;Ville :&nbsp;
<input type="text" name="villeeProprietaire" id="villeeProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="villeeProprietaire" />"
maxlength="28" size="28">&nbsp;
<input type="checkbox"
name="cedexProprietaireCk" onClick="modifierCedexProprietaire()"
<jsp:getProperty name="beanEmplGeneral" property="cedexProprietaire" /> >
Cédex</font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="15">
<p style="margin-left: 5"><b><font face="Arial" size="2">N° de téléphone : </font></b>
</td>
<td width="906" colspan="2" height="15">
<p style="margin-left: 2"><font size="2" face="Arial">Fixe :&nbsp;&nbsp;
<input type="text"
name="numTelFixeProprietaire" id="numTelFixeProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="numTelFixeProprietaire" />"
maxlength="14" size="18">&nbsp;&nbsp;&nbsp;

Portable :&nbsp;&nbsp;
<input type="text"
name="numTelPortableProprietaire" id="numTelPortableProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="numTelPortableProprietaire" />"
maxlength="14" size="19">&nbsp;&nbsp;&nbsp;Fax :&nbsp;&nbsp;
<input type="text"
name="numTelFaxeProprietaire" id="numTelFaxeProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="numTelFaxeProprietaire" />"
maxlength="14" size="14"></font>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="20">
<p style="margin-left: 5"><font face="Arial" size="2"><b>Émail :</b></font></p>
</td>
<td width="906" colspan="2" height="20">
<p style="margin-left: 2">
<font size="2" face="Arial">
<input type="text"
name="emailProprietaire" id="emailProprietaire"
value="<jsp:getProperty name="beanEmplGeneral" property="emailProprietaire" />"
maxlength="60" size="55"></font></p>
</td>
</tr>
<tr>
<td width="1175" height="12" bgcolor="#FFFFE8" colspan="3">
<b><font size="3" face="Arial" color="#003399">Redevable autorisé :</font></b>&nbsp;
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="20">
<b><font face="Arial" size="2"> &nbsp;Informations sur le redevable
autorisé</font></b><font color="#FF0000">* </font><b><font face="Arial" size="2">:</font></b>&nbsp;
</td>
<td width="906" colspan="2" height="20">
<table border="1" width="100%">
<tr>
<td width="100%">
<table border="1" width="100%">
<tr>
<td width="22%"><font size="2" face="Arial">Numéro du
redevable autorisé&nbsp; :&nbsp;
</font></td>
<td width="78%">
<input readonly type="text" name="idRedevableAutorise" size="33" value="<jsp:getProperty name="beanEmplGeneral" property="idRedevableAutorise" />"/></td>
</tr>
<tr>
<td width="22%"><font size="2" face="Arial">Nom du redevable
autorisé&nbsp; :&nbsp;
</font></td>
<td width="78%"><input type="text" name="nomRedevable" size="33" onkeyup="caps(this)"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="Arial" size="3"><a href="javascript:rechercheRedevable(document.forms[0].nomRedevable.value)" ><img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">
<b>echercher Redevable</b></a><b>&nbsp;</b></font>













</td>
</tr>
<tr>




<td width="100%" colspan="2" height="20">
<div id="listeRedevable" style="width: 593; height: 20"> </div>
</td>

</tr>
<tr>




<td width="100%" colspan="2" height="20">
<div id="lienRedevable" style="width: 593; height: 20"> </div>
</td>

</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="269" background="./images/fond_bleu.gif" height="20">
<b><i><font color="#FF0000" size="2">(*)Champs
obligatoires</font></i></b>
&nbsp;
</td>
<td width="906" colspan="2" height="20">
<p align="center">

<% if (peutAcceder) { %>

<%if( beanEmplGeneral.getNumEmplacement().length() != 0 ) {%>
<a href="javascript:modifier();"><img border="0" src="./images/modifier.gif" width="150" height="17"></a>
<a href="javascript:supprimer();"><img border="0" src="./images/supprimer.gif" width="150" height="17"></a>
<%} else {%>
<a href="javascript:creer();"><img border="0" src="./images/valider.gif" width="150" height="17"></a>
<%}%>
<% } %>
</td>
</tr>

<tr>
<td width="269" height="19">
<p style="margin-left: 5"></p>
</td>
<td width="906" colspan="2" height="19" align="center">
<p align="center">
</td>
</tr>

</table>

<!-- 
<p>&nbsp;</p>
 -->
<!-- Paul -->
<table border="1" width="100%" height="1" style="margin-top: 10px;margin-bottom: 10px">
<tr>
<td colspan="2" width="1150" height="1" bgcolor="#AFF3BB" >
<b><font size="4"><span style="text-transform: uppercase">Geolocaliser 
l'emplacement :&nbsp;</span></font></b>
<font face="Arial" size="3"><a href="javascript:geolocaliser()" ><img height="20" src="images/lettre_a.gif" width="20" align="absMiddle" border="0">
<b>fficher</b></a><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>

<font face="Arial" size="3"><a href="javascript:fermer()" ><img height="20" src="images/lettre_f.gif" width="20" align="absMiddle" border="0">
<b>ermer</b></a><b>&nbsp;</b></font>
</td>
</tr>
<tr>
<td colspan="2" width="1150" height="1" >
<div id="googleMap" style="width:100%;height:500px;display:none"></div>
</td>
</tr>
</table>

<table border="1" width="100%" height="1">
<tr>
<td width="1150" colspan="2" bgcolor="#AFF3BB" height="42">
<p align="center"><img border="0" src="images/camera.png" width="40" height="40"></td>
</tr>

<tr>
<td width="1150" colspan="2" bgcolor="#AFF3BB" height="22"><b><span style="text-transform: uppercase"><font size="4">Liste
des PHOTOS POUR CET EMPLACEMENT :</font></span></b></td>
</tr>


<%if( ! beanEmplGeneral.getNombreImageEmplacement().equalsIgnoreCase("0") ) {%>
<tr>
<td width="268" height="316">
<b>
<table border="1" width="100%" height="478">
<tr>
<td width="464%" align="center" colspan="2" height="19" bgcolor="#DFFFEF">
<b>
<font color="#000080" size="3">Nom de la photo :</font></b>
</td>
</tr>
<tr>
<td width="464%" align="center" colspan="2" height="19">
<p align="center"><b><input type="text" name="nomImageEmplacement" readonly size="26" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></p>
</td>
</tr>
<tr>
<td width="464%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><b><font color="#000080">Date de
création</font> :</b>
</td>
</tr>
<tr>
<td width="464%" align="center" colspan="2" height="19"><center><b><input type="text" name="dateCreationImageEmplacement" readonly size="25" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
</td>
</tr>
<tr>
<td width="464%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><b><font color="#000080">N°
de la photo</font></b> :</td>
</tr>
<tr>
<td width="464%" align="center" colspan="2" height="23"><center><b><input type="text" name="numeroImageEmplacementCourante" readonly size="24" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
</td>
</tr>
<tr>
<td width="50%" align="center" height="19"><font color="#000000"><b>
<a href="javascript:precedantImageEmplacement()">&lt;&lt;Prec</a>
</b></font></td>
<td width="50%" align="center" height="19"><font color="#000000"><b>
<a href="javascript:suivantImageEmplacement()">Suiv&gt;&gt;</a>
</b></font></td>
</tr>

<% if (peutAcceder) { %>
<tr>
<td width="464%" align="center" colspan="2" height="19"><b>
<a href="javascript:supprimerImageEmplacement();">
<font color="#000080">Supprimer cette photo</font></a></b></td>
</tr>

<% } %>

<tr>
<td width="464%" align="center" colspan="2" height="19">
<b>
<a href="javascript:actualiser();">Actualiser</a>
</b>
</td>
</tr>

<tr>
<td width="464%" align="center" colspan="2" height="293">
<p>DIAPORAMA

<div class="highslide-gallery">
<a id="thumb1" href='./diaporama/images/diaporama.jpg'
onclick="return diaporama1.expand(this, miniGalleryOptions1)">
<img src='./diaporama/images/diaporama.jpg' alt=''/>
</a>

<%
Vector imagesEmplacment = beanEmplGeneral.getImagesEmplacement();
for (int i = 0; i < imagesEmplacment.size() ; i++)
{
String nomImage =(String)imagesEmplacment.elementAt(i);
String lien = "./r2tData/photos/"+nomImage;
%>

<div class="hidden-container">
<a class='highslide' href="<%out.print(lien);%>" title="<%out.print(nomImage);%>"
onclick="return diaporama1.expand(this, miniGalleryOptions1)">
<img src="<%out.print(lien);%>" alt=''/></a>
</div>
<% } %>
</div>
&nbsp;
</td>
</tr>
</table>





</b>
</td>
<td width="876" height="316">
<p align="center">

<img border="0" id="pic" src="" >

</td>
</tr>
<% }else{ %>
<tr>
<td colspan="2" width="1150" height="1" >
<p align="center"><b>Pas de photos disponibles pour cet emplacement</b></p>
</td>
</tr>
<% } %>
<!--Intitialisation-->
<script>
initImagesEmplacement();
if(document.forms[0].cedex.value=='true')
{
document.forms[0].cedexCk.checked=true;
}
else
{
document.forms[0].cedexCk.checked=false;
}

if(document.forms[0].cedexProprietaire.value=='true')
{
document.forms[0].cedexProprietaireCk.checked=true;
}
else
{
document.forms[0].cedexProprietaireCk.checked=false;
}


if('<%=beanEmplGeneral.getCodeInscription()%>'=='RCS')
{
document.forms[0].codeInscription[0].checked=true;
}
else
{
document.forms[0].codeInscription[1].checked=true;
}
</script>
</form>

<% if (peutAcceder) { %>
<tr>
<td colspan="2" width="1150" height="1" bgcolor="#AFF3BB" >
<b><font size="4"><span style="text-transform: uppercase">Ajouter des
photos :&nbsp;</span></font></b>
</td>
</tr>
<tr>
<td colspan="2" width="1150" height="1" >
<form method="post" enctype="multipart/form-data">
<input type="file" name="myimage">
<input type="button" type="button" value="Valider" onclick="javascript:uploadFichier();"/>
</form>
</td>
</tr>
<% } %>
</table>




<BR>
<% if (peutAcceder) { %>
<table border="1" width="100%" height="1">
<tr>
<td colspan="2" width="1150" height="1" bgcolor="#AFF3BB" >
<b><font size="4"><span style="text-transform: uppercase">Ajouter des
documents :&nbsp;</span></font></b>
</td>
</tr>
<tr>
<td colspan="2" width="1150" height="1" >
<form method="post" enctype="multipart/form-data">
   <input type="file" id="mydocument" name="mydocument">
   <input type="button" type="button" value="Valider" onclick="javascript:uploadFichierDocument();"/>
</form>
</td>
</tr>
<tr>
<td colspan="2" width="1150" height="1" >
<%=beanEmplGeneral.getListeDesDocuements()%>
</td>
</tr>
<% } %>
</table>


<script>
majLienRedevable(document.forms[0].idRedevableAutorise.value);
</script>



<p align="center"><font face="Arial" size="3" color="#0000FF"><b><a href="./entree?action=empl_resultat_recherche_redevable.jsp">Liste
des redevables</a></b></font></p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

</div>
<script>
reglerdimention();
</script>











</body>
</html>




