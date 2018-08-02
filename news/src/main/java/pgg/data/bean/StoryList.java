package pgg.data.bean;

import java.util.List;

import pgg.data.bean.Story;

/**
 * Created by PDD on 2018/3/10.
 */

public class StoryList {

    private String date;
    private List<Story> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
