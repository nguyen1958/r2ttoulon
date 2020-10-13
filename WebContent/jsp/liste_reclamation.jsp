<html>

<head>


<META http-equiv="Expires" content="0">
<script type="text/javascript" src="./resources/js/controle.js"></script> 
<jsp:useBean id="beanReclamation" scope="session" class="fr.analogon.r2t.reclamation.BAffListeReclamation" />
<jsp:setProperty name="beanReclamation" property="request" value="<%=request%>"/>
<title>Liste des reclamations:</title>
  <script>
  </script>

</head>
<body background="./images/nuages.jpg" topmargin="0">
<form method="POST" action="liste_reclamation.jsp">

 
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="1">
          <img border="0" src="images/reclamation.png" width="40" height="40">
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="1">
          <p align="left"><font size="4"><b>LISTE DES RECLAMATIONS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img border="0" src="images/rouge.jpg" width="15" height="15">CLOTUREES&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img border="0" src="images/vert.jpg" width="15" height="15">EN COURS</b></font>
        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">
          <p align="center">
         <jsp:getProperty name="beanReclamation" property="listeReclamations"/>
        </td>
  </tr>

     
  </table>
</body>





