package org.cloudbox.console.exec;

import org.cloudbox.console.app.IAppService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 9/29/16.
 */
public interface IExecService extends IAppService {
    ScheduledExecutorService getExecutorService();
}
