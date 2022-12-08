package com.celtic.automation.cmcs.parallel;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.pages.AccountTabPage;
import com.celtic.automation.cmcs.pages.BillingTab;
import com.celtic.automation.cmcs.pages.CommonObjects;
import com.celtic.automation.cmcs.pages.DashBoardPage;
import com.celtic.automation.cmcs.pages.DistanceTabPage;
import com.celtic.automation.cmcs.pages.Enquiry;
import com.celtic.automation.cmcs.pages.Financepage;
import com.celtic.automation.cmcs.pages.FleetPage;
import com.celtic.automation.cmcs.pages.FleetTabPage;
import com.celtic.automation.cmcs.pages.InventoryPage;
import com.celtic.automation.cmcs.pages.LoginPage;
import com.celtic.automation.cmcs.pages.Payment;
import com.celtic.automation.cmcs.pages.PaymentTab;
import com.celtic.automation.cmcs.pages.SupplementaryDocuments;
import com.celtic.automation.cmcs.pages.VehicleAdd;
import com.celtic.automation.cmcs.pages.VehicleAmend;
import com.celtic.automation.cmcs.pages.VehicleDelete;
import com.celtic.automation.cmcs.pages.VehicleList;
import com.celtic.automation.cmcs.pages.VehicleTabPage;
import com.celtic.automation.cmcs.pages.WgtGroup;
import com.celtic.automation.cmcs.pages.WgtGroupAdd;
import com.celtic.automation.cmcs.util.ConfigReader;
import com.celtic.automation.cmcs.util.ElementUtil;
import com.celtic.automation.cmcs.util.ReadExcelUtil;
import com.celtic.automation.cmcs.util.ScreenshotUtility;
import com.celtic.automation.cmcs.util.WriteExcelUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.junit.rules.ErrorCollector;

public class RWC003 {

	private LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
	private DashBoardPage dashboardpage = new DashBoardPage(DriverFactory.getDriver());
	private AccountTabPage accounttabpage = new AccountTabPage(DriverFactory.getDriver());
	private FleetTabPage fleettabpage = new FleetTabPage(DriverFactory.getDriver());
	private FleetPage fleetpage = new FleetPage(DriverFactory.getDriver());
	private CommonObjects commonobjects = new CommonObjects(DriverFactory.getDriver());
	private DistanceTabPage distancetabpage = new DistanceTabPage(DriverFactory.getDriver());
	private WgtGroup wgtgroup = new WgtGroup(DriverFactory.getDriver());
	private WgtGroupAdd wgtgroupadd = new WgtGroupAdd(DriverFactory.getDriver());
	private VehicleTabPage Vehicletabpage = new VehicleTabPage(DriverFactory.getDriver());
	private VehicleAmend vehicleAmend =new VehicleAmend(DriverFactory.getDriver());
	private VehicleDelete vehicleDelete =new VehicleDelete(DriverFactory.getDriver());
	private BillingTab billingtab = new BillingTab(DriverFactory.getDriver());
	private Payment pay =new Payment(DriverFactory.getDriver());
	private PaymentTab paymenttab = new PaymentTab(DriverFactory.getDriver());
	private InventoryPage inventorypage = new InventoryPage(DriverFactory.getDriver());
	private VehicleAdd vehicleadd= new VehicleAdd(DriverFactory.getDriver());
	private Financepage financepage =new Financepage(DriverFactory.getDriver());
	private VehicleList vehiclelist =new VehicleList(DriverFactory.getDriver());
	private SupplementaryDocuments suppledocs =new SupplementaryDocuments(DriverFactory.getDriver());
	private Enquiry enquiry=new Enquiry(DriverFactory.getDriver());

	private int readRowNo=4;
	private int writeRowNo=5;
	String renewVehiclesCount=null;
	private String ParentWindow=null;
	private String childWindow =null;
	private String fileLocation= null;
	private String DesiredPath =null;
	private ScreenshotUtility screenshotUtil =new ScreenshotUtility();
	private ErrorCollector error = new ErrorCollector();
	private ConfigReader config=new ConfigReader();
	private ElementUtil eleutil =new ElementUtil();

	private ReadExcelUtil excelutil=null;
	private WriteExcelUtil excelutilWrite=null;

	private String[] fullClassName=this.getClass().getName().split("[.]");
	private String className = this.getClass().getName().split("[.]")[fullClassName.length-1];
	private String installmentPlanCheckStatus=null;

	@Given("login user as Internal")
	public void loginIRP() throws Exception {
	    config.loggerConfigurator(new Throwable().getStackTrace()[0].getClassName());

		config.initprop();
		excelutil = new ReadExcelUtil(config.readRwcExcel());
		excelutilWrite=new WriteExcelUtil();

		DriverFactory.getDriver().get(config.readLoginURL());
		CommonStep.scenario.log("Launch the application using URL and login with valid credentials");
		ConfigReader.log.info("****************************** Login to the application  *****************************************");
		screenshotUtil.captureScreenshot(className,"ApplicationLogin");

		loginpage.enterUsername(config.readLoginInternalUser());
		ConfigReader.log.info("*** Enter Username ***");
		screenshotUtil.captureScreenshot(className,"Username");

		loginpage.enterPassword(config.readPswrd());
		ConfigReader.log.info("*** Enter Password ***");
		screenshotUtil.captureScreenshot(className,"Password");

		loginpage.clickLoginBtn();
		ConfigReader.log.info("*** Click Login ***");
		screenshotUtil.captureScreenshot(className,"Login");
	}

	@When("User will navigate to IRP option")
	public void  navigateIRP() throws Exception {
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		CommonStep.scenario.log("Click on Renew fleet from Fleet card menu.");

		dashboardpage.clickIRPLink();
		ConfigReader.log.info("*** Click IRP ***");
		screenshotUtil.captureScreenshot(className,"IRP");

		dashboardpage.clickRenewFleetLink();
		ConfigReader.log.info("*** Click RenewFleet ***");
		screenshotUtil.captureScreenshot(className,"RenewFleet");
	}

	@Then("User navigate to renew fleet to input the required information")
	public void renewFleetIRP() throws IOException, Exception {
		CommonStep.scenario.log("Enter valid search data and click to proceed");

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		ConfigReader.log.info("*** Enter Account Number ***");
		screenshotUtil.captureScreenshot(className,"Entering AccountNumber");

		fleetpage.enterFleetNo(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));
		ConfigReader.log.info("*** Enter FleetNo ***");
		screenshotUtil.captureScreenshot(className,"Entering FleetNumber");

		fleetpage.enterFleetyear(excelutil.getCellData("PreSetup","Fleet Expiration Year",readRowNo));
		ConfigReader.log.info("*** Click FleetYear ***");
		screenshotUtil.captureScreenshot(className,"Entering FleetYear");

		commonobjects.clickProceed();	
		commonobjects.waitForSpinner();
	}

	@Then("User will input the data for account screen  and validate message")
	public void accountRenew() throws Exception, Exception {
		//Fetch Values
		CommonStep.scenario.log("Enter valid all detail in account page with comments and click on proceed button");
		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchMCECustomernoLbl(),writeRowNo,accounttabpage.fetchMCECustomerNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchRegistrationTypeLbl(), writeRowNo,accounttabpage.fetchRegistrationType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountCarrierTypeLbl(), writeRowNo,accounttabpage.fetchAccountCarrierType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchIFTAAccountNbrlbl(),writeRowNo,accounttabpage.fetchIFTAAccountNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountCustomerStatusLbl(),writeRowNo,accounttabpage.fetchAccountCustomerStatus());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountStreet0Lbl(),writeRowNo,accounttabpage.fetchAccountStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountZip0lbl(),writeRowNo,accounttabpage.fetchAccountZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountJur0lbl(), writeRowNo,accounttabpage.fetchAccountJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCity0lbl(),writeRowNo,accounttabpage.fetchAccountCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCounty0Lbl(), writeRowNo,accounttabpage.fetchAccountCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCountry0Lbl(),writeRowNo,accounttabpage.fetchAccountCountry0());

		accounttabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountStreet1lbl(),writeRowNo,accounttabpage.fetchAccountStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountZip1Lbl(), writeRowNo,accounttabpage.fetchAccountZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountJur1lbl(), writeRowNo,accounttabpage.fetchAccountJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCity1Lbl(),writeRowNo,accounttabpage.fetchAccountCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCounty1Lbl(),writeRowNo,accounttabpage.fetchAccountCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCountry1Lbl(),writeRowNo,accounttabpage.fetchAccountCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountAttentionToLbl(),writeRowNo,accounttabpage.fetchAccountAttentionTo());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountUSDOTNoLbl(), writeRowNo,accounttabpage.fetchAccountUSDOTNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountTPIDLbl(),writeRowNo,accounttabpage.fetchAccountTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountContactNameLbl(), writeRowNo,accounttabpage.fetchAccountContactName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountEmailLbl(),writeRowNo,accounttabpage.fetchAccountEmail());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountPrimaryPhonelbl(),writeRowNo,accounttabpage.fetchAccountPrimaryPhone());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountAlternatePhoneLbl(), writeRowNo,accounttabpage.fetchAccountAlternatePhone());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountFaxNoLbl(),writeRowNo,accounttabpage.fetchAccountFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountEmailNotificationLbl(), writeRowNo,accounttabpage.fetchAccountEmailNotification());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountFaxNotificationLbl(),writeRowNo,accounttabpage.fetchAccountFaxNotification());


		accounttabpage.checkEmailNotification();
		ConfigReader.log.info("*** Check Email Notification ***");
		screenshotUtil.captureScreenshot(className,"Check EmailNotification");

		commonobjects.provideComments(excelutil.getCellData("AccountTab","Comments",readRowNo));
		ConfigReader.log.info("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Account Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		//Account Verification Screen
		CommonStep.scenario.log("Click on proceed from the verification page");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to fleet  screen and update the required information {string}")
	public void navigateToFleet(String expSucces) throws Exception {
		String actualtext = commonobjects.fetchErrorMessage(expSucces);

		try {
			Assert.assertEquals(actualtext, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Fleet Screen"+expSucces);

		CommonStep.scenario.log("Enter update all the mandatory and valid details in fleet page. Also update Fleet Expiration Date & Fleet Type & Commodity Class and click on proceed button after entering comments");
		//Fetch Fleet Details screen 
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchRegistrationTypeLbl(), writeRowNo,fleettabpage.fetchRegistrationType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFltStatusLbl(),writeRowNo,fleettabpage.fetchFltStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchCarrierTypeLbl(), writeRowNo,fleettabpage.fetchCarrierType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchDBANameLbl(),writeRowNo, fleettabpage.fetchDBAName());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetStreet0Lbl(),writeRowNo,fleettabpage.fleetStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetZip0Lbl(),writeRowNo,fleettabpage.fleetZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetJur0Lbl(), writeRowNo,fleettabpage.fleetJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCity0Lbl(),writeRowNo, fleettabpage.fleetCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCounty0Lbl(), writeRowNo,fleettabpage.fleetCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCountry0Lbl(),writeRowNo,fleettabpage.fleetCountry0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetNonDeliverable0Lbl(), writeRowNo,fleettabpage.fleetNonDeliverable0());

		fleettabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetStreet1Lbl(),writeRowNo,fleettabpage.fleetStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetZip1Lbl(),writeRowNo,fleettabpage.fleetZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetJur1Lbl(), writeRowNo,fleettabpage.fleetJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCity1Lbl(),writeRowNo, fleettabpage.fleetCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCounty1Lbl(), writeRowNo,fleettabpage.fleetCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCountry1Lbl(),writeRowNo,fleettabpage.fleetCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetNonDeliverable1Lbl(), writeRowNo,fleettabpage.fleetNonDeliverable1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetAttentionToLbl(), writeRowNo,fleettabpage.fleetAttentionTO());

		fleettabpage.navigateToServiceProvider();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceProviderLbl(), writeRowNo,fleettabpage.fleetServiceProvider());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceLegalNameLbl(),writeRowNo,fleettabpage.fleetServiceLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceDBANameLbl(), writeRowNo,fleettabpage.fleetServiceDBAName());

		if(fleettabpage.fleetServicePowerOfAttroneyLblpresence()==true) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyLbl(),writeRowNo, fleettabpage.fleetServicePowerOfAttroney());
		}
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyEffDateLbl(), writeRowNo,fleettabpage.fleetServicePowerOfAttroneyEffDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyExpDateLbl(),writeRowNo,fleettabpage.fleetServicePowerOfAttroneyExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceEmailIdLbl(), writeRowNo,fleettabpage.fleetServiceEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePhoneNoLbl(),writeRowNo, fleettabpage.fleetServicePhoneNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceFaxNoLbl(), writeRowNo,fleettabpage.fleetServiceFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceStreetLbl(),writeRowNo,fleettabpage.fleetServiceStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceCityLbl(), writeRowNo,fleettabpage.fleetServiceCity());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceJurLbl(),writeRowNo, fleettabpage.fleetServiceJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceZipCodeLbl(), writeRowNo,fleettabpage.fleetServiceZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceCountryLbl(),writeRowNo,fleettabpage.fleetServiceCountry());

		fleettabpage.clickOnTimeMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingStreetLbl(), writeRowNo,fleettabpage.fleetServiceMailingStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingZipCodeLbl(),writeRowNo, fleettabpage.fleetServiceMailingZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingJurLbl(), writeRowNo,fleettabpage.fleetServiceMailingJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCityLbl(),writeRowNo,fleettabpage.fleetServiceMailingCity());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCountyLbl(), writeRowNo,fleettabpage.fleetServiceMailingCounty());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCountryLbl(),writeRowNo, fleettabpage.fleetServiceMailingCountry());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingAttentionToLbl(), writeRowNo,fleettabpage.fleetServiceMailingAttentionTo());

		eleutil.scrollToBottom();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsContactNameLbl(),writeRowNo,fleettabpage.fleetDetailsContactName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsEmailIdLbl(), writeRowNo,fleettabpage.fleetDetailsEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPrimaryCellNbrLbl(),writeRowNo, fleettabpage.fleetDetailsPrimaryCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsAlternateCellNbrLbl(), writeRowNo,fleettabpage.fleetDetailsAlternateCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFaxNoLbl(),writeRowNo, fleettabpage.fleetDetailsFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsTpIdLbl(), writeRowNo,fleettabpage.fleetDetailsTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsUsdotNbrLbl(),writeRowNo,fleettabpage.fleetDetailsUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsChangeVehUsdotTinLbl(), writeRowNo,fleettabpage.fleetDetailsChangeVehUsdotTin());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltTypeLbl(),writeRowNo, fleettabpage.fleetDetailsFltType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsCommodityClassLbl(), writeRowNo,fleettabpage.fleetDetailsCommodityClass());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltEffDateLbl(),writeRowNo,fleettabpage.fleetDetailsFltEffDatedtPicker());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltExpDateLbl(), writeRowNo,fleettabpage.fleetDetailsFltExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsChangeAddrOnUsdotLbl(),writeRowNo, fleettabpage.fleetDetailsChangeAddrOnUsdot());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFirstOperatedDateLbl(),writeRowNo,fleettabpage.fleetDetailsFirstOperatedDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsWyomingIndicatorLbl(), writeRowNo,fleettabpage.fleetDetailsWyomingIndicator());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsIFTADistanceLbl(),writeRowNo, fleettabpage.fleetDetailsIFTADistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsMobileNotificationLbl(), writeRowNo,fleettabpage.fleetDetailsMobileNotification());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsIRPRequirementsLbl(),writeRowNo, fleettabpage.fleetDetailsIRPRequirements());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsStatementOfUnderstandingLbl(), writeRowNo,fleettabpage.fleetDetailsStatementOfUnderstanding());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsInstallmentAgreementLbl(),writeRowNo,fleettabpage.fleetDetailsInstallmentAgreement());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPowerOfAttorneyLbl(), writeRowNo,fleettabpage.fleetDetailsPowerOfAttorney());

		if(fleettabpage.fleetHVUTFormLblpresence()==true) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsHVUTFormLbl(),writeRowNo, fleettabpage.fleetDetailsHVUTForm());
		}
		if(fleettabpage.fleetPropertyTaxLblpresence()==true) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPropertyTaxLbl(), writeRowNo,fleettabpage.fleetDetailsPropertyTax());
		}

		eleutil.scrollToTop();
		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		ConfigReader.log.info("Message in Fleet Screen "+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Fleet Screen 1");


		fleettabpage.navigateToServiceProvider();
		ConfigReader.log.info("*** navigateToServiceProvider ***");
		screenshotUtil.captureScreenshot(className,"Navigate to Service provider");

		fleettabpage.clickPowerOfAttroney();
		ConfigReader.log.info("*** Click PowerofAttroney ***");
		screenshotUtil.captureScreenshot(className,"Check Power of Attroney");

		fleettabpage.enterEmailID(excelutil.getCellData("FleetTab","Email iD",readRowNo));
		ConfigReader.log.info("*** Entering the Emailid ***");
		screenshotUtil.captureScreenshot(className,"Entering the Emailid");

		//Expiration Date through Excel
		/*	fleettabpage.selectExpirationDate(excelutil.getCellData("FleetTab","Expiration Date",readRowNo));
		ConfigReader.log.info("*** Selecting the Expiration Date ***");
		screenshotUtil.captureScreenshot(className,"Selecting the Expiration Date");
		 */
		/*	if(eleutil.daysDifference("MM/dd/YYYY",eleutil.getCurrentDateInSpecifiedFormat("MM/dd/YYYY"),fleettabpage.fleetDetailsFltExpDate()).equalsIgnoreCase("After")) {
		if(excelutilRWC.getCellData("FleetTab","Expiration Date",readRowNo) != null) {
			fleettabpage.selectExpirationDate(excelutilRWC.getCellData("FleetTab","Expiration Date",readRowNo));
			ConfigReader.log.info("*** Selecting the Expiration Date ***");
			screenshotUtil.captureScreenshot(className,"Selecting the Expiration Date"); 
			}
	}*/
		//Expiration Date through System
		//fleettabpage.selectExpirationDate(eleutil.getAddedDateInSpecifiedFormat("MMddYYYY",2));

		fleettabpage.selectFleetType(excelutil.getCellData("FleetTab","Fleet type",readRowNo));
		ConfigReader.log.info("*** Selecting  the Fleet Type ***");
		screenshotUtil.captureScreenshot(className,"Selecting the Fleet Type");

		fleettabpage.selectCommodityClass(excelutil.getCellData("FleetTab","Commodity Class",readRowNo));
		ConfigReader.log.info("*** Selectng  the Commondity Class ***");
		screenshotUtil.captureScreenshot(className,"Entering the Commodity Class");

		fleettabpage.selectIRPRequirementForm(excelutil.getCellData("FleetTab","IRP Requirements Form",readRowNo));
		ConfigReader.log.info("*** Selecting the IRPRequirementForm ***");
		screenshotUtil.captureScreenshot(className,"Selecting the IRPRequirementForm");

		fleettabpage.selectStatementofUnderstanding(excelutil.getCellData("FleetTab","Statement of Understanding",readRowNo));		
		ConfigReader.log.info("*** Selecting StatementofUnderstanding ***");
		screenshotUtil.captureScreenshot(className,"Selecting StatementofUnderstanding");

		fleettabpage.selectInstallmentAgreement(excelutil.getCellData("FleetTab","Installment Agreement",readRowNo));
		ConfigReader.log.info("*** Selecting InstallmentAgreement ***");
		screenshotUtil.captureScreenshot(className,"Selecting InstallmentAgreement");

		fleettabpage.selectPowerOfAttroney(excelutil.getCellData("FleetTab","Power of Attorney",readRowNo));
		ConfigReader.log.info("*** Selecting PowerOfAttroney ***");
		screenshotUtil.captureScreenshot(className,"Selecting PowerOfAttroney");

		fleettabpage.selectIRPRequirementForm(excelutil.getCellData("FleetTab","IRP Requirements Form",readRowNo));
		ConfigReader.log.info("*** Selecting the IRPRequirementForm ***");
		screenshotUtil.captureScreenshot(className,"Selecting the IRPRequirementForm");

		fleettabpage.selectStatementofUnderstanding(excelutil.getCellData("FleetTab","Statement of Understanding",readRowNo));		
		ConfigReader.log.info("*** Selecting StatementofUnderstanding ***");
		screenshotUtil.captureScreenshot(className,"Selecting StatementofUnderstanding");

		fleettabpage.selectInstallmentAgreement(excelutil.getCellData("FleetTab","Installment Agreement",readRowNo));
		ConfigReader.log.info("*** Selecting InstallmentAgreement ***");
		screenshotUtil.captureScreenshot(className,"Selecting InstallmentAgreement");

		fleettabpage.selectPowerOfAttroney(excelutil.getCellData("FleetTab","Power of Attorney",readRowNo));
		ConfigReader.log.info("*** Selecting PowerOfAttroney ***");
		screenshotUtil.captureScreenshot(className,"Selecting PowerOfAttroney");

		if(fleettabpage.fleetHVUTFormLblpresence()==true) {
			fleettabpage.selectHVUTForm(excelutil.getCellData("FleetTab","HVUTForm 2290",readRowNo));
			ConfigReader.log.info("*** Selecting HVUTForm ***");
			screenshotUtil.captureScreenshot(className,"Selecting HVUTForm");		
		}
		if(fleettabpage.fleetPropertyTaxLblpresence()==true) {
			fleettabpage.selectPropertyTax(excelutil.getCellData("FleetTab","Property Tax",readRowNo));
			ConfigReader.log.info("*** Selecting PropertyTax ***");
			screenshotUtil.captureScreenshot(className,"Selecting PropertyTax");	
		}


		commonobjects.provideComments(excelutil.getCellData("FleetTab","Comments",readRowNo));
		ConfigReader.log.info("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Fleet Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		CommonStep.scenario.log("Click on proceed button from the verification page");

		//Fleet Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to distance to input the data and validate message {string} {string}")
	public void navigateToDistance(String expSucces1, String expSucces2) throws Exception, Exception {
		CommonStep.scenario.log("Select Actual Distance radio button & Enter all the mandatory and valid details in distance page and click on proceed button after entering comments");
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceReportingPeriodFromLbl(), writeRowNo,distancetabpage.distanceReportingPeriodFrom());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceReportingPeriodToLbl(), writeRowNo,distancetabpage.distanceReportingPeriodTo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceUsdotNbrLbl(), writeRowNo,distancetabpage.distanceUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceEstimatedDistanceChartLbl(), writeRowNo,distancetabpage.distanceEstimatedDistanceChart());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceOverrideContJurLbl(), writeRowNo,distancetabpage.distanceOverrideContJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceEstimatedDistanceLbl(), writeRowNo,distancetabpage.distanceEstimatedDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceActualDistanceLbl(), writeRowNo,distancetabpage.distanceActualDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceTotalFleetDistanceLbl(), writeRowNo,distancetabpage.distanceTotalFleetDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceFRPMlgQuetionLbl(), writeRowNo,distancetabpage.distanceFRPMlgQuetion());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceDistanceTypeLbl(), writeRowNo,distancetabpage.distanceDistanceType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceActualDistConfirmationLbl(), writeRowNo,distancetabpage.distanceActualDistConfirmation());


		//Juris Table Verification
		//Juris Table header

		ArrayList<String>  jurisvalues=distancetabpage.fetchTableJuris();
		ArrayList<String>  distancevalues=distancetabpage.fetchTableDistanceMiles();
		ArrayList<String>  percentvalues=distancetabpage.fetchTablePercent();

		for(int i=0;i<jurisvalues.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"DistanceJuris",distancetabpage.distanceJurisTableHeaderJuri()+i, writeRowNo,jurisvalues.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"DistanceJuris",distancetabpage.distanceJurisTableHeaderDistance()+i, writeRowNo,distancevalues.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"DistanceJuris",distancetabpage.distanceJurisTableHeaderPercent()+i, writeRowNo,percentvalues.get(i));
		}
		//Juris list
		String actualmessage = commonobjects.fetchErrorMessage(expSucces1);
		try {
			Assert.assertEquals(actualmessage, expSucces1);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen"+ actualmessage);

		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		ConfigReader.log.info("Message in Distance Screen"+commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className,"Message in Distance Screen 1");

		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen"+ actualmessage2);

		ConfigReader.log.info("Message in Distance Screen"+commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className,"Message in Distance Screen 1");


		distancetabpage.selectYesOrNo(excelutil.getCellData("DistanceTab","Actual Distance Reporting Period",readRowNo));
		ConfigReader.log.info("*** Selecting yes or No of Reporting period Question***");

		commonobjects.provideComments(excelutil.getCellData("DistanceTab","Comments",readRowNo));
		ConfigReader.log.info("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Distance Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		CommonStep.scenario.log("Click on proceed button from the verification page");

		//Distance Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to weight group to add the new weight group {string}")
	public void navigateToWeightGrp(String expSucces) throws Exception {
		CommonStep.scenario.log("Click on Add weight group button Add new Weight Group(s) & enter all the mandatory and valid details in the weight group page and click on proceed button");
		CommonStep.scenario.log("Update an existing Weight Group & enter comments");

		//Fetch Values into Excel
		ArrayList<String>  headervalues=wgtgroup.fetchTableHeader();
		ArrayList<String>  RowDatavalues=wgtgroup.fetchTableRowData();
		int j,k=0;
		for(int i=0;i<RowDatavalues.size();i++) {
			if(i>5){
				j=i%6;
				if(j==0){
					k++;
				}
				excelutilWrite.setCellData(config.writeRwcExcel(),"WeightGroup",headervalues.get(j)+k, writeRowNo,RowDatavalues.get(i));
				ConfigReader.log.info("Weight Group headervalues"+headervalues.get(j));
				ConfigReader.log.info("Weight Group RowDatavalues"+RowDatavalues.get(i));
			}
			else if(i>0 && i<5){

				excelutilWrite.setCellData(config.writeRwcExcel(),"WeightGroup",headervalues.get(i)+k, writeRowNo,RowDatavalues.get(i)); 
				ConfigReader.log.info("Weight Group headervalues"+headervalues.get(i));
				ConfigReader.log.info("Weight Group RowDatavalues"+RowDatavalues.get(i));
			}

		}

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Weight Group Screen"+actualmessage);


		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		ConfigReader.log.info("Message in Weight Group Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Weight Group Screen 1");

		//Add Weight Group Screen
		String weightGroupCountExcel=excelutil.getCellData("WeightGrouptab","TotalWeightGroups",readRowNo);

		for(int weightcount=0;weightcount<Integer.valueOf(weightGroupCountExcel);weightcount++) {
			if(weightcount<Integer.valueOf(weightGroupCountExcel)) {
				wgtgroup.clickAddWeightGroup();
				ConfigReader.log.info("*** Click AddweightGroup ***");
				screenshotUtil.captureScreenshot(className,"Click AddweightGroup");
			}
			wgtgroupadd.selectWeightGroupType(excelutil.getCellData("WeightGrouptab","WeightGroup Type"+String.valueOf(weightcount),readRowNo));
			ConfigReader.log.info("*** Select WeightGroupType ***");
			screenshotUtil.captureScreenshot(className,"Select WeightGroupType");

			wgtgroupadd.enterWeightGroupNo(excelutil.getCellData("WeightGrouptab","Weight Group No."+String.valueOf(weightcount),readRowNo));
			ConfigReader.log.info("*** Enter WeightGroup No ***");
			screenshotUtil.captureScreenshot(className,"Enter WeightGroup No");

			wgtgroupadd.selectMaxGrossWeight(excelutil.getCellData("WeightGrouptab","Max Gross Weight"+String.valueOf(weightcount),readRowNo));
			ConfigReader.log.info("*** Select MaxGross Weight ***");
			screenshotUtil.captureScreenshot(className,"Select MaxGross Weight");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			CommonStep.scenario.log("Click on proceed button from the verification page");
			//Add Weight Group Verification
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
		}
	}
	@And("User will update the existing weight group to proceed")
	public void existingWeightAmendment()throws Exception {
		//Weight Group Screen
		//Edit Existing Weight Group
		wgtgroup.clickHandImg();

		String juriExcelCount=excelutil.getCellData("WeightJuris","Juris Count",readRowNo);
		for(int i=0;i<Integer.valueOf(juriExcelCount);i++) {
			String juriExcel=excelutil.getCellData("WeightJuris","Juri"+String.valueOf(i),readRowNo);
			wgtgroupadd.enterWeightJuriValue(juriExcel);
		}
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		// Weight Group Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		//Validating JUR WITH DIFFERENT WEIGHTS
		String[] weightlist=wgtgroup.validateJurisWeightsedited(); //[AL, AR, AZ]
		for(int i=0;i<Integer.valueOf(juriExcelCount);i++) {
			String juriExcel=excelutil.getCellData("WeightJuris","Juri"+String.valueOf(i),readRowNo);
			if(weightlist[i].equalsIgnoreCase(juriExcel)){
				assert true;
			}
		}

		//In Weight Group Screen
		CommonStep.scenario.log("Click Done");
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to vehicle screen and proceed further")
	public void navigateToVehicle()throws Exception {
		CommonStep.scenario.log("Complete Amend vehicle  and edit Ownership Details Safety USDOT and TIN and change Weight Group such that New Plate is requested Also request TVR and enter comments Delete Vehicle(s) such that all Documents are collected and Comments entered");
		renewVehiclesCount =Vehicletabpage.fetchRenewVehicle();
		//Fetch Values
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchAmendVehicleLbl(), writeRowNo,Vehicletabpage.fetchAmendVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchAddVehiclesLbl(), writeRowNo,Vehicletabpage.fetchAddVehicles());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchDeleteVehicleLbl(), writeRowNo,Vehicletabpage.fetchDeleteVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchRenewVehicleLbl(), writeRowNo,Vehicletabpage.fetchRenewVehicle());

		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
		ConfigReader.log.info(commonobjects.validateInfoMsgs());
	} //End of vehicle ammend if loop



	@Then("User will navigate to billing to input data and avail installment plan")
	public void billingAndInstallment() throws Exception {

		if(eleutil.getTitle().trim().equalsIgnoreCase("Vehicle Renewal Entry - IRP")) {
			ConfigReader.log.info("Screen is in Vehicle Renewal Entry Screen");
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
		}
		CommonStep.scenario.log("Click Done from the supplement selection page");
		//Fetch values from Biling Screen
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchRegisterMonthLbl(),writeRowNo,billingtab.fetchRegisterMonth());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchNoOfVehiclesInSuppLbl(),writeRowNo,billingtab.fetchNoOfVehiclesinSupp());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchSupplementStatusLbl(),writeRowNo,billingtab.fetchSupplementStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchEnterpriseSystemCreditLbl(),writeRowNo,billingtab.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchIRPSystemCreditLbl(),writeRowNo,billingtab.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchRenewalFeeEffectiveDatelbl(),writeRowNo,billingtab.fetchRenewalFeeEffectiveDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchInvoiceDateLbl(),writeRowNo,billingtab.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchApplicationReceiptDateLbl(),writeRowNo,billingtab.fetchApplicationReceiptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchPaymentDateLbl(),writeRowNo,billingtab.fetchPaymentDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchExchangeRateLbl(),writeRowNo,billingtab.fetchExchangeRate());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchManualAdjBaseJurLbl(),writeRowNo,billingtab.fetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingBatchBillingLbl(),writeRowNo,billingtab.fetchBillingBatchBilling());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingTVRLbl(),writeRowNo,billingtab.fetchBillingTVR());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingInstallmentPlanLbl(),writeRowNo,billingtab.fetchBillingInstallmentPlan());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingIsUseOneTimeMailingLbl(),writeRowNo,billingtab.fetchBillingIsUseOneTimeMailing());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchElectronicDeliveryTypelbl(),writeRowNo,billingtab.fetchElectronicDeliveryType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing","Email",writeRowNo,billingtab.fetchBillingEmail());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchInvoiceReportTypelbl(),writeRowNo,billingtab.fetchBillingInvoiceReportType());

		ArrayList<String> TableFeeType=billingtab.fetchTableFeeType();
		ArrayList<String> TableFeeAmount=billingtab.fetchTableFeeAmount();

		for(int i=0;i<TableFeeType.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"BillingGrid",billingtab.fetchBillingGridFeeType()+i,writeRowNo,TableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"BillingGrid",billingtab.fetchBillingGridFeeAmount()+i,writeRowNo,TableFeeAmount.get(i));
		}	


		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		billingtab.clickTVR();
		ConfigReader.log.info("*** Billing-Click TVR ***");
		screenshotUtil.captureScreenshot(className,"Billing-Click TVR");
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingTVRNoOfDaysLbl(),writeRowNo,billingtab.fetchBillingTVRNoOfDays());

		installmentPlanCheckStatus=billingtab.fetchBillingInstallmentPlan();
		CommonStep.scenario.log("CBilling-heck Installment Payment checkbox");
		ConfigReader.log.info("***Billing- Installment Plan Check Box Status ***"+installmentPlanCheckStatus); //Before clciking should returns false

		billingtab.clickInstallmentPlan();
		ConfigReader.log.info("*** Billing-Click Installement Plan ***");
		screenshotUtil.captureScreenshot(className,"Billing-Click Installement Plan");

		billingtab.selectElectronicDeliveryType(excelutil.getCellData("BillingTab","Electronic Delivery Type",readRowNo));
		ConfigReader.log.info("*** Billing-Select Electronic DeliveryType ***");
		screenshotUtil.captureScreenshot(className,"Billing-Select Electronic DeliveryType");

		commonobjects.provideComments(excelutil.getCellData("BillingTab","BillingComments",readRowNo));
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		String mainwidow=eleutil.GetParentWindow();

		//check the Installment Plan is checked or not
		installmentPlanCheckStatus=billingtab.fetchBillingInstallmentPlan();
		ConfigReader.log.info("*** Billing-Installment Plan Check Box Status ***"+installmentPlanCheckStatus); //After clicking should returns true


		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		eleutil.waitForTwoWindow(2);
		String childWindow=eleutil.SwitchtoFirstChildWindow();
		eleutil.sleepTime(4000);  //to wait for swith to 2nd window
		eleutil.saveAsFile();
		String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
		ConfigReader.log.info("fileLocation"+fileLocation);
		String DesiredPath=eleutil.checkFileExistence(fileLocation,"Billing","pdf");
		eleutil.sleepTime(4000); //to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); //to display the file upload is completed
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(mainwidow);
	}

	@Then("User will navigate to payment tab to input the fields and validate message {string} {string} {string}")
	public void  paymentTabRenew(String expSucces, String expSucces2, String expSucces3) throws Exception {
		CommonStep.scenario.log("Verify the page & set Delivery Types as PDF Click on proceed button");
		//Fetch Values from Cart payment
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchEnterpriseSystemCreditLbl(),writeRowNo,paymenttab.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchIRPSystemCreditLbl(),writeRowNo,paymenttab.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchInvoiceDateLbl(),writeRowNo,paymenttab.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchInvoiceNumberLbl(),writeRowNo,paymenttab.fetchInvoiceNumber());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchPaymentReciptDateLbl(),writeRowNo,paymenttab.fetchPaymentReciptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchManualAdjBaseJurLbl(),writeRowNo,paymenttab.fetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchBatchCredentialLbl(),writeRowNo,paymenttab.fetchBatchCredential());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchWireTransferFeeLbl(),writeRowNo,paymenttab.fetchWireTransferFee());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchElectronicDeliveryTypeLbl(),writeRowNo,paymenttab.fetchElectronicDeliveryType());

		ArrayList<String> paymentTableFeeType=paymenttab.fetchTableFeeType();
		ArrayList<String> paymentTableFeeAmount=paymenttab.fetchTableFeeAmount();
		for(int i=0;i<paymentTableFeeType.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchHeaderFeeType()+i,writeRowNo,paymentTableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.fetchHeaderFeeAmount()+i,writeRowNo,paymentTableFeeAmount.get(i));
		}

		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage1, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen"+ expSucces);

		ConfigReader.log.info("Message in Payment Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 1");


		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen"+ expSucces2);

		ConfigReader.log.info("Message in Payment Screen"+ commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 2");
		String actualmessage3 = commonobjects.fetchErrorMessage(expSucces3);
		try {
			Assert.assertEquals(actualmessage3, expSucces3);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen"+ expSucces3);

		ConfigReader.log.info("Message in Payment Screen"+commonobjects.fetchErrorMessage(expSucces3));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 3");

		pay.selectElectronicDeliverytype(excelutil.getCellData("Payment","ElectronicDeliveryType",readRowNo));
		ConfigReader.log.info("***Payment Screen-Select Delivery type***");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		//Navigates to Payment Verification Screen
		CommonStep.scenario.log("Payment Screen-CClick pay Now");

		paymenttab.clickPayNow();
		ConfigReader.log.info("***Payment Screen-Click Pay Now**");
		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		commonobjects.validateInfoMsgs();
	}


	@Then("User will select the payment type and fill the requirement")
	public void paymentType() throws Exception, Exception {

		//Fetch Values from 
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchMCECustomerIdLbl(),writeRowNo,pay.fetchMCECustomerId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchLegalNameLbl(),writeRowNo,pay.fetchLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchInvoiceDateLbl(),writeRowNo,pay.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchEnterpriseSystemCreditLbl(),writeRowNo,pay.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchIRPSystemCreditLbl(),writeRowNo,pay.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchIftaSystemCreditLbl(),writeRowNo,pay.fetchIftaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchOpaSystemCreditLbl(),writeRowNo,pay.fetchOpaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchTotalAmountDueLbl(),writeRowNo,pay.fetchTotalAmountDue());

		ArrayList<String> TableHeadervalues=pay.fetchTableHeaders();
		ArrayList<String> tableInvoice=pay.fetchTableInvoiceDetails();
		for(int i=0;i<tableInvoice.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",TableHeadervalues.get(i),writeRowNo,tableInvoice.get(i));
		}

		String paymentExcelCount=excelutil.getCellData("Payment","payment Count",readRowNo);

		CommonStep.scenario.log("Select multiple payment types and select PDF delivery type and click on proceed button");
		for(int i=0; i<Integer.valueOf(paymentExcelCount);i++) {

			ConfigReader.log.info("no.of rows present is:"+pay.fetchTableRowsize());
			if(pay.fetchTableRowsize()<Integer.valueOf(paymentExcelCount)) {
				paymenttab.clickPaymenToAdd();
			}
			String PaymentType=excelutil.getCellData("Payment","PaymentType"+i,readRowNo);
			String PaymentNumberValue=excelutil.getCellData("Payment","PaymentChequeNo",readRowNo);
			pay.selectPaymentType(i,PaymentType);	
			ConfigReader.log.info("***Payment Screen-Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Screen-Payment Type"+i);


			pay.enterpaymentNoBasedonType(i,PaymentNumberValue);
			ConfigReader.log.info("***Payment Screen-Payment Number based on Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Screen-Payment Number based on  Payment Type"+i);

			String totalAmount=pay.fetchTotalAmount();
			ConfigReader.log.info("totalAmount is "+totalAmount);
			String cashAmount=String.format("%.2f",(Double.valueOf(totalAmount)/Integer.valueOf(paymentExcelCount)));
			if(i==(Integer.valueOf(paymentExcelCount)-1)) {
				String RemainingAmount=pay.fetchRemainingBalance();
				ConfigReader.log.info("Payment Screen-Remaining balance is:"+RemainingAmount);
				pay.enterPaymentAmountBasedonType(i,RemainingAmount);
			}
			else {
				pay.enterPaymentAmountBasedonType(i,cashAmount);
			}

			ConfigReader.log.info("***PPayment Screen-ayment Amount based on Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Screen-Payment Amount based on  Payment Type"+i);
		}

		pay.selectPaymentReceipt(excelutil.getCellData("Payment","Payment receipt",readRowNo));
		ConfigReader.log.info("***Payment Screen-Enter Payment type and amount***");
		String mainwidow=eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		ConfigReader.log.info(commonobjects.validateInfoMsgs());
		eleutil.waitForTwoWindow(2);
		String childWindow=eleutil.SwitchtoFirstChildWindow();
		eleutil.sleepTime(4000);  //to wait for swith to 2nd window
		eleutil.saveAsFile();
		String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
		ConfigReader.log.info("fileLocation"+fileLocation);
		String DesiredPath=eleutil.checkFileExistence(fileLocation,"Payment","pdf");
		eleutil.sleepTime(4000);  //to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); //to display the file upload is completed

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(mainwidow);
	}

	@Then("User will validate message {string} {string} {string}")
	public void validateMessage(String expSucces, String expSucces2, String expSucces3) throws Exception {
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen "+ expSucces);

		ConfigReader.log.info("Message in Payment Screen "+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 1");

		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage1, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen "+ expSucces2);
		ConfigReader.log.info("Message in Payment Screen "+commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 2");
	}
	@Then("User will navigate to supplement documents to collect the document {string}")
	public void vehicleDocument(String expSucces) throws Exception {
		CommonStep.scenario.log("Collect all vehicle level documents from Supplement documents");

		dashboardpage.clickEnterpriseLink();
		commonobjects.waitForSpinner();

		dashboardpage.clickIRPLink();
		commonobjects.waitForSpinner();

		dashboardpage.clickSupplementDocuments();
		ConfigReader.log.info("Supplement Documents- Click on Supplement Documents");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Click on Supplement Documents);");

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		ConfigReader.log.info("Supplement Documents- Enter Account Number");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Enter Account Number");

		fleetpage.enterFleetNoSupplement(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));
		ConfigReader.log.info("Supplement Documents- Enter Fleet Number");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Enter Fleet Number");

		fleetpage.enterFleetYearSupplement(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
		ConfigReader.log.info("Supplement Documents- Enter Fleet Year");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Enter Fleet Year");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		suppledocs.selectIRPRequirements(excelutil.getCellData("SupplementaryDocuments","IRPRequirements",readRowNo));
		ConfigReader.log.info("Supplement Documents- Select IRP Requirements");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Select IRP Requirements");

		suppledocs.selectStatmentDoccs(excelutil.getCellData("SupplementaryDocuments","statmentdoccs",readRowNo));
		ConfigReader.log.info("Supplement Documents- Select Staement Documents");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Select Staement Documents");

		suppledocs.selectInstallmentDocs(excelutil.getCellData("SupplementaryDocuments","Installmentdocs",readRowNo));
		ConfigReader.log.info("Supplement Documents- Select Installment Documents");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Select Installment Documents");

		/*	suppledocs.selectPowerOfAttony(excelutil.getCellData("SupplementaryDocuments","Powerofattony",readRowNo));
		ConfigReader.log.info("Supplement Documents- Select Power Of Attroney");
		screenshotUtil.captureScreenshot(className,"Supplement Documents- Select Power Of Attroney");
		 */
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		ConfigReader.log.info(commonobjects.validateInfoMsgs());

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

	}
	@Then("User will navigate to payment page for second installment and validate message {string} {string}")
	public void paymentSecondInstallment(String expSucces, String expSucces2) throws Exception {

		if(installmentPlanCheckStatus.equalsIgnoreCase("true")) {
			ConfigReader.log.info("Installment Payment is required to do");
			CommonStep.scenario.log("Go to Payment - Installment Payment & serach above record & complete 2nd Installment payment");

			dashboardpage.clickEnterpriseLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickIRPLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickInstallmentPayment();
			ConfigReader.log.info("*** Installment Payment Screen-Click on Installment Payment ***");
			commonobjects.waitForSpinner();

			financepage.clickandenterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
			ConfigReader.log.info("*** Installment Payment Screen-Enter Account Number ***");
			screenshotUtil.captureScreenshot(className,"Installment Payment Screen-Enter Account Number");

			financepage.clickandenterfleet(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));
			ConfigReader.log.info("*** Installment Payment Screen-Enter Fleet Number ***");
			screenshotUtil.captureScreenshot(className,"Installment Payment Screen-Enter Fleet Number");


			financepage.clickandenterfleetyear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
			ConfigReader.log.info("*** Installment Payment Screen-Enter Fleet Year ***");
			screenshotUtil.captureScreenshot(className,"Installment Payment Screen-Enter Fleet Year");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			financepage.clickgrid();

			//Fetch Vallues from 
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchPostPaymentAccountNoLbl(),writeRowNo,financepage.fetchPostPaymentAccountNo());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetNoLbl(),writeRowNo,financepage.fetchFleetNo());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchLegalNameLbl(),writeRowNo,financepage.fetchLegalName());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetMonthYearLbl(),writeRowNo,financepage.fetchFleetMonthYear());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementNoLbl(),writeRowNo,financepage.fetchSupplementNo());

			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchDBANameLbl(),writeRowNo,financepage.fetchDBAName());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetTypeLbl(),writeRowNo,financepage.fetchFleetType());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementDescLbl(),writeRowNo,financepage.fetchSupplementDesc());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchUSDOTLbl(),writeRowNo,financepage.fetchUSDOT());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementEffectiveDateLbl(),writeRowNo,financepage.fetchSupplementEffectiveDate());

			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementStatusLbl(),writeRowNo,financepage.fetchSupplementStatus());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInvoiceDateLbl(),writeRowNo,financepage.fetchInvoiceDate());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInvoicenoLbl(),writeRowNo,financepage.fetchInvoiceNo());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchEnterpriseSystemCreditLbl(),writeRowNo,financepage.fetchEnterpriseSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInstallmentReceiptDateLbl(),writeRowNo,financepage.fetchInstallmentReceiptDate());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInstallmentPlanNumberlbl(),writeRowNo,financepage.fetchInstallmentPlanNumber());



			ArrayList<String> TableFeeType=billingtab.fetchTableFeeType();
			ArrayList<String> TableFeeAmount=billingtab.fetchTableFeeAmount();

			for(int i=0;i<TableFeeType.size();i++) {
				excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",billingtab.fetchBillingGridFeeType()+i,writeRowNo,TableFeeType.get(i));
				excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",billingtab.fetchBillingGridFeeAmount()+i,writeRowNo,TableFeeAmount.get(i));
			}	


			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			//payment Verification
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			//Fetch Values from 
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchMCECustomerIdLbl(),writeRowNo,pay.fetchMCECustomerId());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchLegalNameLbl(),writeRowNo,pay.fetchLegalName());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",financepage.fetchDBANameLbl(),writeRowNo,financepage.fetchDBAName());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchEnterpriseSystemCreditLbl(),writeRowNo,pay.fetchEnterpriseSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchIRPSystemCreditLbl(),writeRowNo,pay.fetchIRPSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchIftaSystemCreditLbl(),writeRowNo,pay.fetchIftaSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchOpaSystemCreditLbl(),writeRowNo,pay.fetchOpaSystemCredit());

			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchTotalAmountDueLbl(),writeRowNo,pay.fetchTotalAmountDue());

			ArrayList<String> tableHeadervalues=pay.fetchTableHeaders();
			ArrayList<String> tableInvoice=pay.fetchTableInvoiceDetails();
			for(int i=0;i<tableInvoice.size();i++) {
				excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",tableHeadervalues.get(i),writeRowNo,tableInvoice.get(i));
			}

			String paymentExcelCount=excelutil.getCellData("Installment Payment","payment Count",readRowNo);
			for(int i=0; i<Integer.valueOf(paymentExcelCount);i++) {
				ConfigReader.log.info("no.of rows present is:"+pay.fetchTableRowsize());
				if(pay.fetchTableRowsize()<Integer.valueOf(paymentExcelCount)) {
					paymenttab.clickPaymenToAdd();
				}
				String paymentType=excelutil.getCellData("Payment","PaymentType"+i,readRowNo);
				String paymentNumberValue=excelutil.getCellData("Payment","PaymentChequeNo",readRowNo);
				pay.selectPaymentType(i,paymentType);	
				ConfigReader.log.info("***Installment Payment-Payment Type***");
				screenshotUtil.captureScreenshot(className,"Payment Type"+i);


				pay.enterpaymentNoBasedonType(i,paymentNumberValue);
				ConfigReader.log.info("***Installment Payment-Payment Number based on Payment Type***");
				screenshotUtil.captureScreenshot(className,"Payment Number based on  Payment Type"+i);

				String totalAmount=pay.fetchTotalAmount();
				ConfigReader.log.info("Installment Payment-totalAmount is "+totalAmount);
				String cashAmount=String.format("%.2f",(Double.valueOf(totalAmount)/Integer.valueOf(paymentExcelCount)));
				if(i==(Integer.valueOf(paymentExcelCount)-1)) {
					String RemainingAmount=pay.fetchRemainingBalance();
					ConfigReader.log.info("Installment Payment-Remaining balance is:"+RemainingAmount);
					pay.enterPaymentAmountBasedonType(i,RemainingAmount);
				}
				else {
					pay.enterPaymentAmountBasedonType(i,cashAmount);
				}

				ConfigReader.log.info("***Installment Payment-Payment Amount based on Payment Type***");
				screenshotUtil.captureScreenshot(className,"Payment Amount based on  Payment Type"+i);
			}

			pay.selectPaymentReceipt(excelutil.getCellData("Payment","Payment receipt",readRowNo));
			ConfigReader.log.info("***Installment Payment-Enter Payment type and amount***");
			String mainwidow=eleutil.GetParentWindow();

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			//Payment 
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			}
			catch(Throwable e) {
				error.addError(e);
			}

			CommonStep.scenario.log("Message in Installment Payment Screen "+ expSucces);

			ConfigReader.log.info("Installment Payment-Message in Installment Payment Screen "+commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className,"Message in Installment Payment Screen 1");

			String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
			try {
				Assert.assertEquals(actualmessage1, expSucces2);
			}
			catch(Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Installment Payment Screen "+ expSucces2);
			ConfigReader.log.info("Installment Payment-Message in Installment Payment Screen "+commonobjects.fetchErrorMessage(expSucces2));
			screenshotUtil.captureScreenshot(className,"Message in Installment Payment Screen 2");

			eleutil.waitForTwoWindow(2);
			String childWindow=eleutil.SwitchtoFirstChildWindow();
			eleutil.sleepTime(4000);  //to wait for switch to 2nd window
			eleutil.saveAsFile();
			String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
			ConfigReader.log.info("Installment Payment-fileLocation"+fileLocation);
			String DesiredPath=eleutil.checkFileExistence(fileLocation,"InstallmentPayment","pdf");
			eleutil.sleepTime(4000);  //to wait for the PDF Load completely
			eleutil.uploadFile(DesiredPath);
			eleutil.sleepTime(4000); //to display the file upload is completed

			eleutil.closeSpecificWindow(childWindow);
			eleutil.SwitchSpecificWindow(mainwidow);

		}

	}
	@Then("User will validate all the inquiries and report with respect to the flow.")
    public void inquiriesAndReports() throws Exception {
            String childWindow=null;
            Boolean flag=false;
            String expiryYearExcel=null;
            String ParentWindow=eleutil.GetParentWindow();

    /*    dashboardpage.clickServiceLink();
            commonobjects.waitForSpinner();

            dashboardpage.clickIRPLink();
            commonobjects.waitForSpinner();*/
            
            dashboardpage.clickFleetEnquiry(); 
            ConfigReader.log.info("FleetEnquiry-Click on Fleet Enquiry");

            childWindow= eleutil.SwitchtoFirstChildWindow();

            fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
            ConfigReader.log.info("FleetEnquiry-Enter Account Number");
            
            fleetpage.enterFleetyear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
            ConfigReader.log.info("FleetEnquiry-Enter Account Number");
            
            commonobjects.clickProceed();
            screenshotUtil.captureScreenshot(className,"FleetEnquiry-Click on proceed");
            
            flag= enquiry.fleetenquiryvaluevalidation(excelutil.getCellData("Enquiry","Fleet EXP.MM/YYYY",readRowNo)); 

            eleutil.closeSpecificWindow(childWindow);
            eleutil.SwitchSpecificWindow(ParentWindow);
            
            try {
                   Assert.assertEquals(flag, true);
            }
            catch(Throwable e) {
                   error.addError(e);
            }

            dashboardpage.clickSupplementEnquiry(); 
            ConfigReader.log.info("SupplementEnquiry-Click on Supplement Enquiry");

            childWindow= eleutil.SwitchtoFirstChildWindow();
            
            enquiry.enterSupplementEnquiryAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
            ConfigReader.log.info("SupplementEnquiry-Enter Account Number");
            
            enquiry.enterSupplementEnquiryExpYear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
            ConfigReader.log.info("SupplementEnquiry-Enter Account Number");
            
            commonobjects.clickProceed();
            screenshotUtil.captureScreenshot(className,"SupplementEnquiry-Click on proceed");
            
            flag=enquiry.supplementEnquiryvaluevalidation(excelutil.getCellData("Enquiry","Fleet EXP.MM/YYYY",readRowNo));
            
            eleutil.closeSpecificWindow(childWindow);
            eleutil.SwitchSpecificWindow(ParentWindow);
            
            try {
                   Assert.assertEquals(flag, true);
            }
            catch(Throwable e) {
                   error.addError(e);
            }
            expiryYearExcel=excelutil.getCellData("Enquiry","Fleet EXP.MM/YYYY",readRowNo);
            
            dashboardpage.clickVehicleEnquiry();
            ConfigReader.log.info("Vehicle Enquiry-Click on Vehicle Enquiry");
            
            childWindow= eleutil.SwitchtoFirstChildWindow();
            
            fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
            ConfigReader.log.info("Vehicle Enquiry-Enter Account Number");
            
            enquiry.enterVehicleEnquiryExpYear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
            ConfigReader.log.info("Vehicle-Enter Expiry Year");
            
            commonobjects.clickProceed();
            screenshotUtil.captureScreenshot(className,"Vehicle Enquiry-Click on proceed");
            for(int i=1;i<=Integer.valueOf(renewVehiclesCount); i++) {
                   String ActualVIN=enquiry.fetchVehicleEnquiryVIN(String.valueOf(i),expiryYearExcel);
                   try {
                           Assert.assertEquals(enquiry.vehicleEnquiryTableRowCount(), renewVehiclesCount);
                   }
                   catch(Throwable e) {
                           error.addError(e);
                   }
            }
                   eleutil.closeSpecificWindow(childWindow);
                    eleutil.SwitchSpecificWindow(ParentWindow);
                   
                    dashboardpage.clickVehicleSupplementEnquiry(); 
                   ConfigReader.log.info("SupplementEnquiry-Click on Supplement Enquiry");

                   childWindow= eleutil.SwitchtoFirstChildWindow();
                   
                   fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
                   ConfigReader.log.info("SupplementEnquiry-Enter Account Number");
                   
                    enquiry.enterVehicleSupplementEnquiryExpYear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
                   ConfigReader.log.info("SupplementEnquiry-Enter Account Number");
                   
                   commonobjects.clickProceed();
                    screenshotUtil.captureScreenshot(className,"SupplementEnquiry-Click on proceed");
                   for(int i=1;i<=Integer.valueOf(renewVehiclesCount); i++) {
                           enquiry.FetchVehicleSupplementEnquiryVIN(String.valueOf(i),expiryYearExcel);
            
                   try {
                           Assert.assertEquals(enquiry.vehicleEnquiryTableRowCount(), renewVehiclesCount);
                   }
                   catch(Throwable e) {
                           error.addError(e);
                   }
                   }
                   eleutil.closeSpecificWindow(childWindow);
                    eleutil.SwitchSpecificWindow(ParentWindow);
    }


}
