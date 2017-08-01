package health.springboot.zookeeper.service;

/**
 * Created by Administrator on 2017/3/19.
 */
public interface HostStatusCheckService {
    /**
     * 检查指定节点的健康状态
     * @param hostUrl
     * @return
     */
    String checkProject(String hostUrl);

    /**
     * 检查配置中全部节点的健康状态
     * @return
     */
    String checkAllProject();
}
