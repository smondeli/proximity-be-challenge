package tech.proximity.api;

import tech.proximity.entity.Video;
import tech.proximity.service.AppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/course/{courseId}/subject/{subjectId}/video")
public class VideoApi {
    private AppService appService;
    public VideoApi() {
        this.appService = AppService.getAppService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadVideo(@PathParam("courseId") int courseId,
                                @PathParam("subjectId") int subjectId,
                                Video video){
        try{
            appService.uploadVideo(courseId, subjectId, video);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }

    }

    @GET
    @Path("{videoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideoById(@PathParam("videoId") int videoId){
        try {
            return Response.ok(appService.getVideoById(videoId)).build();
        }catch (Exception e){
            return Response.serverError().build();
        }

    }
}
