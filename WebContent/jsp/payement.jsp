<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
  <%@ page language = "java" %>
  <jsp:useBean id="beanPayement" scope="page" class="fr.analogon.r2t.view.regie.BaffPayement"/>
  <jsp:setProperty name="beanPayement" property="request" value="<%=request%>"/> 
</head>


<script>
//----------------------------------------------
// Affichage d'un Objet déclaré dans le document...
//----------------------------------------------
function Affiche_OBJ(){
var Arg = arguments; // Récup liste des arguments passée à la fonction
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

function changementTypePayement()
{
    var typePayement = document.forms[0].typePayement.options[document.forms[0].typePayement.selectedIndex].text ;	
	if(typePayement.indexOf("Espece") == -1 )
	{		
		Affiche_OBJ('rubBanque');
		document.getElementById('idBanque').value= document.forms[0].banque.options[document.forms[0].banque.selectedIndex].value;	
	}
	else
	{
		Masque_OBJ('rubBanque');
		document.getElementById('idBanque').value=0;
		document.getElementById('numeroCheque').value=0;
	}
}


function verfierSommeFactureMontantPayement()
{
 var somme=0;
 
	for(var i=0; i<tableauFacture.length ;  i++)
   {
     var idChamps = "V" + tableauFacture[i];   
	 somme = parseInt(somme) + parseInt(document.getElementById(idChamps).value) ;
	 //Verfier la somme des factures avec le montant a payer	 
	 if(tableauFacture.length ==1)
	 {
	   //verifier si le montant et > au montant de la facture
	   if( parseInt(document.getElementById("montantAPayer").value) > parseInt(document.getElementById(idChamps).value) )
	   {
	     alert("le montant à payer doit être inferieur ou egal au montant de la facture")
	   }
	 }
	 else
	 {
	 }
   }
	
}




function saisiePayement()
{
  if(tableauFacture.length == 0 )
  {
	alert("Selectionnez une facture pour effectuer un paiement");
  }
  else
  {
     verfierSommeFactureMontantPayement();
     document.forms[0].submit();     
  }
}

function majAffichagePayement()
{
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
	 somme = parseInt(somme) + parseInt(document.getElementById(idChamps).value) ;
   }
   document.getElementById("montantAPayer").value = somme;      
   majAffichagePayement();
  if(tableauFacture.length > 1)
  {    
	document.getElementById("montantAPayer").readOnly=true;
  }
  else
  {
	document.getElementById("montantAPayer").readOnly=false;
  }
   
   
}
</script>

<form method="POST" action="gestionPayement" name="formulaire" >
<input type="text" name="listeNumeroFacture" >
<input type="hidden" id="idBanque"  name="idBanque">

<head>
<title></title>
</head>

<body background="../images/fond_bleu.gif">

<table border="1" width="100%" height="100">
  <tr bgcolor="#FFFFE8">
    <td width="25%" align="center" height="19"><b>Facture</b></td>
    <td width="25%" align="center" height="19"><b>Montant&nbsp;</b></td>
    <td width="25%" align="center" height="19"><b>Solde</b></td>
    <td width="25%" align="center" height="19"><b>Paiement</b></td>
  </tr>
  <tr>
    <td width="25%" align="center" height="19"><b>Facture 20091876</b></td>
    <td width="25%" align="center" height="19">150</td>
    <td width="25%" align="center" height="19"><input type="hidden" id="V20091876" value="150">150</td>
    <td width="25%" align="center" height="19"><input type="checkbox" name="20091876" onclick=mettreAJourTableau(this.name)></td>
  </tr>
  
    <tr>
    <td width="25%" align="center" height="19"><b>Facture 20091877</b></td>
    <td width="25%" align="center" height="19">250</td>
    <td width="25%" align="center" height="19"><input type="hidden" id="V20091877" value="250">250</td>
    <td width="25%" align="center" height="19"><input type="checkbox" name="20091877" onclick=mettreAJourTableau(this.name)></td>
  </tr>
  
    <tr>
    <td width="25%" align="center" height="19"><b>Facture 20091878</b></td>
    <td width="25%" align="center" height="19">200</td>
    <td width="25%" align="center" height="19"><input type="hidden" id="V20091878" value="50">50</td>
    <td width="25%" align="center" height="19"><input type="checkbox" name="20091878" onclick=mettreAJourTableau(this.name)></td>
  </tr>


  
  </table>



<DIV ID="rubTableauPayement">
<table border="1" width="100%">
  <tr>
    <td width="20%"><b>Nombre de facture(s) a payer</b></td>
    <td width="80%">
	<input type="text" id="nombreFacture" name="nombreFacture" READONLY>	
    </td>
  </tr>
  <tr>
    <td width="20%"><b>Type de Paiement</b></td>
    <td width="80%">
	<jsp:getProperty name="beanPayement" property="typePayement"/>		
  </td>
  </tr>
  <tr>
    <td width="20%"><b>Montant à payer</b></td>
    <td width="80%"><input type="text" name="montantAPayer" id="montantAPayer" size="20"></td>
  </tr>
  <tr>
    <td width="20%"><b>Numéro de quittance</b></td>
    <td width="80%"><input type="text" name="numeroQuittance" size="20"></td>
  </tr>
</table>





<DIV ID="rubBanque">  
<table border="1" width="100%">
  <tr>
    <td width="20%"><b>Numéro de chèque</b></td>
    <td width="80%"><input type="text" name="numeroCheque" id="numeroCheque" size="20"></td>
  </tr>
  <tr>
    <td width="20%"><b>Banque</b></td>
    <td width="80%">
	<jsp:getProperty name="beanPayement" property="banque"/>	
  </tr>
  </table>
</DIV>



<table border="1" width="100%">
  <tr>
    <td width="100%" colspan="2" align="center"><b><a href="javascript:saisiePayement()">Valider
      le paiement</a></b></td>
  </tr>
</table>

</div>




<script>
majAffichagePayement();
changementTypePayement();
</script>


</form>
