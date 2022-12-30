<html> 
<head> 

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" topmargin="0">



<script type="text/javascript" src="./resources/js/controle.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffVille" scope="request" class="fr.analogon.r2t.view.ville.BAffVille"/>
<jsp:setProperty name="baffVille" property="request" value="<%=request%>"/>

<script type="text/javascript">  

//Paul convertir les caractères entrés du clavier en majuscule
function caps(element){
	element.value = element.value.toUpperCase();
}

function valider()
{
	var resultat=0;
  	var verif=document.forms[0].code.value;
  	if(verif.length==0){
  		alert("Le champs code est obligatoire");
  		resultat=-1;
  	}
  	verif=document.forms[0].ville.value;
  	if(verif.length==0){
  		alert("Le champs ville est obligatoire");
  		resultat=-1;
  	}
  
  if(resultat== 0 )
  {      
     document.forms[0].submit();	   
  }	
}


function supprimer()
{
     var choix = confirm("Voulez-vous supprimer cette ville ?");
	 if (choix) 
           {       
              document.forms[0].choix.value="supprimer";                
		  document.forms[0].submit();  
	 }   
}



</script>

<form method="POST" action="gestionVille" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />

  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="68">
   
    <tr>
      <td width="100%" colspan="2" bgcolor="#AFF3BB" height="42">
        <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
      </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!baffVille.getCreation()){%>  
	              <font size="4"><b>MODIFIER UNE VILLE :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER UNE VILLE :&nbsp</b></font>                 
		      <% } %>                 

                </p>
      </td>
    </tr>
<% if(!baffVille.getCreation()){%> 
    <tr>
     
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;ID : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <input type="text" readonly name="id" size="10" value="<jsp:getProperty name="baffVille" property="id"/>" >	
      </td>
    </tr>
    
<%}%>

    

	 <tr>
	      <td background="./images/fond_bleu.gif" width="218">
	            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Code: </font></b></font>
	            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
	       </td>
	      
	      <td width="868" height="23">
	        <input type="text"  name="code" size="20" maxlength="20" value="<jsp:getProperty name="baffVille" property="code"/>" >
	      </td>
	 </tr>
    
    

	  <tr>     
	    <td background="./images/fond_bleu.gif" width="218">
	          <font color="#000000"><b><font face="Arial" size="2">&nbsp;Ville: </font></b></font>
	          <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
	     </td>
	    
	    <td width="868" height="23">
	      <input onkeyup="caps(this)" type="text"  name="ville" size="80" maxlength="80" value="<jsp:getProperty name="baffVille" property="ville"/>" >
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


  <% if(!baffVille.getCreation()){%>   
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

  <% if(!baffVille.getCreation()){%>   
        <p align="center">
        <a href="./entree?action=liste_ville.jsp"><font color="#0000FF">
        <img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
        <font face="Arial" size="3">iste des </font></b><font face="Arial" size="3"><b>villes</b></font></font></a>
        <p align="center">&nbsp;</p>
   <%}%>
</body>

</html>






















