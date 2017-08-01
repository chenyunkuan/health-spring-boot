package health.springboot.zookeeper.service;

/**
 * Created by Administrator on 2017/3/19.
 */
public interface ZookeeperCheckService {
    /**
     * zookeeper健康检查
     * @return
     */
    String healthCheck();
}
