package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;


public class Financepage {
	
	private  WebDriver driver;

	public Financepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//label[@for='AccountNo']") WebElement postPaymentAccountNoLbl;
	@FindBy(xpath="//input[@id='AccountNo']") WebElement postPaymentAccountNo;
	@FindBy(xpath="//label[@for='FleetNbr']") WebElement installmentFleetNoLbl;
	@FindBy(xpath="//input[@id='FleetNbr']") WebElement installmentFleetNo;
	@FindBy(xpath="//label[@for='LegalName']") WebElement installmentLegalNameLbl;
	@FindBy(xpath="//input[@id='LegalName']") WebElement installmentLegalName;
	@FindBy(xpath="//label[@for='FltMonthYear']") WebElement postPaymentFleerMonthYearLbl ;
	@FindBy(xpath="//input[@id='FltMonthYear']") WebElement postPaymentFleerMonthYear;
	@FindBy(xpath="//label[@for='SupplementNo']") WebElement installmentSupplementNoLbl;
	@FindBy(xpath="//input[@id='SupplementNo']") WebElement installmentSupplementNo;
	@FindBy(xpath="//label[@for='DBAName']") WebElement installmentDBANameLbl;
	@FindBy(xpath="//input[@id='DBAName']") WebElement installmentDBAName;
	@FindBy(xpath="//label[@for='CMVFleetType']") WebElement postPaymentFleetTypeLbl;
	@FindBy(xpath="//input[@id='CMVFleetType']") WebElement postPaymentFleetType;
	@FindBy(xpath="//label[@for='SupplementDesc']") WebElement installmentSupplementDescLbl;
	@FindBy(xpath="//input[@id='SupplementDesc']") WebElement installmentSupplementDesc;
	@FindBy(xpath="//label[@for='UsdotNbr']") WebElement installmentUSDOTLbl;
	@FindBy(xpath="//input[@id='UsdotNbr']") WebElement installmentUSDOT;
	@FindBy(xpath="//label[@for='SupplementEffectiveDate']") WebElement postPaymentSupplementEffectiveDateLbl;
	@FindBy(xpath="//input[@id='SupplementEffectiveDate']") WebElement postPaymentSupplementEffectiveDate;
	@FindBy(xpath="//label[@for='SupplementStatus']") WebElement installmentSupplementStatusLbl;
	@FindBy(xpath="//input[@id='SupplementStatus']") WebElement installmentSupplementStatus;
	@FindBy(xpath="//label[@for='InvoiceDate']") WebElement installmentInvoiceDateLbl;
	@FindBy(xpath="//input[@id='InvoiceDate']") WebElement installmentInvoiceDate;
	@FindBy(xpath="//label[@for='invoiceNbr']") WebElement installmentinvoiceNbrLbl;
	@FindBy(xpath="//input[@id='invoiceNbr']") WebElement installmentInvoiceNbr;
	@FindBy(xpath="//label[@for='EnterpriseEscrow']") WebElement postPaymentEnterpriseSystemCreditLbl;
	@FindBy(xpath="//input[@id='EnterpriseEscrow']") WebElement postPaymentEnterpriseSystemCredit;
	@FindBy(xpath="//label[@for='ReceiptDate']") WebElement installmentReceiptDateLbl;
	@FindBy(xpath="//input[@id='ReceiptDate']") WebElement installmentReceiptDate;
	@FindBy(xpath="//label[@for='InstallmentPlanNumber']") WebElement installmentInstallmentPlanNumberLbl;
	@FindBy(xpath="//input[@id='InstallmentPlanNumber']") WebElement installmentInstallmentPlanNumber;
	
	@FindBy(xpath="//th[@class='sorting_disabled']") List<WebElement> FeeHeaders;
	@FindBy(xpath="//tr[@role='row']//th[not(contains(@class,'hidden')) and (contains(@class,'Align'))]") List<WebElement> feeRow;
	
	@FindBy(xpath="//a[@title='Finance']") WebElement dashboardFinanceTab;
	@FindBy(xpath="//a[@title='Post Payment']") WebElement financePostPayment;
	
	@FindBy(xpath="//input[@id='btnSearch']") WebElement postPaymentSearch;
	@FindBy(xpath="//a[@id='lnkGridSelectgvPostPayment']") WebElement postPaymentCartId;
	@FindBy(xpath="//input[@id='AccountNo']") WebElement postPaymentMceId;
	
	//installment payment
	
	
	@FindBy(xpath="//a[@title='Services']") WebElement installmentService;
	@FindBy(xpath="//a[@title='IRP']") WebElement installmentIRP;
	@FindBy(xpath="//input[@id='AccountNo']") WebElement installmentAccountNo;
	
	@FindBy(xpath="//input[@id='FltExpYear']") WebElement installmentFleetYear;
	@FindBy(xpath="//a[@id='lnkGridSelectCustomerGrid']") WebElement installmentGrid;
	@FindBy(xpath="//select[@id='paymentVos_0__PayType']") WebElement installmentPayType;
	@FindBy(xpath="//input[@id='paymentVos_0__PaymentAmount']") WebElement installmentPaymentCash;
	@FindBy(xpath="//select[@id='outPutModelSelect']") WebElement intallmentPdf;
	
	
	public void clickfinance() {
		ElementUtil.clickElement(dashboardFinanceTab);
	}
	public void clickpostpayment() {
		ElementUtil.clickElementUsingActions(financePostPayment);
	}
	
	
	public void enterMCEid(String MCEIDvalue) {
		ElementUtil.clickElement(postPaymentMceId);
		ElementUtil.webEditTxtChange(postPaymentMceId,MCEIDvalue);
	}
	
	
	public void clicksearch() {
		ElementUtil.clickElement(postPaymentSearch);
	}
	
	public void clickoncartid() {
		ElementUtil.clickElement(postPaymentCartId);
	}
	public void clickservice() {
		ElementUtil.clickElement(installmentService);
	}
	public void clickIRP() {
		ElementUtil.clickElement(installmentIRP);
	}
	public void selectpaymenttype(String selectValue) {
		ElementUtil.selectFromDropdownByVisibleText(installmentPayType, selectValue); //Cash,Check,Certified Check,E-check,Credit Card,Wire Transfer,EFT,Enterprise System Credit,IRP System Credit,IFTA System Credit,OPA System Credit,IFTA Bond	
	}
	public void entercashamount(String installmenttypevalue) {	
	ElementUtil.webEditTxtChange(installmentPaymentCash,installmenttypevalue);
	}
	public void clickreceipt(String selectValue) {
	ElementUtil.selectFromDropdownByValue(intallmentPdf, selectValue);
}
	
	public void clickandenterAccountNo(String accountnovalue) {
		ElementUtil.webEditTxtChange(installmentAccountNo,accountnovalue);
	}
	public void clickandenterfleet(String fleetnovalue) {
		ElementUtil.webEditTxtChange(installmentFleetNo,fleetnovalue);
	}
	public void clickandenterfleetyear(String yearvalue) {
		ElementUtil.webEditTxtChange(installmentFleetYear,yearvalue);
	}
	public void clickgrid() throws InterruptedException {
		ElementUtil.clickElement(installmentGrid);
	}

	
	
	//Fetch values
	public ArrayList<String> fetchTableHeader() {
		ElementUtil.waitUntilElementsVisible(FeeHeaders);
		ArrayList<String> headersArray = new ArrayList<String>();
		for(int i=0;i<FeeHeaders.size();i++) {
			headersArray.add(ElementUtil.FetchTextBoxValuewithText(FeeHeaders.get(i)));
		}
		return headersArray;
	}
	public ArrayList<String> fetchTableRowValues() {
		ElementUtil.waitUntilElementsVisible(feeRow);
		ArrayList<String> jurisArray = new ArrayList<String>();
		for(int i=0;i<feeRow.size();i++) {
			jurisArray.add(ElementUtil.FetchTextBoxValuewithText(feeRow.get(i)));
		}
		return jurisArray;
	}
	
	public String fetchPostPaymentAccountNoLbl() {
		return ElementUtil.FetchTextBoxValuewithText(postPaymentAccountNoLbl);
	}
	public String fetchPostPaymentAccountNo() {
		return ElementUtil.FetchTextBoxValuewithattribute(postPaymentAccountNo,"value");
	}
	public String fetchFleetNoLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentFleetNoLbl);
	}
	public String fetchFleetNo() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentFleetNo,"value");
	}
	public String fetchLegalNameLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentLegalNameLbl);
	}
	public String fetchLegalName() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentLegalName,"value");
	}
	public String fetchFleetMonthYearLbl() {
		return ElementUtil.FetchTextBoxValuewithText(postPaymentFleerMonthYearLbl );
	}
	public String fetchFleetMonthYear() {
		return ElementUtil.FetchTextBoxValuewithattribute(postPaymentFleerMonthYear,"value");
	}
	public String fetchSupplementNoLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentSupplementNoLbl);
	}
	public String fetchSupplementNo() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentSupplementNo,"value");
	}
	public String fetchDBANameLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentDBANameLbl);
	}
	public String fetchDBAName() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentDBAName,"value");
	}
	public String fetchFleetTypeLbl() {
		return ElementUtil.FetchTextBoxValuewithText(postPaymentFleetTypeLbl);
	}
	public String fetchFleetType() {
		return ElementUtil.FetchTextBoxValuewithattribute(postPaymentFleetType,"value");
	}
	public String fetchSupplementDescLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentSupplementDescLbl);
	}
	public String fetchSupplementDesc() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentSupplementDesc,"value");
	}
	public String fetchUSDOTLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentUSDOTLbl);
	}
	public String fetchUSDOT() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentUSDOT,"value");
	}
	public String fetchSupplementEffectiveDateLbl() {
		return ElementUtil.FetchTextBoxValuewithText(postPaymentSupplementEffectiveDateLbl);
	}
	public String fetchSupplementEffectiveDate() {
		return ElementUtil.FetchTextBoxValuewithattribute(postPaymentSupplementEffectiveDate,"value");
	}
	public String fetchSupplementStatusLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentSupplementStatusLbl);
	}
	public String fetchSupplementStatus() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentSupplementStatus,"value");
	}
	public String fetchInvoiceDateLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentInvoiceDateLbl);
	}
	public String fetchInvoiceDate() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentInvoiceDate,"value");
	}
	public String fetchInvoicenoLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentinvoiceNbrLbl);
	}
	public String fetchInvoiceNo() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentInvoiceNbr,"value");
	}
	public String fetchEnterpriseSystemCreditLbl() {
		return ElementUtil.FetchTextBoxValuewithText(postPaymentEnterpriseSystemCreditLbl);
	}

	public String fetchEnterpriseSystemCredit() {
		return ElementUtil.FetchTextBoxValuewithattribute(postPaymentEnterpriseSystemCredit,"value");
	}
	public String fetchInstallmentReceiptDateLbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentReceiptDateLbl);
	}

	public String fetchInstallmentReceiptDate() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentReceiptDate,"value");
	}
	public String fetchInstallmentPlanNumberlbl() {
		return ElementUtil.FetchTextBoxValuewithText(installmentInstallmentPlanNumberLbl);
	}

	public String fetchInstallmentPlanNumber() {
		return ElementUtil.FetchTextBoxValuewithattribute(installmentInstallmentPlanNumber,"value");
	}


}