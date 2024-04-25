package fi.csc.data;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/v1/service")
@Transactional
public class ServiceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Palvelu> hello() {
        return Palvelu.listAll();
    }
}
