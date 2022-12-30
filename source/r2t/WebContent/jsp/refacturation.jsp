<html>
<head>
<title>R2T - REFACTURATION</title>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script>  
</head>

<body background="./images/nuages.jpg" topmargin="0">

<jsp:useBean id="beanRefacturation" scope="page" class="fr.analogon.r2t.batch.facture.BaffRefacturation" />
<jsp:setProperty name="beanRefacturation" property="request" value="<%=request%>"/>

<script>
var lesEmplacements=new Array;
var listeBareme=new Array;

      
function activerBoutonValider()
{
   		   contenuDiv ="";  
		   contenuDiv =contenuDiv +"<table border='1' width='100%'><tr>";
		   contenuDiv =contenuDiv +"<td width='50%' align='center'>&nbsp; <font face='Arial' size='3'><b><img border='0' src='images/pdfbleue.png' width='24' height='19'><a href='javascript:previsualiserFacture();'>Previsualiser ";
		   contenuDiv =contenuDiv +"la facture&nbsp; </a></b></font><div id ='SectionPreFacture' ></div></td>";
		   contenuDiv =contenuDiv +"<td width='50%' align='center'><font face='Arial' size='3'><b><img border='0' src='images/valider4sx.png' width='24' height='19'><a href='javascript:validerRefacurationFinale();'>Valider";
		   contenuDiv =contenuDiv +" la refacturation</a></b></font></td></tr></table>";
		   document.getElementById("validerRefacturation").innerHTML=contenuDiv;   
		   
}

function desactiverBoutonValider()
{
   			contenuDiv ="";  
		   document.getElementById("validerRefacturation").innerHTML=contenuDiv;   
		   
}





function controleChamps()
{
    var saisieOk = 0;
	
	//if (lesEmplacements.length==0)  saisieOk = -1;	
	//alert("Nombre d'emplacement="+lesEmplacements.length);
	for(var i=0; i<lesEmplacements.length; i++)
	{
	  //alert("Emplacement numero"+ i );	  
	  for(var j=0; j < lesEmplacements[i].listOuvrage.length; j++)
	  {	       
		   //alert("Emplacement"+ i + " - Ouvrage" + j );	
		   var dd= lesEmplacements[i].listOuvrage[j].dateDebut;			 
		   var df= lesEmplacements[i].listOuvrage[j].dateFin;		
		   
		   var quantite= lesEmplacements[i].listOuvrage[j].quantite;
		   var nomOuvrage= lesEmplacements[i].listOuvrage[j].nomOuvrage;
		   
		   var numeroDePeriode= lesEmplacements[i].listOuvrage[j].numeroDePeriode;	
		   var surface= lesEmplacements[i].listOuvrage[j].surface;			 
		   var longueur= lesEmplacements[i].listOuvrage[j].longueur;			 
		   var largeur= lesEmplacements[i].listOuvrage[j].largeur;	
		 
		   		   
		   var nombreFace= lesEmplacements[i].listOuvrage[j].nombreFace;			 
		   var typeOuvrage= lesEmplacements[i].listOuvrage[j].typeOuvrage;			 
		   //nombreFace et  
		   //alert("surface="+surface);		  		   
		   //alert("longueur="+longueur);		
		   //alert("largeur="+largeur);    
		   
		
         
       
		 
		   if(dd.length!=0)
		   {
		      retourVer1 = verif_date(dd);			  
			  //alert(retourVer1);
		      if (!retourVer1) 
			  {
			     alert("La date d’application de l’ouvrage N° "+ j  +" dans l'emplacement N° "+ i +" doit être de la forme JJ/MM/AAAA");
			     saisieOk = -1;
			  }
		   }
		   else
		   {
		      saisieOk = -1;
		      alert("La date de debut de l'ouvrage N° "+ j  +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		   
		   if(df.length!=0)
		   {
		      retourVer2 = verif_date(df);  
			  
		      if (!retourVer2) 
			  { 
			     alert("La date Date de fin de la période taxée de l’ouvrage N° "+ j  +" dans l'emplacement N° "+ i +" doit être de la forme JJ/MM/AAAA");
				 saisieOk = -1;
			  }
		   }
		   else
		   {
		      saisieOk = -1;
		      alert("La date de fin de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		   
		   if(quantite.length==0)
		   {		  
		      saisieOk = -1;
		      alert("Le champs quantité de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		   if(numeroDePeriode.length==0)
		   {		  
		      saisieOk = -1;
		      alert("Le champs numero de période de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		    if(surface.length==0)
		   {		  
		      saisieOk = -1;
		      alert("Le champs surface de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		    if(longueur.length==0)
		   {		  
		      saisieOk = -1;
		      alert("Le champs longueur de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		    if(largeur.length==0)
		   {		  
		      saisieOk = -1;
		      alert("Le champs largeur de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
		   }
		   
		   if ( document.forms[0].typeTaxe.value  == "TLPE"  )
		   {
			   if(nombreFace.length==0)
			   {		  
				  saisieOk = -1;
				  alert("Le champs nombreFace de l'ouvrage N° "+ j +" dans l'emplacement N° "+ i +" doit etre remplis!");
			   }
		   }
		   
		   	   		   
	  }	
	}
	return saisieOk;

}


function previsualiserFacture()
{
   
   //contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"400\" height=\"400\" \"></font> </p>";
   //document.getElementById("SectionPreFacture").innerHTML = contenu;
    var saisieOk = controleChamps();
	
	
	if (saisieOk == 0)
	{
		document.getElementById("SectionPreFacture").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";
		document.forms[0].etapeFacturation.value="pre-Refacturation";
		
	   var req="&"; 
	   for(var i=0; i<document.forms[0].elements.length; i++)
	   {
		  req=req+document.forms[0].elements[i].name+"=";
		  req=req+document.forms[0].elements[i].value+"&";
	   }  
	   //req =encodeURI(req);
	   var url = './getPrefacture?etapeFacturation=preRefacturation'+req;
	   if (window.ActiveXObject)
	   {
			httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	   else if (window.XMLHttpRequest)
	   {
			httpRequest = new XMLHttpRequest();
	   }
	   //alert("url="+url);
	   httpRequest.open("POST", url, true);        
	   httpRequest.onreadystatechange = function() {processRequestPrefacture(); } ;
	   httpRequest.send(null);
	}
	else
	{
	    alert("Veuillez saisire tous les champs nécessaires");
	}


   
}
function processRequestPrefacture()
{      
   if (httpRequest.readyState == 4)
   {
      if(httpRequest.status == 200)
      {
         var xmldoc = httpRequest.responseXML.documentElement; 
         var lienFacture= xmldoc.getElementsByTagName("lienFacture")[0].childNodes[0].nodeValue;
         //alert(lienFacture);         
         ContentFacture="<a href=" + lienFacture + " target=\"_blank\" > Lien nouvelle facture </a>";				  				    				   				 
         document.getElementById("SectionPreFacture").innerHTML = ContentFacture;
        
         
      }
      else
      {
         alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
      }
  }
} 


function validerRefacurationFinale()
{
  var saisieOk = controleChamps();
  if (saisieOk == 0) 
  { 
   document.forms[0].action='./gestionRefacturation?etapeFacturation=refacturation';  
   document.forms[0].etapeFacturation.value="refacturation";
   document.forms[0].submit(); 
   }
   	else
	{
	    alert("Veuillez saisire tous les champs nécessaires");
	}
}






function getProfile(numFacture,choixOuvrage)
{      

 var url = './getInfosFacture?author='+numFacture+"&typeFacture=annulee";

        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestFacture(choixOuvrage); } ;
        httpRequest.send(null);        
}
   
function processRequestFacture(choixOuvrage)
{    
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {               
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("facture");
              //alert (node.length);           
              var existe= xmldoc.getElementsByTagName("existe")[0].childNodes[0].nodeValue;
              if (existe =="true")           
              {                 
                 var typeTaxe= xmldoc.getElementsByTagName("typeTaxe")[0].childNodes[0].nodeValue;            
                 var numClient= xmldoc.getElementsByTagName("numClient")[0].childNodes[0].nodeValue;
                 var nomClient= xmldoc.getElementsByTagName("nomClient")[0].childNodes[0].nodeValue;
                 var lienFacture= xmldoc.getElementsByTagName("lienFacture")[0].childNodes[0].nodeValue;
                 var typeTaxe= xmldoc.getElementsByTagName("typeTaxe")[0].childNodes[0].nodeValue;
                                      
                 ContentFacture="<a href=" + lienFacture + " target=\"_blank\" > Lien Facture </a>";				  				    				   				 
                 document.getElementById("SectionFacture").innerHTML = ContentFacture;           
                 document.forms[0].numRedevable.value=numClient;
                 document.forms[0].nomRedevable.value=nomClient; 
                 document.forms[0].typeTaxe.value=typeTaxe; 
                 modifierTypeTaxe();
                 
                 document.forms[0].numeroFactureValide.value="true";              
              }
              else
              { 
                 document.forms[0].numRedevable.value="";
                 document.forms[0].nomRedevable.value="";
                 document.getElementById("SectionFacture").innerHTML = "";  
                 document.forms[0].numeroFactureValide.value="false";                                                            
  				 	alert( "Cette facture n'existe pas");
  				 					 
              }              
              
            }
            else
            {
                alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
            }
        }
}   



function rechercheVoie(nomRueRecherche)
{
       
        if(nomRueRecherche.length > 4)
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
          alert("Entrez au moins 5 caracteres pour rechercher des rues")
        }
        
}

function rechercheVoieParCodeVoie(codeVoixRecherche)
{
        //alert(codeVoixRedevable);        
        var url = './rechercheRue?codeVoixRecherche='+codeVoixRecherche;
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
              //alert(node.length) 	;
              if (node.length==0)           
              {
                 alert ("Code Voie non valide !!");
                 document.getElementById("listeRue").innerHTML = "";
                 document.forms[0].adressRedevable.value="";
              }
              else
              {
                 document.forms[0].codeVoixValide.value="true";
                 var code= xmldoc.getElementsByTagName("code")[0].childNodes[0].nodeValue;                  
                 var adresse= xmldoc.getElementsByTagName("adresse")[0].childNodes[0].nodeValue;                                
  				 	//alert(code+" ---- "+adresse); 
				 	document.forms[0].codeVoixRedevable.value=code;
					document.forms[0].adressRedevable.value=adresse;
					document.getElementById("listeRue").innerHTML = ""; 
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
              //alert(node.length) 	;		
              
              var contenuListRue="<select name='listDesRues' onClick=majChampAdresse(listDesRues.options[this.selectedIndex].text)>";

                		
				  for (i = 0; i < node.length; i++)
				  {
				            
                  var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;                  
                  
                  var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue;                  
					   var quartier= xmldoc.getElementsByTagName("quartier")[i].childNodes[0].nodeValue;                  
                  var element= code+"-----"+adresse+"-----"+quartier;
                  //alert(element);
                  contenuListRue=contenuListRue+"<option value="+element+">"+element+"</option>";
                  //alert(contenuListRue);
                  //alert("ELEMEENT"+this.options[i].value);
               } 
            
               if (node.length==0)           
              {
                 contenuListRue=contenuListRue+"<option>    PAS DE RESULTATS    </option>";
                 document.forms[0].adressRedevable.value="";
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


function choixDimension1(indexOuvrage)
{
   //alert("choix lingeur et maj");
   
   nomDimensionArticle="dimensionArticle"+indexOuvrage; 
   nomSurface="surface"+indexOuvrage;    
   nomLargeur="largeur"+indexOuvrage;     
   nombreFace= "nombreFace"+indexOuvrage;    	
   typeOuvrage= "typeOuvrage"+indexOuvrage;    			 
   nomLongueur="longueur"+indexOuvrage;
 
   contenuDiv= "<b>Longueur: </b><input type='text' name='longueurTmp"+indexOuvrage+"' onclick= majLongeur('"+indexOuvrage+"')  onchange= majLongeur('"+indexOuvrage+"')>";                   
   contenuDiv= contenuDiv+"<b>     Largeur:</b> <input type='text' name='largeurTmp"+indexOuvrage+"' onclick= majLargeur('"+indexOuvrage+"')  onchange= majLargeur('"+indexOuvrage+"') >";   
   
   document.getElementsByName(nomSurface)[0].value="0";
   	document.getElementsByName(nomLargeur)[0].value="";
	document.getElementsByName(nomLongueur)[0].value="";
   document.getElementById(nomDimensionArticle).innerHTML = contenuDiv; 
    
     nomTypeDimmension="typeDimmension"+indexOuvrage; 
    var reg=new RegExp("_");
    var tableau=indexOuvrage.split(reg);
    var numEmplacement= tableau[0];
    var numOuvrage= tableau[1];        
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeDimmension="longLarg" ;
    majInfosOuvrage(indexOuvrage);
  
}

function choixDimension2(indexOuvrage)
{
   nomDimensionArticle="dimensionArticle"+indexOuvrage; 
   nomSurface="surface"+indexOuvrage;    
   nomLargeur="largeur"+indexOuvrage;    
   nomLongueur="longueur"+indexOuvrage; 
   nomNombreFace="nombreFace"+indexOuvrage;
   nomTypeOuvrage="typeOuvrage"+indexOuvrage;
   
   contenuDiv= "<b>Surface: </b><input type='text' name='surfaceTmp"+indexOuvrage+"' onclick= majSurface('"+indexOuvrage+"')  onchange= majSurface('"+indexOuvrage+"')>";                   
   
   document.getElementsByName(nomSurface)[0].value="";
   
   if ( document.forms[0].typeTaxe.value  == "TLPE"  )
   {     
     document.getElementsByName(nomNombreFace)[0].value="";
     document.getElementsByName(nomTypeOuvrage)[0].value="";
   }
   
   document.getElementsByName(nomLargeur)[0].value=1;
	document.getElementsByName(nomLongueur)[0].value=1;
   document.getElementById(nomDimensionArticle).innerHTML = contenuDiv; 
    nomTypeDimmension="typeDimmension"+indexOuvrage; 
    var reg=new RegExp("_");
    var tableau=indexOuvrage.split(reg);
    var numEmplacement= tableau[0];
    var numOuvrage= tableau[1];        
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeDimmension="surface" ;
	majInfosOuvrage(indexOuvrage);
}

function majLongeur(indexOuvrage)
{
   nomLongueur="longueur"+indexOuvrage; 
   nomLongueurTmp="longueurTmp"+indexOuvrage;   
   valeur=	document.getElementsByName(nomLongueurTmp)[0].value;
	document.getElementsByName(nomLongueur)[0].value=valeur;  
 	majInfosOuvrage(indexOuvrage);
}

function majLargeur(indexOuvrage)
{
   //alert("maj largeur");
   nomLargeur="largeur"+indexOuvrage; 
   nomLargeurTmp="largeurTmp"+indexOuvrage;   
   valeur=	document.getElementsByName(nomLargeurTmp)[0].value;
	document.getElementsByName(nomLargeur)[0].value=valeur;  
 	majInfosOuvrage(indexOuvrage);
}

function majSurface(indexOuvrage)
{
   nomSurface="surface"+indexOuvrage; 
   nomSurfaceTmp="surfaceTmp"+indexOuvrage;   
   valeur=	document.getElementsByName(nomSurfaceTmp)[0].value;
	document.getElementsByName(nomSurface)[0].value=valeur;  
 	majInfosOuvrage(indexOuvrage);
}


function majNombreFace(indexOuvrage)
{
   nomNombreFace="nombreFace"+indexOuvrage; 
   nomNombreFaceTmp="nombreFaceTmp"+indexOuvrage;   
   valeur=	document.getElementsByName(nomNombreFaceTmp)[0].value;
   document.getElementsByName(nomNombreFace)[0].value=valeur;  
   majInfosOuvrage(indexOuvrage);
}



function majTypeOuvrage(indexOuvrage)
{
   nomTypeOuvrage="typeOuvrage"+indexOuvrage; 
   nomTypeOuvrageTmp="typeOuvrageTmp"+indexOuvrage;   
   valeur=	document.getElementsByName(nomTypeOuvrageTmp)[0].value;
   document.getElementsByName(nomTypeOuvrage)[0].value=valeur;  
   majInfosOuvrage(indexOuvrage);
}






function validerFacture()
{  
   document.forms[0].action="./gestionRefacturation?typeAction=validerFacture";
   document.forms[0].submit();   
}

function modiferBareme(indexOuvrage)
{  
   //majInfosOuvrage(indexOuvrage);
   //alert(indexOuvrage);   
   nomTypeBareme="typeBareme"+indexOuvrage;    
   nomSurface="surface"+indexOuvrage;    
   
   nomLargeur="largeur"+indexOuvrage;    
	nomLongueur="longueur"+indexOuvrage;    
	nomChoixDimension="choixDimension"+indexOuvrage;    
	nomDimensionArticle="dimensionArticle"+indexOuvrage; 
	nomNombreFace="nombreFace"+indexOuvrage;
	nomTypeOuvrage="typeOuvrage"+indexOuvrage;
	
    var valBareme=document.getElementsByName(nomTypeBareme)[0].value;	
    //alert(valBareme);
	
	if(valBareme.indexOf("FORFAIT")>0 || valBareme.indexOf("UNITE/")>0)
	{	 	 
	   document.getElementById(nomChoixDimension).innerHTML = "-"; 
	   document.getElementById(nomDimensionArticle).innerHTML = "-"; 
	   document.getElementsByName(nomSurface)[0].value=0;
   	   document.getElementsByName(nomLargeur)[0].value=1;
	   document.getElementsByName(nomLongueur)[0].value=1;
	   if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	   {
	     document.getElementsByName(nomNombreFace)[0].value=1;
	      document.getElementsByName(nomTypeOuvrage)[0].value="Normal";
	   }


	}
	if(valBareme.indexOf("ML/")>0)
	{
	   document.getElementById(nomChoixDimension).innerHTML = "-"; 
	   contenuDiv="<b>Longueur: </b><input type='text' name='longueurTmp"+indexOuvrage+"'  onclick= majLongeur('"+indexOuvrage+"')  onchange= majLongeur('"+indexOuvrage+"')> ";	   
      document.getElementById(nomDimensionArticle).innerHTML = contenuDiv; 
      document.getElementsByName(nomSurface)[0].value="0";
   	   document.getElementsByName(nomLargeur)[0].value="1";
	   document.getElementsByName(nomLongueur)[0].value="";
	     if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	   {
	     document.getElementsByName(nomNombreFace)[0].value=1;
	      document.getElementsByName(nomTypeOuvrage)[0].value="Normal";
	   }
	  
	}	   
	
	if(valBareme.indexOf("M2/")>0)
	{
	   contenuDiv="<input type='radio'  value='lonlarg' checked name='longSuraface"+indexOuvrage+"'  onClick=choixDimension1('"+indexOuvrage+"')>";
      contenuDiv=contenuDiv+'Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface';
	   contenuDiv=contenuDiv+"<input type='radio' value='surf' name='longSuraface"+indexOuvrage+"'  onClick=choixDimension2('"+indexOuvrage+"')>";
       document.getElementById(nomChoixDimension).innerHTML = contenuDiv;    			
      document.getElementsByName(nomSurface)[0].value="0";
	   if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	   {
	     document.getElementsByName(nomNombreFace)[0].value=1;
	      document.getElementsByName(nomTypeOuvrage)[0].value="Normal";
	   }
       choixDimension1(indexOuvrage);
	}
	//alert('majInfosOuvrage');
    majInfosOuvrage(indexOuvrage);
}

function modifierTypeTaxe()
{
  var typeTaxe= document.forms[0].typeTaxe[document.forms[0].typeTaxe.selectedIndex].text; 
  
	    //if (typeTaxe == "TLPE")
	    //{ 
	    //    alert("TLPE affichage de l'annneee");		  
	    //	  document.getElementById("divAnneeFacturationTLPE").style.visibility='visible';		  
	    //}
	    //else
	    //{
	    //  alert("masquer l'annneee");		  
	    // document.getElementById("divAnneeFacturationTLPE").style.visibility='hidden';		
	    //}
  
	//alert(typeTaxe);
  ret=true;
  if(lesEmplacements.length !=0)
  {
    ret=confirm("Ce changement va supprimer tous les emplacements et les ouvrages créés, voulez vous continuer ?");
  }
  if (ret ==true)
  {    
    lesEmplacements=new Array;
    listeBareme=new Array; 
    document.getElementById("contenu").innerHTML="";
    document.getElementById("infos").innerHTML="";
    rechercheListBareme(typeTaxe,document.forms[0].anneeExercice.value); 
	document.getElementById("validerRefacturation").innerHTML="";        
  }
  
}



function rechercheListBareme(typeTaxe,anneeExercice)
{        
   var url = './rechercheBareme?typeTaxe='+typeTaxe+"&anneeExercice="+anneeExercice;

	if (window.ActiveXObject)
   {
      httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
   }
   else if (window.XMLHttpRequest)
   {
      httpRequest = new XMLHttpRequest();
   }
      httpRequest.open("GET", url, true);        
      httpRequest.onreadystatechange = function() {processRequestRechercheListBareme(); } ;
      httpRequest.send(null);  
}

function getListBareme(indexOuvrage)
{
  //alert(indexOuvrage);
  resultat="<select name='typeBareme"+ indexOuvrage +"' onchange=javascript:modiferBareme('"+indexOuvrage+"')>";

  for (i = 0; i < listeBareme.length; i++)
  {  
     var valeurTmp =  listeBareme[i].replace(/'/g, " ");	 
	 resultat=resultat+"<option value='"+valeurTmp+"'>"+listeBareme[i]+"</option>";     
	 //alert(resultat);
  }
  nomTypeTaxe= "typeBareme"+ indexOuvrage;  
  resultat=resultat+"</select>";
  return(resultat);
}



function processRequestRechercheListBareme()
{      
  if (httpRequest.readyState == 4)
  {
     if(httpRequest.status == 200)
     {
         var xmldoc = httpRequest.responseXML.documentElement;
         var node = xmldoc.getElementsByTagName("ligne");
         //alert("node.length="+node.length);
		  for (i = 0; i < node.length; i++)
		  {
            var ligne= xmldoc.getElementsByTagName("ligne")[i].childNodes[0].nodeValue;                  
            listeBareme[i]=ligne;            
			//alert(ligne);
         } 
         if (node.length==0)           
         {
            alert("Pas de bareme disponible pour l'annnée  " + document.forms[0].anneeExercice.value);             
            document.getElementById("validerRefacturation").innerHTML="";
         }
         else
         {
            //Cas ou il y a des baremes 
            //var contenuDiv = "<p align=center> <img border='0' src='./images/valider4sx.png' width='24' height='19'>";
            //contenuDiv =contenuDiv +"<a href='javascript:validerFacture();'>Valider la Facture</a></b></font></p>";
            

         }        
     }
     else
     {
         alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
     }             
  }
} 





function Ouvrage(contenu) 
{
   this.typeBareme="";
   this.choix="";
   this.surface="";
   this.largeur="";
   this.longueur="";
   this.quantite="";	
   this.nomOuvrage="";
   
   this.numeroDePeriode="";	
   this.dateDebut="";	
   this.dateFin="";
   this.typeOuvrage="";
   this.nombreFace="";
   this.typeDimmension="";
   this.contenu=contenu;
   
    
}





////////////////////////////////////////////////A COMMETER /////////////////////////////////
/////////////////////typeOuvrage et nombreFace








function afficherInfosOuvrage(indexOuvrage)
{    
    var reg=new RegExp("_");
    var tableau=indexOuvrage.split(reg);
    var numEmplacement= tableau[0];
    var numOuvrage= tableau[1];        
    nomTypeBareme="typeBareme"+numEmplacement+"_"+numOuvrage;

    nomSurface="surface"+numEmplacement+"_"+numOuvrage;    
    nomLargeur="largeur"+numEmplacement+"_"+numOuvrage; 
    nomLongueur="longueur"+numEmplacement+"_"+numOuvrage; 
    typeOuvrage="typeOuvrage"+numEmplacement+"_"+numOuvrage; 
    nombreFace="nombreFace"+numEmplacement+"_"+numOuvrage; 
    
    nomQuantite="quantite"+numEmplacement+"_"+numOuvrage;    
	nomNomOuvrage="nomOuvrage"+numEmplacement+"_"+numOuvrage; 
	
    nomNumPeriode="numPeriode"+numEmplacement+"_"+numOuvrage;    
    nomDateDebut="dateDebut"+numEmplacement+"_"+numOuvrage;    
    nomDateFin="dateFin"+numEmplacement+"_"+numOuvrage;    
    
    //alert("Remplissage des cellules ");
    document.getElementsByName(nomTypeBareme)[0].value =lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeBareme ;

    document.getElementsByName(nomSurface)[0].value =lesEmplacements[numEmplacement].listOuvrage[numOuvrage].surface ;
    document.getElementsByName(nomLargeur)[0].value =lesEmplacements[numEmplacement].listOuvrage[numOuvrage].largeur ;
    document.getElementsByName(nomLongueur)[0].value =lesEmplacements[numEmplacement].listOuvrage[numOuvrage].longueur ;


    document.getElementsByName(nomQuantite)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].quantite ;
	document.getElementsByName(nomNomOuvrage)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nomOuvrage ;
	document.getElementsByName(nomNumPeriode)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].numeroDePeriode ;
    document.getElementsByName(nomDateDebut)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].dateDebut ;
    document.getElementsByName(nomDateFin)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].dateFin ;
    
	
	if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	{
	   document.getElementsByName(typeOuvrage)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeOuvrage ;
	   document.getElementsByName(nombreFace)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nombreFace ;
	   nomTypeOuvrage="typeOuvrage"+indexOuvrage; 
	   nomNombreFace="nombreFace"+indexOuvrage; 
	}
	
    nomTypeBareme="typeBareme"+indexOuvrage;
	nomChoixDimension="choixDimension"+indexOuvrage;    
	nomDimensionArticle="dimensionArticle"+indexOuvrage; 
	
    var valBareme=document.getElementsByName(nomTypeBareme)[0].value;	
   
	//alert(nomTypeOuvrage);
	//alert(nomNombreFace);
	if(valBareme.indexOf("FORFAIT")>0 || valBareme.indexOf("UNITE/")>0)
	{	 	 
	   document.getElementById(nomChoixDimension).innerHTML = "-"; 
	   document.getElementById(nomDimensionArticle).innerHTML = "-";	
	   //alert("test");
	   //alert("TEST"+document.getElementById(nomDimensionArticle).innerHTML);
	   if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	   {
	      document.getElementById(nomTypeOuvrage).innerHTML = "typeOuvrage";	
	      document.getElementById(nomNombreFace).innerHTML = "-";	
	   }
	   
	}
	if(valBareme.indexOf("ML/")>0)
	{
	   document.getElementById(nomChoixDimension).innerHTML = "-"; 
	   contenuDiv="<b>Longueur: </b><input type='text' name='longueurTmp"+indexOuvrage+"'  onclick= majLongeur('"+indexOuvrage+"')  onchange= majLongeur('"+indexOuvrage+"')> ";	   
      document.getElementById(nomDimensionArticle).innerHTML = contenuDiv;  
      nomLongueurTmp="longueurTmp"+indexOuvrage;  
      document.getElementsByName(nomLongueurTmp)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].longueur ;	  
	}	   
	
	if(valBareme.indexOf("M2/")>0)
	{   
       //Recuperation du type (Surface ou longEtLargeur)
       nomLongueurTmp="longueurTmp"+indexOuvrage;  
       nomLargeurTmp="largeurTmp"+indexOuvrage;  
       nomSurfaceTmp="surfaceTmp"+indexOuvrage;  
       if(lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeDimmension=='longLarg')
       { 
         //CAS 1
        	contenuDiv="<input type='radio'  value='lonlarg' checked name='longSuraface'  onClick=choixDimension1('"+indexOuvrage+"')>";
          contenuDiv=contenuDiv+'Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface';
	      contenuDiv=contenuDiv+"<input type='radio' value='surf' name='longSuraface'  onClick=choixDimension2('"+indexOuvrage+"')>";
         document.getElementById(nomChoixDimension).innerHTML = contenuDiv;    			       
        
         contenuDiv= "<b>Longueur: </b><input type='text' name='longueurTmp"+indexOuvrage+"' onclick= majLongeur('"+indexOuvrage+"')  onchange= majLongeur('"+indexOuvrage+"')>";                   
	      contenuDiv= contenuDiv+"<b>     Largeur:</b> <input type='text' name='largeurTmp"+indexOuvrage+"' onclick= majLargeur('"+indexOuvrage+"')  onchange= majLargeur('"+indexOuvrage+"') >";   
              
         //alert("AFTER BEGIN");         
         nomDimensionArticle="dimensionArticle"+indexOuvrage; 
         //alert("NEXT BEGIN");         
         //alert("nomDimensionArticle="+nomDimensionArticle);
         //alert("contenuDiv="+contenuDiv);
		  document.getElementById(nomDimensionArticle).innerHTML = contenuDiv; 
		  document.getElementsByName(nomLongueurTmp)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].longueur ;	  
         document.getElementsByName(nomLargeurTmp)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].largeur ;	  
       }
       else if(lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeDimmension=='surface')
       { 
         //CAS 2
        contenuDiv="<input type='radio'  value='lonlarg'  name='longSuraface'  onClick=choixDimension1('"+indexOuvrage+"')>";
          contenuDiv=contenuDiv+'Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface';
	      contenuDiv=contenuDiv+"<input type='radio' checked value='surf' name='longSuraface'  onClick=choixDimension2('"+indexOuvrage+"')>";
         document.getElementById(nomChoixDimension).innerHTML = contenuDiv;    			       
        
         contenuDiv= "<b>Surface: </b><input type='text' name='surfaceTmp"+indexOuvrage+"' onclick= majSurface('"+indexOuvrage+"')  onchange= majSurface('"+indexOuvrage+"')>";                   

         //alert("AFTER BEGIN");         
         nomDimensionArticle="dimensionArticle"+indexOuvrage; 
         //alert("NEXT BEGIN");         
         //alert("nomDimensionArticle="+nomDimensionArticle);
         //alert("contenuDiv="+contenuDiv);
		  document.getElementById(nomDimensionArticle).innerHTML = contenuDiv; 
		  document.getElementsByName(nomSurfaceTmp)[0].value=lesEmplacements[numEmplacement].listOuvrage[numOuvrage].surface ;	  
       }

       
	}
    //alert(lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeDimmension);    
    
}


function majInfosOuvrage(indexOuvrage)
{    
    //alert("maj infos dans tableau");
    var reg=new RegExp("_");
    var tableau=indexOuvrage.split(reg);
    var numEmplacement= tableau[0];
    var numOuvrage= tableau[1];        

    nomTypeBareme="typeBareme"+numEmplacement+"_"+numOuvrage;    
    nomSurface="surface"+numEmplacement+"_"+numOuvrage;    
    nomLargeur="largeur"+numEmplacement+"_"+numOuvrage; 
    nomLongueur="longueur"+numEmplacement+"_"+numOuvrage; 

    nomQuantite="quantite"+numEmplacement+"_"+numOuvrage;    
	nomNomOuvrage="nomOuvrage"+numEmplacement+"_"+numOuvrage;  
	
	 nomNumPeriode="numPeriode"+numEmplacement+"_"+numOuvrage;    
	 nomDateDebut="dateDebut"+numEmplacement+"_"+numOuvrage;
 	 nomDateFin="dateFin"+numEmplacement+"_"+numOuvrage;
	 nomTypeOuvrage="typeOuvrage"+numEmplacement+"_"+numOuvrage;    
	 nomNombreFace="nombreFace"+numEmplacement+"_"+numOuvrage;    

	 //Remplissage des tableaux
    //alert("Remplissage des tableaux");
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeBareme=document.getElementsByName(nomTypeBareme)[0].value ;
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].surface=document.getElementsByName(nomSurface)[0].value ;
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].largeur=document.getElementsByName(nomLargeur)[0].value ;
	lesEmplacements[numEmplacement].listOuvrage[numOuvrage].longueur=document.getElementsByName(nomLongueur)[0].value ;
    
	lesEmplacements[numEmplacement].listOuvrage[numOuvrage].quantite=document.getElementsByName(nomQuantite)[0].value ;
	lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nomOuvrage=document.getElementsByName(nomNomOuvrage)[0].value ;
	
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].numeroDePeriode=document.getElementsByName(nomNumPeriode)[0].value ;
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].dateDebut=document.getElementsByName(nomDateDebut)[0].value ;
    lesEmplacements[numEmplacement].listOuvrage[numOuvrage].dateFin=document.getElementsByName(nomDateFin)[0].value ;
    
	if ( document.forms[0].typeTaxe.value  == "TLPE"  )
	{
	  //alert("TLPE--OK");
	  lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeOuvrage=document.getElementsByName(nomTypeOuvrage)[0].value ;
	  lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nombreFace=document.getElementsByName(nomNombreFace)[0].value ;
	
	  //alert("Type ouvrage"+lesEmplacements[numEmplacement].listOuvrage[numOuvrage].typeOuvrage);
	  //alert("Nombre de face"+ lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nombreFace);
	}
	//alert("nomTypeBareme="+lesEmplacements[numEmplacement].listOuvrage[numOuvrage].nomTypeBareme);
    document.getElementById("SectionPreFacture").innerHTML="";
}

function Emplacement(numRue,complNumRue,adresse) 
{
  this.idEmplacement="";
  this.numRue=numRue;
  this.complNumRue=complNumRue;
  this.adresse=adresse;  
  this.contenu="";
  var listOuvrage=new Array;
  this.listOuvrage=listOuvrage;

}

function supprimerTousLesEmplacements()
{

  ret=confirm("Ce changement va supprimer tous les emplacements et les ouvrages créés, voulez vous continuer ?");
  if (ret ==true)
  {
   desactiverBoutonValider();
   lesEmplacements=new Array;
   document.getElementById("contenu").innerHTML="";
   document.getElementById("infos").innerHTML="";
    //rechercheListBareme(typeTaxe,document.forms[0].anneeExercice.value);         
  }
}



function supprimerEmplacement(idEmplacement)
{
   
   //alert(lesEmplacements.length);
   idEmplacement=''+idEmplacement;
   lesEmplacements.splice(idEmplacement,1);
   majAffichageListEmplacement();
   //alert(lesEmplacements.length);
   
}

function majAffichageListEmplacement()
{
   //alert("majAffichageListEmplacement");
   var contenuFinale="";
   for(var i=0; i<lesEmplacements.length; i++)
	{
	    i=''+i;
	    contenuFinale= contenuFinale+lesEmplacements[i].contenu; 
   } 	     
   
   document.getElementById("contenu").innerHTML = contenuFinale;	   
   document.getElementById("infos").innerHTML = "<input type='hidden' name='nombreEmplacment' value="+lesEmplacements.length+"><BR> Nombre d'emplacement= "+lesEmplacements.length;
  
  for(var i=0; i<lesEmplacements.length; i++)
	{
	   majAffichageListOuvrage(i); 
   }
}

function majAffichageListOuvrage(idEmplacement)
{  
  var contenuFinale="";
  //alert("majAffichageListOuvrage");
  for(var i=0; i<lesEmplacements[idEmplacement].listOuvrage.length; i++)
  {
	 //alert(lesEmplacements[idEmplacement].listOuvrage[i].contenu);
	 contenuFinale= contenuFinale +lesEmplacements[idEmplacement].listOuvrage[i].contenu;
  }
  var nomContenu="contenu"+idEmplacement;  
  document.getElementById(nomContenu).innerHTML = contenuFinale;    
  
  //Mise a jour du contenu des cellules 
  for(var i=0; i<lesEmplacements[idEmplacement].listOuvrage.length; i++)
  {
  	 var indexOuvrage=idEmplacement+"_"+i;
	 //alert(indexOuvrage);
	 afficherInfosOuvrage(indexOuvrage);	 
  }  
}

  


function ajouterEmplacement(numRue,complNumRue,codeAdresse)
{

     
      //alert(numRue);
      //alert(codeAdresse);
      
   verfiNumRedevable= verifier("Numero redevable",document.forms[0].numRedevable.value);   
   verfiNumRue= verifier("Numero Voie",document.forms[0].numRue.value);
   verfiCodeVoie= verifier("Code Voie",document.forms[0].codeVoie.value);
   verfiAdresseEmplacement= verifier("Adresse emplacement",document.forms[0].adresseEmplacement.value);   
   adresse2Emplacement= document.forms[0].adresse2Emplacement.value ;   
   adresse3Emplacement= document.forms[0].adresse3Emplacement.value ;   
   
   var pos = document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(document.forms[0].typeTaxe.value);
	if( pos !=-1 )
	{

    if(verfiNumRedevable==0 && verfiNumRue ==0 && verfiAdresseEmplacement ==0  && verfiCodeVoie==0  )
	 {
      activerBoutonValider();
      var contenu="";     
      //var index = document.forms[0].index.value;
  	   //document.forms[0].index.value=parseInt(document.forms[0].index.value)+1;
	   index = lesEmplacements.length;
      contenu= contenu+"<table border=1 width=100%>"; 
      //alert("INDEX="+index);
      contenu=contenu+"<tr>";
   	   contenu=contenu+"<td width=\"18%\"><font color='#FF0000'><b><p align=center>" + numRue+ " " +complNumRue+ " " +document.forms[0].adresseEmplacement.value+ " "+document.forms[0].adresse2Emplacement.value+ " " +document.forms[0].adresse3Emplacement.value+ "</p></b></font>" ;
  	   contenu=contenu+"<input type='hidden' value='*newEmplacement*' name='newEmplacement'>" ;
   	   contenu=contenu+"<input type='hidden' value="+index+" name=idEmplacement_"+index+">" ;
  	   contenu=contenu+"<input type='hidden' value="+numRue+" name=numRue_"+index+">" ;
  	   contenu=contenu+"<input type='hidden' value="+complNumRue+" name=complNumRue_"+index+">" ;
	    contenu=contenu+"<input type='hidden' value=\"" + adresse2Emplacement+ "\"" + "name=adresse2Emplacement_"+index+">" ;
		contenu=contenu+"<input type='hidden' value=\"" + adresse3Emplacement+ "\"" + "name=adresse3Emplacement_"+index+">" ;
		 
  	   contenu=contenu+"<input type='hidden' value='"+codeAdresse+"' name=codeAdresse_"+index+">" ;
      //contenu=contenu+"<a href=javascript:supprimerEmplacement("+index +")><p align=center>Suuprimer cet Emplacement</p></a>";
   	   contenu=contenu+"<a href=javascript:ajouterOuvrage("+index +")><p align=center>Ajouter Ouvrage</p></a></td>";
	   contenu=contenu+"<td width=82%><div id=contenu"+lesEmplacements.length+"></div></td>";
	   contenu=contenu+"</tr>";
	   contenu=contenu+"<tr>";
	   contenu=contenu+"<td width=\"100%\" colspan=\"2\" bgcolor=\"#FFFFD9\">&nbsp;</td>";
	   contenu=contenu+"</tr>";    
	   contenu=contenu+"</table>";  
      
      var newEmplacement=new Emplacement(numRue,complNumRue,document.forms[0].adresseEmplacement.value,document.forms[0].adresse2Emplacement.value,document.forms[0].adresse3Emplacement.value);	   
      newEmplacement.contenu=contenu;
      newEmplacement.idEmplacement=index;
      lesEmplacements[lesEmplacements.length]=newEmplacement;  	         	   
      document.getElementById("contenu").innerHTML=contenu;
	   majAffichageListEmplacement();
    }
   
   }
   else
   {
     alert("Vous n'avez pas les droits pour faire une refacturation de type "+ document.forms[0].typeTaxe.value);
   }
  	 
  
}

function ajouterOuvrage(idEmplacement)
{
  //alert("ajouter ouvrage");
  var leContenu="";
  var indexOuvrage=''+idEmplacement+'_'+lesEmplacements[idEmplacement].listOuvrage.length;  
  //alert("indexOuvrage="+indexOuvrage);  
  leContenu=leContenu+"<table border='1' width='100%' height='221'>";
  leContenu=leContenu+"<tr>";
 

  leContenu=leContenu+"<td width='23%' height='23'><b><font color='#0000FF'>Ouvrage Numéro : </font></b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+indexOuvrage;
  leContenu=leContenu+"<input type='hidden' value='*newOuvrage*' name='separateurOuvrage'>" ;

  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='19'><b>TypeBareme <font color='#FF0000'>*</font>:</b></td>";
  leContenu=leContenu+"<td width='77%' height='19'>"+getListBareme(indexOuvrage)+"</td>";
  leContenu=leContenu+"</tr>";
  
       
  
	  
	  
	  leContenu=leContenu+"<tr>";
	  leContenu=leContenu+"<td width='23%' height='23'><b>Nom ouvrage <font color='#FF0000'></font>:";
	  leContenu=leContenu+"</b></td>";
	  leContenu=leContenu+"<td width='77%' height='23'>";
	  leContenu=leContenu+"<input type='text'  onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
	  leContenu=leContenu+"name='nomOuvrage"+indexOuvrage+"' size='33'/>";
	  leContenu=leContenu+"</td>";
	  leContenu=leContenu+"</tr>";
	  leContenu=leContenu+"<tr>";
	  
	if ( document.forms[0].typeTaxe.value  == "TLPE"  )
  {
    
	  leContenu=leContenu+"<tr>";
	  leContenu=leContenu+"<td width='23%' height='23'><b>Type ouvrage<font color='#FF0000'>*</font>:";
	  leContenu=leContenu+"</b></td>";
	  leContenu=leContenu+"<td width='77%' height='23'>";
	  leContenu=leContenu+"<select name='typeOuvrage"+indexOuvrage+"'  id='typeOuvrage"+indexOuvrage+"'  onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') id='typeOuvrage' name='typeOuvrage' ><option value='Normal' Slected >Normal</option><option value='Numerique'>Numérique</option></select>";
	  leContenu=leContenu+"</td>";
	  leContenu=leContenu+"</tr>";  
	  
	  leContenu=leContenu+"<tr>";
	  leContenu=leContenu+"<td width='23%' height='23'><b>Nombre d'affiche/face<font color='#FF0000'>*</font>:";
	  leContenu=leContenu+"</b></td>";
	  leContenu=leContenu+"<td width='77%' height='23'>";
	  leContenu=leContenu+"<input maxlength='10' type='text' onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
	  leContenu=leContenu+"name='nombreFace"+indexOuvrage+"'  id='nombreFace"+indexOuvrage+"' size='33'/>";
	  leContenu=leContenu+"</td>";
	  leContenu=leContenu+"</tr>";
	}   
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Choix <font color='#FF0000'>*</font>:</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<div id=choixDimension"+indexOuvrage+"> </div>";
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  
 
  
  
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Dimension <font color='#FF0000'>*</font>:</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<div id=dimensionArticle"+indexOuvrage+">";
  leContenu=leContenu+"</div>";
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Quantité <font color='#FF0000'>*</font>:";
  leContenu=leContenu+"</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<input type='text'  onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
  leContenu=leContenu+"name='quantite"+indexOuvrage+"' size='33'/>";
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  leContenu=leContenu+"<tr>";
  
  leContenu=leContenu+"<td width='23%' height='23'><b>Numero de periode <font color='#FF0000'>*</font>:";
  leContenu=leContenu+"</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<input  type='text' onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
  leContenu=leContenu+"name='numPeriode"+indexOuvrage+"' size='33'/>";
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Date d’application de l’ouvrage <font color='#FF0000'>*</font>:";
  leContenu=leContenu+"</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<input maxlength='10' type='text' onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
  leContenu=leContenu+"name='dateDebut"+indexOuvrage+"'  id='dateDebut"+indexOuvrage+"' size='33'/>";
  leContenu=leContenu+"<img onclick=displayCalendar(document.forms[0].dateDebut"+indexOuvrage+",'dd/mm/yyyy',this)"; 
  leContenu=leContenu+" src='./images/calendar.gif' border=0 width=16 height=16>"; 
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>"; 
  
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Date de fin de la période taxée <font color='#FF0000'>*</font>:";
  leContenu=leContenu+"</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<input  maxlength='10' type='text' onchange=majInfosOuvrage('"+indexOuvrage+"') onclick=majInfosOuvrage('"+indexOuvrage+"') ";
  leContenu=leContenu+"name='dateFin"+indexOuvrage+"' id='dateFin"+indexOuvrage+"' size='33'/>";
  leContenu=leContenu+"<img onclick=displayCalendar(document.forms[0].dateFin"+indexOuvrage+",'dd/mm/yyyy',this)"; 
  leContenu=leContenu+" src='./images/calendar.gif' border=0 width=16 height=16>"; 
  
  
  
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
    leContenu=leContenu+"<input name='longueur"+indexOuvrage+"' type='hidden'>";
  leContenu=leContenu+"<input name='largeur"+indexOuvrage+"' type='hidden'>";
  leContenu=leContenu+"<input name='surface"+indexOuvrage+"' type='hidden'>";  

  //alert(document.forms[0].typeTaxe.value);
		   
  
  
  

  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='100%' colspan='2' bgcolor='#EAEAEA' height='20'>&nbsp;</td>";
  leContenu=leContenu+"</tr>";
  

 
  
  
  
  
  leContenu=leContenu+"</table>";

  var newOuvrage=new Ouvrage(leContenu);
  newOuvrage.contenu= leContenu;
  lesEmplacements[idEmplacement].listOuvrage[lesEmplacements[idEmplacement].listOuvrage.length]=newOuvrage;
  
  majAffichageListOuvrage(idEmplacement);
  modiferBareme(indexOuvrage);
       	   
}




function modifierAdresseRedevable()
{   
   //verfiPourcentage= verifier("Pourcentage",document.forms[0].pourcentage.value); 
	var numRedevableAChangerAdresse=document.forms[0].numRedevableAChangerAdresse.value;
	var numVoieRedevable=document.forms[0].numVoieRedevable.value;
	var adressRedevable=document.forms[0].adressRedevable.value;
	var codePostaleRedevable=document.forms[0].codePostaleRedevable.value;
   var villeeRedevable=document.forms[0].villeeRedevable.value;   
  
   verifNumRedevableAChangerAdresse=verifier("Numero de redevable",numRedevableAChangerAdresse);
   verifNumVoieRedevable=verifier("Numero de Voie",numVoieRedevable);
   verifAdressRedevable=verifier("Adress du redevable",adressRedevable);
   verifCodePostaleRedevable=verifier("Code Postale",codePostaleRedevable);
   verifVilleeRedevable=verifier("villee",villeeRedevable);

  if(verifNumRedevableAChangerAdresse ==0 && verifNumVoieRedevable==0 &&
    verifAdressRedevable==0 &&  verifCodePostaleRedevable==0 &&   verifVilleeRedevable==0 )
  {
       
   		ret=confirm("Etes-vous sûr de vouloir changer l'adresse du redevable numero "+numRedevableAChangerAdresse);
	   if (ret == true )
   		{
      		document.forms[0].action="gestionChangementAdresseRedevable";
     		document.forms[0].submit();	       
   		}
  }
}


function modifierCedexRedevable()
  { 
  	
  	if(document.forms[0].cedexRedevableCk.checked==true )
  	{	  	
	  	document.forms[0].cedexRedevable.value="true";
	}
  	else
  	{
	  	document.forms[0].cedexRedevable.value="false";
  	}
  }


function majChampAdresse(adresse)
{  
  var reg=new RegExp("-----");
  var tableau=adresse.split(reg);
  document.forms[0].codeVoie.value=tableau[0];
  document.forms[0].adresseEmplacement.value=tableau[1];
}



function majLienRedevable(adresse)
{
	//alert(adresse);
	var reg=new RegExp("---");
	var tableau=adresse.split(reg);
	var nomPrenomRedevable= tableau[0];
	var numRedevable= tableau[1];
	//alert ("nomPrenomRedevable="+nomPrenomRedevable);
   //alert ("numRedevable="+numRedevable);
   	//alert("here");

	var adresseRedevable="" ;
	for(i=1; i<tableau.length ;i++ )   adresseRedevable=adresseRedevable+ tableau[i] ;

	res= adresse.indexOf("PAS", 0);

	if(res !=0)
	{
	   
	   	var tableau2=nomPrenomRedevable.split(".");
		document.forms[0].nomRedevable.value = tableau2[1];
		var link ="./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role";
		link=link +"&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable="+numRedevable;
		contenuLienRedevable="<font face='Arial' color='#0000FF' size='2' >";
		contenuLienRedevable = contenuLienRedevable +" Redevable : <a target='blanck' href="+ link+" >";
		contenuLienRedevable = contenuLienRedevable + " Lien vers le redevable "  + nomPrenomRedevable ;
		contenuLienRedevable = contenuLienRedevable + " numero : "+ numRedevable +"      </a></font></b>";
		document.getElementById("lienRedevable").innerHTML = contenuLienRedevable;
		document.forms[0].numRedevable.value=numRedevable;		
		
	}
	else
	{
		document.getElementById("lienRedevable").innerHTML = "";
		document.forms[0].numNewRedevable.value="";		
	}

	if (document.forms[0].numRedevable.value == "" )
	{
 		document.getElementById("transfert").innerHTML = "";
	}

}



function rechercheRedevable(numRedevable,nomRedevable,adresseRedevable)
{
        //alert();
				
        document.getElementById("lienRedevable").innerHTML = "";
      
        var url = './rechercheRedevable?numRedevable='+numRedevable+"&nomRedevable="+nomRedevable+"&adresseRedevable="+adresseRedevable;    
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
			  //alert(node.length);
              var contenuListRedevable="<b><font face='Arial' size='2'>Redevable:  </font></b>";
               //alert(node.length);
			  if (node.length!=0)           
              {
                contenuListRedevable=" "+contenuListRedevable+"<select name='listDesRedevable' onclick='majLienRedevable(document.forms[0].listDesRedevable.options[document.forms[0].listDesRedevable.selectedIndex].value)' >";
               
				for (i = 0; i < node.length; i++)
				   {
				      var nomPrenomRedevable= xmldoc.getElementsByTagName("nomPrenomRedevable")[i].childNodes[0].nodeValue;   
					  
					  var raisonSocialeRedevable= xmldoc.getElementsByTagName("raisonSocialeRedevable")[i].childNodes[0].nodeValue;   
				      var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue; 
                   var numRedevable= xmldoc.getElementsByTagName("numRedevable")[i].childNodes[0].nodeValue; 
                    element= raisonSocialeRedevable + "."+nomPrenomRedevable+"---"+numRedevable+"---"+adresse;
                   contenuListRedevable=contenuListRedevable+"<option value=\""+element+"\">"+element+"</option>";
                }
               contenuListRedevable=contenuListRedevable+"</select>";             	                           
              }
              else
              {
                 contenuListRedevable="PAS DE RESULTATS"; 
                 document.getElementById("lienRedevable").innerHTML = "";
                
              }

               
               document.getElementById("listeRedevable").innerHTML = contenuListRedevable;  
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 



    
 
    

    function majAnneeDestination()
    {
      var anneeOrigine=Math.abs(document.forms[0].anneeOrigineBasculeEF.value);
      var anneeDestination=anneeOrigine+1;
      document.forms[0].anneeDestinationBasculeEF.value=anneeDestination;
    }

 </script>

<form name ="refacturation">

<input type="hidden" name="etapeFacturation" >
<input type="hidden" name="numeroFactureValide" >
<%
  String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");     
%>
<input type="hidden" name="listeLibelleDesTypesDeTaxeAutorise" value="<% out.println(listeLibelleDesTypesDeTaxeAutorise); %>" >


<div align="center" style="width: 1133; height: 488">
         
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="43">
        <tr>




 

          <td width="200%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="images/pdf.jpg" width="40" height="40"></p>
          </td>
 
      
      

        </tr>




<a NAME="transfertEmplacment">


          <td width="200%" bgcolor="#AFF3BB" height="17">
              <font size="3" face="Arial" color="#000000"><b>REFACTURATION&nbsp;</b></font><font color="#000000"><b><font size="3" face="Arial">:&nbsp;</font></b></font>
          </td>
 
      
      

      
      

        <tr>






          <td width="200%" height="1">
           <table border="1" width="1129" height="490">
</a>
           <tr>
                  <td width="1119" height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Information
                    sur le redevable et sur la période :</b></font></td>
           </tr>
           <tr>
          <td background="./images/fond_bleu.gif" width="235" height="23">
            <b>
            &nbsp;Année <font color="#FF0000">*</font> </b><b> :</b>
          </td>
          <td width="878" height="23">
            <input type="text" name="anneeExercice" size="15" onchange=modifierTypeTaxe(); value="2009" >
          </td>
           </tr>
           <tr>
          <td background="./images/fond_bleu.gif" width="235" height="19">
            <b>&nbsp;Type de taxe </b><b><font color="#FF0000">*</font></b><b>:</b>
          </td>
          <td width="878" height="19">
           <jsp:getProperty name="beanRefacturation"  property="typeTaxe" />
          
          </td>
           </tr>
           
          <!--
		  <tr id="divAnneeFacturationTLPE">
          <td background="./images/fond_bleu.gif" width="235" height="23">
            <b>
            &nbsp;Année facturation TLPE<font color="#FF0000">*</font> : </b>
          </td>
          <td width="878" height="23">          
             <select name="anneeFacturationTLPE" id="anneeFacturationTLPE" size="1">
               <option value="2010" selected >2010</option>
               <option value="2009">2009</option>
				</select>
          </td>
           </tr>
		   -->
           
           
           
           <tr>
      <td background="./images/fond_bleu.gif" width="235" height="19">
        <b><font face="Arial" size="2">&nbsp;</font>Numéro de la facture
        annulée</b> <b>:</b>
      </td>
      <td width="878" height="24">
        <input type="text" name="idFactue" size="29">&nbsp; <img border="0" src="./images/ok.gif" width="24" ONclick=getProfile(document.forms[0].idFactue.value,"true")  height="18">
        
        
         </td>
           </tr>
           <tr>
      <td background="./images/fond_bleu.gif" width="235" height="19">
        <b><font face="Arial" size="2">&nbsp;</font>Lien vers la facture :</b>
      </td>
      <td width="878" height="19">
       <div id="SectionFacture" style="width: 802; height: 25"></div>
              </td>
           </tr>
            <tr>




        <td width="235" background="./images/fond_bleu.gif" height="143">
          <b>&nbsp;Redevable </b><b><font color="#FF0000">*</font></b><b>:</b></td>
        <td width="878" height="143">
          <table border="1" width="100%">
            <tr>










<td width="23%">
          <b>Numéro du redevable</b><a NAME="transfertEmplacment"><font color="#FF0000">*</font></a><b> :</b></td>
<td width="77%">
<input  type="text"  name="numRedevable" size="33"/></td>
 
      
      
 
      
      

            </tr>
            <tr>




 

<td width="23%"><b>Nom du redevable :</b></td>
<td width="77%"><input  type="text"  name="nomRedevable" size="33"/>
</td>
         </tr>
            <tr>

<td width="23%"><b>Adresse :</b></td>
<td width="77%">
<input  type="text"  name="adresseRedevable" size="33"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp; <font face="Arial" size="3"><a href="javascript:rechercheRedevable(document.forms[0].numRedevable.value,document.forms[0].nomRedevable.value,document.forms[0].adresseRedevable.value )" ><img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0"><b>echercher Redevable&nbsp;</b></a></font> 
 
      
      

</td>
 
      
      

            </tr>
            <tr>




 

<td width="100%"  colspan="2">

<div id="listeRedevable" style="width: 807; height: 34">   </div>
</td>
 
            </tr>
            <tr>




 

<td width="100%"  colspan="2">

<div id="lienRedevable" style="width: 787; height: 31">   </div>

</td>
 
            </tr>
          </table>
        </td>


 
      
      

            </tr>
           <tr>
                  <td width="1119" height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>&nbsp;Ajouter
                    des emplacements et des ouvrages :</b></font></td>
           </tr>
           <tr>




<a NAME="transfertEmplacment">


        <td width="235" background="./images/fond_bleu.gif" height="158">
          &nbsp;
          <p><b><font face="Arial" size="2">&nbsp;</font>Emplacement</b>
          <font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b>&nbsp;</p>
          <p align="center"></a>&nbsp;</p>
          </td>
        <td width="878" height="158">
          <table border="1" width="100%" height="173">
            <tr>




 

<td width="23%" height="19">
          <b>Numéro de voie </b><a NAME="transfertEmplacment"><font color="#FF0000">*</font></a><b>:</b></td>
			<td width="77%" height="19">
			  <input type="text" name ="numRue" >
      		</td>     

            </tr>
            <tr>




 

<td width="23%" height="19">
          <b>Compl:</b></td>
			<td width="77%" height="19">
			    <jsp:getProperty name="beanRefacturation"  property="complNumRue" /> 
      		</td>     

            </tr>
           


 
<input type="hidden"  name="codeVoie" size="33"/>


            <tr>






<td width="23%" height="23"><b>Adresse&nbsp; </b><a NAME="transfertEmplacment"><font color="#FF0000">*</font></a><b>:</b></td>
<td width="77%" height="23">
<input  type="text"  name="adresseEmplacement" size="33"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp; <font face="Arial" size="3"><a href="javascript:rechercheVoie(document.forms[0].adresseEmplacement.value)"><img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0"><b>echercher Adresse </b></a></font> 
 
      
      

</td>
 
      
      

            </tr>
            <tr>






<td width="23%" height="23"><b>compl. Adresse :&nbsp;</b></td>
<td width="77%" height="23">
<input  type="text"  name="adresse2Emplacement" size="33"/> 
 
      
      

</td>
 
      
      

            </tr>


 <tr>






<td width="23%" height="23"><b>compl. Adresse :</b></td>
<td width="77%" height="23">
<input  type="text"  name="adresse3Emplacement" size="33"/> 
 
      
      

</td>
 
      
      

            </tr>
            <tr>




 

<td width="100%"  colspan="2" height="20">

 <div id="listeRue" style="width: 550; height: 20">
</td>
 
            </tr>
            <tr>




 

<td width="100%"  colspan="2" height="35">

<table border="1" width="100%">
  <tr>
    <td width="50%" align="center"><font face="Arial" size="3"><b><img border="0" src="images/ajouter.jpg" width="24" height="19"><a href="javascript:ajouterEmplacement(document.forms[0].numRue.value,document.forms[0].complNumRue.value,document.forms[0].codeVoie.value);">Ajouter
      un emplacement&nbsp;</a></b></font></td>
    <td width="50%" align="center"><font face="Arial" size="3"><b><img border="0" src="./images/erreur.jpg" width="24" height="19"></b></font><a href="javascript:supprimerTousLesEmplacements();"><font face="Arial" size="3"><b>Supprimer tous les emplacements</b></font></a><font face="Arial" size="3"><b>&nbsp;</b></font></td>
  </tr>
</table>

</td>
 
            </tr>
          </table>
        </td>
           </tr>
<tr>
                  <td width="1119" height="19" bgcolor="#FFFFE8" colspan="2">
                  <font size="3" face="Arial" color="#000000"><b>
                  &nbsp;Liste des emplacements et des ouvrages à facturer :</b></font></td>
</tr>
           <tr>
                  <td width="1119" height="38" colspan="2">
                     <div id="contenu"></div>
<div id="infos"></div>
                    </td>
           </tr>
<tr>
                  <td width="1119" height="19"  colspan="2" bgcolor="#FFFFE8"><font size="3" face="Arial" color="#000000"><b>Action
                    :</b></font></td>
</tr>
<tr>
                  <td width="1119" height="17"  colspan="2"><font size="3" face="Arial" color="#000000"><b>
                  
                  
                  <div id ="validerRefacturation">
                
                  </div>
                  
                  
                  </b></font></td>
</tr>
<tr>
                  <td width="1119" height="53"  colspan="2">&nbsp;
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                    <p></td>
</tr>

           </table>
               
         </td>
         </tr>


        <tr>
         <td width="200%" height="1">               
        


         </td> 
        </tr>
      </table>       
  </div>
<script> 
   modifierTypeTaxe(); 
 </script>

</form>
</body>
</html>
























































































































