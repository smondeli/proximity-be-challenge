package tech.proximity.entity;

import tech.proximity.enums.VideoCategory;

public class Video extends BaseEntity implements Comparable<Video>{
    private String title;
    private VideoCategory videoCategory;
    private String checksum;
    private String location;
    private int tagId;
    private int views;
    private int subjectId;
    private int courseId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoCategory getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(VideoCategory videoCategory) {
        this.videoCategory = videoCategory;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public int compareTo(Video video) {
        if(this.views > video.views){
            return -1;
        }else if(this.views < video.views){
            return 1;
        }else{
            return 0;
        }
    }
}
