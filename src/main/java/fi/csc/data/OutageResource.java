package fi.csc.data;


import fi.csc.data.model.Outage;
import fi.csc.data.model.Outage_links;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/v1/outage")
@Transactional
public class OutageResource {

     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Outage> all() {
         return Outage.listAll();
     }

     @Path("/links")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Outage_links> allLinks() {
        return Outage_links.listAll();
     }
}
