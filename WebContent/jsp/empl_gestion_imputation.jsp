<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" topmargin="0">



<script type="text/javascript" src="./resources/js/controle.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffImputation" scope="request" class="fr.analogon.r2t.parametres.imputation.BAffImputation"/>
<jsp:setProperty name="baffImputation" property="request" value="<%=request%>"/>

<script type="text/javascript">  


function valider()
{
  verfiLibelle= verifier("Libelle",document.forms[0].libelle.value);
  verfiDesignation= verifier("Designation",document.forms[0].designation.value);
  verfiCodeBudget= verifier("Code Budget",document.forms[0].codeBudget.value);
  verfiCode= verifier("Code tri bordereau",document.forms[0].code.value);
  verfiNom= verifier("No d?enveloppe budgétaire",document.forms[0].section.value);
  verifMinimumPerception=verifier("Minimum de Perception",document.forms[0].minimumPerception.value);
  
  typeMvt=(document.forms[0].typeMouvement.value).toUpperCase();
  sens=(document.forms[0].sens.value).toUpperCase();
  
  if(typeMvt!="R" && typeMvt!="O" && typeMvt!="I"){
	  verfiCode== -1;
	  alert("le type de mouvement doit etre R,O ou I ");
  }
  if(sens!="D" && typeMvt!="R"){
	  verfiCode== -1;
	  alert("le sens doit etre R ou D ");
  }
  
  if (isNaN(document.forms[0].minimumPerception.value))
  {
     verifMinimumPerception =-1 ;
     alert("le verifMinimumPerception doit etre composé de chiffre ! ");
  }
     
  if(verfiLibelle== 0 &&  verfiDesignation== 0 &&  verfiCodeBudget== 0 &&
      verfiCode== 0 && verfiNom== 0 && verifMinimumPerception==0)
  {    
    if( document.forms[0].existeDeja.value =="false" )
    {   
	    //alert("submit");
	    document.forms[0].typeMouvement.value=typeMvt;
	    document.forms[0].sens.value=sens;
  	    document.forms[0].submit();	   
	 }
	 else
	 {
        var libelle= document.forms[0].libelle.value;
        var annee= document.forms[0].anneeExercice.value
        alert("Le type de taxe "+ libelle +" pour l'année "+ annee+ " est déjà defini ");
        
     }
  }
}


function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer ce type de taxe ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";                
				  document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer ce type de taxe , il y a des baremes liés  ");	   
}


function verifierIntegrite(libelleImputation, anneeExercice)
{
        //alert(libelleImputation + " -" + anneeExercice);        
        var url = "./verfierIntegrite?libelleImputation="+libelleImputation+"&anneeExercice="+anneeExercice;
        //alert(url)    ;
        if (window.ActiveXObject)
        {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if (window.XMLHttpRequest)
        {
            httpRequest = new XMLHttpRequest();
        }
        httpRequest.open("GET", url, true);        
        httpRequest.onreadystatechange = function() {processRequestVerifierIntegrite(); } ;
        httpRequest.send(null); 
}  
  
function processRequestVerifierIntegrite()
{       
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("idImputation"); 
              //alert(node.length);
              if (node.length!=0)           
              {       
                document.forms[0].existeDeja.value ="true";
                var libelle= document.forms[0].libelle.value;
                var annee= document.forms[0].anneeExercice.value
                alert("Le type de taxe "+ libelle +" pour l'année "+ annee+ " est déjà defini ");
              } 
              else
              {
                document.forms[0].existeDeja.value ="false";
              } 
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
} 

</script>

<form method="POST" action="gestionImputation" >
<div id="calcontainer"></div>
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="tailleListe" value=0>
<input type="hidden" name="existeDeja" value="false">
<input type="hidden" name="peutEtreSupprimer" value="<jsp:getProperty name="baffImputation" property="peutEtreSupprimer"/>"  >




  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="833">

      
   
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="42">
        <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
      </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!baffImputation.getCreation()){%>  
	              <font size="4"><b>MODIFIER UN TYPE DE TAXE :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER UN TYPE DE TAXE :&nbsp</b></font>                 
		      <% } %>                 

                </p>
      </td>
    </tr>
<% if(!baffImputation.getCreation()){%> 
    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Code du type de taxe&nbsp;</font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" readonly name="numtypeTaxe" size="20" value="<jsp:getProperty name="baffImputation" property="numtypeTaxe"/>" >	
      </td>
    </tr>
    
<%}%>

    <tr>
      <td width="269" height="23">
        &nbsp;<b><font face="Arial" size="2">Année d'exercice </font></b><b><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" readonly name="anneeExercice" size="20" value="<jsp:getProperty name="baffImputation" property="anneeExercice" />" >
        
         </td>
    </tr>     

 <tr>
      <td width="1137" colspan="2" bgcolor="#ffffe8" height="19">
        &nbsp;
      </td>
 </tr>
    
    

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Libellé </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text"  onchange="verifierIntegrite(document.forms[0].libelle.value, document.forms[0].anneeExercice.value);"
        name="libelle" size="60" value="<jsp:getProperty name="baffImputation" property="libelle"/>" >
        
         </td>
    </tr>  
    
    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Désignation </font><i><font face="Arial" size="2" color="#FF0000">*</font></i><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="designation" size="60" value="<jsp:getProperty name="baffImputation" property="designation"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Code budget </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="codeBudget" size="60" value="<jsp:getProperty name="baffImputation" property="codeBudget"/>" >

       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Code tri bordereau&nbsp; </font><i><font face="Arial" size="2" color="#FF0000">*</font></i><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" maxlength="6" name="code" size="60" value="<jsp:getProperty name="baffImputation" property="code"/>" >

       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;No d'enveloppe budgétaire </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="section" size="60" value="<jsp:getProperty name="baffImputation" property="section"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Montant du minimum de perception
        </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="minimumPerception" size="60" value="<jsp:getProperty name="baffImputation" property="minimumPerception"  />" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Montant du minimum de facturation&nbsp;
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="minimumDeFacturation" size="60" value="<jsp:getProperty name="baffImputation" property="minimumDeFacturation"  />" >
       </td>
    </tr> 
 <%-- Paul 07/08/2018 Evolution eCadre Budgetaire [//54]->[//541,//542,//543,//544,//545] --%>
 	<tr>
      <td width="1137" colspan="2" bgcolor="#ffffe8" height="19">
        	<p align="left">    
	           <font size="4"><b>&nbsp;eCadre budgétaire</b></font>
            </p>
      </td>   
    <tr>
      <td colspan="2">
    	<table width="100%">
    	<tr> 
	    	<td width="51%">
	        	<table border="1" width="100%">
		          <tr>
		            <td width="50%"><b><font face="Arial" size="2">Chapitre :</font></b></td>
		            <td><input type="text" name="chapitre" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="chapitre"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Nature :</font></b></td>
		            <td><input type="text" name="nature" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="nature"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Fonction :</font></b></td>
		            <td><input type="text" name="fonction" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="fonction"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code interne de l'opération d'équipement :</font></b></td>
		            <td><input type="text" name="codeOpeEquipement" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeOpeEquipement"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Type mouvement (R=réel,O=ordre de transfert,I=ordre dans la section) :</font></b></td>
		            <td><input type="text" name="typeMouvement" size="60" maxlength="1" value="<jsp:getProperty name="baffImputation" property="typeMouvement"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Sens (D=Dépense, R=Recette) :</font></b></td>
		            <td><input type="text" name="sens" size="60" maxlength="1" value="<jsp:getProperty name="baffImputation" property="sens"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code segmentation structurelle :</font></b></td>
		            <td><input type="text" name="codeSegStructurelle" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegStructurelle"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de l'élément structurel gestionnaire :</font></b></td>
		            <td><input type="text" name="codeEleStructurelleGestionnaire" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleStructurelleGestionnaire"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de l'élément structurel destinataire :</font></b></td>
		            <td><input type="text" name="codeEleStructurelleDestinataire" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleStructurelleDestinataire"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation opérationnelle :</font></b></td>
		            <td><input type="text" name="codeSegOperationnel" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegOperationnel"/>" ></td>
		          </tr>
		          <tr>
		            <td ><b><font face="Arial" size="2">Code élément opérationnelle :</font></b></td>
		            <td><input type="text" name="codeEleOperationnel" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleOperationnel"/>" ></td>
		          </tr>
		          <tr>
		            <td colspan="2" height="23"><b><font face="Arial" size="2"> </font></b></td>
		          </tr>
		        </table>       	
	      	</td>
	     	<td width="50%">
	        	<table border="1" width="100%">
		          <tr>
		            <td width="47%"><b><font face="Arial" size="2">Code de la segmentation 1 :</font></b></td>
		            <td><input type="text" name="codeSegment1" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegment1"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément sectoriel 1 :</font></b></td>
		            <td><input type="text" name="codeEleSectoriel1" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleSectoriel1"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation 2 :</font></b></td>
		            <td><input type="text" name="codeSegment2" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegment2"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément sectoriel 2 :</font></b></td>
		            <td><input type="text" name="codeEleSectoriel2" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleSectoriel2"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation 3 :</font></b></td>
		            <td><input type="text" name="codeSegment3" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegment3"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément sectoriel 3 :</font></b></td>
		            <td><input type="text" name="codeEleSectoriel3" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleSectoriel3"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation 4 :</font></b></td>
		            <td><input type="text" name="codeSegment4" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegment4"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément sectoriel 4 :</font></b></td>
		            <td><input type="text" name="codeEleSectoriel4" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleSectoriel4"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation 5 :</font></b></td>
		            <td><input type="text" name="codeSegment5" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegment5"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément sectoriel 5:</font></b></td>
		            <td><input type="text" name="codeEleSectoriel5" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleSectoriel5"/>" ></td>
		          </tr>
				  <tr>
		            <td><b><font face="Arial" size="2">Code de la segmentation stratégique :</font></b></td>
		            <td><input type="text" name="codeSegStrategique" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeSegStrategique"/>" ></td>
		          </tr>
		          <tr>
		            <td><b><font face="Arial" size="2">Code élément stratégique :</font></b></td>
		            <td><input type="text" name="codeEleStrategique" size="60" maxlength="10" value="<jsp:getProperty name="baffImputation" property="codeEleStrategique"/>" ></td>
		          </tr>
		        </table>       	
	      	</td>
	      	</table> 
	      </td>
    </tr>
     <tr>
      <td width="1137" colspan="2" bgcolor="#ffffe8" height="19">
        &nbsp;
      </td>
 </tr>
    
 <%-- Fin paul--%>
    <tr>
      <td width="269" height="91">
        <b><font face="Arial" size="2">&nbsp;Gestionnaire :</font></b>
      </td>
      <td width="868" height="91">
        <table border="1" width="100%">
          <tr>
            <td width="17%"><font face="Arial" size="2">Nom gestionnaire :</font></td>
            <td width="83%"><input type="text" name="nomGestionnaire" size="60" value="<jsp:getProperty name="baffImputation" property="nomGestionnaire"/>" ></td>
          </tr>
          <tr>
            <td width="17%"><font face="Arial" size="2">Tel gestionnaire :&nbsp;</font></td>
            <td width="83%"><input type="text" name="telGestionnaire" size="60" value="<jsp:getProperty name="baffImputation" property="telGestionnaire" />" ></td>
          </tr>
          <tr>
            <td width="17%"><font face="Arial" size="2">Fax gestionnaire :</font></td>
            <td width="83%"><input type="text" name="faxGestionnaire" size="60" value="<jsp:getProperty name="baffImputation" property="faxGestionnaire" />" ></td>
          </tr>
        </table>
       </td>
    </tr>

    <tr>
      <td width="269" height="120">
        &nbsp;<b><font face="Arial" size="2">&nbsp;Elu :</font></b>
      </td>
      <td width="868" height="120">
        <table border="1" width="100%">
          <tr>
            <td width="17%"><font face="Arial" size="2">Nom Elu
        :&nbsp;&nbsp;</font></td>
            <td width="83%"><input type="text" name="nomElu" size="60" value="<jsp:getProperty name="baffImputation" property="nomElu" />" ></td>
          </tr>
          <tr>
            <td width="17%"><font face="Arial" size="2">Renseignement1 :&nbsp;</font></td>
            <td width="83%"><input type="text" name="eluRenseignement1" size="60" value="<jsp:getProperty name="baffImputation" property="eluRenseignement1" />" ></td>
          </tr>
          <tr>
            <td width="17%"><font face="Arial" size="2">Renseignement2 :&nbsp;</font></td>
            <td width="83%"><input type="text" name="eluRenseignement2" size="60" value="<jsp:getProperty name="baffImputation" property="eluRenseignement2" />" ></td>
          </tr>
          <tr>
            <td width="17%"><font face="Arial" size="2">Renseignement3 :&nbsp;</font></td>
            <td width="83%"><input type="text" name="eluRenseignement3" size="60" value="<jsp:getProperty name="baffImputation" property="eluRenseignement3" />" ></td>
          </tr>
        </table>
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Code fonction :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="codeFonction" size="60" value="<jsp:getProperty name="baffImputation" property="codeFonction"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;Libellé fonction
        :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="libelleFonction" size="60" value="<jsp:getProperty name="baffImputation" property="libelleFonction"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;<span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:FR;mso-fareast-language:
FR;mso-bidi-language:AR-SA">Code compte</span> :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="codeCentreResponsable" size="60" value="<jsp:getProperty name="baffImputation" property="codeCentreResponsable"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;<span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:FR;mso-fareast-language:
FR;mso-bidi-language:AR-SA">Libellé compte</span> :</font></b>
      </td>
      <td width="868" height="23">
        <input type="text" name="libelleCentreResponsable" size="60" value="<jsp:getProperty name="baffImputation" property="libelleCentreResponsable"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="23">
        <b><font face="Arial" size="2">&nbsp;</font><span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:FR;mso-fareast-language:
FR;mso-bidi-language:AR-SA">Code Centre d'exploitation :</span></b>
      </td>
      <td width="868" height="23">
        <input type="text" maxlength="6" name="codeCentreExecution" size="60" value="<jsp:getProperty name="baffImputation" property="codeCentreExecution"/>" >
       </td>
    </tr>

    <tr>
      <td width="269" height="21">
        <b><font face="Arial" size="2">&nbsp;</font><span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:FR;mso-fareast-language:
FR;mso-bidi-language:AR-SA">Libellé Centre d'exploitation&nbsp;:</span></b>
      </td>
      <td width="868" height="21">
        <input type="text" name="libelleCentreExecution" size="60" value="<jsp:getProperty name="baffImputation" property="libelleCentreExecution"/>" >
       </td>
    </tr>

    <tr>
      <td width="1137" colspan="2" bgcolor="#ffffe8" height="21">
        &nbsp;
      </td>
    </tr>

    <tr>
      <td width="269" height="22">
        <b><font face="Arial" size="2">&nbsp;Nécessite un contrôle terrain :</font></b>
      </td>
      <td width="868" height="22">
          <jsp:getProperty name="baffImputation" property="necessiteControleTerrain"/>      
       </td>
    </tr>

    <tr>
      <td width="269" height="22">
        <b><font face="Arial" size="2">&nbsp;Contrôle induit la facturation : </font></b>
      </td>
      <td width="868" height="22">
       <jsp:getProperty name="baffImputation" property="controleInduitFacturation"/> 
       </td>
    </tr>

    <tr>
      <td width="269" height="21">       
        <b><font face="Arial" size="2">&nbsp;Type de facturation : </font></b>
      </td>
      <td width="868" height="21">
        <jsp:getProperty name="baffImputation" property="typeFacturation"/> 
       </td>
    </tr>

    <tr>
      <td width="269" height="22">
        &nbsp;<font face="Arial" size="2"><b>Cycle de facturation :</b></font>
      </td>
      <td width="868" height="22">
      <jsp:getProperty name="baffImputation" property="cycleFacturation"/> 

        
       </td>
    </tr>

    <tr>
      <td width="269" height="22">
        <font face="Arial" size="2"><b>&nbsp;Renouvellement automatique :</b></font>
      </td>
      <td width="868" height="22">
              <jsp:getProperty name="baffImputation" property="renouvellementAutomatique"/> 

       </td>
    </tr>
    
    
    <%
	String marche = (String)session.getAttribute("marche");
	if (  marche !=null && marche.equalsIgnoreCase("true")) 
	{ 
	%>	  
		<tr>
	      <td width="269" height="23">
	        <b><font face="Arial" size="2">&nbsp;Marché </font><font face="Arial" size="2">
	        :</font></b>
	      </td>
	      <td width="868" height="23">
	        <jsp:getProperty name="baffImputation" property="marche"/>
	      </td>
	    </tr> 	   
<% } %>


    <tr>
      <td width="269" height="19">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
        Champs obligatoires</i></font></b>
      </td>
      <td width="868" height="19">&nbsp;
        
       </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" height="30">
        <table border="0" width="100%">
          <tr>

<%
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur");  
if ( typeUtilisateur !=null &&  typeUtilisateur.equalsIgnoreCase("admin") )    
{ 
%>


  <% if(!baffImputation.getCreation()){%>   
     <td align="center">
        <a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>
      </td>
      <td align="center" width="375">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>
      </td>   

 <%}else{%>
    <td align="center" width="374">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20" ></a>
        </p>
      </td>
 <%}%>

<% } %>

          </tr>

        </table>
      </td>
    </tr>

    
  </table>  
</form>

<p align="center">

<a href="./entree?action=liste_imputation.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
<font face="Arial" size="3">iste des types de taxe</font></b></font></a>




<p align="center">&nbsp;</p>

<p align="center">&nbsp;</p>

<p align="center">&nbsp;</p>

</body>

</html>


















