package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManualLoginComponent extends LoginComponent {
    private By emailField = By.id("email");
    private By passwordField = By.id("password");

    private By signInButton = By.xpath("//button[@type='submit' and @class='primary-global-button']");

    private By emailValidationError = By.cssSelector(".validation-email-error");
    private By passwordValidationError = By.cssSelector(".validation-password-error");
    private By wrongEmailOrPassError = By.cssSelector(".alert-general-error.ng-star-inserted");

    private By passwordFieldWrapper = By.cssSelector(".password-input-btn-show-hide");
    private By successEmailValidation = By.cssSelector(".successful-email-validation");
    private By unSuccessEmailValidation = By.cssSelector(".alert-email-validation");
    private By successfulPasswordValidation = By.cssSelector(".successful-password-validation");
    private By unSuccessPasswordValidation = By.cssSelector(".alert-password-validation");

    private WebDriverWait wait;

    public ManualLoginComponent(WebDriver driver) {
        super(driver);
    }

    //Email Field
    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    //Password field
    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    public boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }
    public void clickPasswordField() {
        getPasswordField().click();
    }

    public WebElement getPasswordFieldWrapper() {
        return driver.findElement(passwordFieldWrapper);
    }

    //Sign in button
    public WebElement getSignInButton() {
        return driver.findElement(signInButton);
    }

    public boolean isDisplayedSignInButton() {
        return getSignInButton().isDisplayed();
    }

    public boolean isEnabledSignInButton() {
        return getSignInButton().isEnabled();
    }

    public ManualLoginComponent clickSignInButton() {
        getSignInButton().click();
        return this;
    }

    //Email Or Password Error
    public WebElement getWrongEmailOrPassError() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(wrongEmailOrPassError));
        return driver.findElement(wrongEmailOrPassError);
    }

    public String getWrongEmailOrPassErrorText() {
        return getWrongEmailOrPassError().getText();
    }


    //Email Validator
    public WebElement getEmailValidationError() {
        return driver.findElement(emailValidationError);
    }

    public boolean IsDisplayedEmailValidationError() {
        return getEmailValidationError().isDisplayed();
    }

    public String getEmailValidationErrorText() {
        return getEmailValidationError().getText();
    }

    public boolean isSuccessfulEmailValidation() {
        return driver.findElement(successEmailValidation).isDisplayed();
    }

    public boolean isUnsuccessfulEmailValidation() {
        return driver.findElement(unSuccessEmailValidation).isDisplayed();
    }

    //Password Validator
    public WebElement getPasswordValidationError() {
        return driver.findElement(passwordValidationError);
    }

    public boolean IsPasswordValidationError() {
        return getPasswordValidationError().isDisplayed();
    }

    public String getPasswordValidationErrorText() {
        return getPasswordValidationError().getText();
    }

    public boolean isSuccessfulPasswordValidation() {
        return driver.findElement(successfulPasswordValidation).isDisplayed();
    }

    public boolean isUnsuccessfulPasswordValidation() {
        return driver.findElement(unSuccessPasswordValidation).isDisplayed();
    }

    //Fill email field
    public ManualLoginComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    //Fill password field
    public ManualLoginComponent inputPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    //Fill email and password fields
    private void fillFields(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
    }

    //Login???? which page return?
    public MyCabinetPage successfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return new MyCabinetPage(driver);
    }

    public ManualLoginComponent unsuccessfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return this;
    }
}
