<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<jsp:useBean id="bean" scope="session" class="fr.analogon.r2t.administration.BAffGroupeTaxe" />
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
</head>

<body background="./images/nuages.jpg" topmargin="0">


 
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="110">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="42">
          <font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="22">
          <p align="left"><font size="4"><b>LISTE DES GROUPES DE TAXES :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          
          <jsp:getProperty name="bean" property="nombreGroupeTaxe"/>

          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="19">
          <p align="center">
          <jsp:getProperty name="bean" property="listeGroupeTaxe"/>
        </td>
  </tr>

    
  </table>


</body>

</html>
