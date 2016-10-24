package org.cloudbox.console.core.web;

import org.cloudbox.console.core.IVBoxHostService;
import org.cloudbox.console.core.VBoxHostModule;
import org.cloudbox.console.core.pojo.VBoxHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by kevin on 8/11/16.
 */
@Path("/vbox/host")
@Produces(MediaType.APPLICATION_JSON)
public class VBoxHostResource {
    private static final Logger log = LoggerFactory.getLogger(VBoxHostResource.class);
    private static final IVBoxHostService service = VBoxHostModule.getService();

    @GET
    @Path("/{id}")
    public VBoxHost getVBoxHost(@PathParam("id") Integer id) {
        return service.getHostById(id);
    }

    @GET
    public List<VBoxHost> getVBoxHosts() {
        return service.getHosts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VBoxHost create(VBoxHost vboxHost) {
        return service.addHost(vboxHost);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public VBoxHost modify(VBoxHost vboxHost) {
        return service.modHost(vboxHost);
    }

    @DELETE
    public void delete(@QueryParam("ids") List<Integer> ids) {
        service.delHosts(ids);
    }
}
