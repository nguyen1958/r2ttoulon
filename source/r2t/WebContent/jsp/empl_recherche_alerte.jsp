<html>
<head>

<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<%@ page language = "java" %>
<jsp:useBean id="beanAlerte" scope="page" class="fr.analogon.r2t.view.alertes.BAffListeAlertes"/>
</head>
<body background="./images/nuages.jpg" topmargin="0">

<script>
    function ok()
    {

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
<form method="POST" action="entree?action=liste_alertes.jsp">


  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">RECHERCHE D'UNE ALERTE :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="1142">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
          détaillées.</font></td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de
            taxe : </font></b></font>
          </td>
          <td width="307">
            <select size="1" name="typeAlerte">
              <option>TOUS</option>
              <option>TAV</option>
              <option>DDV</option>
              <option>INFRACTION</option>
              <option>PUBLICITE</option>
            </select>
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2"><span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">&nbsp;Sur/Sans dossier :</span></font></b></font>
          </td>
          <td width="307">
            <select size="1" name="liedossier">
              <option>TOUS</option>
              <option>SansDossier</option>
              <option>SurDossier</option>
            </select>
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;<span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">Date de création de l'alerte :</span></font></b></font>
          </td>
          <td width="307">
            <input type="text"  readOnly name="dateCreationAlerte" id="dateCreationAlerte" size="15"><b><font size="2" face="Arial">        
			
			<img onclick="displayCalendar(document.forms[0].dateCreationAlerte,'dd/mm/yyyy',this)"
			src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />




	   
        		   
          </font></b>
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;<span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">Nom du contrôleur :</span></b></font>
          </td>
          <td width="307">
            <input type="text" name="nomControleur" size="32">
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
        
             <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;<span style="font-size:10.0pt;font-family:Arial;
mso-fareast-font-family:&quot;Times New Roman&quot;;color:black;mso-ansi-language:FR;
mso-fareast-language:FR;mso-bidi-language:AR-SA">Nom du redevable :</span></b></font>
          </td>
          <td width="307">
            <input type="text" name="nomRedevable" size="32">
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>

   

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Numéro de la rue </b></font><font color="#000000"><b><font face="Arial" size="2">: </font></b></font>
          </td>
          <td width="307">
            <input type="text" name="numeroRue" size="10">
          </td>
          <td width="631">
            &nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Nom
            de la rue </b></font><font color="#000000"><b><font face="Arial" size="2">:</font></b></font>
          </td>
          <td width="307">
            <input type="text" name="nomRue" size="32">
          </td>
          <td width="631">
            &nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;</b></font><font face="Arial" size="2" color="#000000"><b>Quartier </b></font><font color="#000000"><b><font face="Arial" size="2">:</font></b></font>
          </td>
          <td width="307">
            <jsp:getProperty name="beanAlerte"  property="listeQuartier" />
          </td>
          <td width="631">
            &nbsp;
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <p style="margin-left: 5"><font face="Arial" size="2" color="#000000"><b>Etat
            de l'alerte</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="307">
            <p style="margin-left: 5">          
            <select size="1" name="etatAlerte">
            <option>EN COURS</option>
            <option>CLOTURES</option>
            <option>TOUS</option>
          </select>
            
          </td>
          <td width="631">
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
