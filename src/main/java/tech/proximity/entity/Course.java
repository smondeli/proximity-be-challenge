package tech.proximity.entity;

public class Course extends BaseEntity implements Comparable<Course>{
    private String name;
    private int views;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public int compareTo(Course course) {
        if(this.views > course.views){
            return -1;
        }else if(this.views < course.views){
            return 1;
        }else{
            return 0;
        }
    }
}
