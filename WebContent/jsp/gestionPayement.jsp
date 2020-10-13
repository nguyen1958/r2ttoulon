<html>
<head>
<script>
function  annulerPaiement()
{ 
	//Verifier la date du paiement et la date du jour courant 
	var d = new Date();
	
	var moisPayementFactue = document.getElementById('datePayement').value ;
	var jourAujourdhui = d.getDay();
	var moisAujourdhui = d.getMonth()+1;
	var anneeAujourdhui = d.getFullYear();

	var jourpaymennt = moisPayementFactue.substring(0,2);
	var moispaymennt = moisPayementFactue.substring(3,5);
	var anneepaymennt = moisPayementFactue.substring(6,10);

	var onpeuxannuller = false;
	//alert (moisAujourdhui + "/"+anneeAujourdhui)
	
	if (anneeAujourdhui == anneepaymennt &&  moisAujourdhui == moispaymennt ) 
		onpeuxannuller =true;
	
	if (onpeuxannuller)
	{
		ret=confirm("Etes-vous sûr de vouloir annuler ce paiement ?");
		if (ret == true)
		{
			  document.forms[0].annulerPaiement.value="true";
			  document.forms[0].choix.value="modifier";	  
			  document.forms[0].action="./entree?action=gestionPayement.jsp&idPayement="+document.forms[0].idPayement.value;
			  document.forms[0].submit();
		}
		  //window.location.reload( false );
	}
	else
		alert("Impossible d'annuler ce paiement, les états comptables du mois précédents ont été déjà validés ")

}
	


</script>
<jsp:useBean id="beanPayement" scope="page" class="fr.analogon.r2t.view.regie.BaffPayement"/>
  <jsp:setProperty name="beanPayement" property="request" value="<%=request%>"/> 
  
<script type="text/javascript" src="./resources/js/controle.js"></script>
<title>Paiement :</title>
</head>

<body background="./images/nuages.jpg" topmargin="0">
<form  method="POST">
<input name="annulerPaiement" type="hidden" value="">
<input name="choix" type="hidden" value="">
<input name="idPayement" type="hidden" value="<jsp:getProperty name="beanPayement" property="idPayement"/>">



  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="100%">
    <tr>
      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>
   </tr>

    <tr>  
      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Information</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        SUR LE PAIEMENT :</font></b></span></td>  
    </tr>

	<tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3"> &nbsp;Numéro de paiement :
     </font><font face="arial" color="#CC3300" size="3"> 
	   <jsp:getProperty name="beanPayement" property="idPayement"/>	
	 </font>
		</b>    
    </tr>
	
    <tr>  
	    <td width="575" bgcolor="#ffffff" height="19">
          <b>
       	<font face=arial color=#000000 size=3>&nbsp;Date du paiement : </font>
		<font face="arial" color="#CC3300" size="3"> 
		 <jsp:getProperty name="beanPayement" property="datePayement"/>
		 <input type="hidden" id="datePayement" value = "<jsp:getProperty name="beanPayement" property="datePayement"/>">	
       	</font>
	     </b>    
    </tr>
    
    
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3"> &nbsp;Nombre de factures payées :
     </font><font face="arial" color="#CC3300" size="3"> 
	  <jsp:getProperty name="beanPayement" property="nombreDeFacturePayee"/>
	 </font>
     </b>    
    </tr>
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3"> &nbsp;Montant total du
     paiement :&nbsp; </font>
	  
	  </font><font face="arial" color="#CC3300" size="3"> 
	  <jsp:getProperty name="beanPayement" property="montantPayement"/>	 
	 </font>
     </b>    
    </tr>
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Type de paiement :</font></b>

     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="typePayement"/>
	 	 
	 </b> 
	 </font>	 
    </tr>
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Numéro de quittance :</font></b>    
     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="numeroQuittance"/>	 
     </b>	 
	 </font>	 
	</tr>
    <%--paul carte bancaire --%>
    <% if (! beanPayement.getNumeroTransaction().equals("0")) { %>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Numéro de transaction :</font></b>    
     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="numeroTransaction"/>	 
	 </b> 
	 </font>
    </tr>
    <% } %>
    
	<% if (! beanPayement.getNumeroCheque().equals("0")) { %>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Banque :</font></b>    
     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="banque"/>	 
	 </b> 
	 </font>
    </tr>
    
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Numéro du chèque :</font></b>    
     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="numeroCheque"/>	 
	 </b> 
	 </font>
	</tr>
	<% } %>
	
	<tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">&nbsp;Redevable :</font></b>    
     <font face="arial" color="#CC3300" size="3"> 
     <b> 
	 <jsp:getProperty name="beanPayement" property="numRedevable"/>	 
	 </b> 
	 </font>
	</tr>
	
	<tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <p align="center"><b><font face="arial" color="#CC3300" size="3">&nbsp;</font>
     <font color="#FF0000">
	 <%
	if (beanPayement.getEtatPaiement().equalsIgnoreCase("annulle"))
		{
	%>  		
     
     <% if(beanPayement.getTypePayement().equalsIgnoreCase("Remise"))  { %>
		Cette remise est annulée ! 
		<% }else{ %>
		Ce paiement est annulé !
		<% } %>
     </font>
	 <% } else { %>	  
		<a href="javascript:annulerPaiement();"><font face="arial" size="3" color="#0000FF">
		<% if(beanPayement.getTypePayement().equalsIgnoreCase("Remise"))  { %>
		Annuler cette remise 
		<% }else{ %>
		Annuler ce paiement
		<% } %>
		</font></a></b>
	<% } %>	  
	</tr>
	
  </table>
</form>

  <BR><BR>   
  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="100%">
    <tr>
      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>
   </tr>

    <tr>  
      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase"></span></font></b><span style="text-transform: uppercase"><b><font size="4">
        LISTE DES FACTURES POUR CE PAIEMENT :</font></b></span></td>  
    </tr>

	<tr>  	  	  
		  <jsp:getProperty name="beanPayement" property="listeDesFactures"/>	 
	</tr>



</body>



