<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>
<jsp:useBean id="baffPrefacturation" scope="page" class="fr.analogon.r2t.view.batch.BaffRolePrefacturation" />

<script type="text/javascript" src="./resources/js/jquery1.7.2.js"></script>
<!-- test du 13082015 -->

<title>Préfacturation</title>
  <script>
  function lancerRaportannuel()
  {
  }
  
  function lancerRaportTrimestiel()
  {
  }

   function changementTaxe()
   {
   
      if (document.getElementById("typeDeTaxe").value == "TLPE")
	  { 
	      //alert("TLPE affichage de l'annneee");		  
		  document.getElementById("divAnneeFacturationTLPE").style.visibility='visible';		  
	  }
	  else
	  {
	     document.getElementById("divAnneeFacturationTLPE").style.visibility='hidden';		
	  }
   }
  
  function trimestrielle()
  {
     //alert("trimestrielle");
     document.forms["filienForm"].choixFilien.value="t";
  }
  
  function annuelle()
  {
      //alert("annuelle");
     document.forms["filienForm"].choixFilien.value="a";
  }
  

  function mensuelleRelance()
  {
     //alert("mensuelleRelance");
     document.forms["relanceForm"].choixRelance.value="m";
  }
  
  
  function trimestrielleRelance()
  {
     //alert("trimestrielleRelance");
     document.forms["relanceForm"].choixRelance.value="t";
  }
  
  function annuelleRelance()
  {
     //alert("annuelleRelance");
     document.forms["relanceForm"].choixRelance.value="a";
  }

  	//Vérifier si le batch est lancé
    function preFacturation()
	{
    	$.ajax( {
  	        type: "POST",
  	        url: "rechercheAction",
  	      	data:{
	        	action:"batchTraitement",
	        	typeTaxe:$("#typeDeTaxe").val()
	        },
  	        dataType: "json",
  	        success: function(response) {
  	        	if (JSON.stringify(response)=="true"){
  	        		alert("Le traitement est déjà lancé ...");
  	        		return
  	        	}
  	        	document.getElementById("etapeFacturation").value = "preFacturation";		
	  	  		contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  	  		contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  	  		contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  	  		contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  	  		contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  	  		contenu=contenu +"   </tr> </table>";
	  	  		document.getElementById("chargement").innerHTML = contenu;
	  	  		document.forms[0].submit();
	  	  		document.getElementById("chargement").innerHTML = contenu;
  	        },
  	        error: function(error) {
  	        	alert("Erreur rechercheAction \n"+ JSON.stringify(error));
  	        	return
  	        }
  	    })
     
	}
	
	


    function lancerRelance()
	{

	  contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
      document.getElementById("chargement").innerHTML = contenu;
      document.getElementById("chargement").innerHTML = contenu;
	  document.forms["relanceForm"].submit();
      
	}


	
	
    function lancerFilen()
	{
	    //alert("preFacturation...");
	  document.getElementById("etapeFacturation").value = "preFacturation";		
	  contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
      document.getElementById("chargement").innerHTML = contenu;
      //document.getElementById("chargement").innerHTML = contenu;
	  //alert("lancement filien trimestrielle");

	  document.forms["filienForm"].submit();
      
	}
	
    //Vérifier si le batch est lancé
    function valider()
    { 
    	$.ajax( {
  	        type: "POST",
  	        url: "rechercheAction",
  	      	data:{
	        	action:"batchTraitement",
	        	typeTaxe:$("#typeDeTaxe").val()
	        },
  	        dataType: "json",
  	        success: function(response) {
  	        	if (JSON.stringify(response)=="true"){
  	        		alert("Le traitement est déjà lancé ...");
  	        		return
  	        	}
  	        	document.getElementById("etapeFacturation").value = "facturation";
	  	  		contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  	  		contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  	  		contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  	  		contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  	  		contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  	  		contenu=contenu +"   </tr> </table>";
	  	  		document.getElementById("chargement").innerHTML = contenu;
	  	  		document.forms[0].submit();
	  	  		document.getElementById("chargement").innerHTML = contenu;
  	        },
  	        error: function(error) {
  	        	alert("Erreur rechercheAction \n"+ JSON.stringify(error));
  	        	return
  	        }
  	    })
    }
  </script>

</head>
<body background="./images/nuages.jpg" topmargin="0">
<%
String ville = (String)session.getAttribute("ville");
String regie = (String)session.getAttribute("regie");
%>

<form method="POST" action="entree?action=empl_prefacturation.jsp">
<input type="hidden" name="etapeFacturation" id="etapeFacturation">


    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="100%" bordercolor="#00CA00" height="143">
		  <tr>
			  <td width="100%" bgcolor="#AFF3BB" colspan="2" height="42">
				<p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
			  </td>
		  </tr>
		  <tr>
			  <td width="100%" bgcolor="#AFF3BB" colspan="2" height="22">
				  <b><font size="4">LANCEMENT DE BATCH DE FACTURATION :</font></b><font size="3" face="Arial"><b>&nbsp
				  </b></font>
			  </td>
		  </tr>
		  <tr>
			<td width="31%" background="./images/fond_bleu.gif" align="left" height="25">
			  <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Date de création :
			  </span></b></font>&nbsp;
			</td>
			<td width="169%"  height="25">
			  <input type="text" maxlength="10" name="anExercice" size="15" id="anExercice"
			  value="<jsp:getProperty name="baffPrefacturation" property="anExercice" />">
				
			<img onclick="displayCalendar(document.forms[0].anExercice,'dd/mm/yyyy',this)"
			src="./images/calendar.gif" border="0" width="20" height="18" />
			
			</td>
		  </tr>
		  <tr>
			<td width="31%"  align="left" background="images/fond_bleu.gif" height="25">
			  <p align="left"><font face="Arial" size="3">&nbsp;</font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Type
			  de taxe :</span></b></font><font face="Arial" size="3">&nbsp;&nbsp;</font>
			  </p>
			</td>
			<td width="169%" size="3" Arial" height="25">
			<jsp:getProperty name="baffPrefacturation" property="tousTypeDeTaxe" />
			</td> 
		  </tr>
		  <%-- --%>
		  
   		  <tr id="divAnneeFacturationTLPE">
			<td width="31%"  align="left" background="images/fond_bleu.gif" height="25">
			  <p align="left"><font face="Arial" size="3">&nbsp;</font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Année
			  facturation&nbsp;TLPE :</span></b></font><font face="Arial" size="3">&nbsp;&nbsp;</font>
			  </p>
			</td>
			<td width="169%" size="3" Arial" height="25"><jsp:getProperty name="baffPrefacturation" property="anneeFacturationTLPE"/>    
<!-- 	     <select name="anneeFacturationTLPE" id="anneeFacturationTLPE" size="1">
               <option value="2014" >2014</option>
               <option value="2013" selected>2013</option>
               <option value="2012" >2012</option>
               <option value="2011" >2011</option>
			   <option value="2010" >2010</option>
               <option value="2009" >2009</option>
          	</select>
 -->	
			</td> 
		  </tr>	  
		 <tr>
			<td width="100%" colspan="2" align="center" height="26">
				  <% if (ville.equalsIgnoreCase("Toulon") || true  ) { %>   
						<font size="3" face="Arial"><a href="javascript:preFacturation();"><img src="images/lettre_p.gif" align="absmiddle" border="0" width="20" height="20">refacturation</a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <font size="3" face="Arial"><a href="javascript:valider();"><img src="images/lettre_f.gif" align="absmiddle" border="0" width="20" height="20">acturation</a></font><a href="javascript:facturer();" target="bas">    </a>
				   <%}else {%>
					  <a href="javascript:valider();" target="bas">
					   <img border="0" src="./images/valider.gif" width="150" height="20"></a>&nbsp;
				  <%} %>

			</td>
		  </tr>
	</table>
	
				  <div id="chargement" style="width: 1140; height: 71" > 
				 </div>

 
	
 
  </form>
  
<BR>

<form name="filienForm"  method="POST" action="entree?action=empl_filien.jsp">
<input type="hidden" name="choixFilien" id="choixFilien" value="t">
 <% if ( regie !=null && regie.equalsIgnoreCase("true") ) { %>   

    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="1147" bordercolor="#00CA00" height="117">
      <tr>
          <td width="1143" bgcolor="#AFF3BB" colspan="2" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
      </tr>
      <tr>
          <td width="1143" bgcolor="#AFF3BB" colspan="2" height="22">
            <p>
              <b><font size="4">LANCEMENT BATCH FILIEN :</font></b><font size="3" face="Arial"><b>&nbsp;
              </b></font>
          </td>
      </tr>


     <tr>
        <td width="148" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Trimestrielle</span></b></font>&nbsp;:
        </td>
        <td width="993" align="left" height="23">
          <input type="radio" value="trimestrielle" checked name="type" onclick=javascript:trimestrielle(); >Trimestrielle&nbsp;
          <select size="1" name="numeroTrimestreFilien">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
          </select>
        </td>
      </tr>
      
      
      
      
      <tr>
        <td width="148" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Anuelle</span></b></font>&nbsp;:
        </td>
        <td width="993" align="left" height="23">
          <input type="radio" value="annuel" name="type" onclick=javascript:annuelle(); >Annuelle&nbsp;&nbsp;
        </td>
      </tr>
      
      
      
      
      <tr >
        <td width="1143"  align="left"  height="1" colspan="2">
          <p align="center"><font size="3" face="Arial"><a href="javascript:lancerFilen();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          filien </a></font>
        </td>
      </tr>
   

    </table>
<p>&nbsp;</p>

</form>

<form name="relanceForm"  method="POST" action="entree?action=empl_relance.jsp">
	<input type="hidden" name="choixRelance" id="choixRelance" value="m">

    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="1147" bordercolor="#00CA00" height="117">
      <tr>
          <td width="1143" bgcolor="#AFF3BB" colspan="2" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
      </tr>
      <tr>
          <td width="1143" bgcolor="#AFF3BB" colspan="2" height="22">
            <p>
              <b><font size="4">RELANCE :</font></b><font size="3" face="Arial"><b>&nbsp;
              </b></font>
          </td>
      </tr>
      <tr>
        <td width="148" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Mensuelle</span></b></font>&nbsp;:
        </td>
        <td width="993" align="left" height="23">
          <input type="radio" value="mensuelle" checked name="typeRelance" onclick=javascript:mensuelleRelance(); >Mensuelle&nbsp;
          &nbsp;&nbsp;<select size="1" name="periodeRelanceMois">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
      </tr>


      <tr>
        <td width="148" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Trimestrielle</span></b></font>&nbsp;:
        </td>
        <td width="993" align="left" height="23">
          <input type="radio" value="trimestrielle" name="typeRelance" onclick=javascript:trimestrielleRelance(); >Trimestrielle&nbsp;
          <select size="1" name="periodeRelanceTrimestre">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
          </select>
        </td>
      </tr>


     <tr>
        <td width="148" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">&nbsp;Anuelle</span></b></font>&nbsp;:
        </td>
        <td width="993" align="left" height="23">
          <input type="radio" value="annuel" name="typeRelance" onclick=javascript:annuelleRelance(); >Annuelle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
      
      
      
      
      <tr >
        <td width="1143"  align="left"  height="1" colspan="2">
          <p align="center"><font size="3" face="Arial"><a href="javascript:lancerRelance();">
          <img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          relance</a></font>
        </td>
      </tr>
   

    </table>
<p>&nbsp;</p>






</form>



<% if (false) { %>  
    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="100%" bordercolor="#00CA00" height="162">
      <tr>
          <td width="200%" bgcolor="#AFF3BB" colspan="2" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
      </tr>
      <tr>
          <td width="200%" bgcolor="#AFF3BB" colspan="2" height="22">
            <p>
              <b><font size="4">LANCEMENT DE BALANCE :</font></b><font size="3" face="Arial"><b>&nbsp;
              </b></font>
          </td>
      </tr>
      <tr>
        <td width="22%" background="./images/fond_bleu.gif" align="left" height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Balance
          trimestrielle</span></b></font>&nbsp;:
        </td>
        <td width="204%" align="left" height="23">
          <select size="1" name="numeroTrimestre">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
          </select>
        </td>
      </tr>


      <tr >
        <td width="226%"  align="left"  height="30" colspan="2">
          <p align="center"><font size="3" face="Arial"><a href="javascript:lancerRaportTrimestiel();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          balance trimestrielle</a></font>
        </td>
      </tr>
   

      <tr >
        <td width="22%"  align="left" background="images/fond_bleu.gif" height="24">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Balance annuelle
          :</span></b></font>
        </td>
        <td width="204%"  align="left" height="24">
          <input type="text" maxlength=4 name="annee" size="14" value="2013">
        </td>
      </tr>
   
      <tr>
        <td width="200%" colspan="2" align="center" height="39">
              <font size="3" face="Arial"><a href="javascript:lancerRaportannuel();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
              balance annuelle</a></font>
        </td>
      </tr>
    </table>
    <% } %>  
<% } %>  

<script>
changementTaxe();
</script>

</body>
</html>


	  















