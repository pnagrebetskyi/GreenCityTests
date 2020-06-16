package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopGuestComponent {
	//
	private WebDriver driver;
	//
	private WebElement signinLink;
	private WebElement signupLink;

	public TopGuestComponent(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		// init elements
		signinLink = driver.findElement(By.cssSelector("li.sign-in-link.tertiary-global-button a"));
		signupLink = driver.findElement(By.cssSelector("li.sign-up-link.ng-star-inserted div"));
	}

	// Page Object

	// signinLink
    
	public WebElement getSigninLink() {
        return signinLink;
    }

    public String getSigninLinkText() {
        return getSigninLink().getText();
    }
    
    public LoginPage clickSignInLink() {
        getSigninLink().click();
        return new LoginPage(driver);
    }
    
	// signupLink
	
	public WebElement getSignupLink() {
        return signupLink;
    }

    public String getSignupLinkText() {
        return getSignupLink().getText();
    }
    
    public RegisterPage clickSignupLink() {
        getSignupLink().click();
        return new RegisterPage(driver);
    }

	// Functional

	// Business Logic
}
