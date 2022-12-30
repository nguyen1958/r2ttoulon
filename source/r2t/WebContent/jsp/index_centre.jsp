<html>
<head>
<%@ page language = "java"%>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>R2T - ANALOGON</title>
</head>
<jsp:useBean id="baffDivers" scope="page" class="fr.analogon.r2t.view.batch.BAffDivers"/>

<%
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur"); 
String typeApplication = (String)session.getAttribute("typeApplication");
String ville = baffDivers.getVille(); 
String siteWebVille = baffDivers.getSiteWebVille(); 

%>

<body background="../images/nuages.jpg" topmargin="0">
<div align="center">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="../images/fond_trait.gif" width="15%" >
        <p align="center"><img border="0" src="../images/mains.gif" width="40" height="40"></p>
      </td>
     <td background="../images/fond_trait.gif" width="17%">
      </td>
      <td background="../images/fond_trait.gif" valign="top" width="68%">
       <p align="right"><font face="Arial" size="5">Recouvrements des taxes du domaine public Foires et Marchés</font>
      </td>
  </tr>
  </table>
</div>



<div align="center" style="width: 1152; height: 419">
  <br>
  <br>


  <center>

  <br>

  <div align="center">

    <center>

  <table border="0" cellpadding="0" cellspacing="0" width="471" background="../images/bienvenue.gif" height="275">

    <tr>

      <td width="471" height="275" align="center">

        <p style="margin-left: 20; margin-right: 20">&nbsp;</p>

        <div align="center">

          <table border="1" cellpadding="0" cellspacing="0" width="90%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="165">

            <tr>



              <td height="163" >

                <p style="margin-left: 10"><font size="3" face="Arial">
                <b> 
                Bienvenue dans l'espace de la Mairie de  <% out.print(ville); %></b>
                  </font>

                <p style="margin-left: 5"><font size="3" face="Arial">
                <b> Le service des recouvrements et taxes du domaine public vous permet de :<br>
                &nbsp;-Gérer par exercice comptable des redevables ou des sociétés possédant un ou plusieurs emplacements de différents
                types.<br>
                &nbsp;-Facturer des emplacements.</b>
                  </font></td>

            </tr>

          </table>

        </div>

      </td>

    </center>

  </tr>

  <center>

  </table>

    </center>

    </div>

  </center>

  <center>
  <table border="0" cellpadding="0" cellspacing="0" width="100%" height="156" align="center">
        <tr>
      	  
		  
		  
		  <td width="19%" align="center" height="156">          
            <img border="0" src="../imagesapp/<% out.print(ville+".gif"); %>" width="111" height="119">
            <font face="Arial" size="2">
             <br>
			 <a href="<% out.print(siteWebVille); %>" target="newWindow">
            Mairie de <% out.print(ville); %></a>  
			
            </font>
          </td>
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
          <td width="63%" align="center" height="156">
            &nbsp;&nbsp;&nbsp;
          </td>
          <td width="18%" align="center" height="156">
           <a href="http://www.analogon.com.fr" target="newWindow"><img border="0" src="../images/analogonlogo.gif" width="134" height="61" ></a>
           <font face="Arial" size="2">
            <br><a target="newWindow" href="http://www.analogon.fr/">
            Pour visitez notre site web, cliquez ici.</a>
            
<% if (  typeApplication !=null && typeApplication.equalsIgnoreCase("r2t")) { %>	
			<br>R2T - Version  <%=fr.analogon.r2t.main.ConfVariable.codeDeVersionR2T%> 
<% } %>  
<% if (  typeApplication !=null && typeApplication.equalsIgnoreCase("recensement")) { %>	
			<br>Recensement - Version  <%=fr.analogon.r2t.main.ConfVariable.codeDeVersionRecenement%> 
<% } %>  


          
            <br>© Copyright Analogon Sarl
            </font>
          </td>
  </table>
  </center>

</div>



</body>



</html>

