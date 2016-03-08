package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeForm extends FormBean {
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String confirmPassword;
        private String type;

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
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

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = sanitize(type);
        }

        public List<String> getValidationErrors() {
                List<String> errors = new ArrayList<String>();

                if (userName == null || userName.trim().length() == 0)
                        errors.add("User name is required.");
                if (firstName == null || firstName.trim().length() == 0)
                        errors.add("First name is required.");
                if (lastName == null || lastName.trim().length() == 0)
                        errors.add("Last name is required.");
                if (password == null || password.trim().length() == 0)
                        errors.add("Password is required.");
                if (confirmPassword == null || confirmPassword.trim().length() == 0)
                        errors.add("Confirm password is required.");
                if (password != null && confirmPassword != null && !password.equals(confirmPassword))
                        errors.add("Password and Confirm password are not same.");
                if (type == null)
                        errors.add("Button is required");

                return errors;
        }

        private String sanitize(String s) {
                return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
        }
}
