package health.springboot.zookeeper.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by jia on 2017/3/16.
 */
@Component
@ConfigurationProperties(prefix="online")
public class OnlineConfig implements Serializable {

	private String  apphost;
	private String cashier1host;
	private String cashier2host;
	private String mng1host;
	private String mng2host;
	private int port;
	private String name;
	private String password;
	private String command;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getApphost() {
		return apphost;
	}

	public void setApphost(String apphost) {
		this.apphost = apphost;
	}

	public String getCashier1host() {
		return cashier1host;
	}

	public void setCashier1host(String cashier1host) {
		this.cashier1host = cashier1host;
	}

	public String getCashier2host() {
		return cashier2host;
	}

	public void setCashier2host(String cashier2host) {
		this.cashier2host = cashier2host;
	}

	public String getMng1host() {
		return mng1host;
	}

	public void setMng1host(String mng1host) {
		this.mng1host = mng1host;
	}

	public String getMng2host() {
		return mng2host;
	}

	public void setMng2host(String mng2host) {
		this.mng2host = mng2host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
