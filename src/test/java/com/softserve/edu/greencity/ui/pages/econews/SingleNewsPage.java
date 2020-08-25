package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleNewsPage extends TopPart {

    protected WebDriverWait wait;

    private By goToNews = By.cssSelector("div.back-button");
    private By title = By.cssSelector("div.news-title");
    private By data = By.cssSelector("div.news-info > div.news-info-date");
    private By author = By.cssSelector("div.news-info > div.news-info-author");
    private By picture = By.cssSelector("div.news-image > img.news-image-img");
    private By content = By.cssSelector("div.news-text");
    private List<WebElement> tagsList = driver.findElements(By.cssSelector("div.tags > div"));
    private ItemsContainer itemsContainer;

    public SingleNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    @Step
    private void checkElements() {
        itemsContainer = new ItemsContainer(driver);
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
    }

    @Step
    private WebElement getGoToNews() {
        return searchElementByCss(goToNews);
    }

    @Step
    private String getGoToNewsText() {
        return getGoToNews().getText();
    }

    @Step
    private void clickGoToNewsButton() {
        getGoToNews().click();
    }

    @Step
    private List<WebElement> getTagsList() {
        return tagsList;
    }

    @Step
    private WebElement getTitle() {
        return searchElementByCss(title);
    }

    @Step
    public String getTitleText() {
        return getTitle().getText().trim();
    }

    @Step
    private WebElement getData() {
        return searchElementByCss(data);
    }

    @Step
    private String getDataText() {
        return getData().getText();
    }

    @Step
    private WebElement getAuthor() {
        return searchElementByCss(author);
    }

    @Step
    private String getAuthorText() {
        return getAuthor().getText();
    }

    @Step
    private WebElement getContent() {
        return searchElementByCss(content);
    }

    @Step
    private String getContentText() {
        return getContent().getText();
    }

    /**
     * Go to next SingleNewsPage
     *
     * @param number
     * @return SingleNewsPage
     */
    @Step
    public SingleNewsPage switchToNextSingleNewsPageByNumber(int number) {
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new SingleNewsPage(driver);
    }

    /**
     * Go to next SingleNewsPage
     *
     * @return SingleNewsPage
     */
    @Step
    public SingleNewsPage switchToNextSingleNewsPage() {
        switchToNextSingleNewsPageByNumber(1);
        return new SingleNewsPage(driver);
    }

    /**
     * Return to EcoNewsPage
     *
     * @return EcoNewsPage
     */
    @Step
    public EcoNewsPage switchToEcoNewsPageBack() {
        clickGoToNewsButton();
        return new EcoNewsPage(driver);
    }
}
