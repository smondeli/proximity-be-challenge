package tech.proximity.dto;

import tech.proximity.entity.Video;

import java.util.Set;

public class SearchResult {
    private Set<Video> webinars;
    private Set<Video> lectures;

    public Set<Video> getWebinars() {
        return webinars;
    }

    public void setWebinars(Set<Video> webinars) {
        this.webinars = webinars;
    }

    public Set<Video> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Video> lectures) {
        this.lectures = lectures;
    }
}
