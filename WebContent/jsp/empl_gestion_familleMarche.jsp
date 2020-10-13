<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" topmargin="0">



<script type="text/javascript" src="./resources/js/controle.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffFamilleMarche" scope="request" class="fr.analogon.r2t.marche.BAffFamilleMarche"/>
<jsp:setProperty name="baffFamilleMarche" property="request" value="<%=request%>"/>

<script type="text/javascript">  


function valider()
{
  verfiLibelle= verifier("libelle Famille Marche",document.forms[0].libelleFamilleMarche.value);
  if(verfiLibelle== 0 )
  {      
     document.forms[0].submit();	   
  }	
}


function supprimer()
{
       if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer cette famille de marché ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";                
				  document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer cette famille de marché, il y a des informations liés  ");	   
}



</script>

<form method="POST" action="gestionFamilleMarche" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="peutEtreSupprimer" value="<jsp:getProperty name="baffFamilleMarche" property="peutEtreSupprimer"/>"  >

  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="68">
   
    <tr>
      <td width="100%" colspan="2" bgcolor="#AFF3BB" height="42">
        <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
      </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!baffFamilleMarche.getCreation()){%>  
	              <font size="4"><b>MODIFIER UNE FAMILLE :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER UNE FAMILLE :&nbsp</b></font>                 
		      <% } %>                 

                </p>
      </td>
    </tr>
<% if(!baffFamilleMarche.getCreation()){%> 
    <tr>
     
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Code famille : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <input type="text" readonly name="codeFamilleMarche" size="20" value="<jsp:getProperty name="baffFamilleMarche" property="codeFamilleMarche"/>" >	
      </td>
    </tr>
    
<%}%>

    

 <tr>
      <td width="1137" colspan="2" bgcolor="#ffffe8" height="19">
        &nbsp;
      </td>
 </tr>
    
    

    <tr>
      
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Libellé famille: </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      
      <td width="868" height="23">
        <input type="text"  name="libelleFamilleMarche" size="60" value="<jsp:getProperty name="baffFamilleMarche" property="libelleFamilleMarche"/>" >
      </td>
    </tr>  
    
    

    <tr>
      <td width="269" height="19">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
        Champs obligatoires</i></font></b>
      </td>
      <td width="868" height="19">&nbsp;
        
       </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" height="1">
        <table border="0" width="100%" height="1">
          <tr>


  <% if(!baffFamilleMarche.getCreation()){%>   
     <td align="center" height="1">
        <a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>
      </td>
      <td align="center" width="375" height="1">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>
      </td>   

 <%}else{%>
    <td align="center" width="374" height="1">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20" ></a>
        </p>
      </td>
 <%}%>

          </tr>
        </table>
      </td>
    </tr>

    
  </table>  
</form>

  <% if(!baffFamilleMarche.getCreation()){%>   
        <p align="center">
        <a href="./entree?action=liste_familleMarche.jsp"><font color="#0000FF">
        <img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
        <font face="Arial" size="3">iste des </font></b><font face="Arial" size="3"><b>marchés</b></font></font></a>
        <p align="center">&nbsp;</p>
   <%}%>
</body>

</html>






















