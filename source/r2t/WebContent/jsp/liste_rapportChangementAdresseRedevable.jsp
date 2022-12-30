<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.view.batch.BAffListeRapportChangAdresRedevable" />
<%bean.setRequest(request);%>

<title>LISTE DES BATCH CHANGEMENT ADRESSE REDEVABLE :</title>


</head>
<body background="./images/nuages.jpg" topmargin="0">
<form method="POST" action="sjourneaux">
 
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
  <input type="hidden" name="attribut" value="test" >
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
  </tr>
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p>
              <b><font size="4">LISTE DES BATCHS DE CHANGEMENT D'ADRESSE DES REDEVABLES :</font></b>          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">        
         <jsp:getProperty name="bean" property="listeRapportChangementAdresse"/>
          </p>
        </td>
  </tr>
     
  </table>
</form>
<p>&nbsp;</p>
<p align="center"><a href="javascript:window.history.go(-1);"><font color="#0000FF"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">eprendre</font></b></font></a></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>






</body>
