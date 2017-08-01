package health.springboot.zookeeper.controller;

import health.springboot.zookeeper.service.ZookeeperCheckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/19.
 */
@RestController
@RequestMapping("/zookeeper")
public class ZookeeperCheckController {
    @Resource
    private ZookeeperCheckService zookeeperCheckService;

    @RequestMapping("/zookeeperCheck")
    public String zookeeperCheck() {
        return zookeeperCheckService.healthCheck();
    }
}
