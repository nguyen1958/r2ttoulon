/*
 * Generated by JasperReports - 16/01/09 20:00
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import net.sf.jasperreports.engine.*;
import java.util.*;
import net.sf.jasperreports.engine.data.*;


/**
 *
 */
public class CourrierReclamationTAV1_1232132407953_301226 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_par_CheminModele = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_SUBREPORT_DIR = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_par_numeroFacture = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillField field_imputation_faxGestionnaire = null;
    private JRFillField field_reclamation_reponseReclamation = null;
    private JRFillField field_facture_numeroFacture = null;
    private JRFillField field_reclamation_dateReclamation = null;
    private JRFillField field_imputation_designation = null;
    private JRFillField field_facture_typeTaxe = null;
    private JRFillField field_imputation_nomElu = null;
    private JRFillField field_imputation_telGestionnaire = null;
    private JRFillField field_redevable_ville = null;
    private JRFillField field_redevable_adresse2 = null;
    private JRFillField field_redevable_adresse3 = null;
    private JRFillField field_redevable_adresse1 = null;
    private JRFillField field_redevable_prenom = null;
    private JRFillField field_redevable_civilite = null;
    private JRFillField field_imputation_eluRenseignement3 = null;
    private JRFillField field_redevable_nomRedevable = null;
    private JRFillField field_imputation_eluRenseignement1 = null;
    private JRFillField field_imputation_eluRenseignement2 = null;
    private JRFillField field_imputation_minimumPerception = null;
    private JRFillField field_facture_anneeEx = null;
    private JRFillField field_redevable_rdCedex = null;
    private JRFillField field_imputation_nomGestionnaire = null;
    private JRFillField field_redevable_numRedevable = null;
    private JRFillField field_reclamation_dateReponse = null;
    private JRFillField field_redevable_codePostal = null;
    private JRFillField field_redevable_numRue = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_DateDuJour = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_par_CheminModele = (JRFillParameter)pm.get("par_CheminModele");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_SUBREPORT_DIR = (JRFillParameter)pm.get("SUBREPORT_DIR");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_par_numeroFacture = (JRFillParameter)pm.get("par_numeroFacture");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_imputation_faxGestionnaire = (JRFillField)fm.get("imputation_faxGestionnaire");
        field_reclamation_reponseReclamation = (JRFillField)fm.get("reclamation_reponseReclamation");
        field_facture_numeroFacture = (JRFillField)fm.get("facture_numeroFacture");
        field_reclamation_dateReclamation = (JRFillField)fm.get("reclamation_dateReclamation");
        field_imputation_designation = (JRFillField)fm.get("imputation_designation");
        field_facture_typeTaxe = (JRFillField)fm.get("facture_typeTaxe");
        field_imputation_nomElu = (JRFillField)fm.get("imputation_nomElu");
        field_imputation_telGestionnaire = (JRFillField)fm.get("imputation_telGestionnaire");
        field_redevable_ville = (JRFillField)fm.get("redevable_ville");
        field_redevable_adresse2 = (JRFillField)fm.get("redevable_adresse2");
        field_redevable_adresse3 = (JRFillField)fm.get("redevable_adresse3");
        field_redevable_adresse1 = (JRFillField)fm.get("redevable_adresse1");
        field_redevable_prenom = (JRFillField)fm.get("redevable_prenom");
        field_redevable_civilite = (JRFillField)fm.get("redevable_civilite");
        field_imputation_eluRenseignement3 = (JRFillField)fm.get("imputation_eluRenseignement3");
        field_redevable_nomRedevable = (JRFillField)fm.get("redevable_nomRedevable");
        field_imputation_eluRenseignement1 = (JRFillField)fm.get("imputation_eluRenseignement1");
        field_imputation_eluRenseignement2 = (JRFillField)fm.get("imputation_eluRenseignement2");
        field_imputation_minimumPerception = (JRFillField)fm.get("imputation_minimumPerception");
        field_facture_anneeEx = (JRFillField)fm.get("facture_anneeEx");
        field_redevable_rdCedex = (JRFillField)fm.get("redevable_rdCedex");
        field_imputation_nomGestionnaire = (JRFillField)fm.get("imputation_nomGestionnaire");
        field_redevable_numRedevable = (JRFillField)fm.get("redevable_numRedevable");
        field_reclamation_dateReponse = (JRFillField)fm.get("reclamation_dateReponse");
        field_redevable_codePostal = (JRFillField)fm.get("redevable_codePostal");
        field_redevable_numRue = (JRFillField)fm.get("redevable_numRue");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
        variable_DateDuJour = (JRFillVariable)vm.get("DateDuJour");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(".\\");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(2008000001));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getValue())+ " "+((java.lang.String)field_redevable_prenom.getValue())+"\n" +//$JR_EXPR_ID=13$
((java.lang.String)field_redevable_numRue.getValue())+ " " + ((java.lang.String)field_redevable_adresse1.getValue())+"\n" +//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse2.getValue()).equals(""))?((java.lang.String)field_redevable_adresse2.getValue())+"\n":"")+//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse3.getValue()).equals(""))?((java.lang.String)field_redevable_adresse3.getValue())+"\n":"")+//$JR_EXPR_ID=13$
(((java.lang.String)field_redevable_codePostal.getValue())+( " "+((java.lang.String)field_redevable_ville.getValue()))));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("T�l : "+((java.lang.String)field_imputation_telGestionnaire.getValue())+"\n"+//$JR_EXPR_ID=14$
"Fax : "+((java.lang.String)field_imputation_faxGestionnaire.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.util.Date)(((java.util.Date)variable_DateDuJour.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)("Facture "+((java.lang.String)field_facture_typeTaxe.getValue())+ " " +//$JR_EXPR_ID=16$
((java.lang.String)field_facture_anneeEx.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_designation.getValue())+ " " + ((java.lang.String)field_facture_anneeEx.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomGestionnaire.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getValue())+ " "+((java.lang.String)field_redevable_prenom.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("H�tel de Ville"+"\n"+//$JR_EXPR_ID=20$
"17 Place Pey Berland"+"\n"+//$JR_EXPR_ID=20$
"33077 Bordeaux C�dex");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+",");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(false));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomElu.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)((!(((java.lang.String)field_imputation_eluRenseignement1.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement1.getValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement2.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement3.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getValue())+"\n":""));//$JR_EXPR_ID=24$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(".\\");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(2008000001));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getOldValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getOldValue())+ " "+((java.lang.String)field_redevable_prenom.getOldValue())+"\n" +//$JR_EXPR_ID=13$
((java.lang.String)field_redevable_numRue.getOldValue())+ " " + ((java.lang.String)field_redevable_adresse1.getOldValue())+"\n" +//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse2.getOldValue()).equals(""))?((java.lang.String)field_redevable_adresse2.getOldValue())+"\n":"")+//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse3.getOldValue()).equals(""))?((java.lang.String)field_redevable_adresse3.getOldValue())+"\n":"")+//$JR_EXPR_ID=13$
(((java.lang.String)field_redevable_codePostal.getOldValue())+( " "+((java.lang.String)field_redevable_ville.getOldValue()))));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("T�l : "+((java.lang.String)field_imputation_telGestionnaire.getOldValue())+"\n"+//$JR_EXPR_ID=14$
"Fax : "+((java.lang.String)field_imputation_faxGestionnaire.getOldValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.util.Date)(((java.util.Date)variable_DateDuJour.getOldValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)("Facture "+((java.lang.String)field_facture_typeTaxe.getOldValue())+ " " +//$JR_EXPR_ID=16$
((java.lang.String)field_facture_anneeEx.getOldValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_designation.getOldValue())+ " " + ((java.lang.String)field_facture_anneeEx.getOldValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomGestionnaire.getOldValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getOldValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getOldValue())+ " "+((java.lang.String)field_redevable_prenom.getOldValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("H�tel de Ville"+"\n"+//$JR_EXPR_ID=20$
"17 Place Pey Berland"+"\n"+//$JR_EXPR_ID=20$
"33077 Bordeaux C�dex");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getOldValue())+",");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(false));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomElu.getOldValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)((!(((java.lang.String)field_imputation_eluRenseignement1.getOldValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement1.getOldValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement2.getOldValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getOldValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement3.getOldValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getOldValue())+"\n":""));//$JR_EXPR_ID=24$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(".\\");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(2008000001));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getValue())+ " "+((java.lang.String)field_redevable_prenom.getValue())+"\n" +//$JR_EXPR_ID=13$
((java.lang.String)field_redevable_numRue.getValue())+ " " + ((java.lang.String)field_redevable_adresse1.getValue())+"\n" +//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse2.getValue()).equals(""))?((java.lang.String)field_redevable_adresse2.getValue())+"\n":"")+//$JR_EXPR_ID=13$
(!(((java.lang.String)field_redevable_adresse3.getValue()).equals(""))?((java.lang.String)field_redevable_adresse3.getValue())+"\n":"")+//$JR_EXPR_ID=13$
(((java.lang.String)field_redevable_codePostal.getValue())+( " "+((java.lang.String)field_redevable_ville.getValue()))));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("T�l : "+((java.lang.String)field_imputation_telGestionnaire.getValue())+"\n"+//$JR_EXPR_ID=14$
"Fax : "+((java.lang.String)field_imputation_faxGestionnaire.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.util.Date)(((java.util.Date)variable_DateDuJour.getEstimatedValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)("Facture "+((java.lang.String)field_facture_typeTaxe.getValue())+ " " +//$JR_EXPR_ID=16$
((java.lang.String)field_facture_anneeEx.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_designation.getValue())+ " " + ((java.lang.String)field_facture_anneeEx.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomGestionnaire.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+ " "+((java.lang.String)field_redevable_nomRedevable.getValue())+ " "+((java.lang.String)field_redevable_prenom.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("H�tel de Ville"+"\n"+//$JR_EXPR_ID=20$
"17 Place Pey Berland"+"\n"+//$JR_EXPR_ID=20$
"33077 Bordeaux C�dex");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_redevable_civilite.getValue())+",");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(false));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_imputation_nomElu.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)((!(((java.lang.String)field_imputation_eluRenseignement1.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement1.getValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement2.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getValue())+"\n":"")+//$JR_EXPR_ID=24$
(!(((java.lang.String)field_imputation_eluRenseignement3.getValue()).equals(""))?((java.lang.String)field_imputation_eluRenseignement2.getValue())+"\n":""));//$JR_EXPR_ID=24$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
