<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>

<script type="text/javascript" src="./resources/js/controle.js"></script>
<script type="text/javascript" src="./resources/js/jquery1.7.2.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffAutorisation" scope="request" class="fr.analogon.r2t.view.autorisation.BAffAutorisation"/>
<jsp:setProperty name="baffAutorisation" property="request" value="<%=request%>"/>
<script>

var lesEmplacements=new Array;
//variable globale
var listeBareme="";
var baremeParDefaut="";
var idEmplacement=0;

$(function(){

	//Ajouter écouteur évenement supprimer emplacement
	//le 'on' permet de définir des évenements futurs
	//Celui-ci est valable pour les versions jquery 1.7+
	/*
	* SUPPRESSION D'UN TYPE EMPLACEMENT	*
	*/
	//Définir évenement supprimer un emplacement
	$(document).on("click",".supprimer_emplacement",function(){	
		$(this).parents(".emplacement").remove();
		var old_idEmplacement;
		var idOuvrage=0;
		idEmplacement=0;
		//Pour chaque élement emplacement, on renumérote les ouvarges
		$(".emplacement").each(function(index,emplacement){
			old_idEmplacement=$(emplacement).data("idEmplacement");
			idEmplacement++;
			$(emplacement).data("idEmplacement",idEmplacement);
			//Pour chaque ouvrage dans emplacement
			$(".ouvrage",emplacement).each(function(index,ouvrage){
				idOuvrage=index+1;			
				ancien=old_idEmplacement+"_"+idOuvrage;
				nouveau=idEmplacement+"_"+idOuvrage;
				//alert(ancien+"==>"+nouveau);
				//Pour chaque element ayant attribut name, modifier attribut name et numero ouvrage
				$("*[name]",ouvrage).each(function(){
					if ($(this).attr("name")=="numero"){
						//modifier le texte portant sur l'index
						$(this).text(nouveau);		
					}
					//Maj les index
					$(this).attr("name",$(this).attr("name").replace(ancien,nouveau));					
				});
			});
		});
		//s'il n'y a plus d'emplacement, on désactive le bouton valider
		if ($(".emplacement").length==0) desactiverBoutonValider();
	});
	
	//Définir un évenement pour le lien ajouter_ouvrage
	$(document).on("click", ".ajouter_ouvrage", function(){ 
		var monEmplacement=$(this).parents(".emplacement");	
		var noeudOuvrage=$(contenu_ouvrage(monEmplacement));
		$(".conteneur_ouvrage",monEmplacement).append(noeudOuvrage);
    }); 

	//Définir évenement choix de dimension
	//Modifier l'affichage de la dimension selon le choix
	$(document).on("click", "input:radio", function(){
		var monOuvrage=$(this).parents(".ouvrage");	
		var monEmplacement=$(this).parents(".emplacement");  
		//détermine la valeur du bouton checked
		var typeDimension=$("input:radio:checked",monOuvrage).val();
		var indexOuvrage=$("[name='numero']",monOuvrage).text();
		var contenuDimension="";
		if (typeDimension=="longLarg"){
			contenuDimension= "<b>Longueur: </b><input name='longueur"+indexOuvrage+"' type='text' >&nbsp;&nbsp;<b>Largeur:</b> <input name='largeur"+indexOuvrage+"' type='text' >"; 		 
	    }
	    else{
	    	contenuDimension= "<b>Surface: </b><input name='surface"+indexOuvrage+"' type='text' >";                   
	    }
		//modifier le contenu de l'élément
		$(".dimensionArticle",monOuvrage).html(contenuDimension);
				
    }); 

	//Définir évenement changement de bareme
	$(document).on("change", ".bareme", function(){
		var monEmplacement=$(this).parents(".emplacement");  
		var monOuvrage=$(this).parents(".ouvrage");	
		changementBareme(monEmplacement,monOuvrage);
    }); 

	//activerBoutonValider();

});

function activerBoutonValider()
{
		var contenu="<font face='Arial' size='3'><b><img border='0' src='images/valider4sx.png' width='24' height='19'>"
   					+ "<a href='javascript:validerAutorisation();'>Valider l'autorisation</a></b></font>";
		$("#valider").html(contenu);  
		   
}

function desactiverBoutonValider()
{
		$("#valider").html("");		  
}

function changementBareme(monEmplacement,monOuvrage){
	var valBareme=$(":checked",monOuvrage).val();
	var typeTaxe=monEmplacement.data("typeTaxe");
	var typeDimension=$("input:radio:checked",monOuvrage).val();
	var indexOuvrage=$("*[name='numero']",monOuvrage).text();
	var contenuChoix="";    
	var contenuDimension=""; 
	var contenuTypeOuvrage="";
	var contenuNombreFace="";

	if ( typeTaxe  == "TLPE" )
	{	    
		contenuTypeOuvrage="<select name='typeOuvrage"+indexOuvrage+"'><option value='Normal' Selected >Normal</option><option value='Numerique'>Numérique</option></select>";
		contenuNombreFace="<input name='nombreFace"+indexOuvrage+"' maxlength='10' type='text' size='33'/>"
	}
   
	if(valBareme.indexOf("FORFAIT")>0 || valBareme.indexOf("UNITE/")>0)
	{	 	 
		contenuChoix = "-"; 
		contenuDimension = "-";
		
		if ( typeTaxe  == "TLPE" )
		   {
				contenuTypeOuvrage = "typeOuvrage";	
				contenuNombreFace = "-";	
		   }	
	}

	if(valBareme.indexOf("ML/")>0)
	{
		contenuChoix = "-"; 
		contenuDimension="<b>Longueur: </b><input name='longueur"+indexOuvrage+"' type='text' > ";	     
	}	   
		
	if(valBareme.indexOf("M2/")>0)
	{   
	    contenuChoix="<input  name='longSuraface'  type='radio'  value='longLarg' checked >";
	    contenuChoix=contenuChoix+'Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface';
	    contenuChoix=contenuChoix+"<input name='longSuraface' type='radio' value='surf' >";	           			       
        contenuDimension= "<b>Longueur: </b><input name='longueur"+indexOuvrage+"' type='text' >";                   
     	contenuDimension= contenuDimension+"<b>     Largeur:</b> <input name='largeur"+indexOuvrage+"' type='text'   >";   
 
	}

	//Mise à jour les éléments de l'arbre
	$(".typeOuvrage",monOuvrage).html(contenuTypeOuvrage);
	$(".nombreFace",monOuvrage).html(contenuNombreFace);
	$(".choixDimension",monOuvrage).html(contenuChoix);
	$(".dimensionArticle",monOuvrage).html(contenuDimension);
	
}

function changementTaxe()
{
 
  var typeTaxe= document.forms[0].typeDeTaxe[document.forms[0].typeDeTaxe.selectedIndex].text; 

  rechercheListBareme(typeTaxe,document.forms[0].anneeExercice.value);
}

function ajouterEmplacement(typeTaxe)
{
	if (listeBareme==""){
		alert("***Pas de bareme disponible pour l'annnée  " + document.forms[0].anneeExercice.value);  
		return;
	}

	var pos = document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(document.forms[0].typeDeTaxe.value);
	if (pos <0){
		alert("Vous n'avez pas les droits d'exercer pour le type "+ document.forms[0].typeTaxe.value);
		return;
	}
	
	verfiNumRedevable= verifier("Numero redevable",document.forms[0].numRedevable.value);   
 	verfiNumRue= verifier("Numero Voie",document.forms[0].numRue.value);
   	verfiCodeVoie= verifier("Code Voie",document.forms[0].codeVoie.value);
   	verfiAdresseEmplacement= verifier("Adresse emplacement",document.forms[0].adresseEmplacement.value); 
   	verfiCP= verifier("Code postal",document.forms[0].cp.value);
   	verfiVille= verifier("Ville",document.forms[0].ville.value);

   	if(verfiNumRedevable!=0 || verfiNumRue !=0 || verfiAdresseEmplacement !=0  || verfiCodeVoie!=0  || verfiCP !=0  || verfiVille!=0) return; 
	
	//Créer noeud élément emplacement
	idEmplacement++;
	var noeudEmplacement = $(contenu_emplacement(typeTaxe));
	//sauvegarder les données liées au noeud emplacement
	noeudEmplacement.data("bareme",listeBareme)
					.data("baremeParDefaut",baremeParDefaut)
					.data("typeTaxe",typeTaxe)
					.data("idEmplacement",idEmplacement)
					.data("idOuvrage",0);
	//Ajouter noeud élément emplacement au conteneur emplacement
	$("#conteneur_emplacement").append(noeudEmplacement);
	activerBoutonValider();
}

function contenu_emplacement(typeTaxe){
	var leContenu="";
	var ide=''+idEmplacement;
	leContenu=leContenu+"<table border=1 width=100% class='emplacement'>";
	leContenu=leContenu+"<tr>";
	leContenu=leContenu+"<td width='20%'><font color='#FF0000'><b><p align=center>"+typeTaxe+"</p></b></font>";
	leContenu=leContenu+"<input type='hidden' value='*newEmplacement*' name='newEmplacement'>";
	leContenu=leContenu+"<input type='hidden' name='typeTaxe' value='"+typeTaxe+"'>";
	leContenu=leContenu+"<a href='#' class='ajouter_ouvrage'><p align=center>Ajouter un ouvrage</p></a>";
	leContenu=leContenu+"<a href='#' class='supprimer_emplacement'><p align=center>Supprimer ce type emplacement et ouvrages</p></a>";
	leContenu=leContenu+"</td>";
	leContenu=leContenu+"<td width='80%'><div class='conteneur_ouvrage'></div></td>";
	leContenu=leContenu+"</tr>";
	leContenu=leContenu+"</table>";
	
	return leContenu;
}


function contenu_ouvrage(noeudEmplacement)
{
  var leContenu="";
  var typeTaxe=noeudEmplacement.data("typeTaxe");
  var baremeParDefaut=noeudEmplacement.data("baremeParDefaut");
  var idEmplacement=noeudEmplacement.data("idEmplacement");
  var idOuvrage=noeudEmplacement.data("idOuvrage")+1;
  //Maj index ouvrage
  noeudEmplacement.data("idOuvrage",idOuvrage);
  //alert('indexemplacement='+idEmplacement+": indexouvrage="+idOuvrage);
  var indexOuvrage=''+idEmplacement+'_'+idOuvrage;  
  leContenu=leContenu+"<table border='1' class='ouvrage' width='100%' height='221'>";
  leContenu=leContenu+"<tr>";
 

  leContenu=leContenu+"<td width='23%' height='23'><b><font color='#0000FF'>Ouvrage Numéro : </font></b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<span name='numero'>"+indexOuvrage+"<span>";
  leContenu=leContenu+"<input type='hidden' value='*newOuvrage*' name='separateurOuvrage'>" ;
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='19'><b>TypeBareme <font color='#FF0000'>*</font>:</b></td>";
  leContenu=leContenu+"<td width='77%' height='19'>"+noeudEmplacement.data("bareme")+"</td>";
  leContenu=leContenu+"</tr>";    
  
  leContenu=leContenu+"<tr>";
  leContenu=leContenu+"<td width='23%' height='23'><b>Nom ouvrage <font color='#FF0000'></font>:";
  leContenu=leContenu+"</b></td>";
  leContenu=leContenu+"<td width='77%' height='23'>";
  leContenu=leContenu+"<input name='nomOuvrage"+indexOuvrage+"' type='text' size='33'/>";
  leContenu=leContenu+"</td>";
  leContenu=leContenu+"</tr>";
  leContenu=leContenu+"<tr>";
	  

 leContenu=leContenu+"<div class='ouvrageDimension' >"+contenuOuvrageDimension(typeTaxe,baremeParDefaut,indexOuvrage)+"</div>";
  
 leContenu=leContenu+"<tr>";
 leContenu=leContenu+"<td width='23%' height='23'><b>Quantité <font color='#FF0000'>*</font>:";
 leContenu=leContenu+"</b></td>";
 leContenu=leContenu+"<td width='77%' height='23'>";
 leContenu=leContenu+"<input name='quantite"+indexOuvrage+ "' type='text' size='33'/>";
 leContenu=leContenu+"</td>";
 leContenu=leContenu+"</tr>";
 leContenu=leContenu+"<tr>";
 
 leContenu=leContenu+"<tr>";
 leContenu=leContenu+"<td width='23%' height='23'><b>Date d’application de l’ouvrage <font color='#FF0000'>*</font>:";
 leContenu=leContenu+"</b></td>";
 leContenu=leContenu+"<td width='77%' height='23'>";
 leContenu=leContenu+"<input name='dateDebut"+indexOuvrage+"' maxlength='10' type='text' id='dateDebut"+indexOuvrage+"' size='33'/>";
 leContenu=leContenu+"<img onclick=displayCalendar(document.forms[0].dateDebut"+indexOuvrage+",'dd/mm/yyyy',this)"; 
 leContenu=leContenu+" src='./images/calendar.gif' border=0 width=16 height=16>"; 
 leContenu=leContenu+"</td>";
 leContenu=leContenu+"</tr>"; 
 
 leContenu=leContenu+"<tr>";
 leContenu=leContenu+"<td width='23%' height='23'><b>Date de fin de la période taxée :";
 leContenu=leContenu+"</b></td>";
 leContenu=leContenu+"<td width='77%' height='23'>";
 leContenu=leContenu+"<input name='dateFin"+indexOuvrage+"' maxlength='10' type='text' id='dateFin"+indexOuvrage+"' size='33'/>";
 leContenu=leContenu+"<img onclick=displayCalendar(document.forms[0].dateFin"+indexOuvrage+",'dd/mm/yyyy',this)"; 
 leContenu=leContenu+" src='./images/calendar.gif' border=0 width=16 height=16>";    
 leContenu=leContenu+"</td>";
 leContenu=leContenu+"</tr>";

 leContenu=leContenu+"<tr>";
 leContenu=leContenu+"<td width='100%' colspan='2' bgcolor='#EAEAEA' height='20'>&nbsp;</td>";
 leContenu=leContenu+"</tr>";
    
 leContenu=leContenu+"</table>";

 return leContenu;
       	   
}


function contenuOuvrageDimension(typeTaxe,valBareme,indexOuvrage){
	var leContenu="";
	var contenuChoix="";    
	var contenuDimension=""; 
	var contenuTypeOuvrage="";
	var contenuNombreFace="";

	if ( typeTaxe  == "TLPE" )
	{	    
		contenuTypeOuvrage="<select name='typeOuvrage"+indexOuvrage+"'><option value='Normal' Slected >Normal</option><option value='Numerique'>Numérique</option></select>";
		contenuNombreFace="<input name='nombreFace"+indexOuvrage+"' maxlength='10' type='text' size='33'/>"
	}
   
	if(valBareme.indexOf("FORFAIT")>0 || valBareme.indexOf("UNITE/")>0)
	{	 	 
		contenuChoix = "-"; 
		contenuDimension = "-";
		
		if ( typeTaxe  == "TLPE" )
		   {
				contenuTypeOuvrage = "typeOuvrage";	
				contenuNombreFace = "-";	
		   }	
	}
	
	if(valBareme.indexOf("ML/")>0)
	{
		contenuChoix = "-"; 
		contenuDimension="<b>Longueur: </b><input name='longueur"+indexOuvrage+"' type='text' > ";	     
	}	   
		
	if(valBareme.indexOf("M2/")>0)
	{   
	    contenuChoix="<input  name='longSuraface'  type='radio'  value='longLarg' checked >";
	    contenuChoix=contenuChoix+'Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface';
	    contenuChoix=contenuChoix+"<input name='longSuraface' type='radio' value='surf' >";	           			       
        contenuDimension= "<b>Longueur: </b><input name='longueur"+indexOuvrage+"' type='text' >";                   
     	contenuDimension= contenuDimension+"&nbsp;&nbsp;<b>Largeur:</b> <input name='largeur"+indexOuvrage+"' type='text'   >";   
 
	}

	if ( typeTaxe  == "TLPE" )
	  {
	    
		leContenu=leContenu+"<tr>";
		leContenu=leContenu+"<td width='23%' height='23'><b>Type ouvrage<font color='#FF0000'>*</font>:";
		leContenu=leContenu+"</b></td>";
		leContenu=leContenu+"<td width='77%' height='23'>";
		leContenu=leContenu+"<div class='typeOuvrage'>"+contenuTypeOuvrage+"</div>";
		leContenu=leContenu+"</td>";
		leContenu=leContenu+"</tr>";  
		  
		leContenu=leContenu+"<tr>";
		leContenu=leContenu+"<td width='23%' height='23'><b>Nombre d'affiche/face<font color='#FF0000'>*</font>:";
		leContenu=leContenu+"</b></td>";
		leContenu=leContenu+"<td width='77%' height='23'>";
		leContenu=leContenu+"<div class='nombreFace'>"+contenuNombreFace+"</div>";
		leContenu=leContenu+"</td>";
		leContenu=leContenu+"</tr>";
	}
	
    leContenu=leContenu+"<tr>";
    leContenu=leContenu+"<td width='23%' height='23'><b>Choix <font color='#FF0000'>*</font>:</b></td>";
    leContenu=leContenu+"<td width='77%' height='23'>";
    leContenu=leContenu+"<div class='choixDimension'>"+contenuChoix+"</div>";
    leContenu=leContenu+"</td>";
    leContenu=leContenu+"</tr>";
     
    
    leContenu=leContenu+"<tr>";
    leContenu=leContenu+"<td width='23%' height='23'><b>Dimension <font color='#FF0000'>*</font>:</b></td>";
    leContenu=leContenu+"<td width='77%' height='23'>";
    leContenu=leContenu+"<div class='dimensionArticle'>"+contenuDimension+"</div>";
    leContenu=leContenu+"</td>";
    leContenu=leContenu+"</tr>";	
	       
	return leContenu;
}


function controleChamps()
{
    var saisieOk = 0;

    $(".emplacement").each(function(){
        if ($(".ouvrage",this).length==0){
        	 alert("Vous devez saisir au moins un ouvrage pour le type "+$(this).data("typeTaxe"));
		     saisieOk = -1;
        }
        
    	$(".ouvrage",this).each(function(i,ouvrage){
    		var typeTaxe=$(this).parents(".emplacement").data("typeTaxe");
    		var indexOuvrage=$("[name='numero']",this).text();
    		var dd= $("[name*='dateDebut']",this).val();
    		var df= $("[name*='dateFin']",this).val();				   
    	   	var quantite= $("[name*='quantite']",this).val();
    	   	var nomOuvrage= $("[name*='nomOuvrage']",this).val();	   
    	   	var surface= $("[name*='surface']",this).val();		 
    	   	var longueur= $("[name*='longueur']",this).val();			 
    	   	var largeur= $("[name*='largeur']",this).val();	 	   		   
    	   	var nombreFace= $("[name*='nombreFace']",this).val();			 
    	   	var typeOuvrage= $("[name*='typeOuvrage']",this).val();

    	   	if(dd.length!=0)
    		   {
    		      retourVer1 = verif_date(dd);			  
    		      if (!retourVer1) 
    			  {
    			     alert("La date d’'application de l'’ouvrage N° "+ indexOuvrage  +" du type "+ typeTaxe +" doit être de la forme JJ/MM/AAAA");
    			     saisieOk = -1;
    			  }
    		   }
    		   else
    		   {
    		      saisieOk = -1;
    		      alert("La date de debut de l'ouvrage N° "+ indexOuvrage  +" du type "+ typeTaxe +" doit etre rempli!");
    		   }
    		   
    		   
    		   if(df.length!=0)
    		   {
    		      retourVer2 = verif_date(df);  
    			  
    		      if (!retourVer2) 
    			  { 
    			     alert("La date Date de fin de la période taxée de l’ouvrage N° "+ indexOuvrage  +" du type "+ typeTaxe +" doit être de la forme JJ/MM/AAAA");
    				 saisieOk = -1;
    			  }
    		   }
    		   
    		   if(quantite.length==0)
    		   {		  
    		      saisieOk = -1;
    		      alert("Le champs quantité de l'ouvrage N° "+ indexOuvrage +" du type "+ typeTaxe +" doit etre rempli!");
    		   }
    		   
    		   
    		   //test si champs existe
    		   if ($("[name*='surface']",this).length>0 && surface.length==0)
    		   {		  
    		      saisieOk = -1;
    		      alert("Le champs surface de l'ouvrage N° "+ indexOuvrage +" du type "+ typeTaxe +" doit etre rempli!");
    		   }
    		   
    		   if ($("[name*='longueur']",this).length>0 && longueur.length==0)
    		   {		  
    		      saisieOk = -1;
    		      alert("Le champs longueur de l'ouvrage N° "+ indexOuvrage +" du type "+ typeTaxe +" doit etre rempli!");
    		   }

    		   if ($("[name*='largeur']",this).length>0 && largeur.length==0)
    		   {		  
    		      saisieOk = -1;
    		      alert("Le champs largeur de l'ouvrage N° "+ indexOuvrage +" du type "+ typeTaxe +" doit etre rempli!");
    		   }
    		   
    		   if ( typeTaxe  == "TLPE"  )
    		   {
    			   if(nombreFace.length==0)
    			   {		  
    				  saisieOk = -1;
    				  alert("Le champs nombreFace de l'ouvrage N° "+ indexOuvrage +" du type "+ typeTaxe +" doit etre rempli!");
    			   }
    		   } 
    	}); //fin each ouvrage

    }); //fin each emplacement

	return saisieOk;

}

function validerAutorisation()
{
  var saisieOk = controleChamps();
  if (saisieOk == 0) 
  {
   //Générer le queryString avec les champs du formulaire (séparé par des &)
   var str = $("form").serialize();
   document.forms[0].action='./gestionAutorisation?'+str;  
   document.forms[0].submit(); 
   }
 else	
	{
	    alert("Veuillez saisir tous les champs nécessaires");
	}
}


function rechercheRedevable(numRedevable,nomRedevable,adresseRedevable){
	$.ajax( {
        type: "GET",
        url: "rechercheRedevable",
        data: "numRedevable="+numRedevable+"&nomRedevable="+nomRedevable+"&adresseRedevable="+adresseRedevable,
        dataType: "xml",
        success: function(xml) {
        	processRequestRedevable(xml);
            },
        error: function(xhr, textStatus, thrownError) {
        	alert("Erreur chargement page \n"+ textStatus +":"+thrownError);
          }
    }
  );       
}

function processRequestRedevable(xmldoc)
{  
	var node=$(xmldoc).find("redevable");
	var contenuListRedevable="<b><font face='Arial' size='2'>Redevable: </font></b>";
    contenuListRedevable+=" <select id='listDesRedevable' onchange='majLienRedevable(this.value)' >";
	if ( node.length!=0 ) {
		node.each(function(){
				element= $(this).find("raisonSocialeRedevable").text() + "." 
					    + $(this).find("nomPrenomRedevable").text() + "---" 
					    + $(this).find("numRedevable").text() + "---" 
					    + $(this).find("adresse").text();
				contenuListRedevable+="<option value='"+element+"'>"+element+"</option>";  
			  });
    	  contenuListRedevable+="</select>";
	}
	else
    {
       contenuListRedevable="PAS DE RESULTATS";
       $("#lienRedevable").html("");              
    }

	$("#listeRedevable").html(contenuListRedevable);  
} 


function majLienRedevable(adresse)
{
	//alert(adresse);
	var reg=new RegExp("---");
	var tableau=adresse.split(reg);
	var nomPrenomRedevable= tableau[0];
	var numRedevable= tableau[1];	  
	var tableau2=nomPrenomRedevable.split(".");
	var link ="./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role";
		link=link +"&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable="+numRedevable;
		contenuLienRedevable="<font face='Arial' color='#0000FF' size='2' >";
		contenuLienRedevable = contenuLienRedevable +" Redevable : <a href="+ link+" >";
		contenuLienRedevable = contenuLienRedevable + " Lien vers le redevable "  + nomPrenomRedevable ;
		contenuLienRedevable = contenuLienRedevable + " numero : "+ numRedevable +"      </a></font></b>";

	$("#lienRedevable").html(contenuLienRedevable);	
	document.forms[0].nomRedevable.value = tableau2[1];
	document.forms[0].numRedevable.value=numRedevable;				

}

function rechercheVoie(nomRueRecherche){
	$.ajax( {
        type: "GET",
        url: "rechercheRue",
        data: "nomRueRecherche=" + nomRueRecherche,
        dataType: "xml",
        success: function(xml) {
        	processRequestRue(xml);
        },
        error: function(xhr, textStatus, thrownError) {
        	alert("Erreur chargement page \n"+ textStatus +":"+thrownError);
        }
    }
  );       
}

function processRequestRue(xmldoc){
	
	var node=$(xmldoc).find("rue");
	var contenuListRue="<select name='listDesRues' onChange=majChampAdresse(this.value)>";
	
	if (node.length!=0){
  	 	node.each(function(){
			element=  $(this).find("code").text() + "---" 
				    + $(this).find("adresse").text() + "---" 
				    + $(this).find("quartier").text();
			contenuListRue+="<option value='"+element+"'>"+element+"</option>";  
		  }); 
  	  contenuListRue+="</select>";
    }
    else
    {     
  	  contenuListRue="PAS DE RESULTATS";             
    }

	$("#listeRue").html(contenuListRue);

}

function majChampAdresse(adresse)
{  
  var reg=new RegExp("---");
  var tableau=adresse.split(reg);
  document.forms[0].codeVoie.value=tableau[0];
  document.forms[0].adresseEmplacement.value=tableau[1];
}


function rechercheListBareme(typeTaxe,anneeExercice){
	$.ajax( {
        type: "GET",
        url: "rechercheBareme",
        data: "typeTaxe="+typeTaxe+"&anneeExercice="+anneeExercice,
        dataType: "xml",
        success: function(xml) {
        	console.log(xml);
        	processRequestListBareme(xml);
        },
        error: function(xhr, textStatus, thrownError) {
        	alert("Erreur chargement page \n"+ textStatus +":"+thrownError);
        }
    }
  );       
	
}


function processRequestListBareme(xmldoc)
{
	var node=$(xmldoc).find("ligne");
	if (node.length!=0){
   	 var contenu="<select name='typeBareme' class='bareme'>";
      	  node.each(function(index){
				element= $(this).text();
				if (index==0) baremeParDefaut=element;
				contenu+="<option value='"+element+"'>"+element+"</option>";  
			  });
      	  contenu+="</select>";
      	  listeBareme=contenu;
    }
    else       
    {
       alert("Pas de bareme disponible pour l'annnée  " + document.forms[0].anneeExercice.value);  
     
    }          
} 

</script>

<body background="./images/nuages.jpg" topmargin="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Autorisations</font></td>
  </tr>
</table>
<form name="formulaire" >
<%
  String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");     
%>
<input type="hidden" name="listeLibelleDesTypesDeTaxeAutorise" value="<%=listeLibelleDesTypesDeTaxeAutorise%>">
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="anneeExercice" value="<%=baffAutorisation.getAnneeExercice() %>"/>
<input type="hidden"  name="codeVoie" size="33"/>

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center">
 <tr>
     <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/autoris01.jpg" width="40" height="40"></b></font>
     </td>
 </tr>  
 <tr>
     <td  colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left"><font size="4"><b>CREER UNE AUTORISATION :</b></font></p>
     </td> 
 </tr>
 <tr>
     <td width="20%" background="./images/fond_bleu.gif">
    		<b>&nbsp;Autorisation :</b>
     </td>
     <td>
	       <table border="1" width="100%">
	            <tr>
					<td width="23%"><b>Ref dossier :</b></td>
					<td width="77%"><input id="refDossier" type="text" name ="refDossier" ></td>
	            </tr>
	        </table>
	 </td>
 </tr>
 <tr>
      <td height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Information sur le redevable :</b></font></td>
 </tr>
 <tr>
	  <td width="20%" background="./images/fond_bleu.gif">
    		<b>&nbsp;Redevable</b><b><font color="#FF0000">*</font></b><b> :</b>
      </td>
      <td>
          <table border="1" width="100%">
            <tr>
				<td width="23%"><b>Numéro du redevable</b><font color="#FF0000">*</font><b> :</b></td>
				<td width="77%"><input  id="numRedevable" type="text"  name="numRedevable" size="33"/></td>
            </tr>
            <tr>
				<td width="23%"><b>Nom du redevable :</b></td>
				<td width="77%"><input  type="text"  name="nomRedevable" size="33"/></td>
 			</tr>
 			<tr>
				<td width="23%"><b>Adresse :</b></td>
				<td width="77%"><input  type="text"  name="adresseRedevable" size="33"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font face="Arial" size="3"><a href="javascript:rechercheRedevable(document.forms[0].numRedevable.value,document.forms[0].nomRedevable.value,document.forms[0].adresseRedevable.value )" >
					<img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0"><b>echercher Redevable&nbsp;</b></a></font>       
				</td>       
            </tr>
            <tr>
				<td colspan="2"><div id="listeRedevable" style="width: 100%; height:20;"></div></td>
            </tr>
            <tr>
				<td colspan="2"><div id="lienRedevable" style="width: 100%; height:20;"></div></td>
            </tr>
          </table>
        </td>
</tr>
<!-- ADRESSE -->
 <tr>
       <td height="19" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>&nbsp;Information sur l'emplacement :</b></font></td>
 </tr>
 <tr>
       <td width="20%" background="./images/fond_bleu.gif"><b>&nbsp;Adresse autorisée</b><b><font color="#FF0000">*</font></b><b> :</b></td>
       <td>
	       <table border="1" width="100%">
	            <tr>
					<td width="23%"><b>Numéro de voie</b><font color="#FF0000">*</font><b> :</b></td>
					<td width="77%"><input id="numRue" type="text" name ="numRue" ></td>
	            </tr>
	            <tr>
					<td width="23%"><b>Compl :</b></td>
					<td width="77%"><jsp:getProperty name="baffAutorisation"  property="complNumRue" /></td> 
	 			</tr>
	 			<tr>
					<td width="23%"><b>Adresse</b><font color="#FF0000">*</font><b> :</b></td>
					<td width="77%"><input  type="text"  name="adresseEmplacement" size="33"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font face="Arial" size="3"><a href="javascript:rechercheVoie(document.forms[0].adresseEmplacement.value)">
						<img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0"><b>echercher Adresse </b></a></font>     
					</td>				       
	            </tr>
	            <tr>
			        <td width="23%"><b>compl. Adresse :</b></td>
					<td width="77%"><input  type="text"  name="adresse2Emplacement" size="33"/></td>
	            </tr>
	 			<tr>
					<td width="23%"><b>compl. Adresse :</b></td>
					<td width="77%"><input  type="text"  name="adresse3Emplacement" size="33"/> </td> 
            	</tr>
            	 <tr>
			        <td width="23%"><b>Code postal</b><font color="#FF0000">*</font><b> :</b></td>
					<td width="77%"><input  type="text"  name="cp" size="13" value="<%=baffAutorisation.getCpMairie() %>"/></td>
	            </tr>
	 			<tr>
					<td width="23%"><b>Ville</b><font color="#FF0000">*</font><b> :</b></td>
					<td width="77%"><input  type="text"  name="ville" size="33" value="<%=baffAutorisation.getVilleMairie() %>"/> </td> 
            	</tr>
            	<tr>
            		<td colspan="2">
						<div id="listeRue" style="width: 100%;height:20;"></div>
					</td>
            	</tr>
 			</table>
 		</td>
 </tr>
<tr height="40">
		<td width="20%" align="center" background="./images/fond_bleu.gif"><font face="Arial" size="3">
		<b><img border="0" src="images/ajouter.jpg" width="24" height="19">
		<a href="javascript:ajouterEmplacement(document.forms[0].typeDeTaxe.value);">Ajouter un type emplacement</a>
		</b></font>
		</td>
		<td>
	       <table border="1" width="100%">
	            <tr>
					<td width="23%"><b>Type de taxe</b><font color="#FF0000">*</font><b> :</b></td>
					<td width="77%"><jsp:getProperty name="baffAutorisation" property="tousTypeDeTaxe" /></td> <!-- émet evenement changementTaxe() -->
	            </tr>
	       </table>
		<td>
</tr>
</table>
<p>
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center">
 <tr>
        <td width="100%" height="20" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>
        &nbsp;Liste des emplacements et des ouvrages autorisés :</b></font>
        </td>
 </tr>
 <tr>
       <td width="100%" height="38" colspan="2"><div id="conteneur_emplacement"></div></td>
 </tr>
 <tr>
       <td width="100%" height="20" colspan="2"><div id="infos"></div></td>
 </tr>
  <tr>
       <td width="100%" height="30" colspan="2" align="center">
         <div id ="valider"></div>
       </td>
 </tr>
       
</table>

</body>

