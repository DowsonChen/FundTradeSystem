package controller;


import javax.servlet.http.HttpServletRequest;


import model.EmployeeDAO;
import model.Model;


public class EmployeeHomeAction extends Action {
	private EmployeeDAO employeeDAO;

	public EmployeeHomeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "employeeHome.do";
	}

	public String perform(HttpServletRequest request) {
		return "employeeHome.jsp";
	}
}
