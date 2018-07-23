package com.ilucky.test.share.threadpool;

public class Bo {

	private String account;
	private String uuid;
	private String ip;
	private String country;
	private String province;
	private String city;
	
	public Bo(String account, String uuid, String ip, String country, String province, String city) {
		super();
		this.account = account;
		this.uuid = uuid;
		this.ip = ip;
		this.country = country;
		this.province = province;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Bo [account=" + account + ", uuid=" + uuid + ", ip=" + ip + ", country=" + country + ", province="
				+ province + ", city=" + city + "]";
	}
}
