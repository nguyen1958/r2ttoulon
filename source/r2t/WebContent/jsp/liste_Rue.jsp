<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<jsp:useBean id="beanRueRecherche" scope="session" class="fr.analogon.r2t.rue.BAffRue" />
<jsp:setProperty name="beanRueRecherche" property="request" value="<%=request%>"/>
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
          <p align="left"><font size="4"><b>LISTE DES RUES :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <jsp:getProperty name="beanRueRecherche" property="nombreDeRue"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="19">
          <p align="center">
          <jsp:getProperty name="beanRueRecherche" property="listeRechercheRue"/>
        </td>
  </tr>

     
  </table>

</body>

</html>
