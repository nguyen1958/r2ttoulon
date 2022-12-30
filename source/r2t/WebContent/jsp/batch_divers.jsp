<html>
<head>

<title>R2T - Batch Divers - Analogon</title>
</head>
  <script type="text/javascript" src="./resources/js/Calendar.js"></script>
  <script type="text/javascript" src="./resources/js/controle.js"></script>  
<body background="./images/nuages.jpg" topmargin="0">


<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.view.batch.BAffDivers" />
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
<script>
//Paul 31/10/2017
//Après chargement du document <==> $document(ready)
//argument passé depuis alerte redevable
//Si numéro!="" alors sélectionner automatiquement 
var numero="";
document.addEventListener('DOMContentLoaded', function () {
    numero=document.forms[0].numEmplacement.value;
    if(numero.length!=0) {
    	rechercheEmplacement(numero,"","");
    	document.forms[0].redirectTo.value="empl_resultat_recherche_alerte.jsp&numRedevable=-1&alerte=1";
    }
});


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
  document.forms[0].codeVoixRedevable.value=tableau[0];
  document.forms[0].adressRedevable.value=tableau[1];
}


function rechercheVoie(nomRueRecherche)
{
        //alert(nomRueRecherche);
        if(nomRueRecherche.length!=0)
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
          alert("Entrez au moins 1 caractere pour rechercher des rues")
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












function rechercheRedevableAChangerAdresse(numRedevableAChangerAdresse,nomRedevableAChangerAdresse,adresseRedevableAChangerAdresse)
{
        //alert(numRedevableAChangerAdresse);        
        var url = './rechercheRedevable?numRedevable='+numRedevableAChangerAdresse+"&nomRedevable="+nomRedevableAChangerAdresse+"&adresseRedevable="+adresseRedevableAChangerAdresse;    
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestRedevableAChangerAdresse(); } ;
        httpRequest.send(null); 
}  



function processRequestRedevableAChangerAdresse()
{       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("redevable");
              var contenuListRedevableAChangerAdresse="<b><font face='Arial' size='2'>Choix de l'Redevable  :  </font></b>";
              contenuListRedevableAChangerAdresse=" "+contenuListRedevableAChangerAdresse+"<select name='listDesRedevableAChangerAdresse' onclick='majLienRedevableAChangerAdresse(this.value)' >";
              //alert(node.length);
				  for (i = 0; i < node.length; i++)
				  {				                              
                 var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue; 
                 var numRedevableAChangerAdresse= xmldoc.getElementsByTagName("numRedevable")[i].childNodes[0].nodeValue; 
                 element= numRedevableAChangerAdresse+"---"+adresse;
                  contenuListRedevableAChangerAdresse=contenuListRedevableAChangerAdresse+"<option value="+element+">"+element+"</option>";
               }             	                           

              if (node.length==0)           
              {
                 contenuListRedevableAChangerAdresse=contenuListRedevableAChangerAdresse+"<option>    PAS DE RESULTATS    </option>"; 
                 document.getElementById("lienRedevableAChangerAdresse").innerHTML = "";
                
              }
               contenuListRedevableAChangerAdresse=contenuListRedevableAChangerAdresse+"</select>"; 
               document.getElementById("listeRedevableAChangerAdresse").innerHTML = contenuListRedevableAChangerAdresse;  
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 




function majLienRedevableAChangerAdresse(adresse)
{
var reg=new RegExp("---");
var tableau=adresse.split(reg);
var numRedevableAChangerAdresse= tableau[0];
var link ="./entree?action=empl_gestion_Redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role";
link=link +"&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable="+numRedevableAChangerAdresse;
//alert(link);
contenuLienRedevableAChangerAdresse="<font face='Arial' color='#0000FF' size='2' >";
contenuLienRedevableAChangerAdresse = contenuLienRedevableAChangerAdresse +" Redevable qui a change d'adresse: <a href="+ link+" > Lien vers le redevable numéro : "+ numRedevableAChangerAdresse +"      </a></font></b>";
res= adresse.indexOf("PAS", 0);

if(res !=0)
{
	document.getElementById("lienRedevableAChangerAdresse").innerHTML = contenuLienRedevableAChangerAdresse;
	document.forms[0].numRedevableAChangerAdresse.value=numRedevableAChangerAdresse;
}
else
{
	document.getElementById("lienRedevableAChangerAdresse").innerHTML = "";
		document.forms[0].numRedevableAChangerAdresse.value="";
}

if (document.forms[0].numRedevableAChangerAdresse.value != "" )
{
  //alert("activer le trenasfert");
  //contenuTransfert= "<p align='center'>";
  //contenuTransfert= contenuTransfert+"<a href='javascript:transfertEmplacement();' target='bas'><font face='Arial' size='3'>";
  //contenuTransfert= contenuTransfert+"<img height='20' src='images/lettre_v.gif' width='20' align='absMiddle' border='0'>";
  //contenuTransfert= contenuTransfert+"<b>alider le transfert de l'emplacement numero "+document.forms[0].numEmplacementATransferer.value ;
  //contenuTransfert= contenuTransfert+ " vers le RedevableAChangerAdresse numero " +document.forms[0].numNewRedevableAChangerAdresse.value ;
  //contenuTransfert= contenuTransfert+"</b></font></a>";
  //contenuTransfert= contenuTransfert+"</p>";
  //document.getElementById("changerAdresseRedevable").innerHTML = contenuTransfert; 
}
else
{
 document.getElementById("changerAdresseRedevable").innerHTML = ""; 

}

}






function basculerTLPE()
{
  ret=confirm("Etes-vous sûr de vouloir faire la bascule de tous les emplacements actifs de type TLPE ?");
  if (ret == true)
  {
    document.forms[0].choix.value="basculerTLPE";
    document.forms[0].action="basculerTLPE";
    document.forms[0].submit();
  }
}


function basculerUneTaxe()
{
  var  typeDeTaxe = document.getElementById("typeDeTaxe").value ;
  //alert(typeDeTaxe );
  ret=confirm("Etes-vous sûr de vouloir faire la bascule de la taxe "+typeDeTaxe +" ?");
  if (ret == true)
  {
    document.forms[0].choix.value="basculerUneTaxe="+typeDeTaxe ;
    document.forms[0].action="basculerUneTaxe";
    document.forms[0].submit();
  }
}


  
function basculerLesTav()
{
  ret=confirm("Etes-vous sûr de vouloir faire la bascule de tous les TAV ?");
  if (ret == true)
  {
    document.forms[0].choix.value="basculerLesTav";
    document.forms[0].action="basculerLesTav";
    document.forms[0].submit();
  }
}
    
function basculerLesEmplacmentEtLesBaremes()
{           
   ret="false";
   verfiPourcentage= verifier("Pourcentage",document.forms[0].pourcentage.value); 
   if(isNaN(document.forms[0].pourcentage.value))
   {
     alert("Le poucentage doit etre un nombre");
     verfiPourcentage =-1;
   }
   
   if (verfiPourcentage==0)
   {
     ret=confirm("Etes-vous sûr de vouloir basculer tous les baremes de l'année "  + document.forms[0].anneeExercice.value + " à l'année " + document.forms[0].anneeExerciceSuivante.value + " ?" );      
     if (ret == true )
     {
      document.forms[0].action="basculerImputationBareme";
      document.forms[0].submit();
    }
   }
   
   
} 
    
    

function transfertEmplacement()
{

   ret=confirm("Etes-vous sûr de vouloir transferer l'emplacement N "+document.forms[0].numEmplacementATransferer.value+" pour le redevable N "+ document.forms[0].numNewRedevable.value+"?");
            if (ret == true)
            {              
              document.forms[0].action="gestionTransfertEmplacement";
	           document.forms[0].submit();
	         }
}

function majLienEmplacement(adresse)
{
var reg=new RegExp("---");
var tableau=adresse.split(reg);
var numEmpalcement= tableau[1];
var link ="";
var res="ko";
var pos=adresse.indexOf("ko---");

if (pos>=0)
{
	alert("Cet emplacement a ete facture et la facture est valide, pas possible de le transferer");
	//contenuLienEmplacement = "Cet emplacement a ete facture et la facture est valide, pas possible de le transferer";
	res= 0;
}
else
{
	link ="./entree?action=empl_gestion_emplacementodp.jsp&choix=modifier";
	link=link+"&optionTransfert=role&origine=origine&numEmplacment="+numEmpalcement;
	contenuLienEmplacement="<font face='Arial' color='#0000FF' size='2' >";
	contenuLienEmplacement = contenuLienEmplacement +"Emplacement : <a href="+ link+" > Lien vers l'emplacement numero : "+ numEmpalcement +"      </a></font></b>";
	res= adresse.indexOf("PAS", 0);
}	

if(res !=0)
{
	document.getElementById("lienEmplacement").innerHTML = contenuLienEmplacement;
	document.forms[0].numEmplacementATransferer.value=numEmpalcement;
}
else
{
	document.getElementById("lienEmplacement").innerHTML = "";
	document.forms[0].numEmplacementATransferer.value="";
}


if (document.forms[0].numEmplacementATransferer.value != "" && document.forms[0].numNewRedevable.value != "" )
{
  //alert("activer le trenasfert");
  contenuTransfert= "<p align='center'>";
  contenuTransfert= contenuTransfert+"<a href='javascript:transfertEmplacement();' target='bas'><font face='Arial' size='3'>";
  contenuTransfert= contenuTransfert+"<img height='20' src='images/lettre_v.gif' width='20' align='absMiddle' border='0'>";
  contenuTransfert= contenuTransfert+"<b>alider le transfert de l'emplacement numero "+document.forms[0].numEmplacementATransferer.value ;
  contenuTransfert= contenuTransfert+ " vers le redevable numero " +document.forms[0].numNewRedevable.value + " "+document.forms[0].nomPrenomNewRedevable.value;
  contenuTransfert= contenuTransfert+"</b></font></a>";
  contenuTransfert= contenuTransfert+"</p>";
 document.getElementById("transfert").innerHTML = contenuTransfert; 
}
else
{
 document.getElementById("transfert").innerHTML = ""; 

}


}

function rechercheEmplacement(numEmplacement,nomEmplacement,adresseEmplacement)
{
        //alert(numEmplacement);        
        var url = './rechercheEmplacement?numEmplacement='+numEmplacement+"&nomEmplacement="+nomEmplacement+"&adresseEmplacement="+adresseEmplacement;    
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestEmplacement(); } ;
        httpRequest.send(null); 
}  
  
function processRequestEmplacement()
{       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("emplacement");
              var contenuListEmplacement="<b><font face='Arial' size='2'>Choix de l'emplacement  :  </font></b>";
              contenuListEmplacement=" "+contenuListEmplacement+"<select name='listDesEmplacment' onclick='majLienEmplacement(this.value)' >";
              //alert(node.length);
				  for (i = 0; i < node.length; i++)
				  {				                              
                  var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue; 
                  var numEmplacement= xmldoc.getElementsByTagName("numEmplacement")[i].childNodes[0].nodeValue;
                  var peutEtreSupprimer= xmldoc.getElementsByTagName("peutEtreSupprimer")[i].childNodes[0].nodeValue;
                  element= peutEtreSupprimer+ "---"+numEmplacement+"---"+adresse;
                  //alert(element);
                  contenuListEmplacement=contenuListEmplacement+"<option value='"+element+"'>"+element+"</option>";  
                 
               }             	                           
			
              if (node.length==0)           
              {
                 contenuListEmplacement=contenuListEmplacement+"<option>    PAS DE RESULTATS    </option>"; 
                 document.getElementById("lienEmplacement").innerHTML = "";
                
              }
               contenuListEmplacement=contenuListEmplacement+"</select>"; 
               document.getElementById("listeEmplacement").innerHTML = contenuListEmplacement;  
               
               //Paul 31/10/2017 si en provenance de redevable.alerte voir plus haut document.addEventListener
               if(numero.length!=0) {
               		document.getElementById("listeEmplacement").children[1].click(this);
               }
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 




function majLienRedevable(adresse)
{
var reg=new RegExp("---");
var tableau=adresse.split(reg);
var numRedevable= tableau[0];
var nomPrenomRedevable= tableau[2];
var link ="./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=ajouter&typeRecherche=role";
link=link +"&optionTransfert=role&typeRedevable=normal&origine=origine&numRedevable="+numRedevable;
//alert(link);
contenuLienRedevable="<font face='Arial' color='#0000FF' size='2' >";
contenuLienRedevable = contenuLienRedevable +
						" Nouveau redevable: <a href="+ link+" > Lien vers le redevable numéro : "+
						numRedevable;
						"</a></font></b>";
res= adresse.indexOf("PAS", 0);

if(res !=0)
{
	document.getElementById("lienRedevable").innerHTML = contenuLienRedevable;
	document.forms[0].numNewRedevable.value=numRedevable;
	document.forms[0].nomPrenomNewRedevable.value=nomPrenomRedevable;
}
else
{
	document.getElementById("lienRedevable").innerHTML = "";
	document.forms[0].numNewRedevable.value="";
	document.forms[0].nomPrenomNewRedevable.value="";
}

if (document.forms[0].numEmplacementATransferer.value != "" && document.forms[0].numNewRedevable.value != "" )
{
  //alert("activer le trenasfert");
  contenuTransfert= "<p align='center'>";
  contenuTransfert= contenuTransfert+"<a href='javascript:transfertEmplacement();' target='bas'><font face='Arial' size='3'>";
  contenuTransfert= contenuTransfert+"<img height='20' src='images/lettre_v.gif' width='20' align='absMiddle' border='0'>";
  contenuTransfert= contenuTransfert+"<b>alider le transfert de l'emplacement numero "+document.forms[0].numEmplacementATransferer.value ;
  contenuTransfert= contenuTransfert+ " vers le redevable numero " +document.forms[0].numNewRedevable.value + " "+document.forms[0].nomPrenomNewRedevable.value;
  contenuTransfert= contenuTransfert+"</b></font></a>";
  contenuTransfert= contenuTransfert+"</p>";
 document.getElementById("transfert").innerHTML = contenuTransfert; 
}
else
{
 document.getElementById("transfert").innerHTML = ""; 

}

}



function rechercheRedevable(numRedevable,nomRedevable,adresseRedevable)
{   
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
              var contenuListRedevable="<b><font face='Arial' size='2'>Choix du Redevable  :  </font></b>";
              contenuListRedevable=" "+contenuListRedevable+"<select name='listDesRedevable' onchange='majLienRedevable(this.value)' >";
              //Paul ajouter affichage nomprenom du redevable
			  for (i = 0; i < node.length; i++)
			  {				                              
                 var adresse= xmldoc.getElementsByTagName("adresse")[i].childNodes[0].nodeValue; 
                 var numRedevable= xmldoc.getElementsByTagName("numRedevable")[i].childNodes[0].nodeValue;
                 var nomPrenomRedevable=xmldoc.getElementsByTagName("nomPrenomRedevable")[i].childNodes[0].nodeValue;
                 element= numRedevable+"---"+adresse+"---"+nomPrenomRedevable;
                 contenuListRedevable=contenuListRedevable+"<option value='"+element+"'>"+element+"</option>";
               }             	                           

              if (node.length==0)           
              {
                 contenuListRedevable=contenuListRedevable+"<option>    PAS DE RESULTATS    </option>"; 
                 document.getElementById("lienRedevable").innerHTML = "";
                
              }
              if(node.length==1){//bug ce cas onchange ne fonctionne pas
            	  majLienRedevable(element);
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



    
 
    

    function majAnneeDestination()
    {
      var anneeOrigine=Math.abs(document.forms[0].anneeOrigineBasculeEF.value);
      var anneeDestination=anneeOrigine+1;
      document.forms[0].anneeDestinationBasculeEF.value=anneeDestination;
    }

 </script>



<%
   String typeUtilisateur= (String)session.getAttribute("typeUtilisateur"); 
	String ville = (String)session.getAttribute("ville"); 
%>	






<form method="POST" action="sdivers">
<input name="dateBascule" type="hidden" value='<jsp:getProperty name="bean"  property="dateBascule" />'>
<input type="hidden" name="basculeElementFacturationFait" value="false">
<input type="hidden" name="basculeElementFacturationFait" value="true">
<input type="hidden" name="choix">
<input type="hidden" name="basculeTavFait" value="true">
<input type="hidden" name="numEmplacementATransferer" >
<input type="hidden" name="numNewRedevable" >
<input type="hidden" name="nomPrenomNewRedevable" >
<input type="hidden" name="cedexRedevable" value="false" >
<!-- Paul 31/10/2017 retour à alerte redevable-->
<input type="hidden" name="redirectTo" value="" >


  <div align="center" style="width: 1300; height: 488">
         
     <table border="1" cellpadding="0" cellspacing="0" width="98%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="43">
        <tr>
          <td width="200%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="images/admin.jpg" width="40" height="40"></p>
          </td>     
        </tr>
<a NAME="transfertEmplacment">
		<tr>
          <td width="200%" bgcolor="#AFF3BB" height="17">
              <font size="3" face="Arial" color="#000000"><b>TRANSFERT
              D'EMPLACEMENT&nbsp;</b></font><font color="#000000"><b><font size="3" face="Arial">
              :&nbsp;</font></b></font>
          </td>
		</tr>
        <tr>
         <td width="200%" height="1">
           	<table border="1" width="100%" height="1">
            <tr>
	        	<td width="20%" background="./images/fond_bleu.gif" height="66">
	          	<font face="Arial" size="2"><b>&nbsp;</b></font><b><font face="Arial" size="2">Emplacement</font></b>
	          	<font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
	        	<td width="80%" height="66">
          			<table border="1" width="100%">
            		<tr>
						<td width="23%"><b>N° emplacement :</b></td>
						<td width="77%">
						<input  type="text"  name="numEmplacement" size="33" value='<jsp:getProperty name="bean" property="numEmplacement"/>'/></td>
            		</tr>
            		<tr>
						<td width="23%"><b>Nom de l'emplacement :</b></td>
						<td width="77%"><input  type="text"  name="nomEmplacement" size="33"/></td>
            		</tr>
            		<tr>
						<td width="23%"><b>Adresse :</b></td>
						<td width="77%">
							<input  type="text"  name="adresseEmplacement" size="33"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font face="Arial" size="3">
								<a href="javascript:rechercheEmplacement(document.forms[0].numEmplacement.value,document.forms[0].nomEmplacement.value,document.forms[0].adresseEmplacement.value )" >
								<img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">
 								<b>echercher Emplacement&nbsp;</b></a>
 							</font> 
						</td>
            		</tr>
            		<tr>
						<td width="100%"  colspan="2">
							<div id="listeEmplacement" style="width: 593; height: 20">   </div>
						</td>
            		</tr>
            		<tr>
						<td width="100%"  colspan="2">
							<div id="lienEmplacement" style="width: 593; height: 20">   </div>

						</td>
            		</tr>
          	</table>
         </td>
        </tr>
        <tr>
        	<td width="20%" background="./images/fond_bleu.gif" height="66">
          		<font face="Arial" size="2"><b>&nbsp;</b></font><b><font face="Arial" size="2">Redevable</font></b>
          		<font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b>
          	</td>
        	<td width="80%" height="66">
          		<table border="1" width="100%">
            	<tr>
					<td width="22%"><b>N° redevable :</b></td>
					<td width="78%">
						<input  type="text"  name="numRedevable" size="33"/></td>
             	</tr>
            	<tr>
					<td width="22%"><b>Nom du redevable :</b></td>
					<td width="78%"><input  type="text"  name="nomRedevable" size="33"/></td>
            	</tr>
            	<tr>
					<td width="22%"><b>Adresse :</b></td>
					<td width="78%">
						<input  type="text"  name="adresseRedevable" size="33"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; 
						<font face="Arial" size="3">
							<a href="javascript:rechercheRedevable(document.forms[0].numRedevable.value,document.forms[0].nomRedevable.value,document.forms[0].adresseRedevable.value )" >
							<img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">
 							<b>echercher Redevable&nbsp;</b></a>
 						</font> 
					</td>
            	</tr>
            	<tr>
					<td width="100%"  colspan="2">
					<div id="listeRedevable" style="width: 593; height: 40">   </div>
					</td>
            	</tr>
            	<tr>
					<td width="100%"  colspan="2">
					<div id="lienRedevable" style=" height: 20">   </div>
					</td>
            	</tr>
          	</table>
           </td>
    	</tr>
        <tr>
	        <td width="20%" background="./images/fond_bleu.gif" height="22">
	          <b><font face="Arial" size="2">&nbsp;Transfert</font></b>
	          <font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
	        <td width="80%" background="./images/fond_bleu.gif" height="6">
	          <div id="transfert" style="width: 100%; height: 23">   </div>
	        </td>
       </tr>
       <tr>




        <td width="100%"  height="79" colspan="2">
          &nbsp; 
 
      
      

</td>
 
      
      

            </tr>
           </table>
               
         </td>
 
      
      

        </tr>
        <tr>




 

          <td width="200%" bgcolor="#AFF3BB" height="1">
            <p align="center"><img border="0" src="images/changement.png" width="40" height="40"></p>
          </td>
 
      
      

        </tr>




<a NAME="changementAdresse">

          <td width="200%" bgcolor="#AFF3BB" height="17">
              <font color="#000000"><b><font size="3" face="Arial">
              CHANGEMENT D'ADRESSE DU REDEVABLE&nbsp; :&nbsp;</font></b></font>
          </td>
 
      
      

      
      

        <tr>






          <td width="200%" height="1">
           <table border="1" width="100%" height="5">
            <tr>




        <td width="20%" background="./images/fond_bleu.gif" height="66">
          <font face="Arial" size="2"><b>&nbsp;</b></font><b><font face="Arial" size="2">Redevable</font></b>
          <font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
        <td width="80%" height="66">
          <table border="1" width="100%" height="140">
            <tr>










<td width="19%" height="23"><b>N° redevable </b></a><a NAME="changementAdresse"><b><font color="#FF0000" face="Arial" size="4">*</font></b></a><b>:</b></td>
<td width="81%" height="23">
<input  type="text"  name="numRedevableAChangerAdresse" size="33"/></td>
 
      
      
 
      
      

            </tr>
            <tr>




 

<td width="19%" height="23"><b>Nom du redevable :</b></td>
<td width="81%" height="23"><input  type="text"  name="nomRedevableAChangerAdresse" size="33"/>
 
      
      

 
      
      


 
      
      

 
      
      

</td>
 
      
      

            </tr>
            <tr>






<td width="19%" height="24"><b>Adresse :</b></td>
<td width="81%" height="24">
<input  type="text"  name="adresseRedevableAChangerAdresse" size="33"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="Arial" size="3"></font><font face="Arial" size="3">
<a href="javascript:rechercheRedevableAChangerAdresse(document.forms[0].numRedevableAChangerAdresse.value,document.forms[0].nomRedevableAChangerAdresse.value,document.forms[0].adresseRedevableAChangerAdresse.value )"><img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">
 <b>echercher Redevable&nbsp;</b></a></font> 
 
      
      

</td>
 
      
      

            </tr>
            <tr>




 

<td width="100%"  colspan="2" height="20">

<div id="listeRedevableAChangerAdresse" style="width: 593; height: 20">   </div>
</td>
 
            </tr>
            <tr>




 

<td width="100%"  colspan="2" height="20">

<div id="lienRedevableAChangerAdresse" style="width: 593; height: 18">   </div>

</td>
 
            </tr>
          </table>
        </td>


 
      
      

            </tr>
            <tr>




        <td width="20%" background="./images/fond_bleu.gif" height="22">
          <font face="Arial" size="2"><b>&nbsp;Nouveau numéro de voie</b></font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
          <font face="Arial" size="2"><b> :</b></font></td>
        <td width="80%" height="6">
          <table border="1" width="100%">
            <tr>
              <td width="894" height="15">
              
              <input type="text" name="numVoieRedevable" id="numVoieRedevable" size="11"  >               
              
              <b>Code Voie</b> 
                <input  size="9" type="text" name="codeVoixRedevable" id="codeVoixRedevable"       >
              <b>&nbsp;</b>
              <a href="javascript:rechercheVoieParCodeVoie(document.forms[0].codeVoixRedevable.value);" >
              <img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25">
              </a>
              
              <b>&nbsp;&nbsp;&nbsp;&nbsp; Recherche par
              voie: </b>
              <input type="text" name="nomRueRecherche" size="26">
              <a href="javascript:rechercheVoie(document.forms[0].nomRueRecherche.value);" >
              <img border="0" src="./images/ok.gif" align="absmiddle" width="25" height="25">
			      </a>
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




        <td width="20%" background="./images/fond_bleu.gif" height="25">
          <b><font size="2" face="Arial">&nbsp;Nouvelle adresse
          personnelle</font><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial"><font color="#FF0000">
          </font> :</font></b></td>
        <td width="80%" height="9">
          <table border="1" width="100%">
            <tr>
              <td width="100%" colspan="2">
          <p style="margin-left: 2">
          <font size="2" face="Arial"><input  type="text"  name="adressRedevable" id="adressRedevable" size="49"></font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
          </p>
              </td>
     	       </tr>
            <tr>
  	      <td width="54%" height="23">
   	       <p style="margin-left: 2">
    	      <font size="2" face="Arial">Compl. Adresse &nbsp; :&nbsp; 
<input  type="text"   name="complement1AdressRedevable" id="complement1AdressRedevable"  size="34">&nbsp;&nbsp;</font></p>
        </td>
              <td width="46%">
          <font size="2" face="Arial">CP</font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
          <font size="2" face="Arial"> &nbsp;&nbsp;:&nbsp;<input  type="text"  name="codePostaleRedevable" id="codePostaleRedevable"   maxlength="5" size="18"></font></td>
            </tr>
            <tr>
        <td width="54%" height="33">
          <p style="margin-left: 2">
          <font size="2" face="Arial">Compl. Adresse&nbsp; :&nbsp; 
<input  type="text"       name="complement2AdressRedevable" id="complement2AdressRedevable"    size="34"></font></p>

          </td>
              <td width="46%"><font size="2" face="Arial">Ville</font><b><font color="#FF0000" face="Arial" size="4">*</font></b>
                <font size="2" face="Arial"> :&nbsp;<input  type="text" name="villeeRedevable" id="villeeRedevable"  maxlength="30" size="18">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox"  onClick="modifierCedexRedevable()"   name="cedexRedevableCk" >Cédex</font></td>
            </tr>
          </table>
        </td>
 
      
      

      

            </tr>
            <tr>




        <td width="100%" background="./images/fond_bleu.gif" height="6" colspan="2">
          <p align="center">
<a href="javascript:modifierAdresseRedevable();"><img border="0" src="./images/valider.gif" width="150" height="17"></a>

        </td>
 
      
      

      

            </tr>
           </table>
               
         </td>
 
      
      

        </tr>
        <tr>






          <td width="200%" height="1">
               
         </td>
 
      
      

        </tr>
        <tr>
          <td width="200%" height="17">

<% if ( ! ville.equalsIgnoreCase("toulon") || (ville.equalsIgnoreCase("toulon") &&   typeUtilisateur.equalsIgnoreCase("admin")  ) ) {%>

         <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="43">
          <tr>
            <td width="200%" bgcolor="#AFF3BB" height="1">
              <p align="center"><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></p>
            </td>
          </tr>
          <tr>
            <td width="200%" bgcolor="#AFF3BB" height="17"><a NAME="bascule"><font size="3" face="Arial" color="#000000"><b>BASCULE</b>
              </font><font color="#000000"><b><font size="3" face="Arial">:&nbsp;</font></b></font></a><font color="#000000"><b><font size="3" face="Arial">&nbsp;</font></b></font></td>
          </tr>
          <tr>
            <td width="200%" height="1">
              <table border="1" width="100%" height="35">
                <tr>
                  <td width="100%" height="18" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Basculer
                    les types de taxe et les barèmes :</b></font></td>
                </tr>
                <tr>
                  <td width="24%" background="./images/fond_bleu.gif" height="30"><font face="Arial" size="2"><b>&nbsp;</b></font><b><font face="Arial" size="2">De
                    l'année&nbsp;</font></b> <font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
                  <td width="76%" height="30"><input readOnly type="text" name="anneeExercice" 
                  size="20"
                  value="<jsp:getProperty name="bean" property="anneeExercice"/>"
                  ></a></td>
                </tr>
                <tr>
                  <td width="24%" background="./images/fond_bleu.gif" height="21"><font face="Arial" size="2"><b>&nbsp;V</b></font><b><font face="Arial" size="2">ers
                    l'année </font></b><font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
                  <td width="76%" height="21"><input readOnly type="text" name="anneeExerciceSuivante"
                   size="20"
                   value="<jsp:getProperty name="bean" property="anneeExerciceSuivante"/>"
                   ></a></td>
                </tr>
                  <tr>
                  <td width="24%" background="./images/fond_bleu.gif" height="21"><font face="Arial" size="2"><b></b></font>
                  <b><font face="Arial" size="2">&nbsp;Pourcentage (<font color="#FF0000">XX.YY</font>)</font></b><font color="#FF0000">*</font><b><font face="Arial" size="2">:</font></b></td>
                  <td width="76%" height="21"><input type="text" name="pourcentage" 
                  value="<jsp:getProperty name="bean" property="pourcentage"/>"
                  size="20" ></td>
                </tr>
                <tr>
                  <% if(! bean.getBasculeFaite() ){%>
                  <td width="100%" background="./images/fond_bleu.gif" height="21" colspan="2">
                    <p align="center">&nbsp;<a href="javascript:basculerLesEmplacmentEtLesBaremes();" target="bas"><font face="Arial" size="3"><img height="20" src="images/lettre_b.gif" width="20" align="absMiddle" border="0"><b>asculer les barèmes et les type de taxes
                    </b></font></a></td>
                    

                  <% }else {%>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="21">
                    <p align="center"><b><font face="Arial" size="3" color="#FF0000">La
                    bascule a déjà été effectuée le : 
                    <script> document.write(document.forms[0].dateBascule.value ) </script>
                    avec un poucentage 
                    <jsp:getProperty name="bean" property="pourcentage"/>
                    %                    
                    </font>
                    </b> 
                    </p>
                  </td>
                  <% }%>
                </tr>
                <tr>
                  <td width="100%" height="49"  colspan="2">&nbsp;</td>
                </tr>
                
                <% if (  ville.equalsIgnoreCase("bordeaux")  ) {%>

                <tr>
                  <td width="100%" height="21" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Basculer
                    les TAV actifs&nbsp; :</b></font></td>
                </tr>
                <tr>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="21" colspan="2">
                    <p align="center">&nbsp;<a href="javascript:basculerLesTav();" target="bas"><font face="Arial" size="3"><img height="20" src="images/lettre_b.gif" width="20" align="absMiddle" border="0"><b>asculer tous les TAV actifs</b></font></a>&nbsp;</td>
                </tr>
                
              
                
                <tr>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="27" colspan="2">
                    &nbsp;</td>
                </tr>
                
                <tr>
                  <td width="100%" height="21" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Basculer
                    les emplacements de type TLPE actifs&nbsp; :</b></font></td>
                </tr>
                <tr>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="15" colspan="2">
                    <p align="center">&nbsp;<a href="javascript:basculerTLPE();" target="bas"><font face="Arial" size="3"><img height="20" src="images/lettre_b.gif" width="20" align="absMiddle" border="0"><b>asculer les emplacements de type TLPE actifs</b></font></a></td>
                </tr>
                

                
                <tr>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="15" colspan="2">
                    &nbsp;</td>
                </tr>
                
				 <% } %>
               
 
 
		 	 <% if (  ville.equalsIgnoreCase("toulon")  ) {%>

                <tr>
                  <td width="100%" height="21" bgcolor="#FFFFE8" colspan="2"><font size="3" face="Arial" color="#000000"><b>Basculer
                    la taxe&nbsp; :                    
                    <jsp:getProperty name="bean" property="tousLesTypeDeTaxe"/>
                    
                    </b></font></td>
                </tr>
                <tr>
                  <td width="100%" background="file:./images/fond_bleu.gif" height="15" colspan="2">
                    <p align="center">&nbsp;<a href="javascript:basculerUneTaxe();" target="bas"><font face="Arial" size="3"><img height="20" src="images/lettre_b.gif" width="20" align="absMiddle" border="0"><b>asculer
                    les elements actifs</b></font></a></td>
                </tr>
                
        <% } %>
        
                
                
                

                
              </table>
              
             <%}%> 
            </td>
          </tr>
         </table>
         <p>&nbsp;
         </td>
        </tr>
        <tr>
          <td width="200%" height="17">
         &nbsp;
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;</p>
         <p>&nbsp;
         </td>
        </tr>
      </table> 
 
      
      
  </div>

</form>


     <p>&nbsp;</p>
<p>

</body>
</html>















