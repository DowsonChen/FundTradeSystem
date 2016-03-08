package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewCustomerTransactionForm extends FormBean {
        private String userName;
        private String action;

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = sanitize(userName);
        }
        
        public String getAction() {
                return action;
        }

        public void setAction(String action) {
                this.action = sanitize(action);
        }
        
        public boolean isPresent() {
                return action != null;
        }

        public List<String> getValidationErrors() {
                List<String> errors = new ArrayList<String>();
                
                if (userName == null || userName.trim().length() == 0)
                        errors.add("User name is required.");

                if (errors.size() > 0) {
                        return errors;
                }
                
                if (!action.equals("viewCustomerTransaction")) {
                        errors.add("Invalid Action.");
                }

                if (userName.matches(".*[<>\"].*")) {
                        errors.add("User Name may not contain angle brackets or quotes.");
                }            
                return errors;
        }

        private String sanitize(String s) {
                return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
        }
}
