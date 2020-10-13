<html>

<head>

  <%@ page language = "java" %>
  <jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.administration.BAffRupture" />
  <jsp:setProperty name="bean" property="request" value="<%=request%>"/>
<title>Rupture de controle</title>
</head>
<script>
  function arreterFacturation(idArticle)
  {
    //alert(document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value);   
    if( document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value=="" )
       alert ("Vous n'avez pas les droits pour faire des modifications");
    else
   {        
	   var choix = confirm("Voulez- vous arreter la facturation de l'ouvrage numero " + idArticle + " ? ");
	   if (choix) 
      {       
        document.forms[0].action="./entree?action=ruptureControle.jsp&numOuvrage="+idArticle+"&nomQuartier="+document.forms[0].nomQuartier.value;
		  document.forms[0].submit();  
	   }	
   }
  }
  
function actualiser()
{
  //alert("test refresh");
  window.location.reload( false );
}

</script>


<Form method="POST" >
<input name="nomQuartier" type="hidden" value="<jsp:getProperty name="bean"  property="nomQuartier"/>" >
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300" >    
  


<%
String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise");  
%> 



<input name="listeLibelleDesTypesDeTaxeAutorise" type="hidden" value="<%out.print(listeLibelleDesTypesDeTaxeAutorise);%>" />



<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="56" width="100%">
 
  <tr>
    <td bgcolor="#AFF3BB" height="46"><p align="center">
    <a name="rapport">
    </a>
    <a href="javascript:actualiser();">
    <img border="0" src="images/rapports.jpg" width="40" height="40">
    </a>
    &nbsp;</td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">
    LISTE des rUPTURES DE CONTROLE</span></font></b><span style="text-transform: uppercase"><b><font size="4">
      :
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      Nombre de ruptureS =&nbsp;<jsp:getProperty name="bean"  property="nombreRupture" /></font></b></span></td>
      
  </tr>
 <tr background="images/nuages.jpg">
    <td width="1111" height="1" align="center">
      <table border="1" width="100%" height="15">
        <tr>
    <td width="176%" height="1" align="center">
  <jsp:getProperty name="bean"  property="listeRupture" />
    </td>
        </tr>
 
      </table>
 </td>
  </tr>

</table>


<BR>



<p align="center"><a href="javascript:window.history.go(-1);"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>eprendre</b></font></a>
          &nbsp;</p>
</body>



</Form>
</html>






    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 































