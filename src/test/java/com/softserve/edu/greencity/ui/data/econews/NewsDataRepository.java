package com.softserve.edu.greencity.ui.data.econews;

import java.util.ArrayList;
import java.util.List;

public class NewsDataRepository {

    private NewsDataRepository() {
    }

    public static NewsData getDefault() {
        return getAllFieldsNews();
    }

    public static NewsData getRequiredFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "Green Day", "Content = description");
    }

    public static NewsData getNewsWithValidData() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                "Be eco! Be cool!",
                "It's so healthy, fun and cool to bring eco habits in everyday life"
        );
    }

    public static NewsData getNewsWithValidData(String title) {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                title,
                "It's so healthy, fun and cool to bring eco habits in everyday life"
        );
    }

    public static NewsData getNewsWithValidData(List<Tag> tags) {
        return new NewsData(tags,
                "Be eco! Be cool!",
                "It's so healthy, fun and cool to bring eco habits in everyday life"
        );
    }

    public static NewsData getNewsWithInvalidTitleField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        return new NewsData(tags,
                " ",
                "March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave., takes place the most important event for professionals and funs of natural food and healthy life"
        );
    }

    public static NewsData getNewsWithInvalidContentField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags, "Green Day", "foo");
    }

    public static NewsData getNewsWithEmptyContentField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                "XVI International specialized exhibition of ecologic products for the daily life",
                " "
        );
    }

    public static NewsData getNewsWithInvalidSourceField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                "XVI International specialized exhibition of ecologic products for the daily life",
                "March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave., takes place the most important event for professionals and funs of natural food and healthy life",
                "www.greenmatch.co.uk/blog/how-to-be-more-eco-friendly"
        );
    }

    public static NewsData getAllFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData("Green Day", tags, "https://news.com",
                "Content = description", "src/test/resources/test1.jpg");
    }

    public static NewsData getNewsWithInvalidData() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        tags.add(Tag.ADS);
        return new NewsData("The loss of any species is devastating. However, the decline or " +
                "extinction of one species can trigger an avalanche within an ecosystem, wiping out" +
                " many species in the process",
                tags, "news.com", "Content", "src/test/resources/invalid.gif");
    }

    public static NewsData getExistingNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        return new NewsData(tags, " Толока в Горіховому гаю ", " Test Test Test Test Test Test Test  ");
    }

    public static NewsData getNewsWithInvalidTags(List<Tag> tags) {
        return new NewsData(tags,
                "Be eco! Be cool!",
                "It's so healthy, fun and cool to bring eco habits in everyday life"
        );
    }

    public static List<Tag> getNewsByTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.ADS);
        tags.add(Tag.EVENTS);
        return tags;
    }
}
