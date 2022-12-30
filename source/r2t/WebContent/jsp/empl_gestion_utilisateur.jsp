<html> 
<head> 
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen"><SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
<body background="./images/nuages.jpg" top margin="0" >
<script type="text/javascript" src="./resources/js/controle.js"></script>  





<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.administration.BAffUtilisateur"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>

<script type="text/javascript">  
var listeTypeTaxe=new Array();

function initListeTypeTaxe()
{
  var bar = document.forms[0].listeDesTypesDeTaxeAutorise.value.split(";");
  for(var i = 0;i<bar.length;i++)
  {
	 //alert(bar[i]);
     if(bar[i].length != 0)
     {   
	     mettreAJourTableau(bar[i]);
	  }
  }
}



function majTypeTaxeAutoise(typeTaxe)
{
  //alert(typeTaxe);
  mettreAJourTableau(typeTaxe);
}


function mettreAJourTableau(what)
{  
	var existe="false";
	for(var i=0; i<listeTypeTaxe.length ;  i++)
	{		
		if (listeTypeTaxe[i]==what)
		{
		  listeTypeTaxe.splice(i,1);
		  existe="true";
		  break;
		}
	}
	if(existe=="false") 
	  listeTypeTaxe[listeTypeTaxe.length]=what;	
	
	var listeDesTypesDeTaxeAutorise="";
   for(var i=0; i<listeTypeTaxe.length ;  i++)
	{
	  listeDesTypesDeTaxeAutorise = listeDesTypesDeTaxeAutorise + ";" +listeTypeTaxe[i];	
   }
   document.forms[0].listeDesTypesDeTaxeAutorise.value= listeDesTypesDeTaxeAutorise;
	
	//alert(listeTypeTaxe.length );  
}




function supprimer()
{
        if (document.forms[0].peutEtreSupprimer.value=="true")
    	{
		     var choix = confirm("Voulez-vous supprimer cet Utilisateur  ?");
			 if (choix) 
             {       
                document.forms[0].choix.value="supprimer";
				document.forms[0].submit();  
			 }
		}
		else
			alert("Impossible de supprimer cet Utilisateur, il y a des éléments liés ");	   
}

function valider()
{
     var verfiNomUtilisateur= verifier("Nom Utilisateur",document.forms[0].nomUtilisateur.value); 
     var verfiPrenomUtilisateur= verifier("Prenom Utilisateur",document.forms[0].prenomUtilisateur.value); 
	 var  verfiLoginUtilisateur= verifier("login Utilisateur",document.forms[0].loginUtilisateur.value);
	 var verfiMotDePasseUtilisateur= verifier("Mot de passe",document.forms[0].motDePaseeUtilisateur.value);
	 var verfiActifUtilisateur= verifier("Actif ",document.forms[0].actifUtilisateur.value);  
	 var verfiTypeUtilisateur= verifier("Type Utilisateur ",document.forms[0].typeUtilisateur.value); 
	 
	 if(document.forms[0].loginUtilisateur.value.length < 4 )
	 {
	    alert("Le login doit etre au mois sur 4 caracteres");
		verfiLoginUtilisateur= -1;
		//verfier les caractere interdie dans le login et password 
		
	 }  
	  if(document.forms[0].motDePaseeUtilisateur.value.length < 4 )
	 {
	    alert("Le mot de passe doit etre au mois sur 4 caracteres");
		verfiMotDePasseUtilisateur= -1;
	 }  
	 
	 if(document.forms[0].typeUtilisateur.value=="CONSULTEUR" && listeTypeTaxe.length >0  )
	 {
	    alert("Le type CONSULTEUR ne peut gerer aucun type de taxe ");
	 }
	 else if(document.forms[0].typeUtilisateur.value=="GESTIONNAIRE" && listeTypeTaxe.length == 0  )
	 {
	    alert("Le type GESTIONNAIRE doit geré au moins un type de taxe ");
	 }
	  else if(document.forms[0].typeUtilisateur.value=="ADMINISTRATEUR" && listeTypeTaxe.length == 0  )
	 {
	    alert("Le type ADMINISTRATEUR doit geré au moins un type de taxe ");
	 }

	 else
	 {
	
	   if( verfiNomUtilisateur== 0 && verfiPrenomUtilisateur== 0 && verfiLoginUtilisateur== 0 &&  verfiMotDePasseUtilisateur== 0 &&  verfiActifUtilisateur== 0 &&
           verfiTypeUtilisateur== 0   )     
       {
    		 document.forms[0].submit();  	 
       }
       else
      {
       //alert("Champs manquant !!")
      }
    }

}



</script>



<form method="POST" action="gestionUtilisateur" name="formulaire" >
  <div id="calcontainer"></div>
  
<input type="hidden" name="listeDesTypesDeTaxeAutorise" value="<jsp:getProperty name="bean" property="listeDesTypesDeTaxeAutorise" />" />
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden"  name="peutEtreSupprimer" size="20" value="<jsp:getProperty name="bean" property="peutEtreSupprimer" />" >

  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="267">
 <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1142" height="42">
          <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/users.png" width="40" height="40"></b></font>          </td>
 </tr>
   

    <tr>
      <td width="1133" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!bean.getCreation()){%>  
	              <font size="4"><b>MODIFIER LE PROFIL D'UN UTILISATEUR  :&nbsp</b></font>
                  <% }else{ %>                 
                 <font size="4"><b>AJOUTER </b></font>  
	              <font size="4"><b> UN UTILISATEUR </b></font>
              <font size="4"><b> :&nbsp</b></font>                 
		      <% } %>                 
        </p>      </td>
    </tr>
<% if(!bean.getCreation()){%> 
    <tr>
      <td width="232" height="23">
        <b><font face="Arial" size="2">&nbsp;Code utilisateur :</font></b>      </td>
      <td width="899" height="23">
        <input type="text" readonly name="codeUtilisateur" size="20" value="<jsp:getProperty name="bean" property="codeUtilisateur" />" >      </td>
    </tr>
    
<%}%>

     
    
    

    
 <% if(!bean.getCreation()){%> 
 <%}%> 
    
    

 
    
    

    <tr>
      <td width="1131" height="21" colspan="2">&nbsp;      </td>
    </tr>

    

    <tr>
      <td width="232" height="23"><span style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Nom</font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b> <font face="Arial" size="2"><b>:</b></font> </span></td>
      <td width="899" height="23">
        <input type="text"  name="nomUtilisateur" size="30" value="<jsp:getProperty name="bean" property="nomUtilisateur"  />" >      </td>
    </tr>

    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Prénom</font><i><font face="Arial" size="2" color="#FF0000">*</font></i></b>
        <font face="Arial" size="2"><b>:</b></font>      </td>
      <td width="899" height="23">
        <input type="text"  name="prenomUtilisateur" size="30" value="<jsp:getProperty name="bean" property="prenomUtilisateur" />" >      </td>
    </tr>

     <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Login
        </font></b>
        <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b>      </td>
      <td width="899" height="23"><input type="text"  name="loginUtilisateur" size="30" value="<jsp:getProperty name="bean" property="loginUtilisateur" />" ></td>
    </tr>
	
    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Mot de passe
        </font></b>
        <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b>      </td>
      <td width="899" height="23"><input type="text"  name="motDePaseeUtilisateur" size="30" 
	  value="<jsp:getProperty name="bean" property="motDePaseeUtilisateur" />" ></td>
    </tr>

    <tr>
      <td height="23" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Actif
        </font></b>
        <b><font face="Arial" size="2" color="#FF0000"><i>*</i></font></b><b> :</b>      </td>
      <td width="899" height="23"><jsp:getProperty name="bean"  property="actifUtilisateur" /></td>
    </tr>
    <tr>
      <td height="24"><p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Type
        d'utilisateur </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b> </td>
      <td height="24"><jsp:getProperty name="bean"  property="typeUtilisateur" />      </td>
    </tr>
    <tr>
      <td height="24"><p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Type de taxe
        autorisé </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:</font></b> </td>
      <td height="24"><jsp:getProperty name="bean"  property="boutonListeDesTypesDeTaxeAutorise" />    
      </td>
    </tr>

 <tr>
      <td height="19" width="232">
        <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2">Remarques
        :</font></b>      </td>
      <td width="899" height="19"><label>
        <textarea name="remarqueUtilisateur" cols="70" rows="4"><jsp:getProperty name="bean" property="remarqueUtilisateur" /></textarea>
      </label>	  </td>
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



<p align="center"><a href="./entree?action=liste_utilisateur.jsp"><font color="#0000FF">
<img border="0" src="images/lettre_l.gif" align="absmiddle" width="20" height="20"><b>
<font face="Arial" size="3">iste des utilisateurs</font></b></font></a>

<p align="center">&nbsp;</p>
</body>

<script>
  initListeTypeTaxe();
</script>

</html>
































