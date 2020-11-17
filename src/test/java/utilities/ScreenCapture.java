package utilities;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;

import cucumber.api.java.it.Date;

public class ScreenCapture  { 

 
   static XWPFDocument newWord;
   static FileOutputStream out;
   static XWPFParagraph para;
   static XWPFRun run;
   public static RemoteWebDriver driver;
   public static int iCapture;
   public static String testId;
   public static String reportPath;
   static int i =0;
           
/* ---------------------------------------------------------------------
           Method Name: CreateScreenshotDoc
           Description: Method to create the word document for appending the screenshot
           Author: Sathish A
------------------------------------------------------------------------*/
public static void CreateScreenshotDoc(String fileName, String scenarioName){		
          iCapture = 0;
          testId = fileName;
          System.out.println(testId);
          
          System.out.println("createDocument");
          try {
        	  SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM-YYYY");
      		java.util.Date date = new java.util.Date();
      		String newDate = formatdate.format(date).toString();
      		System.out.println(newDate);
          LocalDateTime now= LocalDateTime.now();
          reportPath = "./src/test/resources/Results/" + newDate + "/";
          File directory = new File(reportPath);
	  		if (!directory.exists()) {
	  			directory.mkdirs();
	  		}
	  		reportPath = reportPath + fileName ;
	  		
			directory = new File(reportPath);
			if (!directory.exists()) {
				directory.mkdirs();

			}

			directory = new File(reportPath + "/snap");
			if (!directory.exists()) {
				directory.mkdirs();

			}

			reportPath = reportPath + "/";
	  		
          System.out.println(reportPath);
          newWord = new XWPFDocument();          
          File fl = new File(reportPath + fileName +".docx");
          if (!fl.exists()){
        	  out = new FileOutputStream (new File(reportPath + fileName +".docx"));          
              para = newWord.createParagraph();
              run = para.createRun();
              run.setText("Test Name : " + fileName);
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
             
/* ---------------------------------------------------------------------
	Method Name: captureScreen
	Description: Method to create the word document for appending the screenshot
	Author: Sathish A
------------------------------------------------------------------------*/  
   
public static String captureScreen(){
	String fileName = null;
              try {
            	  SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss");
      			java.util.Date date = new java.util.Date();
      			String newDate = formatdate.format(date).toString();
      			System.out.println(newDate);
            	  System.out.println("Capturing Screen Shot");
                  	XWPFDocument doc = new XWPFDocument(new FileInputStream(reportPath + testId + ".docx"));
                  	para = doc.getLastParagraph();
                  	run = para.createRun();              	
                     File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                     fileName = reportPath + "snap/snap_"+testId + "_" + iCapture+ "_" +newDate+ ".png";
                     File destFile=new File(reportPath + "snap/snap_"+testId + "_" + iCapture+ "_" +newDate+ ".png");
                     FileUtils.copyFile(src, destFile);
                     FileInputStream pic = new FileInputStream(destFile);
                     i++;
                     run.addBreak();               
                     run.addPicture(pic,XWPFDocument.PICTURE_TYPE_JPEG, "Sample"+i+".jpeg", Units.toEMU(450), Units.toEMU(250));
                     run.addBreak();     
                     pic.close();
                     out = new FileOutputStream(new File(reportPath + testId +".docx"));
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

public static void captureScreen_Robot() throws AWTException{
    try {
    	
    	
    	
    	
    	
  	  SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss");
		java.util.Date date = new java.util.Date();
		String newDate = formatdate.format(date).toString();
		System.out.println(newDate);
  	  System.out.println("Capturing Screen Shot");
        	XWPFDocument doc = new XWPFDocument(new FileInputStream(reportPath + testId + ".docx"));
        	para = doc.getLastParagraph();
        	run = para.createRun();              	
          
           File destFile=new File(reportPath + "snap/snap_"+testId + "_" + iCapture+ "_" +newDate+ ".png");
           Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
       		BufferedImage capture = new Robot().createScreenCapture(screenRect);
       		ImageIO.write(capture, "bmp", destFile);
           
          
           FileInputStream pic = new FileInputStream(destFile);
           i++;
           run.addBreak();               
           run.addPicture(pic,XWPFDocument.PICTURE_TYPE_JPEG, "Sample"+i+".jpeg", Units.toEMU(450), Units.toEMU(250));
           run.addBreak();     
           pic.close();
           out = new FileOutputStream(new File(reportPath + testId +".docx"));
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
    
    
    
}

 
 
public String getTextByXPath(String xpathValue) {
           String text = null;
           try {
                      Thread.sleep(3000);
                      text =driver.findElementByXPath(xpathValue).getText();
                      
                      System.out.println("The "+text+" is reterived from the field "+xpathValue+".");
                      
           } catch (NoSuchElementException e) {
                      // TODO Auto-generated catch block
                      System.out.println("The element "+xpathValue+" is not present in the application");
           }catch (WebDriverException e) {
                      System.out.println("The Browser is unexpectedly closed");
           } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
           }finally{
                      captureScreen();
           }
           return text;
           
}
 
public static void replaceText() throws IOException {
    // TODO Auto-generated method stub
        boolean foundTable = false;
    System.out.println(reportPath + testId + ".docx");
    
    File fl = new File(reportPath + testId + ".docx");
    String name = "Sample1";
    XWPFDocument doc = new XWPFDocument(new FileInputStream(fl));
    System.out.println("Test");      
               for(XWPFParagraph p: doc.getParagraphs()){
                          for (XWPFRun run:p.getRuns()){
                        	  		
                                     for(XWPFPicture ep: run.getEmbeddedPictures()){
                                    	 		
                                                System.out.println(ep.getDescription());
                                                System.out.println(ep.getPictureData().getFileName());
                                     }
                          }
               }
    
    
    
}

 


}
