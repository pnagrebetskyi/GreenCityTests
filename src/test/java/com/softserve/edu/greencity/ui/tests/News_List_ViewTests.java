package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class News_List_ViewTests extends GreenCityTestRunner {
    String cssBackgroundColorProperty;
    String expectedBackgroundColorRBG;
    String cssLineHeightProperty;
    String expectedLineHeight;
    private final String DEFAULT_IMAGE = "assets/img/icon/econews/default-image-list-view.png";

    @BeforeClass
    public void beforeClass() {
        cssBackgroundColorProperty = "background-color";
        expectedBackgroundColorRBG = "rgba(5, 107, 51, 1)";
        cssLineHeightProperty = "font-size";
        expectedLineHeight = "32px";

    }

    @BeforeTest
    private SoftAssert assertSoftly() {
        return new SoftAssert();
    }

    @Test(testName = "GC-332")
    @Description("Verify that content items are displayed in a list view")
    public void isDisplayedContentItems() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        String listViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);
        assertSoftly().assertEquals(listViewIconColor, expectedBackgroundColorRBG);

        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).IsDisplayedItemComponent());
        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).IsDisplayedItemComponent());
        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(2).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(3).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(4).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(5).IsDisplayedItemComponent());
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-333")
    @Description("Verify that content items have all specified elements.")
    public void isForgotPasswordPopup() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        ItemComponent firstItem = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);

        assertSoftly().assertTrue(firstItem.isDisplayedImage());
        assertSoftly().assertTrue(firstItem.isDisplayedTags());
        assertSoftly().assertTrue(firstItem.isDisplayedTitle());
        assertSoftly().assertTrue(firstItem.isDisplayedContent());
        assertSoftly().assertTrue(firstItem.isDisplayedDateOfCreation());
        assertSoftly().assertTrue(firstItem.isDisplayedAuthor());

        assertSoftly().assertTrue(firstItem.isCorrectDateFormat(firstItem.getDateOfCreationText()));

        assertSoftly().assertAll();
    }

    @Test(testName = "GC-707")
    @Description("Verify that Content items are displayed as a list in case if 'List view' option is activated")
    public void isDisplayedListContent() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Assert.assertTrue(ecoNewsPage.getItemsContainer().hasListViewClassActive());
    }


    @Test(testName = "GC-704")
    @Description("Verify that ‘List view’ icon is present on the 'Eco news' page")
    public void isPresentListView() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        assertSoftly().assertTrue(ecoNewsPage.isDisplayedListView());

        String hoverListViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);
        assertSoftly().assertEquals(hoverListViewIconColor, expectedBackgroundColorRBG);

        ecoNewsPage
                .switchToListView()
                .hoverToGridView();

        String ListViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);
        assertSoftly().assertEquals(ListViewIconColor, expectedBackgroundColorRBG);
        assertSoftly().assertAll();
    }


    @Test(testName = "GC-710")
    @Description("Verify that 6 first Content items are displayed by deafault")
    public void isDisplayedFirstSixContent() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).IsDisplayedItemComponent());
        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).IsDisplayedItemComponent());
        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(2).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(3).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(4).IsDisplayedItemComponent());
        //assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(5).IsDisplayedItemComponent());
        assertSoftly().assertAll();
    }

    @DataProvider
    private Object[] getWindowWidth1() {
        return new Object[]{1200, 1024, 800, 576, 575};
    }

    @Test(testName = "GC-720", dataProvider = "getWindowWidth1")
    @Description("Verify that Content items contain all reqiured UI elements according to mock-up.")
    public void CheckContentUiElements(int windowWidt) {
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .publishNews()
                .switchToListView();

        ecoNewsPage.changeWindowWidth(windowWidt);

        ItemComponent firstItem = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);

        assertSoftly().assertTrue(firstItem.isDisplayedImage());
        assertSoftly().assertTrue(firstItem.isDisplayedTags());
        assertSoftly().assertTrue(firstItem.isDisplayedTitle());
        assertSoftly().assertTrue(firstItem.isDisplayedContent());
        assertSoftly().assertTrue(firstItem.isDisplayedDateOfCreation());
        assertSoftly().assertTrue(firstItem.isCorrectDateFormat(firstItem.getDateOfCreationText()));
        assertSoftly().assertTrue(firstItem.isDisplayedAuthor());
        //TODO check author after date
        assertSoftly().assertAll();
        //TODO delete news by title
    }


    @DataProvider
    private Object[] getWindowWidth2() {
        return new Object[]{1440, 1024, 768/*, 576, 360*/};
    }

    @Test(testName = "GC-725", dataProvider = "getWindowWidth2")
    @Description("Verify that displayed image in List view is default image if user didn’t choose own image during news creation.")
    public void isPresentDefaultImage(int windowWidth) {
        //TODO INSERT into eco_news and eco_news_tags
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();
        String src = ecoNewsPage.getItemsContainer().chooseNewsByNumber(2).getImage().getAttribute("src");

        ecoNewsPage.changeWindowWidth(windowWidth);
        assertSoftly().assertEquals(src, DEFAULT_IMAGE);
        //TODO DELETE
    }

    //@Test(testName = "GC-724")
    @Description("Verify that when Title consist of 1 row, then Description consist of 3 row.")
    public void isPresentDefaultImage1() {
        //TODO INSERT into eco_news and eco_news_tags
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();

        WebElement firstItemTitle = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitle();
        System.out.println(firstItemTitle.getCssValue(cssLineHeightProperty));//21px
        System.out.println(firstItemTitle.getSize().getHeight());//26//(318, 26)
// TODO DELETE

    }

    //@Test(testName = "GC-723")
    @Description("Verify that when Title consist of 3 row, then Description consist of 1 row.")
    public void row() {
        //TODO INSERT into eco_news and eco_news_tags
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();
        ecoNewsPage.getItemsContainer().chooseNewsByNumber(8).getTitle().click();
        SingleNewsPage singleNewsPage = new SingleNewsPage(driver);
        System.out.println(singleNewsPage.getTitle().getSize().getHeight());
        System.out.println(singleNewsPage.getTitle().getCssValue("font-size"));

        //TODO DELETE
    }

    //@Test(testName = "GC-708")
    @Description("Verify that when Title consist of 4 row, then Description consist of 0 row.")
    public void row2() {
        //TODO INSERT into eco_news and eco_news_tags
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        ecoNewsPage.changeWindowWidth(1400);
        ecoNewsPage.switchToListView();

        System.out.println(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitle().getCssValue("font-size"));
        ;
        //TODO DELETE
    }

   // @Test(testName = "GC-706", dataProvider = "getWindowWidth")
    @Description("Verify that Title is displayed fully.")
    public void row6(int windowWidth) {
        //TODO INSERT into eco_news and eco_news_tags
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        ecoNewsPage.changeWindowWidth(windowWidth);
        ecoNewsPage.switchToListView();

        System.out.println(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitleText().trim());

        //TODO DELETE
    }

    //@Test(testName = "GC-352")
    @Description("Verify that Content items are displayed in chronological order")
    public void row7() throws ParseException {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Date date1 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationDateFormat();
        Date date2 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationDateFormat();

        assertSoftly().assertTrue(date1.compareTo(date2) == 0);//<0

        //TODO Select

    }

}