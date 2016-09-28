package org.cloudbox.console.core;

import org.cloudbox.console.core.pojo.VBoxHost;
import org.virtualbox_5_1.IVirtualBox;
import org.virtualbox_5_1.VirtualBoxManager;

/**
 * Created by kevin on 9/29/16.
 */
public class VBox {
    private VBoxHost host;
    private VirtualBoxManager manager;

    public VBox(VBoxHost host) {
        this.host = host;
        manager = VirtualBoxManager.createInstance("");
    }

    public void connect() {
        String url = "http://" + host.getIp() + ":" + host.getPort();
        manager.connect(url, host.getUser(), host.getPasswd());
    }

    public void disconnect() {
        manager.disconnect();
    }

    public IVirtualBox getVBox() {
        return manager.getVBox();
    }
}
