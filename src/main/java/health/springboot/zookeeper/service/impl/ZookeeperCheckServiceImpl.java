package health.springboot.zookeeper.service.impl;

import com.dnkx.common.utils.DateUtils;
import health.springboot.zookeeper.model.OnlineConfig;
import health.springboot.zookeeper.service.ZookeeperCheckService;
import health.springboot.zookeeper.util.SSHHelper;
import health.springboot.zookeeper.model.Mail;
import health.springboot.zookeeper.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by jia on 2017/3/16.
 */
@Service
public class ZookeeperCheckServiceImpl implements ZookeeperCheckService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    MailUtil mailUtil;
    @Resource
    SSHHelper sshHelper;
    @Resource
    Mail mail;
    @Resource
    OnlineConfig onlineConfig;
    @Override
    public String healthCheck() {
        String message;
        String status;
        int success = 0;
        String name = onlineConfig.getName();
        String password = onlineConfig.getPassword();
        int port = onlineConfig.getPort();
        String command = onlineConfig.getCommand();
        logger.info("Execute the script : zookeeperHealth , write into file:zookeeperCheck");
        String mng1 = sshHelper.exec(onlineConfig.getMng1host(), name, password, port, command);
        message = onlineConfig.getMng1host() + " mng1 :" + mng1 + "<br><br>";
        success = isNormal(mng1, success);
        String mng2 = sshHelper.exec(onlineConfig.getMng2host(), name, password, port, command);
        message += onlineConfig.getMng2host() + " mng2 :" + mng2 + "<br><br>";
        success = isNormal(mng2, success);
        String cashier1 = sshHelper.exec(onlineConfig.getCashier1host(), name, password, port, command);
        message += onlineConfig.getCashier1host() + " cashier1 :" + cashier1 + "<br><br>";
        success = isNormal(cashier1, success);
        String cashier2 = sshHelper.exec(onlineConfig.getCashier2host(), name, password, port, command);
        message += onlineConfig.getCashier2host() + " cashier2 :" + cashier2 + "<br><br>";
        success = isNormal(cashier2, success);
        String app = sshHelper.exec(onlineConfig.getApphost(), name, password, port, command);
        message += onlineConfig.getApphost() + " app :" + app + "<br><br>";
        success = isNormal(app, success);
        logger.info("read file : zookeeperCheck ,result is : {}", message);
        mail.setSubject(DateUtils.dateToString(new Date(), "YYYY-MM-dd") + " 线上zookeeper健康检查");
        status = "<span style='font-weight: bold;font-size: 18px'>total 5 zookeepers was checked, " + (5-success) + " zookeepers is failed</span><br/>";
        mail.setMessage(status + message);
        mailUtil.send(mail);
        return mail.getMessage();
    }

    private int isNormal(String message, int success) {
        if (message.contains("follower") || message.contains("standalone") || message.contains("leader")) {
            return success += 1;
        }
        return success;
    }

}
