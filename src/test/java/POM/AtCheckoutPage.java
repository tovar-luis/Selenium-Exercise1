package POM;

import org.openqa.selenium.By;

import selenium.ClsWebElements;

public class AtCheckoutPage extends ClsWebElements{

	//Locators
	private By checkoutAsGuestBtn = By.xpath("//label[text()='Checkout as Guest']");
	private By continueCheckoutBtn = By.id("onepage-guest-register-button");
	
	private By firstNameInput = By.id("billing:firstname");
	private By lastNameInput = By.id("billing:lastname");
	private By emailInput = By.id("billing:email");
	private By addressInput = By.id("billing:street1");
	private By cityInput = By.id("billing:city");
	private By regionInput = By.id("billing:region");
	private By zipInput = By.id("billing:postcode");
	private By countrySelect = By.id("billing:country_id");
	private By telephoneInput = By.id("billing:telephone");
	private By continueBillingBtn = By.xpath("//button[@onclick='billing.save()']");
	
	private By continueShippingBtn = By.xpath("//button[@onclick='shippingMethod.save()']");
	
	private By checkMoneyOrderBtn = By.xpath("//label[@for='p_method_checkmo']");
	private By continuePaymentBtn = By.xpath("//button[@onclick='payment.save()']");
	
	private By placeOrderBtn = By.xpath("//button[@title='Place Order']");
	
	private By successMsg = By.xpath("//h1[text()='Your order has been received.']");
	 
	//Methods
	public String checkTitle() {
		return getTitle();
	}
	
	public void selectCheckoutAsGuest() {
		WaitForLoad();
		WaitForElementClickable(checkoutAsGuestBtn);
		Click(checkoutAsGuestBtn);
	}
	
	public void clickContinueCheckout() {
		WaitForLoad();
		WaitForElementClickable(continueCheckoutBtn);
		Click(continueCheckoutBtn);
	}
	
	
	public void fillBillingInfo() {
		WaitForLoad();
		WaitForElement(firstNameInput);
		SendKeys(firstNameInput, "Luis");
		
		WaitForLoad();
		WaitForElement(lastNameInput);
		SendKeys(lastNameInput, "Tovar");
		
		WaitForLoad();
		WaitForElement(emailInput);
		SendKeys(emailInput, "intern6@agilethought.com");
		
		WaitForLoad();
		WaitForElement(addressInput);
		SendKeys(addressInput, "Calle 15");
		
		WaitForLoad();
		WaitForElement(cityInput);
		SendKeys(cityInput, "Mérida");
		
		WaitForLoad();
		WaitForElement(zipInput);
		SendKeys(zipInput, "97139");
		
		WaitForLoad();
		WaitForElement(telephoneInput);
		SendKeys(telephoneInput, "9995711401");
		
		WaitForLoad();
		WaitForElement(countrySelect);
		SelectItem(countrySelect, "BYVALUE", "MX");
		
		WaitForLoad();
		WaitForElement(regionInput);
		SendKeys(regionInput, "Yucatán");
		
	}
	
	public void clickContinueBilling() {
		WaitForLoad();
		WaitForElementClickable(continueBillingBtn);
		Click(continueBillingBtn);
	}
	
	public void clickContinueShipping() {
		WaitForLoad();
		WaitForElementClickable(continueShippingBtn);
		Click(continueShippingBtn);
	}
	
	public void selectCheckMoneyOrder() {
		WaitForLoad();
		WaitForElementClickable(checkMoneyOrderBtn);
		Click(checkMoneyOrderBtn);
	}
	
	public void clickContinuePayment() {
		WaitForLoad();
		WaitForElementClickable(continuePaymentBtn);
		Click(continuePaymentBtn);
	}
	
	public void clickPlaceOrder() {
		WaitForLoad();
		WaitForElementClickable(placeOrderBtn);
		Click(placeOrderBtn);
	}
	
	public boolean checkIfSuccess() {
		return checkIfExists(successMsg);
	}
			
}