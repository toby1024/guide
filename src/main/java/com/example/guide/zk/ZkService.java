package com.example.guide.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.example.guide.netty.simple.Constant.NETTY_ZK_ROOT;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 17:42
 **/
public class ZkService {
  private static final Logger logger = LoggerFactory.getLogger(ZkService.class);

  private CountDownLatch latch = new CountDownLatch(1);

  private String registryAddress;

  private ZooKeeper zk = null;

  public ZkService(String registryAddress) {
    this.registryAddress = registryAddress;
    zk = connectServer();
  }

  public void register(String data) {
    if (data != null) {
      if (zk != null) {
        createNode(data);
      } else {
        logger.error("zk server is not connect");
      }
    }
  }

  /**
   * 连接 zookeeper 服务器
   *
   * @return
   */
  private ZooKeeper connectServer() {
    if (zk == null || !zk.getState().isConnected()) {
      try {
        zk = new ZooKeeper(registryAddress, 1000, new Watcher() {
          @Override
          public void process(WatchedEvent event) {
            if (event.getState() == Event.KeeperState.SyncConnected) {
              latch.countDown();
            }
          }
        });
        latch.await();
      } catch (IOException | InterruptedException e) {
        logger.error(e.getMessage(), e);
      }
    }
    return zk;
  }

  public String listNetty() throws KeeperException, InterruptedException {
    List<String> children = zk.getChildren(NETTY_ZK_ROOT, false);
    for (String s : children) {
      System.out.println(s);
    }
    String s = children.get(0);
    return new String(zk.getData(NETTY_ZK_ROOT.concat("/").concat(s), false, null));
  }

  /**
   * 创建节点
   *
   * @param data
   */
  private void createNode(String data) {
    try {
      if (zk.exists(NETTY_ZK_ROOT, false) == null) {
        zk.create(NETTY_ZK_ROOT, "".getBytes(),
            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      }

      byte[] bytes = data.getBytes();

      String path = zk.create(NETTY_ZK_ROOT + "/", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
      logger.debug("create zookeeper node ({} => {})", path, data);
    } catch (KeeperException | InterruptedException e) {
      logger.error("", e);
    }
  }
}
