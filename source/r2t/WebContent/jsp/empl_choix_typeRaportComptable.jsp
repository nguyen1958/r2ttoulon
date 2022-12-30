<html>
<head>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>
<jsp:useBean id="baffPrefacturation" scope="page" class="fr.analogon.r2t.view.batch.BaffRolePrefacturation" />

<title>Rapports comptable</title>
  <script>
  
  function setUrl(element){
	  var dateDebut=document.getElementById("datedebutHistoriqueControle").value;
	  var dateFin=document.getElementById("datefinHistoriqueControle").value;
	  if(dateDebut=="" && dateFin==""){
		  alert("Les dates sont obligatoires");
		  return false;
	  }
	  
	  var url = "${pageContext.request.contextPath}/gestionRapportHistoriqueControle?"+
			    "datedebut="+document.getElementById("datedebutHistoriqueControle").value+
			  	"&datefin="+document.getElementById("datefinHistoriqueControle").value;
	  element.setAttribute("target","_blank");
	  element.setAttribute("href",url)
  }
  
  function lancerRaportComptableMarche()
  
  {      
      //alert("Lancement de raport Comptable Marché");	 
	  document.forms[0].action="gestionRapportComptableMarche";
	  contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"100%\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
      document.getElementById("chargement").innerHTML = contenu;
	  document.forms[0].submit();
	 
  }
  
   function lancerRaportJournalier()
   {      
     //alert("RJ");	 
	 document.forms[0].action="gestionRapportComptableJournalier";
	 	contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu;
	 document.forms[0].submit();
	 
   }
   
   function lancerRaportMensuel()
   {      
     //alert("RM");
	 document.forms[0].action="gestionRapportComptableMensuel";	
	 	contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu; 
	 document.forms[0].submit();
   }
     function lancerRaportTrimestriel()
   {      
     //alert("RT");
	 document.forms[0].action="gestionRapportComptableTrimestriel";	 
	 	contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu;
	 document.forms[0].submit();
   }
   
     function lancerRaportannuel()
   {      
     //alert("RA");
	 document.forms[0].action="gestionRapportComptableAnnuel";	 
	 	contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu;
	 document.forms[0].submit();
   }
  
  

  </script>

</head>
<body background="./images/nuages.jpg" topmargin="0">

<form method="POST">
<input type="hidden" name="choix"  value="lancer">

  <div align="center">
    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="100%" bordercolor="#00CA00" height="143">
      <tr>
          <td width="3426" bgcolor="#AFF3BB" colspan="3" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
      </tr>
      <tr>
          <td width="3426" bgcolor="#AFF3BB" colspan="3" height="22">
            <p>
              <b><font size="4">LANCEMENT DE BATCHS - RAPPORTS COMPTABLES :</font></b><font size="3" face="Arial"><b>&nbsp
              </b></font>
          </td>
      </tr>


      <tr >
        <td background="images/fond_bleu.gif" width="223"   align="left"  height="18">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Journaux
          comptables journaliers&nbsp;</span></b></font>
        </td>
        <td width="148"  align="left"  height="18">
          <input type="text" maxlength="10" name="dateRaportJournalier" size="15" id="dateRaportJournalier"
          value="<jsp:getProperty name="baffPrefacturation" property="anExercice" />">
          	
		<img onclick="displayCalendar(document.forms[0].dateRaportJournalier,'dd/mm/yyyy',this)"
		src="./images/calendar.gif" border="0" width="20" height="18" />
				
			
        </td>
        <td width="496"  align="left"  height="18">
          <font size="3" face="Arial"><a href="javascript:lancerRaportJournalier();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
      

      <tr >
        <td background="images/fond_bleu.gif" width="223"  align="left"  height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Journaux
          comptables mensuels </span></b></font>
        </td>
        <td width="148"  align="left"  height="23">
          <select size="1" name="numeroMois">
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
          </select>
        </td>
        <td width="496"  align="left"  height="23">
          <font size="3" face="Arial"><a href="javascript:lancerRaportMensuel();">
		  <img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
   

      <tr >
        <td width="223"  background="images/fond_bleu.gif" align="left"  height="27">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Journaux
          comptables trimestriels</span></b></font>
        </td>
        <td width="148"  align="left"  height="27">
          <select size="1" name="numeroTrimestre">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
          </select>
        </td>
        <td width="496"  align="left"  height="27">
          <font size="3" face="Arial"><a href="javascript:lancerRaportTrimestriel();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
   

      <tr >
        <td background="images/fond_bleu.gif" width="223"  align="left"  height="28">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Journaux
          comptables annuels</span></b></font>
        </td>
        <td width="148"  align="left"  height="28">
          <input type="text" name="annee" size="14" value="2013">
        </td>
        <td width="496"  align="left"  height="28">
          <font size="3" face="Arial"><a href="javascript:lancerRaportannuel();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
      
      
      
      <tr>
        <td background="images/fond_bleu.gif" width="223"   align="left"  height="18">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Rapports Comptable Marché&nbsp;</span></b></font>
        </td>
        <td width="148"  align="left"  height="18">
          <input type="text" maxlength="10" name="dateRaportComptableMarche" size="15" id="dateRaportComptableMarche"
          value="<jsp:getProperty name="baffPrefacturation" property="anExercice" />">
        <img onclick="displayCalendar(document.forms[0].dateRaportComptableMarche,'dd/mm/yyyy',this)"
		src="./images/calendar.gif" border="0" width="20" height="18" />
		</td>
        <td width="496"  align="left"  height="18">
          <font size="3" face="Arial"><a href="javascript:lancerRaportComptableMarche();"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
  <!-- Rapports pour tablette -->    
      <tr >
        <td background="images/fond_bleu.gif" width="223"  align="left"  height="23">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">
          Liste des emplacements libres non traités </span></b></font>
        </td>
        <td width="148"  align="left"  height="23">
        </td>
        <td width="496"  align="left"  height="23">
          <font size="3" face="Arial"><a target="_blank" href="${pageContext.request.contextPath}/gestionRapportEmplacementLibre">
		  <img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
      <tr>
        <td background="images/fond_bleu.gif" width="223"   align="left"  height="18">
          <font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">
          Historique contrôle [date début / date fin]</span></b></font>
        </td>
        <td width="160"  align="left"  height="18">
          	<input type="text" maxlength="10" name="datedebutHistoriqueControle" size="15" id="datedebutHistoriqueControle"
          		value="<jsp:getProperty name="baffPrefacturation" property="anExercice" />">
        		<img onclick="displayCalendar(document.forms[0].datedebutHistoriqueControle,'dd/mm/yyyy',this)"
				src="./images/calendar.gif" border="0" width="20" height="18" />&nbsp;&nbsp;
		 	<input type="text" maxlength="10" name="datefinHistoriqueControle" size="15" id="datefinHistoriqueControle"
          		value="<jsp:getProperty name="baffPrefacturation" property="anExercice" />">
        		<img onclick="displayCalendar(document.forms[0].datefinHistoriqueControle,'dd/mm/yyyy',this)"
				src="./images/calendar.gif" border="0" width="20" height="18" />
		</td>
        <td width="496"  align="left"  height="18">
          <font size="3" face="Arial"><a  href="#" onClick="setUrl(this)"><img src="images/lettre_l.gif" align="absmiddle" border="0" width="20" height="20">ancer
          le rapport</a></font>
        </td>
      </tr>
   
    </table>
  </div>
   <div id="chargement" style="width: 1143; height: 29" > 
  </div>
  </form>
  
 

</body>
</html>














