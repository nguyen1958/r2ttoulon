<html>
<head>
<link rel="shortcut icon" href="calendar.ico" type="image/x-icon" />   
<link type="text/css" rel="stylesheet" href="./resources/css/dhtmlgoodies_calendar.css?random=18042008" media="screen">
<SCRIPT type="text/javascript" src="./resources/js/dhtmlgoodies_calendar.js?random=18042008"></script>
<script type="text/javascript" src="./resources/js/controle.js"></script>
</head>


<body background="./images/nuages.jpg" topmargin="0" vlink="#0000FF" alink="#0000FF">

<%@ page language = "java" %>
<jsp:useBean id="bAffLEF" scope="page" class="fr.analogon.r2t.view.regie.BAffListeElementFacturation" />
<jsp:setProperty name="bAffLEF" property="request" value="<%=request%>"/>

<%
String ville = (String)session.getAttribute("ville"); 
%>

<script>

function remplirChoixDimension()
{
     contenuDiv="<font face=\"Arial\" size=\"2\"><input type=\"radio\"  value=\"lonlarg\" checked name=\"longSuraface\" onClick=choixDimension1()>";
	 contenuDiv=contenuDiv+"Long&amp;larg&nbsp;&nbsp;&nbsp;&nbsp; Surface";
	 contenuDiv=contenuDiv+"<input type=\"radio\" value=\"surf\" name=\"longSuraface\" onclick=\"choixDimension2()\" ></font>";
     document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
}

function majDimension()
{  	
	var valBareme=document.forms[0].codeBareme.value;	
	if(valBareme.indexOf("FORFAIT")>0)
	{	  
	   contenuDiv="<b> - </b>";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
    document.forms[0].longueur.value='1'; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	if(valBareme.indexOf("ML/")>0)
	{
	 contenuDiv="<b> Longueur: </b><input type='text' name='longeur1' ONBLUR='changerLongueur(this.value)'   ONFOCUS='changerLongueur(this.value)'   >";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
	  document.forms[0].longueur.value=''; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv;
	}
	if(valBareme.indexOf("UNITE/")>0)
	{
	 contenuDiv="<b> - </b>";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
    document.forms[0].longueur.value='1'; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	
	if(valBareme.indexOf("FORFAIT/")>0)
	{
	 contenuDiv="<b> - </b>";  
	 document.forms[0].surface.value='0'; 
	 document.forms[0].largeur.value='1'; 
    document.forms[0].longueur.value='1'; 
	 document.getElementById("dimensionArticle").style.height=24;
	 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
 	 contenuDiv=" -"; 
	 document.getElementById("choixDimension").style.height=24;
	 document.getElementById("choixDimension").innerHTML = contenuDiv; 
	}
	
	if(valBareme.indexOf("M2/")>0)
	{
	    choixDimension1();
		remplirChoixDimension();	 
		

	}
	
}


function changerSurface(d)
{
  document.forms[0].surface.value=d;
}

function changerLargeur(d)
{
  document.forms[0].largeur.value=d;
}

function changerLongueur(d)
{
  document.forms[0].longueur.value=d;
}

function choixDimension1()
{
 contenuDiv= "<b>Longueur: </b><input type='text' name='longeur1' ONBLUR='changerLongueur(this.value)' onfocus='changerLongueur(this.value)'>";  
 contenuDiv= contenuDiv+"<b>     Largeur:</b> <input type='text' name='largeur1' ONBLUR='changerLargeur(this.value)'>"; 
 document.forms[0].surface.value='0'; 
 document.forms[0].largeur.value=''; 
 document.forms[0].longueur.value=''; 
 document.getElementById("dimensionArticle").style.height=24;
 document.getElementById("dimensionArticle").innerHTML = contenuDiv; 
}

function choixDimension2()
{
 contenuDiv= "<b>Surface: </b><input type='text' name='surface1' ONBLUR='changerSurface(this.value)' ONfocus='changerSurface(this.value)' > "; 
 document.forms[0].largeur.value='1'; 
 document.forms[0].longueur.value='1'; 
  document.forms[0].surface.value=''; 
 document.getElementById("dimensionArticle").style.height=24;
 document.getElementById("dimensionArticle").innerHTML = contenuDiv;  
}


function majListeBareme(){

    bIndic= document.forms[0].baremeIndicatif.value;
    nbB=document.forms[0].codeBareme.options.length;
    for(var i=0;i <nbB;i++){
      option=document.forms[0].codeBareme.options[i];
      if(option.value==bIndic){
        option.selected=true;
        break;
      }
    }

  }


    function validerArticle()
    {
      longueur=  document.forms[0].longueur.value;
      largeur=  document.forms[0].largeur.value;
      quantite=  document.forms[0].quantite.value;
	  surface=document.forms[0].surface.value;
	  
      res1=verifier("longueur",longueur);
      res2=verifier("largeur",largeur);      
      res3=verifier("quantite",quantite);
	  verfiSurface=verifier("Surface",surface);
	  
	  res1=TestInt(longueur,"longueur");
	  res2=TestInt(largeur,"largeur");
	  res3=TestInt(quantite,"quantite");     
	  verfiSurface=TestInt(surface,"Surface"); 
	  
	  
      verfiDate=verif_date(document.forms[0].dateDebutAutorisation.value);
      
	  var verifDateFin = 0;
	  
	  var necessiteControleTerrain = document.getElementById("necessiteControleTerrain").value;
	  if(necessiteControleTerrain.toLowerCase() == "non" )
	  {
		  if (document.forms[0].dateFinAutorisation.value.length != 0)
		  {
			 resverifDateFin=verif_date(document.forms[0].dateFinAutorisation.value);
			if(resverifDateFin) 
			{
			   verifDateFin=0; 
			}
			else
			{
			   verifDateFin =-1; alert("La date de fin d'autorisation doit etre sous la forme JJ/MM/AAAA"); 
			}
		  }
	  }
      verfiNombreFaceAffiche = 0;
      if (document.getElementById("nombreFaceAffiche") != null )
      {
        verfiNombreFaceAffiche=verifier("Nombre de face /affiche",document.forms[0].nombreFaceAffiche.value);
		verfiNombreFaceAffiche=TestInt(document.forms[0].nombreFaceAffiche.value,"Nombre de face /affiche");
	  }
      
      if(verfiDate) 
      { verfiDate=0; }
      else
      { verfiDate =-1; alert("La date de debut d autorisation doit etre sous la forme JJ/MM/AAAA"); }
      //alert(verfiDate);
     if (res1==0 && res2==0&& res3==0 && verfiDate==0 && verifDateFin==0 && verfiSurface==0 && verfiNombreFaceAffiche == 0 )
	  {
	     document.forms[0].choix.value="creer";
	     //alert("SUBMIT");
        document.forms[0].submit();
     }     
    }
	   
</script>
<input type="hidden" id="necessiteControleTerrain" name="necessiteControleTerrain" value="<jsp:getProperty name="bAffLEF" property="necessiteControleTerrain"/>">	
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td background="./images/fond_trait.gif" width="15%">
        <p align="center"><img border="0" src="./images/roles_graph.gif" width="60" height="40"></p>
      </td>
    <td background="./images/fond_trait.gif" valign="top">
      <p align="right"><font face="Arial" size="5">Liste des Ouvrages</font></td>
  </tr>
  </table>

 <DIV align=center><CENTER>
<TABLE cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="100%" borderColorLight=#c0c0c0 border=1 height="96">
  <tr>
          <td bgcolor="#AFF3BB" width="1122">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/listeouvrage.jpg" width="40" height="40"></b></font>
          </td>
  </tr>
  <tr>
        <td align="center" width="861" height="22" bgcolor="#AFF3BB">
          <p align="left"><b><font size="4">&nbsp;LISTE DES OUVRAGES :</font></b>
        </td>
  </tr>
  <tr>
  
  
  <TD width="1113" bgcolor="#FFFFFF">
  <P style="MARGIN-LEFT: 5px">
  <B><font face="Arial" color="#000000" size="2">Exercice :</font><FONT face=Arial color=#003399 size=3> </FONT>
    <FONT face=Arial color=#cc3300 size=3><jsp:getProperty name="bAffLEF" property="anExercice"/></FONT>
  </B>
  </P>
  </TD>
  </tr>
  
  <tr>
    <TD bgColor=#ffffff width="1113" height="20">
      <P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>Redevable : </FONT>
      <FONT face=Arial color=#cc3300 size=2>
      <%=bAffLEF.getHtmlLienVersRd()%>
      </FONT></B></P>
    </TD>
 
</tr>
  <tr>
    <TD bgColor=#ffffff width="1113" height="20">
      <P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>Type d'emplacement : </FONT>
      <FONT face=Arial color=#cc3300 size=2><jsp:getProperty name="bAffLEF" property="libelleImputation"/></FONT></B></P>
    </TD>
  </tr>
  <tr>
    <TD bgColor=#ffffff width="1113" height="20">
      <P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>Secteur
      : </FONT>
      <FONT face=Arial color=#cc3300 size=2><jsp:getProperty name="bAffLEF" property="codeSecteur"/></FONT></B></P>
    </TD>
   
  </tr>
  
   <tr>
    <TD bgColor=#ffffff width="1113" height="20">
      <P style="MARGIN-LEFT: 5px; MARGIN-RIGHT: 5px"><B><FONT face=Arial color=#000000 size=2>Emplacement : </FONT>
      <FONT face=Arial color=#cc3300 size=2>         
     
        <a href=<jsp:getProperty name="bAffLEF" property="lienEmplacement"/>>
           <jsp:getProperty name="bAffLEF" property="raisonSocialEmplacement"/> :
           <jsp:getProperty name="bAffLEF" property="adresseComplete"/>
         </a>        
      </FONT></B></P>
    </TD>
  </tr>

 </TABLE>
</CENTER>
</DIV>
<hr>
<div align="center">

 <jsp:getProperty name="bAffLEF" property="listeElementsFacturation"/>

  <p align="center">
            &nbsp;&nbsp; 


  </p>
  <hr>


  <form method="POST" action="gestionelementfacturation">
  <input type="hidden" name="numEmplacment" value="<jsp:getProperty name="bAffLEF" property="numEmplacement"/>">
  <input type="hidden" name="exercice" value="<jsp:getProperty name="bAffLEF" property="anExercice"/>">
  <input type="hidden" name="numRedevable" value="<jsp:getProperty name="bAffLEF" property="numRedevable"/>">
  <input type="hidden" name="choix" value="<jsp:getProperty name="bAffLEF" property="choix"/>">
  <input type="hidden" name="codeSecteur" value="<jsp:getProperty name="bAffLEF" property="codeSecteur"/>">
  
  <input type="hidden" name="libelleImputation" maxlength=30 size="20"  value="<jsp:getProperty name="bAffLEF" property="libelleImputation"/>">
  <input type="hidden" name="traitement" value="article">
  <input type="hidden" name="typefacturation" value="">
  <input type="hidden" name="dateProchainControle" >
  <input type="hidden" name="dateDernierControle" >

<%
    String listeLibelleDesTypesDeTaxeAutorise = (String)session.getAttribute("typeTaxeAutorise"); 
    Boolean peutAcceder = Boolean.valueOf(bAffLEF.verfierAcces(listeLibelleDesTypesDeTaxeAutorise));    
%>	


<%  if (peutAcceder) { %>

   <div align="center">
  <center>
    <p style="margin-left: 5">&nbsp;
    <table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" height="379">
   
    </center>
      
    <tr>
          <td colspan="2" bgcolor="#AFF3BB" width="1122" height="42">
            <p align="center"><font size="3" face="Arial" color="#000000"><b><img border="0" src="images/maj.jpg" width="40" height="40"></b></font>
          </td>
    </tr>
      <tr>
        <td align="center" width="861" height="22" colspan="2" bgcolor="#AFF3BB">
          <p align="left"><b><font size="4">AJOUTER UN OUVRAGE</font><font face="Arial" size="3" color="#003399"><span style="text-transform: uppercase"> 
          :</span></font></b>
        </td>
      </tr>  
    <tr>

        <td align="left" width="294" height="19" >
          <p style="margin-left: 5"><font color="#000000" size="2" face="Arial"><b>Barème</b></font>
          <b><font face="Arial" color="#FF0000" size="3">*</font></b><font color="#000000" size="2" face="Arial"><b>:</b></font>
        </td>

      <font face="Arial" size="2">

		 <td align="left" width="210" height="19" nowrap>
          <p style="margin-left: 5">
          <jsp:getProperty name="bAffLEF" property="listeCodeBareme"/><font face="Arial" size="2">
        </td>
        
      </font>
    </tr>
    
    
<%if (bAffLEF.getLibelleImputation().equalsIgnoreCase("TLPE")) {%>   
    <tr>
      <td width="247" height="22"><font face="Arial" size="2">&nbsp;</font><b>Type
        <i><font color="#FF0000" size="2">*</font></i></b><font size="2">:</font>
      </td>      
      <td width="866" height="22">
        <select name="typeOuvrage" size="0">
			 <option selected value="Normal">Normal</option>
			 <option value="Numerique">Numerique</option>
	    </select>
      </td>      
    </tr>
    <tr>
      <td width="247" height="22"><font face="Arial" size="2">&nbsp;</font><b>Nombre
        d'affiche/face <i><font color="#FF0000" size="2">*</font></i></b><font size="2">:</font>
      </td>      
      <td width="866" height="22">
			<input type="text" name="nombreFaceAffiche" id="nombreFaceAffiche" size="15" >	
      </td>      
    </tr>
<%}%> 
    
    
    
    
    
    
    
    
    <tr>
	  <td align="left" width="294" height="1" nowrap>
          <b>
          <font color="#000000" size="2" face="Arial">&nbsp;Date de mise en application&nbsp;(</font></b>
          <font color="#FF0000" size="2" face="Arial">JJ/MM/AAAA</font><b>
          <font color="#000000" size="2" face="Arial">)</font>
          <font face="Arial" color="#FF0000" size="3">*</font><font color="#000000" size="2" face="Arial">:</font>

          </b>

       <td width="210" align="left" height="1" nowrap>
      <input  type="text" name="dateDebutAutorisation" id="dateDebutAutorisation" size="15" maxlength="10" value="" readonly>
      
		<img onclick="displayCalendar(document.forms[0].dateDebutAutorisation,'dd/mm/yyyy',this)"
		src="./images/calendar.gif" border="0" width="20" height="18" />
   
    </tr>
	
	<!-- Si bordeuax et l'ouvrage necessite un controle terrain , on affiche pas ce champs,sinon on l'affiche-->
	<%  if ( ! ( ville.equalsIgnoreCase("bordeaux") &&  bAffLEF.getNecessiteControleTerrain().equalsIgnoreCase("oui") ) ) {%> 
	
    <tr>
        <td align="left" width="294" height="2" nowrap>
          <b>
          <font color="#000000" size="2" face="Arial">&nbsp;Date de fin de validité&nbsp;(</font></b>
          <font color="#FF0000" size="2" face="Arial">JJ/MM/AAAA</font><b>
          <font color="#000000" size="2" face="Arial">)&nbsp;:</font>

          </b>

        </td>
	   <td align="left" width="210" height="2" nowrap>
          <input  type="text" name="dateFinAutorisation" id="dateFinAutorisation" size="15" maxlength="10" value="" readonly>
      
		<img onclick="displayCalendar(document.forms[0].dateFinAutorisation,'dd/mm/yyyy',this)"
		src="./images/calendar.gif" border="0" width="20" height="18" />
          
          
        </td>
    </tr>
	<%}%>
    <tr>
        <td align="left" width="294" height="23" nowrap>
          <p><font color="#000000" size="2" face="Arial"><b>&nbsp;Nom Ouvrage:</b></font></p>
        </td>
	   <td align="left" width="210" height="23" nowrap>
          <font face="Arial" size="2"><input type="text" name="nomarticle" size="40" value="" maxlength="30" ></font>
          
          
        </td>
    </tr>
    <tr>
        <td align="left" width="294" height="24" nowrap>
          <font color="#000000" size="2" face="Arial"><b>&nbsp;Choix</b></font><b><font color="#000000" size="2" face="Arial">
          </font><font face="Arial" color="#FF0000" size="3">*</font></b><font color="#000000" size="2" face="Arial"><b>:&nbsp;</b></font>
        </td>
	   <td align="left" width="210" height="24" nowrap>
         <div id="choixDimension" style="width: 791; height: 24">   </div>
          
          
        </td>
    </tr>
    <tr>
        <td align="left" width="294" height="21" nowrap>
          <b><font color="#000000" size="2" face="Arial">
          &nbsp;Dimension
          </font><font face="Arial" color="#FF0000" size="3">*</font></b><font color="#000000" size="2" face="Arial"><b> :</b></font>
        </td>
	   <td align="left" width="210" height="21" nowrap>
          <font face="Arial" size="2">
            <div id="dimensionArticle" style="width: 791; height: 26">   </div>

          </font>
          
          
        </td>
    </tr>
    
    <input type="hidden" name="longueur" size="15" value="">
    <input type="hidden" name="largeur" size="15" value="">
    <input type="hidden" name="surface" size="15" value="">
    

    <tr>
        <td align="left" width="294" height="23" nowrap>
          <p><font color="#000000" size="2" face="Arial"><b>&nbsp;Quantité </b></font><b><font face="Arial" color="#FF0000" size="3">*</font></b><font color="#000000" size="2" face="Arial"><b>:</b></font></p>
        </td>
	   <td align="left" width="210" height="23" nowrap>
          <font face="Arial" size="2"><input type="text" name="quantite" size="15" value="" maxlength="30" ></font>
          
          
        </td>
    </tr>
    <tr>
        <td align="left" width="294" height="137" nowrap>
          <p><font color="#000000" size="2" face="Arial"><b>&nbsp;Commentaire</b></font><font color="#000000" size="2" face="Arial"><b>&nbsp;:</b></font></p>
        </td>
	   <td align="left" width="210" height="137" nowrap>
          <font face="Arial" size="2"><b>                
          <textarea rows="6" name="commentaireOuvrage" cols="53"></textarea></b></font><b>&nbsp;</b>
          
  
          
          
        </td>
    </tr>
    <tr>
        <td align="center" width="504" height="1" colspan="2">
          <table border="0" width="100%" cellspacing="0" cellpadding="0">
            <tr>
              <td width="100%">
                <p align="center">
                <a href="javascript:validerArticle();" >
                  <img border="0" src="images/valider.gif" width="150" height="20" >
                </a>
                </td>
            </tr>
          </table>
        </td>
    </tr>
    <tr>
        <td align="center" width="504" height="1" colspan="2">
          <p align="left"><b><font face="Arial" color="#FF0000" size="1">*
          Champs obligatoires</font></b>
        </td>
    </tr>
   </table>

  <br>
  <font face="Arial" size="3" color="#0000FF"><b><a href="entree?action=empl_resultat_recherche_redevable.jsp">Liste
  des redevables</a></b></font>
  <p><b> 
 
    </center>

<% } %>


</form>
<script>
majDimension();
</script>

</body>


  

