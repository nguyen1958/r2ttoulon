<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
  <%@ page language = "java" %>
  
  <jsp:useBean id="beanPayement" scope="page" class="fr.analogon.r2t.view.regie.BaffPayement"/>
  <jsp:setProperty name="beanPayement" property="request" value="<%=request%>"/> 
  
  <jsp:useBean id="beanRole" scope="page" class="fr.analogon.r2t.view.role.BAffRedevable"/>
  <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
	<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
  <script type="text/javascript" src="./resources/js/controle.js"></script>  
  <jsp:setProperty name="beanRole" property="request" value="<%=request%>"/> 
  <style>
  	.hidden {
  		visibility: hidden;
  	}
  </style> 
</head>

<body background="./images/nuages.jpg" topmargin="0" leftmargin="0">

<script>
/*
 * paul 21/12/2021
 * Forcer le rechargement de la page lorsqu'on clique sur "back button" du navigateur
 */
onload=function(){	
	var e=document.getElementById("refreshed");
	//alert("Refresh=>"+e.value);
	if(e.value=="no")e.value="yes";
	else{e.value="no";location.reload();}
	//alert("Refresh after=>"+e.value);
}


var  verfierMontantAPayer=0;
var  verfierNumeroQuittance=0;
var  verfierNumeroCheque=0;
var  verfierNumeroTransaction=0;

//Paul convertir les caractères entrés du clavier en majuscule
function caps(element){
	element.value = element.value.toUpperCase();
}

function chargerRaisonSocial()
{
	//alert("Appel ajax chargement de raison social");

	var njr = document.getElementById( "natureJuridiqueRedevable" ).value ;
	var raisonSocialeRedevableok = document.getElementById( "raisonSocialeRedevableok" ).value ;
	
    //alert(njr);
	var url = "./chargerRaisonSocialRedevable?njr="+njr+"&raisonSocialeRedevableok="+raisonSocialeRedevableok;
	if (window.ActiveXObject)
	{
	httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest)
	{
	httpRequest = new XMLHttpRequest();
	}
	  httpRequest.open("GET", url, true);
	  httpRequest.onreadystatechange = function() {processRequestChargerRaisonSocialRedevable(); } ;
	  httpRequest.send(null);
	
}




function processRequestChargerRaisonSocialRedevable()
{
	if (httpRequest.readyState == 4)
	{
		//alert("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ|"+httpRequest.status);
		
		if(httpRequest.status == 200)
		{
			var contenursr = httpRequest.responseText;
			document.getElementById("raisonSocialeRedevable").innerHTML=contenursr;
		}
	}
	else
	{
		//alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
	}
}




function verifierPrenom()	
{
	var res = "0";
	var njr = document.getElementById( "natureJuridiqueRedevable" ).value ;
	//alert ( njr);
	if (njr.indexOf("11")==0)
	{
		prenomRedevable= document.getElementById("prenomRedevable").value ;
		//alert(prenomRedevable);
		if (prenomRedevable.length==0) 
		{
			verifPrenomRedevable= verifier("Prenom redevable",document.forms[0].prenomRedevable.value);
			res = "-1";
		}
	}	
	//alert(res);
	return res;
}



	

function verfierUnicite()	
{
	//alert("verfierUnicite ///////");
	siret=document.forms[0].siret.value;
	siren=document.forms[0].siren.value;
	numRedevable=document.forms[0].numRedevable.value;
	var url = "./verifierUnciteSiretSiren?siret="+siret+"&siren="+siren+"&numRedevable="+numRedevable ;
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

function processRequestUnicite()
{

	if (httpRequest.readyState == 4)	{
		if(httpRequest.status == 200)
		{
		    
			var xmldoc = httpRequest.responseXML.documentElement;
			var node = xmldoc.getElementsByTagName("disponible");
			var disponible ;
			for (i = 0; i < node.length; i++)
			{
				disponible= xmldoc.getElementsByTagName("disponible")[i].childNodes[0].nodeValue;				
			}
			if ( disponible == "ok")
			{
				document.forms[0].verifSiretSiren.value = 0 ;				
			}
			else if ( disponible == "ko")
			{
				//alert("Le numero de SIRET et le numero de SIREN doivent doit etres uniques");
				document.forms[0].verifSiretSiren.value = -1 ;
			}
			else
			{
				alert("erreur");
				document.forms[0].verifSiretSiren.value = -1 ;
			}
		}
		else
		{
			alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
		}
	}
}


function decim2(varia)
{ 
  var decim2=Math.round(varia*100)/100 ;
  return decim2;
}

//----------------------------------------------
// Affichage d'un Objet déclaré dans le document...
//----------------------------------------------
function Affiche_OBJ(){
var Arg = arguments; // Récup liste des arguments passée à la fonction
var Obj;
var Obj;
for( var i=0; i< Arg.length; i++){ // On parcours la liste
Obj = document.getElementById( Arg[i]); // Récup Objet correspondant
if( Obj){
Obj.style.visibility = "visible";
//-- Ou autre méthode
Obj.style.display = "";
}
}
}
//----------------------------------------------
// Masquage d'un Objet déclaré dans le document...
//----------------------------------------------
function Masque_OBJ(){
var Arg = arguments; // Récup liste des arguments passée à la fonction
var Obj;
for( var i=0; i< Arg.length; i++){ // On parcours la liste
Obj = document.getElementById( Arg[i]); // Récup Objet correspondant
if( Obj){
Obj.style.visibility = "hidden";
//-- Ou autre méthode
Obj.style.display = "none";
}
}
}


function convertirEnDouble(entree)
{
  
     var retour =entree;
	entree = ""+entree;
	var pos=entree.lastIndexOf(".");
	
	if(pos==-1)
	    retour = entree +".00";
	else if( pos == entree.length-2)
		 retour = entree +"0";
	//alert(retour);
   return retour;
}

//Paul carte bancaire 07072020
function changementTypePayement()
{
    Masque_OBJ('rubRemise');
    Masque_OBJ('rubExoneration');
    Masque_OBJ('rubBanque');
    Masque_OBJ('rubCB');
    Affiche_OBJ('rubPayement');
        
    if(document.forms[0].typePayement != null )
    {
	    var typePayement = document.forms[0].typePayement.options[document.forms[0].typePayement.selectedIndex].text ;	
		if(typePayement.indexOf("Remise") != -1 )
		{	
			Affiche_OBJ('rubRemise');
			Masque_OBJ('rubPayement');
			document.getElementById('montantRemise').value = document.getElementById('montantAPayer').value ;
			document.getElementById('idBanque').value = 0 ;
			document.getElementById('numeroCheque').value=0;
			document.getElementById('numeroTransaction').value=0;
			document.getElementById('numeroQuittance').value='';
			//alert("here");
		}//paul 07072020
		else if(typePayement.indexOf("Exoneration") != -1 )
		{	
			Affiche_OBJ('rubExoneration');
			Masque_OBJ('rubPayement');
			document.getElementById('montantExoneration').value = document.getElementById('montantAPayer').value ;
			document.getElementById('idBanque').value = 0 ;
			document.getElementById('numeroCheque').value=0;
			document.getElementById('numeroTransaction').value=0;
			document.getElementById('numeroQuittance').value='';
			//alert("here");
		}
		else if(typePayement.indexOf("Espece") != -1 )
		{
			document.getElementById('idBanque').value=0;
			document.getElementById('numeroCheque').value=0;
			document.getElementById('numeroTransaction').value=0;
		}
		else if(typePayement.indexOf("Carte bancaire") != -1 )
		{
			Affiche_OBJ('rubCB');
			document.getElementById('idBanque').value=0;
			document.getElementById('numeroCheque').value=0;
			document.getElementById('numeroTransaction').value='';
		}
		else
		{
			Affiche_OBJ('rubBanque');
			document.getElementById('idBanque').value=0;
			document.getElementById('idBanque').value= document.forms[0].banque.options[document.forms[0].banque.selectedIndex].value;
			document.getElementById('numeroTransaction').value=0;
		}

	}
}


function verfierSommeFactureMontantPayement()
{
   res = 0;
   var somme="0";
   for(var i=0; i<tableauFacture.length ;  i++)
   {
     var idChamps = "V" + tableauFacture[i];   
	 somme = eval(somme) + eval(document.getElementById(idChamps).value) ;	 
	 somme=convertirEnDouble(somme);
	 somme = decim2(somme);	 
   }
   if( tableauFacture.length > 1 && eval(document.getElementById("montantAPayer").value) < eval(somme) )
   {
      alert("Le montant a payer doit être supérieur ou égale a la somme des factures ");
	  res =-1;
   }
	
   if( eval(document.getElementById("montantAPayer").value) > eval(somme) )
   {
		alert("Le montant a payer doit être inferieur ou égale a la somme des factures"); 
		res= -1;
				 
	}
	   
   return res;
	
}



//paul 07072020
function saisiePayement()
{
   var typePayement = document.forms[0].typePayement.options[document.forms[0].typePayement.selectedIndex].text ;
   var controleValeurRemise=0,controleValeurExoneration=0;
   var verfierMontantAPayer=0,verfierNumeroQuittance=0,verfierNumeroCheque=0;
   var verfierSommeFactureMontantP=0, verfierNumeroTransaction=0;
   var verfierMontantRemise =0,verfierMontantExoneration=0;
   
   if(typePayement.indexOf("Remise") != -1 )
   {
      //verfierNumeroQuittance= 0;  
      //verfierNumeroCheque = 0; 
      //verfierSommeFactureMontantP =  0;
      //verfierNumeroTransaction=0;
      montantRemise =document.getElementById("montantRemise").value ;          
      montantAPayer =document.getElementById("montantAPayer").value ;
      verfierMontantRemise = verifier("Montant de la remise",document.getElementById("montantRemise").value);     
      verfierMontantAPayer = verifier("Montant a payer",document.getElementById("montantAPayer").value);
      if( eval(montantRemise) > eval(montantAPayer) )
      {
    	  controleValeurRemise= -1;
   		  alert("Le montant de la remise doit etre inferieur au solde de la facture ");		 
   	 }
   }
   //paul 07072020
   else if(typePayement.indexOf("Exoneration") != -1 )
   {
	      //verfierNumeroQuittance= 0;  
	      //verfierNumeroCheque = 0; 
	      //verfierSommeFactureMontantP =  0;
	      //verfierNumeroTransaction=0;
	      montantExoneration =document.getElementById("montantExoneration").value ;
	      montantAPayer =document.getElementById("montantAPayer").value ;
	      verfierMontantExoneration = verifier("Montant de l'exonération",document.getElementById("montantExoneration").value);
	      verfierMontantAPayer = verifier("Montant a payer",document.getElementById("montantAPayer").value);
	      if( eval(montantExoneration) > eval(montantAPayer) )
	      {
	    	  controleValeurExoneration= -1;
	   		  alert("Le montant de l'exonérartion doit etre inferieur au solde de la facture ");		 
	   	 }
	   }
   else 
   {
	  //verfierMontantRemise = 0; verfierMontantExoneration=0; //07072020
      verfierMontantAPayer = verifier("Montant a payer",document.getElementById("montantAPayer").value);  
      verfierNumeroQuittance= verifier("Numero de quittance",document.getElementById("numeroQuittance").value);  
      verfierNumeroCheque = verifier("Numero de cheque",document.getElementById("numeroCheque").value); 
      verfierNumeroTransaction= verifier("Numero de transaction",document.getElementById("numeroTransaction").value);
      verfierSommeFactureMontantP =  verfierSommeFactureMontantPayement();
  }
 //07072020
 if ( verfierMontantAPayer ==0 && verfierNumeroQuittance == 0 &&  verfierNumeroCheque==0 && tableauFacture.length > 0  
      &&  verfierSommeFactureMontantP == 0 && verfierNumeroTransaction== 0 
      && verfierMontantRemise == 0  && controleValeurRemise ==0
      && verfierMontantExoneration == 0  && controleValeurExoneration ==0)
  {
     if(typePayement.indexOf("Remise") != -1 )
     {
        var question ="Voulez vous continuer la remise de "+document.getElementById("montantRemise").value +" euros\n" ;
		question=question+" pour la facture  :\n";
		 for(var i=0; i<tableauFacture.length ;  i++)
		 {
		     question=question+ "    -Facture N° "+tableauFacture[i] +"\n";
		 }
		 document.getElementById('montantAPayer').value =document.getElementById('montantRemise').value;
	 }
     //07072020
     else if(typePayement.indexOf("Exoneration") != -1 )
     {
         var question ="Voulez vous continuer l'exonération de "+document.getElementById("montantExoneration").value +" euros\n" ;
 		question=question+" pour la facture  :\n";
 		 for(var i=0; i<tableauFacture.length ;  i++)
 		 {
 		     question=question+ "    -Facture N° "+tableauFacture[i] +"\n";
 		 }
 		 document.getElementById('montantAPayer').value =document.getElementById('montantExoneration').value;
 	 }
	 else
     {
	     var question ="Voulez vous continuer ce paiement ? \n -Montant Paiement Total = "+document.getElementById("montantAPayer").value +" euros\n" ;
		 question=question+"-Nombre de facture total= "+document.getElementById("nombreFacture").value +"\n";
		 question=question+"-Facture a payées :\n";
		 for(var i=0; i<tableauFacture.length ;  i++)
		 {
		     question=question+ "    -Facture N° "+tableauFacture[i] +"\n";
		 }
	 }
	 reponse = confirm(question);
	 if(reponse)
	 {
		 document.getElementById("montantTotalFactures").value= convertirEnDouble(document.getElementById("montantTotalFactures").value);
		 document.forms[0].action="gestionPayement";
		 //alert("valider OK ...")
		 document.forms[0].submit(); 
	} 
  }
}

function majAffichagePayement()
{
  //alert(tableauFacture.length);
  if(tableauFacture.length != 0 )
  {
	Affiche_OBJ('rubTableauPayement');
  }
  else
  {
	Masque_OBJ('rubTableauPayement');
  }
	
}

function facture(numeroFacture, montantFacture ) 
{
    this.numeroFacture=numeroFacture;   
	this.montantFacture=montantFacture;   
}


function ajouterFacture(numeroFacture, montantFacture ) 
{
    this.numeroFacture=numeroFacture;   
	this.montantFacture=montantFacture;   
}



//Tableau de facture
var tableauFacture=new Array();

function mettreAJourTableau(what)
{
    //alert(what);  
	var existe="false";
	for(var i=0; i<tableauFacture.length ;  i++)
	{		
		if (tableauFacture[i]==what)
		{
		  tableauFacture.splice(i,1);
		  existe="true";
		  break;
		}
	}
	if(existe=="false") 
	  tableauFacture[tableauFacture.length]=what;	
	
	var listeNumeroFacture="";
   for(var i=0; i<tableauFacture.length ;  i++)
	{
	  listeNumeroFacture = listeNumeroFacture + ";" +tableauFacture[i];	
   }
   document.forms[0].listeNumeroFacture.value= listeNumeroFacture;	
   //alert(tableauFacture.length );  
   var idChamps = "V" + what;   
   //alert(document.getElementById(idChamps).value);   
   document.getElementById("nombreFacture").value = tableauFacture.length;      
   
   //Maj du montant total a payer :
   var somme = 0;
   for(var i=0; i<tableauFacture.length ;  i++)
   {
      var idChamps = "V" + tableauFacture[i];   
	  somme = eval(somme) + eval(document.getElementById(idChamps).value) ;	 
	  somme=convertirEnDouble(somme);	 
   }
   somme = decim2(somme);
   document.getElementById("montantTotalFactures").value = somme;   
   document.getElementById("montantAPayer").value = somme;  
   document.getElementById('montantRemise').value = somme ; 
   
   majAffichagePayement();
   document.getElementById("montantTotalFactures").readOnly=true;
   if(tableauFacture.length > 1)
  {    
	//document.getElementById("montantTotalFactures").readOnly=true;
  }
  else
  {
	//document.getElementById("montantTotalFactures").readOnly=false;
  }

   
}














function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}

function init()
{
  //alert(document.forms[0].etatEmplacement.value);
  switch (document.forms[0].etatEmplacement.value)
  {
       case "enActivite": 
  		   document.forms[0].etatDesEmplacements[0].checked = true;
          break;
  		 case "termine": 
  		   document.forms[0].etatDesEmplacements[1].checked = true;
          break;
  		 case "tous": 
  		   document.forms[0].etatDesEmplacements[2].checked = true;
          break;
   }

  switch (document.forms[0].etatFacture.value)
  {
     
       case "enCours": 
  		   document.forms[0].etatDesFactures[0].checked = true;
  		   document.forms[0].etatDesPaiement[0].checked = true;
          break;
  		 case "paye": 
  		   document.forms[0].etatDesFactures[1].checked = true;
  	       document.forms[0].etatDesPaiement[1].checked = true;
          break;  		
   }

}

function afficherEmplacement(type)
{
	var action = "./entree?action=empl_gestion_redevable.jsp&choix=modifier";
	action = action + "&numRedevable="+document.forms[0].numRedevable.value+"&etatEmplacement="+type;
	document.forms[0].action = action;
	
	document.forms[0].submit();
}

function afficherFacture(type)
{
	var action = "./entree?action=empl_gestion_redevable.jsp&choix=modifier";
	action = action + "&numRedevable="+document.forms[0].numRedevable.value+"&etatFacture="+type;
	document.forms[0].action = action;	
	document.forms[0].submit();
}

function detecterChangementAdresse()
{
 //alert("Appel module changement adresse");
 document.forms[0].changementAdresseRedevable.value="true";
}

function majChampAdresse(adresse)
{  
  var reg=new RegExp("-----"); 
  var tableau=adresse.split(reg); 
  res= adresse.indexOf("PAS", 0);

if(res !=0)
{
  document.forms[0].codeVoixRedevable.value=tableau[0];
  document.forms[0].adressRedevable.value=tableau[1];
  document.forms[0].codeRivolieRedevable.value=tableau[3];
  document.forms[0].codePostaleRedevable.value=tableau[4];
  detecterChangementAdresse();
}
else
{
  document.forms[0].codeVoixRedevable.value="";
  document.forms[0].adressRedevable.value="";
}
  
}

function majChampCodeVille(codeVille)
{
	var reg=new RegExp("-----"); 
	var tableau=codeVille.split(reg); 
	res= codeVille.indexOf("PAS", 0);

	if(res < 0)
	{
	  document.forms[0].codePostaleRedevable.value=tableau[0];
	  document.forms[0].villeeRedevable.value=tableau[1];
	  detecterChangementAdresse();
	}
	else
	{
	  document.forms[0].codePostaleRedevable.value="";
	  document.forms[0].villeeRedevable.value="";
	}
  
}

function majChampCodeVilleLiq(codeVille)
{
	var reg=new RegExp("-----"); 
	var tableau=codeVille.split(reg); 
	res= codeVille.indexOf("PAS", 0);

	if(res < 0)
	{
	  document.forms[0].codePostaleLiquidateur.value=tableau[0];
	  document.forms[0].villeeLiquidateur.value=tableau[1];
	}
	else
	{
	  document.forms[0].codePostaleLiquidateur.value="";
	  document.forms[0].villeeLiquidateur.value="";
	}
  
}


function rechercheVoie(nomRueRecherche)
{
       
        if(nomRueRecherche.length >3)
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
          alert("Entrez au moins 4 caractéres pour rechercher des rues")
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
              //alert(node.length) 	;
              if (node.length==0)           
              {
                 alert ("Aucune rue disponible pour ce code rivoli !!");
                 document.getElementById("listeRue").innerHTML = "";
                 document.forms[0].adressRedevable.value="";
              }
              else
              {
                 document.forms[0].codeVoixValide.value="true";
                 var codeVoie= xmldoc.getElementsByTagName("code")[0].childNodes[0].nodeValue;                  
                 var adresse= xmldoc.getElementsByTagName("adresse")[0].childNodes[0].nodeValue;                                
                 var codeRivolie= xmldoc.getElementsByTagName("codeRivolie")[0].childNodes[0].nodeValue;                                
  					var codePostal= xmldoc.getElementsByTagName("codePostal")[0].childNodes[0].nodeValue;

  				 	//alert(code+" ---- "+adresse); 
				 	document.forms[0].codeVoixRedevable.value=codeVoie;
					document.forms[0].adressRedevable.value=adresse;
					document.forms[0].codePostaleRedevable.value=codePostal;

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
             document.forms[0].codePostaleRedevable.value="";
             document.forms[0].villeeRedevable.value="";
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


function rechercheCodeVilleLiq(codeRecherche)
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
        httpRequest.onreadystatechange = function() {processRequestRechercheCodeVilleLiq(); } ;
        httpRequest.send(null); 
}

function processRequestRechercheCodeVilleLiq()
{       
	if (httpRequest.readyState == 4)
    {
        if(httpRequest.status == 200)
        {
          var xmldoc = httpRequest.responseXML.documentElement;			 			  
          var node = xmldoc.getElementsByTagName("ligne");			  
          var contenuListVille="<select name='listeCodeVilleLiq' onClick=majChampCodeVilleLiq(listeCodeVilleLiq.options[this.selectedIndex].text) onChange=majChampCodeVilleLiq(listeCodeVilleLiq.options[this.selectedIndex].text)>";
  
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
             document.forms[0].codePostaleLiquidateur.value="";
             document.forms[0].villeeLiquidateur.value="";
          }                	 

           contenuListVille+="</select>"; 
           document.getElementById("listeCodeVilleLiq").innerHTML = contenuListVille;  
       }
        else
        {
            alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
        }             
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
              var contenuListRue="<select name='listDesRues' onclick=majChampAdresse(listDesRues.options[this.selectedIndex].text)  onChange=majChampAdresse(listDesRues.options[this.selectedIndex].text)>";
      
				  for (i = 0; i < node.length; i++)
				  {
				  
               var code= xmldoc.getElementsByTagName("code")[i].childNodes[0].nodeValue;                  
               var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue;                  
				  var quartier= xmldoc.getElementsByTagName("quartier")[i].childNodes[0].nodeValue;                  
				  var codeRivolie= xmldoc.getElementsByTagName("codeRivolie")[i].childNodes[0].nodeValue;
				  var codePostal= xmldoc.getElementsByTagName("codePostal")[i].childNodes[0].nodeValue;

               
           var element= code+"-----"+adresse+"-----"+quartier+"-----"+codeRivolie+"-----"+codePostal;
                  //alert(element);
                  contenuListRue=contenuListRue+"<option value="+element+">"+element+"</option>";
                  //alert(contenuListRue);
                  //alert("ELEMEENT"+this.options[i].value);
               } 
            
               if (node.length==0)           
              {
                 contenuListRue=contenuListRue+"<option>    PAS DE RESULTATS    </option>";
                 document.forms[0].adressRedevable.value="";
                 document.forms[0].codeRivolieRedevable.value="";
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


   function modifier()
   {
    
     verfiDate=0;
     if(document.forms[0].dateNaissanceRedevable.value.length != 0  )
     {
       //alert("date existe");
       verfiDate= verif_date(document.forms[0].dateNaissanceRedevable.value);
       if (!verfiDate)
       {
         alert("Date doit etre de la forme JJ/MM/AAAA");
         verfiDate=1;
       }
       else
         verfiDate=0;
     }
     else
     {
       //alert("date n existe pas");
       verfiDate=0;
     }
	  
     var verfiMailRedevable=0;
     if(document.forms[0].emailRedevable.value.length != 0  )
     {
       //alert("mail existe");
       verfiMailRedevable= VerifMail(document.forms[0].emailRedevable.value);
       //alert(verfiMailRedevable);
       if (verfiMailRedevable=="false")
       {
         alert("Adresse e-mail invalide ");
         verfiMailRedevable=1;
       }
       else
         verfiMailRedevable=0;
     }
     else
     {
    //Paul ajouter alerter mais pas bloquer si mail n'est pas renseigné
       alert("Le mail du redevable n'est pas renseigné ")
       verfiMailRedevable=0;
     }
     
     
     var verfiEmailLiquidateur=0;
     if(document.forms[0].emailLiquidateur.value.length != 0  )
     {
       //alert("mail existe");
       verfiEmailLiquidateur= VerifMail(document.forms[0].emailLiquidateur.value);
       //alert(verfiEmailLiquidateur);
       if (verfiEmailLiquidateur=="false")
       {
         alert("Adresse e-mail invalide ");
         verfiEmailLiquidateur=1;
       }
       else
         verfiEmailLiquidateur=0;
     }
     else
     {
       //alert("mail n existe pas");
       //alert(verfiEmailLiquidateur);
       verfiEmailLiquidateur=0;
     }
     verfiNom= verifier("nom du redevable",document.forms[0].nomRedevable.value);     
     verfiAdresse= verifier("Adresse",document.forms[0].adressRedevable.value);     
     verifNumVoieRedevable= verifier("Numero de voie",document.forms[0].numVoieRedevable.value);     
	  //verifNumRue= verifier("Numéro de rue de l'emplacement ",document.forms[0].numRue.value); 
	  verifCodePostaleRedevable= verifier("Code Postale dans l'adresse du redevable",document.forms[0].codePostaleRedevable.value); 
	  verifVilleeRedevable= verifier("Ville dans l'adresse du redevable",document.forms[0].villeeRedevable.value); 

    verfiRedevableActif=0;  
	
    
    if(document.forms[0].redevableActif.value == "NON" )
    {
	        if(document.forms[0].peutEtreDesactvier.value == "false" )
			{			    			  
			  if(document.forms[0].ville.value.toLowerCase() == "bordeaux" )
			  {				
				alert("Impossible de rendre ce redevable inactif, il a des emplacements actifs ");
  		      }
			  else
			  {
			    alert("Impossible de rendre ce redevable inactif, il y a des Factures en cours ");
			  }
			  verfiRedevableActif=-1;
			}		
		
	 }   
   
     var verfiRedevableSiretSiren = 0;
     if( document.forms[0].siren.value.length == 0 && document.forms[0].siret.value.length == 0 )
         verfiRedevableSiretSiren = 0;
     else  
     	verfiRedevableSiretSiren  = document.forms[0].verifSiretSiren.value ;
     //Verfier unicite Siren et Siret
     if (verfiRedevableSiretSiren == -1)  alert("Le numero de SIRET et de SIREN doivent être uniques");
	 
     //Paul Alerter si le siren n'est pas renseigné mais pas bloquer
     if( document.forms[0].siren.value.length == 0) alert("Le numéro SIREN n'est pas renseigné ");
   //Paul Alerter et bloquer si aucun numéro de téléphone est renseiné
     var verifTelephone=0;
   	 if (document.forms[0].numTelFixeRedevable.value.length==0 && 
   			document.forms[0].numTelPortableRedevable.value.length==0 &&
   			document.forms[0].numTelFaxeRedevable.value.length==0 ){
   		verifTelephone=-1;
   		 alert("Au moins un numéro de téléphone du redevable doit être renseigné");
   		 
   	 }
	 
     verfiPrenomRedevable= verifierPrenom();
     //verfiRaisonSocialRedevable= verifierRaisonSocialRedevable();
    
     
     if( verfiEmailLiquidateur==0 && verfiMailRedevable==0 && 
          verfiNom==0 && verfiAdresse==0 && verfiDate==0 && verifNumVoieRedevable==0 
          && verifCodePostaleRedevable==0 &&  verifVilleeRedevable==0 && verifTelephone==0
		  && verfiRedevableActif==0 && verfiRedevableSiretSiren==0 && verfiPrenomRedevable==0
		   )     
     {
     	document.forms[0].submit();  
     }
     else
     {
       //alert("Champs manquant !!")
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
  
  function modifierCedexLiquidateur()
  { 
  	
  	if(document.forms[0].cedexLiquidateurCk.checked==true )
  	{	  	
	  	document.forms[0].cedexLiquidateur.value="true";
	}
  	else
  	{
	  	document.forms[0].cedexLiquidateur.value="false";
  	}
  }
  
 
  
  
function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer ce redevable  ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";
				document.forms[0].submit();  
			 }
		}
		else
		{
			  if(document.forms[0].ville.value.toLowerCase() == "bordeaux" )
				alert("Impossible de rendre ce redevable inactif, il a des emplacements actifs ");
			  else
			   alert("Impossible de rendre ce redevable inactif, il y a des Factures en cours ou il y a des emplacements affecte a ce redevable ");
		}
		
			
}
</script>

<%
String regie = (String)session.getAttribute("regie");
String ville = (String)session.getAttribute("ville"); 
%>

<form method="POST" action="gestionredevable" name="formilaireRedevable" >


<input type="hidden" id="ville" name="ville" value="<%out.print(ville);%>" >
<input type="hidden" name="listeNumeroFacture" >
<input type="hidden" id="idBanque"  name="idBanque">
<input type="hidden" name="verifSiretSiren" size="20" value="0" >

<input type="hidden" name="etatEmplacement" value="<jsp:getProperty name="beanRole" property="etatEmplacement"/>"  >
<input type="hidden" name="etatFacture" value="<jsp:getProperty name="beanRole" property="etatFacture"/>"  >
<input type="hidden" name="peutEtreSupprimer" value="<jsp:getProperty name="beanRole" property="peutEtreSupprimer"/>"  >
<input type="hidden" name="peutEtreDesactvier" value="<jsp:getProperty name="beanRole" property="peutEtreDesactvier"/>"  >
<input type="hidden" name="cedexRedevable" value="false">
<input type="hidden" name="changementAdresseRedevable" value="false">
<input type="hidden" name="cedexLiquidateur" value="false">
<input type="hidden" name="codeVoixValide" >
<input type="hidden" name="codeVoixRedevable"  value="<jsp:getProperty name="beanRole" property="codeVoixRedevable"/>"  >
<input type="hidden" name="numRedevable" value="<jsp:getProperty name="beanRole" property="numRedevable"/>" >


<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Gestion du redevable</font></td>
  </tr>
</table>


<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <table border="1" width="100%">
              <tr>
                <td width="25%" align="center"><b><span style="font-size:12.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">
<a href="#gestionEmplacement">Gestion des emplacements</a></span></b></td>
                <td width="25%" align="center"><b><span style="font-size:12.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA"><a href="#gestionFactures">Gestion des
                  factures &amp; réclamations</a></span></b></td>
                <td width="25%" align="center"><b><span style="font-size:12.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA"><a href="#gestionPaiement">Gestion des<span style="mso-spacerun: yes">&nbsp;
                  </span>paiements</a></span></b></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/user.png" width="40" height="40"></b></font></p>
          </td>
        </tr>
</table>




<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td width="100%" bgcolor="#FFFFE8">
            <p>
                         
            <input type="hidden" name="choix"   maxlength="30" size="10"
                   value="<jsp:getProperty name="beanRole" property="choix"/>" >

              <% if (beanRole.getChoix().equalsIgnoreCase("modifier")) {%>
                 <font size="3" face="Arial" color="#000000"><b>&nbsp;MODIFICATION DU REDEVABLE
                 N°&nbsp; <jsp:getProperty name="beanRole" property="numRedevable"/>
			  <%}else{%>              
			     <font size="3" face="Arial" color="#000000"><b>&nbsp;CREATION D'UN NOUVEAU REDEVABLE :&nbsp;
 			  <%}%>              

                         
              </b></font>
             </b></font>
          </td>
        </tr>
</table>
      

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="241">

	
      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b><span style="font-family: Arial, sans-serif">N</span><span style="font-family: &quot;Arial&quot;,&quot;sans-serif&quot;;">ature
          juridique</span> :</b></font></p>
        </td>
				
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
	          <jsp:getProperty name="beanRole" property="natureJuridiqueRedevable"/>
          </p>
        </td>        
      </tr>
     

      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="5">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Raison sociale</font><font color="#FF0000" face="Arial" size="4"></font><font size="2" face="Arial"> :</font></b>
          </td>
        <td width="80%" colspan="2" height="5">
         <table border="1" width="100%">
          <tr>
            <td width="35%">
            	<jsp:getProperty name="beanRole" property="raisonSocialeRedevable"/>
            </td>
            <td width="65%"><b><font size="2" face="Arial">Nom </font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"> :
              </font></b><font size="2" face="Arial"> 
              <input onkeyup="caps(this)" type="text" name="nomRedevable"  id="nomRedevable"  size="40"  value="<jsp:getProperty name="beanRole" property="nomRedevable"/>"  >
              <input  type="hidden" name="raisonSocialeRedevableok"  id="raisonSocialeRedevableok"  size="40"  value="<jsp:getProperty name="beanRole" property="raisonSocialeRedevableok"/>"  >
              
              </font></td>
          </tr>
         </table>
         
        </td>
      </tr>

      <tr>
      
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Responsable / Service – Nom de l'enseigne&nbsp;</font><font size="2" face="Arial">
          :</font></b></td>
        <td width="80%" colspan="2" height="23">
          <table border="1" width="100%">
            <tr>
              <td width="35%"><input onkeyup="caps(this)"  type="text"   name="responsableRedevable" id="responsableRedevable" 
   value="<jsp:getProperty name="beanRole" property="responsableRedevable"/>"
                maxlength="40" size="40" >&nbsp;</td>
              <td width="65%"><b>
           Nom de jeune fille :</b>
           <input onkeyup="caps(this)" type="text" name="nomJeuneFilleRedevable" id="nomJeuneFilleRedevable" 
             value="<jsp:getProperty name="beanRole" property="nomJeuneFilleRedevable"/>"
                maxlength="40" size="40"></td>
            </tr>
          </table>
        </td>
      </tr>
	  
	  
	  
	  <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Siren&nbsp;</font><font size="2" face="Arial">
          :</font></b></td>
        <td width="80%" colspan="2" height="23">
          <table border="1" width="100%">
            <tr>
              
              <td width="35%"><input onkeyup="caps(this)"  onchange="verfierUnicite()" onclick="verfierUnicite()"  type="text" name="siren" id="siren" 
		   
             value="<jsp:getProperty name="beanRole" property="siren"/>"
                maxlength="9"; size="9"></td>
				<td width="65%"><b><font size="2" face="Arial">Siret : </font></b>
				<input onkeyup="caps(this)" type="text" onchange="verfierUnicite()" onclick="verfierUnicite()"      name="siret" id="siret" 
			  
   value="<jsp:getProperty name="beanRole" property="siret"/>"
                maxlength="5" size="5" >&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
	  
	  
	  
	  
	  
      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Prénom :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
          <input onkeyup="caps(this)"  type="text" 
          name="prenomRedevable" id="prenomRedevable" 
          value="<jsp:getProperty name="beanRole" property="prenomRedevable"/>"
          maxlength="30" size="25">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</p>
        </td>        
      </tr>
	  
	  <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Information complementaire :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
          <input  type="text" 
          name="informationComplementaire" id="informationComplementaire" 
          value="<jsp:getProperty name="beanRole" property="informationComplementaire"/>"
          maxlength="100" size="123">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</p>
        </td>        
      </tr>
	  
	  <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Remarque :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
		  <TEXTAREA cols="110" rows="7" name="commentaire" ><jsp:getProperty name="beanRole" property="commentaire"/></TEXTAREA>          
        </td>        
      </tr>
      <!-- Paul  -->
       <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Commentaires depuis tablette :</b></font></p>
        </td>  
        <td width="100%" colspan="2" height="23">
          <%=beanRole.getListeCommentaire()%>
        </td>     
      </tr>
      
     <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="24">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Date de
          naissance : JJ/MM/AAAA&nbsp;</b></font></p>
        </td>
        <td width="80%" colspan="2" height="24">
          <table border="1" width="100%">
            <tr>
              <td width="35%">
          <input  type="text" 
          name="dateNaissanceRedevable" id="dateNaissanceRedevable"
           value="<jsp:getProperty name="beanRole" property="dateNaissanceRedevable"/>"
                maxlength="10" size="25" readonly>              
         
		  <img onclick="displayCalendar(document.forms[0].dateNaissanceRedevable,'dd/mm/yyyy',this)"
		  src="./images/calendar.gif" border="0" width="20" height="18" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
              <td width="65%"><b>
          Lieu de naissance :&nbsp;</b><input onkeyup="caps(this)" type="text" 
          name="lieuNaissanceRedevable" id="lieuNaissanceRedevable" 
          value="<jsp:getProperty name="beanRole" property="lieuNaissanceRedevable"/>"
                maxlength="30" size="22"></td>
            </tr>
          </table>
        </td>
	</tr>
     <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Nationalité
          :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
          <input onkeyup="caps(this)"  type="text" 
          name="nationalliteRedevable" id="nationalliteRedevable" 
          value="<jsp:getProperty name="beanRole" property="nationalliteRedevable"/>"
          maxlength="30" size="20"></p>
        </td>
      </tr>
      
     
      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="64">
          <font face="Arial" size="2"><b>&nbsp;Numéro de voie</b></font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
          <font face="Arial" size="2"><b> :</b></font></td>
        <td width="80%" colspan="2" height="64">
          <table border="1" width="100%" height="1">
            <tr>
              <td width="894" height="15">
              
              <b><font size="2" face="Arial">N°</font></b><font color="#FF0000">*
              </font><b>
              <input onChange="detecterChangementAdresse();" type="text" name="numVoieRedevable" size="5" value=<jsp:getProperty name="beanRole" property="numVoieRedevable"/> >
              <font size="2" face="Arial">Compl</font></b>
                <jsp:getProperty name="beanRole" property="complementNumeroRueRedevable" />
              <b>&nbsp;</b>  
              <b>Code Rivoli</b> <input onkeyup="caps(this)"  size="9" type="text" name="codeRivolieRedevable" id="codeRivolieRedevable" onChange="detecterChangementAdresse();"
                 value="<jsp:getProperty name="beanRole" property="codeRivolieRedevable"  />">           
              <a href="javascript:rechercheVoieParCodeVoie(document.forms[0].codeRivolieRedevable.value,document.forms[0].numVoieRedevable.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>            
              <b>&nbsp;&nbsp;&nbsp;&nbsp; Recherche par nom voie: </b>
              <input type="text" name="nomRueRecherche" size="26">
              <a href="javascript:rechercheVoie(document.forms[0].nomRueRecherche.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
              &nbsp;
               </td>
            </tr>        
            <tr>
              <td width="876" height="21">
                   <div id="listeRue" style="width: 550; height: 20">   </div>
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td width="21%" background="./images/fond_bleu.gif" rowspan="4" height="54">
          <p style="margin-left: 5"><b><font size="2" face="Arial">Adresse de facturation</font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
        <%if (ville.equalsIgnoreCase("bordeaux")) {%>
        <td width="80%" colspan="2" height="23">
   		 <font size="2" face="Arial">Immeuble/Résidense :<input  type="text" 
          name="immeubleResidenceRedevable" id="immeubleResidenceRedevable" onChange="detecterChangementAdresse();"
          value="<jsp:getProperty name="beanRole" property="immeubleResidenceRedevable" />"
          size="40">&nbsp;&nbsp;></font>
        </td>
        <% } %>
      </tr>
      <tr>
            <td width="41%" height="20">
        	<p style="margin-left: 2">
          <font size="2" face="Arial">
          Nom de la voie : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <input  type="text" onkeyup="caps(this)"
          name="adressRedevable" id="adressRedevable" onChange="detecterChangementAdresse();"
          value="<jsp:getProperty name="beanRole" property="adressRedevable"/>"
           size="40"></font></p>
			</td>
			
        <td width="39%" height="23" >
           <p style="margin-left: 2;margin-top:2">
           	<b>Recherche ville par code : </b>
              <input type="text" name="codeRecherche" size="10">
              <a href="javascript:rechercheCodeVille(document.forms[0].codeRecherche.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
              &nbsp;&nbsp;<span id="listeCodeVille"> </span>	
           </p>
     	</td>
      </tr>
      <tr>
        <td width="41%" height="20">
          <font size="2" face="Arial">compl. Adresse :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input  type="text" onkeyup="caps(this)"
          name="complement1AdressRedevable" id="complement1AdressRedevable" onChange="detecterChangementAdresse();"
          value="<jsp:getProperty name="beanRole" property="complement1AdressRedevable" />"
          size="40">&nbsp;&nbsp;</font>
        </td>
        <td width="39%" height="20" >
        	<p style="margin-left: 2">
	          <font size="2" face="Arial">CP</font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
	          <font size="2" face="Arial"> &nbsp;&nbsp;:&nbsp;&nbsp;
	          <input  type="text" readonly
	          name="codePostaleRedevable" id="codePostaleRedevable" onChange="detecterChangementAdresse();"
	          value="<jsp:getProperty name="beanRole" property="codePostaleRedevable"/>"
	          maxlength="5" size="10"></font>
	          <font size="2" face="Arial">Ville</font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
	          <font size="2" face="Arial"> :&nbsp;&nbsp;
	          <input  type="text" readonly
	          name="villeeRedevable" id="villeeRedevable" onChange="detecterChangementAdresse();"
	          value="<jsp:getProperty name="beanRole" property="villeeRedevable"/>"
	          maxlength="30" size="25">&nbsp;
	          <input type="checkbox"  onClick="modifierCedexRedevable()" onChange="detecterChangementAdresse();"
	          name="cedexRedevableCk"      <jsp:getProperty name="beanRole" property="cedexRedevable"/>     
	           onChange=detecterChangementAdresse();>Cédex</font></p>	
        </td>
      </tr>

      <tr>
        <td width="41%" height="20">
          <p style="margin-left: 2">
          <font size="2" face="Arial">compl. Adresse&nbsp; :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input  type="text" onkeyup="caps(this)"
          name="complement2AdressRedevable" id="complement2AdressRedevable" onChange="detecterChangementAdresse();"
          value="<jsp:getProperty name="beanRole" property="complement2AdressRedevable"/>"
          size="40"></font></p>
        </td>
        <td width="39%" height="20" >
			
        </td>
      </tr>

    <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="46">
          <p style="margin-left: 5"><b><font face="Arial" size="2">N° de téléphone :  </font></b>
        </td>
        <td width="80%" colspan="2" height="46">
           <p style="margin-left: 2">Fixe:&nbsp;&nbsp;
           <input  type="text" 
            name="numTelFixeRedevable" id="numTelFixeRedevable" 
		    value="<jsp:getProperty name="beanRole" property="numTelFixeRedevable"/>"
           maxlength="14" size="18">&nbsp;&nbsp;&nbsp;
           
          Portable :&nbsp;&nbsp;
          <input  type="text" 
          name="numTelPortableRedevable" id="numTelPortableRedevable" 
          value="<jsp:getProperty name="beanRole" property="numTelPortableRedevable"/>"
           maxlength="14" size="19">&nbsp;&nbsp;&nbsp;Fax :&nbsp;&nbsp;
          <input  type="text" 
          name="numTelFaxeRedevable" id="numTelFaxeRedevable" 
          value="<jsp:getProperty name="beanRole" property="numTelFaxeRedevable"/>"
           maxlength="14" size="14">
        </td>
      </tr>

     <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Émail :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="23">
          <p style="margin-left: 2">
          <input  type="text"
           name="emailRedevable" id="emailRedevable" 
           value="<jsp:getProperty name="beanRole" property="emailRedevable"/>"
          maxlength="60" size="55"></p>
        </td>
      </tr>
      
      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="19">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Profession :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="19">
          <p style="margin-left: 2">   
          <jsp:getProperty name="beanRole" property="proffesionRedevable"/>
          </p>
        </td>
      </tr>
      
      <tr>
        <td width="21%" background="./images/fond_bleu.gif" height="19">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Actif :</b></font></p>
        </td>
        <td width="80%" colspan="2" height="19">
          <p style="margin-left: 2">   
          <jsp:getProperty name="beanRole" property="redevableActif"/>
          </p>
        </td>
      </tr>
      
     
      
</table>

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="244">
        <tr>
        <td  width="100%" bgcolor="#FFFFE8" colspan="3" height="19">
          <font size="3" face="Arial" color="#000000"><b>LIQUIDATEUR :</b></font></td>
        </tr>
        <tr>
        <td  width="19%" background="./images/fond_bleu.gif" height="16">
          <font size="2" face="Arial"><b>&nbsp;</b></font><b><font size="2" face="Arial">Raison
          sociale</font><font size="2" face="Arial"> :</font></b></td>
        <td width="81%" colspan="2" height="16">
          <font size="2" face="Arial"><b>          <jsp:getProperty name="beanRole" property="raisonSocialeLiquidateur"/></b></font>
        </td>
        </tr>
        <tr>
        <td  width="19%" background="./images/fond_bleu.gif" height="22">
          <font size="2" face="Arial"><b>&nbsp;Nom :</b></font></td>
        <td width="81%" colspan="2" height="22">
          <font size="2" face="Arial"><b><input onkeyup="caps(this)" type="text" size="29" maxlength="20" name="nomLiquidateur"
          value="<jsp:getProperty name="beanRole" property="nomLiquidateur" />"
          >
          </b></font>
        </td>
        </tr>
        
        <tr>        
           <td  width="19%" background="./images/fond_bleu.gif" height="23">
             <font size="2" face="Arial"><b>&nbsp;Prénom :</b></font>
           </td>          
           <td width="81%" colspan="2" height="23">
             <font size="2" face="Arial"><b><input onkeyup="caps(this)" type="text" size="29" maxlength="20"
              name="prenomLiquidateur"
              value="<jsp:getProperty name="beanRole" property="prenomLiquidateur" />"      >
            </b></font>
          </td>        
        </tr>    
        
        <tr>        
           <td  width="19%" background="./images/fond_bleu.gif" height="23">
             <font size="2" face="Arial"><b>&nbsp;Numéro de voie :</b></font>
           </td>          
           <td width="81%" colspan="2" height="23">
             <font size="2" face="Arial"><b>
                <b><font size="2" face="Arial">N°</font></b>
               <input type="text" name="numVoieLiquidateur" size="3" value=<jsp:getProperty name="beanRole" property="numVoieLiquidateur"/> 
             >
               </b></font><font size="2" face="Arial"><b>Compl
                <jsp:getProperty name="beanRole" property="complementNumeroRueLiquidateur" />
            </b></font>
          </td>        
        </tr>
         <tr>        
           <td  width="19%" background="./images/fond_bleu.gif" rowspan="3" height="37">
             <font size="2" face="Arial"><b> &nbsp;Adresse personnelle :</b></font>
           </td>          
        <!--   <td width="81%" colspan="2" height="23"> -->
         	<td width="44%" height="23">
             <font size="2" face="Arial"><b><input type="text" size="29" maxlength="30"
              name="adressLiquidateur" onkeyup="caps(this)"
              value="<jsp:getProperty name="beanRole" property="adressLiquidateur" />"      >
            </b></font>
          </td>
          <td width="39%" height="23" >
           <p style="margin-left: 2;margin-top:2">
           	<b>Recherche ville par code : </b>
              <input type="text" name="codeRechercheLiq" size="10">
              <a href="javascript:rechercheCodeVilleLiq(document.forms[0].codeRechercheLiq.value);" ><img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25"></a>
              &nbsp;&nbsp;<span id="listeCodeVilleLiq"> </span>	
           </p>
     	</td>        
        </tr>    
        <tr>
	        <td width="44%" height="23">
	          <p style="margin-left: 2">
	          <font size="2" face="Arial">compl. Adresse&nbsp; :&nbsp; 
	          <input  type="text" onkeyup="caps(this)"
	          name="complement1AdressLiquidateur" id="complement1AdressLiquidateur" 
	          value="<jsp:getProperty name="beanRole" property="complement1AdressLiquidateur"  />"
	           size="39">&nbsp;&nbsp;</font></p>
	        </td>
	        <td width="37%" height="23" >
	           <p style="margin-left: 2">
	          <font size="2" face="Arial">CP&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	          <input  type="text" readonly
	          name="codePostaleLiquidateur" id="codePostaleLiquidateur" 
	          value="<jsp:getProperty name="beanRole" property="codePostaleLiquidateur"  />"
	          maxlength="5" size="18"></font></p>
	        </td>
        </tr>
        <tr>
	        <td width="44%" height="1">
	          <p style="margin-left: 2">
	          <font size="2" face="Arial">compl. Adresse&nbsp; :&nbsp; 
	          <input  type="text" onkeyup="caps(this)"
	          name="complement2AdressLiquidateur" id="complement2AdressLiquidateur" 
	          value="<jsp:getProperty name="beanRole" property="complement2AdressLiquidateur"  />"
	           size="40"></font></p>
	        </td>
	        <td width="37%" height="1" >
	        <p style="margin-left: 2">
	          <font size="2" face="Arial">Ville :&nbsp;&nbsp;
	          <input  type="text" name="villeeLiquidateur" id="villeeLiquidateur" readonly
	          value="<jsp:getProperty name="beanRole" property="villeeLiquidateur"  />"
	          maxlength="30" size="30">&nbsp;
	          <input type="checkbox" 
	          name="cedexLiquidateurCk"   onClick="modifierCedexLiquidateur()"
	          <jsp:getProperty name="beanRole" property="cedexLiquidateur" />    > Cédex
	          </font></p>
	        </td>
        </tr>
        <tr>
        <td width="19%" background="./images/fond_bleu.gif" height="46">
          <p style="margin-left: 5"><b><font face="Arial" size="2">N° de téléphone :  </font></b>
        </td>
        <td width="81%" colspan="2" height="46">
           <p style="margin-left: 2">Fixe:&nbsp;&nbsp;
           <input  type="text" 
            name="numTelFixeLiquidateur" id="numTelFixeLiquidateur" 
		    value="<jsp:getProperty name="beanRole" property="numTelFixeLiquidateur" />"
           maxlength="14" size="18">&nbsp;&nbsp;&nbsp;
           
          Portable :&nbsp;&nbsp;
          <input  type="text" 
          name="numTelPortableLiquidateur" id="numTelPortableLiquidateur" 
          value="<jsp:getProperty name="beanRole"  property="numTelPortableLiquidateur" />"
           maxlength="14" size="19">&nbsp;&nbsp;&nbsp;Fax :&nbsp;&nbsp;
          <input  type="text" 
          name="numTelFaxeLiquidateur" id="numTelFaxeLiquidateur" 
          value="<jsp:getProperty name="beanRole" property="numTelFaxeLiquidateur" />"
           maxlength="14" size="14">
        </td>
        </tr>
        <tr>
        <td width="19%" background="./images/fond_bleu.gif" height="23">
          <p style="margin-left: 5"><font face="Arial" size="2"><b>Émail :</b></font></p>
        </td>
        <td width="81%" colspan="2" height="23">
          <p style="margin-left: 2">
          <input  type="text"
           name="emailLiquidateur" id="emailLiquidateur" 
           value="<jsp:getProperty name="beanRole" property="emailLiquidateur"  />"
          maxlength="60" size="55"></p>
        </td>
        </tr>
        <tr>
        <td width="19%" background="./images/fond_bleu.gif" height="23">
          <b><i><font size="2" face="Arial" color="#FF0000">(</font></i><font color="#FF0000" face="Arial" size="4">*</font><i><font size="2" face="Arial" color="#FF0000">) : saisie obligatoire</font></i></b>
        </td>
        <td width="81%" colspan="2" height="23">
          <p align="center">



<%
String typeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");  
if (  typeTaxeAutorise.length() != 0 )           
{
 %>  

<% if (beanRole.getChoix().equalsIgnoreCase("modifier")) {
	if (!beanRole.getNumRedevable().equalsIgnoreCase("-1")) {
%>
	<a href="javascript:modifier();"><img border="0" src="./images/modifier.gif" width="150" height="17"></a>
	<a href="javascript:supprimer();"><img border="0" src="./images/supprimer.gif" width="150" height="17"></a>&nbsp;
<%}}else{%>
<a href="javascript:modifier();"><img border="0" src="./images/valider.gif" width="150" height="17"></a>
<%}%>

<% } %>

        </td>
        </tr>
</table>
          
     
<p align="center">




<A NAME="gestionEmplacement">  

<% if (beanRole.getChoix().equalsIgnoreCase("modifier")) {%>    
          
     
 &nbsp; 
         
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="66">
        <tr>
          <td width="200%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="images/emplacement.png" width="40" height="40"></p>
          </td>
        </tr>
        <tr>
          <td width="200%" bgcolor="#AFF3BB" height="17">
              <font color="#000000"><b><font size="3" face="Arial">
              GESTION DES EMPLACEMENTS :&nbsp;</font></b></font><font size="4"><b></b></font></a><font size="4"><b><A NAME="gestionReclamation">&nbsp;</a></b></font><A NAME="gestionEmplacement"><input type="radio" value="enActivite" name="etatDesEmplacements" onclick=afficherEmplacement("enActivite")>En activité&nbsp;&nbsp; 
           <input type="radio" value="termine" name="etatDesEmplacements" onclick=afficherEmplacement("termine")>Terminé&nbsp;</a>&nbsp;         
           <input type="radio" value="tous" name="etatDesEmplacements" onclick=afficherEmplacement("tous")>Tous&nbsp;<font size="4"><b><A NAME="gestionReclamation">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></b></font><font size="4"><b><A NAME="gestionReclamation">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img border="0" src="images/vert.jpg" width="15" height="15">En
          Activité&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img border="0" src="images/gris.jpg" width="15" height="15">Terminé</a></b></font>
          </td>
        </tr>
<A NAME="gestionEmplacement">

<%
if (  typeTaxeAutorise.length() != 0 )           
{
 %>  
</a>
        <tr>
          <td width="200%" height="17">
           <%=beanRole.getAffichageEmplacement()%>         
         </td>
        </tr>
<% } %>
        <tr>
        

          <td width="200%" height="17">
            <%=beanRole.getListeEmplacementRedevable()%>
         </td>
        </tr>
      </table> 
 
      
      

<A NAME="gestionFactures">      
     <BR> 
     <BR> 
     
</a> 
     
     
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="22">
        
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p style="margin-left: 5">
              <font size="3" face="Arial" color="#000000"><b>FACTURES ANNEE&nbsp;
              :</b></font>
              <input type="text" size="13" maxlength="20" name="anneeFacture"  value="<jsp:getProperty name="beanRole" property="anneeFacture" />"  >
              <b><a href="javascript:document.forms[0].submit();">Valider</a></b>
          </td>
        </tr>
      </table> 
      
     
     
<p> 
 
      
      

<A NAME="gestionFactures">      
</p>
     
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="66">
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="./images/pdfvertf.png" width="40" height="40"></p>
          </td>
        </tr>
        
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="17">
            <p style="margin-left: 5">
              <font size="3" face="Arial" color="#000000"><b>FACTURES ET RECLAMATIONS&nbsp;
              :</b></font><font size="4"><b><A NAME="gestionReclamation">&nbsp;&nbsp;</a></b></font><A NAME="gestionEmplacement"><input type="radio" value="enCours" name="etatDesFactures" onclick=afficherFacture("enCours")>En
cours</a><A NAME="gestionFactures">&nbsp;&nbsp; <input type="radio" value="payee" name="etatDesFactures" onclick=afficherFacture("paye")>
</a>Payée<A NAME="gestionFactures"></a><A NAME="gestionEmplacement">&nbsp;</a><font size="4"><b><A NAME="gestionReclamation">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img border="0" src="images/vert.jpg" width="15" height="15">En
              cours&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img border="0" src="images/rouge.jpg" width="15" height="15">Clôturée&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></b></font>
          </td>
        </tr>
        <tr>
          <td width="100%" height="19">
          <%=beanRole.getListeFactureUtilisateur()%>
          </td>
        </tr>
      </table> 
      
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="40">
        
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p style="margin-left: 5">
              <font size="3" face="Arial" color="#000000"><b>FACTURES ANNULEES :</b></font>
          </td>
        </tr>
        <tr>
          <td width="100%" height="19">
          <%=beanRole.getListeFactureAnnuleeUtilisateur()%>
          </td>
        </tr>
      </table> 
     
      
     
     
           <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="40">
        
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p style="margin-left: 5">
              <font size="3" face="Arial" color="#000000"><b>REMBOURSEMENT :</b></font>
          </td>
        </tr>
        <tr>
          <td width="100%" height="19">
          <%=beanRole.getListeRemboursementUtilisateur()%>
          </td>
        </tr>
      </table> 
     
      
     
     
<% 
if (  regie !=null && regie.equalsIgnoreCase("false")) 
{ 
%>
     
     
<A NAME="gestionPaiement">
     <BR> 
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="66">
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="./images/paiement.png" width="40" height="40"></p>
          </td>
        </tr>
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="17">
            <p style="margin-left: 5">
              <font color="#000000"><b><font size="3" face="Arial">
              GESTION DES PAIEMENTS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </font></b></font></a><A NAME="gestionEmplacement"><input type="radio" value="enCours" name="etatDesPaiement" onclick=afficherFacture("enCours")>En
cours</a><A NAME="gestionFactures">&nbsp;&nbsp; <input type="radio" value="payee" name="etatDesPaiement" onclick=afficherFacture("paye")>
</a>Payée<A NAME="gestionFactures"></a><A NAME="gestionEmplacement">&nbsp;</a> 
            </td>
        </tr>
        <tr>
          <td width="100%" height="19">
          <%=beanRole.getListePaiementFacture()%>
          </td>
        </tr>
      </table> 
	  
	  
	  
<% } %>	  
	  
	  
<% 
if (  regie !=null && regie.equalsIgnoreCase("true")) 
{ 
%>		  
	    <BR> 
     <BR> 
     <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="66">	 
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="./images/paiement.png" width="40" height="40"></p>
          </td>
        </tr>
        <tr>
          <td width="100%" bgcolor="#AFF3BB" height="17">
            <p style="margin-left: 5">
              <font color="#000000"><b><font size="3" face="Arial">
              GESTION DES PAIEMENTS </font></b></font>
              <font size="3" face="Arial" color="#000000"><b>EN COURS </b></font><font color="#000000"><b><font size="3" face="Arial">:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </font></b></font><A NAME="gestionEmplacement"><input type="radio" value="enCours" name="etatDesPaiement" onclick=afficherFacture("enCours")>En
cours</a><A NAME="gestionFactures">&nbsp;&nbsp; <input type="radio" value="payee" name="etatDesPaiement" onclick=afficherFacture("paye")>
</a>Payée<A NAME="gestionFactures"></a><A NAME="gestionEmplacement">&nbsp;</a> 
            </td>
        </tr>
        <tr>
          <td width="100%" height="19">
          <%=beanRole.getListeDesFacturesNonPayer()%>
          </td>
        </tr>
      </table> 
	  

<DIV ID="rubTableauPayement">
<table border="1" width="100%">
  
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Date de paiement</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%">
    <input maxlength="10" readOnly type="text" name="datePayement" id="datePayement" size="12" value="<%=beanRole.getDateDuJour()%>" />
            <img onclick="displayCalendar(document.forms[0].datePayement,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
    </td>
  </tr>
  
  
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Nombre de facture(s) à payer</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%">
	<input type="text" id="nombreFacture" name="nombreFacture" READONLY>	
    </td>
  </tr>
   <tr>
	<td width="20%"><b><font size="2" face="Arial">Montant total </font><font color="#FF0000" face="Arial" size="2"></font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%">
	<input type="text" readOnly name="montantTotalFactures" id="montantTotalFactures" size="20">
	</td>
  </tr>
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Type de Paiement</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%">
	<jsp:getProperty name="beanPayement" property="typePayement"/>		
  </td>
  </tr>
</table>




<DIV ID="rubPayement">  
<table border="1" width="100%">
  
  <tr>    
	
		<td width="20%"><b><font size="2" face="Arial">Montant perçu</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
		  
    <td width="80%">
	<input type="text" name="montantAPayer" id="montantAPayer" size="20">
	</td>
  </tr>
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Numéro de quittance</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%"><input type="text" maxlength=7 name="numeroQuittance"  id="numeroQuittance" size="20"></td>
  </tr>
</table>
</div>

<DIV ID="rubCB">  
<table border="1" width="100%">
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Numéro de transaction</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%"><input type="text" maxlength=20 name="numeroTransaction"  id="numeroTransaction" size="20"></td>
  </tr>
</table>
</DIV>

<DIV ID="rubBanque">  
<table border="1" width="100%">
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Numéro de chèque</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%"><input type="text" maxlength=7 name="numeroCheque" id="numeroCheque" size="20"></td>
  </tr>
  <tr>
    <td width="20%"><b><font size="2" face="Arial">Banque</font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
    <td width="80%">
	<jsp:getProperty name="beanPayement" property="banque"/>	
  </tr>
  </table>
</DIV>


<DIV ID="rubRemise">  
<table border="1" width="100%">
  <tr>    
	<td width="20%"><b><font size="2" face="Arial">Montant de la remise </font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
	<td width="80%">
	<input type="text" name="montantRemise" id="montantRemise" size="20">
	</td>
  </tr>
</table>
</DIV>
<!-- Paul 07072020 -->
<DIV ID="rubExoneration">  
<table border="1" width="100%">
  <tr>    
	<td width="20%"><b><font size="2" face="Arial">Montant de l'exonération </font><font color="#FF0000" face="Arial" size="2">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
	<td width="80%">
	<input type="text" name="montantExoneration" id="montantExoneration" size="20">
	</td>
  </tr>
</table>
</DIV>

<table border="1" width="100%">
  <tr>
    <td width="100%" colspan="2" align="center"><b><a href="javascript:saisiePayement()">Valider
      </a></b></td>
  </tr>
</table>

</div>
	  
<% } %>

<% }%>    

  
   
<script>
  
   init();  
   majAffichagePayement();
   changementTypePayement();
</script>
       

       
       
 </form>
      
<p align="center"><font face="Arial" size="3" color="#0000FF"><b><a href="./entree?action=empl_resultat_recherche_redevable.jsp">Liste
des redevables</a></b></font></p>
  <BR><BR><BR>
  	<!--paul 21/12/2021 Forcer le rechargement de la page lorsqu'on clique sur "back button" du navigateur--> 
	<!-- type=hidden ne marche avec chrome (valeur n'est pas conservée) alors utiliser css pour cacher le champs -->
	<input type="text" id="refreshed" value="no" class="hidden"> 
 <BR><BR><BR> 
 <BR><BR><BR> 
 <BR><BR><BR> 
  <BR><BR><BR> 
</body>
</html>

<script>
	chargerRaisonSocial();
</script>

































































