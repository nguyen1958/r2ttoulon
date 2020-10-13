<html>

<head>
	<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 	<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 	<script type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
	<script type="text/javascript" src="./resources/js/controle.js"></script>
  	<jsp:useBean id="baffResultatRechercheAlerte" scope="session" class="fr.analogon.r2t.main.BaffResultatRechercheAlerte" />
  	<jsp:setProperty name="baffResultatRechercheAlerte" property="request" value="<%=request%>"/>
  	<script>
    function ok()
    {
      document.forms[0].submit();
    }
    </script>
</head>

<body background="./images/nuages.jpg" topmargin="0" link="#000000" alink="#000000" vlink="#000000">
  <table border="0" cellpadding="0" cellspacing="0" width="98%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Liste des Alertes</font></td>
  </tr>
  </table>
  
<form method="POST" action="">
 <div align="center">
    <div align="center">
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="50">
        <tr>
          	<td colspan="3" bgcolor="#AFF3BB" height="42">
            	<p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          
            </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="23">
            <b><font size="4">RECHERCHE :</font></b>          
          </td>
        </tr> 
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            	Recherche
          		multi-critères  :<b> </b>veuillez remplir
          		un ou plusieurs champs ci-dessous pour obtenir les informations
            	détaillées.</font>
         </td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;N° secteur de l'emplacement : </font></b></font>
          </td>
          <td width="307">
            <jsp:getProperty name="baffResultatRechercheAlerte" property="numSecteur"/> 
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de taxe : </font></b></font>
          </td>
          <td width="307">
          	<jsp:getProperty name="baffResultatRechercheAlerte" property="typeTaxe"/>
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;N° dans la rue de l'emplacement : </font></b></font>
          </td>
          <td width="307">
            <input type="text" name="numVoieEmplacement" size="33" value="<jsp:getProperty name="baffResultatRechercheAlerte" property="numVoieEmplacement"/>">
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
		<tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Nom de la rue de l'emplacement : </font></b></font>
          </td>
          <td width="307">
            <input type="text" name="nomVoieEmplacement" size="33" value="<jsp:getProperty name="baffResultatRechercheAlerte" property="nomVoieEmplacement"/>" >
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date du controle : </font></b></font>
          </td>
          <td width="307">
            <input  type="text" name="dateControle" id="dateControle"
           	value="<jsp:getProperty name="baffResultatRechercheAlerte" property="dateControle"/>"
                maxlength=&quot;10&quot; size="25">                      
		  	<img onclick="displayCalendar(document.forms[0].dateControle,'dd/mm/yyyy',this)"
		  	src="./images/calendar.gif" border="0" width="20" height="18" />
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
        <tr>
          <td background="./images/fond_bleu.gif" width="200">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Contrôleur : </font></b></font>
          </td>
          <td width="307">
          	<jsp:getProperty name="baffResultatRechercheAlerte" property="controleur"/>
          </td>
          <td width="631">
            &nbsp;</td>
        </tr>
       <tr>
      		<td width="231" height="23">&nbsp;</td>
      		<td width="252" height="23">
        		<b><font face="Arial" size="2">&nbsp;&nbsp; </font></b>
        	</td>
          	<td width="655" height="23">
            	<a href="javascript:ok();">                
            		<img border="0" src="./images/ok.gif" width="20" height="20" align="left">         
             	</a> 
            </td>
        </tr>
        </table>
    </div>
  	</div>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td bgcolor="#AFF3BB" width="1122" height="30">
          	<b><font size="4">RESULTATS</font></b>  
          </td>
        </tr>
        <tr>
          <td bgcolor="#AFF3BB" width="1122">
            <b><font size="3">LISTE DES EMPLACEMENTS LIBRES :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <jsp:getProperty name="baffResultatRechercheAlerte" property="nombreEmplacementLibre"/></font></b>
          </td>
        </tr>    
        <tr>        
   		   <jsp:getProperty name="baffResultatRechercheAlerte" property="resultatEmplacementLibre"/>            
        </tr>   
      </table>
       <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td bgcolor="#AFF3BB" width="1122" height="10">
            <p align="center"><font size="3" face="Arial" color="#000000"></font>
          </td>
        </tr>
        <tr>
          <td bgcolor="#AFF3BB" width="1122">
            <b><font size="3">LISTE DES EMPLACEMENTS A MODIFIER :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <jsp:getProperty name="baffResultatRechercheAlerte" property="nombreEmplacementAModifier"/></font></b>
          </td>
        </tr>    
        <tr>        
   		   <jsp:getProperty name="baffResultatRechercheAlerte" property="resultatEmplacementAModifier"/>            
        </tr>   
      </table>
</form>

</body>

</html>



