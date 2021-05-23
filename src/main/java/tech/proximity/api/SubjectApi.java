package tech.proximity.api;

import tech.proximity.entity.Subject;
import tech.proximity.service.AppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/v1/course/{courseId}/subject")
public class SubjectApi {
    private AppService appService;

    public SubjectApi() {
        this.appService = AppService.getAppService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubjects(@PathParam("courseId") int courseId, Set<Subject> subjects){
        try{
            appService.createSubjects(courseId, subjects);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
}
