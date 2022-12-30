<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.administration.BAffSynchro" />
<%bean.setRequest(request);%>

<script>
function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}
</script>

</head>
<body background="./images/nuages.jpg" topmargin="0">
<form method="POST" action="sjourneaux">
 
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
  <input type="hidden" name="attribut" value="test" >
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
          
            <p align="center"><font size="3" face="Arial" color="#000000"><b>
            <a href="javascript:actualiser();">
            <img border="0" src="images/Synchro.png" width="40" height="40">
            </a>
            </b></font></p>
          
          </td>
  </tr>
  <tr>
          <td width="100%" bgcolor="#AFF3BB">
            <p>
              <font size="3" face="Arial"><b>&nbsp</b></font><b><font size="4">HISTORIQUE DES SYNCHRONISATIONS  :</font></b><font size="3" face="Arial"><b>&nbsp
              </b></font>
          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1"> 
           <jsp:getProperty name="bean" property="historiqueSynchronisation"/>          
        </td>
  </tr>
     
  </table>
</body>
