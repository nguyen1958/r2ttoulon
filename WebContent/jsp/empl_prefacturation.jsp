<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="batch" scope="page" class="fr.analogon.r2t.view.batch.GestionBatch" />
  <jsp:setProperty name="batch" property="request" value="<%=request%>"/>
<title>Batch</title>
<script>
function retour()
{ 
	history.go(-1);
}

function gestionBatch()
{	
	document.forms[0].submit();    
}
</script>
</head>



<form method="POST"  action="entree?action=gestionBatch.jsp">
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">
<input type="hidden" name="numeroBatch" value="<jsp:getProperty name="batch" property="numeroBatch"/>">
<input type="hidden" name="nombreFactures" value="<jsp:getProperty name="batch" property="nombreFactures"/>">
<input type="hidden" name="anExercice" value="<jsp:getProperty name="batch" property="anExercice"/>">
<input type="hidden" name="typeDeTaxe" value="<jsp:getProperty name="batch" property="typeDeTaxe"/>">
<input type="hidden" name="etapeFacturation" value="<jsp:getProperty name="batch" property="etapeFacturation"/>">
<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="1610" borderColorLight="#c0c0c0" border="1" align="center" height="95">
  <tr>
        <td width="575" bgcolor="#DFFFEF" align="center" height="1">
          <p align="left">
          <b><font face="Arial" size="4">Batch - Géstion des factures&nbsp;</font></b>
          </p>
        </td>
  </tr>
  <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp; Date de création : &nbsp;</font>
       <font color="#CC0000">
<jsp:getProperty name="batch" property="anExercice"/> 
       </font>
       <font face=arial color=#cc3300 size=3></font>
    </b>
    
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b>     
      <font face=arial color=#000000 size=3>&nbsp; Type de taxe : &nbsp;</font>
      <font color="#CC0000">
<jsp:getProperty name="batch" property="typeDeTaxe"/>
      </font>
     </b> 
    
    
    
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Numéro batch : </font><font color="#CC0000"><jsp:getProperty name="batch" property="numeroBatch"/>
</font></b>
    
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Nombre de factures créées :</font><font color="#CC0000"> 
        
<jsp:getProperty name="batch" property="nombreFactures"/>
        </font></b>



 
   
  

  
     
  </tr>
    

     <%  if ( batch.getEditerDesRemboursement().equalsIgnoreCase("true") ) { %>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Nombre de remboursements créées :</font><font color="#CC0000"> 
	<input type="hidden" name="nombreRemboursements" value= <jsp:getProperty name="batch" property="nombreRemboursements"/>>         
		<jsp:getProperty name="batch" property="nombreRemboursements"/>
        </font></b>
     <%}%>



  
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font color="#CC0000">
    
     </font></b>



 
   
  

  






     
</table>

<p>&nbsp;</p>

<table border="0" width="100%">
  <tr>
    <td width="200%" align="center">
      <p align="center">
      <a href="javascript:retour();" target="bas"><font face="Arial" size="3"><img height="20" src="images/lettre_r.gif" width="20" align="absMiddle" border="0">
            <b>elancer un batch</b></font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="Arial" size="3">
      </font>
      
     
     <% if(batch.getEditerDesFactureAnnulee().equalsIgnoreCase("true") 
	       || 
		   batch.getEditerDesRemboursement().equalsIgnoreCase("true") 
		   ||
		   (!batch.getNombreFactures().equalsIgnoreCase("0") ) ) { %> 
           
	     <a href="javascript:gestionBatch();" target="bas"><font face="Arial" size="3"><img height="20"    src="images/lettre_i.gif" width="20" align="absMiddle" border="0"> <b>nformations sur ce batch</b></font></a>
       <% } %> 
       

     
    </td>
  </tr>
  
</table>

</Form>


</html>







