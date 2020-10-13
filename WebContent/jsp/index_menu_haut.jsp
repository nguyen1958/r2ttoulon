<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
	 "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <title>R2T - ANALOGON</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <jsp:useBean id="bean" scope="page" class="fr.analogon.r2t.view.batch.BAffDiversTraitement" />
  <jsp:setProperty name="bean" property="request" value="<%=request%>"/>
  <jsp:useBean id="baffDivers" scope="page" class="fr.analogon.r2t.view.batch.BAffDivers"/>
  <style>
body
{
	margin		: 0px;
	padding 	: 0px;
}
#header
{
	margin		: 0px;
	height 		: 34px;
}
#analogon
{
	font-family	: Arial;
	font-weight	: bold;
	width		: 300px;
	float		: left;
}
#mairie
{
	font-family	: Arial;
	font-weight	: bold;
	float		: left;
	width		: 200px;
}
.imghidden
{
	display		: none;
}
#bar
{
width:100%;
height:15px;
background-image: url(../images/bord_haut.gif);
}
/*---------------------------------------------------------*/
#acceuil
{
	background-image: url(../images/accueil01.gif);
	width		: 60px;
	height		: 34px;

	float		: right;
}

#acceuil:hover
{
	background-image: url("../images/accueil02.gif");
}
/*---------------------------------------------------------*/


#autorisation
{
	background-image: url(../images/autorisation01.gif);
	width		: 90px;
	height		: 34px;

	float		: right;
}

#autorisation:hover
{
	background-image: url("../images/autorisation02.gif");
}
/*---------------------------------------------------------*/
#alerte
{
	background-image: url(../images/controle01.gif);
	width		: 60px;
	height		: 34px;

	float		: right;
}

#alerte:hover
{
	background-image: url("../images/controle02.gif");
}
/*---------------------------------------------------------*/
#identification
{
	background-image: url(../images/identification01.gif);
	width		: 97px;
	height		: 34px;

	float		: right;
}

#identification:hover
{
	background-image: url("../images/identification02.gif");
}
/*---------------------------------------------------------*/
#parametres
{
	background-image: url(../images/parametres01.gif);
	width		: 69px;
	height		: 34px;

	float		: right;
}

#parametres:hover
{
	background-image: url("../images/parametres02.gif");
}
/*---------------------------------------------------------*/
#role
{
	background-image: url(../images/role01.gif);
	width		: 67px;
	height		: 34px;

	float		: right;
}

#role:hover
{
	background-image: url("../images/role02.gif");
}
/*---------------------------------------------------------*/
#admin
{
	background-image: url(../images/admin01.gif);
	width		: 67px;
	height		: 34px;

	float		: right;
}

#admin:hover
{
	background-image: url("../images/admin02.gif");
}
/*---------------------------------------------------------*/
#batch
{
	background-image: url(../images/batch01.gif);
	width		: 67px;
	height		: 34px;

	float		: right;
}

#batch:hover
{
	background-image: url("../images/batch02.gif");
}



/*---------------------------------------------------------*/
#regie
{
	background-image: url(../images/regie01.gif);
	width		: 67px;
	height		: 34px;

	float		: right;
}

#regie:hover
{
	background-image: url("../images/regie02.gif");
}

/*---------------------------------------------------------*/
#marche
{
	background-image: url(../images/marche01.gif);
	width		: 67px;
	height		: 34px;

	float		: right;
}

#marche:hover
{
	background-image: url("../images/marche02.gif");
}

  </style>
</head>
<body>
<%
String typeUtilisateur = (String)session.getAttribute("typeUtilisateur"); 
String typeApplication = (String)session.getAttribute("typeApplication");
String ville = baffDivers.getVille(); 
String regie = (String)session.getAttribute("regie");
String marche = (String)session.getAttribute("marche");
%>

  <div id="header">
    <div id="analogon" style="width: 289; height: 19">
        &nbsp;R2T Analogon -
        Mairie de <% out.print(ville); %>
    </div>
    <div id="mairie" style="width: 359; height: 20">
        &nbsp;&nbsp; <font color="#FF0000">Aujourd'hui :  <jsp:getProperty name="bean" property="dateAjourdhui"/> </font>
    </div>
<% 
if (  typeApplication !=null && typeApplication.equalsIgnoreCase("r2t")) 
{ 
%>	
	<a href="../empl/index_batch.htm" target="centre"><div id="batch"></div></a>
<% } %>
 

<% 
if (  typeUtilisateur !=null && typeUtilisateur.equalsIgnoreCase("admin")) 
{ 
%>
      <a href="../empl/index_admin.htm" target="centre"><div id="admin"></div></a>
<% } %>
      <a href="../empl/index_role.htm" target="centre" ><div id="role"></div></a>


<% 
if (  regie !=null && regie.equalsIgnoreCase("true")) 
{ 
%>	  
	  <a href="../empl/index_regie.htm" target="centre" ><div id="regie"></div></a>
<% } %>	  
	  
<% 

if (  marche !=null && marche.equalsIgnoreCase("true")) 
{ 
%>	  
	  	  
<% } %>	  

	  
<% 
if (  typeApplication !=null && typeApplication.equalsIgnoreCase("r2t")) 
{ 
%>	  
      <a href="../empl/index_parametres.htm" target="centre"><div id="parametres"></div></a>      
      <a href="../empl/index_alertes.htm" target="centre"   ><div id="alerte"></div></a>
      <!-- 
      <a href="../empl/index_autorisation.htm" target="centre"   ><div id="autorisation"></div></a> 
      -->
<% } %>
      <a href="./index_centre.jsp" target="centre"><div id="acceuil"></div></a>
	  <a href="../entree?action=login.jsp" target="_top"  ><div id="identification"></div></a>
	  
	  
  </div>
  <div id="bar">
  </div>
<img src="../images/batch02.gif" class="imghidden"/>
<img src="../images/admin02.gif" class="imghidden"/>
<img src="../images/regie02.gif" class="imghidden"/>
<img src="../images/marche02.gif" class="imghidden"/>
<img src="../images/role02.gif" class="imghidden"/>
<img src="../images/parametres02.gif" class="imghidden"/>
<img src="../images/identification02.gif" class="imghidden"/>
<img src="../images/controle02.gif" class="imghidden"/>
<img src="../images/acceuil02.gif" class="imghidden"/>
</body>
</html>
