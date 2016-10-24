package org.cloudbox.console.exec;

import org.cloudbox.console.app.Context;
import org.cloudbox.console.app.IAppModule;
import org.cloudbox.console.app.IAppService;

import java.util.concurrent.*;

/**
 * Created by kevin on 9/29/16.
 */
public enum ExecModule implements IExecService, IAppModule {
    INSTANCE;

    private ScheduledExecutorService executor;

    //
    // implements IAppModule
    //
    @Override
    public void init(Context ctx) {
        executor = new ScheduledThreadPoolExecutor(8);
    }

    @Override
    public void startup(Context ctx) {

    }

    @Override
    public Class<? extends IAppService> getServiceType() {
        return IExecService.class;
    }

    @Override
    public IAppService getServiceImpl() {
        return this;
    }


    @Override
    public ScheduledExecutorService getExecutorService() {
        return executor;
    }
}
