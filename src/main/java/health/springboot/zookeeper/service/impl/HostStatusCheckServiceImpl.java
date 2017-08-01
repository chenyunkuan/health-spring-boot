package health.springboot.zookeeper.service.impl;

import com.dnkx.common.utils.BeanUtil;
import com.dnkx.common.utils.DateUtils;
import health.springboot.zookeeper.model.HostStatusConfig;
import health.springboot.zookeeper.model.Mail;
import health.springboot.zookeeper.service.HostStatusCheckService;
import health.springboot.zookeeper.util.HttpClientUtil;
import health.springboot.zookeeper.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jia on 2017/3/17.
 */
@Service("checkProjectService")
public class HostStatusCheckServiceImpl implements HostStatusCheckService{
	@Resource
	private HostStatusConfig hostStatusConfig;
	@Resource
	private Mail mail;
	@Resource
	private MailUtil mailUtil;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final String HEALTH_CHECK_PAHT = "/health/ok.do";
	@Override
	public String checkProject(String hostUrl){
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		try {
			return httpClientUtil.getResponseBodyAsString("http://"+hostUrl+HEALTH_CHECK_PAHT,null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return "failed";
	}
	@Override
	public String checkAllProject(){
		//将对象转换成Map,去null值
		Map<String, Object> hostMap = BeanUtil.bean2map(hostStatusConfig);
		logger.info("map is {}",hostMap);
		Set<Map.Entry<String, Object>> entries = hostMap.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
		int okHostQty = 0;//ok数量
		StringBuilder messageBuilder = new StringBuilder();
		//遍历host,确认
		while (iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			String hostName = entry.getKey();
			String hostIp = entry.getValue().toString();
			String result = checkProject(hostIp);
			if ("ok".equals(result)){
				okHostQty += 1;
				messageBuilder.append("<span style='font-weight:bold'>" + hostName + "</span>(" + hostIp + ") : result is <span style='font-weight:bold'>" + result + "</span><br/>");
			}else {
				messageBuilder.append("<span style='color: red;'>" + hostName + "(" + hostIp + ") : result is " + result + "</span><br/>");
			}
			logger.info("host {} was checked and result is {}",hostName,result);
		}
		messageBuilder.insert(0,"<span style='font-weight: bold;font-size: 18px'>total " + hostMap.size() + " host was checked, " + (hostMap.size()-okHostQty) + " host is failed</span><br/>");
		if (okHostQty < hostMap.size()){
			//发送邮件
			mail.setSubject(DateUtils.dateToString(new Date(), "YYYY-MM-dd")+" 线上应用节点健康检查");
			mail.setMessage(messageBuilder.toString());
			mailUtil.send(mail);
		}
		logger.info(messageBuilder.toString());
		return messageBuilder.toString();
	}
}
