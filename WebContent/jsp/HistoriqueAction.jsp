<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.administration.HistoriqueTaches" />
<%bean.setRequest(request);%>

</head>
<body background="./images/nuages.jpg" topmargin="0">
<script>
 function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}
</script>

<form method="POST" action="sjourneaux">
 
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
  <input type="hidden" name="attribut" value="test" >
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b>
            
            
            <a href="javascript:actualiser();">
           <img border="0" src="images/batch.png" width="40" height="40">			</a>
            
            </b></font></p>
          </td>
  </tr>
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p>
              <font size="3" face="Arial"><b>&nbsp</b></font><b><font size="4">HISTORIQUE DES
              TACHES :</font></b><font size="3" face="Arial"><b>&nbsp
              </b></font>
          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">       
           <jsp:getProperty name="bean" property="historiqueTache"/>       
        </td>
  </tr>
    
  <tr>
       <td width="1139" align="center" height="1"><a href="javascript:window.history.go(-1);"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a></td>
  </tr>
  
     
  </table>
</body>
