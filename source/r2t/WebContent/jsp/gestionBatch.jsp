<html>
<head>
  <%@ page language = "java" %>
  <jsp:useBean id="baffBatch" scope="page" class="fr.analogon.r2t.view.batch.BaffBatch" />
  <jsp:setProperty name="baffBatch" property="request" value="<%=request%>"/>
<title>Batch</title>

</head>
<script>
function ouvrirFichierDesFactures()
{
 alert("test");
}
</script>

<%
String ville = (String)session.getAttribute("ville"); 
%>

<Form method="POST" action="imprimerFactures" >
<body background="./images/nuages.jpg" topmargin="0" link="#336699" alink="#336699" vlink="#336699" hlink="#CC3300">



  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="17" width="1117">
    <tr>


  
      <td width="1113" bgcolor="#AFF3BB" height="11">
        <table border="1" width="100%">
          <tr>
            <td width="25%" align="center">


  
   <a NAME="moduleOuvrage" href="#informationBatch">   

      <font color="#0000FF"><b><font size="4"><span style="text-transform: uppercase">Information</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        BATCH</font></b></span></font>

  
   </a>

  
            </td>
			
			<% if (ville.equalsIgnoreCase("Bordeaux")) { %>
			
            <td width="25%" align="center"><a NAME="moduleOuvrage" href="#pleaiade"><b><font size="4" color="#0000FF"><span style="text-transform: uppercase">FLUX
        PLEIADE</span></font></b>
  
              </a>

  
            </td>
            <td width="25%" align="center"><a NAME="moduleOuvrage" href="#rapport"><b><font size="4" color="#0000FF"><span style="text-transform: uppercase">RapPorts
        </span></font></b>  
              </a>
  
            </td>
			
			<% } %>
			
			
            <td width="25%" align="center"><a NAME="moduleOuvrage" href="#Impreesion"><font color="#0000FF"><b><font size="4"><span style="text-transform: uppercase">Impression</span></font></b></font>

  
              </a>

  
            </td>
          </tr>
        </table>
   </td>


    </tr>
    <tr>


  
  

      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/information.jpg" width="40" height="40">&nbsp;</td>


      </a>

  
    </tr>
    <tr>


  
   <A NAME="informationBatch">   

      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Information</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        SUR LE BATCH :</font></b></span></td>

  
      </a>

  
    </tr>


    <tr>
  
    <td width="575" bgcolor="#ffffff" height="19">
     <b>
       <font face=arial color=#000000 size=3>&nbsp;Date de création du batch: &nbsp;</font></b><b><font face="arial" color="#000000" size="3">
       
        </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="dateDeCreation" /></font>
       </font></b>
    
    
    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b>     
      <font face=arial color=#000000 size=3>&nbsp;Type de taxe : &nbsp;&nbsp;
      </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="typeDeTaxe" /></font>

      
     </b> 
    
    
    
     
    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="18">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Numéro du batch :

          </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="numeroBatch" /></font>
</b>
    
    </tr>
    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Nombre de factures créées :
          
                    </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="nombreFactures" /></font>
     </b>


 
     
    </tr>
    
    <% if(   baffBatch.getEditerRemboursement().equalsIgnoreCase("true")  ) { %> 

    <tr>
    <td width="575" bgcolor="#ffffff" height="19">     
     <b><font face="arial" color="#000000" size="3">
          &nbsp;Nombre de remboursements créées :
          
                    </font><font face="arial" color="#CC3300" size="3"> <jsp:getProperty name="baffBatch" property="nombreRemboursements" /></font>
     </b>


 
     
    </tr>
    <% } %>
  </table>
<p>&nbsp;</p>


<% if(baffBatch.getEditerDesFactureAnnulee().equalsIgnoreCase("true") 
   || 
   baffBatch.getEditerRemboursement().equalsIgnoreCase("true") 
   ||
   !baffBatch.getNombreFactures().equalsIgnoreCase("0") ) { %> 
<table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="114" width="1117">
 
  <tr>
    <td bgcolor="#AFF3BB" height="46" colspan="2"><p align="center"><a name="rapport"><img border="0" src="images/rapports.jpg" width="40" height="40">&nbsp;</a></td>
  </tr>
  <tr>
    <td bgcolor="#AFF3BB" height="22" colspan="2"><b><font size="4"><span style="text-transform: uppercase">
	Rapports & role </span></font></b><span style="text-transform: uppercase"><b><font size="4">:</font></b></span></td>
  </tr>
  <% if ( baffBatch.getEditerRemboursement().equalsIgnoreCase("true")) { %> 
 <tr>
    <td width="489" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienRemboursement"/>"> Remboursement </a> </td>
    <td width="622" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienRoleRemboursement"/>"> Role remboursement </a> </td>
  </tr>
<%}%> 
  
 <% if ( baffBatch.getEditerDesFactureAnnulee().equalsIgnoreCase("true")) { %> 
 <tr>
    <td width="489" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienRoleAnnulation"/>"> R&ocirc;le Annulation </a> </td>
    <td width="622" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienCertificatAnnulationRecette"/>"> Certificat d&rsquo;annulation de recette </a> </td>
  </tr>
<%}%>

<% if(!baffBatch.getNombreFactures().equalsIgnoreCase("0")) { %> 
  <tr>
    <td width="489" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienRoleFacturation"/>"> R&ocirc;le facturation </a> </td>
	<% if ( ! ville.equalsIgnoreCase("toulon")) { %>    
		<td width="622" height="22" align="center"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienRoleJustificatif"/>"> Justificatif de recette </a> </td>
	<% } %>
	
  </tr>
<%}%>  


<% if ( ! ville.equalsIgnoreCase("toulon")) { %>

 <% if (! baffBatch.getAEditerRoleChangementAdresseRedevable().equalsIgnoreCase("false")) { %> 
  <tr>
    <td width="1111" height="22" align="center" colspan="2"><a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienaRoleChangementAdresseRedevable"/>"> R&ocirc;le
      changement adresse redevable</a> </td>
  </tr>
<%}%> 

<% } %>

</table>
<p>&nbsp;</p>
<% } %> 




<% if (ville.equalsIgnoreCase("Bordeaux")) { %>
  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="52" width="1117">
    <tr>


  
     

      <td width="1113" bgcolor="#AFF3BB" height="46">
        <p align="center">&nbsp;<A NAME="Impreesion"><img border="0" src="images/pleaide.jpg" width="40" height="40">


      </a>

  
      </td>


      </a>

  
    </tr>
    <tr>


  
   <A NAME="pleaiade">   

      <td width="1113" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">FLUX
        PLEIADES</span></font></b>
      <span style="text-transform: uppercase"><b><font size="4">
        : </font></b></span></td>

  
      </a>

  
    </tr>


    <tr>
      <td width="1113" height="22"><b><span style="text-transform: uppercase"><font size="4">&nbsp;</font></span><font size="4">
      <span style="text-transform: uppercase">
        </span></font><font face="arial" color="#000000" size="3">
        Fichier envoyé à Pléiades :&nbsp; 
        <a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienFichierToPleaide"/>">        
           <jsp:getProperty name="baffBatch" property="nomFichierToPleaide" /> 
        </a>       
        </font></b></td>
    </tr>


    <tr>
      <td width="1113" height="22"><b><span style="text-transform: uppercase"><font size="4">&nbsp;</font></span><font size="4">
      <span style="text-transform: uppercase">
        </span></font><font face="arial" color="#000000" size="3">
        Fichier reçu de Pléiades :&nbsp; 
        <a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienFichierFromPleaide"/>">        
           <jsp:getProperty name="baffBatch" property="nomFichierFromPleaide" /> 
        </a>       
        </font></b></td>
    </tr>


  </table>
<p>&nbsp;</p>
<!-- Fin de verfication de la ville-->
<% }%> 




<% if( !baffBatch.getNombreFactures().equalsIgnoreCase("0") ) { %> 



<A NAME="Impreesion"> 

  <table bordercolordark="#ffffff" bordercolorlight="#c0c0c0" border="1" cellpadding="0" cellspacing="0" height="295" width="1117">
    <tr>

      <td width="2226" bgcolor="#AFF3BB" height="46">
        <p align="center"><img border="0" src="images/imprimante.jpg" width="40" height="40">&nbsp;</td>


      </a>

  
    </tr>
    <tr>

 

      <td width="2226" bgcolor="#AFF3BB" height="22"><b><font size="4"><span style="text-transform: uppercase">Impression</span></font></b><span style="text-transform: uppercase"><b><font size="4">
        :</font></b></span></td>

  
    </tr>


    <tr>
        <td  width="100%" bgcolor="#FFFFE8" height="19">
          <font size="3" face="Arial" color="#000000"><b>&nbsp;Liste des
          factures :</b></font></td>
    </tr>
    <tr>
      <td width="1113" height="83" align="center">
        <p align="left">&nbsp;
        
        
        <SELECT size="4" name="v">
	        <option>------------------FACTURES----------------</option>
   		     <jsp:getProperty name="baffBatch" property="listeFactures" />
   		 </SELECT>

        </p>

 </td>
    </tr>
    <tr>
      <td width="1113" height="20" align="center">
       <a target='_blank' href="<jsp:getProperty name="baffBatch" property="lienFichierDesFactures" />" >
              <img border="0" src="images/lettre_i.gif" align="absmiddle" width="20" height="20"><font face="Arial" size="3"><b>mprimer toutes les factures créées par ce batch</b></font>
        </a>
      </td>
    </tr>    
    <tr>
      <td width="1113" height="1" align="center">
 </td>
    </tr>
  </table>
  <% }%> 

<p align="center">
          &nbsp;</p>

</body>



</Form>
</html>






    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 













