package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopGuestComponent {
    private WebDriver driver;
    private By signInLink = By.cssSelector("li.sign-in-link.tertiary-global-button a");
    private By signUpLink = By.cssSelector("li.sign-up-link.ng-star-inserted div");

    public TopGuestComponent(WebDriver driver) {
        this.driver = driver;
    }

    //Sign In link
    public WebElement getSignInLink() {
        return driver.findElement(signInLink);
    }

    public boolean isDisplayedSignInLink() {
        return getSignInLink().isDisplayed();
    }

    public LoginComponent clickSignInLink() {
        getSignInLink().click();
        return new LoginComponent(driver);
    }

    //Sign Up link
    public WebElement getSignUpLink() {
        return driver.findElement(signUpLink);
    }

    public boolean isDisplayedSignUpLink() {
        return getSignUpLink().isDisplayed();
    }

    public String getSignUpLinkText() {
        return getSignUpLink().getText();
    }

    public RegisterComponent clickSignUpLink() {
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }
}
