package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class Reporter extends BaseTestSetup{
	public static ExtentTest test;
	public static ExtentReports extent;
	public static String testCaseName, testDescription, category, authors, reportPath, documentPath;
	
	public void startReporter() {
			SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss");
			java.util.Date date = new java.util.Date();
			String newDate = formatdate.format(date).toString();
			System.out.println(newDate);
			reportPath = "./reports/"+newDate;
			 File directory = new File(reportPath);
		  		if (!directory.exists()) {
		  			directory.mkdirs();
		  		}
		
	}
	
	public void reportStep(String desc, String status)  {
		
		// Write if it is successful or failure or information
		/*try {
		File javaFile = new File("./src/test/resources/audio/audio.vbs");	
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(javaFile)));
		
		
			writer.write("Dim text, speech\n Set speech = CreateObject(\"SAPI.SpVoice\")\n" );
			writer.write("text = \""+ desc + "\"\n");
			writer.write("speech.Speak text");
			writer.close();
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C" , "D:\\CBA_Framework\\Core-Banking_v1.0\\src\\test\\resources\\audio\\audio.vbs");
        	pb.start();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, desc);
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, desc);		
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			long snapNumber = 100000l;
			
			try {			
					snapNumber= takeSnap();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			captureScreen(snapNumber);
			test.log(LogStatus.INFO, desc + test.addScreenCapture("./../."+reportPath +"/images/"+snapNumber+".jpg"));
		}
	}

	
	public void reportStep(String desc, String status, boolean screenFlag) {
		
		
		long snapNumber = 100000l;
		
		try {	
				if (screenFlag){
				snapNumber= takeSnap();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, desc);
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, desc);
			
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}
	}

	public abstract long takeSnap();
	

	public ExtentReports startResult(){
		startReporter();
		extent = new ExtentReports(reportPath +"/result.html", false);
		extent.loadConfig(new File("configs/extent-config.xml"));
		return extent;
	}

	public ExtentTest startTestCase(String testCaseName, String testDescription){
		test = extent.startTest(testCaseName, testDescription);
		return test;
	}

	public void endResult(){		
		extent.flush();
	}

	public void endTestcase(){
		extent.endTest(test);
	}
	
	
	static XWPFDocument newWord;
	   static FileOutputStream out;
	   static XWPFParagraph para;
	   static XWPFRun run;
	   public static RemoteWebDriver driver;
	   public static int iCapture;
	   public static String testId;	   
	   static int i =0;
	
	
	/* ---------------------------------------------------------------------
    Method Name: CreateScreenshotDoc
    Description: Method to create the word document for appending the screenshot
    Author: Sathish A
------------------------------------------------------------------------*/
public static void CreateScreenshotDoc(String fileName, String scenarioName){	
	
   iCapture = 0;
   
   System.out.println(testId);
   if(fileName.length() > 50) {
	   fileName = fileName.substring(0, 50);
   }
   		SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss");
		java.util.Date date = new java.util.Date();
		String newDate = formatdate.format(date).toString();
		System.out.println(newDate);
		String TestName =fileName ;
		fileName = fileName +"_"+ newDate;
		 
		testId = fileName;
   System.out.println("createDocument");
   try {
	   documentPath = reportPath + "/TestScript_ScreenShot";
   File directory = new File(documentPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}	
		
		LocalDateTime now= LocalDateTime.now();
		documentPath = documentPath + "/";
		
  
   newWord = new XWPFDocument();          
   File fl = new File(documentPath + fileName +".docx");
   if (!fl.exists()){
 	  out = new FileOutputStream(new File(documentPath + fileName +".docx"));          
       para = newWord.createParagraph();
       run = para.createRun();
       run.setText("Test Name : " + TestName);
       run.addBreak();
       run.addBreak();
       run.setText("Executed On : " + now);
       run.addBreak();
       run.addBreak();
       run.setText("Scenario Name: ");
   		run.setText(scenarioName);  
   		run.addBreak();	
       newWord.write(out);
       out.close();
       
   }
 	  
   
   }
   catch (FileNotFoundException e) {
              // TODO Auto-generated catch block
                  e.printStackTrace();
   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static String captureScreen(long snapNumber){
	String fileName = null;
              try {
            	  File destFile;
                  	XWPFDocument doc = new XWPFDocument(new FileInputStream(documentPath + testId + ".docx"));
                  	para = doc.getLastParagraph();
                  	run = para.createRun();
                     destFile=new File(reportPath +"/images/"+snapNumber+".jpg");                    
                     FileInputStream pic = new FileInputStream(destFile);
                     i++;
                     run.addBreak();               
                     run.addPicture(pic,XWPFDocument.PICTURE_TYPE_JPEG, "Sample"+i+".jpeg", Units.toEMU(450), Units.toEMU(250));
                     run.addBreak();     
                     pic.close();
                     out = new FileOutputStream(new File(documentPath + testId +".docx"));
                     doc.write(out);
                     out.close();
          } catch (IOException e) {
                     System.out.println("The file may be corrupted or cannot copy file to destination");
          }catch(WebDriverException e){
                     System.out.println("The Browser is unexpectedly closed");
          } catch (InvalidFormatException e) {
                     // TODO Auto-generated catch block
                         e.printStackTrace();
              }finally{
                         iCapture++;
              }
			
			return fileName;                     
              
              
              
   }


	
	
}
