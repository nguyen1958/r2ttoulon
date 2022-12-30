<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="batch" scope="page" class="fr.analogon.r2t.view.batch.GestionBatchRelance" />
  <jsp:setProperty name="batch" property="request" value="<%=request%>"/>
<title>Batch</title>
</head>



<form method="POST"  action="">
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">
<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="1610" borderColorLight="#c0c0c0" border="1" align="center" height="95">
  <tr>
        <td width="575" bgcolor="#DFFFEF" align="center" height="1">
          <p align="left">
          <b><font face="Arial" size="4">Batch - Relance&nbsp;</font></b>
          </p>
        </td>
  </tr>
  <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp; Date lancement : &nbsp;</font>
       <font color="#CC0000">
<jsp:getProperty name="batch" property="dateDeLancementBatchRelance"/> 
       </font>
       <font face=arial color=#cc3300 size=3></font>
    </b>
    
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b>     
      <font face=arial color=#000000 size=3>&nbsp; Période : &nbsp;</font>
      <font color="#CC0000">
<jsp:getProperty name="batch" property="periodeRelance"/>
      </font>
     </b> 
    
    
    
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Numéro batch : </font><font color="#CC0000"><jsp:getProperty name="batch" property="numeroBatchRelance"/>
</font></b>
    
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Nombre de factures impyées :</font><font color="#CC0000"> 
<input type="hidden" name="nombreFacturesImpaye" value= <jsp:getProperty name="batch" property="nombreFacturesImpaye"/>>         
<jsp:getProperty name="batch" property="nombreFacturesImpaye"/>
        </font></b>



 
   
 <%  if ( ! batch.getNombreFacturesImpaye().equalsIgnoreCase("0") )   { %>    
  
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     



 
   
  

  






     
     &nbsp; <b><font face="arial" color="#000000" size="3">Fichier Excel :</font><font color="#CC0000">
     &nbsp; <a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierExcel'/>">Fichier excel</a></font></b>

 
   
  

  






     
  </tr>



 
   
  

  
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     



 
   
  

  






     
     &nbsp; <b><font face="arial" color="#000000" size="3">Factures imapayées :</font><font color="#CC0000">
     &nbsp; <a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierPdf'/>">Fichier
     PDF</a></font></b>

<%}%> 
   
  

  






     
</table>

<p>&nbsp;</p>

</Form>


</html>









