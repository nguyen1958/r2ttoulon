<%@ page import="java.util.Vector"%> 
<html>
<head>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<jsp:useBean id="baffListeAlertes" scope="page" class="fr.analogon.r2t.view.alertes.BAffListeAlertes" />
<jsp:setProperty name="baffListeAlertes" property="request" value="<%=request%>"/>
<script type="text/javascript" src="./diaporama/highslide/highslide-with-gallery.js"></script>
<link rel="stylesheet" type="text/css" href="./diaporama/highslide/highslide.css" />
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

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




<script> 
  
<!--Generation des images des alerte -->
<%=baffListeAlertes.getTableauImagesAlerteSpontanne()%>

function modifier()
{ 
      //alert("VALIDER ALERTE");
	  document.forms[0].action="gestionalertes";	     
  	  document.forms[0].actionAlerte.value="modifierAlerte";
      document.forms[0].submit();
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
 
}

function supprimerImageAlerte()
{
            ret=confirm("Etes-vous sûr de supprimer cette image ?");            
            if (ret == true)
            {                
	             document.forms[0].action= "gestionalertes";
	             document.forms[0].actionAlerte.value= "modifierAlerte";	             
	             document.forms[0].imageASupprimer.value=document.forms[0].nomImageAlerte.value;					            
	             //alert("submit");
                document.forms[0].submit();                
            }
}


function initImagesAlerte()
{   
		tableauImageAlerte= getTableauImagesAlerte();	
		document.forms[0].numeroImageAlerte.value=0;
		document.forms[0].tailleTableauAlerte.value=tableauImageAlerte.length;
		var nomFichierAlerte= tableauImageAlerte[0][0].substr(17,tableauImageAlerte[0][0].length); 
		document.forms[0].nomImageAlerte.value=nomFichierAlerte;
		document.forms[0].dateCreationImageAlerte.value=tableauImageAlerte[0][1];
		document.getElementById('picAlerte').src=tableauImageAlerte[0][0]; 
		afficherNumeroImageAlerte();
}

	  
function verifier(attribut,valeur) 
{
 var Resultat=-1;
 if(valeur.length==0)
 {     
   alert("Le champs est obligatoire: " + attribut + " n'a pas été rempli.");	  
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
  
    
function supprimerAlerte()
{
     if (confirm("Vous désirez vraiment supprimer cette alerte ?")) 
      {
  	     document.forms[0].actionAlerte.value="supprimerAlerte";
  	     document.forms[0].action="gestionalertes";	 
		 document.forms[0].choix="supprimer";			 
         document.forms[0].submit();
      }      
}

</script>
</head>

<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    Boolean peutAcceder = Boolean.valueOf(baffListeAlertes.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
%>
  
  
<body background="./images/nuages.jpg" topmargin="0" vlink="#0000FF" alink="#0000FF">
<div id="calcontainer"></div>
<form name=f method="POST" action="gestionalertes">
<input type="hidden" name="tailleTableauEmplacement"  >  
<input type="hidden" name="imageASupprimer"  >  
<input type="hidden" name="choix"  >  
<input type="hidden" name="numeroImageAlerte" value="" >
<input type="hidden" name="tailleTableauAlerte"  >
<input type="hidden" name="actionAlerte"  >




   
<%=baffListeAlertes.getIdAlerteSpontanne()%>

<table border="1" cellpadding="0" cellspacing="0" width="1144" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="464">
      <tr>
      <td width="1054" colspan="2" bgcolor="#AFF3BB" height="22">
        <p align="center"><img border="0" src="images/exclamation.jpg" width="40" height="40"></td>
      </tr>
      <tr>
      <td width="1054" colspan="2" bgcolor="#AFF3BB" height="22"><span style="text-transform: uppercase"><b><font size="4">INFORMATION
        ALERTE HORS OUVRAGE&nbsp; :</font></b></span></td>
      </tr>
      <tr>
        <td width="225" height="23">
          <b>Numéro de l'Alerte : </b>
        </td>
        <td width="911" height="23">
          &nbsp;<input readonly type="text" name="numeroAlerte" size="15" 
          value="<jsp:getProperty name="baffListeAlertes" property="idAlerte"/>" >
        </td>
      </tr>
      <tr>
        <td width="225" height="23">
          <b>Date de création de l'Alerte :</b>
        </td>
        <td width="911" height="23">
          &nbsp;<input readonly type="text" name="dateCreationAlerte" size="15"  
          value="<jsp:getProperty name="baffListeAlertes" property="dateAlerte"/>"  >
        </td>
      </tr>
      <tr>
        <td width="225" height="23">
          <b>Adressse&nbsp; :</b>
        </td>
        <td width="911" height="23">
          &nbsp;<input readonly type="text" name="dateCreationAlerte" size="54"  
          value="<jsp:getProperty name="baffListeAlertes" property="adresseOuvrage" />"  >
        </td>
      </tr>
      <tr>
        <td width="225" height="23">
          <b>Quartier&nbsp; :</b>
        </td>
        <td width="911" height="23">
          &nbsp;<input readonly type="text" name="dateCreationAlerte" size="54"  
          value="<jsp:getProperty name="baffListeAlertes" property="nomQuartier" />"  >
        </td>
      </tr>
      <tr>
        <td width="225" height="19">
          <b>Contrôleur : </b>
        </td>
        <td width="911" height="19">
            <b>
               <jsp:getProperty name="baffListeAlertes" property="controleurAlerte"/>
            </b>
          </a>
        </td>
      </tr>
      <tr>
        <td width="225" height="46">
          <b>Texte de l'Alerte :</b>
        </td>
        <td width="927" height="46">
          <textarea rows="3" name="textAlerte" cols="85" style="font-weight: bold"><jsp:getProperty name="baffListeAlertes" property="textAlerte"/></textarea>
        </td>
      </tr>
      <tr>
        <td width="1122" height="14" colspan="2" bgcolor="#AFF3BB">
          <p align="left"><b><font size="4"><span style="text-transform: uppercase">Liste des
          photos - ALERTE :</span></font></b>
          </p>
        </td>
      </tr>
      
      
<% String nombreImageAlerte= baffListeAlertes.getNombreImageAlerteSpontanne();
if( ! nombreImageAlerte.equalsIgnoreCase("0")) {%>
<tr>
        <td width="225" height="446">
          


 <table border="1" width="100%" height="457">
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF">
      <b>
      <font color="#000080" size="3">Nom de
              l'Image&nbsp; </font></b>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19">
                  <p align="center"><b><input type="text" name="nomImageAlerte" 
                  readonly size="26" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></p>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><b><font color="#000080">Date de
              Création</font></b>
      <b>
      &nbsp;</b></td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19"><center><b><input type="text" name="dateCreationImageAlerte" readonly size="25" style="font-family: Arial; text-transform: uppercase; font-weight: bold; text-align: center; border-style: dotted"></b></center>
                </td>
              </tr>
              <tr>
                <td width="465%" align="center" colspan="2" height="19" bgcolor="#DFFFEF"><b><font color="#000080">N°
                  Image</font></b> <b>&nbsp;</b></td>
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
                <font color="#000080">Supprimer Image Alerte</font></a><font color="#000080">&nbsp;</font></b></td>
              </tr>
              <% } %>  
              
              
              
             <tr>
                <td width="464%" align="center" colspan="2" height="293">
                  <p>DIAPORAMA
                  
                <div class="highslide-gallery">
					<a id="thumb1" href='./diaporama/images/diaporama.jpg' 
						onclick="return diaporama1.expand(this, miniGalleryOptions1)">
						<img src='./diaporama/images/diaporama.jpg' alt=''/>			
					</a>
					
				<% 
				Vector imagesAlerte = baffListeAlertes.getImagesAlerte();
				for (int i = 0; i < imagesAlerte.size() ; i++) 
				{ 
				  String nomImage =(String)imagesAlerte.elementAt(i);
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
            
        </td>
        <td width="911" height="446">
          <p align="center"><img border="0" id="picAlerte" src="" width="863" height="488" >
        </td>
      </tr>
<%}else{%>
     

      <tr>
        <td width="1122" height="19" colspan="2">
                <p align="center">
                <b>
				Pas d'image disponible pour cette alerte</b></p>
        </td>
      </tr>
  <%}%>
      <tr>
        <td width="225" height="19">
          <b>&nbsp;Remarque :</b>
        </td>
        <td width="927" height="46">          
          <textarea rows="2" name="remarqueAlerte" cols="82" style="font-weight: bold"><jsp:getProperty name="baffListeAlertes" property="remarque"/></textarea>
        </td>
      </tr>
  <center>
      <tr>
        <td width="225" height="19">
          &nbsp;<b>Etat de l'alerte :</b>
        </td>
    </center>
        <td width="911" height="19">
          <p align="left"><jsp:getProperty name="baffListeAlertes" property="etatAlerte"/>
        </td>
      </tr>
     <tr>
        <td width="225" height="19">
          <b>&nbsp;Date de fin de l'alerte :</b>
        </td>
        <td width="911" height="19">
          <p align="left">
          <input ReadOnly type="text" name="dateFinAlerte" id="dateFinAlerte" size="15"  
          value="<jsp:getProperty name="baffListeAlertes" property="dateFinAlerte"/>"><b>
          
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
        <td width="1052" colspan="2" height="19">
          <p align="center"><a href="javascript:modifier();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>&nbsp;
          <a href="javascript:supprimerAlerte();"><img border="0" src="./images/supprimer.gif" width="150" height="20"></a>
        </td>
      </tr>     
<% } %>



    </table>
</center>
<%if( ! nombreImageAlerte.equalsIgnoreCase("0")) {%>
<script>initImagesAlerte(); </script>
<%}%>

</form>

<p align="center"><a href="./entree?action=liste_alertes.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">iste des alertes</font></b></font></a>

</body>
</html>
































