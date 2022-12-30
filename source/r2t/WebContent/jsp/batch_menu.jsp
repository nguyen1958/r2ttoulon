<html>
<head>
<title>Menu batch et journeaux</title>
</head>
<jsp:useBean id="baffDivers" scope="page" class="fr.analogon.r2t.view.batch.BAffDivers" />
<body background="../images/back_parametres.gif" topmargin="0" leftmargin="0" link="#000000" vlink="#000000" alink="#000000">

  <table border="0" cellpadding="0" cellspacing="0" width="150" height="202">
    <tr>
      <td width="100%" height="89">
        <p><img border="0" src="../images/batch_dossier.gif" width="135" height="95">
        </p>
      </td>
    </tr>
    
    
<% 
String relance = baffDivers.getRelance(); 
String regie = (String)session.getAttribute("regie");
String ville = (String)session.getAttribute("ville");
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur");

   String accee="no" ; 
if (  typeUtilisateur !=null && typeUtilisateur.equalsIgnoreCase("admin")
 
    ) 
{
   accee ="ok"; 
}%>


<% if(  regie.equalsIgnoreCase("true") &&   typeUtilisateur.equalsIgnoreCase("gest") ) {%>
    <tr>
      <td width="100%" height="41"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a href="../empl/batch_index_divers.htm" target="droite"><font face="Arial" size="2"><img border="0" src="../images/lettre_t.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="2">raitements<br>
        </font></b></a><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font><font face="Arial" size="2">divers</font></td>
    </tr>

    <tr>
      <td width="100%" height="40">
 		</td>
    </tr>
    <tr>
      <td width="100%" height="40">
 		</td>
    </tr>
   
<% }else { %>




<% if (accee.equalsIgnoreCase("ok")) {%>

    <tr>
      <td width="100%" height="40">
      <a href="batch_index_excel.htm" target="droite">
      <b><font face="Arial" size="3">&nbsp;</font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;</font>
      <a href="../empl/index_batch_prefacturation.htm" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_l.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">ancement<br>
      </font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font face="Arial" size="2">&nbsp;d'un
      batch</font></td>
    </tr>
	
	
	<% 
	if (  regie !=null && regie.equalsIgnoreCase("true")) 
	{ 
	%>
		 <tr>
		  <td width="100%" height="18">
		  <img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
		</tr>
		
		 <tr>
		      <td width="100%" height="40">
		      <a href="batch_index_excel.htm" target="droite">
		      <b><font face="Arial" size="3">&nbsp;</font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;</font>
		      <a href="../empl/index_batch_raportsComptable.htm" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_l.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">ancement<br>
		      </font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
		      <font face="Arial" size="2">&nbsp;de rapports &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;comptables</font></td>
    	</tr>
		
	<% } %>  
	
	
	
	
    <tr>
      <td width="100%" height="1"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="41"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a href="../empl/batch_index_divers.htm" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_t.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">raitements<br>
        </font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font><font face="Arial" size="2">divers</font></td>
    </tr>
    
	
<% 
if (  relance !=null && relance.equalsIgnoreCase("true")) 
{ 
%>
	<tr>
      <td width="100%" height="12"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="16"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><b><font face="Arial" size="3"><a href="../empl/index_batch_relance.htm" target="droite"><img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20">elance</a></font></b></td>
    </tr>
<% } %>        
	
	
	<tr>
      <td width="100%" height="12"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
<% } %>    
    
    

    <tr>
      <td width="100%" height="41">
      <font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp; </font>
      <a href="../empl/historiqueBatch.htm" target="droite">      
      <font face="Arial" size="3"><img border="0" src="../images/lettre_h.gif" align="absmiddle"
       width="20" height="20"></font><b><font face="Arial" size="3">istorique<br>
       </font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </font><font face="Arial" size="2">des batchs</font></td>    
      
    </tr>
    

<% 
if (  regie !=null && regie.equalsIgnoreCase("false")) 
{ 
%>    <tr>
      <td width="100%" height="22"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>

    <tr>
      <td width="100%" height="19"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a href="../empl/factures_index.htm" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_g.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">estion
        des<br>
        </font></b></a><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font><font face="Arial" size="2">factures&nbsp;</font><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;
        </font></td>
    </tr>
<% } %>		
    <tr>
      <td width="100%" height="1"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="14"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a target="droite" href="../empl/refacturation_index.htm"><font face="Arial" size="3"><img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">efacturation</font></b></a></td>
    </tr>
    <tr>
      <td width="100%" height="9"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="16"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a href="../empl/raports_index.htm" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_r.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">apport</font></b></a></td>
    </tr>   
    <tr>
      <td width="100%" height="1"></td>
    </tr>
    <!--  
    <tr>
      <td width="100%" height="22">
        <font face="Arial" size="3">
        <img border="0" src="../images/masque_menu.gif" align="left" width="21" height="20">
      </td>
    </tr>
    <tr>
      <td width="100%" height="21">
      </td>
    </tr>
	-->
<%}%>
 	<tr>
      <td width="100%" height="9"><img border="0" src="../images/parametres_trait.gif" width="119" height="20"></td>
    </tr>
    <tr>
      <td width="100%" height="16"><font face="Arial" size="3">&nbsp;&nbsp;&nbsp;&nbsp;
        </font><a href="../entree?action=batch_export.jsp" target="droite"><font face="Arial" size="3"><img border="0" src="../images/lettre_e.gif" align="absmiddle" width="20" height="20"></font><b><font face="Arial" size="3">xport<br>
        </font></b></a><font face="Arial" size="3">
    </tr>

  </table>

</body>

</html>
