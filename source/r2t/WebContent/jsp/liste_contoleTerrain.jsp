<html>
<head>
<jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.administration.BAffListeContoleTerrain" />
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<title>Liste des controle </title>
</head>
<script>
function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}
</script>

<body background="./images/nuages.jpg" topmargin="0">
<form method="POST">

      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="61">
 
  <tr>
          <td width="100%" bgcolor="#AFF3BB" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b>
            
            <a href="javascript:actualiser();">
             <img border="0" src="images/batch.png" width="40" height="40">
			</a>
            </b></font></p>
          </td>
  </tr>
  <tr>
          <td width="100%" bgcolor="#AFF3BB" height="32">
            <p>
              <b><font size="4">LISTE DES CONTROLES TERRAIN :
               
             </font></b>          </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">        
         <jsp:getProperty name="bean" property="listeControleTerrain"/>
          </p>
        </td>
  </tr>
     

     
  </table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>



</body>

