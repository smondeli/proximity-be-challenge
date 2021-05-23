package tech.proximity.api;

import tech.proximity.entity.Tag;
import tech.proximity.service.AppService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/tag")
public class TagApi {
    private AppService appService;
    public TagApi() {
        this.appService = AppService.getAppService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTag(Tag tag){
        try{
            appService.createTag(tag);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }

    }
}
