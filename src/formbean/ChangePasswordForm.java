package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import sun.security.krb5.internal.PAData.SaltAndParams;

public class ChangePasswordForm extends FormBean {
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String type;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = sanitize(currentPassword);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = sanitize(newPassword);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = sanitize(confirmPassword);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (currentPassword == null || currentPassword.trim().length() == 0) {
			errors.add("Original password is required.");
		}

		if (newPassword == null || newPassword.trim().length() == 0) {
			errors.add("New password is required.");
		}

		if (confirmPassword == null || confirmPassword.trim().length() == 0) {
			errors.add("Confirm password is required.");
		}

		if (type == null)
			errors.add("Button is required.");

		if (!newPassword.equals(confirmPassword)) {
			errors.add("New Password and Confirm Password do not match.");
		}

		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}
