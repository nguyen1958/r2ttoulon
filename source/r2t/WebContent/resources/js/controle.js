 /************************
 Contenu du fichier selection.js
 *************************/
 var select = 0;
 var temp;

 // colore la ligne en transparent
 function transp(ligne)
 {
 if (ligne.style.background!='transparent') ligne.style.background='transparent';
 }

 // colore la ligne en lavande
 function lavend(ligne)
 {
 if (ligne.style.background!='transparent') ligne.style.background='yellow';
 }

 // colore la ligne en transparent si elle est rouge
 // remet en transparent la ligne selectionnée precedement et colore celle si en rouge si differente
 // indique qu'une ligne est selectionnée en mettant le parametre select a 1
 function selec(ligne)
 {
 if (!select)
 { select = 1;
 ligne.style.background='yellow';
 temp = ligne;
 }
 else
 {
 if (ligne.style.background=='transparent')
 { select = 0;
 ligne.style.background='transparent';
 }
 else
 { temp.style.background='transparent';
 ligne.style.background='transparent';
 temp=ligne;
 }
 }

 }
 
 
 
 
 
function verif_date(input)
{
var regex = new RegExp("[/-]");
var date = input.split(regex);
var nbJours = new Array('',31,28,31,30,31,30,31,31,30,31,30,31);
var result = true;

if ( date['2']%4 == 0 && date['2']%100 > 0 || date['2']%400 == 0 )
nbJours['2'] = 29;

if( isNaN(date['2']) )
result=false;

if ( isNaN(date['1']) || date['1'] > 12 || date['1'] < 1 )
result=false;
if(input.length != 10)
result=false;

if ( isNaN(date['0']) || date['0'] > nbJours[Math.round(date['1'])] || date['0'] < 1 )
result=false;

return result;
}




function verifier(attribut,valeur) 
{
 var Resultat=-1;
 if(valeur.length==0)
 {     
   alert("Le champ "+ attribut+" est obligatoire ! "  );	  
 }
 else
 {
   if (valeur.lastIndexOf(",")!=-1)
   {
      alert("Le caractère , est interdit. Vous pouvez utiliser . à la place.")
   }
   else
   {
    return 0;
   }
 }
 return Resultat;
}
function VerifMail(adresse)
{
	var res="false"; 
	var arobase = adresse.indexOf("@",1);                   //"@" doit etre au min a la 2° place
	var point = adresse.indexOf(".",arobase+2);             //"." doit etre separer d'au moin 1 caractere de "@"
        var longueur = adresse.length;
	if ((arobase > -1)&&(longueur >2)&&(point > 1)&&((longueur-point) >2))   //verification
		{
                res="true";
		}
	else
		{		
		res="false";
                }
        return res;
}


 function TestInt(id,nom)
 {
  var res=-1;
 //test de l'integrité de l'element
  if (id!="")
  {
   if (isNaN(id)==false)
    {
      res=0;
    }
	else
	{
	  alert("Le champ "+ nom + " doit etre un nombre");
	}
	
  }
  return res;
 }
 