package health.springboot.zookeeper.controller;

import health.springboot.zookeeper.service.HostStatusCheckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/19.
 */
@RestController
@RequestMapping("/hostStatus")
public class HostStatusCheckController {
    @Resource
    private HostStatusCheckService hostStatusCheckService;

    @RequestMapping("/hostStatusCheck")
    public String hostStatusCheck() {
        return hostStatusCheckService.checkAllProject();
    }
}
