package com.softserve.edu.greencity.ui.pages.econews;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ItemComponent {

	private WebDriver driver;
	protected WebDriverWait wait;

	private WebElement newsItem;
	private WebElement title;
	private WebElement content;
	private WebElement dateOfCreation;
	private WebElement author;
	private List<WebElement> tags;

	public ItemComponent(WebDriver driver, WebElement newsItem) {
		this.driver = driver;
		this.newsItem = newsItem;
		initElements();
	}

	private void initElements() {
		tags = newsItem.findElements(By.cssSelector("div.filter-tag div"));
		title = newsItem.findElement(By.cssSelector("div.title-list p"));
		content = newsItem.findElement(By.cssSelector("div.list-text p"));
		dateOfCreation = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(1)"));
		author = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(2)"));
//		makeElPresent();
	}

	private void makeElPresent() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Duration duration = Duration.ofMillis(20L);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration).ignoring(TimeoutException.class);
//		WebDriverWait wait = new WebDriverWait(myDriver, 15);
//		wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
		wait.until(ExpectedConditions.visibilityOfAllElements(title, content, dateOfCreation, author));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private List<WebElement> getTags() {
		return tags;
	}

	private WebElement getNewsItem() {
		return newsItem;
	}

	protected WebElement getTitle() {
		return title;
	}

	protected String getTitleText() {
		return getTitle().getText();
	}

	protected void clickTitle() {
		getTitle().click();
	}

	private WebElement getContent() {
		return content;
	}

	protected String getContentText() {
		return getContent().getText();
	}

	protected void clickContent() {
		getContent().click();
	}

	private WebElement getDateOfCreation() {
		return dateOfCreation;
	}

	private String getDateOfCreationText() {
		return getDateOfCreation().getText();
	}

	private WebElement getAuthor() {
		return author;
	}

	private String getAuthorText() {
		return getAuthor().getText();
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
}


//package com.softserve.edu.greencity.ui.pages.econews;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public final class ItemComponent implements StableWebElementSearch {
//
//	private WebDriver driver;
//	protected WebDriverWait wait;
//
//	private By title = By.cssSelector("div.title-list p");
//	private By content = By.cssSelector("div.list-text p") ;
//	private By dateOfCreation = By.cssSelector("div.user-data-added-news > p:nth-child(1)");
//	private By author = By.cssSelector("div.user-data-added-news > p:nth-child(2)");
//	private List<WebElement> tags;
//
//	public ItemComponent(WebDriver driver, WebElement newsItem) {
//		this.driver = driver;
//		checkElements();
//	}
//
//	private void checkElements() {
//		wait.until(ExpectedConditions.visibilityOf(getTitle()));
//		tags = driver.findElements(By.cssSelector("div.filter-tag div"));
//	}
//
//	private List<WebElement> getTags() {
//		return tags;
//	}
//
//	protected WebElement getTitle() {
//		return searchElementByCss(title);
//	}
//
//	protected String getTitleText() {
//		return getTitle().getText();
//	}
//
//	protected void clickTitle() {
//		getTitle().click();
//	}
//
//	private WebElement getContent() {
//		return searchElementByCss(content);
//	}
//
//	protected String getContentText() {
//		return getContent().getText();
//	}
//
//	protected void clickContent() {
//		getContent().click();
//	}
//
//	private WebElement getDateOfCreation() {
//		return searchElementByCss(dateOfCreation);
//	}
//
//	private String getDateOfCreationText() {
//		return getDateOfCreation().getText();
//	}
//
//	private WebElement getAuthor() {
//		return searchElementByCss(author);
//	}
//
//	private String getAuthorText() {
//		return getAuthor().getText();
//	}
//
//	/**
//	 * List with names of Tags
//	 *
//	 * @return List<String>
//	 */
//	protected List<String> getTagsText() {
//		List<String> str = new ArrayList<String>();
//		for (WebElement elem : getTags()) {
//			str.add(elem.getText().toLowerCase());
//		}
//		Collections.sort(str);
//		return str;
//	}
//
//	@Override
//	public WebDriver setDriver() {
//		return null;
//	}
//}
