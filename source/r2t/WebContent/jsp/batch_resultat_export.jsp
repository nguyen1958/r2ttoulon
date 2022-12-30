<html>

<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
  <jsp:useBean id="baffResultatExport" scope="session" class="fr.analogon.r2t.view.export.BaffResultatExport" />
  <jsp:setProperty name="baffResultatExport" property="request" value="<%=request%>"/>
<script>
  	function exporterOuvrage(){
  		//var queryString="?type=ouvrage&sql="+encodeURIComponent(document.forms[0].sqlExportOuvrage.value);
  		//document.location.href="${pageContext.request.contextPath}/exportExcel"+queryString;
  		document.getElementById("typeExport").value = "ouvrage";
  		document.forms["export"].submit();
  		return false;
  	}
  	
  	function exporterPaiement(){
  		//var queryString="?type=paiement&sql="+encodeURIComponent(document.forms[0].sqlExportPaiement.value);
  		//document.location.href="${pageContext.request.contextPath}/exportExcel"+queryString;
  		document.getElementById("typeExport").value = "paiement";
  		document.forms["export"].submit();
  		return false;
  	}
  </script>     
  
</head>

<body background="./images/nuages.jpg" topmargin="0" link="#000000" alink="#000000" vlink="#000000">
<form name="export" method="POST" action="exportExcel">
	  <table border="0" cellpadding="0" cellspacing="0" width="100%">
	    <tr>
	      <td background="./images/fond_trait.gif" width="15%">
	        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
	      </td>
	    <td background="./images/fond_trait.gif" valign="top">
	      <p align="right"><font face="Arial" size="5">Export - Résultat&nbsp;&nbsp;</font></td>
	  </tr>
	  </table>
	  <input type="hidden" name="typeExport" id="typeExport" type="text">
	  <input type="hidden" name="sqlExportOuvrage" value="<jsp:getProperty name="baffResultatExport" property="sqlExportOuvrage"/>"  >
	  <input type="hidden" name="sqlExportPaiement" value="<jsp:getProperty name="baffResultatExport" property="sqlExportPaiement"/>"  >
</form>
<form method="POST" action="">	  
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
        <tr bgcolor="#AFF3BB">
          <td colspan="2">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/users.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr bgcolor="#AFF3BB">
          <td width="793">
            <b><font size="4">LISTE DES RESULTATS :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <jsp:getProperty name="baffResultatExport" property="nombreRedevable"/></font></b>
          </td>
          <td>
          	&nbsp;
          	<jsp:getProperty name="baffResultatExport" property="linkExportOuvrage"/>&nbsp;&nbsp;&nbsp;&nbsp;
          	<jsp:getProperty name="baffResultatExport" property="linkExportPaiement"/>
          </td>
        </tr>    
        <tr>        
   		   <jsp:getProperty name="baffResultatExport" property="resultatRecherche"/>            
        </tr>
    </table>
</form>

</body>

</html>



