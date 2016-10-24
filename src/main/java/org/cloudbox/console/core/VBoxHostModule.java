package org.cloudbox.console.core;

import org.apache.ibatis.session.SqlSession;
import org.cloudbox.console.app.Context;
import org.cloudbox.console.app.IAppModule;
import org.cloudbox.console.app.IAppService;
import org.cloudbox.console.core.dao.VboxHostDAO;
import org.cloudbox.console.core.pojo.VBoxHost;
import org.cloudbox.console.exec.IExecService;
import org.cloudbox.console.utils.DB;

import java.util.List;

/**
 * Created by kevin on 9/28/16.
 */
public enum VBoxHostModule implements IVBoxHostService, IAppModule {
    INSTANCE;

    private IExecService execService;
    private VBoxManager boxManager;

    public static IVBoxHostService getService() {
        return INSTANCE;
    }

    //
    // implements IAppModule
    //
    @Override
    public void init(Context ctx) {
        execService = ctx.getServiceImpl(IExecService.class);
        boxManager = new VBoxManager(execService);
    }

    @Override
    public void startup(Context ctx) {

    }

    @Override
    public Class<? extends IAppService> getServiceType() {
        return IVBoxHostService.class;
    }

    @Override
    public IAppService getServiceImpl() {
        return this;
    }

    //
    // implements IVBoxHostService
    //
    @Override
    public VBoxHost addHost(VBoxHost host) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            dao.createVboxHost(host);
            return host;
        }
    }

    @Override
    public VBoxHost modHost(VBoxHost host) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            dao.modifyVboxHost(host);
            return host;
        }
    }

    @Override
    public List<VBoxHost> getHosts() {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            return dao.getVboxHosts();
        }
    }

    @Override
    public VBoxHost getHostById(int id) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            return dao.getVboxHostById(id);
        }
    }

    @Override
    public void delHosts(List<Integer> ids) {
        try (SqlSession session = DB.getSqlSession(true)) {
            VboxHostDAO dao = session.getMapper(VboxHostDAO.class);
            ids.forEach(dao::deleteVboxHost);
        }
    }


}
