package fi.csc.data;

//import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.core.Context;

import java.util.List;
import java.util.Optional;

@Path("/v2/services")
@Transactional
public class ServiceResource {

    @Inject
    PalveluService ps;

    @Context
    HttpServerRequest request;

    /**
     * Lista CSC palveluista
     *
     * @return JSON muotoisen tekstiä sisältävän listan.
     */
    @Path("/text")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@CacheResult(cacheName = "palvelu-cache")
    public List<PalveluC> all() {
        ApplicationLifecycle.päivitä(request.headers().get("User-Agent"));
        return ApplicationLifecycle.siivottuNone;
    }

    /**
     * Lista CSC palveluista niin kuin se on snowssa
     *
     * @return JSON muotoisen HTMLaa sisältävän listan.
     */
    @Path("/html")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@CacheResult(cacheName = "palvelu-cache")
    public List<PalveluC> kamalaa() {
        ApplicationLifecycle.päivitä(request.headers().get("User-Agent"));
        return ApplicationLifecycle.html;
    }

    /**
     * Kun pyynnössä on polussa URN palautetaan vastaava palvelu
     *
     * @param urn String palvelun URN
     * @return yksittäisen palvelun tekstina (kommenteissa HTML)
     */
    @Path("/{urn}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PalveluC getPalvelu(@PathParam("urn") String urn) {
        //return ps.getByURN(urn);
        return ApplicationLifecycle.htsp.get(urn);
    }
}
