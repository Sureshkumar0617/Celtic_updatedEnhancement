package com.celtic.automation.cmcs.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.celtic.automation.cmcs.factory.DriverFactory;

public class ScreenshotUtility extends DriverFactory{
	
	private ConfigReader config =new ConfigReader();
	
public  void captureScreenshot(String classname,String screenshotName) throws Exception {
	config.initprop();
	String exeTime = new SimpleDateFormat("hh:mm").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)getDriver();
	File source=ts.getScreenshotAs(OutputType.FILE);
	//get recently created folder under test-output which contains celtic-spark name  recentcreatedfile=./test-output\\celtic-Spark 07-Nov-22 12-10-53\\
	String fileLocation=System.getProperty("user.dir")+"\\"+config.readPassedScreenshotFile(); 
	String recentCreatedFile=ElementUtil.getfolder(fileLocation);
	File f = new File(recentCreatedFile);
	if(f.exists()) { 
		FileUtils.copyFile(source, new File(recentCreatedFile+"\\"+"Screenshot"+"\\"+classname,screenshotName+".png"));	
	}	
	else {
		FileUtils.copyFile(source, new File(fileLocation+"\\"+"Screenshot"+"\\"+classname+exeTime,screenshotName+".png"));
	}
	
	
}
}



