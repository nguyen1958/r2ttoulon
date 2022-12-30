<html>

<script>
function valider()
{
            veriflogi = true;
			verfipass = true;
            if (document.forms[0].login.value.length < 4  )  
			{
              alert("Le login doit faire au moins 4 caractères.");
			  veriflogi = false;
            }
			if (document.forms[0].pass.value.length < 4  )  
			{
              alert("Le mot de pasee doit faire au moins 4 caractères.");
			  verfipass = false;
            }
			
            if (veriflogi == true && verfipass == true)
			{
				//document.forms[0].action="";
                document.forms[0].submit();
            }

}
</script>




<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>R2T - Authentification - Analogon</title>
<%@ page language = "java"%>
<jsp:useBean id="baffAuth" scope="page" class="fr.analogon.r2t.main.BaffAuth"/>
<jsp:setProperty name="baffAuth" property="request" value="<%=request%>"/>
<jsp:useBean id="baffDivers" scope="page" class="fr.analogon.r2t.view.batch.BAffDivers"/>

<style type="text/css">
<!--
.Style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
</head>

<body background="./images/nuages.jpg" topmargin="0" leftmargin="0">
<%
String ville = baffDivers.getVille(); 
String siteWebVille = baffDivers.getSiteWebVille(); 
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur"); 
String typeApplication =  request.getRequestURL().toString();
if (typeApplication.contains("recensement"))
	typeApplication="recensement";
else if (typeApplication.contains("r2t"))
	typeApplication="r2t";
else
	typeApplication="";

%>
<table>
<form method="POST" target="_top" action="authentification">
<input type="hidden"  name= "msg" value="<jsp:getProperty name='baffAuth' property='message'/>" >


     <div align="center">
       <table border="0" cellpadding="0" cellspacing="0" width="100%" height="123">
        <tr>
          <td width="125" rowspan="2" height="100">
          </td>
          <td  height="80">
            <p align="right">
            <!--<img border="0" src="./images/mairietxt.gif" width="450" height="80">-->

            <font face="Arial" size="5"><b>
			<% if (typeApplication.contains("recensement")) { %>
			   Recensement
			<% }else{ %>           
			   R2T
			<%}%>
			- Direction de l’Organisation et de l’Informatique 		
			 </font>
            </b>
          </td>
        </tr>

      </table>

   </div>




  <center>



  <div align="center">
    <center>

  <table border="0" cellpadding="0" cellspacing="0" width="621" height="277">
    <tr>
      <td width="471" background="./images/acces.gif">
        <div align="center">
          <center>
          <table border="0" cellpadding="0" cellspacing="0" width="90%" bordercolordark="#FFFFFF">
            <%if (false) {%>
            <tr>
            <td align="center">
            Connection refusé.
            <td>
            <tr>
            <%}
            else{%>
            <tr>
              <td colspan="3">
                <p align="center" style="margin-left: 5"><font size="3" face="Arial">
                <span class="Style1">
                <jsp:getProperty name="baffAuth" property="messageSessionLogin"/> 
                </span>
                <!--Veuillez
                saisir votre mot de passe et votre login avant d'accéder à nos
                services.--></font></td>
            </tr>
  <center>
  <tr>
    <td rowspan="4">
      <p align="center"><img border="0" src="./images/cle.gif" width="80" height="80"></td>
    <td colspan="2">
      <p style="margin-left: 5"></td>
  </tr>
  <tr>
    <td>
      <p style="margin-left: 5"><font face="Arial" size="3"><b>Login:</b></font></td>
    <td>
      <p style="margin-left: 0"><font face="Arial" size="3"><input type="text" name="login" size="20"></font></td>
  </tr>
  <tr>
    <td>
      <p style="margin-left: 5"><font face="Arial" size="3"><b>Mot de passe:</b></font></td>
    <td>
      <p style="margin-left: 0"><font face="Arial" size="3"><input type="password" name="pass" size="20"></font></td>
  </tr>
  <tr>
    <td>
      <p style="margin-left: 5"></td>
    <td>
      <p style="margin-left: 0">
      <a href="javascript:valider();"><img border="0" src="./images/valider.gif" width="150" height="20"></a></td>


  </tr><%}%>
            </table>
          </center>
          </div>
        </center>
        </td>
      <td width="150" valign="bottom">
        <img border="0" src="./images/passe.gif" width="150" height="186">
        </td>
    </tr>
  </table>

    </center>
  </div>
  </center>
  </form>
  <center>
  <table border="0" cellpadding="0" cellspacing="0" width="100%" height="123" align="center">
        <tr>
          <td width="25%" align="center">            
            <img border="0" src="./imagesapp/<% out.print(ville+".gif"); %>"  width="83" height="93">
            <font face="Arial" size="2">
             <br>
			 <a href="<% out.print(siteWebVille); %>" target="newWindow">
            Mairie de <% out.print(ville); %></a>  
			
            </font>
          </td>
		  
		  
		  
		  
		  
		  
		  
		  
		  
          <td width="50%" align="center">
            &nbsp;&nbsp;&nbsp;
          </td>
          <td width="25%" align="center">
           
            <a href="http://www.analogon.fr" target="newWindow">
              <img border="0" src="./images/analogonlogo.gif" >
            </a>
            <font face="Arial" size="2">
            <br>
            Pour visitez notre site web, 
            <a href="http://www.analogon.fr" target="newWindow">
				  cliquez ici.
				</a>
				
<% if (typeApplication.contains("recensement")) { %>
			<br>Recensement - Version  <%=fr.analogon.r2t.main.ConfVariable.codeDeVersionRecenement%> 
<% }else{ %>           
		    <br>R2T - Version  <%=fr.analogon.r2t.main.ConfVariable.codeDeVersionR2T%> 	
<% } %>  
			
			<br>© Copyright Analogon Sarl
            </font>
          </td>
        <tr>
  </table>
  </center>


  </body>









