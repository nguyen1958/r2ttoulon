<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffDivers"/>

  <script>
  

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
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=liste_reclamation.jsp">


  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="1146" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="1142">
            <b><font size="4">RECHERCHE D'UNE RECLAMATION :</font></b>
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
          <td background="./images/fond_bleu.gif" width="257">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de Taxe : </font></b></font>
          </td>
          <td width="193">
          <jsp:getProperty name="bean"  property="typeTaxe" />
          </td>
          <td width="688">&nbsp;
            </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date de Création de la
            Réclamation : </font></b></font>
          </td>
          <td width="193">
            <input maxlength="10" type="text" name="dateCreationReclamation" id="dateCreationReclamation" size="20">
		     
			  <img onclick="displayCalendar(document.forms[0].dateCreationReclamation,'dd/mm/yyyy',this)"
				src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
           
          </td>
          <td width="688">&nbsp;
            </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;N° du </b></font><font color="#000000"><b><font face="Arial" size="2">Redevable
            : </font></b></font>
          </td>
          <td width="193">
            <input type="text" name="numeroRedevable" size="20">
          </td>
          <td width="688">&nbsp;
            </td>
        </tr>
        
        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Nom Redevable : </font></b>
          </td>
          <td width="193">
            <input type="text" name="nomRedevable" size="20">
          </td>
          <td width="688">&nbsp;
            </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;N° Facture</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="193">
            <input type="text" name="numeroFacture" size="20">
          </td>
          <td width="688">&nbsp;
            
          </td>
        </tr>
        
        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;N° Titre</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="193">
            <input type="text" name="numeroTitre" size="20">
          </td>
          <td width="688">&nbsp;
            
          </td>
        </tr>
        
        
       <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Année</b></font><font color="#000000"><b><font face="Arial" size="2">
            </font></b></font>
            <font face="Arial" size="2" color="#000000"><b> Titre</b></font><font color="#000000"><b><font face="Arial" size="2"> 
            : </font></b></font>
          </td>
          <td width="193">
            <input type="text" name="annee" size="20">
          </td>
          <td width="688">&nbsp;
            
          </td>
        </tr>
        
        
        
        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <font face="Arial" size="2" color="#000000"><b>&nbsp;Contrôle Effectué</b></font><font color="#000000"><b><font face="Arial" size="2"> : </font></b></font>
          </td>
          <td width="193">
            
          <select size="1" name="controleEffectue">
            <option>NON</option>
            <option>OUI</option>
            <option>TOUS</option>
          </select>
            
          </td>
          <td width="688">&nbsp;
            
          </td>
        </tr>

        <tr>
          <td background="./images/fond_bleu.gif" width="257">
            <p style="margin-left: 5"><font face="Arial" size="2" color="#000000"><b>Etat
            de la Réclamation</b></font><font color="#000000"><b><font face="Arial" size="2">
            : </font></b></font>
          </td>
          <td width="193">
            <select size="1" name="etatReclamation">
            <option>EN COURS</option>
            <option>CLOTURES</option>
            <option>TOUS</option>
          </select>
            
          </td>
          <td width="688">
            <p style="margin-left: 5"><input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I2">
          </td>
        </tr>
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
