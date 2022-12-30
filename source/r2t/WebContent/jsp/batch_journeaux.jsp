<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="beanHitoriqueBatch" scope="session" class="fr.analogon.r2t.view.batch.BAffJourneaux" />
<%beanHitoriqueBatch.setRequest(request);%>
<title>Batch :</title>


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
              <b><font size="4">HISTORIQUE DES BATCHS :</font></b>
          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">        
         <jsp:getProperty name="beanHitoriqueBatch" property="historiquebatch"/>
          </p>
        </td>
  </tr>
     
  </table>
</form>






</body>
