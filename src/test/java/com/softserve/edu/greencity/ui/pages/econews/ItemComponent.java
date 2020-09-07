package com.softserve.edu.greencity.ui.pages.econews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ItemComponent {
    private WebDriver driver;
    protected WebDriverWait wait;
    private WebElement newsItem;
    private By tags = By.cssSelector(".filter-tag div");
    private By image = By.cssSelector(".list-image-content");
    private By title = By.cssSelector(".title-list p");
    private By content = By.cssSelector(".list-text p");
    private By dateOfCreation = By.cssSelector(".user-data-text-date");
    private By author = By.cssSelector(".user-data-added-news > p:nth-child(2)");

    public ItemComponent(WebDriver driver, WebElement newsItem) {
        this.driver = driver;
        this.newsItem = newsItem;
        checkElements();
    }

    private void checkElements() {
        getTitle();
        //getContent();
        //getDateOfCreation();
        //getAuthor();
        //makeElPresent();
    }

    private void makeElPresent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Duration duration = Duration.ofMillis(20L);
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(duration).ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(getTitle(), getImage(), getContent(), getDateOfCreation(), getAuthor()));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean IsDisplayedItemComponent() {
        if (newsItem.isDisplayed()) {
            return true;
        } else scrollToElement(newsItem);
        return newsItem.isDisplayed();
    }


    //Tags
    public List<WebElement> getTags() {
        return newsItem.findElements(tags);
    }

    public boolean isDisplayedTags() {
        boolean isDisplayedCurrent = false;
        for (WebElement current : getTags()) {
            isDisplayedCurrent = current.isDisplayed();
        }
        return isDisplayedCurrent;
    }

    //Image
    public WebElement getImage() {
        return newsItem.findElement(image);
    }

    public boolean isDisplayedImage() {
        return getImage().isDisplayed();
    }

    //Title
    public WebElement getTitle() {
        return newsItem.findElement(title);
    }

    public String getTitleText() {
        return getTitle().getText();
    }

    protected void clickTitle() {
        getTitle().click();
    }

    public boolean isDisplayedTitle() {
        return getTitle().isDisplayed();
    }

    //Content
    public WebElement getContent() {
        return newsItem.findElement(content);
    }

    protected String getContentText() {
        return getContent().getText();
    }

    protected void clickContent() {
        getContent().click();
    }

    public boolean isDisplayedContent() {
        return getContent().isDisplayed();
    }

    //DateOfCreation
    public WebElement getDateOfCreation() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfCreation));
        return newsItem.findElement(dateOfCreation);
    }

    public String getDateOfCreationText() {
        return getDateOfCreation().getText();
    }

    public Date getDateOfCreationDateFormat() {
        Date date = null;
        String dateText = getDateOfCreation().getText();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        try {
            date = formatter.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public boolean isDisplayedDateOfCreation() {
        return getDateOfCreation().isDisplayed();
    }

    public boolean isCorrectDateFormat(String date) {
        Pattern DATE_PATTERN = Pattern.compile("[a-zA-Z]{3}\\s+\\d{1,2},?\\s+\\d{4}");
        return DATE_PATTERN.matcher(date).matches();
    }

    //Author
    private WebElement getAuthor() {
        return newsItem.findElement(author);
    }

    private String getAuthorText() {
        return getAuthor().getText();
    }

    public boolean isDisplayedAuthor() {
        return getAuthor().isDisplayed();
    }

    /**
     * List with names of Tags
     *
     * @return List<String>
     */
    protected List<String> getTagsText() {
        List<String> str = new ArrayList<String>();
        for (WebElement elem : getTags()) {
            str.add(elem.getText().toLowerCase());
        }
        Collections.sort(str);
        return str;
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
}
