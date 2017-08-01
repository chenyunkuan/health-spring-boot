package health.springboot.zookeeper.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by jia on 2017/3/16.
 */
@Component
@ConfigurationProperties(prefix="hostStatus")
public class HostStatusConfig implements Serializable {
	/*	online.apphost: http://app2.dounikaixin.cn
        online.cashier1host: http://cashier.dounikaixin.cn
        online.cashier2host: 139.196.145.158
        online.mng1host:  http://admin.dounikaixin.cn
        online.mng2host: 139.224.25.215*/
	private String  app1host;
	private String  app2host;
	private String cashier1host;
	private String cashier2host;
	private String mng1host;
	private String mng2host;
	private String settlement1host;
	private String settlement2host;
	private String commerce1host;
	private String commerce2host;
	private String site1host;
	private String site2host;

	public String getSite1host() {
		return site1host;
	}

	public void setSite1host(String site1host) {
		this.site1host = site1host;
	}

	public String getSite2host() {
		return site2host;
	}

	public void setSite2host(String site2host) {
		this.site2host = site2host;
	}

	public String getApp1host() {
		return app1host;
	}

	public void setApp1host(String app1host) {
		this.app1host = app1host;
	}

	public String getApp2host() {
		return app2host;
	}

	public void setApp2host(String app2host) {
		this.app2host = app2host;
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

	public String getSettlement1host() {
		return settlement1host;
	}

	public void setSettlement1host(String settlement1host) {
		this.settlement1host = settlement1host;
	}

	public String getSettlement2host() {
		return settlement2host;
	}

	public void setSettlement2host(String settlement2host) {
		this.settlement2host = settlement2host;
	}

	public String getCommerce1host() {
		return commerce1host;
	}

	public void setCommerce1host(String commerce1host) {
		this.commerce1host = commerce1host;
	}

	public String getCommerce2host() {
		return commerce2host;
	}

	public void setCommerce2host(String commerce2host) {
		this.commerce2host = commerce2host;
	}
}
