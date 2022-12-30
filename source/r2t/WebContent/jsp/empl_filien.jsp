<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="batch" scope="page" class="fr.analogon.r2t.view.batch.GestionBatchFilien" />
  <jsp:setProperty name="batch" property="request" value="<%=request%>"/>
<title>Batch</title>
<script>
function retour()
{ 
	history.go(-1);
}

function validerBatchFilien()
{	
    var m="Voulez vous valider ce batch, il n'est plus possible de saisire de payement pour ces factures  ? ";
    var confirmation=confirm(m);
    if (confirmation)
    {
 	   document.forms[0].actionBatch.value="validerBatch";
     	document.forms[0].submit();    
    }
}
</script>
</head>

<% String etatBatchFilien = batch.getEtatBatchFilien(); %>

<form method="POST"  action="entree?action=empl_filien.jsp">

<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">
<input type="hidden" name="actionBatch" value="">
<input type="hidden" name="choixFilien" value="<jsp:getProperty name="batch" property="choixFilien"/>">

         
<input type="hidden" name="numeroBatchFilien" value='<jsp:getProperty name="batch" property="numeroBatchFilien"/>' >         

<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="1610" borderColorLight="#c0c0c0" border="1" align="center" height="95">
  <tr>
        <td width="575" bgcolor="#DFFFEF" align="center" height="1">
          <p align="left">
          <b><font face="Arial" size="4">Batch - Filien&nbsp;</font></b>
          </p>
        </td>
  </tr>
  <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp; Date lancement : &nbsp;</font>
       <font color="#CC0000">
<jsp:getProperty name="batch" property="dateDeLancementBatcheFilien"/> 
       </font>
       <font face=arial color=#cc3300 size=3></font>
    </b>
    
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b>     
      <font face=arial color=#000000 size=3>&nbsp; Période : &nbsp;</font>
      <font color="#CC0000">
<jsp:getProperty name="batch" property="numeroTrimestreFilien"/>
      </font>
     </b> 
    
    
    
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Numéro batch : </font><font color="#CC0000"><jsp:getProperty name="batch" property="numeroBatchFilien"/>
</font></b>
    
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp; Nombre de factures impayées :</font><font color="#CC0000"> 
<input type="hidden" name="nombreFacturesImpayeFilien" value= <jsp:getProperty name="batch" property="nombreFacturesImpayeFilien"/>>         
<jsp:getProperty name="batch" property="nombreFacturesImpayeFilien"/>
        </font></b>



 
   
<%  if ( ! batch.getNombreFacturesImpayeFilien().equalsIgnoreCase("0") )   { %>   

  
     
  <tr>
    <td width="575" bgcolor="#ffffff" height="19">     

     &nbsp;     
     <b><font face="arial" color="#000000" size="3">Fichier Filien Personne privée:</font><font color="#CC0000">
     &nbsp;<a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierToRegie11'/>">Filien</a>
     ( <jsp:getProperty name='batch' property='nbreFacture11'/> factures)
     </font></b>
     &nbsp;&nbsp;
     
 <tr>
    <td width="575" bgcolor="#ffffff" height="19">     

     &nbsp;     
     <b><font face="arial" color="#000000" size="3">Fichier Filien Personne morale:</font><font color="#CC0000">
     &nbsp;<a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierToRegie12'/>">Filien</a>
     ( <jsp:getProperty name='batch' property='nbreFacture12'/> factures)
     </font></b>
     &nbsp;&nbsp;

 <tr>
    <td width="575" bgcolor="#ffffff" height="19">     

     &nbsp;     
     <b><font face="arial" color="#000000" size="3">Fichier Filien Etat:</font><font color="#CC0000">
     &nbsp;<a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierToRegie21'/>">Filien</a>
     ( <jsp:getProperty name='batch' property='nbreFacture21'/> factures)
     </font></b>
     &nbsp;&nbsp;
     
     
      <tr>
    <td width="575" bgcolor="#ffffff" height="19">     

     &nbsp;     
     <b><font face="arial" color="#000000" size="3">Fichier Filien Region:</font><font color="#CC0000">
     &nbsp;<a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierToRegie22'/>">Filien</a>
     ( <jsp:getProperty name='batch' property='nbreFacture22'/> factures)
     </font></b>
     &nbsp;&nbsp;
     
     
     
      <tr>
    <td width="575" bgcolor="#ffffff" height="19">     

     &nbsp;     
     <b><font face="arial" color="#000000" size="3">Fichier Filien Département:</font><font color="#CC0000">
     &nbsp;<a   target="_blank" href="<jsp:getProperty name='batch' property='nomFichierToRegie23'/>">Filien</a> 
     ( <jsp:getProperty name='batch' property='nbreFacture23'/> factures)
     </font></b>
     &nbsp;&nbsp;     
     
     <input name="nbreFacture11" type="hidden" value="<jsp:getProperty name='batch' property='nbreFacture11'/>" >
     <input name="nbreFacture12" type="hidden" value="<jsp:getProperty name='batch' property='nbreFacture12'/>" >
     <input name="nbreFacture21" type="hidden" value="<jsp:getProperty name='batch' property='nbreFacture21'/>" >
     <input name="nbreFacture22" type="hidden" value="<jsp:getProperty name='batch' property='nbreFacture22'/>" >
     <input name="nbreFacture23" type="hidden" value="<jsp:getProperty name='batch' property='nbreFacture23'/>" >
     
     
     
  <%  if ( ! etatBatchFilien.equalsIgnoreCase("valide") )   { %> 
  <tr>
        <td width="1143"  align="left"  height="1">
          <p align="left"><font size="3" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:validerBatchFilien();">
          <img src="images/lettre_v.gif" align="absmiddle" border="0" width="20" height="20">alider
          le batch filien</a></font>
        </td>
  </tr>
  <%}%>

 
  <%}%>
   
  

  






     
</table>

<p>&nbsp;</p>

</Form>


</html>









