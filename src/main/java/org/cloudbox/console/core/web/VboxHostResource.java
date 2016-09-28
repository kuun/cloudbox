package org.cloudbox.console.core.web;

import org.cloudbox.console.core.IVboxHostService;
import org.cloudbox.console.core.VboxHostModule;
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
public class VboxHostResource {
    private static final Logger log = LoggerFactory.getLogger(VboxHostResource.class);
    private static final IVboxHostService service = VboxHostModule.getService();

    @GET
    @Path("/{id}")
    public VBoxHost getVboxHost(@PathParam("id") Integer id) {
        return service.getHostById(id);
    }

    @GET
    public List<VBoxHost> getVboxHosts() {
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
