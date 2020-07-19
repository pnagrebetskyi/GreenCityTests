package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.CookiesAndStorageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * In GoogleLoginPage class you can see two different locators to each WebElement of this page.
 * It`s because in headless mode appear different sign-in form with different locators.
 */
public class GoogleLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private WebElement emailField;
    private WebElement emailNextButton;
    private WebElement passwordField;
    private WebElement passwordNextButton;

    private final String EMAIL_FIELD_ID = "identifierId";
    private final String EMAIL_FIELD_ID_HEADLESS = "Email";
    private final String EMAIL_NEXT_BUTTON_XPATH = "//*[@id='identifierNext']/div";
    private final String EMAIL_NEXT_BUTTON_ID_HEADLESS = "next";

    private final String PASSWORD_FIELD_XPATH = ".//*[@id='password']/div[1]/div/div[1]/input";
    private final String PASSWORD_FIELD_ID_HEADLESS = "password";
    private final String PASSWORD_NEXT_BUTTON_XPATH = "//*[@id='passwordNext']/div";
    private final String PASSWORD_NEXT_BUTTON_ID_HEADLESS = "submit";

    public final static String GOOGLE_LOGIN_PAGE_URL = "https://accounts.google.com/signin/oauth";

    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailField() {
        if (GreenCityTestRunner.CHROME_HEADLESS_OPTION) {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FIELD_ID_HEADLESS)));

            return emailField = driver.findElement(By.id(EMAIL_FIELD_ID_HEADLESS));
        }

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FIELD_ID)));

        return emailField = driver.findElement(By.id(EMAIL_FIELD_ID));
    }

    public WebElement getEmailNextButton() {
        if (GreenCityTestRunner.CHROME_HEADLESS_OPTION) {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_NEXT_BUTTON_ID_HEADLESS)));

            return emailField = driver.findElement(By.id(EMAIL_NEXT_BUTTON_ID_HEADLESS));
        }

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_NEXT_BUTTON_XPATH)));

        return emailNextButton = driver.findElement(By.xpath(EMAIL_NEXT_BUTTON_XPATH));
    }

    public WebElement getPasswordField() {
        if (GreenCityTestRunner.CHROME_HEADLESS_OPTION) {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FIELD_ID_HEADLESS)));

            return emailField = driver.findElement(By.id(PASSWORD_FIELD_ID_HEADLESS));
        }

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_FIELD_XPATH)));

        return passwordField = driver.findElement(By.xpath(PASSWORD_FIELD_XPATH));
    }

    public WebElement getPasswordNextButton() {
        if (GreenCityTestRunner.CHROME_HEADLESS_OPTION) {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_NEXT_BUTTON_ID_HEADLESS)));

            return emailField = driver.findElement(By.id(PASSWORD_NEXT_BUTTON_ID_HEADLESS));
        }

        return passwordNextButton = driver.findElement(By.xpath(PASSWORD_NEXT_BUTTON_XPATH));
    }

    public TipsTricksPage successfulLoginByGoogle(User user) {
        String parentWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList();
        windowHandles.addAll(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        getEmailField().sendKeys(user.getEmail());
        getEmailNextButton().click();

        getPasswordField().sendKeys(user.getPassword());
        getPasswordNextButton().click();

        driver.switchTo().window(parentWindow);

        return new TipsTricksPage(driver);
    }

    public void clearCookies() {
        CookiesAndStorageHelper cookiesAndStorageHelper = new CookiesAndStorageHelper(driver);
        cookiesAndStorageHelper.cleanGoogleAuthCookiesAndStorages();
    }
}
