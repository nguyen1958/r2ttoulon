<html>

<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
  <jsp:useBean id="baffAutorisation" scope="session" class="fr.analogon.r2t.view.autorisation.BAffAutorisation" />
  <jsp:setProperty name="baffAutorisation" property="request" value="<%=request%>"/>
    
  
</head>

<body background="./images/nuages.jpg" topmargin="0" link="#000000" alink="#000000" vlink="#000000">
  <table border="0" cellpadding="0" cellspacing="0" width="98%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Liste des Autorisations</font></td>
  </tr>
  </table>
  
<form method="POST" action="">

      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr>
          <td bgcolor="#AFF3BB" width="1122">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td bgcolor="#AFF3BB" width="1122">
            <b><font size="4">LISTE DES AUTORISATIONS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <jsp:getProperty name="baffAutorisation" property="nombreAutorisation"/></font></b>
          </td>
        </tr>    
        <tr>        
   		   <jsp:getProperty name="baffAutorisation" property="resultatRecherche"/>            
        </tr>
        
   
        </table>
</form>

</body>

</html>



