<html>
<head>
 <link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen"><SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
 <script type="text/javascript" src="./resources/js/Calendar.js"></script> 
<%@ page language = "java" %>
<jsp:useBean id="baffBareme1" scope="request" class="fr.analogon.r2t.parametres.bareme.BAffBareme"/>
<jsp:setProperty name="baffBareme1" property="request" value="<%=request%>"/>


  <script>   
    
    function ok()
    {     
      document.forms[0].submit();
    }
    
    
  </script>
</head>
<body background="./images/nuages.jpg" >
<form method="POST" action="entree?action=liste_bareme.jsp">

<input type="hidden" name="choix" size="20" value="chercher" >

  <div align="center">
    <div align="center">
        <center>
      <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="119">
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="2464" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/bareme.png" width="40" height="40"></b></font>
          </td>
        </tr>
        <tr>
          <td colspan="3" bgcolor="#AFF3BB" width="2464" height="23">
            <b><font size="4">RECHERCHE D'UN BAREME :</font></b>
          </td>
        </tr>
   
        <tr>
          <td colspan="3" bgcolor="#DFFFEF" width="2464" height="16">
            <p style="margin-left: 5"><font size="2" face="Arial"><img border="0" src="./images/fleche.gif" align="absmiddle" width="8" height="8">
            Recherche
          multi-critères  :<b> </b>veuillez remplir
          un ou plusieurs champs ci-dessous pour obtenir les informations
            détaillées.</font></td>
        </tr>

        <tr>
      <td width="128" height="23">
        <b><font face="Arial" size="2">&nbsp;Code barème :</font></b>
      </td>
      <td width="94" height="23">
            <input type="text" name="codeBareme" size="20">
      </td>
          <td width="333" height="23">
            &nbsp;
          </td>
        </tr>
        
        <tr>
      <td width="128" height="23">
        &nbsp;<b><font face="Arial" size="2">Année exercice</font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">
        :&nbsp;</font></b>
      </td>
      <td width="94" height="23">
            <input type="text" name="anneeExercice" size="20">
      </td>
          <td width="333" height="23">
            &nbsp;
          </td>
        </tr>
        
        
 <tr>
      <td height="12" width="128">
        <b>
        <font face="Arial" size="2">&nbsp;Type de taxe </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2"><i>
        </i></font></b><font face="Arial" size="2"><b>:&nbsp;</b></font>
      </td>
      <td height="12" width="94">
<jsp:getProperty name="baffBareme1"  property="listeTypeDeTaxeSansCode" />
      </td>
      <td width="886" height="12">
         &nbsp;         

       </td>
 </tr>

        
        
        
       <tr>
      <td width="128" height="23">
        &nbsp;<b><font face="Arial" size="2">Libellé </font><font face="Arial" size="2" color="#FF0000"><i>*</i></font><font face="Arial" size="2">:&nbsp;</font></b>
      </td>
      <td width="94" height="23">
            <input type="text" name="libelle" size="20">
      </td>
          <td width="333" height="19">
            &nbsp;
          </td>
        </tr>
        
        
        
       <tr>
      <td width="234" height="19">
        &nbsp;
      </td>
      <td width="200" height="19">
        <b><font face="Arial" size="2">&nbsp;&nbsp; </font></b>
      </td>
          <td width="333" height="19">
            <input type="image" href="javascript:ok();" target="bas" border="0" src="./images/ok.gif" align="absmiddle" width="20" height="20" name="I4">
          </td>
        </tr>
        
        
        
        </table>
      </center>
    </div>
  </div>
</form>

</body>

</html>
