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
<jsp:useBean id="baffPayement" scope="request" class="fr.analogon.r2t.view.regie.BaffPayement"/>
<jsp:setProperty name="baffPayement" property="request" value="<%=request%>"/>

<script type="text/javascript">  

function changerTypeTaxe()
{
	
}

function valider()
{
  verfiNumeroQuittance= verifier("numero de quittance",document.forms[0].numeroQuittance.value);
  verfiDatePayement= verifier("date",document.forms[0].datePayement.value);
  verfiMontantPayement= verifier("montant",document.forms[0].montantPayement.value);
  action = document.forms[0].choix.value;
  
  
  if(verfiNumeroQuittance== 0 &&  verfiDatePayement== 0 && verfiMontantPayement==0)
  {      
      var choix = confirm("Voulez-vous "+action+" ce paiement ?");
      if (choix) 
      {
          document.forms[0].choix.value="ajouter";
	   	  document.forms[0].submit();  
	  } 
     	   
  }	
}


function supprimer()
{
       
     var choix = confirm("Voulez-vous supprimer ce paiement ?");
	 if (choix) 
           {       
              document.forms[0].choix.value="supprimer";                
		      document.forms[0].submit();  
	 	}  
}

function modifier()
{
       
     var choix = confirm("Voulez-vous modifier ce paiement ?");
	 if (choix) 
           {       
              document.forms[0].choix.value="modifier";                
		      document.forms[0].submit();  
	 	}  
}



</script>

<form method="POST" action="gestionPayement" >
<input type="hidden" name="choix" value="<%=request.getParameter("choix") %>" />
<input type="hidden" name="paiementMarche" value="true" />


  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" align="center" height="68">
   
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="42">
        <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/typeTaxe.jpg" width="40" height="40"></b></font>
      </td>
    </tr>
    <tr>
      <td width="1139" colspan="2" bgcolor="#AFF3BB" height="22">
              <p align="left">
              <% if(!baffPayement.getCreation()){%>  
	              <font size="4"><b>MODIFIER UN PAIEMENT :&nbsp</b></font>
              <% }else{ %>                 
                 <font size="4"><b>AJOUTER UN PAIEMENT :&nbsp</b></font>                 
		      <% } %>                 

                </p>
      </td>
    </tr>
<% if(!baffPayement.getCreation()){%> 
    <tr>
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Id paiement : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <input type="text" readonly name="idPayement" size="20" value="<jsp:getProperty name="baffPayement" property="idPayement"/>" >	
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
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Marché : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
           <jsp:getProperty name="baffPayement" property="nomMarche"/>
      </td>
    </tr>
    <tr>
      
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Numéro quittance : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <input type="text"  name="numeroQuittance" size="20" value="<jsp:getProperty name="baffPayement" property="numeroQuittance"/>" >
      </td>
    </tr>
    
    
    <tr>
          <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Date : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
          </td>
          <td width="222">                      
            <input readonly maxlength="10" type="text" name="datePayement" id="datePayement" size="12" value="<jsp:getProperty name="baffPayement" property="datePayement"/>" >
            
            <img onclick="displayCalendar(document.forms[0].datePayement,'dd/mm/yyyy',this)"
            src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />
          
          </td>
      </tr>
    
    <tr>
      <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Montant (XX.YY): </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <input type="text"  name="montantPayement" size="20" value="<jsp:getProperty name="baffPayement" property="montantPayement"/>" >
      </td>
    </tr>
    <tr>
     <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Type de paiement : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <jsp:getProperty name="baffPayement" property="typePayementMarche"/>
      </td>
    </tr>
       
  <% if(!baffPayement.getCreation()){%>     
       
    <tr>
     <td background="./images/fond_bleu.gif" width="218">
            <font color="#000000"><b><font face="Arial" size="2">&nbsp;Etat du paiement : </font></b></font>
            <font face="Arial" size="2" color="#FF0000"><i>*</i></font>
       </td>
      <td width="868" height="23">
        <jsp:getProperty name="baffPayement" property="etatPaiement"/>
      </td>
    </tr>
<% } %>

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


  <% if(!baffPayement.getCreation()){%>   
     <td align="center" height="1">
        <a href="javascript:modifier();"><img border="0" src="./images/modifier.gif" width="150" height="20"></a>
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

  
</body>

</html>

  <% if(!baffPayement.getCreation()){%>

 
<% } %>


















