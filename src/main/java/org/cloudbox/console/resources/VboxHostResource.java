package org.cloudbox.console.resources;

import org.apache.ibatis.session.SqlSession;
import org.cloudbox.console.api.VboxHost;
import org.cloudbox.console.dao.VboxHostDAO;
import org.cloudbox.console.utils.DB;
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

    @GET
    @Path("/{id}")
    public Object getVboxHost(@PathParam("id") Integer id) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            return dao.getVboxHostById(id);
        }
    }

    @GET
    public List<VboxHost> getVboxHosts() {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            return dao.getVboxHosts();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VboxHost create(VboxHost vboxHost) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            dao.createVboxHost(vboxHost);
            return vboxHost;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public VboxHost modify(VboxHost vboxHost) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            dao.modifyVboxHost(vboxHost);
            return vboxHost;
        }
    }

    @DELETE
    public void delete(@QueryParam("id") int id) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            dao.deleteVboxHost(id);
        }
    }
}
