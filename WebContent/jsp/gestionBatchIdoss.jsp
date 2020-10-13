<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="baffBatchIdoss" scope="page" class="fr.analogon.r2t.idoss.BAfIdoss" />
  <jsp:setProperty name="baffBatchIdoss" property="request" value="<%=request%>"/>
<title>IDOSS</title>
</head>

<script>
function majLigneIdoss(idLigneIdoss)
{
  document.forms[0].choix.value="modifierUneLigneIdoss";
  document.forms[0].action="./entree?action=gestionBatchIdoss.jsp&idLigneIdoss="+idLigneIdoss;
  document.forms[0].submit();
}

function nePasValiderTousLesLigneIdoss()
{
  //alert("ne pas valider l'approche ");
  document.forms[0].choix.value="validerTousLesLigneIdoss";
  document.forms[0].action="./entree?action=gestionBatchIdoss.jsp";
  document.forms[0].submit();
}

function validerTousLesLigneIdoss()
{
  //alert("valider les apprichement IDoss ");
  document.forms[0].choix.value="nePasValiderTousLesLigneIdoss";
  document.forms[0].action="./entree?action=gestionBatchIdoss.jsp";
  document.forms[0].submit();
}


</script>



<Form method="POST" >
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">
 <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="1117">
   <tr>
      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>
    </tr>
    <tr>
     <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Information</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        BATCH IDOSS :</font></b></span></td>
    </tr>
    <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp;Date de lancement du batch : &nbsp;</font></b><b><font face="arial" color="#000000" size="3">
       
        </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatchIdoss" property="dateDeCreation" /></font>
       </font></b>
    
    
    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Numéro batch :
			<input name="numeroBatchIdoss" type="hidden" value='<jsp:getProperty name="baffBatchIdoss" property="numeroBatchIdoss" />'>
			<input name="dateDeCreation" type="hidden" value='<jsp:getProperty name="baffBatchIdoss" property="dateDeCreation" />'>
			<input name="choix" type="hidden">


			
          </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatchIdoss" property="numeroBatchIdoss" /></font>
</b>
    
    </tr>
  </table>
<BR>
<BR>



<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="56" width="1117">
 
  <tr>
    <td bgcolor="#AFF3BB" height="46"><p align="center"><a name="rapport"><img border="0" src="images/rapports.jpg" width="40" height="40">&nbsp;</a></td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">
    LISTE des rapprochements </span></font></b><span style="text-transform: uppercase"><b><font size="4">VALIDES
      :</font></b></span></td>
  </tr>
 <tr>
    <td width="1111" height="1" align="center">
      <table border="1" width="100%" height="15">
        <tr>
    <td width="176%" height="1" align="center">
    <jsp:getProperty name="baffBatchIdoss" property="tableauLigneIdossValide" />
    </td>
        </tr>
 
      </table>
 </td>
  </tr>

</table>


<BR>



<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="56" width="1117">
 
  <tr>
    <td bgcolor="#AFF3BB" height="46"><p align="center"><a name="rapport"><img border="0" src="images/rapports.jpg" width="40" height="40">&nbsp;</a></td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">
    LISTE des rapprochements NON VALIDES</span></font></b><span style="text-transform: uppercase"><b><font size="4">
      :</font></b></span></td>
  </tr>
 <tr>
    <td width="1111" height="1" align="center">
      <table border="1" width="100%" height="15">
        <tr>
    <td width="176%" height="1" align="center">
    <jsp:getProperty name="baffBatchIdoss" property="tableauLigneIdossNonValide" />
    </td>
        </tr>
      </table>
 </td>
  </tr>

</table>


 <p>
<BR>




 </p>




  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="6" width="1117">
    <tr>    

      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center">&nbsp;<A NAME="Impreesion"><img border="0" src="images/pleaide.jpg" width="40" height="40">


      </a>

  
      </td>


    </tr>
    <tr>


 

      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Rapprochement
        impossible</span></font></b> <span style="text-transform: uppercase"><b><font size="4">: </font></b></span></td>

  


  
    </tr>


    <tr>
    <td width="1111" height="1" align="center">
      <table border="1" width="100%" height="15">
        <tr>
    <td width="176%" height="1" align="left">
    <jsp:getProperty name="baffBatchIdoss" property="tableauLigneNonReconnu" />
    </td>
        </tr>
      </table>
 </td>
    </tr>


  </table>
<p>&nbsp;</p>
<p align="center"><a href="javascript:window.history.go(-1);"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a>
          &nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
</body>



</Form>
</html>






    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 
























