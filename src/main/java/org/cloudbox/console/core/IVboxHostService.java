package org.cloudbox.console.core;

import org.cloudbox.console.core.pojo.VBoxHost;

import java.util.List;

/**
 * Created by kevin on 9/28/16.
 */
public interface IVboxHostService {
    // add a vbox host to system
    VBoxHost addHost(VBoxHost host);
    // modify a vbox host in system
    VBoxHost modHost(VBoxHost host);
    // get all vbox hosts in system
    List<VBoxHost> getHosts();
    // get vbox host by host id
    VBoxHost getHostById(int id);
    // delete vbox hosts by host id
    void delHosts(List<Integer> ids);
}
