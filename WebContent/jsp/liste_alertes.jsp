<html>
<head>
<script type="text/javascript" src="./resources/js/controle.js"></script>
<jsp:useBean id="beanAlertes" scope="session" class="fr.analogon.r2t.view.alertes.BAffListeAlertes" />
<jsp:setProperty name="beanAlertes" property="request" value="<%=request%>"/>
<title>journaux</title>

</head>
<body background="./images/nuages.jpg" topmargin="0">
  <script>
  
  function actualiser()
  {
    //alert("test refresh");
    window.location.reload( false );
  }


    function executerArrete(){
      document.forms[0].choix.value="executearretejournlalier";
      document.forms[0].afficheJournalier.value="false";
      document.forms[0].submit();
    }

    function genererJourneauxJour(){
      document.forms[0].choix.value="genererJourneauxJour";
      document.forms[0].submit();
    }

    function genererJourneauxQuinzaine(){
      document.forms[0].choix.value="genererJourneauxQuinzaine";
      document.forms[0].submit();
    }

    function genererJourneauxMois(){
      document.forms[0].choix.value="genererJourneauxMois";
      document.forms[0].submit();
    }

    function genererJourneauxTrimestre(){
      document.forms[0].choix.value="genererJourneauxTrimestre";
      document.forms[0].submit();
    }

    function genererJourneauxAn(){
      document.forms[0].choix.value="genererJourneauxAn";
      document.forms[0].submit();
    }
  </script>

<form method="POST" action="listeAlertes.jsp">
 
  <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="60">

 
  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="42">        
		<a href="javascript:actualiser();">
          <img border="0" src="images/exclamation.jpg" width="40" height="40">
		</a>
        </td>
  </tr>

  <tr>
        <td width="1139" bgcolor="#AFF3BB" align="center" height="22">
          <p align="left"><b><font size="4">LISTE DES ALERTES :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;<img border="0" src="images/rouge.jpg" width="15" height="15">CLOTURES&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img border="0" src="images/vert.jpg" width="15" height="15">EN COURS</font></b>
        </td>
  </tr>

  <tr>
        <td width="1139" align="center" height="1">
          <p align="center">
          <jsp:getProperty name="beanAlertes" property="listealertes"/>
                  </td>
  </tr>

     
  </table>
</body>





