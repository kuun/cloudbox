package org.cloudbox.console.app;

import org.cloudbox.console.core.VBoxHostModule;
import org.cloudbox.console.exec.ExecModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 10/1/16.
 */
public class Context {
    private Map<Class<? extends IAppService>, IAppService> services;
    private IAppModule[] modules;

    public Context() {
        modules = new IAppModule[]{
                ExecModule.INSTANCE,
                VBoxHostModule.INSTANCE
        };

        services = new HashMap<>();
        for (IAppModule module : modules) {
            services.put(module.getServiceType(), module.getServiceImpl());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends IAppService> T getServiceImpl(Class<T> clazz) {
        return (T) services.get(clazz);

    }

    void bootstrap() {
        for (IAppModule module : modules) {
            module.init(this);
        }
        for (IAppModule module : modules) {
            module.startup(this);
        }
    }
}
