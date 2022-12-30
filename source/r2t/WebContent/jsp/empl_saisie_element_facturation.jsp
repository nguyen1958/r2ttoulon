<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<%@ page language = "java" %>
<%@ page import="java.util.Vector"%> 
<jsp:useBean id="baffElementFacturation" scope="page" class="fr.analogon.r2t.view.regie.BaffElementFacturation" />
<jsp:setProperty name="baffElementFacturation" property="request" value="<%=request%>"/>
<jsp:useBean id="baffListeAlertes" scope="page" class="fr.analogon.r2t.view.alertes.BAffListeAlertes" />
<jsp:setProperty name="baffListeAlertes" property="request" value="<%=request%>"/>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./diaporama/highslide/highslide-with-gallery.js"></script>
<link rel="stylesheet" type="text/css" href="./diaporama/highslide/highslide.css" />
<script type="text/javascript" src="./resources/js/controle.js"></script>
<script type="text/javascript" src="./resources/js/jquery.js"></script>



<script type="text/javascript">



diaporama1.graphicsDir = './diaporama/highslide/graphics/';
diaporama1.align = 'center';
diaporama1.transitions = ['expand', 'crossfade'];
diaporama1.fadeInOut = true;
diaporama1.outlineType = 'glossy-dark';
diaporama1.wrapperClassName = 'dark';
diaporama1.captionEval = 'this.a.title';
diaporama1.numberPosition = 'caption';
diaporama1.useBox = true;
diaporama1.width = 900;
diaporama1.height = 700;
diaporama1.dimmingOpacity = 0.8;

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




<script type="text/javascript">
diaporama2.graphicsDir = './diaporama/highslide/graphics/';
diaporama2.align = 'center';
diaporama2.transitions = ['expand', 'crossfade'];
diaporama2.fadeInOut = true;
diaporama2.outlineType = 'glossy-dark';
diaporama2.wrapperClassName = 'dark';
diaporama2.captionEval = 'this.a.title';
diaporama2.numberPosition = 'caption';
diaporama2.useBox = true;
diaporama2.width = 900;
diaporama2.height = 700;
diaporama2.dimmingOpacity = 0.8;

// Add the slideshow providing the controlbar and the thumbstrip
diaporama2.addSlideshow({
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
var miniGalleryOptions2 = {
	thumbnailId: 'thumb2'
}
</script>






</head>
<body background="./images/nuages.jpg" topmargin="0" vlink="#0000FF" alink="#0000FF">









<script> 
  
<!--Generation des images de l'emplcament-->
<%=baffElementFacturation.getTableauImagesEmplacement()%>
<%=baffElementFacturation.getTableauImagesAlerte()%>

function chargerPeutEtreModifier()
{
   //alert(document.getElementById("quantite").readOnly);
   if(document.getElementById("peutEtreModifier").value== "false" ) 
   {
      if (document.getElementById("Surface") != null )  document.getElementById("Surface").readOnly = true;
	  if (document.getElementById("Largeur") != null )  document.getElementById("Largeur").readOnly = true;
	  if (document.getElementById("Longueur") != null )  document.getElementById("Longueur").readOnly = true;
	  if (document.getElementById("nombreFaceAffiche") != null )  document.getElementById("nombreFaceAffiche").readOnly = true;
	  
	  
	  
	  document.getElementById("quantite").readOnly = true;	  	  
	  document.getElementById("dateDebutAutorisation").readOnly = true;
	  document.getElementById("calenderDateDebutAutorisation").style.visibility="hidden";	  
	  //document.getElementById("trchoix").style.visibility="hidden"; 
	  document.getElementById("choixDimension").style.visibility="hidden"; 
	  //Seul l'adminstrateur de la taxe peut modifer ce champs apres facturation
	  if ( document.getElementById("peutModiferDernierePeriode").value == "false" )
	     document.getElementById("dernierePeriodeFacture").readOnly = true;	  
	  
   }
}

function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}

function reglerdimention()
{
	//alert (document.getElementById("pic").width);
	//if (document.getElementById("pic").height > 488) document.getElementById("pic").height =488;
	//if (document.getElementById("pic").width > 722) document.getElementById("pic").width =722;
	if(document.getElementById("pic") != null)
	{
	  document.getElementById("pic").height =488;
	  document.getElementById("pic").width =822;
	}	
}

function  remplirDureeUnitaire() 
{
    var valBareme=document.forms[0].codeBareme.value;	
	document.forms[0].dureeunitaire.value="";
	if(valBareme.indexOf("/JOUR")>0) 	document.forms[0].dureeunitaire.value="JOUR";	
	if(valBareme.indexOf("/MOIS")>0) 	document.forms[0].dureeunitaire.value="MOIS";	
	if(valBareme.indexOf("/SEMAINE")>0) 	document.forms[0].dureeunitaire.value="SEMAINE";	
	if(valBareme.indexOf("/AN")>0) 	document.forms[0].dureeunitaire.value="AN";
	//alert(document.forms[0].dureeunitaire.value);
}

 function validerAlerte()
 {     
     document.forms[0].action="gestionelementfacturation?actionAlerte=validerAlerte";
   	  document.forms[0].choix.value="modifier";	
	   if (document.forms[0].etatAlerte.value=="CLOTURER")	   
	   {
	     if (document.forms[0].nombreImageAlerte.value !="0")
	     {	     
	       ret=confirm("Voulez vous que toutes les images des alertes seront ajouter au images de cet emplacement ?");            
          if (ret == true)
          { 
             document.forms[0].action="gestionelementfacturation?actionAlerte=validerAlerte&transfertImageAlerte=ok";
          }
          else
          {
            //alert("Pas de transfert d'image");
          }
        }                             
       }
       document.forms[0].submit(); 
 }






function supprimerImageEmplacement()
{
	ret=confirm("Etes-vous sure de supprimer cette image ?");            
            if (ret == true)
            {   
                document.forms[0].nomImageASupprimer.value=  document.forms[0].nomImageEmplacement.value;
  			    document.forms[0].choix.value="modifier";				 
		        modifierOuvrage();                
            }
   			//window.location.reload( false );

}

function supprimerImageAlerte()
{
            ret=confirm("Etes-vous sure de supprimer cette image ?");            
            if (ret == true)
            {
	            document.forms[0].action=	"gestionelementfacturation";
				  document.forms[0].nomImageAlerteASupprimer.value=  document.forms[0].nomImageAlerte.value;
  			     document.forms[0].choix.value="modifier";				 
   			     document.forms[0].source.value="ouvrage";
		        document.forms[0].submit();                
               
            }
}


function modifierOuvrage()
{
  
  validerArticle();
}
function majSurface(v)
{
//alert("maj surface"+v);
document.forms[0].surface.value= v;
}


function remplirChoixDimension()
{
    contenuDiv="<font face=\"Arial\" size=\"2\"><input type=\"radio\"  value=\"lonlarg\" checked name=\"longSuraface\" onClick=choixDimension1()>";
	contenuDiv=contenuDiv+"Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface";
	contenuDiv=contenuDiv+"<input type=\"radio\" value=\"surf\" name=\"longSuraface\" onclick=\"choixDimension2()\" ></font>";
   document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
}


function initMajDimension()
{

	var valBareme=document.forms[0].codeBareme.value;	

	if(valBareme.indexOf("ML/")>0)
	{
	 contenuDiv= "<b>Longueur: </b><input type='text' onchange='changerLongueur(this.value)' value="+document.forms[0].longueur.value+">"; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv;
	}
	if(valBareme.indexOf("UNITE/")>0)
	{
	 contenuDiv="<b> - </b>";
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	if(valBareme.indexOf("FORFAIT")>0)
	{
	 contenuDiv="<b> - </b>";
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	 document.forms[0].longueur.value=1;
	 document.forms[0].largeur.value=1;
	 document.forms[0].surface.value=0;
	}
	if(valBareme.indexOf("M2/")>0)
	{
	   var surface= document.forms[0].surface.value;
	   if(surface==0)
	   {
	   		
	   		contenuDiv= "<b>Longueur:</b><input id ='Longueur' type='text' onchange='changerLongueur(this.value)' value=\""+document.forms[0].longueur.value+"\" /> <b>Largeur</b>: <input type='text'  id='Largeur' onchange='changerLargeur(this.value)'          value="+document.forms[0].largeur.value+"> "; 
	 		document.getElementById("dimensionArticle").style.height=24;
	 		document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
			remplirChoixDimension();
		}
		else
		{
			 contenuDiv="<font face=\"Arial\" size=\"2\"><input type=\"radio\"  value=\"lonlarg\"  name=\"longSuraface\" onClick=choixDimension1()>";
			contenuDiv=contenuDiv+"Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface";
			contenuDiv=contenuDiv+"<input type=\"radio\" value=\"surf\" name=\"longSuraface\" checked onclick=\"choixDimension2()\" ></font>";
  			 document.getElementById("choixDimension").style.height=24;
	 		document.getElementById("choixDimension").innerHTML = contenuDiv;
		   contenuDiv= "<b>Surface: </b><input id='Surface' type='text'   onchange='changerSurface(this.value)'   value="+document.forms[0].surface.value+" onchange=\"majSurface(this.value)\" >";  
 			document.getElementById("dimensionArticle").style.height=24;
	 		document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
			
		}
	}
	reglerdimention();
	remplirDureeUnitaire();
	
	
}



function majDimension()
{
	var valBareme=document.forms[0].codeBareme.value;
	if(valBareme.indexOf("FORFAIT")>0)
	{
	 contenuDiv="<b> - </b>";
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	if(valBareme.indexOf("ML/")>0)
	{
	 contenuDiv="<b> Longueur: </b><input type='text' name='longeur1' ONBLUR='changerLongueur(this.value)'   ONFOCUS='changerLongueur(this.value)'   >";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
	  document.forms[0].longueur.value=''; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv;
	}
	if(valBareme.indexOf("UNITE/")>0)
	{
	 contenuDiv="<b> - </b>";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
    document.forms[0].longueur.value='1'; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	if(valBareme.indexOf("M2/")>0)
	{
		choixDimension1();
		remplirChoixDimension();		 
	}	
	remplirDureeUnitaire();
}


function changerSurface(d)
{
  document.forms[0].surface.value=d;
}

function changerLargeur(d)
{
  document.forms[0].largeur.value=d;
}

function changerLongueur(d)
{
  document.forms[0].longueur.value=d;
}

function choixDimension1()
{
 contenuDiv= "<b>Longueur: </b><input type='text' name='longeur1' ONBLUR='changerLongueur(this.value)' onfocus='changerLongueur(this.value)'>";  
 contenuDiv= contenuDiv+"<b>     Largeur:</b> <input type='text' name='largeur1' ONBLUR='changerLargeur(this.value)'>"; 
 document.forms[0].surface.value='0'; 
 document.forms[0].largeur.value=''; 
 document.forms[0].longueur.value=''; 
 document.getElementById("dimensionArticle").style.height=24;
 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
}

function choixDimension2()
{
 contenuDiv= "<b>Surface: </b><input type='text' name='surface1' ONBLUR='changerSurface(this.value)'  ONfocus='changerSurface(this.value)' > "; 
 document.forms[0].largeur.value='1'; 
 document.forms[0].longueur.value='1'; 
 document.forms[0].surface.value=''; 
 document.getElementById("dimensionArticle").style.height=24;
 document.getElementById("dimensionArticle").innerHTML = contenuDiv;  
}



function uploadFichier()
{
 

   if (document.forms[1].myimage.value.length !=0)
  { 
	   //window.location.reload( false );     
      document.forms[1].action="importImageEmplacment?source=ouvrage&idarticle="+document.forms[0].idarticle.value+"&numEmplacment="+document.forms[0].numEmplacment.value ;  
       document.forms[1].submit();   
  }
  else
  {
    alert("Veuillez indiquer le chemin vers la photo ! ");
  }
}



function afficherNumeroImageAlerte()
{   
   var a =document.forms[0].numeroImageAlerte.value ;   
   var b =document.forms[0].tailleTableauAlerte.value;  
	var ai = parseInt(a)+1;
   document.forms[0].numeroImageAlerteCourante.value=ai+"/"+ b;
}


function suivantImageAlerte()
{  
  tableauImageAlerte= getTableauImagesAlerte();
  document.forms[0].numeroImageAlerte.value++;
  document.forms[0].tailleTableauAlerte.value=tableauImageAlerte.length;  
 if (document.forms[0].numeroImageAlerte.value == tableauImageAlerte.length)
      document.forms[0].numeroImageAlerte.value=0;
  var pathFichier=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][0];
  var nomFichier= pathFichier.substr(17,pathFichier.length); 
  document.forms[0].nomImageAlerte.value=nomFichier;
  document.forms[0].dateCreationImageAlerte.value=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][1];	
  document.getElementById('picAlerte').src=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][0]; 
  afficherNumeroImageAlerte();
  reglerdimention();
  
}

function precedantImageAlerte()
{

  tableauImageAlerte= getTableauImagesAlerte();
  document.forms[0].numeroImageAlerte.value--;
  document.forms[0].tailleTableauAlerte.value=tableauImageAlerte.length;  
  if (document.forms[0].numeroImageAlerte.value < 0 )
      document.forms[0].numeroImageAlerte.value=tableauImageAlerte.length-1;
  var pathFichier=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][0];
  var nomFichier= pathFichier.substr(17,pathFichier.length); 
  document.forms[0].nomImageAlerte.value=nomFichier;
  document.forms[0].dateCreationImageAlerte.value=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][1];	
  document.getElementById('picAlerte').src=tableauImageAlerte[document.forms[0].numeroImageAlerte.value][0]; 
  afficherNumeroImageAlerte();
  reglerdimention();
 
}




function initImagesAlerte()
{   
		tableauImageAlerte= getTableauImagesAlerte();	
		document.forms[0].numeroImageAlerte.value=0;
		document.forms[0].tailleTableauAlerte.value=tableauImageAlerte.length;
		if(tableauImageAlerte.length!=0)
		{
		  var nomFichierAlerte= tableauImageAlerte[0][0].substr(17,tableauImageAlerte[0][0].length); 
		  document.forms[0].nomImageAlerte.value=nomFichierAlerte;
		  document.forms[0].dateCreationImageAlerte.value=tableauImageAlerte[0][1];
		  document.getElementById('picAlerte').src=tableauImageAlerte[0][0]; 
		  afficherNumeroImageAlerte();
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
   var nomFichier= tableauImageEmplacement[0][0].substr(17,tableauImageEmplacement[0][0].length); 
	document.forms[0].nomImageEmplacement.value=nomFichier;
	document.forms[0].dateCreationImageEmplacement.value=tableauImageEmplacement[0][1];	
	document.getElementById('pic').src=tableauImageEmplacement[0][0]; 
	afficherNumeroImageEmplacement();
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
 
}



   
  
  function majListeBareme()
  {

    bIndic= document.forms[0].baremeIndicatif.value;
    nbB=document.forms[0].codeBareme.options.length;
    for(var i=0;i <nbB;i++){
      option=document.forms[0].codeBareme.options[i];
      if(option.value==bIndic){
        option.selected=true;
        break;
      }
    }

  }

    function valider()
    {
  
      quantite=  document.forms[0].quantite.value;
	  var res=  verifier("quantite",quantite);
	  var res=  verifier("quantite",quantite);
	  alert (res);
      if(res==0)
      {
          document.forms[0].submit();       
      }
    }

  

    function validerArticle()
    {
      longueur=  document.forms[0].longueur.value;
      largeur=   document.forms[0].largeur.value;
      quantite=  document.forms[0].quantite.value;
      surface=  document.forms[0].surface.value;   
	  dernierePeriodeFacture =	document.forms[0].dernierePeriodeFacture.value;   
      var res1=  verifier("longueur",longueur);
      var res2=  verifier("largeur",largeur);
      var res3=  verifier("quantite",quantite);
	  var res4=  verifier("surface",surface);
	  var res5=  verifier("dernierePeriodeFacture",dernierePeriodeFacture);
	  	  
	  res1 = TestInt(longueur,"longueur");
	  res2 = TestInt(largeur,"largeur");
	  res3 = TestInt(quantite,"quantite");
	  res4 = TestInt(surface,"surface");
	  res5= TestInt(dernierePeriodeFacture,"dernierePeriodeFacture");
      verifSurface= verifier("Surface",surface);
      verfiNombreFaceAffiche = 0;
      if (document.getElementById("nombreFaceAffiche") != null )
      {
	    nombreFaceAffiche = document.forms[0].nombreFaceAffiche.value;
        verfiNombreFaceAffiche=verifier("Nombre de face /affiche",nombreFaceAffiche);
		verfiNombreFaceAffiche=TestInt(nombreFaceAffiche,"nombreFaceAffiche");
      }
      
	  verfiDateDebutAutorisation=0;
	  verfiDateFinAutorisation=0;	
	  verfiDateDernierControle=0;	 	  
	  verfiDateProchainControle=0;
 	  

      verfiDateDebutAutorisationB= verif_date(document.forms[0].dateDebutAutorisation.value);
       if (!verfiDateDebutAutorisationB)
       {
         alert("La date de debut d'autorisation doit etre de la forme JJ/MM/AAAA");
         verfiDateDebutAutorisation=1;
       }
	   
	   if(document.forms[0].dateFinAutorisation.value.length != 0 )
	   {
		   verfiDateFinAutorisationB= verif_date(document.forms[0].dateFinAutorisation.value);
		   if (!verfiDateFinAutorisationB  )
		   {
			 alert("La date de fin d'autorisation doit etre de la forme JJ/MM/AAAA");
			 verfiDateFinAutorisation=1;
		   }
		}
	    
	   if(document.forms[0].dateDernierControle.value.length != 0 )
	   {
		    verfiDateDernierControleB= verif_date(document.forms[0].dateDernierControle.value);
		   if (!verfiDateDernierControleB)
		   {
			 alert("La date du dernier controle doit etre de la forme JJ/MM/AAAA");
			 verfiDateDernierControle=1;
		   }		   	 	  
		}
		
		if(document.forms[0].dateProchainControle.value.length != 0 )
	    {		
		   verfiDateProchainControleB= verif_date(document.forms[0].dateProchainControle.value);
		   if (!verfiDateProchainControleB)
		   {
			 alert("La date du prochain controle doit etre de la forme JJ/MM/AAAA");
			 verfiDateProchainControle=1;
		   } 
		}

	    	
	 
	 if (res1==0 && res2==0&& res3==0 && res4==0 && verifSurface==0  
		   && verfiDateDebutAutorisation==0  && verfiDateFinAutorisation==0  && verfiDateDernierControle==0  && verfiDateProchainControle==0 
	       && verfiNombreFaceAffiche== 0 && res5==0  )
	 {
	  	  
		  /////////////////////////////////////////////////////////////////////////////////////////////
		  //Contrôle de la date du dernier contrôle si elle est saisie ,
		  //Si l’ouvrage doit être « A Facturer » « Contrôler présent » « Contrôler à facturer » alors le contrôle suivant doit être appliqué :	   
		  var verifDernierDateDeControle =0;	  
		  ville= document.forms[0].ville.value.toLowerCase();
		  necessiteControleTerrain= document.forms[0].necessiteControleTerrain.value.toLowerCase();
		  //alert("verif ok ");
		  
		  if(ville == "bordeaux" && necessiteControleTerrain == "oui" && document.forms[0].dateDernierControle.value.length != 0 )
		  {
			 var etatArticle =document.forms[0].etatArticle.value;		 
			 //Si l’ouvrage doit être « A Facturer » « Contrôler présent » « Contrôler à facturer » alors le contrôle suivant doit être appliqué :
			 if(etatArticle == "A Facturer" ||  etatArticle == "Contrôlé Présent" || etatArticle == "Contrôlé à Facturer" )
			 {
				//La date de dernier contrôle ne doit pas être inférieure à la date de mise en application complétée du nombre de périodes déjà facturées.
				//alert("controle de date de dernier controle");
				dateDebutAutorisation = document.forms[0].dateDebutAutorisation.value;
				dateDernierControle = document.forms[0].dateDernierControle.value;			
				dernierePeriodeFacture= document.forms[0].dernierePeriodeFacture.value;			
				var requestURL = "./verfierDates" ;			
				var periode=document.forms[0].dureeunitaire.value;				
				verifDernierDateDeControle = -1;
				if(dateDebutAutorisation.length != 0 && dernierePeriodeFacture.length != 0)
				{			    			
					$.ajax({
					type: "GET",   
					url: requestURL,   
					cache: false,
					data: ({
						  dateDernierControle: dateDernierControle,
						  dateDebutAutorisation: dateDebutAutorisation,		  
						  periode : periode,
						  nbreDePeriode : dernierePeriodeFacture
						}),
					dataType: "html",
					error:function(msg){
					 alert( "Error !: " + msg );
					},
					success:function(data){
					//affiche le contenu du fichier dans le conteneur dédié
					//alert(data);
					//La date de dernier contrôle ne doit pas être inférieure à la date de mise en application complétée du nombre de périodes déjà facturées.
					if(data == -1 )
					{
						alert("La date de dernier contrôle ne doit pas être inférieure à la date de mise en application complétée du nombre de périodes déjà facturées.");						
					}						
					else
					{	
						//alert("submit");
						document.forms[0].submit();
					}
				  }});
				}
			 }
			 else
			 {      	  
	           //alert("submit");
			   document.forms[0].submit();
			 }
			 
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		else
		{      	  
         //alert("submit");
		 document.forms[0].submit();
		}
    }     
 }


    function supprimer()
    {
      if (document.forms[0].peutEtreSuuprimer.value == "true")
	  {
		  if (confirm("Vous voulez vraiment supprimer cet ouvrage ?")) 
		  {
			document.forms[0].choix.value="supprimer";
			document.forms[0].submit();
		 }
	   }
	   else
	   {
	     alert("Impossible de supprimer cet ouvrage, il est déja facturé!");
	   }
    }
    
    function supprimerAlerte()
    {

     if (confirm("Vous voulez vraiment supprimer cette alerte ?")) 
      {
  	     document.forms[0].actionAlerte.value="supprimerAlerte";
  	     document.forms[0].action="gestionelementfacturation";     
         document.forms[0].submit();
      }
      
    }

  </script>
<div id="calcontainer"></div>





<jsp:getProperty name="baffElementFacturation" property="entete"/>

<form  method="POST" action="gestionelementfacturation">
<input type="hidden" name="peutEtreSuuprimer" value="<jsp:getProperty name="baffElementFacturation" property="peutEtreSuuprimer"/>">
<input type="hidden" name="peutEtreModifier" id="peutEtreModifier" value="<jsp:getProperty name="baffElementFacturation" property="peutEtreModifier"/>">
<input type="hidden" name="actionAlerte" >
<input type="hidden" name="source"  >
<input type="hidden" name="nomImageASupprimer"  >
<input type="hidden" name="nomImageAlerteASupprimer"  >
<input type="hidden" name="numeroImageEmplacement"  >
<input type="hidden" name="tailleTableauEmplacement"  >  
<input type="hidden" name="imageASupprimer"  >  
<input type="hidden" name="nombreImageAlerte" value="<jsp:getProperty name="baffElementFacturation" property="nombreImageAlerte"/>">
<input type="hidden" name="numeroImageAlerte" value="" >
<input type="hidden" name="tailleTableauAlerte"  >   
<input type="hidden" name="numEmplacment" value="<jsp:getProperty name="baffElementFacturation" property="numEmplacment"/>">
<input type="hidden" name="exercice" value="<jsp:getProperty name="baffElementFacturation" property="exercice"/>">
<input type="hidden" name="numRedevable" value="<jsp:getProperty name="baffElementFacturation" property="numRedevable"/>">
<input type="hidden" name="choix" value="<jsp:getProperty name="baffElementFacturation" property="choix"/>">
<input type="hidden" name="codeType" value="<jsp:getProperty name="baffElementFacturation" property="codeType"/>">
<input type="hidden" name="codeSecteur" value="<jsp:getProperty name="baffElementFacturation" property="codeSecteur"/>">
<input type="hidden" name="numeroElementFacturation" value="<jsp:getProperty name="baffElementFacturation" property="numElementFacturation"/>">
<input type="hidden" name="traitement" value="<jsp:getProperty name="baffElementFacturation" property="traitement"/>""/>
<input type="hidden" name="idarticle" value="<jsp:getProperty name="baffElementFacturation" property="idArticle"/>""/>
<input type="hidden" name="dureeunitaire" id="dureeunitaire">
<input type="hidden" name="necessiteControleTerrain" value="<jsp:getProperty name="baffElementFacturation" property="necessiteControleTerrain"/>">

<%
    if (request.getParameter("errortailleimage") != null && ! request.getParameter("errortailleimage").contains("ok") ) 
    {
%>
   <input type="hidden" id="errortailleimage" value="<%= request.getParameter("errortailleimage") %>">
   <script type="text/javascript">alert("La taille de l'image doit etre inferieur a "+document.getElementById("errortailleimage").value  +" koctet")</script>
<% 
    }
%>





<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="96" width="1117">
  <tr>
      <td width="1104" bgcolor="#AFF3BB" height="22">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>
  </tr>
  <tr>
  <td bgcolor="#ffffff" width="1113">
  <table border="1" width="100%">
    <tr>
      <td width="50%" bgcolor="#AFF3BB">
        <p align="center"><b><span style="font-size:12.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">
<a href="#moduleOuvrage">Information
        Ouvrage</a></span></b></td>
     


   
      
      <td width="50%" bgcolor="#AFF3BB">
        <p align="center"><b><span style="font-size:12.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">
<a href="#moduleAlerte">Alerte</a></span></b></td>

    </tr>
  </table>
  </td>
  </tr>



  
  <tr>
  <td bgcolor="#ffffff" width="1113">
  <p style="margin-left: 5px;">
  <b><font face="Arial" color="#000000" size="2">Exercice : </font>
  <font color="#cc3300" size="2"><jsp:getProperty name="baffElementFacturation" property="exercice"/></font>
  </b>
  </p>
  </td>
  </tr>
  
  <tr>
    <td bgcolor="#ffffff" height="20" width="1113">
      <p style="margin-left: 5px"><b><font face="Arial" color="#000000" size="2">Redevable :
      </font>
      <font color="#cc3300" face="Arial" size="2"> 
      <a href= "<jsp:getProperty name="baffElementFacturation" property="lienRedvable"/>" >
         <jsp:getProperty name="baffElementFacturation" property="nomPrenomRedevable"/>      
         </a>
 </font></b></p>
    </td> 
  </tr>
  
  <tr>
    <td bgcolor="#ffffff" height="20" width="1113">
      <p style="margin-left: 5px; margin-right: 5px;"><b><font face="Arial" color="#000000" size="2">Type d'emplacement :
      </font>
      <font color="#cc3300" face="Arial" size="2">
       <jsp:getProperty name="baffElementFacturation" property="libelleImputation"/>  
      </font></b></p>
    </td>
  </tr>
  <tr>
    <td bgcolor="#ffffff" height="20" width="1113">
      <p style="margin-left: 5px; margin-right: 5px;"><b><font face="Arial" color="#000000" size="2">Secteur
      :
      </font>
      <font color="#cc3300" face="Arial" size="2">
          <jsp:getProperty name="baffElementFacturation" property="codeSecteur"/>  
      </font></b></p>
    </td>
   
  </tr>

   <tr>
    <TD bgColor=#ffffff width="1113" height="20">
      <P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>Emplacement : </FONT>
      <FONT face=Arial color=#cc3300 size=2>         
     
        <a href=<jsp:getProperty name="baffElementFacturation" property="lienEmplacement"/>>
           <jsp:getProperty name="baffElementFacturation" property="raisonSocialEmplacement"/> :
           <jsp:getProperty name="baffElementFacturation" property="adresseComplete"/> 
           
         </a>  
         - 
         ( <jsp:getProperty name="baffElementFacturation" property="listeOuvrage"/> )     
      </FONT></B></P>
    </TD>
  </tr>


  <hr>


<%
	String ville = (String)session.getAttribute("ville"); 
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 	
	String typeUtilisateur = (String)session.getAttribute("typeUtilisateur");  
	//out.println("typeUtilisateur="+typeUtilisateur);
	//out.println("listeLibelleDesTypesDeTaxeAutorise="+listeLibelleDesTypesDeTaxeAutorise);
    Boolean peutAcceder = Boolean.valueOf(baffElementFacturation.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
	Boolean peutModiferDernierePeriode = false ;
	if( typeUtilisateur !=null &&  typeUtilisateur.equalsIgnoreCase("admin") && 
		listeLibelleDesTypesDeTaxeAutorise.contains(baffElementFacturation.getLibelleImputation()) )
		peutModiferDernierePeriode = true;
	//out.println("listeLibelleDesTypesDeTaxeAutorise="+listeLibelleDesTypesDeTaxeAutorise);	
	//out.println("typeTaxeCourant="+baffElementFacturation.getLibelleImputation());		
	//out.println("peutModiferDernierePeriode="+peutModiferDernierePeriode);	
	
%> 
<input type="hidden" id="peutModiferDernierePeriode" name="peutModiferDernierePeriode" value="<%= peutModiferDernierePeriode %>"/>
<input type="hidden" name="ville" value="<%= ville %>">  
  
<%if (baffElementFacturation.getTraitement().equalsIgnoreCase("article")) {%>


   <%if (true) {%>

  
   <A NAME="moduleOuvrage">   

  
  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="520" width="1119">
    <tr>
      <td width="1113" colspan="2" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/maj.jpg" width="40" height="40">&nbsp;</td>
    </tr>
    <tr>
      <td width="1113" colspan="2" bgcolor="#AFF3BB" height="22"><span style="text-transform: uppercase"><b><font size="4">MODIFICATION d'un OUVRAGE :</font></b></span></td>
    </tr>


<input type="hidden" name="longueur" size="15" value="<jsp:getProperty name="baffElementFacturation" property="longueurArticle"/>" >
<input type="hidden" name="largeur" size="15" value="<jsp:getProperty name="baffElementFacturation" property="largeurArticle"/>" >
<input type="hidden" name="surface" size="15" value="<jsp:getProperty name="baffElementFacturation" property="surface"/>" >
<input type="hidden" name="libelleImputation" value="<jsp:getProperty name="baffElementFacturation" property="libelleImputation"/>" >




    <tr>
      <td width="247" height="19"><b>&nbsp;Choisissez votre baréme <i><font color="#FF0000" size="2">*</font></i>:</b></td>
      <td width="866" height="19">       
          <jsp:getProperty name="baffElementFacturation" property="listeCodeBareme"/>
          <input type="hidden" name="baremeIndicatif" size="4" value="" onKeyup="majListeBareme();">      </td>
    </tr>
    
    
    
    <%if (baffElementFacturation.getLibelleImputation().equalsIgnoreCase("TLPE")) {%>   

    <tr>
      <td width="247" height="20"><font face="Arial" size="2">&nbsp;</font><b>Type
        <i><font color="#FF0000" size="2">*</font></i></b><font size="2">:</font>
      </td>      
      <td width="866" height="20">
      <jsp:getProperty name="baffElementFacturation" property="typeOuvrage"/>
      </td>      
    </tr>
    


    <tr>
      <td width="247" height="23"><font face="Arial" size="2">&nbsp;</font><b>Nombre
        d'affiche/face <i><font color="#FF0000" size="2">*</font></i></b><font size="2">:</font>
      </td>      
      <td width="866" height="23">
			<input type="text" id="nombreFaceAffiche" name="nombreFaceAffiche" size="15" value="<jsp:getProperty name="baffElementFacturation" property="nombreFaceAffiche"/>" >	
      </td>      
    </tr>
<%}%> 
    
    
<script>
remplirDureeUnitaire();
</script>
    
    
    
    
    
    
    
    
    
    
    
    
    <tr>
      <td width="247" height="23"><b>&nbsp;Nom article :</b></td>
      <td width="866" height="23">
          <font face="Arial" size="2">
          <input type="text" name="nomarticle" size="56" maxlength="30" 
		        value="<jsp:getProperty name="baffElementFacturation" property="nomArticle"/>">
		  </font>     </td>
    </tr>
     



  
 
    <tr id="trchoix">
      <td width="247" height="24"><b>&nbsp;</b><b>Choix</a><A NAME="moduleOuvrage"> </a><i><font color="#FF0000" size="2">*</font></i></b><A NAME="moduleOuvrage"><b>: </b></a>
      </td>      
      <td width="866" height="24"><font face="Arial" size="2">
        <div id="choixDimension" style="width: 791; height: 24">   </div></font>
      </td>      
    </tr>
   
 

  
 
    <tr>
      <td width="247" height="28"><b>&nbsp;Dimensions</b><A NAME="moduleOuvrage"><b> </b></a><b><i><font color="#FF0000" size="2">*</font></i></b><A NAME="moduleOuvrage"><b>: </b></a></td>
      <td width="866" height="28">
     <div id="dimensionArticle" style="width: 791; height: 28">   </div>      </td>
    </tr>
    <tr>
      <td width="247" height="23"><b>&nbsp;La quantité<font color="#FF0000" size="2">*</font></i>:</b></td>
      <td width="866" height="23">
      <font face="Arial" size="2">
      <input type="text" name="quantite" id="quantite" size="15" value="<jsp:getProperty name="baffElementFacturation" property="quantite"/>"></font>      </td>
    </tr>

  
    <tr>
      <td width="247" height="79"><b>&nbsp;Commentaire :</b></td>
      <td width="866" height="79">
          <font face="Arial" size="2"><b>                
    <textarea rows="3" name="commentaireOuvrage" cols="91"><jsp:getProperty name="baffElementFacturation" property="commentaireOuvrage"/></textarea></b></font>    </tr>
    <tr>
      <td width="247" height="33"><p><b>&nbsp;Autorisation&nbsp;</b><font color="#FF0000" face="Arial" size="2">(JJ/MM/AAAA)</font><font color="#000000" face="Arial" size="2"><b> </b>
        </font><b><i><font color="#FF0000" size="2"> </font></i>:</b> </p>      </td>
      <td width="866" height="33">
        <table width="866" border="0" height="14">
          <tr>
            <td width="397" height="8">Date de mise en application<b><i><font color="#FF0000" size="2">*</font></i></b><font face="Arial" size="2">:
              <input type="text"  
			  name="dateDebutAutorisation" readonly maxlength="10" id="dateDebutAutorisation" size="17"  
			  value="<jsp:getProperty name="baffElementFacturation" property="dateDebutAutorisation" />">
            </font> 
            
             <img id="calenderDateDebutAutorisation" onclick="displayCalendar(document.forms[0].dateDebutAutorisation,'dd/mm/yyyy',this)"
             src="./images/calendar.gif" border="0" width="20" height="18" /></td>
            
			<td width="444" height="8">Date de fin de validit&eacute; : <font face="Arial" size="2">
			  <input type="text" readonly name="dateFinAutorisation" maxlength="10" id="dateFinAutorisation" size="17" value="<jsp:getProperty name="baffElementFacturation" property="dateFinAutorisation"/>">
            </font> 
            
             <img onclick="displayCalendar(document.forms[0].dateFinAutorisation,'dd/mm/yyyy',this)"
             src="./images/calendar.gif" border="0" width="20" height="18" /></td>
          </tr>
      </table>      
	  </tr>
    <tr>
      <td width="247" height="32">&nbsp;<b>Controle terrain&nbsp;</b><font color="#FF0000" face="Arial" size="2">(JJ/MM/AAAA)</font><b><i><font color="#FF0000" size="2"> </font></i>:</b></td>
      <td width="866" height="32">
      <table border="0" width="100%">
        <tr>
          <td width="47%">Date du dernier contrôle<b> : </b>
            <input  maxlength="10" type="text" readonly name="dateDernierControle" id="dateDernierControle" size="20" 
			value="<jsp:getProperty name="baffElementFacturation" property="dateDernierControle" />">
       
		<img onclick="displayCalendar(document.forms[0].dateDernierControle,'dd/mm/yyyy',this)"
		src="./images/calendar.gif" border="0" width="20" height="18" />
            &nbsp;&nbsp;</td>
          <td width="53%">Date du prochain controle:
            <input maxlength="10" type="text"  readonly name="dateProchainControle" id="dateProchainControle" size="18" 
			value="<jsp:getProperty name="baffElementFacturation" property="dateProchainControle"/>">
        
        <img  onclick="displayCalendar(document.forms[0].dateProchainControle,'dd/mm/yyyy',this)"
        src="./images/calendar.gif" border="0" width="20" height="18" /></td>
        </tr>
      </table>      </td>
    </tr>
    
    <tr>
      <td width="247" height="31">&nbsp;<b>Information IDOSS :</b></td>
      <td width="866" height="31">
      <table border="0" width="100%">
        <tr>        
          <td width="53%">Date de fin d'autorisation :
            <input readonly maxlength="10" type="text" name="dateFinIdoss" id="dateFinIdoss" size="18" 
				value="<jsp:getProperty name="baffElementFacturation" property="dateFinIdoss"/>">        
      		</td>
        </tr>
      </table>     
      </td>
    </tr>
    
	
	
	
	
    <tr>
      <td width="247" height="24">&nbsp;<b>N° dérniere période facturé :
        </b>      </td>
      <td width="866" height="24"><font face="Arial" size="2">
        <input type="text" name="dernierePeriodeFacture" id="dernierePeriodeFacture" size="15" 
		value="<jsp:getProperty name="baffElementFacturation" property="dernierePeriodeFacture"/>">
      </font></td>
    </tr>

  <tr>
      <td width="247" height="19"><b>&nbsp;</b><A NAME="moduleOuvrage"><b>Etat de
        l'ouvrage :</b>
                  </a>      </td>
      <td width="866" height="19">
        <jsp:getProperty name="baffElementFacturation" property="etatArticle"/>      </td>
  </tr>

    <tr>
      <td width="247" height="23"><b><i><font color="#FF0000" size="2">(*)Champs
          obligatoires</font></i></b>
        &nbsp;      </td>
      <td width="866" height="23">
        <p align="center">&nbsp;

<%
    if (peutAcceder) { 
%>       
        
        
  <%if( baffElementFacturation.getChoix().equalsIgnoreCase("modifier") ) {%>
       <a href=javascript:modifierOuvrage();><img border="0" src="./images/modifier.gif" width="150" height="17"></a>
		<a href=javascript:supprimer();><img border="0" src="./images/supprimer.gif" width="150" height="17"></a>
    <%} else {%>
		<a href="javascript:creer();"><img border="0" src="./images/valider.gif" width="150" height="17"></a>
    <%}%>
        &nbsp;      </td>
    </tr>
<% } %>
    <tr>
        <td colspan="2" align="center" height="19" width="1113">
        <p align="left">        </td>
    </tr>
  </table>
  <center>
    <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="96" width="100%">
      <tr>
        <td width="1113" bgcolor="#AFF3BB" height="19" colspan="2">

  
   <p align="center">

  
   <A NAME="moduleOuvrage">   

  <img border="0" src="images/camera.png" width="40" height="40">

  
      </a>

  
        </td>
      </tr>

      <tr>
        <td width="1113" bgcolor="#AFF3BB" height="19" colspan="2">

  
   <A NAME="moduleOuvrage">   
  <b><font size="4"><span style="text-transform: uppercase">LISTE DES PHOTOS DE
  l'EMPLACEMENT :</span></font></b>
          </a>
        </td>
      </tr>

<%if( ! baffElementFacturation.getNombreImageEmplacement().equalsIgnoreCase("0")) {%>

      <tr>
        <td width="230" height="17">
        
        
        <table border="1" width="100%" height="478">
              <tr>
                <td align="center" colspan="2" height="19" bgcolor="#DFFFEF">
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
              création :</font></b> <b>&nbsp;</b></td>
              </tr>
              <tr>
                <td width="464%" align="center" colspan="2" height="19"><center><b><input type="text" name="dateCreationImageEmplacement" readonly size="25" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
                </td>
              </tr>
              <tr>
                <td width="464%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><font color="#000080"><b>N°
                  de photo</b></font> :</td>
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
              
              
<%   
    if (peutAcceder) { 
%>   
              
              <tr>              
                <td width="464%" align="center" colspan="2" height="19"><b>
                <a href="javascript:supprimerImageEmplacement();">
                <font color="#000080">Supprimer cette photo</font></a></b></td>
              </tr>
              
              
<% } %>              
              
              <tr>
      <b>

                <td width="464%" align="center" colspan="2" height="19">
      <b>

      <a href="javascript:actualiser();">Actualiser</a>
      </b>

      </td>
      </b>

              </tr>
              
              <tr>            
              
                <td width="464%" align="center" colspan="2" height="293">
                
                
                
                DIAPORAMA
                  
              
			 	
				
				<div class="highslide-gallery">
					<a class='highslide' id="thumb1" href='./diaporama/images/diaporama.jpg' 
						onclick="return diaporama1.expand(this, miniGalleryOptions1)">
						<img src='./diaporama/images/diaporama.jpg' alt=''/>			
					</a>
					
				<% 
				Vector imagesEmplacment = baffElementFacturation.getImagesEmplacement();
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
				
				
				
				
				
				
				
				
                
                
                
                
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p></td>
              </tr>
			  

          </table>

        
        
        
        </td>
        <td width="881" height="17">
          <p align="center"><img border="0" id="pic" src="" >
          </p>
        </td>
      </tr>
<%}else{%>
    </center>
      <tr>
        <td width="1111" height="17" colspan="2">
        <p align="center">               
        <b>Pas de photos disponibles pour cet emplacement</b>
        </td>
      </tr>


  <center>

<%}%>
</table>
    
<A NAME="moduleAlerte"> 
   
 <% if (baffListeAlertes.getAlerte().equalsIgnoreCase("true") )    
{%>    

  <table border="1" cellpadding="0" cellspacing="0" width="1139" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="1">
      <tr>
      <td width="1135" colspan="2" bgcolor="#AFF3BB" height="22">
        <p align="center"><img border="0" src="images/exclamation.jpg" width="40" height="40"></td>
      </tr>
      <tr>
      <td width="1135" colspan="2" bgcolor="#AFF3BB" height="22"><span style="text-transform: uppercase"><b><font size="4">LISTE
        DES ALERTES&nbsp; :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <jsp:getProperty name="baffListeAlertes" property="listeAlerteOuvrage"/>
        </font></b></span></td>
      </tr>
    </center>
      <tr>
      <td width="1135" colspan="2" bgcolor="#AFF3BB" height="22">
        <p align="left"><span style="text-transform: uppercase"><b><font size="4">INFORMATION
        ALERTE&nbsp; :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Alerte <jsp:getProperty name="baffListeAlertes" property="idAlerte"/>
        </font></b></span></p>
      </td>
      </tr>

    <center>

      <tr>
        <td width="227" height="23">
          <b>Numéro de l'alerte : </b>
        </td>
        <td width="906" height="23">
          &nbsp;<input readonly type="text" name="numeroAlerte" size="15" value="<jsp:getProperty name="baffListeAlertes" property="idAlerte"/>" >
        </td>
      </tr>
      <tr>
        <td width="227" height="23">
          <b>Date de création de l'alerte :</b>
        </td>
        <td width="906" height="23">
          &nbsp;<input readonly type="text" name="dateCreationAlerte" size="15"  value="<jsp:getProperty name="baffListeAlertes" property="dateAlerte"/>"  >
        </td>
      </tr>
      <tr>
        <td width="227" height="19">
          <b>Contrôleur : </b>
        </td>
        <td width="906" height="19">
            <b>
            
<% 
  
  if (   typeUtilisateur !=null &&  typeUtilisateur.equalsIgnoreCase("admin") )      	
{ 
%>
            <jsp:getProperty name="baffListeAlertes" property="controleurAlerte"/>
            
<% } else { %>            
            <jsp:getProperty name="baffListeAlertes" property="nomPrenomControleur"/>
<% } %>
               
            </b>
          </a>
        </td>
      </tr>
      <tr>
        <td width="227" height="46">
          <b>Texte de l'alerte :</b>
        </td>
        <td width="906" height="46">
          <textarea rows="3" name="textAlerte" cols="85" style="font-weight: bold"><jsp:getProperty name="baffListeAlertes" property="textAlerte"/></textarea>
        </td>
      </tr>
      <tr>
      <td width="1135" colspan="2" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Liste des
          photos - ALERTE :</span></font></b></td>
      </tr>
      
 <%if( ! baffElementFacturation.getNombreImageAlerte().equalsIgnoreCase("0")) {%>
    
      <tr>
        <td width="227" height="446">
          <p align="center">
          


 <table border="1" width="100%" height="478">
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF">
      <b>
      <font color="#000080" size="3">Nom de la photo</font></b><b><font color="#000080" size="3">
      :</font></b>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19">
                  <p align="center"><b><input type="text" name="nomImageAlerte" readonly size="26" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></p>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><font color="#000080"><b>Date de
                  création</b>
                  </font> :</td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19"><center><b><input type="text" name="dateCreationImageAlerte" readonly size="25" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><font color="#000080"><b>N°
                  de photo</b></font> :</td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="23"><center><b><input type="text" name="numeroImageAlerteCourante" readonly size="24" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
                </td>
              </tr>
              <tr>
                <td width="50%" align="center" height="19"><font color="#000000"><b>
                <a href="javascript:precedantImageAlerte()">&lt;&lt;Prec</a>
                </b></font></td>
                <td width="50%" align="center" height="19"><font color="#000000"><b>
                <a href="javascript:suivantImageAlerte()">Suiv&gt;&gt;</a>
                </b></font></td>
              </tr>
<% 
   if (peutAcceder) { 
%>             
              <tr>
                <td width="465%" align="center" colspan="2" height="19"><b>
                <a href="javascript:supprimerImageAlerte();">
                <font color="#000080">Supprimer photo alerte</font></a><font color="#000080">&nbsp;</font></b></td>
              </tr>
              
<% } %>              
              
              
              <tr>
                <td width="465%" align="center" colspan="2" height="293">&nbsp;
                  
				  
				  DIAPORAMA
                 
			     <div class="highslide-gallery">
			
					<a class='highslide' id="thumb2" href='./diaporama/images/diaporama.jpg' title=""
							onclick="return diaporama2.expand(this, miniGalleryOptions2)">
						<img src='./diaporama/images/diaporama.jpg' alt=''/></a>

						<div class="hidden-container">						
															
							<% 
							Vector imagesAlerte = baffListeAlertes.getImagesAlerte();
							for (int i = 0; i < imagesAlerte.size() ; i++) 
							{ 
								  String nomImage =(String)imagesAlerte.elementAt(i);
								  String lien = "./r2tData/photos/"+nomImage;
							%>	
								
								<a class='highslide' href="<%out.print(lien);%>" title="<%out.print(nomImage);%>"
								onclick="return diaporama2.expand(this, miniGalleryOptions2)">
								<img src="<%out.print(lien);%>" alt=''/></a>		
								
							<% } %>
						</div>
					</div>
				  
				  
				  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p></td>
              </tr>
             
          </table>














        </td>
        <td width="906" height="446">
          <p align="center"><img border="0" id="picAlerte" src="" width="822" height="488" >
        </td>
      </tr>
<%}else{%>
              <tr>
                <td width="1135" align="center" colspan="2" height="45">
                  <p>Pas de photo disponible pour cette alerte </p>
				</td>
              </tr>

<%}%>
      
      <tr>
        <td width="227" height="19">
          <b>&nbsp;Remarque :</b>
        </td>
    </center>
        <td width="906" height="46">          
          <textarea rows="2" name="remarqueAlerte" cols="82" style="font-weight: bold"><jsp:getProperty name="baffListeAlertes" property="remarque"/></textarea>
        </td>
      </tr>
  <center>
      <tr>
        <td width="227" height="19">
          &nbsp;<b>Etat de l'alerte :</b>
        </td>
    </center>
        <td width="906" height="19">
          <p align="left"><jsp:getProperty name="baffListeAlertes" property="etatAlerte"/>
        </td>
      </tr>
     <tr>
        <td width="227" height="19">
          <b>&nbsp;Date de fin de l'alerte :</b>
        </td>
        <td width="906" height="19">
          <p align="left">
          <input type="text" name="dateFinAlerte" id="dateFinAlerte" size="15" maxLength="10"  
          value="<jsp:getProperty name="baffListeAlertes" property="dateFinAlerte"/>"><b>
          <font size="2" face="Arial">
          
          <img onclick="displayCalendar(document.forms[0].dateFinAlerte,'dd/mm/yyyy',this)"
          src="./images/calendar.gif" border="0" width="16" height="16" />        		   
        		   
		
          </font></b>
        </td>
    </tr>
  <center>

  
<%   
     if (peutAcceder) { 
%>
	     <tr>
        <td width="1135" colspan="2" height="19">
          <p align="center"><a href="javascript:validerAlerte();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>&nbsp;&nbsp;
          <a href="javascript:supprimerAlerte();"><img border="0" src="./images/supprimer.gif" width="150" height="20"></a>&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
<% } %>   


    <tr>
        <td width="1135" colspan="2" height="1">
              <p align="center"><a href="./entree?action=liste_alertes.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">iste des alertes</font></b></font></a>

        
        </td>
      </tr>
  </table>
<% }
//FIN si il y a une alerte %>    
    
    <%} %>

<%} %>
    </center>


<p>

<%if( ! baffElementFacturation.getNombreImageEmplacement().equalsIgnoreCase("0")) {%>
   <script>  initImagesEmplacement(); </script>   
<% }%>

<% if (baffListeAlertes.getAlerteExiste().equalsIgnoreCase("true")) {%>    
       <script>initImagesAlerte(); </script>      
<% }%>

    
</form>





<% 
 if (peutAcceder) { 
%>

    <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="38" width="1141">

      <tr>
        <td width="1137" height="19" bgcolor="#AFF3BB">

  
      <b><font size="4"><span style="text-transform: uppercase">Ajouter des
      photos&nbsp;</span></font></b><A NAME="moduleOuvrage"><b><font size="4"><span style="text-transform: uppercase">:</span></font></b></a>
        </td>
      </tr>

      <tr>
        <td width="1137" height="19">  
   <b><font size="4"><span style="text-transform: uppercase">
   
   <form method="POST" enctype="multipart/form-data" >
	     <input type="file" name="myimage">
         <input type="button" type="button" value="Valider" onclick="javascript:uploadFichier();"/>
      </form>    
   
   </span></font></b>        </td>
      </tr>
</table>
<% } %>


 
 
 
<script>
initMajDimension();
</script>
<p>&nbsp;</p>
<table border="1" cellpadding="0" cellspacing="0" width="1140" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="1">
  <tr>
    <a NAME="moduleAlerte">
    <td width="1136" bgcolor="#AFF3BB" height="22">
      <p align="center"><img border="0" src="images/pleaide.jpg" width="40" height="40"></td>
    </a>
  </tr>
  <tr>
    <a NAME="moduleAlerte">
    <td width="1136" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">HiStorique
      </span></font></b><span style="text-transform: uppercase"><b><font size="4">:</font></b></span></td>
    </a>
  </tr>
  <tr>
    <td width="1134" height="22">
    <jsp:getProperty name="baffElementFacturation" property="historiqueEtat"/>
    </td>
  </tr>
</table>
<p align="center"><font face="Arial" size="3" color="#0000FF"><b><a href="entree?action=empl_resultat_recherche_redevable.jsp">Liste
des redevables</a></b></font></p>
<p>&nbsp;</p>
<p>&nbsp;</p>



<script>
chargerPeutEtreModifier();
reglerdimention();

</script>





</body>
