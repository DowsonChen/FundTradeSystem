package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerDAO cDAO;
	EmployeeDAO eDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }
    
	public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new CreateFundAction(model));
        Action.add(new ChangePasswordAction(model));
        Action.add(new CreateEmployeeAction(model));
        Action.add(new CreateCustomerAction(model));
        Action.add(new RequestCheckAction(model));
        Action.add(new DepositCheckAction(model));
        Action.add(new TransactionHistoryAction(model));
        Action.add(new ViewCustomerTransactionAction(model));
        Action.add(new EmployeeHomeAction(model));
        Action.add(new CustomerHomeAction(model));
        Action.add(new SellFundAction(model));
        Action.add(new BuyFundAction(model));
        Action.add(new ViewCustomerAction(model));
        Action.add(new ResetCustPassAction(model));
        Action.add(new TransitionDayAction(model));
        Action.add(new ResearchFundAction(model));
        Action.add(new LogUserOutAction(model));
        
        eDAO = model.getEmployeeDAO();
        cDAO = model.getCustomerDAO();
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = performTheAction(request);
		//System.out.println(nextPage);

		sendToNextPage(nextPage, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		//System.out.println(action);
		if (session.getAttribute("user") == null) {

			return Action.perform("login.do", request);
		}

		if (action.equals("start")) {
			if (session.getAttribute("user") instanceof CustomerBean)
				return Action.perform("customerMain.do", request);
			else
				return Action.perform("employeeMain.do", request);
		}
		
		if(session.getAttribute("user") != null && !action.equals("logUserOut.do") ){
			if(session.getAttribute("user") instanceof CustomerBean){
				CustomerBean cb = (CustomerBean) session.getAttribute("user");
				CustomerBean tmp = cDAO.read(cb.getUserName());
				if(!request.getCookies()[0].getValue().equals(tmp.getCookie())){
					List<String> errors = new ArrayList<String>();
					errors.add("Session Terminated!");
					request.setAttribute("user", session.getAttribute("user"));
					request.setAttribute("errors", errors);
					session.setAttribute("user", null);
					return "terminated.jsp";
				}
				
			} else if(session.getAttribute("user") instanceof EmployeeBean){
				EmployeeBean eb = (EmployeeBean) session.getAttribute("user");
				EmployeeBean tmp;
				//System.out.println(request.getCookies()[0].getValue());
				try {
					tmp = eDAO.read(eb.getUserName());
					if(!request.getCookies()[0].getValue().equals(tmp.getCookie())){
						List<String> errors = new ArrayList<String>();
						errors.add("Session Terminated!");
						request.setAttribute("user", session.getAttribute("user"));
						request.setAttribute("errors", errors);
						session.setAttribute("user", null);
						return "terminated.jsp";
					}
				} catch (RollbackException e) {
					e.printStackTrace();
					return Action.perform("login.do", request);
				}				
			}			
		}
		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp") || nextPage.endsWith(".html")) {
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(
				Controller.class.getName() + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {

		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}	
}
