
<html>
<head>


 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

 
<%@ page language = "java" %>
  <jsp:useBean id="baffResultatRechercheRedevable" scope="request" class="fr.analogon.r2t.main.BaffParametresDeRechercheRedevable" />
  <jsp:setProperty name="baffResultatRechercheRedevable" property="request" value="<%=request%>"/>


  <script>
    function ok()
    {
      if ( isNaN(document.forms[0].numRedevable.value))
      	alert("Le numéro du redevable doit etre un nombre ! ");
      else   
      	document.forms[0].submit();
    }

    function effacer(){
      if (document.forms[0].exercice != null){
        document.forms[0].exercice.value=document.forms[0].ExerciceEnCours.value;
      }
      if (document.forms[0].periode != null){
        document.forms[0].periode.value=document.forms[0].PeriodeEnCours.value;
      }
   
      if (document.forms[0].nomRedevable != null){
        document.forms[0].nomRedevable.value="";
      }
      if (document.forms[0].afficherVignette != null){
        document.forms[0].afficherVignette.checked=false;
      }
      if (document.forms[0].afficherNonPaye != null){
        document.forms[0].afficherNonPaye.checked=false;
      }
      if (document.forms[0].numQuittance != null){
        document.forms[0].numQuittance.value="";
      }
      if (document.forms[0].montant != null){
        document.forms[0].montant.value="";
      }
      if (document.forms[0].codeVoie != null){
        document.forms[0].codeVoie.value="";
      }
      if (document.forms[0].codeType != null){
        document.forms[0].codeType.value="";
      }
      if (document.forms[0].codeSecteur != null){
        document.forms[0].codeSecteur.value="";
      }
      if (document.forms[0].codeEmplacement != null){
        document.forms[0].codeEmplacement.value="";
      }
      if (document.forms[0].raisonSociale != null){
        document.forms[0].raisonSociale.value="";
      }
      if (document.forms[0].profession != null){
        document.forms[0].profession.value="";
      }
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
      <p align="right"><font face="Arial" size="5">Recherche d'un redevable</font></td>
  </tr>
  </table>
<form method="POST" action="entree?action=empl_resultat_recherche_redevable.jsp" onSubmit="ok()" >

  <input type="hidden" name="origine" value="rechercheRedevable"/>
  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="387">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142" height="22">
            <b><font size="4">RECHERCHE D'UN REDEVABLE :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche </font><font size="2" face="Arial">par numéro du
            redevable</font></td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;</font></b></font><font color="#000000"><b><font face="Arial" size="2">N°
            Redevable&nbsp; :</font></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="numRedevable" size="33" >
          </td>
          <td width="631" height="23">
             <A href="javascript:ok();">                
              <img border="0" src="./images/ok.gif" width="20" height="20" align="left">         
             </a>
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

        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="23">
            <font color="#000000"><b><font face="Arial" size="2"><span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">&nbsp;</span></font></b></font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Nom/Prénom
            du redevable :</span></b></font>
          </td>
          <td width="239" height="23">
            <input type="text" name="nomRedevable" size="33" >
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
            <option value="OUI" selected>OUI</option>
            <option value="NON">NON</option>
            <option value="TOUS">TOUS</option>
            </select>           
           
          </td>
          <td width="631" height="23">
            &nbsp;</td>
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
            <option selected value="tous">Tous</option>
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
            <jsp:getProperty name="baffResultatRechercheRedevable" property="numSecteur"/> 
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
            <jsp:getProperty name="baffResultatRechercheRedevable" property="nomQuartier"/>    
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
            <jsp:getProperty name="baffResultatRechercheRedevable" property="listeTaxe"/>            
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
          <td width="239" height="24">
           <jsp:getProperty name="baffResultatRechercheRedevable" property="listeBareme"/>            
          </td>
          <td width="631" height="24">
            &nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="283" height="24">
            <font face="Arial" size="2"><b>&nbsp;Profession :&nbsp;</b></font>
          </td>
          <td width="239" height="24">
           <jsp:getProperty name="baffResultatRechercheRedevable" property="listeProfessions"/>            
          </td>
          <td width="631" height="24">
            <p align="center">
             <A href="javascript:ok();">                
              <img border="0" src="./images/ok.gif" width="20" height="20" align="left">         
             </a>
            </p>
          </td>
        </tr>
        </table>
      </center>
    </div>
  </div>
  
 
</form>

</body>

</html>
