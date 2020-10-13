<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<script>

function verfierUnicite()
{
     
	 
     numeroEmplacementPersonalise=document.forms[0].numeroEmplacementPersonalise.value;
	 codeSecteur=document.forms[0].codeSecteur.value;
  	 
	 if ( numeroEmplacementPersonalise.length == 0)
	    alert("le numero de l'emplacement est obligatoire");
	 else
	 {
     
		 var url = "./verifierUnciteCodeNumEmplacement?codeSecteur="+codeSecteur+"&numeroEmplacementPersonalise="+numeroEmplacementPersonalise;    
			if (window.ActiveXObject)
			{
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			else if (window.XMLHttpRequest)
			{
				httpRequest = new XMLHttpRequest();
			}
			httpRequest.open("GET", url, true);        
			httpRequest.onreadystatechange = function() {processRequestUnicite(); } ;
			httpRequest.send(null); 
	}
 
}


function processRequestUnicite()
{   
			numeroEmplacementPersonalise=document.forms[0].numeroEmplacementPersonalise.value;
			codeSecteur=document.forms[0].codeSecteur.value;    
        if (httpRequest.readyState == 4)
        {
            if(httpRequest.status == 200)
            {
              var xmldoc = httpRequest.responseXML.documentElement;
              var node = xmldoc.getElementsByTagName("disponible"); 
			  var disponible ;
			  
			 for (i = 0; i < node.length; i++)
			 {				                              
                 disponible= xmldoc.getElementsByTagName("disponible")[i].childNodes[0].nodeValue;               
             }             	                           
             if ( disponible == 'ko')
			   alert("Un  emplacement existe deja avec le secteur "+codeSecteur+" et le numero d'emplacement "+ numeroEmplacementPersonalise);
             else if ( disponible == 'ok')
			    ok();
			 else 
			   alert("erreur");
			 
           }
           else
           {
               alert("Erreur chargement page \n"+ httpRequest.status +":"+ httpRequest.statusText);
           }             
        }
}





function ok()
{
       var reg=new RegExp("---");
       var tableau=document.forms[0].codeType.value.split(reg);
       document.forms[0].idImputation.value=tableau[0];
       document.forms[0].libelleImputation.value=tableau[1];
   
       //alert(document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value);
       //alert(document.forms[0].libelleImputation.value);

      var pos = document.forms[0].listeLibelleDesTypesDeTaxeAutorise.value.indexOf(document.forms[0].libelleImputation.value);
      if( pos !=-1 )
         document.forms[0].submit();
      else
         alert("Vous n'avez pas le droit de creer des emplacements du type "+document.forms[0].libelleImputation.value );
}
</script>
<%@ page language = "java" %>
<jsp:useBean id="beanType" scope="page" class="fr.analogon.r2t.view.role.BAffChoixTypeEmplacement"/>
<jsp:setProperty name="beanType" property="request" value="<%=request%>"/>
</head>
<body background="./images/nuages.jpg" topmargin="0" leftmargin="0">




<form method="POST" action="entree?action=empl_gestion_emplacementodp.jsp&choix=creer&optionTransfert=role&origine=origine">
<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
	String ville = (String)session.getAttribute("ville"); 

%>	
<input type="hidden" name="numRedevable" maxlength=30 value="<jsp:getProperty name="beanType" property="numRedevable"/>" >
<input type="hidden" name="idImputation" maxlength=30 >
<input type="hidden" name="libelleImputation" maxlength=30 >
<input type="hidden" name="choix" value="creer">
<input type="hidden" name="listeLibelleDesTypesDeTaxeAutorise" value="<%out.print(listeLibelleDesTypesDeTaxeAutorise);%>"/>
<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF">
<table width="100%">
        <tr>
          <td width="1125" bgcolor="#AFF3BB">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/emplacement.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td width="1125" bgcolor="#DFFFEF">
            <p style="margin-left: 5"><font color="#000000"><b>
            <font size="3" face="Arial">CREATION&nbsp; D'UN NOUVEL EMPLACEMENT :</font></b></font>
          </td>
        </tr>

        <td width="1125">

<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="93">
        <tr>
        <td  width="16%" background="./images/fond_bleu.gif" height="23">
          <font size="2" face="Arial"><b>&nbsp;Type de taxe </b></font><b><font color="#FF0000" face="Arial" size="4">*
          </font><font size="2" face="Arial">:</font></b>
        </td>
        <td width="84%" height="23">
          <jsp:getProperty name="beanType" property="listeTypes"/>
                  </td>
        </tr>
        
        <tr>
        <td  width="16%" background="./images/fond_bleu.gif" height="23">
          &nbsp;
          <font size="2" face="Arial"><b>Secteur </b></font><b><font color="#FF0000" face="Arial" size="4">*
          </font><font size="2" face="Arial">:</font></b></td>
        <td width="84%" height="23">
          <jsp:getProperty name="beanType" property="secteur"/>
        </td>
        </tr>
        
        <% if (  ville.equalsIgnoreCase("toulon")) { %> 
        
        <tr>
        <td  width="16%" background="./images/fond_bleu.gif" height="23">
          &nbsp;<b><font size="2" face="Arial">N°Emplacement </font> </b><b><font color="#FF0000" face="Arial" size="4">*
          </font><font size="2" face="Arial">:</font></b></td>
        <td width="84%" height="23">
     			<input type="text" size="10" name="numeroEmplacementPersonalise" value="" /> 			         
        </td>
        </tr>
        
        <% } %> 
        
        <tr>
        <td  width="16%" background="./images/fond_bleu.gif" height="23">
          &nbsp;
          <font size="2" face="Arial"><b>Année </b></font><b><font color="#FF0000" face="Arial" size="4">*</font><font size="2" face="Arial">
          :</font></b></td>
        <td width="84%" height="23">
			<input type="text" size="4" name="anneeExerciceImputation" readOnly 
			maxlength="4" name="anneeTypeTaxe"  value=<jsp:getProperty name="beanType" property="anneeTypeTaxe"/> >			         
			
        </td>
        </tr>
        
        <tr>
        <td  width="16%" background="./images/fond_bleu.gif" height="24">
          <b><font face="Arial" color="#FF0000" size="2"><i>(</i>*<i>) : Saisie obligatoire</i></font></b>
        </td>
        <td width="84%" height="24">
          <p align="center">

		<% if (  ville.equalsIgnoreCase("toulon")) { %> 
   	 			<a href="javascript:verfierUnicite();" target="bas">
    	<% } else { %> 
	        	<a href="javascript:ok();" target="bas">
    	<% } %> 

	
        
        
        
    	<img border="0" src="./images/valider.gif" align="absmiddle" width="150" height="17"></a>
        </td>
        </tr>        
</table>






     
          
     
     <input type="hidden" name="numRedevable" maxlength=30 value="<jsp:getProperty name='beanType' property='numRedevable'/>" />
    <p style="margin-left: 2">
 </form>
</table>
</body>



