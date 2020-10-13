<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen"><SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" top margin="0" >
<script type="text/javascript" src="./resources/js/controle.js"></script>  





<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.administration.BAffGroupeTaxe"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>

<script type="text/javascript">  


function supprimer()
{
       
	 var choix = confirm("Voulez-vous supprimer ce groupe de taxe  ?");
	 if (choix) 
	 {       
	    document.forms[0].choix.value="supprimer";
		document.forms[0].submit();  
	 }  
}

function valider()
{

   var verfiNom= verifier("Groupe",document.forms[0].libelle.value); 
	 	
   if( verfiNom== 0 )     
      {
   		 document.forms[0].submit();  	 
      }

}



</script>


<form method="POST" action="gestionGroupeTaxe" name="formulaire" >

<input type="hidden" name="listeDesTypesDeTaxeLiee" value="<jsp:getProperty name="bean" property="listeDesTypesDeTaxeLiee" />" />
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="idGroupe" value="<%=request.getParameter("idGroupe") %>" />

  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="267">
 <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>          </td>
 </tr>
   

    <tr>
      <td width="1133" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!bean.getCreation()){%>  
	              <font size="4"><b>MODIFIER LE GROUPE DE TAXE  :&nbsp</b></font>
                  <% }else{ %>                 
                 <font size="4"><b>AJOUTER </b></font>  
	              <font size="4"><b> UN GROUPE DE TAXE </b></font>
              <font size="4"><b> :&nbsp</b></font>                 
		      <% } %>                 
        </p>      </td>
    </tr>
<% if(!bean.getCreation()){%> 
    <tr>
      <td width="232" height="23">
        <b><font face="Arial" size="2">&nbsp;Identifiant :</font></b>      </td>
      <td width="899" height="23">
        <input type="text" readonly name="idGroupe" size="20" value="<jsp:getProperty name="bean" property="idGroupe" />" >      </td>
    </tr>
    
<%}%>
  
 <% if(!bean.getCreation()){%> 
 <%}%> 
 
    <tr>
      <td width="1131" height="21" colspan="2">&nbsp;      </td>
    </tr>   

    <tr>
      <td width="232" height="23"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Groupe</font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b> <font face="Arial" size="2"><b>:</b></font> </span></td>
      <td width="899" height="23">
        <input type="text"  name="libelle" size="30" value="<jsp:getProperty name="bean" property="libelle"  />" >      </td>
    </tr>

    
    <tr>
      <td height="24"><p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Type de taxe
        associé :</font></b> </td>
      <td height="24"><jsp:getProperty name="bean"  property="boutonlisteDesTypesDeTaxeLiee" />  
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
      <td align="center" width="375">
        <a href="javascript:supprimer();"><img border="0" src="images/supprimer.gif" width="150" height="20"></a>      </td>
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



<p align="center"><a href="./entree?action=liste_groupeTaxe.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
<font face="Arial" size="3">iste des groupes de taxes</font></b></font></a>

<p align="center">&nbsp;</p>
</body>
































