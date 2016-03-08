package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.FundInfoBean;
import databean.FundPriceHistoryBean;
import databean.TransactionBean;
import databean.balanceInfo;
import formbean.BuyFundForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.TransactionDAO;

public class BuyFundAction extends Action {
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory.getInstance(BuyFundForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public BuyFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "buyFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			BuyFundForm buyFundForm = formBeanFactory.create(request);
			request.setAttribute("form", buyFundForm);

			if (session.getAttribute("user") == null) {
				return "login.do";
			}

			DecimalFormat df = new DecimalFormat("###,##0.00");
			FundBean[] fundList = fundDAO.match();
			if(fundList != null) {
				List<FundInfoBean> fundInfoList = new ArrayList<FundInfoBean>();
				for(FundBean a: fundList) {
					String name = a.getName();
					FundPriceHistoryBean historyBean = fundPriceHistoryDAO.getLatestFundPrice(a.getFundId());
					if(historyBean != null) {
						double price = ((double)(fundPriceHistoryDAO.getLatestFundPrice(a.getFundId()).getPrice() / 100.0));
						String priceString = df.format(price);
						FundInfoBean aInfo = new FundInfoBean(name, "$" + priceString);
						fundInfoList.add(aInfo);
					}
				}
				session.setAttribute("fundListInfoList", fundInfoList);
			}
			CustomerBean customerBean = (CustomerBean) session.getAttribute("user");
			Double cash = (double) (customerDAO.read(customerBean.getUserName()).getCash()/100.00);
			DecimalFormat df2 = new DecimalFormat(	"###,##0.00");
			//System.out.println(cash);
			request.setAttribute("avai_cash",df2.format(transactionDAO.getValidBalance(customerBean.getUserName(), cash)));
			//System.out.println(request.getAttribute("avai_cash"));
			if (!buyFundForm.isPresent()) {
				return "buyFund.jsp";
			}
			
			errors.addAll(buyFundForm.getValidationErrors());
			if (errors.size() != 0) {
				return "buyFund.jsp";
			}
			
			// Current customer and the customer ID
			
			String userName = customerBean.getUserName();
			int customerId = customerBean.getCustomerId();
			long curCash = customerBean.getCash() / 100;
			
			// Calculate shares
//			if (buyFundForm.getAmount())
			double amount = Double.parseDouble(buyFundForm.getAmount());
			if (amount < 1) {
				errors.add("Please enter an amount of at least $1. ");
				return "buyFund.jsp";
			}
			// Can't acceed 10,000,000
			if (amount > 1000000) {
				errors.add("Please enter an amount less than or equal to $ 1,000,000. ");
				return "buyFund.jsp";
			}
			if ((amount * 100.0 - (long) (amount * 100.0)) > 0) {
				errors.add("We only allow at most two decimals for amount");
				return "buyFund.jsp"; 
			}
			
			
			//Check valid balance

			double validBalance = transactionDAO.getValidBalance(userName, curCash);
//			balanceInfo balanceInfo = new balanceInfo(validBalance);
//			session.setAttribute("balanceInfo", balanceInfo);
			if (amount > validBalance) {
				errors.add("You do not have enough money to proceed with the transaction. ");

				return "buyFund.jsp";
			}

			// Get the fund ID of the fund name in form
			FundBean fundBean = fundDAO.read(buyFundForm.getName());
			if (fundBean == null) {
				errors.add("Fund does not exist");
				return "buyFund.jsp";
			}
			int fundId = fundBean.getFundId();

			// Create a transaction bean
			TransactionBean transactionBean = new TransactionBean();
			transactionBean.setCustomerId(customerId);
			transactionBean.setFundId(fundId);
			transactionBean.setUserName(customerBean.getUserName());
			transactionBean.setAmount((long)(amount * 100l));
			transactionBean.setTransactionType("8");
			transactionDAO.create(transactionBean);

			return "success-customer.jsp";
		} catch (NumberFormatException e) {
			errors.add("Either the number is too long or it is not a number. Please enter a valid number. ");
			return "buyFund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "buyFund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "buyFund.jsp";
		}
	}
}
