package tech.proximity.api;

import tech.proximity.service.AppService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/search")
public class SearchApi {
    private AppService appService;
    public SearchApi() {
        this.appService = AppService.getAppService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterVideos(@QueryParam("title") String title,
                                 @QueryParam("course") int courseId,
                                 @QueryParam("subject") int subjectId,
                                 @QueryParam("tag") int tagId,
                                 @QueryParam("mostViewedVideos") boolean mostViewedVideos){
        try{
            return Response.ok(appService.getVideos(title, courseId, subjectId, tagId, mostViewedVideos)).build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{videoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonalizedSuggestions(@PathParam("videoId") int id,
                                               @QueryParam("suggestions") boolean suggestions){
        try{
            return Response.ok(appService.getPersonalizedSuggestions(id, suggestions)).build();
        }catch (Exception e){
            return Response.serverError().build();
        }

    }
}
