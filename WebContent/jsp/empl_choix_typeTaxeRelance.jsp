<html>
<head>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/Calendar.js"></script>
<jsp:useBean id="baffPrefacturation" scope="page" class="fr.analogon.r2t.view.batch.BaffRolePrefacturation" />

<title>Relance</title>
  <script>
  
    function lancerRelance()
    { 
	contenu="<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"98%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" height=\"137\">";
	  contenu=contenu +"<tr><td align=\"center\" height=\"135\">";
	  contenu=contenu +"   <p align=\"center\"><font face=\"Arial\"><br>";
	  contenu=contenu +"   <img border=\"0\" src=\"./images/chargement.gif\" width=\"500\" height=\"283\" \"></font> </p>";
	  contenu=contenu +"   <p align=\"center\">&nbsp;</td>";
	  contenu=contenu +"   </tr> </table>";
       document.getElementById("chargement").innerHTML = contenu;
       document.forms[0].submit();
       document.getElementById("chargement").innerHTML = contenu;

    }
  </script>

</head>
<body background="./images/nuages.jpg" topmargin="0">

<form method="POST" action="entree?action=empl_prefacturation.jsp">
  <div align="center">
    <table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" width="100%" bordercolor="#00CA00" height="143">
      <tr>
          <td width="100%" bgcolor="#AFF3BB" colspan="2" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/batch.png" width="40" height="40"></b></font></p>
          </td>
      </tr>
      <tr>
          <td width="100%" bgcolor="#AFF3BB" colspan="2" height="22">
            <p>
              <b><font size="4">RELANCE :</font></b><font size="3" face="Arial"><b>&nbsp
              </b></font>
          </td>
      </tr>
      

      <tr >
        <td width="23%"  align="left" background="images/fond_bleu.gif" height="24">
          <p align="left"><font face="Arial" size="3">&nbsp;</font><font face="Arial" size="2" color="#000000"><b><span style="font-size: 10.0pt; font-family: Arial; mso-fareast-font-family: Times New Roman; color: black; mso-ansi-language: FR; mso-fareast-language: FR; mso-bidi-language: AR-SA">Type
          de taxe :</span></b></font><font face="Arial" size="3">&nbsp;&nbsp;</font>
          </p>
        </td>
        <td width="177%" size="3" Arial" height="24"></font>
			<input type="text" value="type de taxe">
        </td> 
      </tr>
      

      <tr >
        <td width="23%"  align="left" background="images/fond_bleu.gif" height="24">
          &nbsp;
        </td>
        <td width="177%" size="3" Arial" height="24">
          <p align="center">   
	                <font size="3" face="Arial"><a href="javascript:preFacturation();"><img src="images/lettre_r.gif" align="absmiddle" border="0" width="20" height="20">elance</a></font>
        </td> 
      </tr>
      
    </table>
  </div>
   <div id="chargement" style="width: 1145; height: 116" > 
  </div>
  </form>
  
<BR>



</body>
</html>














