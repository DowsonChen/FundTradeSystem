package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerForm extends FormBean {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String confirmPassword;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String cash;
	private String type;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		System.out.println("Set name : " + firstName);
		this.firstName = sanitize(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = sanitize(lastName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = sanitize(userName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = sanitize(password);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = sanitize(confirmPassword);
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = sanitize(address1);
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = sanitize(address2);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = sanitize(city);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = sanitize(state);
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = sanitize(zip);
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = sanitize(cash);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = sanitize(type);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.trim().length() == 0)
			errors.add("User Name is required.");

		if (firstName == null || firstName.trim().length() == 0)
			errors.add("First Name is required.");

		if (lastName == null || lastName.trim().length() == 0)
			errors.add("Last Name is required.");

		if (password == null || password.trim().length() == 0)
			errors.add("Password is required.");

		if (confirmPassword == null || confirmPassword.trim().length() == 0)
			errors.add("Confirm password is required.");

		if (!password.equals(confirmPassword))
			errors.add("Password and Confirm password is not the same.");

		if (address1 == null || address1.trim().length() == 0)
			errors.add("Address1 is required.");

		if (address2 == null || address2.trim().length() == 0)
			errors.add("Address2 is required.");

		if (city == null || city.trim().length() == 0)
			errors.add("City is required.");

		if (state == null || state.trim().length() == 0)
			errors.add("State is required.");

		if (cash == null || cash.trim().length() == 0 || cash.charAt(0) == '-')
			errors.add("Cash is required and it should be positive.");

		if (type == null)
			errors.add("Button is required.");

		return errors;
	}

	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}
