package org.strotmann.tcg;

public class Benutzer {
	
	private String userName;
	private String tcgPasswort;
	private String mailServer;
	private String mailFrom;
	private String mailUser;
	private String mailPasswort;
	
	public Benutzer(String userName, String tcgPasswort, String mailServer,
			String mailFrom, String mailUser, String mailPasswort) {
		super();
		this.userName = userName;
		this.tcgPasswort = tcgPasswort;
		this.mailServer = mailServer;
		this.mailFrom = mailFrom;
		this.mailUser = mailUser;
		this.mailPasswort = mailPasswort;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTcgPasswort() {
		return tcgPasswort;
	}
	public void setTcgPasswort(String tcgPasswort) {
		this.tcgPasswort = tcgPasswort;
	}
	public String getMailServer() {
		return mailServer;
	}
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	public String getMailFrom() {
		return mailFrom;
		
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailUser() {
		return mailUser;
	}
	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}
	public String getMailPasswort() {
		return mailPasswort;
	}
	public void setMailPasswort(String mailPasswort) {
		this.mailPasswort = mailPasswort;
	}

}
