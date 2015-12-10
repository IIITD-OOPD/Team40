package oopd.Team40.data;

public class DBProperties {
	private String url;
	private String user;
	private String passwd;

	public DBProperties(String url, String user, String passwd) {
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPasswd() {
		return passwd;
	}

}
