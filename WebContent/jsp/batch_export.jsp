
<html>
<head>


 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

 
<%@ page language = "java" %>
  <jsp:useBean id="baffExport" scope="request" class="fr.analogon.r2t.view.export.BaffExport" />
  <jsp:setProperty name="baffExport" property="request" value="<%=request%>"/>

  <script type="text/javascript" src="./resources/js/jquery1.7.2.js"></script>
  <script>  
  	$(function(){
  		$("#typeTaxe").change(function(){
  			rechercheBareme($(this).val())
  		});
  		
  		function rechercheBareme(typeTaxe){
  			$.ajax( {
  	  	        type: "GET",
  	  	        url: "rechercheBareme",
  	  	        data: "typeTaxe="+typeTaxe,
  	  	        dataType: "xml",
  	  	        success: function(xml) {
  	  	        	console.log(xml);
  	  	        	processRequestListBareme(xml);
  	  	        },
  	  	        error: function(xhr, textStatus, thrownError) {
  	  	        	alert("Erreur chargement page \n"+ textStatus +":"+thrownError);
  	  	        }
  	  	    });
  		}
  		
  		function processRequestListBareme(xmldoc)
  		{
  			var node=$(xmldoc).find("ligne");
  			var contenu="<select name='typeBareme'>";
	   		contenu+="<option value=''></option>";
  			if (node.length!=0){	   	 
		    	node.each(function(index){
		      	val= $(this).attr("key")
				element= $(this).text();
				contenu+="<option value='"+val+"'>"+element+"</option>";  
				});
  		    }
  			contenu+="</select>";
	      	  $("#blocBareme").html(contenu);
  		} 
  		
  		//Chargement des baremes 
  		rechercheBareme($("#typeTaxe").val())
  	
  				
  	});
  	
  </script> 

  <script>
    function ok()
    {
      if ( isNaN(document.forms[0].numRedevable.value))
      	alert("Le numéro du redevable doit etre un nombre ! ");
      else   
      	document.forms[0].submit();
    }

  </script>
</head>
<body background="./images/nuages.jpg" topmargin="0">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Export - Recherche&nbsp;&nbsp;</font></p></td>
  </tr>
  </table>
<form method="POST" action="entree?action=batch_resultat_export.jsp" onSubmit="ok()" >
  <input type="hidden" name="origine" value="rechercheRedevable"/>
  <div align="center">
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="387">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
            détaillées.</font></td>
        </tr>
<!-- Redevable -->     
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">REDEVABLE :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
          </td>
        </tr>		    
        <tr>
          <td background="./images/fond_bleu.gif" width="400" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">N°
            Redevable&nbsp; :</font></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="numRedevable" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font face="Arial" size="2"><b>&nbsp;Nom du redevable :&nbsp;</b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="nomRedevable" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font face="Arial" size="2"><b>&nbsp;Prénom du redevable :&nbsp;</b></font>
          </td>
         <td width="239" height="23">
            <input type="text" name="prenomRedevable" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            &nbsp;<font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Redevable
            actif :</span></b></font>
          </td>
          <td width="239" height="23">
            <select size="1" name="redevbaleActif">
            <option value="true" selected>OUI</option>
            <option value="false">NON</option>
            <option value="">TOUS</option>
            </select>                    
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
		<tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font face="Arial" size="2"><b>&nbsp;Profession :&nbsp;</b></font>
          </td>
          <td width="239" height="24">
           <jsp:getProperty name="baffExport" property="listeProfessions"/>            
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
<!-- Emplacement -->        
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">EMPLACEMENT :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font face="Arial" size="2"><b>&nbsp;Nom commercial de l'emplacement :&nbsp;</b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="raisonSociale" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            &nbsp;<font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">N°
            emplacement :</span></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="numEmplacement" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Etat
            de l'emplacement :</span></b></font>
          </td>
          <td width="239" height="23">           
           
          <select size="1" name="etatEmplacement">
            <option selected value="">Tous</option>
            <option value="enactivite">En Activité</option>
            <option value="termine">Terminé</option>
          </select>           
           
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>     
        
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font color="#000000"><b><font face="Arial" size="2"><span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA"></span></font></b></font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;N°
            secteur de l'emplacement :</span></b></font>
          </td>
          <td width="239" height="24">           
            <jsp:getProperty name="baffExport" property="numSecteur"/> 
          </td>
          <td width="631" height="24">
            &nbsp;</td>
        </tr>
        
             <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            &nbsp;<span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA"><b><font face="Arial" size="2" color="#000000">Quartier
            </font></b></span><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">
            de l'emplacement&nbsp;</span></b></font><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA"><b><font face="Arial" size="2" color="#000000">
            :</font></b></span>
          </td>
          <td width="239" height="24">
            <jsp:getProperty name="baffExport" property="nomQuartier"/>    
          </td>
          <td width="631" height="24">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            &nbsp;<font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">N°
            dans la rue </span></b></font>
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA"> de
            l'emplacement :</span></b></font>
          </td>
          <td width="239" height="24">
            <input type="text" name="numVoieEmplacement" size="33" >
          </td>
          <td width="631" height="24">
            &nbsp;
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Nom
            de la rue de l'emplacement :</span></b></font>
          </td>
          <td width="239" height="24">
            <input type="text" name="nomVoieEmplacement" size="33" >
          </td>
          <td width="631" height="24">
            &nbsp;
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;</span></b></font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Type
            de taxe :</span></b></font>
          </td>
          <td width="239" height="24">
            <jsp:getProperty name="baffExport" property="listeTaxe"/>            
          </td>
          <td width="631" height="24">
            &nbsp;
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Type
            de barème :</span></b></font>
          </td>
          <td width="239" height="24" id="blocBareme">
           <jsp:getProperty name="baffExport" property="listeBareme"/>            
          </td>
          <td width="631" height="24">
            &nbsp;
          </td>
        </tr>
<!-- Facturation -->       
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">FACTURATION :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
          </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">N°
            de la facture&nbsp; :</font></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="numFacture" size="33" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Etat
            de la facture :</span></b></font>
          </td>
          <td width="239" height="23">
          	  <jsp:getProperty name="baffExport" property="etatFacture"/>                                         
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Montant
            de la facture&nbsp; :</font></b></font>
          </td>
          <td width="350" height="23">
          	<span>Min</span>
            <input type="text" name="montantFactureMin" size="20" >
            <span>&nbsp;&nbsp;Max</span>
            <input type="text" name="montantFactureMax" size="20" >
          </td>
          <td width="631" height="23">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Date de création 
            de la facture&nbsp; :</font></b></font>
          </td>
          <td width="239" height="23">
          	<span> De </span>                     
            <input maxlength="10" type="text" name="dateFactureMin" id="dateFactureMin" size="12" />       
            <img onclick="displayCalendar(document.forms[0].dateFactureMin,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
            <span>&nbsp;&nbsp;à </span>
            <input maxlength="10" type="text" name="dateFactureMax" id="dateFactureMax" size="12" />       
            <img onclick="displayCalendar(document.forms[0].dateFactureMax,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
          </td>   
          <td width="631" height="23">
            &nbsp;</td>
        </tr>  
        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">Année
            &nbsp; :</font></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="anneeFacture" size="12" >
          </td>
          <td width="631" height="23">
            <a href="javascript:ok();">                
              <img border="0" src="./images/ok.gif" width="20" height="20" align="left">         
             </a>
          </td>
        </tr>         
	</table>
  </div>
  
 
</form>

</body>

</html>
