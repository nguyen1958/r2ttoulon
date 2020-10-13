<html> 
<%@ page language = "java" %>
<head> 

<META http-equiv="Expires" content="0">
<meta name="Pragma" CONTENT="no-cache">
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script> 
<jsp:useBean id="beanReclamation" scope="request" class="fr.analogon.r2t.reclamation.BAffReclamation"/>
<jsp:setProperty name="beanReclamation" property="request" value="<%=request%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<script type="text/javascript">  

var httpRequest;
var httpRequestReclamtion;
var listeDesOuvrageProbleme=new Array();

function getLeCourierReponse()
{
    document.getElementById("courierDeReponse").innerHTML = "<B>Chargement en cours,veuillez patientez...</B>";
    var idReclamation= document.forms[0].idReclamation.value;
    var idModeleCourier= document.forms[0].modeleCourier.value;
    var url = "./recupererCourierReponse?idReclamation="+idReclamation+"&idModeleCourier="+idModeleCourier;
	//alert(url);
    if (window.ActiveXObject)
    {
       httpRequestReclamtion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest)
    {
        httpRequestReclamtion = new XMLHttpRequest();
    }
    httpRequestReclamtion.open("GET", url, true);        
    httpRequestReclamtion.onreadystatechange = function() {processRequestCourierReponse(); } ;
    httpRequestReclamtion.send(null);
   
}


function processRequestCourierReponse()
{
   //alert("OK TEST");      
   if (httpRequestReclamtion.readyState == 4)
   {
      if(httpRequestReclamtion.status == 200)
      {
         var xmldoc = httpRequestReclamtion.responseXML.documentElement; 
         var lienCourier= xmldoc.getElementsByTagName("lienCourier")[0].childNodes[0].nodeValue;                
		  var cheminModele= xmldoc.getElementsByTagName("cheminModele")[0].childNodes[0].nodeValue;
         var link="<a name='lienVersDataServer'  href=" + lienCourier + " target=\"_blank\" > Courier.doc </a>";				  				    				   				 
		  document.getElementById("courierDeReponse").innerHTML = link;  
		  var olink = document.getElementsByName('lienVersDataServer');		 
		  document.generaleActionForm.remoteFilePath.value=olink[0].getAttribute('href');
		  document.generaleActionForm.modeleCourier.value = cheminModele;
         var newLink="<a  href ='vbscript:downloaddata()'> Lien Courier.doc</a>";
		  document.getElementById("courierDeReponse").innerHTML = newLink;         
      }
      else
      {
         alert("Erreur chargement page \n"+ httpRequestReclamtion.status +":"+ httpRequestReclamtion.statusText);
      }
  }
} 













function mettreAJourTableau(what)
{  
	//alert("mettreAJourTableau:"+what+"|"+listeDesOuvrageProbleme);  
	var existe="false";
	for(var i=0; i<listeDesOuvrageProbleme.length ;  i++)
	{		
		if (listeDesOuvrageProbleme[i]==what)
		{
		  listeDesOuvrageProbleme.splice(i,1);
		  existe="true";
		  break;
		}
	}
	if(existe=="false") 
	  listeDesOuvrageProbleme[listeDesOuvrageProbleme.length]=what;	
	
	var listeOuvrage="";
    for(var i=0; i<listeDesOuvrageProbleme.length ;  i++)
	{
	  listeOuvrage = listeOuvrage + ";" +listeDesOuvrageProbleme[i];	
    }
    document.forms[0].listeOuvrage.value= listeOuvrage;
	
	//alert(listeDesOuvrageProbleme+"|"+listeOuvrage);  
}
function valider()
{
  
  ifacture = verifier("Numéro de la facture",document.forms[0].idFactue.value) ;
  dateReclamation = verifier("Date de la réclamation",document.forms[0].dateReclamation.value) ;
  texteReclamation= verifier("Texte de la réclamation",document.forms[0].texteReclamation.value);
  if(ifacture==0 &&   dateReclamation==0 && texteReclamation==0) 
  {

		var pos = document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(document.forms[0].typeTaxe.value);
       //alert(pos);
      if( pos != -1 )
         document.forms[0].submit();
      else
      {
         alert("Vous n'avez pas le droit de créer & modifier des réclamations sur des factures de type "+document.forms[0].typeTaxe.value );  
       
      }
   }  
}



function majControleur(ligne)
{
  //alert(ligne);
  var reg=new RegExp("---");
  var tableau=ligne.split(reg); 
  res= ligne.indexOf("PAS", 0);

if(res !=0)
{
	document.forms[0].numControleur.value=tableau[0];
   document.forms[0].nomControleur.value=tableau[1];
}
else
{	
	document.forms[0].numControleur.value="";
   document.forms[0].nomControleur.value="";
}

}


function rechercheUtilisateur(nomUtilisateur)
{		      
       //alert(nomUtilisateur);        
        var url = './rechercheUtilisateur?nomUtilisateur='+nomUtilisateur;    
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestUtilisateur(); } ;
        httpRequest.send(null); 
}


function processRequestUtilisateur()
{       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("utilisateur");
              var contenuListUtilisateur="<b><font face='Arial' size='2'>Choix du controleur  :  </font></b>";
              contenuListUtilisateur=" "+contenuListUtilisateur+"<select name='listeDesUtilisateur' onclick='majControleur(listeDesUtilisateur.options[this.selectedIndex].text)' >";
              //alert(node.length);
				  for (i = 0; i < node.length; i++)
				  {	
                var numUtilisateur= xmldoc.getElementsByTagName("idUtilisateur")[i].childNodes[0].nodeValue; 
					var nomPrenomUtilisateur= xmldoc.getElementsByTagName("nomPrenomUtilisateur")[i].childNodes[0].nodeValue; 
	             element= numUtilisateur+"---"+nomPrenomUtilisateur;
                contenuListUtilisateur=contenuListUtilisateur+"<option value="+element+">"+element+"</option>";
               }             	                           
    			 //document.getElementById("linkControleur").innerHTML = "link Controleur";
	
              if (node.length==0)           
              {
                 contenuListUtilisateur=contenuListUtilisateur+"<option>    PAS DE RESULTATS    </option>"; 
                 //document.getElementById("linkControleur").innerHTML = "";
                
              }
               contenuListUtilisateur=contenuListUtilisateur+"</select>"; 
               document.getElementById("listeUtilisateur").innerHTML = contenuListUtilisateur;  
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 




        
    function getProfile(numFacture,choixOuvrage)
    {
       document.forms[0].tailleListe.value=0;
       document.forms[0].anneeTitre.value="";
       document.forms[0].numeroTitre.value="";
        var url = './getInfosFacture?author=' + numFacture;

        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequest(choixOuvrage); } ;
        httpRequest.send(null);        
   }
   
   function getProfile1(numeroTitre,anneeTitre,choixOuvrage)
   {
      //alert("test");
      //alert(numeroTitre);
      //alert(anneeTitre);
       document.forms[0].tailleListe.value=0;
       document.forms[0].idFactue.value="";

        var url = './getInfosFacture?numeroTitre='+numeroTitre+"&anneeTitre="+anneeTitre;
       //alert(url);

        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequest(choixOuvrage); } ;
        httpRequest.send(null);    
   
   }
   
  

    function processRequest(choixOuvrage)
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
              var anneeTitre= xmldoc.getElementsByTagName("anneeTitre")[0].childNodes[0].nodeValue;
              var numTitre= xmldoc.getElementsByTagName("numTitre")[0].childNodes[0].nodeValue;                          
              var numClient= xmldoc.getElementsByTagName("numClient")[0].childNodes[0].nodeValue;
              var nomClient= xmldoc.getElementsByTagName("nomClient")[0].childNodes[0].nodeValue;
              var numeroFacture= xmldoc.getElementsByTagName("numeroFacture")[0].childNodes[0].nodeValue;             
              var lienFacture= xmldoc.getElementsByTagName("lienFacture")[0].childNodes[0].nodeValue;        
              var lienContenuFacture= "./entree?action=gestionFacture.jsp&numeroFacture="+numeroFacture;
              var lienClient="./entree?action=empl_gestion_redevable.jsp&numRedevable="+numClient+
        	       "&choix=modifier&boton=ajouter&typeRecherche=role&optionTransfert=role" +
        	       "&typeRedevable=normal&origine=origine";               
              var node = xmldoc.getElementsByTagName("ouvrage");  
				 var ContentListeOuvrage=" <input type='hidden' name='listeOuvrage'  >";
				
				  for (i = 0; i < node.length; i++)
				  { 
				  	 //ContentListeOuvrage=ContentListeOuvrage +
					 //"<option>"+xmldoc.getElementsByTagName("numeroOuvrage")[i].childNodes[0].nodeValue+
                  //" : "+xmldoc.getElementsByTagName("nomOuvrage")[i].childNodes[0].nodeValue+"  "+"</option>";
                  var numOuvrage= xmldoc.getElementsByTagName("numeroOuvrage")[i].childNodes[0].nodeValue;
                  var nomOuvrage= xmldoc.getElementsByTagName("nomOuvrage")[i].childNodes[0].nodeValue;
                  var nomBareme= xmldoc.getElementsByTagName("nomBareme")[i].childNodes[0].nodeValue;

                  //alert(nomBareme);
                  ContentListeOuvrage=ContentListeOuvrage+
                  "<input type='checkbox' name=Ouv"+numOuvrage+" onclick=mettreAJourTableau(this.name)>"+numOuvrage+ ":  "+ nomOuvrage + "  ------(" + nomBareme+")<BR>";                  
                   document.forms[0].tailleListe.value=eval(document.forms[0].tailleListe.value) + 20;
                 
               } 
               document.getElementById("SectionListeOuvrage").style.height= document.forms[0].tailleListe.value;
				  ContentRedevable="<a href="+lienClient+"  >"+ nomClient +"</a>"; 
				  ContentRedevable=ContentRedevable+"<input type='hidden' name='idRedevable' value='"+numClient +"'> ";					
  				  ContentFacture="<a href=" + lienFacture + " target=\"_blank\" > Lien Facture </a>";					  				  
   				  ContentFacture=ContentFacture + ".      .+<a href=" + lienContenuFacture + " target=\"_blank\" >D'infos sur cette facture... </a>";
  				  ContentTypeTaxe="<input readonly type='text' name='typeTaxe' value="+typeTaxe+">";
  				  
  				  if( node.length==0)
  				  {
  				      ContentListeOuvrage="";
				      ContentRedevable="";					
  				      ContentFacture="";	
  				      ContentTypeTaxe="";
			         alert("Numero de facture introuvable");
  				  }  
  				  if (choixOuvrage=="false") 
  				  {  	
     				  document.forms[0].tailleListe.value	=0;
  				     ContentListeOuvrage= document.forms[0].listeOuvrageObtenu.value;
  				     words = ContentListeOuvrage.split(";");					  
                  ContentListeOuvrage="";
                   for(i=1 ; i<words.length ; i++)
						{
							word = words[i];
							ContentListeOuvrage=ContentListeOuvrage + word +"<BR>";	
							 document.forms[0].tailleListe.value=eval(document.forms[0].tailleListe.value) + 20;

						}	
						 document.getElementById("SectionListeOuvrage").style.height= document.forms[0].tailleListe.value;
											
  				  }
  				  document.getElementById("SectionListeOuvrage").innerHTML = ContentListeOuvrage;
               document.getElementById("SectionFacture").innerHTML = ContentFacture;
			     document.getElementById("SectionRedevable").innerHTML = ContentRedevable;                    
			     document.getElementById("SectionTypeTaxe").innerHTML = ContentTypeTaxe; 
			     document.forms[0].anneeTitre.value=anneeTitre;
			     document.forms[0].numeroTitre.value=numTitre;
   			     document.forms[0].idFactue.value=numeroFacture;

			     
			      
			     }
			     else
			     {
			      document.getElementById("SectionListeOuvrage").innerHTML = "";
               document.getElementById("SectionFacture").innerHTML = "";
			     document.getElementById("SectionRedevable").innerHTML = "";                    
			     document.getElementById("SectionTypeTaxe").innerHTML = "";                                                            
  				 	alert( "Cette facture n'existe pas");

			     }
            
              
            }
            else
            {
                alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
            }
        }
    }   


function verroulerTitre( )
{

}

function verroulerIdFacture( )
{  
   if(document.forms[0].idFactue.value =="")
   {
    	 document.forms[0].idFactue.readOnly= true;
	     document.forms[0].numeroTitre.readOnly= false;
   		  document.forms[0].anneeTitre.readOnly= false;
   }
   else
   {
    alert("Suprimer le numéro de la facture pour accéder à ce champs")
   }

 
}





function init( )
{

  if (document.forms[0].idFactue.value!="")
  {
     getProfile(document.forms[0].idFactue.value,"false");
  }

}

















function verifier(attribut,valeur) 
{
 var Resultat=-1;
 if(valeur.length==0)
 {     
   alert("Le champs "+ attribut+" est obligatoire ! "  );	  
 }
 else
 {
   if (valeur.lastIndexOf(",")!=-1)
   {
      alert("Le caractère , est interdit. Vous pouvez utiliser . à la place.")
   }
   else
   {
    return 0;
   }
 }
 return Resultat;
}


function supprimer()
{
   var choix = confirm("Voulez-vous supprimer cette réclamation ?");
   if (choix) 
   {       
       document.forms[0].choix.value="supprimer";
		document.forms[0].submit();
   } 
}

</script>
</head>
<body background="./images/nuages.jpg" topmargin="0">
<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    Boolean peutAcceder = Boolean.valueOf(beanReclamation.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
%> 


<form method="POST" action="gestionReclamation" >
<input type="hidden" name="choix" value="<jsp:getProperty name="beanReclamation" property="choix"/>" >
<input type="hidden" name="tailleListe" value="0"  >
<input type="hidden" name="listeOuvrageObtenu"  size="29" value="<jsp:getProperty name="beanReclamation" property="listeOuvrage"/>"  >
<input type="hidden" name="listeLibelleDesTypesDeTaxeAutorise" value="<%out.print(listeLibelleDesTypesDeTaxeAutorise);%>" >


<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="5">

<tr>
      <td width="1142" colspan="2" bgcolor="#AFF3BB" height="42">
        <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/reclamation.png" width="40" height="40"></b></font>
      </td>
    </tr>
    <tr>
      <td width="1142" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!beanReclamation.getCreation()){%>  
	              <font size="4"><b>MODIFIER UNE RECLAMATION :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER UNE RECLAMATION :&nbsp</b></font>                 
		      <% } %>                 

        </p>
      </td>
    </tr>
<% if(!beanReclamation.getCreation()){%> 
    <tr>
      <td width="273" height="10">
        <b><font face="Arial" size="2">&nbsp;N° de la réclamation :</font></b>
      </td>
      <td width="867" height="10">
        <input type="text" name="idReclamation" readonly size="29" 
         value="<jsp:getProperty name="beanReclamation" property="idReclamation"/>">	
      </td>
    </tr>
    
<%}%>

    <tr>
      <td width="273" height="7">
        <b><font face="Arial" size="2">&nbsp;Numéro de la facture<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="7">
        <input type="text" name="idFactue" size="29"  
         ONCHANGE=getProfile(document.forms[0].idFactue.value,"true")                 
         value="<jsp:getProperty name="beanReclamation" property="idFactue"/>" >&nbsp;
         <% if(beanReclamation.getCreation()){%>  
           <img border="0" src="./images/ok.gif" width="24" ONclick=getProfile(document.forms[0].idFactue.value,"true")  height="18">
        <%}%>
        
      </td>
    </tr>  
    
    
 <tr>
      <td width="273" height="11">
        <b><font face="Arial" size="2">&nbsp;Numéro du titre :</font></b>      </td>
      <td width="867" height="11">
        <input  type="text" name="numeroTitre" size="29" >&nbsp;&nbsp;&nbsp;&nbsp;
        <b><font face="Arial" size="2">ET&nbsp;</font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <b><font face="Arial" size="2">Année du titre</font><i>   </i>:</b>
        <input  type="text"  name="anneeTitre" size="29" >&nbsp;&nbsp; 
        <% if(beanReclamation.getCreation()){%> 
           <img border="0" src="./images/ok.gif" width="29" ONclick=getProfile1(document.forms[0].numeroTitre.value,document.forms[0].anneeTitre.value,"true")  height="18">
        <%}%>
      </td>
    </tr>  
    
    
     <tr>
      <td width="1140" height="1" colspan="2">
      </td>
    </tr> 
    
    
     <tr>
      <td width="273" height="25">
        <b><font face="Arial" size="2">&nbsp;Lien vers la facture :</font></b>
      </td>
      <td width="867" height="25">
       <div id="SectionFacture" style="width: 802; height: 23"></div>
       </td>
    </tr> 
    
    
     <tr>
      <td width="273" height="38">
        <b><font face="Arial" size="2">&nbsp;Liste des ouvrages liés à la
        réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="38">
        <div id="SectionListeOuvrage" style="width: 807; height: 1">   </div>
       </td>
    </tr> 
    
    
     <tr>
      <td width="273" height="25">
        <b><font face="Arial" size="2">
        &nbsp;Redevable :</font></b>
      </td>
      <td width="867" height="25">
        <div id="SectionRedevable" style="width: 721; height: 23"></div>
       </td>
    </tr> 

    
    
    <tr>
      <td width="273" height="13">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Date
      de la réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="13">
        <input maxlength="10" type="text" name="dateReclamation" id="dateReclamation" size="29" 
         value="<jsp:getProperty name="beanReclamation" property="dateReclamation"/>" >
        
  <% if(beanReclamation.getCreation()){%>  
          
          <img onclick="displayCalendar(document.forms[0].dateReclamation,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" width="16" height="16" />      	
          <% }%>  

      </td>
    </tr>
<% if(!beanReclamation.getCreation()){%> 
      <tr>
      <td width="273" height="22">
        <b><font face="Arial" size="2">
        &nbsp;Nom du redevable :</font></b>
      </td>
      <td width="867" height="22">
        <input type="text" name="nomRedevable" readonly size="29"  
        value="<jsp:getProperty name="beanReclamation" property="nomRedevable"/>"> 
       </td>
    </tr>  
    
<%}%> 

    
    <tr>
      <td width="273" height="22">
        <b><font face="Arial" size="2">
        &nbsp;Type de taxe&nbsp; :</font></b>
      </td>
      <td width="867" height="22">
         <div id="SectionTypeTaxe" style="width: 155; height: 19"></div>
      </td>
    </tr>  
    
  
   

    

 

    
    <tr>
      <td width="273" height="22">
        <b><font face="Arial" size="2">&nbsp;Type de réception de la réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="22">
      <jsp:getProperty name="beanReclamation" property="typeReceptionReclamation"/>
     </td>
    </tr>  
    
  
   

    
    <tr>
      <td width="273" height="22">
        <b><font face="Arial" size="2">&nbsp;Nature de la réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="22">
        <jsp:getProperty name="beanReclamation" property="natureReclamation"/> 
     </td>
    </tr>  
    
  
   

    
    <tr>
      <td width="273" height="39">
        <b><font face="Arial" size="2">&nbsp;Texte de la réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="39">
        <textarea rows="5" name="texteReclamation" cols="84"><jsp:getProperty name="beanReclamation" property="texteReclamation"/></textarea>

      </td>
    </tr>  
    
<% if(!beanReclamation.getCreation()){%>
 

    <tr>
      <td width="273" height="23">
        <b><font face="Arial" size="2">
        &nbsp;Contrôle effectué<span class="Style1"><font color="#FF0000">*</font></span>:</font></b>      </td>
      <td width="867" height="23">    
        <jsp:getProperty name="beanReclamation" property="controleEffectue"/>
      </td>      
    </tr>

<script>
document.forms[0].dateReclamation.readOnly=true;
document.forms[0].numeroTitre.readOnly=true;
document.forms[0].anneeTitre.readOnly=true;
document.forms[0].idFactue.readOnly=true;
//alert(document.forms[0].dateReclamation.readOnly);
</script>
 <tr>
      <td width="273" height="23">
        <b><font face="Arial" size="2">&nbsp;Date
        de contrôle :</font></b>
      </td>
      <td width="867" height="23">
         <input type="text"  name="dateContole" id="dateContole"
         size="29" value="<jsp:getProperty name="beanReclamation" property="dateContole"/>" >&nbsp;
          
  <img onclick="displayCalendar(document.forms[0].dateContole,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" width="16" height="16" />   

 
      </td>
 </tr>
 <tr>
      <td width="273" rowspan="3" height="24">
        <b><font face="Arial" size="2">&nbsp;Contrôleur :</font></b>
      </td>
      <td width="867" height="1">
          <b><font face="Arial" size="2">&nbsp;N° du contrôleur :</font></b>
          <input type="text" readonly =true name="numControleur"  size="29" value="<jsp:getProperty name="beanReclamation" property="numControleur" />" >          
 </td>
 </tr>
 <tr>
      <td width="867" height="18">
          <b><font face="Arial" size="2">&nbsp;Nom du contrôleur :</font></b>
          <input type="text" name="nomControleur"  size="29" value="<jsp:getProperty name="beanReclamation" property="nomControleur"  />" >
          <b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<font face="Arial" size="3"><a href="javascript:rechercheUtilisateur(document.forms[0].nomControleur.value )">
<img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">echercher un contrôleur</font></a></b>
       
          
          
       
       
      </td>
 </tr>

 <tr>
      <td width="867" height="19">
         <div id="listeUtilisateur" style="width: 593; height: 20">   </div>              </td>
 </tr>

    <tr>
      <td width="273" height="17">
        <b><font face="Arial" size="2">&nbsp;Date de la réponse &nbsp; :</font></b>
      </td>
      <td width="867" height="17">
      
        <input type="text" maxlength="10" name="dateReponse" id="dateReponse" size="29"        
        value="<jsp:getProperty name="beanReclamation" property="dateReponse"/>">
        
          
          <img onclick="displayCalendar(document.forms[0].dateReponse,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" width="16" height="16" />      	
        

	 
             
			

      </td>
    </tr>
    <tr>
      <td width="273" height="39">
        <b><font face="Arial" size="2">
        &nbsp;Réponse&nbsp;à la réclamation :</font></b>      </td>
      <td width="867" height="39">
        <textarea rows="4" name="texteReponse" cols="85"><jsp:getProperty name="beanReclamation" property="texteReponse"/></textarea>
      </td>      
    </tr>
    
    
<%  if (peutAcceder) { %>       
<tr>
      <td width="273" height="25">
        <b><font face="Arial" size="2">&nbsp;Annulation de la facture
        :&nbsp;&nbsp;</font></b>      </td>
      <td width="867" height="25">
        <jsp:getProperty name="beanReclamation" property="lienInfosFacture"/>
       </td>
</tr>
    
    
       
<tr>
      <td width="273" height="25">
        <b><font face="Arial" size="2">&nbsp;Courrier de réponse&nbsp;
        :</font></b>
      </td>
      <td width="867" height="25">  
      <table border="0" width="100%" height="1">
        <tr>
          <td width="23%" height="1"><b><font face="Arial" size="2">Modéle</font></b><b><font face="Arial" size="2">
          <span class="Style1"><font color="#FF0000">*</font></span> 
            </font></b> 
             <jsp:getProperty name="beanReclamation" property="listeModelesCouriers"/>
            <b><font face="Arial" size="2">&nbsp; </font></b></td>
          <td width="27%" height="1">
		
             <input type="button" value="Generer" name="Generer" onclick="getLeCourierReponse();"></td>
        
		</tr>
      </table>
      </td>
              
</tr>
    
    <tr>
      <td width="273" height="25">
      &nbsp;-
      </td>
      <td width="867" height="25">
      <div id="courierDeReponse"> - </div>
        
      </td>
    </tr>    
 
<%}%> 
<tr>
      <td width="273" height="25">     
      <b><font face="Arial" size="2">&nbsp;Liste des courriers :</font></b>
      </td>
      <td width="867" height="25">
        <jsp:getProperty name="beanReclamation" property="listeCourierReponse"/>

        
      </td>
</tr>
    
    
<tr>
      <td width="273" height="25">
        <b><font face="Arial" size="2">&nbsp;Etat de la réclamation<span class="Style1"><font color="#FF0000">*</font></span> :</font></b>      </td>
      <td width="867" height="25">
        <jsp:getProperty name="beanReclamation" property="etatReclamation"/>
      </td>
</tr>
    
    
<% }%>







    <tr>
      <td width="273" height="1">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
        Champs obligatoires</i></font></b>
      </td>
      <td width="867" height="1">&nbsp;
        
      </td>
    </tr>
    <tr>
      <td width="1142" colspan="2" height="30">




<table border="0" width="100%" height="1">

<%  if (peutAcceder) { %>
<tr>     
  <% if(!beanReclamation.getCreation()){%> 
  
     
	 <td align="center" width="374" height="1">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/modifier.gif" width="150" height="20"  ></a>
        </p>
      </td>  
      <td align="center" width="375" height="1">
        <p align="center">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20" ></a>
        </p>
      </td>   


 <%} else{ %> 
  <td align="center" width="374" height="1">    
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20"  ></a>    
      </td>
 <% } %>
 
<% } %> 

</tr>
</table>
      </td>
    </tr>    
  </table>  
</form>        

<script>
init();
</script>






<IE:Download ID="marqueedata" STYLE="behavior:url(#default#download)" />
<script language="VBScript">
Sub lanceWord()
  Dim Modele
  Modele = Document.generaleActionForm.modeleCourier.Value
  Dim Data
  Data = Document.generaleActionForm.localFilePath.Value
  Set FileSys = CreateObject("Scripting.FileSystemObject")
  
  If Not FileSys.FileExists(Modele) Then
    Alert "Le fichier Modele n'existe pas : " & Modele
  ElseIf Not FileSys.FileExists(Data) Then
    Alert "Le fichier ficher Donnes n'existe pas :" & Data
  Else
    Set ObjWord = CreateObject("Word.Application")

    ObjWord.Documents.Open Modele, False, False, False

    With ObjWord.ActiveDocument.MailMerge
      .Destination = wdSendToNewDocument
      .OpenDataSource Data, False, False, True, False
      .SuppressBlankLines = True
      .Execute False

    End With

    ObjWord.Documents(Modele).Close SaveChanges=wdDoNotSaveChanges
    ObjWord.Visible = True
    ObjWord.Activate
  End If
End Sub
</script>


<script language="JavaScript1.2">
function downloaddata()
{  
   var cheminOnServer = document.generaleActionForm.remoteFilePath.value; 
   marqueedata.startDownload(cheminOnServer,displaydata)
}

function displaydata(data)
{ 
     
    var fsoForWrite = 2;
	var fso = new ActiveXObject("Scripting.FileSystemObject");  
	var f = document.generaleActionForm.localFilePath.value;
	var objText = fso.OpenTextFile(f, fsoForWrite, true);
	objText.Write(data);
	objText.Close();
	objText = null;
	fso = null;
	setTimeout("lanceWord()",1000);  
}

</script>
  
<form name="generaleActionForm" >
<input type ="hidden" name="remoteFilePath" value="">
<input type ="hidden" name="modeleCourier" value="">
<input type ="hidden" name="localFilePath" value="<jsp:getProperty name="beanReclamation" property="cheminDataCourier"/>" >

</form>
<% if(!beanReclamation.getCreation()){%>
<p align="center"><a href="./entree?action=liste_reclamation.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
<font face="Arial" size="3">iste des réclamations</font></b></font></a>
<%}%>

</body>
</html>








