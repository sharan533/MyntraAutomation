package com.myntra.driverscript;


import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.myntra.datatables.DataTable;
import com.myntra.testscripts.Initialize;
import com.myntra.utility.ApplicationIndependent;
import com.myntra.utility.ReportUtil;

public class Driverscript {

 public static WebDriver oBrowser;
 public static Properties config;
 public static Properties objectMap;
 public static String controllerFile;
 public static String testScriptExcelFile;
 public static String testDataColumn;
 public static String TestscriptStatus="";
 public static DataTable datatable;
 public static Logger log=LogManager.getLogger(Driverscript.class);
 
 
 @BeforeSuite
 public void startScript()
 {
  String sDateTime;
  try
  {
   String ResultReportFileName=System.getProperty("user.dir")+"/Results/ResultReports/SummaryResultReports.html";
   sDateTime=ApplicationIndependent.getDateTime("dd-MMM-yyyy h:m:s z");
   ReportUtil.createReport(ResultReportFileName, sDateTime, "QA Testing");
  }catch(Exception e)
  {
   log.error("there is an execption araised during the execution of the Method startScript , The Exception :"+e );
  }
 }
 
 
 @BeforeClass
 public void loadFiles()
 {
  try
  {
   log.info("The Execution of the Method loadFiles has started here...");
   
   String configFile=System.getProperty("user.dir")+"/Configuration/configuration.properties";
   config=ApplicationIndependent.property(configFile);
   
   String objectMapFile=System.getProperty("user.dir")+"/Objects/objectmap.properties";
   objectMap=ApplicationIndependent.property(objectMapFile);
   
   datatable=new DataTable();
     
   log.info("The Execution of the Method loadFiles has ended here...");
   
   
  }catch(Exception e)
  {
   log.error("There is an execption araised during the execution of the Method loadFiles , The Exception :"+e );
  }
 }
 
 /*@Test
 public void executeTestScripts()
 {
  try {
   log.info("The Execution of the Method executeTestScripts has started here...");
   oBrowser=Initialize.launch();
   Initialize.navigate(oBrowser);
   LoginLogout.login(oBrowser);
   LoginLogout.logout(oBrowser);
   Initialize.closeApplication(oBrowser);
   log.info("The Execution of the Method executeTestScripts has ended here...");
  } catch (Exception e) {
   log.error("there is an execption araised during the execution of the Method executeTestScripts , The Exception :"+e );
  }
 }*/
 
 @Test
 public void executeTestScripts()
 {
  String StartTime = null,endtime = null;
  try
  {
   log.info("The Execution of the Method executeTestScripts has started here...");
   ReportUtil.startSuite("Scenarios");
   controllerFile=System.getProperty("user.dir")+"/Controller/data_Controller.xlsx";
   int controllerRowCount=datatable.rowCount(controllerFile, "Scenarios");
   for (int tcid=0;tcid<controllerRowCount;tcid++)
   {
    String testcaseid=datatable.getCellData(controllerFile, "Scenarios", "TestcaseID", tcid+2);
    String testcasename=datatable.getCellData(controllerFile, "Scenarios", "TestcaseName", tcid+2);
    String description=datatable.getCellData(controllerFile, "Scenarios", "Description ", tcid+2);
    String runStatus=datatable.getCellData(controllerFile, "Scenarios", "RunStatus ", tcid+2);
   
    System.out.println("testcaseid  :"+testcaseid);
    System.out.println("testcasename  :"+testcasename);
    System.out.println("description  :"+description);
    System.out.println("runStatus  :"+runStatus);
   
    if (runStatus.equalsIgnoreCase("yes"))
    {
     
     log.info("The Scenario "+testcasename+" execution has started ................");
     StartTime=ApplicationIndependent.getDateTime("dd-MMM-yyyy h:m:s z");
     Class oDriver[]=new Class[1];
     oDriver[0]=WebDriver.class;
     
     oBrowser=Initialize.launch();
     testScriptExcelFile=System.getProperty("user.dir")+"/TestScriptDataFiles/"+testcasename+".xlsx";
     int testScriptFileRowCount=datatable.rowCount(testScriptExcelFile, testcaseid);
     
     for (int tsid=0;tsid<testScriptFileRowCount;tsid++)
     {
      String testscriptid=datatable.getCellData(testScriptExcelFile, testcaseid, "TestScriptID", tsid+2);
      String testdescription=datatable.getCellData(testScriptExcelFile, testcaseid, "Description", tsid+2);
      String methodname=datatable.getCellData(testScriptExcelFile, testcaseid, "MethodName", tsid+2);
      String packageclassname=datatable.getCellData(testScriptExcelFile, testcaseid, "PackageClassName", tsid+2);
      testDataColumn=datatable.getCellData(testScriptExcelFile, testcaseid, "TestDataColumn", tsid+2);
      //verifytextdata=datatable.getCellData(testScriptExcelFile, testcaseid, "VerifyText", tsid+2);
      //objectmapdata=datatable.getCellData(testScriptExcelFile, testcaseid, "ObjectMap", tsid+2);
      System.out.println("testscriptid  :"+testscriptid);
      System.out.println("testdescription  :"+testdescription);
      System.out.println("methodname  :"+methodname);
      System.out.println("packageclassname  :"+packageclassname);
      System.out.println(" testDataColumn :"+testDataColumn);
      Class cls=Class.forName(packageclassname);
      Object obj=cls.newInstance();
     
      Method method=obj.getClass().getMethod(methodname, oDriver);
      log.info("The Method "+methodname+" execution has started in Scenario "+testcasename);
      String executionStatus=(String) method.invoke(obj, oBrowser);
     
      TestscriptStatus=TestscriptStatus+executionStatus;
      String Screenshotpath=System.getProperty("user.dir")+"/Results/Screenshots";
      String screenshotName=Screenshotpath+"/Scenarios_"+testscriptid+"_"+testcasename+"_"+methodname+"_Screenshot.jpeg";
      if (executionStatus.equalsIgnoreCase("fail"))
      {
       ApplicationIndependent.captureScreenshot(oBrowser, screenshotName);
      }
      log.info("The Execution status of the Method "+methodname+" in Scenario "+testcasename+" has become "+executionStatus);
      ReportUtil.addArrayList(testscriptid, testdescription, methodname, packageclassname, executionStatus, screenshotName);
     }
     endtime=ApplicationIndependent.getDateTime("dd-MMM-yyyy h:m:s z");
     log.info("The Scenario "+testcasename+" execution has ended ................");
     
     if (TestscriptStatus.contains("Fail"))
     {
      ReportUtil.writeTestResults(testcaseid, testcasename, "Fail", StartTime, endtime);
     }else
     {
      ReportUtil.writeTestResults(testcaseid, testcasename, "Pass", StartTime, endtime);
     }
     TestscriptStatus="";
    }
       
    log.info("+++++++++++++++++++++++++++++++++++++++++");
   }
   log.info("The Execution of the Method executeTestScripts has ended here...");
  }catch(Exception e)
  {
   log.error("there is an execption araised during the execution of the Method loadFiles , The Exception :"+e );
  }
  ReportUtil.endSuite();
 }
 
 @AfterSuite
 public void endScript()
 {
  String endTime=null;
  try
  {
   endTime=ApplicationIndependent.getDateTime("dd-MMM-yyyy h:m:s z");
   ReportUtil.updateEndTime(endTime);
  }catch(Exception e)
  {
   log.error("there is an execption araised during the execution of the Method endScript , The Exception :"+e );
  }
 }
}