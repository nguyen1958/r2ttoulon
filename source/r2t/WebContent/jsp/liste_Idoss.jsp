<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.idoss.BAfIdoss" />
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
</head>

<body background="./images/nuages.jpg" topmargin="0">


 
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="117">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="42">
          <font size="3" face="Arial" color="#000000"><b><img border="0" src="images/users.png" width="40" height="40"></b></font>
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="22">
          <p align="left"><font size="4"><b>LISTE DES BATCHS IDOSS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <jsp:getProperty name="bean" property="nombreDeBatch"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="19">
          <p align="center">
          <jsp:getProperty name="bean" property="listeRechercheBatch"/>
        </td>
  </tr>

     
  </table>

<p align="center">
          <a href="javascript:window.history.go(-1);"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a>
  </p>

</body>

</html>
