package tech.proximity.api;

import tech.proximity.entity.Course;
import tech.proximity.service.AppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/course")
public class CourseApi {
    private AppService appService;
    public CourseApi() {
        this.appService = AppService.getAppService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCourse(Course course){
        try{
            appService.createCourse(course);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostViewedCourses(@QueryParam("mostViewed") boolean mostViewed){
        try{
            return Response.ok(appService.getCourses(mostViewed)).build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
}
