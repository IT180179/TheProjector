package com.example;

import com.example.helper.DetailPresentationHelper;
import com.example.helper.PPKMeetingHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;

@Path("/ppt")
public class PowerPointResource {

    @GET
    @Path("/ppk")
    @Produces("application/vnd.openxmlformats-officedocument.presentationml.presentation")
    public Response ppk()
            throws FileNotFoundException, InvalidClassException, IOException{
        ByteArrayInputStream bis = PPKMeetingHelper.generatePPKPresentation();
        return  Response.ok(bis).header("content-disposition",
                "attachment; filename = PPKMeeting.pptx").build();
    }

    @GET
    @Path("/detail")
    @Produces("application/vnd.openxmlformats-officedocument.presentationml.presentation")
    public Response detail()
            throws FileNotFoundException, InvalidClassException, IOException{
        ByteArrayInputStream bis = DetailPresentationHelper.generateDetailPresentation();
        return  Response.ok(bis).header("content-disposition",
                "attachment; filename = DetailPräsentation.pptx").build();
    }

}
