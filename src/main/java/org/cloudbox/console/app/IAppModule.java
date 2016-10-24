package org.cloudbox.console.app;

/**
 * Created by kevin on 9/28/16.
 */
public interface IAppModule {
    void init(Context ctx);
    void startup(Context ctx);
    Class<? extends IAppService> getServiceType();
    IAppService  getServiceImpl();
}
