<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" top margin="0" >
<script type="text/javascript" src="./resources/js/controle.js"></script>  

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.reclamation.BAffModeleCourier"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>

<script type="text/javascript">  

function changerModele()
{  
  document.forms[0].cheminModeleCourier.value = document.forms[0].cheminModeleCourierIn.value; 
  
}

function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer ce Modele  ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";
				   document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer ce modele de couriers, il y a des éléments liés ");	   
}

function valider()
{
     var verfiNomModeleCourier= verifier(" Nom du Modele ",document.forms[0].nomModeleCourier.value); 
     var verfiCheminModeleCourier= verifier(" chemin du Modele ",document.forms[0].cheminModeleCourier.value); 
     	 if( verfiNomModeleCourier== 0 && verfiCheminModeleCourier== 0 )     
     {
    	 document.forms[0].submit();  	 
     }
    else
     {
       //alert("Champs manquant !!")
     }

}






</script>



<form method="POST" action="gestionModeleCourier" name="formulaire" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="peutEtreSupprimer" value="<jsp:getProperty name="bean" property="peutEtreSupprimer"/>">

<table border="1" cellpadding="0" cellspacing="0" width="1148" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="23">
 <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/rapports.jpg" width="40" height="40"></b></font>          </td>
 </tr>
   

    <tr>
      <td width="1133" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!bean.getCreation()){%>  
	              <font size="4"><b>MODIFIER UN MODELE :&nbsp</b></font>
                  <% }else{ %>                 
                 <font size="4"><b>AJOUTER </b></font>  
	              <font size="4"><b> UN MODELE </b></font><font size="4"><b>:&nbsp</b></font>                 
		      <% } %>                 
        </p>      </td>
    </tr>
<% if(!bean.getCreation()){%> 
    <tr>
      <td width="232" height="23">
        <font face="Arial" size="2">
        <b>&nbsp;</b>      </font> <font face="Arial" size="2">
        <b>Code :</b>      </font>      </td>
      <td width="899" height="23">
        <input type="text" readonly name="idModeleCourier" size="20" value="<jsp:getProperty name="bean" property="idModeleCourier" />" >      </td>
    </tr>
    
<%}%>
       
 <% if(!bean.getCreation()){%> 
 <%}%> 
    

    <tr>
      <td width="1131" height="21" colspan="2">&nbsp;      </td>
    </tr>

    <tr>
      <td width="232" height="23"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">&nbsp;Nom du modèle </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b> <font face="Arial" size="2"><b>:</b></font> </span></td>
      <td width="899" height="23">
        <input type="text"  name="nomModeleCourier" size="30" value="<jsp:getProperty name="bean" property="nomModeleCourier"  />" >      </td>
    </tr>

    <tr>
      <td height="1" width="232">
        <p style="margin-left: 5; margin-right: 5"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Chemin vers le modèle </font></b> </span><b><font face="Arial" size="2">
        </font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b>
        <font face="Arial" size="2"><b>:</b></font>      </td>
      <td width="899" height="1">
      

         <input type="text" name="cheminModeleCourier" READONLY size="100" value="<jsp:getProperty name="bean" property="cheminModeleCourier"/>" >&nbsp;
         <input type="file" name="cheminModeleCourierIn"   onchange="changerModele();" size="1" >
         &nbsp;
	</td>
    </tr>

    <tr>
      <td width="232" height="19">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2" color="#FF0000"><i>*
      Champs obligatoires</i></font></b>      </td>
      <td width="899" height="19">&nbsp;      </td>
    </tr>
    <tr>
      <td width="1133" colspan="2" height="1">
        <table border="0" width="100%">
          <tr>
 
    
  <% if(!bean.getCreation()){%>   
    <td align="center"><a href="javascript:valider();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a></td>
      <% if(bean.getPeutEtreSupprimer()){%>   
      <td align="center" width="375">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>      </td>
        <%}%>
     
 <%}else{%>
 
   <td align="center" width="374">
        <p align="center">
        <a href="javascript:valider();"><img border="0" src="images/valider.gif" width="150" height="20" ></a>        </p>      </td>
  <%}%>
          </tr>
        </table>      </td>
    </tr>
  </table>  
</form>



<p align="center">
          <a href="javascript:window.history.go(-1);"><font color="#0000FF"><img border="0" src="images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3">eprendre</font></b></font></a></p>
<p align="center">&nbsp;</p>
</body>

</html>










