
package controller;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import databean.HistoryBean;
import databean.PositionBean;
import databean.PositionInfo;
import databean.TransactionBean;
import formbean.ViewCustomerForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;


public class ViewCustomerAction extends Action {

	private FormBeanFactory<ViewCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerForm.class);


	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO historyDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO=model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		historyDAO = model.getFundPriceHistoryDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "viewCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				ViewCustomerForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);

				// read all customers into list
				request.setAttribute("customerList", customerDAO.match());

				DecimalFormat df3 = new DecimalFormat("#,##0.000");
				DecimalFormat df2 = new DecimalFormat(	"###,##0.00");

				// If no params were passed, return with no errors so that the
				// form
				// will be presented (we assume for the first time).
				if (!form.isPresent()) {
					return "viewCustomer.jsp";
				}

				// Any validation errors?
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "viewCustomer.jsp";
				}

				if (customerDAO.read(form.getUserName()) == null) {
					errors.add("Customer does not exist");
					return "viewCustomer.jsp";
				}

				CustomerBean customer=customerDAO.read(form.getUserName());

				TransactionBean[] tb = transactionDAO.match(MatchArg.equals(
						"userName", customer.getUserName()));

				// List of History Beans
				HistoryBean[] hb = null;


				hb = new HistoryBean[tb.length];

				for (int i = 0; i < tb.length; i++) {

					hb[i] = new HistoryBean();
					// Date from Transaction:

					String date = tb[i].getExecuteDate();
					if (date == null || date.length() == 0) {
						date = "Pending";
					}
					hb[i].setDate(date);

					// Operation
					int type = Integer.parseInt(tb[i].getTransactionType());
					String operation;
					if (type == 8) {
						operation = "Buy Fund";
					} else if (type == 4) {
						operation = "Sell Fund";
					} else if (type == 2) {
						operation = "Request Check";
					} else if (type == 1) {
						operation = "Deposit Check";
					} else
						operation = "Invalid Operation";
					hb[i].setOperation(operation);

					// Operation Type
					String transaction;
					if (type == 8 || type == 2) {
						transaction = "Debit";
					} else if (type == 4 || type == 1) {
						transaction = "Credit";
					} else
						transaction = "Invalid Type";
					hb[i].setType(transaction);

					// Fund Name
					int fundId;
					String fund;
					if (tb[i].getFundId() == 0) {
						fund = "";
					} else {
						fundId = tb[i].getFundId();
						fund = fundDAO.read(fundId).getName();
					}
					hb[i].setFund(fund);

					// Total Amount
					double amount = tb[i].getAmount() / 100.0;
					String total;
					NumberFormat formatter = new DecimalFormat("#,##0.00");
					if (amount == 0) {
						total = "";
						hb[i].setTotal(total);
					} else if (transaction.equals("Credit")) {
						total = "$" + formatter.format(amount) + "&nbsp;";
						hb[i].setTotal(total);
					} else if (transaction.equals("Debit")) {
						total = "<font color=\"red\">($"
								+ formatter.format(amount) + ")";
						hb[i].setTotal(total);
					}

					// Get Shares
					String totShares;
					if (operation.equals("Deposit Check")
							|| operation.equals("Request Check")) {
						totShares = "";
					} else if (operation.equals("Buy Fund")
							&& date.equals("Pending")) {
						totShares = "";
					} else {
						double shares = tb[i].getShares() / 1000.0;
						NumberFormat formatShare = new DecimalFormat(
								"#,##0.000");
						totShares = formatShare.format(shares);
					}
					hb[i].setTotShares(totShares);

					// Get Price
					String price;
					if (operation.equals("Deposit Check")
							|| operation.equals("Request Check")) {
						price = "";
						hb[i].setPrice(price);
					} else if (operation.equals("Sell Fund")
							&& date.equals("Pending")) {
						price = "";
						hb[i].setPrice(price);
					} else if (operation.equals("Buy Fund")
							&& date.equals("Pending")) {
						price = "";
						hb[i].setPrice(price);
					} else {
						long sharePrice = fundPriceHistoryDAO.read(tb[i].getFundId(), date).getPrice();
						NumberFormat sharePricer = new DecimalFormat(
								"#,##0.00");
						price = sharePricer.format(sharePrice / 100.0);
						hb[i].setPrice("$"+price);
					}

				}
				request.setAttribute("transactionList", hb);


				request.setAttribute("customerName",customer.getFirstName()+" "+customer.getLastName() );
				request.setAttribute("userName",customer.getUserName());
				request.setAttribute("address1",customer.getAddress1());
				request.setAttribute("address2",customer.getAddress2());
				request.setAttribute("state",customer.getState());
				request.setAttribute("city",customer.getCity());
				request.setAttribute("zip",customer.getZipcode());

				String lastDay = transactionDAO.getLastDate(customer);
				request.setAttribute("lastDay", lastDay == null ? "No recent transactions" : lastDay);

				request.setAttribute("cash",df2.format(customer.getCash() / 100.0));
				request.setAttribute("avai_cash",df2.format(transactionDAO.getValidBalance(customer.getUserName(), customer.getCash() / 100.0)));
				//System.out.println("checkpoint1");
				PositionBean[] fundList = positionDAO.match(MatchArg.equals("customerId",customer.getCustomerId()));
				request.setAttribute("fundList",fundList);

				PositionBean[] positionList = positionDAO.match(MatchArg.equals("customerId",customer.getCustomerId()));
				if(positionList != null) {
					List<PositionInfo> positionInfoList = new ArrayList<PositionInfo>();
					for(PositionBean a: positionList) {
						double shares = ((double)(a.getShares())/1000.0);

						double price = ((double)(historyDAO.getLatestFundPrice(a.getFundId()).getPrice() / 100.0));
						double value = shares * price;
						String name=fundDAO.read(a.getFundId()).getName();

						String sharesString = df3.format(shares);
						String priceString = df2.format(price);
						String valueString = df2.format(value);

						PositionInfo aInfo = new PositionInfo(name,sharesString,priceString,"$" + valueString);
						positionInfoList.add(aInfo);
					}
					request.setAttribute("positionInfoList",positionInfoList);

				}


				return "viewCustomerAccount.jsp";
			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");
				return "login.do";
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "viewCustomer.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "viewCustomer.jsp";
		}

	}
}
