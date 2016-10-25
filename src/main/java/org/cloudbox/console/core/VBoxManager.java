package org.cloudbox.console.core;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.cloudbox.console.core.pojo.VBoxHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtualbox_5_1.IVirtualBox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by kevin on 9/29/16.
 */
class VBoxManager {
    private static final Logger log = LoggerFactory.getLogger(VBoxManager.class);

    private Map<Integer, VBox> boxes = new HashMap<>();
    private BlockingQueue<VBox> unconnectedBoxes = new LinkedBlockingQueue<>();
    private ReadWriteLock boxesLock = new ReentrantReadWriteLock();
    private ScheduledExecutorService executor;

    public VBoxManager(ScheduledExecutorService executor) {
        executor.execute(() -> {
            while (true) {
                List<VBox> failedBoxes = new LinkedList<>();
                while (true) {
                    try {
                        VBox box = unconnectedBoxes.poll(2, TimeUnit.SECONDS);
                        if (box == null) break;
                        try {
                            box.connect();
                        } catch (Exception e) {
                            log.debug("vbox connecting error: {}", ExceptionUtils.getStackTrace(e));
                            failedBoxes.add(box);
                            continue;
                        }
                        activeVBox(box);
                    } catch (Exception e) {
                        log.info("process unconnected boxes failed: {}", ExceptionUtils.getStackTrace(e));
                    }
                }
                for (VBox box : failedBoxes) {
                    try {
                        unconnectedBoxes.put(box);
                    } catch (InterruptedException e) {
                        log.error("put box to unconnnected queue failed, box: {}, error: {}", box.getHost(), e);
                    }
                }
            }
        });
    }

    void addVBox(VBoxHost host) {
        try {
            boxesLock.writeLock().lock();
            VBox vbox = boxes.get(host.getId());
            if (vbox != null) {
                log.info("vbox is exist, vbox: {}", vbox);
                return;
            }
            vbox = new VBox(host);
            boxes.put(host.getId(), vbox);
            try {
                vbox.connect();
            } catch (Exception e) {
                // add box to unconnected list, retry lately.
                unconnectedBoxes.add(vbox);
                throw e;
            }
        } catch (Exception e) {
            log.warn("add vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            boxesLock.writeLock().unlock();
        }
    }

    void delVBox(int id) {
        try {
            boxesLock.writeLock().lock();

            VBox vbox = boxes.get(id);
            if (vbox == null) {
                log.debug("no vbox found, id: {}", id);
                return;
            }
            boxes.remove(id);
            vbox.disconnect();
        } catch (Exception e) {
            log.warn("delete vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            boxesLock.writeLock().unlock();
        }
    }

    IVirtualBox getVBox(int id) {
        try {
            boxesLock.readLock().lock();
            VBox vbox = boxes.get(id);
            if (vbox == null) {
                return null;
            }
            return vbox.getVBox();
        } catch (Exception e) {
            log.warn("get vbox failed, error: {}", ExceptionUtils.getStackTrace(e));
        } finally {
            boxesLock.readLock().unlock();
        }
        return null;
    }

    private void activeVBox(VBox box) {
        Lock lock = boxesLock.writeLock();
        try {
            lock.lock();
            boxes.put(box.getHost().getId(), box);
        } catch (Exception e) {
            log.error("active box failed, box: {}, error: {}", box.getHost(), e);
        } finally {
            lock.unlock();
        }
    }
}
