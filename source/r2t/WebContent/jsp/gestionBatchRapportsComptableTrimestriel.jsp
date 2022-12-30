<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="baffBatch" scope="page" class="fr.analogon.r2t.view.batch.BaffBatchRapportComptableTrimestrielle" />
  <jsp:setProperty name="baffBatch" property="request" value="<%=request%>"/>
<title>Raport Comptable</title>

</head>


<Form method="POST" action="imprimerFactures" >
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">



  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="1117">
    <tr>


  
  

      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>


    </tr>
    <tr>


  
   <A NAME="informationBatch">   

      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Information</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        SUR LE BATCH :</font></b></span></td>

  
      </a>

  
    </tr>


    <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp;Date de lancement du batch: &nbsp;</font></b><b><font face="arial" color="#000000" size="3">
       
        </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="dateDeCreation" /></font>
       </font></b>
    
    
    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Numéro du batch :
          </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="numeroBatch" /></font>
</b>
    
    </tr>
  </table>


<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="114" width="1117">
<tr>
    <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienBalanceTrimestrielToulon"/>
    </td>   
    <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienVentilationParBaremeTrimestrielToulon"/>
    </td>   
</tr>

<tr>
    <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienVentilationParEmplacementTrimestrielToulon"/>
    </td>   
    <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienListeDesAcomptesTrimestrielToulon"/>
    </td>
</tr>

<tr>       
    <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienListeDesAnnulationsTrimestrielToulon"/>
    </td>
	 <td align="center">    	
		<jsp:getProperty name="baffBatch" property="lienListeDesImpayesTrimestrielToulon"/>
    </td>
</tr>










</table>


<p align="center">
          &nbsp;</p>

</body>



</Form>
</html>