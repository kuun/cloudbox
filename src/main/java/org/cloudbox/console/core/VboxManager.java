package org.cloudbox.console.core;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloudbox.console.core.pojo.VBoxHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtualbox_5_1.IVirtualBox;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by kevin on 9/29/16.
 */
class VboxManager {
    private static final Logger log = LoggerFactory.getLogger(VboxManager.class);

    private Map<Integer, VBox> boxes = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    void addVbox(VBoxHost host) {
        try {
            lock.writeLock().lock();
            VBox vbox = boxes.get(host.getId());
            if (vbox != null) {
                log.info("vbox is exist, vbox: {}", vbox);
                return;
            }
            vbox = new VBox(host);
            boxes.put(host.getId(), vbox);
            vbox.connect();
        } catch (Exception e) {
            log.warn("add vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            lock.writeLock().unlock();
        }
    }

    void delVbox(int id) {
        try {
            lock.writeLock().lock();

            VBox vbox = boxes.get(id);
            if (vbox == null) {
                log.debug("no vbox found, id: {}", id);
                return;
            }
            boxes.remove(id);
            vbox.disconnect();
        } catch (Exception e){
            log.warn("delete vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            lock.writeLock().unlock();
        }
    }

    IVirtualBox getVBox(int id) {
        try {
            lock.readLock().lock();
            VBox vbox = boxes.get(id);
            if (vbox == null) {
                return null;
            }
            return vbox.getVBox();
        } catch (Exception e) {
            log.warn("get vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }

}
