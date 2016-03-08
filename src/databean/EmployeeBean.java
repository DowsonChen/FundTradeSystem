package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class EmployeeBean {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	private String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public void setUserName(String v) {
		userName = v;
	}

	public void setFirstName(String v) {
		firstName = v;
	}

	public void setLastName(String v) {
		lastName = v;
	}

	public void setPassword(String v) {
		password = v;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}
}
