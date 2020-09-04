package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.api.mail.GoogleMailAPI;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Getter
public class ManualRegisterComponent extends RegisterComponent implements StableWebElementSearch {

    private WebDriver driver;
    private String confirmURL = null;
    private By email_field_selector = By.cssSelector("input[name='email']");
    private By userNameFieldSelector = By.cssSelector("input[name='fistName']");
    private By passwordFieldSelector = By.cssSelector("input[name='form-control password']");
    private By passwordConfirmFieldSelector = By.cssSelector("input[name='form-control password-confirm");
    private By signButtonSelector = By.cssSelector("app-sign-up button.primary-global-button");
    private By emailValidatorSelector = By.xpath("//input[@name='email']/following-sibling::div/div");
    private By firstNameValidatorSelector = By.xpath("//input[@name='fistName']/following-sibling::div/div");
    private By passwordValidatorSelector = By.xpath("//*[@name = 'form-control password']//parent::div//following-sibling::div");
    private By showPasswordButtonSelector = By.xpath("//input[@name='form-control password']/../span/img");
    private By showPasswordConfirmButtonSelector = By.xpath("//input[@name='form-control password-confirm']/../span/img");
    private By passwordConfirmValidatorSelector = By.xpath("//*[@name = 'form-control password-confirm']//parent::div//following-sibling::div");
    private By errorMessages = By.cssSelector("div.error-message");
    private By signUpErrorsMsg = By.cssSelector("app-sign-up div.error-message-show");
    private By sackfulRegistrationPopUp = By.cssSelector("app-submit-email div.submit-email");
    public ManualRegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step
    public WebElement getEmailField() {
        return searchElementByCss(email_field_selector);
    }

    @Step
    public WebElement getUserNameField() {
        return searchElementByCss(userNameFieldSelector);
    }

    @Step
    public WebElement getPasswordField() {
        return searchElementByCss(passwordFieldSelector);
    }

    @Step
    public WebElement getPasswordConfirmField() {
        return searchElementByCss(passwordConfirmFieldSelector);
    }

    @Step
    private ManualRegisterComponent inputUserName(String userName) {
        this.getUserNameField().sendKeys(userName);
        return this;
    }

    @Step
    private ManualRegisterComponent clearFirstName() {
        this.getUserNameField().clear();
        return this;
    }

    @Step
    private ManualRegisterComponent clickFirstName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getUserNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getUserNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedFirstNameField() {
        return getUserNameField().isDisplayed();
    }


    @Step
    private WebElement getUserNameValidator() {
        return searchElementByCss(firstNameValidatorSelector);
    }

    @Step
    public String getUserNameValidatorText() {
        return getUserNameValidator().getText();
    }



    @Step
    private RegisterComponent inputEmailField(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    @Step
    private RegisterComponent clearEmailField() {
        this.getEmailField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickEmailField(WebDriver driver) {
        if (isDisplayedEmailField()) {
            this.getEmailField().click();
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }

    @Step
    public String getEmailValidatorText() {
        return getEmailValidator().getText();
    }

    @Step
    private WebElement getEmailValidator() {
        return searchElementByCss(emailValidatorSelector);
    }



    @Step
    private RegisterComponent inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }

    @Step
    private RegisterComponent clearPasswordField() {
        this.getPasswordField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickPasswordField(WebDriver driver) {
        if (isDisplayedPasswordField()) {
            this.getPasswordField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

    @Step
    private WebElement getShowPasswordButton() {
        return searchElementByXpath(showPasswordButtonSelector);
    }

    @Step
    private RegisterComponent clickShowPasswordButton() {
        if (isDisplayedShowPasswordButton()) {
            this.getShowPasswordButton().click();
        }
        return this;
    }


    @Step
    private boolean isDisplayedShowPasswordButton() {
        return getShowPasswordButton().isDisplayed();
    }

    @Step
    public String getPasswordValidatorText() {
        return getPasswordValidator().getText();
    }

    @Step
    private WebElement getPasswordValidator() {
        return searchElementByXpath(passwordValidatorSelector);
    }
    @Step
    public String getSingInErrorsMsg(int errorNumber){
        return driver.findElements(errorMessages).get(errorNumber-1).getText();
    }
    @Step
    public List<WebElement> getSingInErrorsMessages(int errorNumber){
        return driver.findElements(errorMessages);
    }
    @Step
    public List<WebElement> getSignUpErrorsMessages(int errorNumber){
        return driver.findElements(signUpErrorsMsg);
    }
    @Step
    public String getSignUpErrorsMsg(int errorNumber){
        return driver.findElements(signUpErrorsMsg).get(errorNumber-1).getText();
    }
    @Step
    public String getPasswordConfirmValidatorText() {
        return getPasswordConfirmValidator().getText().trim();
    }

    @Step
    private WebElement getPasswordConfirmValidator() {
        return searchElementByXpath(passwordConfirmValidatorSelector);
    }

    @Step
    public WebElement GetSackfulRegistrationPopUp() {
        return searchElementByCss(sackfulRegistrationPopUp);
    }
    @Step
    private RegisterComponent inputPasswordConfirm(String passwordConfirm) {
        this.getPasswordConfirmField().sendKeys(passwordConfirm);
        return this;
    }

    @Step
    private RegisterComponent clearPasswordConfirmField() {
        this.getPasswordConfirmField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickPasswordConfirmField(WebDriver driver) {
        if (isDisplayedPasswordConfirmField()) {
            this.getPasswordConfirmField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordConfirmField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedPasswordConfirmField() {
        return getPasswordConfirmField().isDisplayed();
    }

    @Step
    private WebElement getShowPasswordConfirmButton() {
        return searchElementByXpath(showPasswordConfirmButtonSelector);
    }

    @Step
    private RegisterComponent clickShowPasswordConfirmButton() {
        if (isDisplayedShowPasswordConfirmButton())
            this.getShowPasswordConfirmButton().click();
        return this;
    }

    @Step
    private boolean isDisplayedShowPasswordConfirmButton() {
        return getShowPasswordConfirmButton().isDisplayed();
    }

    @Step
    public WebElement getSignUpButton() {
        return searchElementByCss(signButtonSelector);
    }

    @Step
    private boolean isDisplayedSignUpButton() {
        return getSignUpButton().isDisplayed();
    }

    @Step
    public boolean signUpIsDisabled() {
        return getSignUpButton().getAttribute("disabled") != null;

    }

    @Step
    private ManualRegisterComponent fillEmailField(String email) {
        if (isDisplayedEmailField()) {
            clickEmailField(driver);
            clearEmailField();
            inputEmailField(email);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillUserNameField(String firstName) {
        if (isDisplayedFirstNameField()) {
            clickFirstName(driver);
            clearFirstName();
            inputUserName(firstName);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillPasswordFieldPassShown(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
            clickShowPasswordButton();
        }
        return this;
    }

    @Step
    public ManualRegisterComponent fillPasswordFieldPassHidden(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillPasswordConfirmField(String passwordConfirm) {
        if (isDisplayedPasswordConfirmField()) {
            clickPasswordConfirmField(driver);
            clearPasswordConfirmField();
            inputPasswordConfirm(passwordConfirm);
            clickShowPasswordConfirmButton();
        }
        return this;
    }

    @Step
    public ManualRegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }

    @Step
    private RegisterComponent verifyRegistration(User user) {
        driver.get( new GoogleMailAPI().getconfirmURL(user.getEmail(),user.getPassword(), 20));
        return this;}

    @Step
    public void registrationWrongUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }

    @Step
    public void fillFieldsWithoutRegistration(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());
    }

    @Step
    public void registrationNewUserVerified(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .waitSuccessfulRegistrationPopUp()
                .waitSuccessfulRegistrationPopUpDisappear()
                .verifyRegistration(userData);
    }

    @Step
    public void registrationUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());
        clickSignUpButton();
    }

    @Step
    public void registerUserCheckIfMailReceived(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .waitSuccessfulRegistrationPopUp()
                .waitSuccessfulRegistrationPopUpDisappear();
    }
    @Step
    public void registerUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword())
                .clickSignUpButton()
                .waitSuccessfulRegistrationPopUp();
    }
    public void enterDataToSingUpFields(User userData){
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }
    private ManualRegisterComponent waitSuccessfulRegistrationPopUp() {
        new WebDriverWait(driver, 10).until(visibilityOfElementLocated(sackfulRegistrationPopUp));
        return this;
    }

    private ManualRegisterComponent waitSuccessfulRegistrationPopUpDisappear() {
        new WebDriverWait(driver, 10).until(invisibilityOfElementLocated(sackfulRegistrationPopUp));
        return this;
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
