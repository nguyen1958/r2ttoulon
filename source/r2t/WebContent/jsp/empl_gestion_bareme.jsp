<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffBareme1" scope="request" class="fr.analogon.r2t.parametres.bareme.BAffBareme"/>
<jsp:setProperty name="baffBareme1" property="request" value="<%=request%>"/>

<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript">

//Tableau de tranche de prix
var listeLigneTableauPrix=new Array();
var listeDesTranchesDesPrix; 


function initTranchePrix()
{
   var chaine=document.forms[0].listeDesTranchesDesPrix.value;
   //alert("Chaîne d'origine : " + chaine);
   var tableau=chaine.split("$");
  
   for (var i=0; i<tableau.length; i++)
   {          
      if(tableau[i].length != 0)
      {
          var tableau2=tableau[i].split("|");          
          ajouterLigneAuTableau();
          var k =i-1;
          for (var j=0; j<tableau2.length; j++)
          {
             nomCellule = "C"+ k + j ;             
             valeur = tableau2[j];
             if(j==0)
             {
               modifierSurfaceMin(nomCellule,valeur)
             }
             
             else if(j==1)
             {
               modifierSurfaceMax(nomCellule,valeur)
             }
             
             else if(j==2)
             {              
               modifierPrix(nomCellule,valeur);
             }
          }                  
      }
	} 
	document.forms[0].listeDesTranchesDesPrix.value ="";	
	tracerTableau();
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


function tracerTableau()
{
  //Tracer une maj du tableau
  contenuDiv="<table border='1' width='100%'>";
  contenuDiv=contenuDiv+"<tr>";
  contenuDiv=contenuDiv+"<td width='35%' align='center'>Surface Min en M² (inclus)</td>";
  contenuDiv=contenuDiv+"<td width='35%' align='center'>Surface Max en M²(inclus)</td>";
  contenuDiv=contenuDiv+"<td width='20%' align='center'>Prix</td>";
  contenuDiv=contenuDiv+"<td width='10%' align='center'>-</td>";
  
  contenuDiv=contenuDiv+"</tr>";
  for(var i = 0;i<listeLigneTableauPrix.length;i++)
  { 
    contenuDiv=contenuDiv+"<tr>";
    contenuDiv=contenuDiv+"<td width='33%' align='center'><input  onChange=modifierSurfaceMin(this.name,this.value) type='text' name='C"+i+"0' size='22' value='"+listeLigneTableauPrix[i].surfaceMin+"'></td>";
	 contenuDiv=contenuDiv+"<td width='33%' align='center'><input onChange=modifierSurfaceMax(this.name,this.value) type='text' name='C"+i+"1' size='22' value='"+listeLigneTableauPrix[i].surfaceMax+"'></td>";
	 contenuDiv=contenuDiv+"<td width='34%' align='center'><input onChange=modifierPrix(this.name,this.value) type='text' name='C"+i+"2' size='22' value='"+listeLigneTableauPrix[i].prix+"'></td>";
	 contenuDiv=contenuDiv+"<td width='34%' align='center' height='19'><img id="+i+" onclick=Supprimerligne(this.id) border='0' src='images/delete.GIF' width='26' height='24'></td>";
	 contenuDiv=contenuDiv+"</tr>";  
  }
  contenuDiv=contenuDiv+"</table>";
  
  if(listeLigneTableauPrix.length > 0 )
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

















function changerTypeTaxe()
{

   //SI TYPE DE TAXE == TLPE
   if(document.forms[0].typeTaxe.value.indexOf("TLPE")!= -1)
   {
      Affiche_OBJ('typePrixTLPE'); 
      Masque_OBJ('typeUtiliserPeriodicite');
      Masque_OBJ('typePrixUnitaire');
      Masque_OBJ('typePrixPeriodes'); 
       
   }
   //TYPE DE TAXE DIFFERENT DE TLPE
   else
   {
	  Masque_OBJ('typePrixTLPE'); 
      Affiche_OBJ('typeUtiliserPeriodicite');
      Affiche_OBJ('typePrixUnitaire');
      Affiche_OBJ('typePrixPeriodes');
   }
   utiliserPeriodicte();
   
}


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















function utiliserPeriodicte()
{
 v =document.forms[0].utiliserPeriodicite.value;
 if (v=="NON") 
 {
      document.getElementById("prixPeriode1").style.visibility='hidden'; 
                document.getElementById("prixPeriode2").style.visibility='hidden';
                        document.getElementById("prixPeriode3").style.visibility='hidden';
                                document.getElementById("prixPeriode4").style.visibility='hidden';
                                        document.getElementById("prixPeriode5").style.visibility='hidden';                                        
                 
                      formulaire.prixPeriode1.value=formulaire.prixUnitaire.value;      
                         formulaire.prixPeriode2.value=formulaire.prixUnitaire.value;      
	                      	formulaire.prixPeriode3.value=formulaire.prixUnitaire.value;      
    		            	      formulaire.prixPeriode4.value=formulaire.prixUnitaire.value;      
            			           formulaire.prixPeriode5.value=formulaire.prixUnitaire.value;        			         

      Masque_OBJ('typePrixPeriodes');
 }
 else
 {
 document.getElementById("prixPeriode1").style.visibility='visible'; 
                document.getElementById("prixPeriode2").style.visibility='visible';
                        document.getElementById("prixPeriode3").style.visibility='visible';
                                document.getElementById("prixPeriode4").style.visibility='visible';
                                        document.getElementById("prixPeriode5").style.visibility='visible';

      Affiche_OBJ('typePrixPeriodes');

 }
 
}

function valider()
{
   //TYPE TRANCHE DE PRIX TLPE
  var i;  
  var queryString = "";  
  
  //alert("Nombre de tranche"+ listeLigneTableauPrix.length);
  for ( i = 0; i < listeLigneTableauPrix.length; i ++)  
  {       
    //alert("Surface min="+ listeLigneTableauPrix[i].surfaceMin);	
    //alert("Surface max="+ listeLigneTableauPrix[i].surfaceMax);
    //alert("Prix="+ listeLigneTableauPrix[i].prix);
    queryString += "$"+listeLigneTableauPrix[i].surfaceMin + "|" +listeLigneTableauPrix[i].surfaceMax + "|"+listeLigneTableauPrix[i].prix  ;  
  }
  
  document.forms[0].listeDesTranchesDesPrix.value=queryString;
  verfiAnneeExercice= verifier("Année",document.forms[0].anneeExercice.value);
  //alert("test");
  verfiLibelle= verifier("Libelle du barème",document.forms[0].libelle.value);

  if(document.forms[0].typeTaxe.value.indexOf("TLPE")!= -1)
  	document.forms[0].prixUnitaire.value=0;
  verfiPrixUnitaire= verifier("Prix unitaire",document.forms[0].prixUnitaire.value);
  
  if (isNaN(document.forms[0].anneeExercice.value))
  {
     verfiAnneeExercice =-1;
     alert("l'Annee d'exercice doit etre un entier");
  }
  if (isNaN(document.forms[0].prixUnitaire.value))
  {
     verfiPrixUnitaire =-1;
     alert("le prixUnitaire doit etre un nombre");
  }
  verfiPrixPeriode1=0;
  verfiPrixPeriode2=0; 
  verfiPrixPeriode3=0;
  verfiPrixPeriode4=0;
  verfiPrixPeriode5=0;
  verifType=0;
  
  if(document.forms[0].utiliserPeriodicite.value=="OUI")
  {
    verfiPrixPeriode1= verifier("Prix période 1",document.forms[0].prixPeriode1.value);
    verfiPrixPeriode2= verifier("Prix période 2",document.forms[0].prixPeriode2.value);
    verfiPrixPeriode3= verifier("Prix période 3",document.forms[0].prixPeriode3.value);
    verfiPrixPeriode4= verifier("Prix période 4",document.forms[0].prixPeriode4.value);
    verfiPrixPeriode5= verifier("Prix période 5",document.forms[0].prixPeriode5.value);
  }

  if (document.forms[0].uniteDeTravail.value == "FORFAIT" && document.forms[0].uniteDeTemps.value != "NON-PRECISE")  
  {
      alert("L' unite de temps doit etre NON-PRECISE");
      verifType=-1;
  }  

   var verifAdroitDeCree= -1;
   reg ="---";
   var tableau=document.forms[0].typeTaxe.value.split(reg);
   var typeDeTaxe = tableau [1];
   var pos= document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(typeDeTaxe);
   if(pos !=-1)
   {
     verifAdroitDeCree=0;
   }
   
   if(verifAdroitDeCree==-1)
   {

	  
      alert("Vous n'avez pas le droit de crer des barémes du type "+ typeDeTaxe);
   }
    	
  verfiTracnhePrix= 0;  	
  if(document.forms[0].typeTaxe.value.indexOf("TLPE")!= -1 && listeLigneTableauPrix.length ==0)
  { 
     alert("Il faut définir au moins une tranche de prix pour le type de taxe TLPE");
     verfiTracnhePrix= -1;  	

  }

 

  
  
  if(verfiAnneeExercice== 0 &&  verfiLibelle== 0 &&  verfiPrixUnitaire== 0 
     && verfiPrixPeriode1==0      && verfiPrixPeriode2==0     && verfiPrixPeriode3==0
          && verfiPrixPeriode4==0     && verfiPrixPeriode5==0 
           && verifType==0 && verifAdroitDeCree == 0 && verfiTracnhePrix==0 )
  {
    utiliserPeriodicte();   
    document.forms[0].submit();    
  }
}


function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		  var choix = confirm("Voulez-vous supprimer le Barème "+ document.forms[0].libelle.value+" ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";
				document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer ce bareme , il y a des ouvrages liées ");	   
}





</script>



<form method="POST" action="gestionbareme" name="formulaire" >
<body background="./images/nuages.jpg" topmargin="0">

<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    Boolean peutAcceder = Boolean.valueOf(baffBareme1.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
	String ville = (String)session.getAttribute("ville"); 
%>

<input type="hidden" name="listeDesTranchesDesPrix" value="<jsp:getProperty name="baffBareme1" property="listeDesTranchesDesPrix" />" > 
<input type="hidden"  name="listeLibelleDesTypesDeTaxeAutorise" value="<%out.println(listeLibelleDesTypesDeTaxeAutorise);%>" > 
<input type="hidden"  name="peutEtreSupprimer" size="20" value="<jsp:getProperty name="baffBareme1" property="peutEtreSupprimer" />" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="263">
 <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/bareme.png" width="40" height="40"></b></font>
          </td>
 </tr>
   

    <tr>
      <td width="1133" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!baffBareme1.getCreation()){%>  
	              <font size="4"><b>MODIFIER UN BAREME :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER </b></font>  
	              <font size="4"><b> UN BAREME</b></font>
              <font size="4"><b> :&nbsp</b></font>                 
		      <% } %>                 

                </p>
      </td>
    </tr>
<% if(!baffBareme1.getCreation()){%> 
    <tr>
      <td width="232" height="23">
        <b><font face="Arial" size="2">&nbsp;Code barème :</font></b>
      </td>
      <td width="899" height="23">
        <input type="text" readonly name="codeBareme" size="20" value="<jsp:getProperty name="baffBareme1" property="codeBareme" />" >	
      </td>
    </tr>
    
<%}%>
    <tr>
      <td width="232" height="23">
        &nbsp;<b><font face="Arial" size="2">Année exercice </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="23">
        <input readonly type="text" maxlength="4" name="anneeExercice" size="20" value="<jsp:getProperty name="baffBareme1" property="anneeExercice" />" >        
         </td>
    </tr>  
    
    

    
 
 <tr>
      <td height="19" width="232">
        <b>
        <font face="Arial" size="2">&nbsp;Type de taxe </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><font face="Arial" size="2"><b>:</b></font>
      </td>	  
         <td width="899" height="19">           		
				<jsp:getProperty name="baffBareme1"  property="typeTaxe" />			
		</td>
 </tr>

    

    <tr>
      <td width="1131" height="23" colspan="2">&nbsp;
        
      </td>
    </tr>

	<tr>
      <td width="232" height="21">
        &nbsp;<b><font face="Arial" size="2">Libellé </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">: </font></b>
      </td>
      <td width="899" height="21">
        <input type="text"  name="libelle" size="50" value="<jsp:getProperty name="baffBareme1" property="libelle"/>" >
       </td>
    </tr>
    
 <% if ( ! ville.equalsIgnoreCase("Bordeaux")) { %> 
    <tr>
      <td width="232" height="21">
        &nbsp;<b><font face="Arial" size="2">Section </font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="21">
        <input type="text"  name="sectionBareme" size="50" value="<jsp:getProperty name="baffBareme1" property="sectionBareme"  />" >
       </td>
    </tr>
	
	
	<tr>
      <td width="232" height="21">
        &nbsp;<b><font face="Arial" size="2">Imputation </font><font face="Arial" size="2" color="#FF0000"><i></i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="21">
        <input type="text"  name="imputationBareme" size="50" value="<jsp:getProperty name="baffBareme1" property="imputationBareme"/>" >
       </td>
    </tr>
	 <% } %> 
	

	
	
	
	

    <tr>
      <td height="21" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Unité de travail
        </font></b>
        <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font>:</b>
      </td>
      <td width="899" height="21">
        <jsp:getProperty name="baffBareme1" property="uniteDeTravail" />
       </td>
    </tr>

    <tr>
      <td height="22" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Unité de temps
        </font></b>
        <b><i><font face="Arial" size="2" color="#FF0000">*</font></i>:</b>
      </td>
      <td width="899" height="22">
        <jsp:getProperty name="baffBareme1" property="uniteDeTemps" />

       </td>
    </tr>

 <tr>
      <td height="22" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Type d'arrondi
        </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="22">
                 <jsp:getProperty name="baffBareme1"  property="typeArrondi" />
       </td>
 </tr>




    <tr>
      <td height="22" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Utiliser Prorata
        </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="22">
        <jsp:getProperty name="baffBareme1"  property="utiliserProrata" />
       </td>
    </tr>
</TABLE>

<DIV ID="typeUtiliserPeriodicite">
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="1">


    <tr>
      <td height="22" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Utiliser
        Périodicité
        </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="22">
        <jsp:getProperty name="baffBareme1"  property="utiliserPeriodicite" />
       </td>
    </tr>
    
</TABLE>
</DIV>



<DIV ID="typePrixUnitaire">
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="1">
 <tr>
      <td height="1" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Prix unitaire
        (XX.YY) </font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b><font face="Arial" size="2"><b>:</b></font>
      </td>
      <td width="899" height="1">
        <input  type="text"  name="prixUnitaire" size="20" value="<jsp:getProperty name="baffBareme1" property="prixUnitaire" />" >

       </td>
 </tr>
</TABLE>
</DIV>

<DIV ID="typePrixPeriodes">
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="134">

    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Prix
        période 1
        :
        </font></b>
      </td>
      <td width="899" height="23">
        <input type="text" id="prixPeriode1" name="prixPeriode1" size="20" value="<jsp:getProperty name="baffBareme1"  property="prixPeriode1" />" >
       </td>
    </tr>

    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Prix
        période 2
        </font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="23">
        <input type="text"  id="prixPeriode2" name="prixPeriode2" size="20" value="<jsp:getProperty name="baffBareme1" property="prixPeriode2"  />" >
       </td>
    </tr>

    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Prix
        période 3
        </font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="23">
        <input type="text"  id="prixPeriode3" name="prixPeriode3" size="20" value="<jsp:getProperty name="baffBareme1" property="prixPeriode3"  />" >
       </td>
    </tr>

    <tr>
      <td height="23" width="232">
        <b><font face="Arial" size="2">
        &nbsp;Prix période 4 </font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="23">
        <input type="text"  id="prixPeriode4" name="prixPeriode4" size="20" value="<jsp:getProperty name="baffBareme1" property="prixPeriode4"  />" >
       </td>
      
    </tr>

    <tr>
      <td height="23" width="232">
        <b><font face="Arial" size="2">&nbsp;Prix période 5 </font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="899" height="23">
        <input type="text" id="prixPeriode5" name="prixPeriode5" size="20" value="<jsp:getProperty name="baffBareme1" property="prixPeriode5"  />" >      
       </td>
    </tr>
    
        
</TABLE>
</DIV>  
  
<% if (true) { %>
<DIV ID="typePrixTLPE">
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="63">
<tr>
      <td width="232" height="19">&nbsp;<b><font face="Arial" size="2"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;;
mso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:FR;mso-fareast-language:
FR;mso-bidi-language:AR-SA">Coefficient type TLPE</span> : </font></b>
        
      </td>
      <td width="899" height="19">
        <input type="text" name="coefficientNumerique" size="20" value="<jsp:getProperty name="baffBareme1" property="coefficientNumerique"  />" >   
      </td>
    </tr>

    <tr>
      <td width="232" height="1">&nbsp;<b><font face="Arial" size="2">Tranche
        de prix :</font></b>&nbsp;
        
      </td>
      <td width="899" height="1">
	    <a href="javascript:ajouterLigneAuTableau();">Ajouter une nouvelle tranche de prix </a> 
	  
        
       </td>
    </tr>

    <tr>
      <td width="232" height="1">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      -
      </td>
      <td width="899" height="1">
        <div id="tableauTranchePrix" ></div>       
       </td>
    </tr>

</TABLE>
</DIV>
<% } %>


<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="1">
    <tr>
      <td width="232" height="1">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
        Champs obligatoires</i></font></b>
      </td>
      <td width="899" height="1">&nbsp;
        
       </td>
    </tr>
    <tr>
      <td width="1133" colspan="2" height="1">
        <table border="0" width="100%" height="1">
          <tr>
 
        
   
  <% if(!baffBareme1.getCreation() &&  peutAcceder  ){%>   
    <td align="center" height="1">
        <a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>
      </td>
      <td align="center" width="375" height="1">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>
      </td>   
 <%}else if(baffBareme1.getCreation()) {%> 
   <td align="center" width="374" height="1">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20" ></a>
        </p>
      </td>
  <%}%>
  </tr>
        </table>
      </td>
    </tr>

    
</table> 

 
</form>

<script>
changerTypeTaxe();
utiliserPeriodicte();
initTranchePrix();

</script>



<% if(!baffBareme1.getCreation()){%> 
<p align="center">
          <a href="entree?action=liste_bareme.jsp"><font color="#0000FF"><img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">iste
des</font></b><font face="Arial" size="3"><b> barèmes</b></font></font></a>
</p>
<% }%> 



</body>

