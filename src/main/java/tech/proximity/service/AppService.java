package tech.proximity.service;

import tech.proximity.dto.SearchResult;
import tech.proximity.entity.Course;
import tech.proximity.entity.Subject;
import tech.proximity.entity.Tag;
import tech.proximity.entity.Video;
import tech.proximity.enums.VideoCategory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppService {

    private static AppService appService;
    private int courseId = 1;
    private int subjectId = 1;
    private int tagId = 1;
    private int videoId = 1;
    private int videoLocationId = 1;
    private Map<Integer, Course> courses = new HashMap<>();
    private Set<Subject> subjects = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Map<Integer, Video> videos = new HashMap<>();

    private AppService() {
    }

    public static AppService getAppService(){
        if(appService == null) {
            appService = new AppService();
        }
        return appService;
    }

    public void createCourse(Course course){
        course.setId(courseId++);
        courses.put(course.getId(), course);
        System.out.println("Created course "+course.getName());
    }

    public void createSubjects(int courseId, Set<Subject> subjects){
        for(Subject subject : subjects){
            subject.setId(subjectId++);
            subject.setCourseId(courseId);
            subjects.add(subject);
            System.out.println("Created subject "+subject.getName());
        }
    }

    public void createTag(Tag tag){
        tag.setId(tagId++);
        tags.add(tag);
        System.out.println("Created tag "+tag.getName());
    }

    public void uploadVideo(int courseId, int subjectId, Video video){
        video.setId(videoId++);
        video.setCourseId(courseId);
        video.setSubjectId(subjectId);
        List<Video> existingVideo = videos.values().stream().filter(v -> v.getChecksum().equalsIgnoreCase(video.getChecksum())).collect(Collectors.toList());
        if(existingVideo.size() > 0){
            video.setLocation(existingVideo.get(0).getLocation());
        }else{
            video.setLocation(""+videoLocationId++);
        }
        videos.put(video.getId(), video);
        System.out.println("Uploaded video "+video.getTitle());
    }

    public List<Course> getCourses(boolean mostViewed) {
        if(mostViewed){
            return courses.values().stream().sorted().limit(3).collect(Collectors.toList());
        }else{
            return courses.values().stream().collect(Collectors.toList());
        }

    }

    public SearchResult getVideos(String title, int courseId, int subjectId, int tagId, boolean mostViewedVideos) {
        SearchResult searchResult = new SearchResult();
        Stream<Video> videosStream = videos.values().stream();
        if(title != null && !title.isEmpty()){
            videosStream = videosStream.filter(v -> v.getTitle().contains(title));
        }
        if(courseId > 0){
            videosStream = videosStream.filter(v -> v.getCourseId() == courseId);
        }
        if(subjectId > 0){
            videosStream = videosStream.filter(v -> v.getSubjectId() == subjectId);
        }
        if(tagId > 0){
            videosStream = videosStream.filter(v -> v.getTagId() == tagId);
        }
        Set<Video> filteredVideos = videosStream.collect(Collectors.toSet());
        Set<Video> webinars = filteredVideos.stream().filter(v -> v.getVideoCategory().equals(VideoCategory.WEBINAR)).collect(Collectors.toSet());
        Set<Video> lectures = filteredVideos.stream().filter(v -> v.getVideoCategory().equals(VideoCategory.LECTURE)).collect(Collectors.toSet());
        if(mostViewedVideos){
            webinars = webinars.stream().sorted().limit(3).collect(Collectors.toSet());
            lectures = lectures.stream().sorted().limit(3).collect(Collectors.toSet());
        }
        searchResult.setWebinars(webinars);
        searchResult.setLectures(lectures);
        return searchResult;
    }

    public Set<Video> getPersonalizedSuggestions(int id, boolean suggestions){
        if(suggestions){
            return videos.values().stream().filter(v -> v.getTagId() == videos.get(id).getTagId()).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    public Video getVideoById(int id){
        Video video = videos.get(id);
        if(video != null){
            video.setViews(video.getViews()+1);
            Course course = courses.get(video.getCourseId());
            if(course != null){
                course.setViews(course.getViews()+1);
            }
        }
        return video;
    }
}
