package com.celtic.automation.cmcs.pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;


public class Enquiry {
	private WebDriver driver;
	public Enquiry(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//Fleet Enquiry
@FindBy(xpath="//table[@id='FltInquiryGrid']") WebElement fleetEnquiryGrid;
@FindBy(xpath="//td[contains(@class,'leftAlign')]") List<WebElement> fleetEnquiryRowValues;

//Supplement Enquiry
@FindBy(xpath="//input[@id='AccountNO']") WebElement supplementEnquiryAccountNo;
@FindBy(xpath="//input[@id='FleetExpiryYear']") WebElement supplementEnquiryExpyear;
@FindBy(xpath="//table[@id='supplementInquiryGrid']") WebElement supplementEnquiryGrid;

//Vehicle Enquiry
@FindBy(xpath="//table[@id='VehInquiryGrid']") WebElement vehicleEnquiryGrid;
@FindBy(xpath="//th[contains(@aria-label,'VIN')]") WebElement vehicleEnquiryVIN;
@FindBy(xpath="//th[contains(@aria-label,'Unit')]") WebElement vehicleEnquiryUnitNo;
@FindBy(xpath="//input[@id='ExpirationYear']") WebElement vehicleEnquiryExpYear;
@FindBy(xpath="//th[contains(@aria-label,'Fleet Exp. MM/YYYY')]") WebElement vehicleEnquiryFleetExp;
@FindBy(xpath="//table[@id='VehInquiryGrid']/tbody/tr") List<WebElement> vehicleEnquiryRows;
//Vehicle Supplement Enquiry
@FindBy(xpath="//table[@id='gvVinTransInquiryGrid']") WebElement vehicleSupplementEnquiryGrid;
@FindBy(xpath="//input[@id='FleetExpYear']") WebElement vehicleSupplementEnquiryExpYear;
@FindBy(xpath="//table[@id='gvVinTransInquiryGrid']/tbody/tr") List<WebElement> vehicleSupplementEnquiryRows;
//Fleet Enquiry

@SuppressWarnings("unused")
public Boolean fleetenquiryvaluevalidation(String valueCheck) throws InterruptedException {
	ElementUtil.waitUntilElementVisible(fleetEnquiryGrid);
	Boolean flag=false;
	if(ElementUtil.isPresentAndDisplayed(fleetEnquiryGrid)) {
		for(int i=0;i<fleetEnquiryRowValues.size();i++) {
			if(ElementUtil.FetchTextBoxValuewithText(fleetEnquiryRowValues.get(i)).contains(valueCheck)) {
				ElementUtil.highlightElement(driver, fleetEnquiryRowValues.get(i));
				ElementUtil.sleepTime(1000); //to wait the screen for user visibility
				flag=true;
			break;
			}
			
		}
	}
	return flag;
}

//Supplement Enquiry
public void enterSupplementEnquiryAccountNo(String accountNoValue) throws IOException {
	ElementUtil.webEditTxtChange(supplementEnquiryAccountNo,accountNoValue);
}
public void enterSupplementEnquiryExpYear(String enquiryExpYear) {
	ElementUtil.webEditTxtChange(supplementEnquiryExpyear,enquiryExpYear);
	
}
@SuppressWarnings("unused")
public Boolean supplementEnquiryvaluevalidation(String valueCheck) throws InterruptedException {
	Boolean flag=false;
	ElementUtil.waitUntilElementVisible(supplementEnquiryGrid);
	if(ElementUtil.isPresentAndDisplayed(supplementEnquiryGrid)) {
		for(int i=0;i<fleetEnquiryRowValues.size();i++) {
			if(ElementUtil.FetchTextBoxValuewithText(fleetEnquiryRowValues.get(i)).contains(valueCheck)) {
				ElementUtil.highlightElement(driver, fleetEnquiryRowValues.get(i));
				ElementUtil.sleepTime(1000); //to wait the screen for user visibility
				flag=true;
			break;
			}
		}
	}
	return flag;
}
//Vehicle Enquiry
public void enterVehicleEnquiryExpYear(String vehicleEnquiryExpYearValue) {
	ElementUtil.webEditTxtChange(vehicleEnquiryExpYear,vehicleEnquiryExpYearValue);
}
public void sortVehicleEnquiryUnitNo() {
	ElementUtil.waitUntilElementVisible(vehicleEnquiryGrid);
	if(ElementUtil.isPresentAndDisplayed(vehicleEnquiryGrid)) {
		if(!(ElementUtil.FetchTextBoxValuewithattribute(vehicleEnquiryUnitNo,"class").contains("asc"))) {
			ElementUtil.clickElement(vehicleEnquiryUnitNo); 
		}
	}
}

public int vehicleEnquiryTableRowCount() {
	return vehicleEnquiryRows.size();
}

public String fetchVehicleEnquiryVIN(String i,String yearValue) {
	String vinTableValue=null;
	WebElement ExpiryYear=driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr["+i+"]/td[5]"));
	WebElement VIN=driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr["+i+"]/td[6]"));
	if(ElementUtil.isPresentAndDisplayed(vehicleEnquiryGrid)) {
		if(ElementUtil.FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
			ElementUtil.highlightElement(driver, VIN);
			ElementUtil.sleepTime(1000); //to wait the screen for user visibility
			vinTableValue=ElementUtil.FetchTextBoxValuewithText(VIN);
		}
	}
	return vinTableValue;
}
public String fetchVehicleEnquiryUnit(String i,String yearValue) {
	String unitTableValue=null;
	WebElement ExpiryYear=driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr["+i+"]/td[5]"));
	WebElement unit=driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr["+i+"]/td[8]"));	
	if(ElementUtil.isPresentAndDisplayed(vehicleEnquiryGrid)) {
		if(ElementUtil.FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
			ElementUtil.highlightElement(driver, unit);
			ElementUtil.sleepTime(1000); //to wait the screen for user visibility
			unitTableValue=ElementUtil.FetchTextBoxValuewithText(unit);
		}
	}
	return unitTableValue;
}
//Vehicle Supplement Enquiry
public void enterVehicleSupplementEnquiryExpYear(String vehicleSupplementEnquiryExpYearValue) {
	ElementUtil.webEditTxtChange(vehicleSupplementEnquiryExpYear,vehicleSupplementEnquiryExpYearValue);
}

public void clickVehicleSupplementUnitNo() {
	if(ElementUtil.isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {
		if(!(ElementUtil.FetchTextBoxValuewithattribute(vehicleEnquiryUnitNo,"class").contains("asc"))) {
			ElementUtil.clickElement(vehicleEnquiryUnitNo); 
		}
	}
}

public int vehicleSupplementEnquiryTableRowCount() {
	return vehicleSupplementEnquiryRows.size();
	
}
public String FetchVehicleSupplementEnquiryVIN(String i,String yearValue) {
	String vinTableValue=null;
	WebElement ExpiryYear=driver.findElement(By.xpath("//tr["+i+"]//td[contains(@class,'Alignment')][4]"));
	WebElement VIN=driver.findElement(By.xpath("//tr["+i+"]//td[contains(@class,'Alignment')][8]"));
	if(ElementUtil.isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {
		
		if(ElementUtil.FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
			ElementUtil.highlightElement(driver, VIN);
			ElementUtil.sleepTime(1000); //to wait the screen for user visibility
			vinTableValue=ElementUtil.FetchTextBoxValuewithText(VIN);
		}
	}
	return vinTableValue;
}
public String fetchVehicleSupplementEnquiryUnit(String i,String yearValue, String serviceTypeValue) {
	String unitTableValue=null;
	WebElement expiryYear=driver.findElement(By.xpath("//tr["+i+"]//td[contains(@class,'Alignment')][4]"));
	WebElement serviceType=driver.findElement(By.xpath("//tr["+i+"]//td[contains(@class,'Alignment')][6]"));
	WebElement unit=driver.findElement(By.xpath("//tr["+i+"]//td[contains(@class,'Alignment')][10]"));
	
	if(ElementUtil.isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {
		if(ElementUtil.FetchTextBoxValuewithText(expiryYear).contains(yearValue)) {
			if(ElementUtil.FetchTextBoxValuewithText(serviceType).contains(serviceTypeValue)) {
				ElementUtil.highlightElement(driver, unit);
				ElementUtil.sleepTime(1000); //to wait the screen for user visibility
				unitTableValue=ElementUtil.FetchTextBoxValuewithText(unit);
			}
		}
	}
	return unitTableValue;
}













}
