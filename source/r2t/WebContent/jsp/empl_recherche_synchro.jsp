<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
 <link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
 <SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>

<%@ page language = "java" %>
<jsp:useBean id="bean" scope="request" class="fr.analogon.r2t.administration.BAffListeContoleTerrain"/>
<jsp:setProperty name="bean" property="request" value="<%=request%>"/>
  <script>   
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >

<form method="POST" action="entree?action=HistoriqueSynchronisation.jsp">

<input type="hidden" name="choix" size="20" value="chercher" >

  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="50">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/recherche.png" width="40" height="40"></b></font>          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" height="23">
            <b><font size="4">RECHERCHE :</font></b>          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-crit�res  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
            d�taill�es.</font></td>
        </tr>

		  <tr>
          <td height="22"><b> <font face="Arial" size="2">&nbsp;Quartier  :</font></b></td>
		  <td height="22"> <jsp:getProperty name="bean" property="listeQuartier" />
		  </td>
		  <td height="22">&nbsp;&nbsp;</td>
		  </tr>        
        
       <tr>
      <td width="231" height="22"><b><font face="Arial" size="2">&nbsp;Date de
        contr�le  :</font></b>      </td>
      <td width="252" height="22">
        
            <input type="text"  readOnly name="dateControle" id="dateControle" size="15"><b><font size="2" face="Arial">        
			
			<img onclick="displayCalendar(document.forms[0].dateControle,'dd/mm/yyyy',this)"
			src="./images/calendar.gif" border="0" alt="S&eacute;lection de la date" />




	   
        		   
          </font></b>
        
            </td>
          <td width="655" height="22">
         
          &nbsp;&nbsp;&nbsp;
         
          </td>
        </tr>
		  
  

        
        
        
       
        
        
        
       <tr>
      <td width="231" height="22"><b><font face="Arial" size="2">&nbsp;Contr�leur
        :</font></b>      </td>
      <td width="252" height="22">
            <jsp:getProperty name="bean" property="listeUtilisateur" />
           </td>
          <td width="655" height="22">
          &nbsp;&nbsp;
          </td>
        </tr>
		  
  

        
        
        
       
        
        
        
       <tr>
      <td width="231" height="23">&nbsp;      </td>
      <td width="252" height="23">
        <b><font face="Arial" size="2">&nbsp;&nbsp; </font></b>      </td>
          <td width="655" height="23">
            <input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I4">          </td>
        </tr>
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
