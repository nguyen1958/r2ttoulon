<html>

<head>
<title>Batch / Divers</title>
</head>
<body background="../images/fond_bleu.gif" topmargin="0">
<div align="center">
  <table border="0" cellpadding="0" cellspacing="0" width="98%">
    <tr>
      <td background="../images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="../images/batch_graph.gif" width="60" height="40"></p>
      </td>
    <td background="../images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">REFACTURATION</font></td>
  </tr>
  </table>
</div>

<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    if (listeLibelleDesTypesDeTaxeAutorise.length() != 0 )  {             
%>
    <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="31">
      <tr>
        <td width="19%" height="29">
          &nbsp;
        </td>
        <td width="31%" bgcolor="#DFFFEF" height="29">
          <p style="margin-left: 5" align="center">
          <font face="Arial" size="2"><b>Recherche : </b></font>
          <a target="bas" href="../entree?action=empl_recherche_refacturation.jsp">
          <img border="0" src="../images/ok.gif" align="absmiddle" width="25" height="25"></a>&nbsp;
        </td>
        <td bgcolor="#DFFFEF" width="29%" height="29">
          <p style="margin-left: 5" align="center">
          <font face="Arial" size="2"><b>Refacturer : </b></font>
          <a target="bas" href="../entree?action=refacturation.jsp">
          <img border="0" src="../images/ok.gif" align="absmiddle"></a>
        </td>
        <td width="21%" height="29">
          &nbsp;
        </td>
      </tr>
    </table>
<% } else {%>

<script language="javascript" type="text/javascript">
   window.open("../entree?action=empl_recherche_refacturation.jsp","bas");

</script>
<% } %>






</body>
</html>
