package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.ForgotPasswordComponent;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginComponent extends TopPart {
    public static final String MODAL_WINDOW_CSS = "app-sign-in div";
    protected WebDriverWait wait;

    private By modalWindow = By.cssSelector(".wrapper");
    private By title = By.cssSelector("app-sign-in h1");
    private By subtitle = By.cssSelector("app-sign-in h2");
    private By forgotPasswordLink = By.cssSelector(".forgot-password");
    private By singInWithGoogleButton = By.cssSelector(".google-sign-in");
    private By singUpLink = By.cssSelector(".missing-account .sign-up-link");
    private By closeFormButton = By.cssSelector(".cross-btn");
    protected By loginComponent = By.cssSelector(".cdk-overlay-pane");

    private ManualLoginComponent manualLoginComponent;

    public LoginComponent(WebDriver driver) {
        super(driver);
        checkElements();
    }

    public void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
    }

    public ManualLoginComponent getManualLoginComponent() {
        return manualLoginComponent = new ManualLoginComponent(driver);
    }

    //Sign in popup window
    protected WebElement getLoginModalWindow() {
        return driver.findElement(modalWindow);
    }

    //Title
    protected WebElement getTitle() {
        return driver.findElement(title);
    }

    public String getTitleText() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return getTitle().getText();
    }

    //SubTitle
    protected WebElement getSubtitle() {
        return driver.findElement(subtitle);
    }

    public String getSubtitleText() {
        return this.getSubtitle().getText();
    }

    //Forgot password
    public WebElement getForgotPasswordLink() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        return driver.findElement(forgotPasswordLink);
    }

    public boolean isDisplayedForgotPasswordLink() {
        return getForgotPasswordLink().isDisplayed();
    }
    public ForgotPasswordComponent clickForgotPasswordLink(){
        getForgotPasswordLink().click();
        return new ForgotPasswordComponent(driver);
    }

    //Sign in with Google
    public WebElement getSingInWithGoogleButton() {
        return driver.findElement(singInWithGoogleButton);
    }

    public boolean isDisplayedSingInWithGoogleButton() {
        return getSingInWithGoogleButton().isDisplayed();
    }

    public GoogleLoginPage clickSingInWithGoogleButton() {
        getSingInWithGoogleButton().click();
        return new GoogleLoginPage(driver);
    }

    //Sign up link
    public WebElement getSignUpLink() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(singUpLink));
        return driver.findElement(singUpLink);
    }

    public boolean isDisplayedSignUpLink() {
        return getSignUpLink().isDisplayed();
    }

    public RegisterComponent clickSignUpLink() {
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }

    //Close Sign in popup window
    public WebElement getCloseFormButton() {
        return driver.findElement(closeFormButton);
    }

    public void closeLoginComponent() {
        getCloseFormButton().click();
    }

    public boolean isLoginComponentClosed() {
        closeLoginComponent();
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);
        return elementsCustomMethods.waitTillElementGone(driver, loginComponent, 6000, 2000);
    }
}

