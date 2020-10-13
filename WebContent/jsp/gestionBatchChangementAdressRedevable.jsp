<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="baffBatch" scope="page" class="fr.analogon.r2t.view.batch.BaffBatchChangementAdress" />
  <jsp:setProperty name="baffBatch" property="request" value="<%=request%>"/>
<title>Changement Adresse </title>

</head>
<script>
function ouvrirFichierDesFactures()
{
 alert("test");
}
</script>

<Form >
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">



  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="1117">
 


  
  

      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>


      </a>    </tr>
    <tr>


  
   <A NAME="informationBatch">   

      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">I</span></font></b><b><font size="4"><span style="text-transform: uppercase">NFORMATION BATCH CHANGEMENT D’ADRESSE DES REDEVABLES :</span></font></b></td>
      </a>    </tr>


    <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp;Date de création du batch : &nbsp;</font></b><b><font face="arial" color="#000000" size="3">
       
        </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="dateDeCreation" /></font>
    </font></b>    </tr>
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Numéro batch :

    </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="numeroBatch" /></font></b>    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Nombre de fichiers créés :</font><font face="arial" color="#CC3300" size="3"> 
    <jsp:getProperty name="baffBatch" property="nombreFichier" /></font>     </b>    </tr>
  </table>
<p>&nbsp;</p>


<% if(!baffBatch.getNombreFichier().equalsIgnoreCase("0")){%> 
<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="114" width="1117">
  <tr>
    <td bgcolor="#AFF3BB" height="46"><p align="center"><a name="rapport"><img border="0" src="images/rapports.jpg" width="40" height="40">&nbsp;</a></td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase"> ROLE DE CHANGEMENT D'ADRESSE DES REDEVABLES </span></font></b><span style="text-transform: uppercase"><b><font size="4">:</font></b></span></td>
  </tr>
  <tr>
    <td height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienFichierChangementAdresseRedevable"/>"> Role changement adresse redevable </a> </td>
  </tr>
  <tr>
    <td height="22" align="center"><A NAME="Impreesion">
    <p>&nbsp;</p>
    <p></a></p></td>
  </tr>
</table>
<% }  %>

<p align="center"><a href="javascript:window.history.go(-1);"><font color="#0000FF"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">eprendre</font></b></font></a></p>
<p>&nbsp;</p>

  <p>&nbsp;</p>








  &nbsp;
          <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
</body>



</Form>
</html>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 













