<html>

<script>

//Tableau de tranche de prix
var listeLigneTableauPrix=new Array();
var listeDesTranchesDesPrix; 

function valider()
{
  //document.forms[0].submit(); 
  var i;  
  var queryString = "";  
  for ( i = 0; i < listeLigneTableauPrix.length; i ++)  
  {  
     queryString += "$"+listeLigneTableauPrix[i].surfaceMin + "|" +listeLigneTableauPrix[i].surfaceMax + "|"+listeLigneTableauPrix[i].prix  ;  
  }
  if (queryString.length > 1)
  {  
   queryString = queryString.substring(0,queryString.length -1); 
   document.forms[0].listeDesTranchesDesPrix.value=queryString;
   }
  alert(document.forms[0].listeDesTranchesDesPrix.value);
  document.forms[0].submit();
}

function Supprimerligne(numeroLigne)
{  
  listeLigneTableauPrix.splice(numeroLigne,1);
  tracerTableau();
}

function modifierSurfaceMin(nomCellule,valeur)
{  
   var numLigne =nomCellule.substring(1,2);
   listeLigneTableauPrix[numLigne].surfaceMin=valeur;  
}

function modifierSurfaceMax(nomCellule,valeur)
{  
   var numLigne =nomCellule.substring(1,2);
   listeLigneTableauPrix[numLigne].surfaceMax=valeur;   
}

function modifierPrix(nomCellule,valeur)
{   
   var numLigne =nomCellule.substring(1,2);
   listeLigneTableauPrix[numLigne].prix=valeur;  
}

function delete(idImage)
{ 
  alert(idImage);
}
function tracerTableau()
{
  //Tracer une maj du tableau
  contenuDiv="<table border='1' width='27%'>";
  contenuDiv=contenuDiv+"<tr>";
  contenuDiv=contenuDiv+"<td width='33%' align='center'>Surface Min en M² (inclus)</td>";
  contenuDiv=contenuDiv+"<td width='33%' align='center'>Surface Max en M²(inclus)</td>";
  contenuDiv=contenuDiv+"<td width='34%' align='center'>Prix</td>";
  contenuDiv=contenuDiv+"<td width='34%' align='center'>-</td>";
  
  contenuDiv=contenuDiv+"</tr>";
  for(var i = 0;i<listeLigneTableauPrix.length;i++)
  { 
    contenuDiv=contenuDiv+"<tr>";
    contenuDiv=contenuDiv+"<td width='33%' align='center'><input  onChange=modifierSurfaceMin(this.name,this.value) type='text' name='C"+i+"0' size='22' value='"+listeLigneTableauPrix[i].surfaceMin+"'></td>";
	 contenuDiv=contenuDiv+"<td width='33%' align='center'><input onChange=modifierSurfaceMax(this.name,this.value) type='text' name='C"+i+"1' size='22' value='"+listeLigneTableauPrix[i].surfaceMax+"'></td>";
	 contenuDiv=contenuDiv+"<td width='34%' align='center'><input onChange=modifierPrix(this.name,this.value) type='text' name='C"+i+"2' size='22' value='"+listeLigneTableauPrix[i].prix+"'></td>";
	 contenuDiv=contenuDiv+"<td width='34%' align='center' height='19'><img id="+i+" onclick=Supprimerligne(this.id) border='0' src='../images/delete.GIF' width='26' height='24'></td>";
	 contenuDiv=contenuDiv+"</tr>";  
  }
  contenuDiv=contenuDiv+"</table>";
  document.getElementById("tableauTranchePrix").innerHTML=contenuDiv; 
  
}


function ajouterLigneAuTableau()
{
   //Ajouter une ligne dans le tableau
   var newLigneTableau=new ligneTableau('','','');	   
   listeLigneTableauPrix[listeLigneTableauPrix.length]=newLigneTableau;
   tracerTableau();
}



function ligneTableau(surfaceMin,surfaceMax,prix) 
{
    this.surfaceMin=surfaceMin;
    this.surfaceMax=surfaceMax;
    this.prix=prix;     
}



</script>


<head>
<meta http-equiv="Content-Language" content="fr">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
</head>

<body>


<form method="POST" action="../testgestionimputation" >
<div id="tableauTranchePrix" ></div>
<p><a href="javascript:ajouterLigneAuTableau();">Ajouter Tranche Prix </a></p>
<p>&nbsp;</p>


<input type="text" name="listeDesTranchesDesPrix" > 
<input type="text" name="action" > 
<p align="left"><a href="javascript:valider();">VALIDER</a></p>
</form>

</body>

</html>
