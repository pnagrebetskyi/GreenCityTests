package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CreateNewsPositiveTest extends GreenCityTestRunner {

    private final String CREATE_NEWS_TITLE = "Create news";
    private final String CONTENT_ERROR = "Must be minimum 20 symbols";
    private final String INVALID_SOURCE_ERROR = " Please add the link of original article/news/post. ";
    private final String IMAGE_ERROR = "Download PNG or JPG only. File size should be less than 10MB";
    private final String VALID_TITLE = "Green Day";
    private final String VALID_CONTENT = "Content = description";
    private final String TAGS_ERROR = "Only 3 tags can be added";

    @BeforeTest
    private SoftAssert assertSoftly() {
        return new SoftAssert();
    }

    @BeforeTest
    User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    /**
     * @ID=GC-595
     */
    @Test
    @Description("Verify that user is on create news form")
    public void checkThatUserOnCreateNewsForm() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        assertSoftly().assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE);
        assertSoftly().assertTrue(createNewsPage.isPublishButtonDisplayed());
        loadApplication().signOut();
        assertSoftly().assertAll();
    }

    /**
     * @ID=GC-591
     */
    @Test
    @Description("Verify that create news button is visible for registered user")
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        EcoNewsPage econewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews();

        Assert.assertTrue(econewsPage.isCreateNewsButtonDisplayed());
        econewsPage.signOut();
    }

    /**
     * ID GC-593
     */
    @Test
    @Description("Verify that create news button is invisible for unregistered user")
    public void checkInvisibilityOfCreateNewsButtonForGuest() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        Assert.assertFalse(ecoNewsPage.isCreateNewsButtonPresent());
    }

    /**
     * @ID=GC-623
     */
    @Test
    @Description("Verify possibility of choosing tags")
    public void verifySelectAndDeselectPossibilityOfTags() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        TagsComponent tagsComponent = createNewsPage.getTagsComponent();
        tagsComponent.selectTag(Tag.NEWS);
        Assert.assertTrue(tagsComponent.isTagActive(Tag.NEWS));
        tagsComponent.deselectTag(Tag.NEWS);
        Assert.assertFalse(tagsComponent.isTagActive(Tag.NEWS));
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-592
     */
    @Test
    @Description("Verify that news would not be created if content is too short")
    public void verifyImpossibilityOfCreatingNewsWithTooShortContent() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithInvalidContentField());

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        Assert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR);
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-645
     */
    @Test
    @Description("Verify that user can`t create news with incorrect URL")
    public void verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithInvalidSourceField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertEquals(createNewsPage.getInvalidSourceErrorText(), INVALID_SOURCE_ERROR);
        assertSoftly().assertAll();
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-637
     */
    @Test
    @Description("Verify that user can`t create news with empty fields")
    public void verifyImpossibilityOfCreatingNewsWithEmptyFields() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-642
     */
    @Test
    @Description("Verify that user can`t create news without tags")
    public void verifyImpossibilityOfCreatingNewsWithoutAnyTags() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.getTagsComponent().deselectTag(Tag.NEWS);
        createNewsPage.getTagsComponent().deselectTag(Tag.EVENTS);

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-644
     */
    @Test
    @Description("Verify that user can`t create news with empty title")
    public void verifyImpossibilityOfCreatingNewsWithEmptyTitle() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithInvalidTitleField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertAll();
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-638
     */
    @Test
    @Description("Verify that user can`t create news with empty content")
    public void verifyImpossibilityOfCreatingNewsWithEmptyContent() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithEmptyContentField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR);
        assertSoftly().assertAll();
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-621
     */
    @Test
    @Description("Verify that user can go to preview page")
    public void verifyPossibilityOfPreViewingNewsPage() {
        PreViewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .goToPreViewPage();

        assertSoftly().assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        assertSoftly().assertAll();
        preViewPage.signOut();
    }

    /**
     * @ID=GC-633
     */
    @Test
    @Description("Verify that preview page is displayed correctly")
    public void verifyThatPreViewIsDisplayedCorrectly() {
        PreViewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getAllFieldsNews())
                .goToPreViewPage();

        assertSoftly().assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        assertSoftly().assertEquals(preViewPage.getTitleFieldText(), VALID_TITLE);
        assertSoftly().assertEquals(preViewPage.getContentFieldText(), VALID_CONTENT);
        assertSoftly().assertAll();
        preViewPage.signOut();
    }

    /**
     * @ID=GC-606
     */
    @Test
    @Description("Verify that User can continue editing news in case ‘Cancel’ button was pressed")
    public void verifyThatUserCanContinueNewsCreations() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getAllFieldsNews())
                .clickCancelButton()
                .clickContinueEditingButton();

        assertSoftly().assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE);
        assertSoftly().assertAll();
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-607
     */
    @Test
    @Description("Verify that user can cancel news creation if he decided to drop posting")
    public void verifyThatUserCanCancelNewsCreations() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getAllFieldsNews())
                .clickCancelButton()
                .clickCancelEditingButton();

        assertSoftly().assertTrue(ecoNewsPage.isGridViewDisplayed());
        assertSoftly().assertAll();
        ecoNewsPage.signOut();
    }

    /**
     * @ID=GC-588
     */
    @Test
    @Description("Verify that user can`t upload .gif format image")
    public void verifyImpossibilityOfUploadingGifImage() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), "src/test/resources/gifImage.gif");
        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR);
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-634
     */
    @Test
    @Description("Verify that user can`t add JPEG image more than 10 MB")
    public void verifyImpossibilityOfUploadingTooLargeImage() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), "src/test/resources/tooLargeImage.jpg");
//        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR); TODO IMAGE CAN BE MORE THAN 10 MB
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-397
     */
    @Test
    @Description("Verify that user can publish news only after filling in all mandatory fields with valid data")
    public void checkImpossibleToCreateNewsWithoutFillingMandatoryFields() throws SQLException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData());

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    /**
     * @ID=GC-401
     */
    @Test(dataProvider = "getStringForTitle")
    @Description("Verify that user can publish news with valid characters in a 'Title' field")
    public void fillTitleFieldFromMinToMax(String title) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData(title));
        assertSoftly().assertTrue(createNewsPage.isPublishButtonClickable());

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    @DataProvider
    public Object[] getStringForTitle() {
        return new Object[]{
                "B",
                "Be eco! Be cool! Be healthy! Do not be indifferent to the future of our planet! " +
                        "It`s so healthy, fun and cool to bring eco habits in everyday life!Test maximum characters",
                "Be eco! Be cool! Be healthy! Do not be indifferent to the future of our planet!",
                "1234567890",
                "~!@#$%^&*()_+|?/,.\\"
        };
    }

    /**
     * @ID=GC-628
     */
    @Test(dataProvider = "getTagsList")
    @Description("Verify news creation with allowed amount of selected tags")
    public void checkCreateNewsWithOneToThreeTags(List<Tag> tags) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData(tags));

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    @DataProvider
    public Object[] getTagsList() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                    }
                },
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                        add(Tag.EVENTS);
                    }
                },
                new ArrayList<Tag>() {
                    {
                        add(Tag.EVENTS);add(Tag.NEWS);
                        add(Tag.EDUCATION);
                    }
                }
        };
    }

    /**
     * @ID=GC-643
     */
    @Test(dataProvider = "getInvalidTagsList")
    @Description("Verify that user can`t create news with more than 3 tags selected")
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews(List<Tag> tags) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithInvalidTags(tags));

        assertSoftly().assertTrue(createNewsPage.isTagsErrorDisplayed());
        assertSoftly().assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR);

        createNewsPage.goToPreViewPage().backToCreateNewsPage();

        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        assertSoftly().assertTrue(createNewsPage.isTagsErrorDisplayed());
        assertSoftly().assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR);

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    @DataProvider
    public Object[] getInvalidTagsList() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                        add(Tag.EVENTS);
                        add(Tag.EDUCATION);
                        add(Tag.ADS);
                    }
                }
        };
    }

    /**
     * @ID=GC-654
     */
    @Test(dataProvider = "getTagsListWithSingleTag")
    @Description("Verify that user can`t create news with 2 or more same tags")
    public void verifyImpossibilityToSelectOneTagTwice(ArrayList<Tag> tags) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData());

        createNewsPage.goToPreViewPage().backToCreateNewsPage();
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    @DataProvider
    public Object[] getTagsListWithSingleTag() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                    }
                }
        };
    }

    /**
     * @ID=GC-616
     */
    @Test
    @Description("Verify that news will be created, when user put more than 20 symbols in ‘Content’ field.")
    public void createNewsWithContentLengthMoreThen20() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData());

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    /**
     * @ID=GC-613
     */
    @Test
    @Description("Verify that news will be created when user add a link, which contains https:// in ‘Source’ field")
    public void createNewsWithSourceField() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidSourceField());

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }

    /**
     * @ID=GC-617
     */
    @Test
    public void verifyAutoFillingDataWhenCreateNews() throws InterruptedException, SQLException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getNewsWithValidData());

        //createNewsPage.clickPublishButton();
        //TODO CHECK THAT NEWS IS CREATED
        //TODO DELETE FROM DATABASE

        createNewsPage.signOut();
    }


    /**
     * Additional methods, tools, helpers for these tests
     * ===========================================================================================================================================================
     */
    public String getCredential(String key) {
        Properties properties = new Properties();
        try {
            properties
                    .load(new BufferedReader(new FileReader("src/test/resources/credentials.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public User getSpecialUser(String emailKey, String passKey) {
        String email = getCredential(emailKey);
        String pass = getCredential(passKey);
        return new User(email, pass);
    }

    public Connection connectToJDBC() {
        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    getCredential("JDBC_url"),
                    getCredential("JDBC_user"),
                    getCredential("JDBC_password"));
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public void cleanDataBase(String title) throws SQLException, InterruptedException {
        cleanDataBase(title, false);
    }

    public void cleanDataBase(String title, boolean waiting) throws SQLException, InterruptedException {
        if (waiting) {
            Thread.sleep(15000);
        }
        Connection connection = connectToJDBC();

        String titleInDataBase = title;
        ResultSet queryResponse = connection
                .createStatement()
                .executeQuery("SELECT * FROM public.eco_news WHERE title = '" + titleInDataBase + "'");
        Assert.assertTrue(queryResponse.next());
        int id = queryResponse.getInt("id");
        connection
                .prepareStatement("DELETE FROM public.eco_news_tags * WHERE eco_news_id = " + id)
                .execute();
        connection
                .prepareStatement("DELETE FROM public.eco_news * WHERE id = " + id)
                .execute();
    }

}
