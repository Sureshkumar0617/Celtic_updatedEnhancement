package com.celtic.automation.cmcs.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class CommonObjects {
	
	private  WebDriver driver;
	public CommonObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(@class,'myTablecontent')]/em[contains(@class,'spinner')]") WebElement loadSpinner;
	@FindBy(xpath="//div[contains(@class,'validation')]//span") List<WebElement> errorMsgsList;
	@FindBy(xpath="(//div[@class='PageHeader row']//h3)[1]") WebElement leftHdr;  //Account Details,FleetDetails ,Distance Details, Weight Group Selection Details, 
	//Renewal Vehicle Processing, Vehicle Details
	@FindBy(xpath="(//div[@class='PageHeader row']//h3)[2]") WebElement rightHdr; //Renew Fleet
	
	//get values by using element.getattribute("value")
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_RegistrantName']") WebElement registrationValue;
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_AccountNO']") WebElement AccountNovalue;
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_FleetNo']") WebElement fleetNoValue;
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_FleetExpiryMonth']") WebElement fleetExpMonthValue;
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_FleetExpiryYear']") WebElement fleetExpYearValue;
		@FindBy(xpath="//div[@id='myPopover']/ul//input[@id='supplementVM_SupplementNo']") WebElement supplementNoValue;
		
		//...+
		@FindBy(xpath="//div[@id='myPopover']/ul//li[@tabindex='0']") WebElement dotsTab;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_AccountNO']") WebElement collapseAccountNoLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_AccountNO']/preceding-sibling::span[@class='view-text']") WebElement collapseAccountNoValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_FleetNo']") WebElement collapseFleetNoLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_FleetNo']/following-sibling::span[@class='font-medium']") WebElement collapseFleetNoValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//span[@class='confirmPageLabel']") WebElement collapseFleetExpyearLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//span[@class='confirmPageLabel']/following-sibling::span[@class='font-medium']") WebElement collapseFleetExpYearValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_SupplementNo']") WebElement collapseSupplementNoLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_SupplementNo']/following-sibling::span[@class='font-medium']") WebElement collapseSupplementNoValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_Tin']") WebElement collapseSupplementTINLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_AccountNO']/preceding-sibling::span[@class='view-text']") WebElement collapseSupplementTinValue; 
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_USDOTNo']") WebElement collapseUSDOTLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_USDOTNo']/preceding-sibling::span[@class='view-text']") WebElement collapseUsdotValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_RegistrantName']") WebElement collapseRegistrationNameLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_RegistrantName']/following-sibling::span[@class='font-medium']") WebElement collapseRegistrationNameValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_DBA']") WebElement collapseDBANameLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_DBA']/preceding-sibling::span[@class='view-text']") WebElement collapseDBANameValue;
		
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_FleetType']") WebElement collapseFleetTypeLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_FleetType']/preceding-sibling::span[@class='view-text']") WebElement collapseFleetTypeValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_SupplementEffDate']") WebElement collapseSupplementEfeDateLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//input[@id='supplementVM_SupplementEffDate']/preceding-sibling::span[@class='view-text']") WebElement collapseSupplementEfeDateValue;
		
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_SupplementDesc']") WebElement collapseSupplementDescLbl;
		@FindBy(xpath="//div[@class='popoverWrapperList']//label[@for='supplementVM_SupplementDesc']/following-sibling::span[@class='font-medium']") WebElement collapseSupplementDescValue;
		
		
		//Comments
		@FindBy(xpath="//div[@class='bottom-comments']//a[contains(text(),'omments')]") WebElement commentsSubHdr;
		@FindBy(xpath="//label[@for='editComment_CommentTxtcommentsVM1']") WebElement commentLbl;
		@FindBy(xpath="//textarea[@id='editComment_CommentTxtcommentsVM1']") WebElement commentTxt;
		@FindBy(xpath="//label[@for='DefaultAccessLevelcommentsVM1']") WebElement accessLevelLbl;
		@FindBy(xpath="//select[@id='DefaultAccessLevelcommentsVM1']") WebElement accessLeveldd;
		@FindBy(xpath="//div[@class='delete-comments']/label") WebElement deleteAllowedCommentLbl;
		@FindBy(xpath="//input[@id='editComment_DelAllowedcommentsVM1']") WebElement deleteAllowedCommentChk;
		
		@FindBy(xpath="//input[@id='addUpdateCommentBtncommentsVM1']") WebElement addorUpdateCommentBtn;
		@FindBy(xpath="//input[@id='refreshCommentBtncommentsVM1']") WebElement refreshCommentBtn;
		
		//comment table section
		@FindBy(xpath="//tr[@class='myCommentTableHeader']/th[1]") WebElement commentEditDeleteTbl;
		@FindBy(xpath="//tr[@class='myCommentTableHeader']/th[2]") WebElement commentTextTbl;
		@FindBy(xpath="//tr[@class='myCommentTableHeader']/th[3]") WebElement commentTimeStampTbl;
		@FindBy(xpath="//tr[@class='myCommentTableHeader']/th[4]") WebElement commentuserIdTbl;
		@FindBy(xpath="//a[contains(@href,'Editcomment')]") WebElement commentEdit;
		@FindBy(xpath="//a[contains(@href,'Deletecomment')]") WebElement commentDelete;
		@FindBy(xpath="//td[contains(@id,'CommentTxt_0')]") WebElement commentEnteredTextValue;
		@FindBy(xpath="//td[contains(@id,'LastUpdT')]") WebElement commentTimestampValue;
		@FindBy(xpath="//td[contains(@id,'Userid')]") WebElement commentUserIdValue;
		
		
		@FindBy(xpath="//input[@id='btnProceed']") WebElement proceedBtn;
		@FindBy(xpath="//input[@id='btnRefresh']") WebElement refershBtn;
		@FindBy(xpath="//input[@id='btnQuit']") WebElement quitBtn;
		@FindBy(xpath="//input[@id='btnDone']") WebElement doneBtn;
		@FindBy(xpath="//input[@id='btnBack']") WebElement backBtn;
		@FindBy(xpath="//input[@id='btnHelp']") WebElement helpBtn;
		@FindBy(xpath="//input[@id='btnCancel']") WebElement cancelBtn;
		@FindBy(xpath="//input[@id='btnConfirmCancel']") WebElement confirmCancelBtn;
		@FindBy(xpath="//input[@id='btnGoToWeightGroupSelection']") WebElement weightGroupSelectionBtn;
		@FindBy(xpath="//input[@id='btnDeleteWeightGroup']") WebElement deleteWeightGroupBtn;
		
		@FindBy(xpath="//ul[@class='errorMessage']//span") WebElement errorMessage;
		@FindBy(xpath="//div[contains(@class,'alert-info')]") WebElement informationMessage;
		@FindBy(xpath="//div[contains(@class,'alert-info')]//li//span") List<WebElement> infoMsgs;
		//Reinstatement
	
		
		public void expandCommentSection() throws Exception {
			if(ElementUtil.FetchTextBoxValuewithattribute(commentsSubHdr,"aria-expanded").equalsIgnoreCase("false")) {
			ElementUtil.scrollToViewAndClickElement(commentsSubHdr);
			}
		}	
		public void waitForSpinner() {
			ElementUtil.waitUntilElementInvisible(loadSpinner, Duration.ofSeconds(150));
		}
		public void enterComments(String commentsValue) {
			ElementUtil.webEditTxtChange(commentTxt,commentsValue);
		}
		public void selectAccesslevel(String accessLevelValue) {
			ElementUtil.selectFromDropdownByVisibleText(accessLeveldd,accessLevelValue);
		}
		public void checkDeleteAllowed() {
			ElementUtil.webCheckON(deleteAllowedCommentChk);
		}
		public void clickAddOrUpdateComment() {
			ElementUtil.clickElement(addorUpdateCommentBtn);
		}
		public void clickClearComment() {
			ElementUtil.clickElement(refreshCommentBtn);
		}
		public void clickProceed() throws InterruptedException {
			ElementUtil.clickElement(proceedBtn);
		}
		public void clickConfirmCancel() {
			ElementUtil.clickElement(confirmCancelBtn);
		}
		public void clickRefresh() {
			ElementUtil.clickElement(refershBtn);
		}
		public void clickQuit() {
			ElementUtil.waitUntilElementClickable(quitBtn);
			ElementUtil.clickElement(quitBtn);
		}
		public void clickBack() {
			ElementUtil.clickElement(backBtn);
		}
		public void clickCancelBtn() {
			ElementUtil.clickElement(cancelBtn);
		}
		public void clickDoneBtn() throws Exception {
			ElementUtil.clickElementIgnoreStaleElementReferenceException(doneBtn);
		}
		public void validateErrorMessage(String errorMessageValue) {
			
			if(ElementUtil.FetchTextBoxValuewithText(errorMessage).contains(errorMessageValue))
				assert true;
			
		}
		public String FetchErrorMessage() {
			return ElementUtil.FetchTextBoxValuewithText(errorMessage);
			
		}
		public void validateInfoMessage(String inforMsg) {
			ElementUtil.highlightElement(driver, informationMessage);
			if(ElementUtil.FetchTextBoxValuewithText(informationMessage).equalsIgnoreCase(inforMsg)) {
			assert true;
			}
		}
	public ArrayList<String> validateInfoMsgs() {
		ArrayList<String> infoMsgsArray = new ArrayList<String>();
		if(!(errorMsgsList.isEmpty())) {
			for(WebElement ele:errorMsgsList) {
				infoMsgsArray.add(ElementUtil.FetchTextBoxValuewithText(ele));	
				ElementUtil.highlightElement(driver, ele);
			}
		}
		return infoMsgsArray;
	}
	public void provideComments(String comments) throws Exception {
		expandCommentSection();
		enterComments(comments);
		checkDeleteAllowed();
		clickAddOrUpdateComment();
	}
	public String fetchErrorMessage(String ExpectederrorMessage) {
		String actualErrorMessage=null;
		String actualCumExpected=null;
		
		for(WebElement ele:errorMsgsList) {
			actualErrorMessage	=ElementUtil.FetchTextBoxValuewithText(ele);
			if(actualErrorMessage.equalsIgnoreCase(ExpectederrorMessage)) {
				actualCumExpected=actualErrorMessage;
				ElementUtil.highlightElement(driver, ele);
				return actualCumExpected;
			}
		}
		return actualCumExpected;
		
	}

}
