package com.smart;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2020/2/5 12:05
 */
public class zkDemo {

    @Test
    public void test() throws Exception {

        // 1. 创建zookeeper连接
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 2000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了" + watchedEvent.getType() + "的事件");
            }
        });

        // 2. 创建父节点
        String path = zooKeeper.create("/smartStudy", "studyForZK".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);

        // 3. 创建子节点
        String childPath = zooKeeper.create("/smartStudy/child", "childValue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(childPath);

        // 4. 获取节点中的值（父节点）
        byte[] dataF = zooKeeper.getData("/smartStudy", false, null);
        System.out.println(new String(dataF));
        List<String> children = zooKeeper.getChildren("/smartStudy", false);
        for (String child : children) {
            System.out.println(child);
        }

        // 5. 修改节点的值
        Stat stat = zooKeeper.setData("/smartStudy", "setUpdate".getBytes(), -1);
        System.out.println(stat);

        // 6. 判断某个节点是否存在
        Stat exists = zooKeeper.exists("/smartStudy/child", false);
        System.out.println(exists);

        // 7. 删除节点
        zooKeeper.delete("/smartStudy/child", -1);

    }
}
