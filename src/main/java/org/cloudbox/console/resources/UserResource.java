package org.cloudbox.console.resources;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.cloudbox.console.api.User;
import org.cloudbox.console.dao.UserDAO;
import org.cloudbox.console.utils.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by kevin on 7/27/16.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @GET
    public Object listUser(@QueryParam("name") String name) {
        return name == null ? getAllUsers() : getUserByName(name);
    }

    @POST
    public void create(User user) {
        insertUser(user);
    }

    @PUT
    public void modifyPasswd(@QueryParam("name") String name,
                             @QueryParam("oldPasswd") String oldPasswd,
                             @QueryParam("newPasswd") String newPasswd) {
        try {
            doModifyPasswd(name, oldPasswd, newPasswd);
        } catch (WebApplicationException e) {
            log.info("error: {}" + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @DELETE
    public void deleteUser(@QueryParam("ids[]") List<Integer> ids) {
        SqlSession session = null;
        try {
            session = DB.getSqlSession(false);
            log.debug("deleted ids: {}", ids);
            UserDAO dao = session.getMapper(UserDAO.class);
            for (int id : ids) {
                User user = dao.getUserById(id);
                if (user == null) {
                    log.debug("can't find user, id: {}", id);
                    throw new WebApplicationException("can't find user, id: " + id, Response.Status.NOT_FOUND);
                }
                if (user.getName().equals("security")) {
                    log.debug("can't delete user: security");
                    throw new WebApplicationException("can't delete user: security", Response.Status.FORBIDDEN);
                }
                dao.deleteUser(id);
            }
            session.commit();
        } catch (Exception e) {
            log.info("delete user error: {}", ExceptionUtils.getStackTrace(e));
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }

    }

    private User getUserByName(String name) {
        try (SqlSession session = DB.getSqlSession(true)) {
            UserDAO dao = session.getMapper(UserDAO.class);
            return dao.getUserByName(name);
        }
    }

    private List<User> getAllUsers() {
        try (SqlSession session = DB.getSqlSession(true)) {
            UserDAO dao = session.getMapper(UserDAO.class);
            return dao.getAllUsers();
        }
    }

    private void insertUser(User user) {
        try (SqlSession session = DB.getSqlSession(true)) {
            UserDAO dao = session.getMapper(UserDAO.class);
            dao.insert(user);
        }
    }

    private void doModifyPasswd(String name, String oldPasswd, String newPasswd) {
        try (SqlSession session = DB.getSqlSession(true)) {
            UserDAO dao = session.getMapper(UserDAO.class);
            User user = dao.getUserByName(name);
            if (user == null) {
                throw new WebApplicationException("user name: " + name, Response.Status.NOT_FOUND);
            }
            dao.modifyPasswd(name, newPasswd);
        }
    }
}
