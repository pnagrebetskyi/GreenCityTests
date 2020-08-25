package com.softserve.edu.greencity.ui.pages.econews;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * PreViewPage class
 *
 * @author lv-493 Taqc/Java
 */
public class PreViewPage extends TopPart {

    protected WebDriverWait wait;

    private By titleField = By.cssSelector("div.news-title");
    private By dateField = By.cssSelector("div.news-info-date");
    private By authorField = By.cssSelector("div.news-info-author");
    private By contentField = By.cssSelector("div.news-text-content");
    private By imgTwitterLink = By.xpath("//img[contains(@src,'twitter.svg')]");
    private By imgLinkedInLink = By.xpath("//img[contains(@src,'linkedin.svg')]");
    private By imgFacebookLink = By.xpath("//img[contains(@src,'facebook.svg')]");
    private By backToEditingLink = By.cssSelector("div.button-text");
    private WebElement publishButton;
    private List<WebElement> tagsFields;

    /**
     * Constructor PreViewPage
     *
     * @param driver
     */
    public PreViewPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    @Step
    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getBackToEditingLink()));
    }

    @Step
    public List<WebElement> getTagsFields() {
        tagsFields = driver.findElements(By.cssSelector("div.tags > div"));
        return tagsFields;
    }

    @Step
    public WebElement getTitleField() {
        return searchElementByCss(titleField);
    }

    @Step
    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    @Step
    public WebElement getDateField() {
        return searchElementByCss(dateField);
    }

    @Step
    public String getDateFieldText() {
        return getDateField().getText();
    }

    @Step
    public WebElement getAuthorField() {
        return searchElementByCss(authorField);
    }

    @Step
    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    @Step
    public WebElement getContentField() {
        return searchElementByCss(contentField);
    }

    @Step
    public String getContentFieldText() {
        return getContentField().getText();
    }

    @Step
    public WebElement getImgTwitterLink() {
        return searchElementByXpath(imgTwitterLink);
    }

    @Step
    public void clickImgTwitterLink() {
        getImgTwitterLink().click();
    }

    @Step
    public WebElement getImgLinkedInLink() {
        return searchElementByXpath(imgLinkedInLink);
    }

    @Step
    public void clickImgLinkedInLink() {
        getImgLinkedInLink().click();
    }

    @Step
    public WebElement getImgFacebookLink() {
        return searchElementByXpath(imgFacebookLink);
    }

    @Step
    public void clickImgFacebookLink() {
        getImgFacebookLink().click();
    }

    @Step
    public WebElement getBackToEditingLink() {
        return searchElementByCss(backToEditingLink);
    }

    @Step
    public void clickBackToEditingLink() {
        getBackToEditingLink().click();
    }

    @Step
    public WebElement getPublishButton() {
        List<WebElement> list = driver.findElements(By.cssSelector("button[type='submit']"));
        if (list.size() > 0) {
            publishButton = list.get(0);
        } else {
            return null;
        }
        return publishButton;
    }

    @Step
    public boolean isPublishButtonPresent() {
        return (driver.findElements(By.cssSelector("button[type='submit']")).size() > 0);
    }

    @Step
    private void clickPublishButton() {
        if (isPublishButtonPresent()) {
            getPublishButton().click();
        } else {
            logger.info("Element Publish button does not exist.");
        }
    }

    /**
     * Get sorted list of Strings with tags names
     *
     * @return List<String>
     */
    @Step
    public List<String> getTagsNames() {
        List<String> tagsNames = new ArrayList<>();
        for (WebElement current : getTagsFields()) {
            tagsNames.add(current.getText().toLowerCase());
        }
        Collections.sort(tagsNames);
        return tagsNames;
    }

    /**
     * Method to back to CreateNewsPage from PreViewPage
     *
     * @return CreateNewsPage
     */
    @Step
    public CreateNewsPage backToCreateNewsPage() {
        clickBackToEditingLink();
        return new CreateNewsPage(driver);
    }

    /**
     * Method to publish news
     *
     * @return EcoNewsPage
     */
    @Step
    public EcoNewsPage publishNews() {
        clickPublishButton();
        return new EcoNewsPage(driver);
    }
}
