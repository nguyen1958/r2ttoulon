<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>


<META http-equiv="Expires" content="0">
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.batch.facture.BAffListeRefacturation" />
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
<title>Liste des refacturation:</title>


</head>
<body background="./images/nuages.jpg" topmargin="0">
<form method="POST" action="liste_refacturation.jsp">

 
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="1">
          <img border="0" src="images/pdf.jpg" width="40" height="40">
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="1">
          <p align="left"><font size="4"><b>LISTE DES REFACTURATIONS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">
          <p align="center">
          <jsp:getProperty name="bean" property="listeRefacturation"/>
        </td>
  </tr>

     
  <tr>
        <td width="1139" align="center" height="1">
          &nbsp;<p>
          <a href="javascript:window.history.go(-1);"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a>
          </p>
        </td>
  </tr>
     
  </table>
</body>





