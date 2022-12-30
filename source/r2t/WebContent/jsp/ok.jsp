<html>



<head>

<%@ page language = "java"%>

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<meta name="GENERATOR" content="Microsoft FrontPage 4.0">

<meta name="ProgId" content="FrontPage.Editor.Document">

<title>R2T - ANALOGON</title>

</head>



<body background="./images/nuages.jpg" topmargin="0">



<div align="center">
  &nbsp;

</div>



<div align="center">
  <img border="0" src="./images/valider4sx.png" width="55" height="55">

</div>



<div align="center">
  <b><font size="3">Operation terminée avec succès .</font></b>

</div>



<div align="center">
  &nbsp;

</div>



<div align="center">
  &nbsp;

</div>



<div align="center">
  <a href="./entree?action=<%= request.getParameter("toRedirect") %>" >
  
  <font color="#0000FF">
  <img border="0" src="./images/lettre_r.gif" align="absmiddle" width="20" height="20"><b><font face="Arial" size="3" color="#000000">eprendre</font></b></font></a>

</div>



<div align="center">
  &nbsp;

</div>
<form>
<input type="hidden" name="toRedirect" value= <%= request.getParameter("toRedirect") %> >
<script language="JavaScript">
//setTimeout("window.location='./entree?action='+document.forms[0].toRedirect.value",5000); // delai en millisecondes
</script>

</form>







</body>



</html>

