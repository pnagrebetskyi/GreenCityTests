package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.QuantityItems;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author lv-493 Taqc/Java
 */
public class EcoNewsPage extends TopPart {

    protected WebDriverWait wait;

    private ItemsContainer itemsContainer;
    private TagsComponent tagsComponent;

    private By header = By.cssSelector("h1");
    private By createNewsButton = By.id("create-button");
    private By gridView = By.cssSelector(".gallery-view .one-square");
    private By listView = By.cssSelector("div.list-view>.one-line");
    private By displayedArticles = By.cssSelector("ul.list.gallery-view-active > li.gallery-view-li-active");
    ///private By countItems = By.cssSelector(("app-remaining-count>p"));
    private By foundItems = By.cssSelector(("app-remaining-count>p"));//By.cssSelector(".list>li.ng-star-inserted");//By.xpath("//*[@class='ng-star-inserted']");//(".wrapper>ul>a")-filter


    public EcoNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getGridView()));
        wait.until(ExpectedConditions.visibilityOf(getListView()));
    }

    //Tag Component
    private TagsComponent getTagsComponent() {
        return tagsComponent = new TagsComponent(driver);
    }

    //Header
    public WebElement getHeader() {
        return driver.findElement(header);
    }

    public String getHeaderText() {
        return getHeader().getText();
    }

    //Create news button
    private WebElement getCreateNewsButton() {
        return driver.findElement(createNewsButton);
    }

    private String getCreateNewsButtonText() {
        return getCreateNewsButton().getText();
    }

    private void clickCreateNewsButton() {
        getCreateNewsButton().click();
    }

    public boolean isDisplayedCreateNewsButton() {
        return getCreateNewsButton().isDisplayed();
    }

    //Amount of items
    private WebElement getFoundItems() {
        return searchElementByCss(foundItems);
    }

    private String getFoundItemsText() {
        return getFoundItems().getText();
    }

    public int getNumberOfItemComponent() {
        driver.findElements(displayedArticles);
        return Integer.parseInt(getFoundItemsText().split(" ")[0]);
    }


    //Grid View
    public WebElement getGridView() {
        return searchElementByCss(gridView);
    }

    public EcoNewsPage hoverToGridView() {
        Actions action = new Actions(driver);
        action.moveToElement(getGridView()).perform();
        return this;
    }

    public boolean isActiveGridView() {
        return getGridView().getAttribute("class").contains("active");
    }

    private void clickGridView() {
        if (!isActiveGridView()) {
            scrollToElement(getGridView());
            getGridView().click();
        }
    }

    //List View
    public WebElement getListView() {
        return searchElementByCss(listView);
    }

    public boolean isDisplayedListView() {
        return getListView().isDisplayed();
    }

    public EcoNewsPage hoverToListView() {
        Actions action = new Actions(driver);
        action.moveToElement(getListView()).perform();
        return this;
    }

    public boolean isActiveListView() {
        return getListView().getAttribute("class").contains("active");
    }

    private void clickListView() {
        if (!isActiveListView()) {
            scrollToElement(getListView());
            getListView().click();
        }
    }

    //Item Container
    public ItemsContainer getItemsContainer() {
        return itemsContainer = new ItemsContainer(driver);
    }

    public void visualiseListElements() {
        int i = 0;
        int numberOfScroll = Math.round(getNumberOfItemComponent() / 12);//on one page can see only 12 news
        //waiting(2);
        while (i <= numberOfScroll) {
            scrollToElement(getCopyright());
            i++;//  open all news
            waiting(3);
        }
    }

    /**
     * Scroll to WebElement, in case when need to click on it or without scrolling are invisible
     *
     * @param el
     */
    private void scrollToElement(WebElement el) {
        Actions action = new Actions(driver);
        action.moveToElement(el).perform();
    }

    /**
     * Waiting for elements became visible
     *
     * @param i
     */
    private void waiting(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /*  /**
     * Get number of ItemComponent, what are present on EcoNewsPage
     *
     * @return int
     */
   /* public int getNumberOfItemComponent() {
        return new QuantityItems().quantityItems(getFoundItemsText());
    }*/

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EcoNewsPage
     */
    public EcoNewsPage selectFilters(List<Tag> tags) {
        scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().selectTags(tags);
        return new EcoNewsPage(driver);
    }

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EcoNewsPage
     */
    public EcoNewsPage deselectFilters(List<Tag> tags) {
        scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().deselectTags(tags);
        return new EcoNewsPage(driver);
    }

    /**
     * Choose language
     *
     * @param language
     * @return EcoNewsPage
     */
    public EcoNewsPage switchLanguage(Languages language) {
        chooseLanguage(language);
        return new EcoNewsPage(driver);
    }

    /**
     * News are displayed as grid
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToGridView() {
        clickGridView();
        return new EcoNewsPage(driver);
    }

    /**
     * News are displaeyd as list
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToListView() {
        clickListView();
        return new EcoNewsPage(driver);
    }

    /**
     * Open SingleNewsPage
     *
     * @param number
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToSingleNewsPageByNumber(int number) {
        scrollToElement(itemsContainer.chooseNewsByNumber(number).getTitle());
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new SingleNewsPage(driver);
    }

    /**
     * Open SingleNewsPage
     *
     * @param news
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToSingleNewsPageByParameters(NewsData news) {
        scrollToElement(itemsContainer.findItemComponentByParameters(news).getTitle());
        itemsContainer.clickItemComponentOpenPage(news);
        return new SingleNewsPage(driver);
    }

    /**
     * Open CreateNewsPage
     *
     * @return CreateNewsPage
     */
    public CreateNewsPage gotoCreateNewsPage() {
        scrollToElement(getCreateNewsButton());
        clickCreateNewsButton();
        return new CreateNewsPage(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
