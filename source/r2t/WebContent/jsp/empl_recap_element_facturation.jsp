<html>
<head>
  <jsp:useBean id="baffElementFacturation" scope="page" class="fr.analogon.r2t.view.regie.BaffElementFacturation" />
  <jsp:setProperty name="baffElementFacturation" property="request" value="<%=request%>"/>
</head>
<body background="./images/nuages.jpg" topmargin="0" vlink="#0000FF" alink="#0000FF">

<form method="POST" action="">
  <DIV align=center>

  <jsp:getProperty name="baffElementFacturation" property="entete"/>
  <hr>
  <jsp:getProperty name="baffElementFacturation" property="listeElementFacturation"/>
  </DIV>
 </form>

</body>

</html>
