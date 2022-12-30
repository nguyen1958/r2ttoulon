<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="beanRechercheFacture" scope="session" class="fr.analogon.r2t.batch.facture.BAffListeFactures" />
<jsp:setProperty name="beanRechercheFacture" property="request" value="<%=request%>"/>
<title>Factures</title>

</head>
<body background="./images/nuages.jpg" topmargin="0">
  <script>
  
  
    function annulerFacture(v,motifannulation)
    {
        document.forms[0].idFacture.value= v;
         document.forms[0].actionFacture.value="ANNULEE";
		 document.forms[0].motifAnnulation.value=motifannulation;
		 if (confirm("Vous désirez annuler cette facture ?"))
        { 		 
		  document.forms[0].submit();       
        } 
    }
 
  </script>

<form method="POST" action="gestionFacture">
  <input type="hidden" name="idFacture" >
  <input type="hidden" name="actionFacture" >
    <input type="hidden" name="motifAnnulation" >
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="62">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="42">
          <img border="0" src="images/pdfvertf.png" width="40" height="40">
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="22">
          <p align="left"><b><font size="4">LISTE DES FACTURES :</font></b><font size="4"><b><A NAME="gestionReclamation">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img border="0" src="images/vert.jpg" width="15" height="15">Validées
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img border="0" src="images/rouge.jpg" width="15" height="15">Annulées&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></b></font>
        </td>     
  <tr>
        <td width="1139" align="center" height="19">
        <jsp:getProperty name="beanRechercheFacture" property="listeFactures"/>
        </td>
  </tr>

     
  <tr>
        <td width="1139" align="center" height="1">
          <p align="center">            
            <a href="javascript:window.history.go(-1);"><img border="0" src="./images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a>
        </td>
  </tr>

     
  </table>
</body>





