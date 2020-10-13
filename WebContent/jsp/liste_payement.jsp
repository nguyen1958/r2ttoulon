<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="beanRecherchePayement" scope="session" class="fr.analogon.r2t.view.regie.BAffListePayement" />
<jsp:setProperty name="beanRecherchePayement" property="request" value="<%=request%>"/>
<title>Factures</title>

</head>
<body background="./images/nuages.jpg" topmargin="0">


<form method="POST" action="gestionFacture">
  <input type="hidden" name="idFacture" >
  <input type="hidden" name="actionFacture" >
    <input type="hidden" name="motifAnnulation" >
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="62">

 
  <tr>
        <td width="100%" bgcolor="#AFF3BB" align="center" height="42">
          <p align="center">
          <img border="0" src="images/pdfvertf.png" width="40" height="40">
          </p>
        </td>
  </tr>

  <tr>
        <td width="100%" bgcolor="#AFF3BB" align="center" height="22">
          <p align="left"><b><font size="4">LISTE DES PAIEMENTS :</font></b>
        </td>
  </tr>     
  <tr>
        <td width="100%" height="19">
        <p align="center">
        <jsp:getProperty name="beanRecherchePayement" property="listePayement"/>
        </td>
  </tr>
     
  </table>
  </form>
</body>





